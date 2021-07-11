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

describe('HanhChinhVN e2e test', () => {
  const hanhChinhVNPageUrl = '/hanh-chinh-vn';
  const hanhChinhVNPageUrlPattern = new RegExp('/hanh-chinh-vn(\\?.*)?$');
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
    cy.intercept('GET', '/api/hanh-chinh-vns+(?*|)').as('entitiesRequest');
    cy.intercept('POST', '/api/hanh-chinh-vns').as('postEntityRequest');
    cy.intercept('DELETE', '/api/hanh-chinh-vns/*').as('deleteEntityRequest');
  });

  it('should load HanhChinhVNS', () => {
    cy.visit('/');
    cy.clickOnEntityMenuItem('hanh-chinh-vn');
    cy.wait('@entitiesRequest').then(({ response }) => {
      if (response.body.length === 0) {
        cy.get(entityTableSelector).should('not.exist');
      } else {
        cy.get(entityTableSelector).should('exist');
      }
    });
    cy.getEntityHeading('HanhChinhVN').should('exist');
    cy.url().should('match', hanhChinhVNPageUrlPattern);
  });

  it('should load details HanhChinhVN page', function () {
    cy.visit(hanhChinhVNPageUrl);
    cy.wait('@entitiesRequest').then(({ response }) => {
      if (response.body.length === 0) {
        this.skip();
      }
    });
    cy.get(entityDetailsButtonSelector).first().click({ force: true });
    cy.getEntityDetailsHeading('hanhChinhVN');
    cy.get(entityDetailsBackButtonSelector).click({ force: true });
    cy.wait('@entitiesRequest').then(({ response }) => {
      expect(response.statusCode).to.equal(200);
    });
    cy.url().should('match', hanhChinhVNPageUrlPattern);
  });

  it('should load create HanhChinhVN page', () => {
    cy.visit(hanhChinhVNPageUrl);
    cy.wait('@entitiesRequest');
    cy.get(entityCreateButtonSelector).click({ force: true });
    cy.getEntityCreateUpdateHeading('HanhChinhVN');
    cy.get(entityCreateSaveButtonSelector).should('exist');
    cy.get(entityCreateCancelButtonSelector).click({ force: true });
    cy.wait('@entitiesRequest').then(({ response }) => {
      expect(response.statusCode).to.equal(200);
    });
    cy.url().should('match', hanhChinhVNPageUrlPattern);
  });

  it('should load edit HanhChinhVN page', function () {
    cy.visit(hanhChinhVNPageUrl);
    cy.wait('@entitiesRequest').then(({ response }) => {
      if (response.body.length === 0) {
        this.skip();
      }
    });
    cy.get(entityEditButtonSelector).first().click({ force: true });
    cy.getEntityCreateUpdateHeading('HanhChinhVN');
    cy.get(entityCreateSaveButtonSelector).should('exist');
    cy.get(entityCreateCancelButtonSelector).click({ force: true });
    cy.wait('@entitiesRequest').then(({ response }) => {
      expect(response.statusCode).to.equal(200);
    });
    cy.url().should('match', hanhChinhVNPageUrlPattern);
  });

  it('should create an instance of HanhChinhVN', () => {
    cy.visit(hanhChinhVNPageUrl);
    cy.get(entityCreateButtonSelector).click({ force: true });
    cy.getEntityCreateUpdateHeading('HanhChinhVN');

    cy.get(`[data-cy="name"]`).type('Innovative Buckinghamshire').should('have.value', 'Innovative Buckinghamshire');

    cy.get(`[data-cy="slug"]`).type('Shirt').should('have.value', 'Shirt');

    cy.get(`[data-cy="type"]`).type('invoice').should('have.value', 'invoice');

    cy.get(`[data-cy="nameWithType"]`).type('Tchad').should('have.value', 'Tchad');

    cy.get(`[data-cy="code"]`).type('bypassing Checking Peso').should('have.value', 'bypassing Checking Peso');

    cy.get(`[data-cy="parentCode"]`).type('Cotton Ohio Cameroon').should('have.value', 'Cotton Ohio Cameroon');

    cy.get(`[data-cy="path"]`).type('Mouse compress Mountains').should('have.value', 'Mouse compress Mountains');

    cy.get(`[data-cy="pathWithType"]`).type('Account Keyboard Plastic').should('have.value', 'Account Keyboard Plastic');

    cy.get(entityCreateSaveButtonSelector).click({ force: true });
    cy.scrollTo('top', { ensureScrollable: false });
    cy.get(entityCreateSaveButtonSelector).should('not.exist');
    cy.wait('@postEntityRequest').then(({ response }) => {
      expect(response.statusCode).to.equal(201);
    });
    cy.wait('@entitiesRequest').then(({ response }) => {
      expect(response.statusCode).to.equal(200);
    });
    cy.url().should('match', hanhChinhVNPageUrlPattern);
  });

  it('should delete last instance of HanhChinhVN', function () {
    cy.intercept('GET', '/api/hanh-chinh-vns/*').as('dialogDeleteRequest');
    cy.visit(hanhChinhVNPageUrl);
    cy.wait('@entitiesRequest').then(({ response }) => {
      if (response.body.length > 0) {
        cy.get(entityTableSelector).should('have.lengthOf', response.body.length);
        cy.get(entityDeleteButtonSelector).last().click({ force: true });
        cy.wait('@dialogDeleteRequest');
        cy.getEntityDeleteDialogHeading('hanhChinhVN').should('exist');
        cy.get(entityConfirmDeleteButtonSelector).click({ force: true });
        cy.wait('@deleteEntityRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(204);
        });
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', hanhChinhVNPageUrlPattern);
      } else {
        this.skip();
      }
    });
  });
});
