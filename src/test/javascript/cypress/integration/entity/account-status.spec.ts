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

describe('AccountStatus e2e test', () => {
  const accountStatusPageUrl = '/account-status';
  const accountStatusPageUrlPattern = new RegExp('/account-status(\\?.*)?$');
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
    cy.intercept('GET', '/api/account-statuses+(?*|)').as('entitiesRequest');
    cy.intercept('POST', '/api/account-statuses').as('postEntityRequest');
    cy.intercept('DELETE', '/api/account-statuses/*').as('deleteEntityRequest');
  });

  it('should load AccountStatuses', () => {
    cy.visit('/');
    cy.clickOnEntityMenuItem('account-status');
    cy.wait('@entitiesRequest').then(({ response }) => {
      if (response.body.length === 0) {
        cy.get(entityTableSelector).should('not.exist');
      } else {
        cy.get(entityTableSelector).should('exist');
      }
    });
    cy.getEntityHeading('AccountStatus').should('exist');
    cy.url().should('match', accountStatusPageUrlPattern);
  });

  it('should load details AccountStatus page', function () {
    cy.visit(accountStatusPageUrl);
    cy.wait('@entitiesRequest').then(({ response }) => {
      if (response.body.length === 0) {
        this.skip();
      }
    });
    cy.get(entityDetailsButtonSelector).first().click({ force: true });
    cy.getEntityDetailsHeading('accountStatus');
    cy.get(entityDetailsBackButtonSelector).click({ force: true });
    cy.wait('@entitiesRequest').then(({ response }) => {
      expect(response.statusCode).to.equal(200);
    });
    cy.url().should('match', accountStatusPageUrlPattern);
  });

  it('should load create AccountStatus page', () => {
    cy.visit(accountStatusPageUrl);
    cy.wait('@entitiesRequest');
    cy.get(entityCreateButtonSelector).click({ force: true });
    cy.getEntityCreateUpdateHeading('AccountStatus');
    cy.get(entityCreateSaveButtonSelector).should('exist');
    cy.get(entityCreateCancelButtonSelector).click({ force: true });
    cy.wait('@entitiesRequest').then(({ response }) => {
      expect(response.statusCode).to.equal(200);
    });
    cy.url().should('match', accountStatusPageUrlPattern);
  });

  it('should load edit AccountStatus page', function () {
    cy.visit(accountStatusPageUrl);
    cy.wait('@entitiesRequest').then(({ response }) => {
      if (response.body.length === 0) {
        this.skip();
      }
    });
    cy.get(entityEditButtonSelector).first().click({ force: true });
    cy.getEntityCreateUpdateHeading('AccountStatus');
    cy.get(entityCreateSaveButtonSelector).should('exist');
    cy.get(entityCreateCancelButtonSelector).click({ force: true });
    cy.wait('@entitiesRequest').then(({ response }) => {
      expect(response.statusCode).to.equal(200);
    });
    cy.url().should('match', accountStatusPageUrlPattern);
  });

  it('should create an instance of AccountStatus', () => {
    cy.visit(accountStatusPageUrl);
    cy.get(entityCreateButtonSelector).click({ force: true });
    cy.getEntityCreateUpdateHeading('AccountStatus');

    cy.get(`[data-cy="uuid"]`)
      .type('334ca9b7-9dba-4948-b924-2c83ec92d422')
      .invoke('val')
      .should('match', new RegExp('334ca9b7-9dba-4948-b924-2c83ec92d422'));

    cy.get(`[data-cy="accountStatus"]`).select('TEMPORARILY_ABSENT');

    cy.get(`[data-cy="lastVisited"]`).type('2021-07-10T13:25').should('have.value', '2021-07-10T13:25');

    cy.get(`[data-cy="statusComment"]`).type('Refined synergies').should('have.value', 'Refined synergies');

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
    cy.url().should('match', accountStatusPageUrlPattern);
  });

  it('should delete last instance of AccountStatus', function () {
    cy.intercept('GET', '/api/account-statuses/*').as('dialogDeleteRequest');
    cy.visit(accountStatusPageUrl);
    cy.wait('@entitiesRequest').then(({ response }) => {
      if (response.body.length > 0) {
        cy.get(entityTableSelector).should('have.lengthOf', response.body.length);
        cy.get(entityDeleteButtonSelector).last().click({ force: true });
        cy.wait('@dialogDeleteRequest');
        cy.getEntityDeleteDialogHeading('accountStatus').should('exist');
        cy.get(entityConfirmDeleteButtonSelector).click({ force: true });
        cy.wait('@deleteEntityRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(204);
        });
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', accountStatusPageUrlPattern);
      } else {
        this.skip();
      }
    });
  });
});
