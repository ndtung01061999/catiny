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

describe('FileInfo e2e test', () => {
  const fileInfoPageUrl = '/file-info';
  const fileInfoPageUrlPattern = new RegExp('/file-info(\\?.*)?$');
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
    cy.intercept('GET', '/api/file-infos+(?*|)').as('entitiesRequest');
    cy.intercept('POST', '/api/file-infos').as('postEntityRequest');
    cy.intercept('DELETE', '/api/file-infos/*').as('deleteEntityRequest');
  });

  it('should load FileInfos', () => {
    cy.visit('/');
    cy.clickOnEntityMenuItem('file-info');
    cy.wait('@entitiesRequest').then(({ response }) => {
      if (response.body.length === 0) {
        cy.get(entityTableSelector).should('not.exist');
      } else {
        cy.get(entityTableSelector).should('exist');
      }
    });
    cy.getEntityHeading('FileInfo').should('exist');
    cy.url().should('match', fileInfoPageUrlPattern);
  });

  it('should load details FileInfo page', function () {
    cy.visit(fileInfoPageUrl);
    cy.wait('@entitiesRequest').then(({ response }) => {
      if (response.body.length === 0) {
        this.skip();
      }
    });
    cy.get(entityDetailsButtonSelector).first().click({ force: true });
    cy.getEntityDetailsHeading('fileInfo');
    cy.get(entityDetailsBackButtonSelector).click({ force: true });
    cy.wait('@entitiesRequest').then(({ response }) => {
      expect(response.statusCode).to.equal(200);
    });
    cy.url().should('match', fileInfoPageUrlPattern);
  });

  it('should load create FileInfo page', () => {
    cy.visit(fileInfoPageUrl);
    cy.wait('@entitiesRequest');
    cy.get(entityCreateButtonSelector).click({ force: true });
    cy.getEntityCreateUpdateHeading('FileInfo');
    cy.get(entityCreateSaveButtonSelector).should('exist');
    cy.get(entityCreateCancelButtonSelector).click({ force: true });
    cy.wait('@entitiesRequest').then(({ response }) => {
      expect(response.statusCode).to.equal(200);
    });
    cy.url().should('match', fileInfoPageUrlPattern);
  });

  it('should load edit FileInfo page', function () {
    cy.visit(fileInfoPageUrl);
    cy.wait('@entitiesRequest').then(({ response }) => {
      if (response.body.length === 0) {
        this.skip();
      }
    });
    cy.get(entityEditButtonSelector).first().click({ force: true });
    cy.getEntityCreateUpdateHeading('FileInfo');
    cy.get(entityCreateSaveButtonSelector).should('exist');
    cy.get(entityCreateCancelButtonSelector).click({ force: true });
    cy.wait('@entitiesRequest').then(({ response }) => {
      expect(response.statusCode).to.equal(200);
    });
    cy.url().should('match', fileInfoPageUrlPattern);
  });

  it('should create an instance of FileInfo', () => {
    cy.visit(fileInfoPageUrl);
    cy.get(entityCreateButtonSelector).click({ force: true });
    cy.getEntityCreateUpdateHeading('FileInfo');

    cy.get(`[data-cy="uuid"]`)
      .type('0b3ebf42-2758-4059-9034-f0747feb0f40')
      .invoke('val')
      .should('match', new RegExp('0b3ebf42-2758-4059-9034-f0747feb0f40'));

    cy.get(`[data-cy="nameFile"]`).type('Fish Salad').should('have.value', 'Fish Salad');

    cy.get(`[data-cy="typeFile"]`).type('Handmade COM Account').should('have.value', 'Handmade COM Account');

    cy.get(`[data-cy="path"]`).type('Sleek Berkshire').should('have.value', 'Sleek Berkshire');

    cy.get(`[data-cy="dataSize"]`).type('49582').should('have.value', '49582');

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
    cy.url().should('match', fileInfoPageUrlPattern);
  });

  it('should delete last instance of FileInfo', function () {
    cy.intercept('GET', '/api/file-infos/*').as('dialogDeleteRequest');
    cy.visit(fileInfoPageUrl);
    cy.wait('@entitiesRequest').then(({ response }) => {
      if (response.body.length > 0) {
        cy.get(entityTableSelector).should('have.lengthOf', response.body.length);
        cy.get(entityDeleteButtonSelector).last().click({ force: true });
        cy.wait('@dialogDeleteRequest');
        cy.getEntityDeleteDialogHeading('fileInfo').should('exist');
        cy.get(entityConfirmDeleteButtonSelector).click({ force: true });
        cy.wait('@deleteEntityRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(204);
        });
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', fileInfoPageUrlPattern);
      } else {
        this.skip();
      }
    });
  });
});
