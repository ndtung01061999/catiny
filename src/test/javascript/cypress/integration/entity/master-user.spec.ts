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

describe('MasterUser e2e test', () => {
  const masterUserPageUrl = '/master-user';
  const masterUserPageUrlPattern = new RegExp('/master-user(\\?.*)?$');
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
    cy.intercept('GET', '/api/master-users+(?*|)').as('entitiesRequest');
    cy.intercept('POST', '/api/master-users').as('postEntityRequest');
    cy.intercept('DELETE', '/api/master-users/*').as('deleteEntityRequest');
  });

  it('should load MasterUsers', () => {
    cy.visit('/');
    cy.clickOnEntityMenuItem('master-user');
    cy.wait('@entitiesRequest').then(({ response }) => {
      if (response.body.length === 0) {
        cy.get(entityTableSelector).should('not.exist');
      } else {
        cy.get(entityTableSelector).should('exist');
      }
    });
    cy.getEntityHeading('MasterUser').should('exist');
    cy.url().should('match', masterUserPageUrlPattern);
  });

  it('should load details MasterUser page', function () {
    cy.visit(masterUserPageUrl);
    cy.wait('@entitiesRequest').then(({ response }) => {
      if (response.body.length === 0) {
        this.skip();
      }
    });
    cy.get(entityDetailsButtonSelector).first().click({ force: true });
    cy.getEntityDetailsHeading('masterUser');
    cy.get(entityDetailsBackButtonSelector).click({ force: true });
    cy.wait('@entitiesRequest').then(({ response }) => {
      expect(response.statusCode).to.equal(200);
    });
    cy.url().should('match', masterUserPageUrlPattern);
  });

  it('should load create MasterUser page', () => {
    cy.visit(masterUserPageUrl);
    cy.wait('@entitiesRequest');
    cy.get(entityCreateButtonSelector).click({ force: true });
    cy.getEntityCreateUpdateHeading('MasterUser');
    cy.get(entityCreateSaveButtonSelector).should('exist');
    cy.get(entityCreateCancelButtonSelector).click({ force: true });
    cy.wait('@entitiesRequest').then(({ response }) => {
      expect(response.statusCode).to.equal(200);
    });
    cy.url().should('match', masterUserPageUrlPattern);
  });

  it('should load edit MasterUser page', function () {
    cy.visit(masterUserPageUrl);
    cy.wait('@entitiesRequest').then(({ response }) => {
      if (response.body.length === 0) {
        this.skip();
      }
    });
    cy.get(entityEditButtonSelector).first().click({ force: true });
    cy.getEntityCreateUpdateHeading('MasterUser');
    cy.get(entityCreateSaveButtonSelector).should('exist');
    cy.get(entityCreateCancelButtonSelector).click({ force: true });
    cy.wait('@entitiesRequest').then(({ response }) => {
      expect(response.statusCode).to.equal(200);
    });
    cy.url().should('match', masterUserPageUrlPattern);
  });

  it.skip('should create an instance of MasterUser', () => {
    cy.visit(masterUserPageUrl);
    cy.get(entityCreateButtonSelector).click({ force: true });
    cy.getEntityCreateUpdateHeading('MasterUser');

    cy.get(`[data-cy="uuid"]`)
      .type('396f6716-f7e8-4775-8c77-e8516421d5e6')
      .invoke('val')
      .should('match', new RegExp('396f6716-f7e8-4775-8c77-e8516421d5e6'));

    cy.get(`[data-cy="fullName"]`).type('Buckinghamshire').should('have.value', 'Buckinghamshire');

    cy.get(`[data-cy="nickname"]`).type('Open-source global Bedfordshire').should('have.value', 'Open-source global Bedfordshire');

    cy.get(`[data-cy="quickInfo"]`)
      .type('../fake-data/blob/hipster.txt')
      .invoke('val')
      .should('match', new RegExp('../fake-data/blob/hipster.txt'));

    cy.setFieldSelectToLastOfEntity('user');

    cy.setFieldSelectToLastOfEntity('myProfile');

    cy.setFieldSelectToLastOfEntity('myAccountStatus');

    cy.setFieldSelectToLastOfEntity('myRank');

    cy.setFieldSelectToLastOfEntity('avatar');

    cy.setFieldSelectToLastOfEntity('baseInfo');

    cy.setFieldSelectToLastOfEntity('myGroupPost');

    cy.setFieldSelectToLastOfEntity('messageGroup');

    cy.setFieldSelectToLastOfEntity('topicInterest');

    cy.get(entityCreateSaveButtonSelector).click({ force: true });
    cy.scrollTo('top', { ensureScrollable: false });
    cy.get(entityCreateSaveButtonSelector).should('not.exist');
    cy.wait('@postEntityRequest').then(({ response }) => {
      expect(response.statusCode).to.equal(201);
    });
    cy.wait('@entitiesRequest').then(({ response }) => {
      expect(response.statusCode).to.equal(200);
    });
    cy.url().should('match', masterUserPageUrlPattern);
  });

  it.skip('should delete last instance of MasterUser', function () {
    cy.intercept('GET', '/api/master-users/*').as('dialogDeleteRequest');
    cy.visit(masterUserPageUrl);
    cy.wait('@entitiesRequest').then(({ response }) => {
      if (response.body.length > 0) {
        cy.get(entityTableSelector).should('have.lengthOf', response.body.length);
        cy.get(entityDeleteButtonSelector).last().click({ force: true });
        cy.wait('@dialogDeleteRequest');
        cy.getEntityDeleteDialogHeading('masterUser').should('exist');
        cy.get(entityConfirmDeleteButtonSelector).click({ force: true });
        cy.wait('@deleteEntityRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(204);
        });
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', masterUserPageUrlPattern);
      } else {
        this.skip();
      }
    });
  });
});
