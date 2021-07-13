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

describe('BaseInfo e2e test', () => {
  const baseInfoPageUrl = '/base-info';
  const baseInfoPageUrlPattern = new RegExp('/base-info(\\?.*)?$');
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
    cy.intercept('GET', '/api/base-infos+(?*|)').as('entitiesRequest');
    cy.intercept('POST', '/api/base-infos').as('postEntityRequest');
    cy.intercept('DELETE', '/api/base-infos/*').as('deleteEntityRequest');
  });

  it('should load BaseInfos', () => {
    cy.visit('/');
    cy.clickOnEntityMenuItem('base-info');
    cy.wait('@entitiesRequest').then(({ response }) => {
      if (response.body.length === 0) {
        cy.get(entityTableSelector).should('not.exist');
      } else {
        cy.get(entityTableSelector).should('exist');
      }
    });
    cy.getEntityHeading('BaseInfo').should('exist');
    cy.url().should('match', baseInfoPageUrlPattern);
  });

  it('should load details BaseInfo page', function () {
    cy.visit(baseInfoPageUrl);
    cy.wait('@entitiesRequest').then(({ response }) => {
      if (response.body.length === 0) {
        this.skip();
      }
    });
    cy.get(entityDetailsButtonSelector).first().click({ force: true });
    cy.getEntityDetailsHeading('baseInfo');
    cy.get(entityDetailsBackButtonSelector).click({ force: true });
    cy.wait('@entitiesRequest').then(({ response }) => {
      expect(response.statusCode).to.equal(200);
    });
    cy.url().should('match', baseInfoPageUrlPattern);
  });

  it('should load create BaseInfo page', () => {
    cy.visit(baseInfoPageUrl);
    cy.wait('@entitiesRequest');
    cy.get(entityCreateButtonSelector).click({ force: true });
    cy.getEntityCreateUpdateHeading('BaseInfo');
    cy.get(entityCreateSaveButtonSelector).should('exist');
    cy.get(entityCreateCancelButtonSelector).click({ force: true });
    cy.wait('@entitiesRequest').then(({ response }) => {
      expect(response.statusCode).to.equal(200);
    });
    cy.url().should('match', baseInfoPageUrlPattern);
  });

  it('should load edit BaseInfo page', function () {
    cy.visit(baseInfoPageUrl);
    cy.wait('@entitiesRequest').then(({ response }) => {
      if (response.body.length === 0) {
        this.skip();
      }
    });
    cy.get(entityEditButtonSelector).first().click({ force: true });
    cy.getEntityCreateUpdateHeading('BaseInfo');
    cy.get(entityCreateSaveButtonSelector).should('exist');
    cy.get(entityCreateCancelButtonSelector).click({ force: true });
    cy.wait('@entitiesRequest').then(({ response }) => {
      expect(response.statusCode).to.equal(200);
    });
    cy.url().should('match', baseInfoPageUrlPattern);
  });

  it('should create an instance of BaseInfo', () => {
    cy.visit(baseInfoPageUrl);
    cy.get(entityCreateButtonSelector).click({ force: true });
    cy.getEntityCreateUpdateHeading('BaseInfo');

    cy.get(`[data-cy="processStatus"]`).select('NEED_PROCESS_NOW');

    cy.get(`[data-cy="owner"]`)
      .type('../fake-data/blob/hipster.txt')
      .invoke('val')
      .should('match', new RegExp('../fake-data/blob/hipster.txt'));

    cy.get(`[data-cy="role"]`)
      .type('../fake-data/blob/hipster.txt')
      .invoke('val')
      .should('match', new RegExp('../fake-data/blob/hipster.txt'));

    cy.get(`[data-cy="modifiedClass"]`).type('Games').should('have.value', 'Games');

    cy.get(`[data-cy="createdDate"]`).type('2021-07-10T04:22').should('have.value', '2021-07-10T04:22');

    cy.get(`[data-cy="modifiedDate"]`).type('2021-07-09T17:29').should('have.value', '2021-07-09T17:29');

    cy.get(`[data-cy="createdBy"]`).type('indexing copy Communications').should('have.value', 'indexing copy Communications');

    cy.get(`[data-cy="modifiedBy"]`).type('Ball ubiquitous Dinar').should('have.value', 'Ball ubiquitous Dinar');

    cy.get(`[data-cy="notes"]`)
      .type('../fake-data/blob/hipster.txt')
      .invoke('val')
      .should('match', new RegExp('../fake-data/blob/hipster.txt'));

    cy.get(`[data-cy="historyUpdate"]`)
      .type('../fake-data/blob/hipster.txt')
      .invoke('val')
      .should('match', new RegExp('../fake-data/blob/hipster.txt'));

    cy.get(`[data-cy="deleted"]`).should('not.be.checked');
    cy.get(`[data-cy="deleted"]`).click().should('be.checked');

    cy.get(entityCreateSaveButtonSelector).click({ force: true });
    cy.scrollTo('top', { ensureScrollable: false });
    cy.get(entityCreateSaveButtonSelector).should('not.exist');
    cy.wait('@postEntityRequest').then(({ response }) => {
      expect(response.statusCode).to.equal(201);
    });
    cy.wait('@entitiesRequest').then(({ response }) => {
      expect(response.statusCode).to.equal(200);
    });
    cy.url().should('match', baseInfoPageUrlPattern);
  });

  it('should delete last instance of BaseInfo', function () {
    cy.intercept('GET', '/api/base-infos/*').as('dialogDeleteRequest');
    cy.visit(baseInfoPageUrl);
    cy.wait('@entitiesRequest').then(({ response }) => {
      if (response.body.length > 0) {
        cy.get(entityTableSelector).should('have.lengthOf', response.body.length);
        cy.get(entityDeleteButtonSelector).last().click({ force: true });
        cy.wait('@dialogDeleteRequest');
        cy.getEntityDeleteDialogHeading('baseInfo').should('exist');
        cy.get(entityConfirmDeleteButtonSelector).click({ force: true });
        cy.wait('@deleteEntityRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(204);
        });
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', baseInfoPageUrlPattern);
      } else {
        this.skip();
      }
    });
  });
});
