import {entityItemSelector} from '../../support/commands';
import {
  entityConfirmDeleteButtonSelector,
  entityCreateButtonSelector,
  entityCreateCancelButtonSelector,
  entityCreateSaveButtonSelector,
  entityDeleteButtonSelector,
  entityDetailsBackButtonSelector,
  entityDetailsButtonSelector,
  entityEditButtonSelector,
  entityTableSelector,
} from '../../support/entity';

describe('ClassInfo e2e test', () => {
  const classInfoPageUrl = '/class-info';
  const classInfoPageUrlPattern = new RegExp('/class-info(\\?.*)?$');
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
    cy.intercept('GET', '/api/class-infos+(?*|)').as('entitiesRequest');
    cy.intercept('POST', '/api/class-infos').as('postEntityRequest');
    cy.intercept('DELETE', '/api/class-infos/*').as('deleteEntityRequest');
  });

  it('should load ClassInfos', () => {
    cy.visit('/');
    cy.clickOnEntityMenuItem('class-info');
    cy.wait('@entitiesRequest').then(({ response }) => {
      if (response.body.length === 0) {
        cy.get(entityTableSelector).should('not.exist');
      } else {
        cy.get(entityTableSelector).should('exist');
      }
    });
    cy.getEntityHeading('ClassInfo').should('exist');
    cy.url().should('match', classInfoPageUrlPattern);
  });

  it('should load details ClassInfo page', function () {
    cy.visit(classInfoPageUrl);
    cy.wait('@entitiesRequest').then(({ response }) => {
      if (response.body.length === 0) {
        this.skip();
      }
    });
    cy.get(entityDetailsButtonSelector).first().click({ force: true });
    cy.getEntityDetailsHeading('classInfo');
    cy.get(entityDetailsBackButtonSelector).click({ force: true });
    cy.wait('@entitiesRequest').then(({ response }) => {
      expect(response.statusCode).to.equal(200);
    });
    cy.url().should('match', classInfoPageUrlPattern);
  });

  it('should load create ClassInfo page', () => {
    cy.visit(classInfoPageUrl);
    cy.wait('@entitiesRequest');
    cy.get(entityCreateButtonSelector).click({ force: true });
    cy.getEntityCreateUpdateHeading('ClassInfo');
    cy.get(entityCreateSaveButtonSelector).should('exist');
    cy.get(entityCreateCancelButtonSelector).click({ force: true });
    cy.wait('@entitiesRequest').then(({ response }) => {
      expect(response.statusCode).to.equal(200);
    });
    cy.url().should('match', classInfoPageUrlPattern);
  });

  it('should load edit ClassInfo page', function () {
    cy.visit(classInfoPageUrl);
    cy.wait('@entitiesRequest').then(({ response }) => {
      if (response.body.length === 0) {
        this.skip();
      }
    });
    cy.get(entityEditButtonSelector).first().click({ force: true });
    cy.getEntityCreateUpdateHeading('ClassInfo');
    cy.get(entityCreateSaveButtonSelector).should('exist');
    cy.get(entityCreateCancelButtonSelector).click({ force: true });
    cy.wait('@entitiesRequest').then(({ response }) => {
      expect(response.statusCode).to.equal(200);
    });
    cy.url().should('match', classInfoPageUrlPattern);
  });

  it('should create an instance of ClassInfo', () => {
    cy.visit(classInfoPageUrl);
    cy.get(entityCreateButtonSelector).click({ force: true });
    cy.getEntityCreateUpdateHeading('ClassInfo');

    cy.get(`[data-cy="uuid"]`)
      .type('eb9aff99-7742-4cd0-9942-e07c86bdbf4b')
      .invoke('val')
      .should('match', new RegExp('eb9aff99-7742-4cd0-9942-e07c86bdbf4b'));

    cy.get(`[data-cy="namePackage"]`).type('object-oriented mindshare Garden').should('have.value', 'object-oriented mindshare Garden');

    cy.get(`[data-cy="fullName"]`).type('Checking redundant Incredible').should('have.value', 'Checking redundant Incredible');

    cy.get(`[data-cy="className"]`).type('Garden redundant FTP').should('have.value', 'Garden redundant FTP');

    cy.get(entityCreateSaveButtonSelector).click({ force: true });
    cy.scrollTo('top', { ensureScrollable: false });
    cy.get(entityCreateSaveButtonSelector).should('not.exist');
    cy.wait('@postEntityRequest').then(({ response }) => {
      expect(response.statusCode).to.equal(201);
    });
    cy.wait('@entitiesRequest').then(({ response }) => {
      expect(response.statusCode).to.equal(200);
    });
    cy.url().should('match', classInfoPageUrlPattern);
  });

  it('should delete last instance of ClassInfo', function () {
    cy.intercept('GET', '/api/class-infos/*').as('dialogDeleteRequest');
    cy.visit(classInfoPageUrl);
    cy.wait('@entitiesRequest').then(({ response }) => {
      if (response.body.length > 0) {
        cy.get(entityTableSelector).should('have.lengthOf', response.body.length);
        cy.get(entityDeleteButtonSelector).last().click({ force: true });
        cy.wait('@dialogDeleteRequest');
        cy.getEntityDeleteDialogHeading('classInfo').should('exist');
        cy.get(entityConfirmDeleteButtonSelector).click({ force: true });
        cy.wait('@deleteEntityRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(204);
        });
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', classInfoPageUrlPattern);
      } else {
        this.skip();
      }
    });
  });
});
