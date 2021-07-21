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

describe('NewsFeed e2e test', () => {
  const newsFeedPageUrl = '/news-feed';
  const newsFeedPageUrlPattern = new RegExp('/news-feed(\\?.*)?$');
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
    cy.intercept('GET', '/api/news-feeds+(?*|)').as('entitiesRequest');
    cy.intercept('POST', '/api/news-feeds').as('postEntityRequest');
    cy.intercept('DELETE', '/api/news-feeds/*').as('deleteEntityRequest');
  });

  it('should load NewsFeeds', () => {
    cy.visit('/');
    cy.clickOnEntityMenuItem('news-feed');
    cy.wait('@entitiesRequest').then(({ response }) => {
      if (response.body.length === 0) {
        cy.get(entityTableSelector).should('not.exist');
      } else {
        cy.get(entityTableSelector).should('exist');
      }
    });
    cy.getEntityHeading('NewsFeed').should('exist');
    cy.url().should('match', newsFeedPageUrlPattern);
  });

  it('should load details NewsFeed page', function () {
    cy.visit(newsFeedPageUrl);
    cy.wait('@entitiesRequest').then(({ response }) => {
      if (response.body.length === 0) {
        this.skip();
      }
    });
    cy.get(entityDetailsButtonSelector).first().click({ force: true });
    cy.getEntityDetailsHeading('newsFeed');
    cy.get(entityDetailsBackButtonSelector).click({ force: true });
    cy.wait('@entitiesRequest').then(({ response }) => {
      expect(response.statusCode).to.equal(200);
    });
    cy.url().should('match', newsFeedPageUrlPattern);
  });

  it('should load create NewsFeed page', () => {
    cy.visit(newsFeedPageUrl);
    cy.wait('@entitiesRequest');
    cy.get(entityCreateButtonSelector).click({ force: true });
    cy.getEntityCreateUpdateHeading('NewsFeed');
    cy.get(entityCreateSaveButtonSelector).should('exist');
    cy.get(entityCreateCancelButtonSelector).click({ force: true });
    cy.wait('@entitiesRequest').then(({ response }) => {
      expect(response.statusCode).to.equal(200);
    });
    cy.url().should('match', newsFeedPageUrlPattern);
  });

  it('should load edit NewsFeed page', function () {
    cy.visit(newsFeedPageUrl);
    cy.wait('@entitiesRequest').then(({ response }) => {
      if (response.body.length === 0) {
        this.skip();
      }
    });
    cy.get(entityEditButtonSelector).first().click({ force: true });
    cy.getEntityCreateUpdateHeading('NewsFeed');
    cy.get(entityCreateSaveButtonSelector).should('exist');
    cy.get(entityCreateCancelButtonSelector).click({ force: true });
    cy.wait('@entitiesRequest').then(({ response }) => {
      expect(response.statusCode).to.equal(200);
    });
    cy.url().should('match', newsFeedPageUrlPattern);
  });

  it('should create an instance of NewsFeed', () => {
    cy.visit(newsFeedPageUrl);
    cy.get(entityCreateButtonSelector).click({ force: true });
    cy.getEntityCreateUpdateHeading('NewsFeed');

    cy.get(`[data-cy="uuid"]`)
      .type('ceef04ac-47a9-45ef-b475-8752f9eb6467')
      .invoke('val')
      .should('match', new RegExp('ceef04ac-47a9-45ef-b475-8752f9eb6467'));

    cy.get(`[data-cy="priorityIndex"]`).type('88100').should('have.value', '88100');

    cy.setFieldSelectToLastOfEntity('baseInfo');

    cy.setFieldSelectToLastOfEntity('post');

    cy.get(entityCreateSaveButtonSelector).click({ force: true });
    cy.scrollTo('top', { ensureScrollable: false });
    cy.get(entityCreateSaveButtonSelector).should('not.exist');
    cy.wait('@postEntityRequest').then(({ response }) => {
      expect(response.statusCode).to.equal(201);
    });
    cy.wait('@entitiesRequest').then(({ response }) => {
      expect(response.statusCode).to.equal(200);
    });
    cy.url().should('match', newsFeedPageUrlPattern);
  });

  it('should delete last instance of NewsFeed', function () {
    cy.intercept('GET', '/api/news-feeds/*').as('dialogDeleteRequest');
    cy.visit(newsFeedPageUrl);
    cy.wait('@entitiesRequest').then(({ response }) => {
      if (response.body.length > 0) {
        cy.get(entityTableSelector).should('have.lengthOf', response.body.length);
        cy.get(entityDeleteButtonSelector).last().click({ force: true });
        cy.wait('@dialogDeleteRequest');
        cy.getEntityDeleteDialogHeading('newsFeed').should('exist');
        cy.get(entityConfirmDeleteButtonSelector).click({ force: true });
        cy.wait('@deleteEntityRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(204);
        });
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', newsFeedPageUrlPattern);
      } else {
        this.skip();
      }
    });
  });
});
