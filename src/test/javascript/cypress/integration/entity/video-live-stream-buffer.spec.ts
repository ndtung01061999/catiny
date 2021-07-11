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

describe('VideoLiveStreamBuffer e2e test', () => {
  const videoLiveStreamBufferPageUrl = '/video-live-stream-buffer';
  const videoLiveStreamBufferPageUrlPattern = new RegExp('/video-live-stream-buffer(\\?.*)?$');
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
    cy.intercept('GET', '/api/video-live-stream-buffers+(?*|)').as('entitiesRequest');
    cy.intercept('POST', '/api/video-live-stream-buffers').as('postEntityRequest');
    cy.intercept('DELETE', '/api/video-live-stream-buffers/*').as('deleteEntityRequest');
  });

  it('should load VideoLiveStreamBuffers', () => {
    cy.visit('/');
    cy.clickOnEntityMenuItem('video-live-stream-buffer');
    cy.wait('@entitiesRequest').then(({ response }) => {
      if (response.body.length === 0) {
        cy.get(entityTableSelector).should('not.exist');
      } else {
        cy.get(entityTableSelector).should('exist');
      }
    });
    cy.getEntityHeading('VideoLiveStreamBuffer').should('exist');
    cy.url().should('match', videoLiveStreamBufferPageUrlPattern);
  });

  it('should load details VideoLiveStreamBuffer page', function () {
    cy.visit(videoLiveStreamBufferPageUrl);
    cy.wait('@entitiesRequest').then(({ response }) => {
      if (response.body.length === 0) {
        this.skip();
      }
    });
    cy.get(entityDetailsButtonSelector).first().click({ force: true });
    cy.getEntityDetailsHeading('videoLiveStreamBuffer');
    cy.get(entityDetailsBackButtonSelector).click({ force: true });
    cy.wait('@entitiesRequest').then(({ response }) => {
      expect(response.statusCode).to.equal(200);
    });
    cy.url().should('match', videoLiveStreamBufferPageUrlPattern);
  });

  it('should load create VideoLiveStreamBuffer page', () => {
    cy.visit(videoLiveStreamBufferPageUrl);
    cy.wait('@entitiesRequest');
    cy.get(entityCreateButtonSelector).click({ force: true });
    cy.getEntityCreateUpdateHeading('VideoLiveStreamBuffer');
    cy.get(entityCreateSaveButtonSelector).should('exist');
    cy.get(entityCreateCancelButtonSelector).click({ force: true });
    cy.wait('@entitiesRequest').then(({ response }) => {
      expect(response.statusCode).to.equal(200);
    });
    cy.url().should('match', videoLiveStreamBufferPageUrlPattern);
  });

  it('should load edit VideoLiveStreamBuffer page', function () {
    cy.visit(videoLiveStreamBufferPageUrl);
    cy.wait('@entitiesRequest').then(({ response }) => {
      if (response.body.length === 0) {
        this.skip();
      }
    });
    cy.get(entityEditButtonSelector).first().click({ force: true });
    cy.getEntityCreateUpdateHeading('VideoLiveStreamBuffer');
    cy.get(entityCreateSaveButtonSelector).should('exist');
    cy.get(entityCreateCancelButtonSelector).click({ force: true });
    cy.wait('@entitiesRequest').then(({ response }) => {
      expect(response.statusCode).to.equal(200);
    });
    cy.url().should('match', videoLiveStreamBufferPageUrlPattern);
  });

  it('should create an instance of VideoLiveStreamBuffer', () => {
    cy.visit(videoLiveStreamBufferPageUrl);
    cy.get(entityCreateButtonSelector).click({ force: true });
    cy.getEntityCreateUpdateHeading('VideoLiveStreamBuffer');

    cy.get(`[data-cy="uuid"]`)
      .type('b6722cec-31c2-4cdf-8414-991c7a4cf861')
      .invoke('val')
      .should('match', new RegExp('b6722cec-31c2-4cdf-8414-991c7a4cf861'));

    cy.setFieldImageAsBytesOfEntity('bufferData', 'integration-test.png', 'image/png');

    cy.setFieldSelectToLastOfEntity('baseInfo');

    cy.setFieldSelectToLastOfEntity('videoStream');

    // since cypress clicks submit too fast before the blob fields are validated
    cy.wait(200); // eslint-disable-line cypress/no-unnecessary-waiting
    cy.get(entityCreateSaveButtonSelector).click({ force: true });
    cy.scrollTo('top', { ensureScrollable: false });
    cy.get(entityCreateSaveButtonSelector).should('not.exist');
    cy.wait('@postEntityRequest').then(({ response }) => {
      expect(response.statusCode).to.equal(201);
    });
    cy.wait('@entitiesRequest').then(({ response }) => {
      expect(response.statusCode).to.equal(200);
    });
    cy.url().should('match', videoLiveStreamBufferPageUrlPattern);
  });

  it('should delete last instance of VideoLiveStreamBuffer', function () {
    cy.intercept('GET', '/api/video-live-stream-buffers/*').as('dialogDeleteRequest');
    cy.visit(videoLiveStreamBufferPageUrl);
    cy.wait('@entitiesRequest').then(({ response }) => {
      if (response.body.length > 0) {
        cy.get(entityTableSelector).should('have.lengthOf', response.body.length);
        cy.get(entityDeleteButtonSelector).last().click({ force: true });
        cy.wait('@dialogDeleteRequest');
        cy.getEntityDeleteDialogHeading('videoLiveStreamBuffer').should('exist');
        cy.get(entityConfirmDeleteButtonSelector).click({ force: true });
        cy.wait('@deleteEntityRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(204);
        });
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', videoLiveStreamBufferPageUrlPattern);
      } else {
        this.skip();
      }
    });
  });
});
