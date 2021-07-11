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

describe('Video e2e test', () => {
  const videoPageUrl = '/video';
  const videoPageUrlPattern = new RegExp('/video(\\?.*)?$');
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
    cy.intercept('GET', '/api/videos+(?*|)').as('entitiesRequest');
    cy.intercept('POST', '/api/videos').as('postEntityRequest');
    cy.intercept('DELETE', '/api/videos/*').as('deleteEntityRequest');
  });

  it('should load Videos', () => {
    cy.visit('/');
    cy.clickOnEntityMenuItem('video');
    cy.wait('@entitiesRequest').then(({ response }) => {
      if (response.body.length === 0) {
        cy.get(entityTableSelector).should('not.exist');
      } else {
        cy.get(entityTableSelector).should('exist');
      }
    });
    cy.getEntityHeading('Video').should('exist');
    cy.url().should('match', videoPageUrlPattern);
  });

  it('should load details Video page', function () {
    cy.visit(videoPageUrl);
    cy.wait('@entitiesRequest').then(({ response }) => {
      if (response.body.length === 0) {
        this.skip();
      }
    });
    cy.get(entityDetailsButtonSelector).first().click({ force: true });
    cy.getEntityDetailsHeading('video');
    cy.get(entityDetailsBackButtonSelector).click({ force: true });
    cy.wait('@entitiesRequest').then(({ response }) => {
      expect(response.statusCode).to.equal(200);
    });
    cy.url().should('match', videoPageUrlPattern);
  });

  it('should load create Video page', () => {
    cy.visit(videoPageUrl);
    cy.wait('@entitiesRequest');
    cy.get(entityCreateButtonSelector).click({ force: true });
    cy.getEntityCreateUpdateHeading('Video');
    cy.get(entityCreateSaveButtonSelector).should('exist');
    cy.get(entityCreateCancelButtonSelector).click({ force: true });
    cy.wait('@entitiesRequest').then(({ response }) => {
      expect(response.statusCode).to.equal(200);
    });
    cy.url().should('match', videoPageUrlPattern);
  });

  it('should load edit Video page', function () {
    cy.visit(videoPageUrl);
    cy.wait('@entitiesRequest').then(({ response }) => {
      if (response.body.length === 0) {
        this.skip();
      }
    });
    cy.get(entityEditButtonSelector).first().click({ force: true });
    cy.getEntityCreateUpdateHeading('Video');
    cy.get(entityCreateSaveButtonSelector).should('exist');
    cy.get(entityCreateCancelButtonSelector).click({ force: true });
    cy.wait('@entitiesRequest').then(({ response }) => {
      expect(response.statusCode).to.equal(200);
    });
    cy.url().should('match', videoPageUrlPattern);
  });

  it('should create an instance of Video', () => {
    cy.visit(videoPageUrl);
    cy.get(entityCreateButtonSelector).click({ force: true });
    cy.getEntityCreateUpdateHeading('Video');

    cy.get(`[data-cy="uuid"]`)
      .type('3332781b-cd12-46e2-bcf8-d195811c8d28')
      .invoke('val')
      .should('match', new RegExp('3332781b-cd12-46e2-bcf8-d195811c8d28'));

    cy.get(`[data-cy="name"]`).type('eyeballs SAS').should('have.value', 'eyeballs SAS');

    cy.setFieldSelectToLastOfEntity('fileInfo');

    cy.setFieldSelectToLastOfEntity('baseInfo');

    cy.setFieldSelectToLastOfEntity('videoOriginal');

    cy.setFieldSelectToLastOfEntity('event');

    cy.get(entityCreateSaveButtonSelector).click({ force: true });
    cy.scrollTo('top', { ensureScrollable: false });
    cy.get(entityCreateSaveButtonSelector).should('not.exist');
    cy.wait('@postEntityRequest').then(({ response }) => {
      expect(response.statusCode).to.equal(201);
    });
    cy.wait('@entitiesRequest').then(({ response }) => {
      expect(response.statusCode).to.equal(200);
    });
    cy.url().should('match', videoPageUrlPattern);
  });

  it('should delete last instance of Video', function () {
    cy.intercept('GET', '/api/videos/*').as('dialogDeleteRequest');
    cy.visit(videoPageUrl);
    cy.wait('@entitiesRequest').then(({ response }) => {
      if (response.body.length > 0) {
        cy.get(entityTableSelector).should('have.lengthOf', response.body.length);
        cy.get(entityDeleteButtonSelector).last().click({ force: true });
        cy.wait('@dialogDeleteRequest');
        cy.getEntityDeleteDialogHeading('video').should('exist');
        cy.get(entityConfirmDeleteButtonSelector).click({ force: true });
        cy.wait('@deleteEntityRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(204);
        });
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', videoPageUrlPattern);
      } else {
        this.skip();
      }
    });
  });
});
