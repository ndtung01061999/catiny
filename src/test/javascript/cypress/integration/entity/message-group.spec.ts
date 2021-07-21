import { entityItemSelector } from '../../support/commands';
import {
  entityTableSelector,
  entityDetailsButtonSelector,
  entityDetailsBackButtonSelector,
  entityCreateButtonSelector,
  entityCreateSaveButtonSelector,
  entityCreateCancelButtonSelector,
  entityEditButtonSelector,
  entityDeleteButtonSelector,
  entityConfirmDeleteButtonSelector,
} from '../../support/entity';

describe('MessageGroup e2e test', () => {
  const messageGroupPageUrl = '/message-group';
  const messageGroupPageUrlPattern = new RegExp('/message-group(\\?.*)?$');
  const username = Cypress.env('E2E_USERNAME') ?? 'admin';
  const password = Cypress.env('E2E_PASSWORD') ?? 'admin';

  before(() => {
    cy.window().then(win => {
      win.sessionStorage.clear();
    });
    cy.visit('');
    cy.login(username, password);
    cy.get(entityItemSelector).should('exist');
  });

  beforeEach(() => {
    cy.intercept('GET', '/api/message-groups+(?*|)').as('entitiesRequest');
    cy.intercept('POST', '/api/message-groups').as('postEntityRequest');
    cy.intercept('DELETE', '/api/message-groups/*').as('deleteEntityRequest');
  });

  it('should load MessageGroups', () => {
    cy.visit('/');
    cy.clickOnEntityMenuItem('message-group');
    cy.wait('@entitiesRequest').then(({ response }) => {
      if (response.body.length === 0) {
        cy.get(entityTableSelector).should('not.exist');
      } else {
        cy.get(entityTableSelector).should('exist');
      }
    });
    cy.getEntityHeading('MessageGroup').should('exist');
    cy.url().should('match', messageGroupPageUrlPattern);
  });

  it('should load details MessageGroup page', function () {
    cy.visit(messageGroupPageUrl);
    cy.wait('@entitiesRequest').then(({ response }) => {
      if (response.body.length === 0) {
        this.skip();
      }
    });
    cy.get(entityDetailsButtonSelector).first().click({ force: true });
    cy.getEntityDetailsHeading('messageGroup');
    cy.get(entityDetailsBackButtonSelector).click({ force: true });
    cy.wait('@entitiesRequest').then(({ response }) => {
      expect(response.statusCode).to.equal(200);
    });
    cy.url().should('match', messageGroupPageUrlPattern);
  });

  it('should load create MessageGroup page', () => {
    cy.visit(messageGroupPageUrl);
    cy.wait('@entitiesRequest');
    cy.get(entityCreateButtonSelector).click({ force: true });
    cy.getEntityCreateUpdateHeading('MessageGroup');
    cy.get(entityCreateSaveButtonSelector).should('exist');
    cy.get(entityCreateCancelButtonSelector).click({ force: true });
    cy.wait('@entitiesRequest').then(({ response }) => {
      expect(response.statusCode).to.equal(200);
    });
    cy.url().should('match', messageGroupPageUrlPattern);
  });

  it('should load edit MessageGroup page', function () {
    cy.visit(messageGroupPageUrl);
    cy.wait('@entitiesRequest').then(({ response }) => {
      if (response.body.length === 0) {
        this.skip();
      }
    });
    cy.get(entityEditButtonSelector).first().click({ force: true });
    cy.getEntityCreateUpdateHeading('MessageGroup');
    cy.get(entityCreateSaveButtonSelector).should('exist');
    cy.get(entityCreateCancelButtonSelector).click({ force: true });
    cy.wait('@entitiesRequest').then(({ response }) => {
      expect(response.statusCode).to.equal(200);
    });
    cy.url().should('match', messageGroupPageUrlPattern);
  });

  it('should create an instance of MessageGroup', () => {
    cy.visit(messageGroupPageUrl);
    cy.get(entityCreateButtonSelector).click({ force: true });
    cy.getEntityCreateUpdateHeading('MessageGroup');

    cy.get(`[data-cy="uuid"]`)
      .type('213dfe71-d01d-4382-b229-11afcde7b10d')
      .invoke('val')
      .should('match', new RegExp('213dfe71-d01d-4382-b229-11afcde7b10d'));

    cy.get(`[data-cy="groupName"]`).type('toolset Hat').should('have.value', 'toolset Hat');

    cy.get(`[data-cy="avatar"]`)
      .type('../fake-data/blob/hipster.txt')
      .invoke('val')
      .should('match', new RegExp('../fake-data/blob/hipster.txt'));

    cy.get(`[data-cy="addBy"]`).type('Persevering Island Toys').should('have.value', 'Persevering Island Toys');

    cy.setFieldSelectToLastOfEntity('baseInfo');

    cy.get(entityCreateSaveButtonSelector).click({ force: true });
    cy.scrollTo('top', { ensureScrollable: false });
    cy.get(entityCreateSaveButtonSelector).should('not.exist');
    cy.wait('@postEntityRequest').then(({ response }) => {
      expect(response.statusCode).to.equal(201);
    });
    cy.wait('@entitiesRequest').then(({ response }) => {
      expect(response.statusCode).to.equal(200);
    });
    cy.url().should('match', messageGroupPageUrlPattern);
  });

  it('should delete last instance of MessageGroup', function () {
    cy.intercept('GET', '/api/message-groups/*').as('dialogDeleteRequest');
    cy.visit(messageGroupPageUrl);
    cy.wait('@entitiesRequest').then(({ response }) => {
      if (response.body.length > 0) {
        cy.get(entityTableSelector).should('have.lengthOf', response.body.length);
        cy.get(entityDeleteButtonSelector).last().click({ force: true });
        cy.wait('@dialogDeleteRequest');
        cy.getEntityDeleteDialogHeading('messageGroup').should('exist');
        cy.get(entityConfirmDeleteButtonSelector).click({ force: true });
        cy.wait('@deleteEntityRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(204);
        });
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', messageGroupPageUrlPattern);
      } else {
        this.skip();
      }
    });
  });
});
