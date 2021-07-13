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

describe('DeviceStatus e2e test', () => {
  const deviceStatusPageUrl = '/device-status';
  const deviceStatusPageUrlPattern = new RegExp('/device-status(\\?.*)?$');
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
    cy.intercept('GET', '/api/device-statuses+(?*|)').as('entitiesRequest');
    cy.intercept('POST', '/api/device-statuses').as('postEntityRequest');
    cy.intercept('DELETE', '/api/device-statuses/*').as('deleteEntityRequest');
  });

  it('should load DeviceStatuses', () => {
    cy.visit('/');
    cy.clickOnEntityMenuItem('device-status');
    cy.wait('@entitiesRequest').then(({ response }) => {
      if (response.body.length === 0) {
        cy.get(entityTableSelector).should('not.exist');
      } else {
        cy.get(entityTableSelector).should('exist');
      }
    });
    cy.getEntityHeading('DeviceStatus').should('exist');
    cy.url().should('match', deviceStatusPageUrlPattern);
  });

  it('should load details DeviceStatus page', function () {
    cy.visit(deviceStatusPageUrl);
    cy.wait('@entitiesRequest').then(({ response }) => {
      if (response.body.length === 0) {
        this.skip();
      }
    });
    cy.get(entityDetailsButtonSelector).first().click({ force: true });
    cy.getEntityDetailsHeading('deviceStatus');
    cy.get(entityDetailsBackButtonSelector).click({ force: true });
    cy.wait('@entitiesRequest').then(({ response }) => {
      expect(response.statusCode).to.equal(200);
    });
    cy.url().should('match', deviceStatusPageUrlPattern);
  });

  it('should load create DeviceStatus page', () => {
    cy.visit(deviceStatusPageUrl);
    cy.wait('@entitiesRequest');
    cy.get(entityCreateButtonSelector).click({ force: true });
    cy.getEntityCreateUpdateHeading('DeviceStatus');
    cy.get(entityCreateSaveButtonSelector).should('exist');
    cy.get(entityCreateCancelButtonSelector).click({ force: true });
    cy.wait('@entitiesRequest').then(({ response }) => {
      expect(response.statusCode).to.equal(200);
    });
    cy.url().should('match', deviceStatusPageUrlPattern);
  });

  it('should load edit DeviceStatus page', function () {
    cy.visit(deviceStatusPageUrl);
    cy.wait('@entitiesRequest').then(({ response }) => {
      if (response.body.length === 0) {
        this.skip();
      }
    });
    cy.get(entityEditButtonSelector).first().click({ force: true });
    cy.getEntityCreateUpdateHeading('DeviceStatus');
    cy.get(entityCreateSaveButtonSelector).should('exist');
    cy.get(entityCreateCancelButtonSelector).click({ force: true });
    cy.wait('@entitiesRequest').then(({ response }) => {
      expect(response.statusCode).to.equal(200);
    });
    cy.url().should('match', deviceStatusPageUrlPattern);
  });

  it('should create an instance of DeviceStatus', () => {
    cy.visit(deviceStatusPageUrl);
    cy.get(entityCreateButtonSelector).click({ force: true });
    cy.getEntityCreateUpdateHeading('DeviceStatus');

    cy.get(`[data-cy="uuid"]`)
      .type('47658617-f0bc-491d-9c32-c511d3890170')
      .invoke('val')
      .should('match', new RegExp('47658617-f0bc-491d-9c32-c511d3890170'));

    cy.get(`[data-cy="deviceName"]`).type('microchip quantify').should('have.value', 'microchip quantify');

    cy.get(`[data-cy="deviceType"]`).select('OTHER_DEVICE');

    cy.get(`[data-cy="deviceStatus"]`).select('OFFLINE');

    cy.get(`[data-cy="lastVisited"]`).type('2021-07-09T14:54').should('have.value', '2021-07-09T14:54');

    cy.get(`[data-cy="statusComment"]`).type('Representative Chicken').should('have.value', 'Representative Chicken');

    cy.setFieldSelectToLastOfEntity('baseInfo');

    cy.setFieldSelectToLastOfEntity('accountStatus');

    cy.get(entityCreateSaveButtonSelector).click({ force: true });
    cy.scrollTo('top', { ensureScrollable: false });
    cy.get(entityCreateSaveButtonSelector).should('not.exist');
    cy.wait('@postEntityRequest').then(({ response }) => {
      expect(response.statusCode).to.equal(201);
    });
    cy.wait('@entitiesRequest').then(({ response }) => {
      expect(response.statusCode).to.equal(200);
    });
    cy.url().should('match', deviceStatusPageUrlPattern);
  });

  it('should delete last instance of DeviceStatus', function () {
    cy.intercept('GET', '/api/device-statuses/*').as('dialogDeleteRequest');
    cy.visit(deviceStatusPageUrl);
    cy.wait('@entitiesRequest').then(({ response }) => {
      if (response.body.length > 0) {
        cy.get(entityTableSelector).should('have.lengthOf', response.body.length);
        cy.get(entityDeleteButtonSelector).last().click({ force: true });
        cy.wait('@dialogDeleteRequest');
        cy.getEntityDeleteDialogHeading('deviceStatus').should('exist');
        cy.get(entityConfirmDeleteButtonSelector).click({ force: true });
        cy.wait('@deleteEntityRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(204);
        });
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', deviceStatusPageUrlPattern);
      } else {
        this.skip();
      }
    });
  });
});
