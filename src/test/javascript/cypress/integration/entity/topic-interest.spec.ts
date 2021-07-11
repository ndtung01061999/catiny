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

describe('TopicInterest e2e test', () => {
  const topicInterestPageUrl = '/topic-interest';
  const topicInterestPageUrlPattern = new RegExp('/topic-interest(\\?.*)?$');
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
    cy.intercept('GET', '/api/topic-interests+(?*|)').as('entitiesRequest');
    cy.intercept('POST', '/api/topic-interests').as('postEntityRequest');
    cy.intercept('DELETE', '/api/topic-interests/*').as('deleteEntityRequest');
  });

  it('should load TopicInterests', () => {
    cy.visit('/');
    cy.clickOnEntityMenuItem('topic-interest');
    cy.wait('@entitiesRequest').then(({ response }) => {
      if (response.body.length === 0) {
        cy.get(entityTableSelector).should('not.exist');
      } else {
        cy.get(entityTableSelector).should('exist');
      }
    });
    cy.getEntityHeading('TopicInterest').should('exist');
    cy.url().should('match', topicInterestPageUrlPattern);
  });

  it('should load details TopicInterest page', function () {
    cy.visit(topicInterestPageUrl);
    cy.wait('@entitiesRequest').then(({ response }) => {
      if (response.body.length === 0) {
        this.skip();
      }
    });
    cy.get(entityDetailsButtonSelector).first().click({ force: true });
    cy.getEntityDetailsHeading('topicInterest');
    cy.get(entityDetailsBackButtonSelector).click({ force: true });
    cy.wait('@entitiesRequest').then(({ response }) => {
      expect(response.statusCode).to.equal(200);
    });
    cy.url().should('match', topicInterestPageUrlPattern);
  });

  it('should load create TopicInterest page', () => {
    cy.visit(topicInterestPageUrl);
    cy.wait('@entitiesRequest');
    cy.get(entityCreateButtonSelector).click({ force: true });
    cy.getEntityCreateUpdateHeading('TopicInterest');
    cy.get(entityCreateSaveButtonSelector).should('exist');
    cy.get(entityCreateCancelButtonSelector).click({ force: true });
    cy.wait('@entitiesRequest').then(({ response }) => {
      expect(response.statusCode).to.equal(200);
    });
    cy.url().should('match', topicInterestPageUrlPattern);
  });

  it('should load edit TopicInterest page', function () {
    cy.visit(topicInterestPageUrl);
    cy.wait('@entitiesRequest').then(({ response }) => {
      if (response.body.length === 0) {
        this.skip();
      }
    });
    cy.get(entityEditButtonSelector).first().click({ force: true });
    cy.getEntityCreateUpdateHeading('TopicInterest');
    cy.get(entityCreateSaveButtonSelector).should('exist');
    cy.get(entityCreateCancelButtonSelector).click({ force: true });
    cy.wait('@entitiesRequest').then(({ response }) => {
      expect(response.statusCode).to.equal(200);
    });
    cy.url().should('match', topicInterestPageUrlPattern);
  });

  it('should create an instance of TopicInterest', () => {
    cy.visit(topicInterestPageUrl);
    cy.get(entityCreateButtonSelector).click({ force: true });
    cy.getEntityCreateUpdateHeading('TopicInterest');

    cy.get(`[data-cy="uuid"]`)
      .type('b44772e6-6bfc-4bc1-a27e-152570f0b1c0')
      .invoke('val')
      .should('match', new RegExp('b44772e6-6bfc-4bc1-a27e-152570f0b1c0'));

    cy.get(`[data-cy="title"]`).type('Orchestrator Auto').should('have.value', 'Orchestrator Auto');

    cy.get(`[data-cy="content"]`)
      .type('../fake-data/blob/hipster.txt')
      .invoke('val')
      .should('match', new RegExp('../fake-data/blob/hipster.txt'));

    cy.setFieldSelectToLastOfEntity('baseInfo');

    cy.setFieldSelectToLastOfEntity('post');

    cy.setFieldSelectToLastOfEntity('pagePost');

    cy.setFieldSelectToLastOfEntity('groupPost');

    cy.get(entityCreateSaveButtonSelector).click({ force: true });
    cy.scrollTo('top', { ensureScrollable: false });
    cy.get(entityCreateSaveButtonSelector).should('not.exist');
    cy.wait('@postEntityRequest').then(({ response }) => {
      expect(response.statusCode).to.equal(201);
    });
    cy.wait('@entitiesRequest').then(({ response }) => {
      expect(response.statusCode).to.equal(200);
    });
    cy.url().should('match', topicInterestPageUrlPattern);
  });

  it('should delete last instance of TopicInterest', function () {
    cy.intercept('GET', '/api/topic-interests/*').as('dialogDeleteRequest');
    cy.visit(topicInterestPageUrl);
    cy.wait('@entitiesRequest').then(({ response }) => {
      if (response.body.length > 0) {
        cy.get(entityTableSelector).should('have.lengthOf', response.body.length);
        cy.get(entityDeleteButtonSelector).last().click({ force: true });
        cy.wait('@dialogDeleteRequest');
        cy.getEntityDeleteDialogHeading('topicInterest').should('exist');
        cy.get(entityConfirmDeleteButtonSelector).click({ force: true });
        cy.wait('@deleteEntityRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(204);
        });
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', topicInterestPageUrlPattern);
      } else {
        this.skip();
      }
    });
  });
});
