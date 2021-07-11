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

describe('VideoStream e2e test', () => {
  const videoStreamPageUrl = '/video-stream';
  const videoStreamPageUrlPattern = new RegExp('/video-stream(\\?.*)?$');
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
    cy.intercept('GET', '/api/video-streams+(?*|)').as('entitiesRequest');
    cy.intercept('POST', '/api/video-streams').as('postEntityRequest');
    cy.intercept('DELETE', '/api/video-streams/*').as('deleteEntityRequest');
  });

  it('should load VideoStreams', () => {
    cy.visit('/');
    cy.clickOnEntityMenuItem('video-stream');
    cy.wait('@entitiesRequest').then(({ response }) => {
      if (response.body.length === 0) {
        cy.get(entityTableSelector).should('not.exist');
      } else {
        cy.get(entityTableSelector).should('exist');
      }
    });
    cy.getEntityHeading('VideoStream').should('exist');
    cy.url().should('match', videoStreamPageUrlPattern);
  });

  it('should load details VideoStream page', function () {
    cy.visit(videoStreamPageUrl);
    cy.wait('@entitiesRequest').then(({ response }) => {
      if (response.body.length === 0) {
        this.skip();
      }
    });
    cy.get(entityDetailsButtonSelector).first().click({ force: true });
    cy.getEntityDetailsHeading('videoStream');
    cy.get(entityDetailsBackButtonSelector).click({ force: true });
    cy.wait('@entitiesRequest').then(({ response }) => {
      expect(response.statusCode).to.equal(200);
    });
    cy.url().should('match', videoStreamPageUrlPattern);
  });

  it('should load create VideoStream page', () => {
    cy.visit(videoStreamPageUrl);
    cy.wait('@entitiesRequest');
    cy.get(entityCreateButtonSelector).click({ force: true });
    cy.getEntityCreateUpdateHeading('VideoStream');
    cy.get(entityCreateSaveButtonSelector).should('exist');
    cy.get(entityCreateCancelButtonSelector).click({ force: true });
    cy.wait('@entitiesRequest').then(({ response }) => {
      expect(response.statusCode).to.equal(200);
    });
    cy.url().should('match', videoStreamPageUrlPattern);
  });

  it('should load edit VideoStream page', function () {
    cy.visit(videoStreamPageUrl);
    cy.wait('@entitiesRequest').then(({ response }) => {
      if (response.body.length === 0) {
        this.skip();
      }
    });
    cy.get(entityEditButtonSelector).first().click({ force: true });
    cy.getEntityCreateUpdateHeading('VideoStream');
    cy.get(entityCreateSaveButtonSelector).should('exist');
    cy.get(entityCreateCancelButtonSelector).click({ force: true });
    cy.wait('@entitiesRequest').then(({ response }) => {
      expect(response.statusCode).to.equal(200);
    });
    cy.url().should('match', videoStreamPageUrlPattern);
  });

  it('should create an instance of VideoStream', () => {
    cy.visit(videoStreamPageUrl);
    cy.get(entityCreateButtonSelector).click({ force: true });
    cy.getEntityCreateUpdateHeading('VideoStream');

    cy.get(`[data-cy="uuid"]`)
      .type('44be9581-0e96-48cd-a1f0-f5c057238764')
      .invoke('val')
      .should('match', new RegExp('44be9581-0e96-48cd-a1f0-f5c057238764'));

    cy.setFieldSelectToLastOfEntity('video');

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
    cy.url().should('match', videoStreamPageUrlPattern);
  });

  it('should delete last instance of VideoStream', function () {
    cy.intercept('GET', '/api/video-streams/*').as('dialogDeleteRequest');
    cy.visit(videoStreamPageUrl);
    cy.wait('@entitiesRequest').then(({ response }) => {
      if (response.body.length > 0) {
        cy.get(entityTableSelector).should('have.lengthOf', response.body.length);
        cy.get(entityDeleteButtonSelector).last().click({ force: true });
        cy.wait('@dialogDeleteRequest');
        cy.getEntityDeleteDialogHeading('videoStream').should('exist');
        cy.get(entityConfirmDeleteButtonSelector).click({ force: true });
        cy.wait('@deleteEntityRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(204);
        });
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', videoStreamPageUrlPattern);
      } else {
        this.skip();
      }
    });
  });
});
