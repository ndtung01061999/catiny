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

describe('Event e2e test', () => {
  const eventPageUrl = '/event';
  const eventPageUrlPattern = new RegExp('/event(\\?.*)?$');
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
    cy.intercept('GET', '/api/events+(?*|)').as('entitiesRequest');
    cy.intercept('POST', '/api/events').as('postEntityRequest');
    cy.intercept('DELETE', '/api/events/*').as('deleteEntityRequest');
  });

  it('should load Events', () => {
    cy.visit('/');
    cy.clickOnEntityMenuItem('event');
    cy.wait('@entitiesRequest').then(({ response }) => {
      if (response.body.length === 0) {
        cy.get(entityTableSelector).should('not.exist');
      } else {
        cy.get(entityTableSelector).should('exist');
      }
    });
    cy.getEntityHeading('Event').should('exist');
    cy.url().should('match', eventPageUrlPattern);
  });

  it('should load details Event page', function () {
    cy.visit(eventPageUrl);
    cy.wait('@entitiesRequest').then(({ response }) => {
      if (response.body.length === 0) {
        this.skip();
      }
    });
    cy.get(entityDetailsButtonSelector).first().click({ force: true });
    cy.getEntityDetailsHeading('event');
    cy.get(entityDetailsBackButtonSelector).click({ force: true });
    cy.wait('@entitiesRequest').then(({ response }) => {
      expect(response.statusCode).to.equal(200);
    });
    cy.url().should('match', eventPageUrlPattern);
  });

  it('should load create Event page', () => {
    cy.visit(eventPageUrl);
    cy.wait('@entitiesRequest');
    cy.get(entityCreateButtonSelector).click({ force: true });
    cy.getEntityCreateUpdateHeading('Event');
    cy.get(entityCreateSaveButtonSelector).should('exist');
    cy.get(entityCreateCancelButtonSelector).click({ force: true });
    cy.wait('@entitiesRequest').then(({ response }) => {
      expect(response.statusCode).to.equal(200);
    });
    cy.url().should('match', eventPageUrlPattern);
  });

  it('should load edit Event page', function () {
    cy.visit(eventPageUrl);
    cy.wait('@entitiesRequest').then(({ response }) => {
      if (response.body.length === 0) {
        this.skip();
      }
    });
    cy.get(entityEditButtonSelector).first().click({ force: true });
    cy.getEntityCreateUpdateHeading('Event');
    cy.get(entityCreateSaveButtonSelector).should('exist');
    cy.get(entityCreateCancelButtonSelector).click({ force: true });
    cy.wait('@entitiesRequest').then(({ response }) => {
      expect(response.statusCode).to.equal(200);
    });
    cy.url().should('match', eventPageUrlPattern);
  });

  it('should create an instance of Event', () => {
    cy.visit(eventPageUrl);
    cy.get(entityCreateButtonSelector).click({ force: true });
    cy.getEntityCreateUpdateHeading('Event');

    cy.get(`[data-cy="uuid"]`)
      .type('274ab501-bbad-492b-a3a5-10702eac8ffd')
      .invoke('val')
      .should('match', new RegExp('274ab501-bbad-492b-a3a5-10702eac8ffd'));

    cy.get(`[data-cy="title"]`).type('Industrial Loan').should('have.value', 'Industrial Loan');

    cy.get(`[data-cy="content"]`)
      .type('../fake-data/blob/hipster.txt')
      .invoke('val')
      .should('match', new RegExp('../fake-data/blob/hipster.txt'));

    cy.get(`[data-cy="type"]`).select('YEAR');

    cy.get(`[data-cy="description"]`)
      .type('../fake-data/blob/hipster.txt')
      .invoke('val')
      .should('match', new RegExp('../fake-data/blob/hipster.txt'));

    cy.get(`[data-cy="startTime"]`).type('2021-07-09T18:41').should('have.value', '2021-07-09T18:41');

    cy.get(`[data-cy="endTime"]`).type('2021-07-10T11:32').should('have.value', '2021-07-10T11:32');

    cy.get(`[data-cy="tagLine"]`).type('Practical Chief').should('have.value', 'Practical Chief');

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
    cy.url().should('match', eventPageUrlPattern);
  });

  it('should delete last instance of Event', function () {
    cy.intercept('GET', '/api/events/*').as('dialogDeleteRequest');
    cy.visit(eventPageUrl);
    cy.wait('@entitiesRequest').then(({ response }) => {
      if (response.body.length > 0) {
        cy.get(entityTableSelector).should('have.lengthOf', response.body.length);
        cy.get(entityDeleteButtonSelector).last().click({ force: true });
        cy.wait('@dialogDeleteRequest');
        cy.getEntityDeleteDialogHeading('event').should('exist');
        cy.get(entityConfirmDeleteButtonSelector).click({ force: true });
        cy.wait('@deleteEntityRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(204);
        });
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', eventPageUrlPattern);
      } else {
        this.skip();
      }
    });
  });
});
