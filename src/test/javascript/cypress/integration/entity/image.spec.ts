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

describe('Image e2e test', () => {
  const imagePageUrl = '/image';
  const imagePageUrlPattern = new RegExp('/image(\\?.*)?$');
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
    cy.intercept('GET', '/api/images+(?*|)').as('entitiesRequest');
    cy.intercept('POST', '/api/images').as('postEntityRequest');
    cy.intercept('DELETE', '/api/images/*').as('deleteEntityRequest');
  });

  it('should load Images', () => {
    cy.visit('/');
    cy.clickOnEntityMenuItem('image');
    cy.wait('@entitiesRequest').then(({ response }) => {
      if (response.body.length === 0) {
        cy.get(entityTableSelector).should('not.exist');
      } else {
        cy.get(entityTableSelector).should('exist');
      }
    });
    cy.getEntityHeading('Image').should('exist');
    cy.url().should('match', imagePageUrlPattern);
  });

  it('should load details Image page', function () {
    cy.visit(imagePageUrl);
    cy.wait('@entitiesRequest').then(({ response }) => {
      if (response.body.length === 0) {
        this.skip();
      }
    });
    cy.get(entityDetailsButtonSelector).first().click({ force: true });
    cy.getEntityDetailsHeading('image');
    cy.get(entityDetailsBackButtonSelector).click({ force: true });
    cy.wait('@entitiesRequest').then(({ response }) => {
      expect(response.statusCode).to.equal(200);
    });
    cy.url().should('match', imagePageUrlPattern);
  });

  it('should load create Image page', () => {
    cy.visit(imagePageUrl);
    cy.wait('@entitiesRequest');
    cy.get(entityCreateButtonSelector).click({ force: true });
    cy.getEntityCreateUpdateHeading('Image');
    cy.get(entityCreateSaveButtonSelector).should('exist');
    cy.get(entityCreateCancelButtonSelector).click({ force: true });
    cy.wait('@entitiesRequest').then(({ response }) => {
      expect(response.statusCode).to.equal(200);
    });
    cy.url().should('match', imagePageUrlPattern);
  });

  it('should load edit Image page', function () {
    cy.visit(imagePageUrl);
    cy.wait('@entitiesRequest').then(({ response }) => {
      if (response.body.length === 0) {
        this.skip();
      }
    });
    cy.get(entityEditButtonSelector).first().click({ force: true });
    cy.getEntityCreateUpdateHeading('Image');
    cy.get(entityCreateSaveButtonSelector).should('exist');
    cy.get(entityCreateCancelButtonSelector).click({ force: true });
    cy.wait('@entitiesRequest').then(({ response }) => {
      expect(response.statusCode).to.equal(200);
    });
    cy.url().should('match', imagePageUrlPattern);
  });

  it('should create an instance of Image', () => {
    cy.visit(imagePageUrl);
    cy.get(entityCreateButtonSelector).click({ force: true });
    cy.getEntityCreateUpdateHeading('Image');

    cy.get(`[data-cy="uuid"]`)
      .type('dc41b75e-9d16-4ede-a878-f0a89dce6bb3')
      .invoke('val')
      .should('match', new RegExp('dc41b75e-9d16-4ede-a878-f0a89dce6bb3'));

    cy.get(`[data-cy="name"]`).type('Vista').should('have.value', 'Vista');

    cy.get(`[data-cy="width"]`).type('8011').should('have.value', '8011');

    cy.get(`[data-cy="height"]`).type('99599').should('have.value', '99599');

    cy.get(`[data-cy="quality"]`).type('1').should('have.value', '1');

    cy.get(`[data-cy="pixelSize"]`).type('15768').should('have.value', '15768');

    cy.get(`[data-cy="priorityIndex"]`).type('88024').should('have.value', '88024');

    cy.get(`[data-cy="dataSize"]`).type('16202').should('have.value', '16202');

    cy.setFieldSelectToLastOfEntity('fileInfo');

    cy.setFieldSelectToLastOfEntity('baseInfo');

    cy.setFieldSelectToLastOfEntity('imageOriginal');

    cy.get(entityCreateSaveButtonSelector).click({ force: true });
    cy.scrollTo('top', { ensureScrollable: false });
    cy.get(entityCreateSaveButtonSelector).should('not.exist');
    cy.wait('@postEntityRequest').then(({ response }) => {
      expect(response.statusCode).to.equal(201);
    });
    cy.wait('@entitiesRequest').then(({ response }) => {
      expect(response.statusCode).to.equal(200);
    });
    cy.url().should('match', imagePageUrlPattern);
  });

  it('should delete last instance of Image', function () {
    cy.intercept('GET', '/api/images/*').as('dialogDeleteRequest');
    cy.visit(imagePageUrl);
    cy.wait('@entitiesRequest').then(({ response }) => {
      if (response.body.length > 0) {
        cy.get(entityTableSelector).should('have.lengthOf', response.body.length);
        cy.get(entityDeleteButtonSelector).last().click({ force: true });
        cy.wait('@dialogDeleteRequest');
        cy.getEntityDeleteDialogHeading('image').should('exist');
        cy.get(entityConfirmDeleteButtonSelector).click({ force: true });
        cy.wait('@deleteEntityRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(204);
        });
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', imagePageUrlPattern);
      } else {
        this.skip();
      }
    });
  });
});
