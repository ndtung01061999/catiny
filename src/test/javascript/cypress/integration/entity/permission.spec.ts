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

describe('Permission e2e test', () => {
  const permissionPageUrl = '/permission';
  const permissionPageUrlPattern = new RegExp('/permission(\\?.*)?$');
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
    cy.intercept('GET', '/api/permissions+(?*|)').as('entitiesRequest');
    cy.intercept('POST', '/api/permissions').as('postEntityRequest');
    cy.intercept('DELETE', '/api/permissions/*').as('deleteEntityRequest');
  });

  it('should load Permissions', () => {
    cy.visit('/');
    cy.clickOnEntityMenuItem('permission');
    cy.wait('@entitiesRequest').then(({ response }) => {
      if (response.body.length === 0) {
        cy.get(entityTableSelector).should('not.exist');
      } else {
        cy.get(entityTableSelector).should('exist');
      }
    });
    cy.getEntityHeading('Permission').should('exist');
    cy.url().should('match', permissionPageUrlPattern);
  });

  it('should load details Permission page', function () {
    cy.visit(permissionPageUrl);
    cy.wait('@entitiesRequest').then(({ response }) => {
      if (response.body.length === 0) {
        this.skip();
      }
    });
    cy.get(entityDetailsButtonSelector).first().click({ force: true });
    cy.getEntityDetailsHeading('permission');
    cy.get(entityDetailsBackButtonSelector).click({ force: true });
    cy.wait('@entitiesRequest').then(({ response }) => {
      expect(response.statusCode).to.equal(200);
    });
    cy.url().should('match', permissionPageUrlPattern);
  });

  it('should load create Permission page', () => {
    cy.visit(permissionPageUrl);
    cy.wait('@entitiesRequest');
    cy.get(entityCreateButtonSelector).click({ force: true });
    cy.getEntityCreateUpdateHeading('Permission');
    cy.get(entityCreateSaveButtonSelector).should('exist');
    cy.get(entityCreateCancelButtonSelector).click({ force: true });
    cy.wait('@entitiesRequest').then(({ response }) => {
      expect(response.statusCode).to.equal(200);
    });
    cy.url().should('match', permissionPageUrlPattern);
  });

  it('should load edit Permission page', function () {
    cy.visit(permissionPageUrl);
    cy.wait('@entitiesRequest').then(({ response }) => {
      if (response.body.length === 0) {
        this.skip();
      }
    });
    cy.get(entityEditButtonSelector).first().click({ force: true });
    cy.getEntityCreateUpdateHeading('Permission');
    cy.get(entityCreateSaveButtonSelector).should('exist');
    cy.get(entityCreateCancelButtonSelector).click({ force: true });
    cy.wait('@entitiesRequest').then(({ response }) => {
      expect(response.statusCode).to.equal(200);
    });
    cy.url().should('match', permissionPageUrlPattern);
  });

  it('should create an instance of Permission', () =>
  {
    cy.visit(permissionPageUrl);
    cy.get(entityCreateButtonSelector).click({force: true});
    cy.getEntityCreateUpdateHeading('Permission');

    cy.get(`[data-cy="uuid"]`)
      .type('ad9cceda-8fb0-4937-bd6b-0ea946cb2558')
      .invoke('val')
      .should('match', new RegExp('ad9cceda-8fb0-4937-bd6b-0ea946cb2558'));

    cy.get(`[data-cy="read"]`).should('not.be.checked');
    cy.get(`[data-cy="read"]`).click().should('be.checked');

    cy.get(`[data-cy="write"]`).should('not.be.checked');
    cy.get(`[data-cy="write"]`).click().should('be.checked');

    cy.get(`[data-cy="share"]`).should('not.be.checked');
    cy.get(`[data-cy="share"]`).click().should('be.checked');

    cy.get(`[data-cy="delete"]`).should('not.be.checked');
    cy.get(`[data-cy="delete"]`).click().should('be.checked');

    cy.get(`[data-cy="add"]`).should('not.be.checked');
    cy.get(`[data-cy="add"]`).click().should('be.checked');

    cy.get(`[data-cy="level"]`).type('76413').should('have.value', '76413');

    cy.setFieldSelectToLastOfEntity('baseInfo');

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
    cy.url().should('match', permissionPageUrlPattern);
  });

  it('should delete last instance of Permission', function () {
    cy.intercept('GET', '/api/permissions/*').as('dialogDeleteRequest');
    cy.visit(permissionPageUrl);
    cy.wait('@entitiesRequest').then(({ response }) => {
      if (response.body.length > 0) {
        cy.get(entityTableSelector).should('have.lengthOf', response.body.length);
        cy.get(entityDeleteButtonSelector).last().click({ force: true });
        cy.wait('@dialogDeleteRequest');
        cy.getEntityDeleteDialogHeading('permission').should('exist');
        cy.get(entityConfirmDeleteButtonSelector).click({ force: true });
        cy.wait('@deleteEntityRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(204);
        });
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', permissionPageUrlPattern);
      } else {
        this.skip();
      }
    });
  });
});
