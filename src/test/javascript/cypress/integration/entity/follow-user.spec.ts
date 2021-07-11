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

describe('FollowUser e2e test', () => {
  const followUserPageUrl = '/follow-user';
  const followUserPageUrlPattern = new RegExp('/follow-user(\\?.*)?$');
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
    cy.intercept('GET', '/api/follow-users+(?*|)').as('entitiesRequest');
    cy.intercept('POST', '/api/follow-users').as('postEntityRequest');
    cy.intercept('DELETE', '/api/follow-users/*').as('deleteEntityRequest');
  });

  it('should load FollowUsers', () => {
    cy.visit('/');
    cy.clickOnEntityMenuItem('follow-user');
    cy.wait('@entitiesRequest').then(({ response }) => {
      if (response.body.length === 0) {
        cy.get(entityTableSelector).should('not.exist');
      } else {
        cy.get(entityTableSelector).should('exist');
      }
    });
    cy.getEntityHeading('FollowUser').should('exist');
    cy.url().should('match', followUserPageUrlPattern);
  });

  it('should load details FollowUser page', function () {
    cy.visit(followUserPageUrl);
    cy.wait('@entitiesRequest').then(({ response }) => {
      if (response.body.length === 0) {
        this.skip();
      }
    });
    cy.get(entityDetailsButtonSelector).first().click({ force: true });
    cy.getEntityDetailsHeading('followUser');
    cy.get(entityDetailsBackButtonSelector).click({ force: true });
    cy.wait('@entitiesRequest').then(({ response }) => {
      expect(response.statusCode).to.equal(200);
    });
    cy.url().should('match', followUserPageUrlPattern);
  });

  it('should load create FollowUser page', () => {
    cy.visit(followUserPageUrl);
    cy.wait('@entitiesRequest');
    cy.get(entityCreateButtonSelector).click({ force: true });
    cy.getEntityCreateUpdateHeading('FollowUser');
    cy.get(entityCreateSaveButtonSelector).should('exist');
    cy.get(entityCreateCancelButtonSelector).click({ force: true });
    cy.wait('@entitiesRequest').then(({ response }) => {
      expect(response.statusCode).to.equal(200);
    });
    cy.url().should('match', followUserPageUrlPattern);
  });

  it('should load edit FollowUser page', function () {
    cy.visit(followUserPageUrl);
    cy.wait('@entitiesRequest').then(({ response }) => {
      if (response.body.length === 0) {
        this.skip();
      }
    });
    cy.get(entityEditButtonSelector).first().click({ force: true });
    cy.getEntityCreateUpdateHeading('FollowUser');
    cy.get(entityCreateSaveButtonSelector).should('exist');
    cy.get(entityCreateCancelButtonSelector).click({ force: true });
    cy.wait('@entitiesRequest').then(({ response }) => {
      expect(response.statusCode).to.equal(200);
    });
    cy.url().should('match', followUserPageUrlPattern);
  });

  it('should create an instance of FollowUser', () => {
    cy.visit(followUserPageUrl);
    cy.get(entityCreateButtonSelector).click({ force: true });
    cy.getEntityCreateUpdateHeading('FollowUser');

    cy.get(`[data-cy="uuid"]`)
      .type('468112e4-8a83-4f64-bf7b-9e3b4384e413')
      .invoke('val')
      .should('match', new RegExp('468112e4-8a83-4f64-bf7b-9e3b4384e413'));

    cy.setFieldSelectToLastOfEntity('baseInfo');

    cy.setFieldSelectToLastOfEntity('followUserDetails');

    cy.setFieldSelectToLastOfEntity('masterUser');

    cy.get(entityCreateSaveButtonSelector).click({ force: true });
    cy.scrollTo('top', { ensureScrollable: false });
    cy.get(entityCreateSaveButtonSelector).should('not.exist');
    cy.wait('@postEntityRequest').then(({ response }) => {
      expect(response.statusCode).to.equal(201);
    });
    cy.wait('@entitiesRequest').then(({ response }) => {
      expect(response.statusCode).to.equal(200);
    });
    cy.url().should('match', followUserPageUrlPattern);
  });

  it('should delete last instance of FollowUser', function () {
    cy.intercept('GET', '/api/follow-users/*').as('dialogDeleteRequest');
    cy.visit(followUserPageUrl);
    cy.wait('@entitiesRequest').then(({ response }) => {
      if (response.body.length > 0) {
        cy.get(entityTableSelector).should('have.lengthOf', response.body.length);
        cy.get(entityDeleteButtonSelector).last().click({ force: true });
        cy.wait('@dialogDeleteRequest');
        cy.getEntityDeleteDialogHeading('followUser').should('exist');
        cy.get(entityConfirmDeleteButtonSelector).click({ force: true });
        cy.wait('@deleteEntityRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(204);
        });
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', followUserPageUrlPattern);
      } else {
        this.skip();
      }
    });
  });
});
