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

describe('BaseInfo e2e test', () => {
  const baseInfoPageUrl = '/base-info';
  const baseInfoPageUrlPattern = new RegExp('/base-info(\\?.*)?$');
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
    cy.intercept('GET', '/api/base-infos+(?*|)').as('entitiesRequest');
    cy.intercept('POST', '/api/base-infos').as('postEntityRequest');
    cy.intercept('DELETE', '/api/base-infos/*').as('deleteEntityRequest');
  });

  it('should load BaseInfos', () => {
    cy.visit('/');
    cy.clickOnEntityMenuItem('base-info');
    cy.wait('@entitiesRequest').then(({ response }) => {
      if (response.body.length === 0) {
        cy.get(entityTableSelector).should('not.exist');
      } else {
        cy.get(entityTableSelector).should('exist');
      }
    });
    cy.getEntityHeading('BaseInfo').should('exist');
    cy.url().should('match', baseInfoPageUrlPattern);
  });

  it('should load details BaseInfo page', function () {
    cy.visit(baseInfoPageUrl);
    cy.wait('@entitiesRequest').then(({ response }) => {
      if (response.body.length === 0) {
        this.skip();
      }
    });
    cy.get(entityDetailsButtonSelector).first().click({ force: true });
    cy.getEntityDetailsHeading('baseInfo');
    cy.get(entityDetailsBackButtonSelector).click({ force: true });
    cy.wait('@entitiesRequest').then(({ response }) => {
      expect(response.statusCode).to.equal(200);
    });
    cy.url().should('match', baseInfoPageUrlPattern);
  });

  it('should load create BaseInfo page', () => {
    cy.visit(baseInfoPageUrl);
    cy.wait('@entitiesRequest');
    cy.get(entityCreateButtonSelector).click({ force: true });
    cy.getEntityCreateUpdateHeading('BaseInfo');
    cy.get(entityCreateSaveButtonSelector).should('exist');
    cy.get(entityCreateCancelButtonSelector).click({ force: true });
    cy.wait('@entitiesRequest').then(({ response }) => {
      expect(response.statusCode).to.equal(200);
    });
    cy.url().should('match', baseInfoPageUrlPattern);
  });

  it('should load edit BaseInfo page', function () {
    cy.visit(baseInfoPageUrl);
    cy.wait('@entitiesRequest').then(({ response }) => {
      if (response.body.length === 0) {
        this.skip();
      }
    });
    cy.get(entityEditButtonSelector).first().click({ force: true });
    cy.getEntityCreateUpdateHeading('BaseInfo');
    cy.get(entityCreateSaveButtonSelector).should('exist');
    cy.get(entityCreateCancelButtonSelector).click({ force: true });
    cy.wait('@entitiesRequest').then(({ response }) => {
      expect(response.statusCode).to.equal(200);
    });
    cy.url().should('match', baseInfoPageUrlPattern);
  });

  it('should create an instance of BaseInfo', () =>
  {
    cy.visit(baseInfoPageUrl);
    cy.get(entityCreateButtonSelector).click({force: true});
    cy.getEntityCreateUpdateHeading('BaseInfo');

    cy.get(`[data-cy="uuid"]`)
      .type('810296ec-d72c-446f-95f2-435e8b3e3e9d')
      .invoke('val')
      .should('match', new RegExp('810296ec-d72c-446f-95f2-435e8b3e3e9d'));

    cy.get(`[data-cy="processStatus"]`).select('NEED_HANDLE');

    cy.get(`[data-cy="modifiedClass"]`).type('B2C Table').should('have.value', 'B2C Table');

    cy.get(`[data-cy="createdDate"]`).type('2021-07-10T06:44').should('have.value', '2021-07-10T06:44');

    cy.get(`[data-cy="modifiedDate"]`).type('2021-07-09T23:25').should('have.value', '2021-07-09T23:25');

    cy.get(`[data-cy="notes"]`)
      .type('../fake-data/blob/hipster.txt')
      .invoke('val')
      .should('match', new RegExp('../fake-data/blob/hipster.txt'));

    cy.get(`[data-cy="deleted"]`).should('not.be.checked');
    cy.get(`[data-cy="deleted"]`).click().should('be.checked');

    cy.get(`[data-cy="priorityIndex"]`).type('54156').should('have.value', '54156');

    cy.get(`[data-cy="countUse"]`).type('53156').should('have.value', '53156');

    cy.setFieldSelectToLastOfEntity('classInfo');

    cy.setFieldSelectToLastOfEntity('createdBy');

    cy.setFieldSelectToLastOfEntity('modifiedBy');

    cy.setFieldSelectToLastOfEntity('owner');

    cy.get(entityCreateSaveButtonSelector).click({ force: true });
    cy.scrollTo('top', { ensureScrollable: false });
    cy.get(entityCreateSaveButtonSelector).should('not.exist');
    cy.wait('@postEntityRequest').then(({ response }) => {
      expect(response.statusCode).to.equal(201);
    });
    cy.wait('@entitiesRequest').then(({ response }) => {
      expect(response.statusCode).to.equal(200);
    });
    cy.url().should('match', baseInfoPageUrlPattern);
  });

  it('should delete last instance of BaseInfo', function () {
    cy.intercept('GET', '/api/base-infos/*').as('dialogDeleteRequest');
    cy.visit(baseInfoPageUrl);
    cy.wait('@entitiesRequest').then(({ response }) => {
      if (response.body.length > 0) {
        cy.get(entityTableSelector).should('have.lengthOf', response.body.length);
        cy.get(entityDeleteButtonSelector).last().click({ force: true });
        cy.wait('@dialogDeleteRequest');
        cy.getEntityDeleteDialogHeading('baseInfo').should('exist');
        cy.get(entityConfirmDeleteButtonSelector).click({ force: true });
        cy.wait('@deleteEntityRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(204);
        });
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', baseInfoPageUrlPattern);
      } else {
        this.skip();
      }
    });
  });
});
