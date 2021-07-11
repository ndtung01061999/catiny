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

describe('GroupPost e2e test', () => {
  const groupPostPageUrl = '/group-post';
  const groupPostPageUrlPattern = new RegExp('/group-post(\\?.*)?$');
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
    cy.intercept('GET', '/api/group-posts+(?*|)').as('entitiesRequest');
    cy.intercept('POST', '/api/group-posts').as('postEntityRequest');
    cy.intercept('DELETE', '/api/group-posts/*').as('deleteEntityRequest');
  });

  it('should load GroupPosts', () => {
    cy.visit('/');
    cy.clickOnEntityMenuItem('group-post');
    cy.wait('@entitiesRequest').then(({ response }) => {
      if (response.body.length === 0) {
        cy.get(entityTableSelector).should('not.exist');
      } else {
        cy.get(entityTableSelector).should('exist');
      }
    });
    cy.getEntityHeading('GroupPost').should('exist');
    cy.url().should('match', groupPostPageUrlPattern);
  });

  it('should load details GroupPost page', function () {
    cy.visit(groupPostPageUrl);
    cy.wait('@entitiesRequest').then(({ response }) => {
      if (response.body.length === 0) {
        this.skip();
      }
    });
    cy.get(entityDetailsButtonSelector).first().click({ force: true });
    cy.getEntityDetailsHeading('groupPost');
    cy.get(entityDetailsBackButtonSelector).click({ force: true });
    cy.wait('@entitiesRequest').then(({ response }) => {
      expect(response.statusCode).to.equal(200);
    });
    cy.url().should('match', groupPostPageUrlPattern);
  });

  it('should load create GroupPost page', () => {
    cy.visit(groupPostPageUrl);
    cy.wait('@entitiesRequest');
    cy.get(entityCreateButtonSelector).click({ force: true });
    cy.getEntityCreateUpdateHeading('GroupPost');
    cy.get(entityCreateSaveButtonSelector).should('exist');
    cy.get(entityCreateCancelButtonSelector).click({ force: true });
    cy.wait('@entitiesRequest').then(({ response }) => {
      expect(response.statusCode).to.equal(200);
    });
    cy.url().should('match', groupPostPageUrlPattern);
  });

  it('should load edit GroupPost page', function () {
    cy.visit(groupPostPageUrl);
    cy.wait('@entitiesRequest').then(({ response }) => {
      if (response.body.length === 0) {
        this.skip();
      }
    });
    cy.get(entityEditButtonSelector).first().click({ force: true });
    cy.getEntityCreateUpdateHeading('GroupPost');
    cy.get(entityCreateSaveButtonSelector).should('exist');
    cy.get(entityCreateCancelButtonSelector).click({ force: true });
    cy.wait('@entitiesRequest').then(({ response }) => {
      expect(response.statusCode).to.equal(200);
    });
    cy.url().should('match', groupPostPageUrlPattern);
  });

  it('should create an instance of GroupPost', () => {
    cy.visit(groupPostPageUrl);
    cy.get(entityCreateButtonSelector).click({ force: true });
    cy.getEntityCreateUpdateHeading('GroupPost');

    cy.get(`[data-cy="uuid"]`)
      .type('e777abb1-3f06-4ee5-a68b-995884281302')
      .invoke('val')
      .should('match', new RegExp('e777abb1-3f06-4ee5-a68b-995884281302'));

    cy.get(`[data-cy="name"]`).type('cyan').should('have.value', 'cyan');

    cy.get(`[data-cy="quickInfo"]`)
      .type('../fake-data/blob/hipster.txt')
      .invoke('val')
      .should('match', new RegExp('../fake-data/blob/hipster.txt'));

    cy.setFieldSelectToLastOfEntity('profile');

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
    cy.url().should('match', groupPostPageUrlPattern);
  });

  it('should delete last instance of GroupPost', function () {
    cy.intercept('GET', '/api/group-posts/*').as('dialogDeleteRequest');
    cy.visit(groupPostPageUrl);
    cy.wait('@entitiesRequest').then(({ response }) => {
      if (response.body.length > 0) {
        cy.get(entityTableSelector).should('have.lengthOf', response.body.length);
        cy.get(entityDeleteButtonSelector).last().click({ force: true });
        cy.wait('@dialogDeleteRequest');
        cy.getEntityDeleteDialogHeading('groupPost').should('exist');
        cy.get(entityConfirmDeleteButtonSelector).click({ force: true });
        cy.wait('@deleteEntityRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(204);
        });
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', groupPostPageUrlPattern);
      } else {
        this.skip();
      }
    });
  });
});
