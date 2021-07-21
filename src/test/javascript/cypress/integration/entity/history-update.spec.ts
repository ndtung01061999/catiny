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

describe('HistoryUpdate e2e test', () =>
{
  const historyUpdatePageUrl = '/history-update';
  const historyUpdatePageUrlPattern = new RegExp('/history-update(\\?.*)?$');
  const username = Cypress.env('E2E_USERNAME') ?? 'admin';
  const password = Cypress.env('E2E_PASSWORD') ?? 'admin';

  before(() =>
  {
    cy.window().then(win =>
    {
      win.sessionStorage.clear();
    });
    cy.visit('');
    cy.login(username, password);
    cy.get(entityItemSelector).should('exist');
  });

  beforeEach(() =>
  {
    cy.intercept('GET', '/api/history-updates+(?*|)').as('entitiesRequest');
    cy.intercept('POST', '/api/history-updates').as('postEntityRequest');
    cy.intercept('DELETE', '/api/history-updates/*').as('deleteEntityRequest');
  });

  it('should load HistoryUpdates', () =>
  {
    cy.visit('/');
    cy.clickOnEntityMenuItem('history-update');
    cy.wait('@entitiesRequest').then(({response}) =>
    {
      if (response.body.length === 0)
      {
        cy.get(entityTableSelector).should('not.exist');
      }
      else
      {
        cy.get(entityTableSelector).should('exist');
      }
    });
    cy.getEntityHeading('HistoryUpdate').should('exist');
    cy.url().should('match', historyUpdatePageUrlPattern);
  });

  it('should load details HistoryUpdate page', function ()
  {
    cy.visit(historyUpdatePageUrl);
    cy.wait('@entitiesRequest').then(({response}) =>
    {
      if (response.body.length === 0)
      {
        this.skip();
      }
    });
    cy.get(entityDetailsButtonSelector).first().click({force: true});
    cy.getEntityDetailsHeading('historyUpdate');
    cy.get(entityDetailsBackButtonSelector).click({force: true});
    cy.wait('@entitiesRequest').then(({response}) =>
    {
      expect(response.statusCode).to.equal(200);
    });
    cy.url().should('match', historyUpdatePageUrlPattern);
  });

  it('should load create HistoryUpdate page', () =>
  {
    cy.visit(historyUpdatePageUrl);
    cy.wait('@entitiesRequest');
    cy.get(entityCreateButtonSelector).click({force: true});
    cy.getEntityCreateUpdateHeading('HistoryUpdate');
    cy.get(entityCreateSaveButtonSelector).should('exist');
    cy.get(entityCreateCancelButtonSelector).click({force: true});
    cy.wait('@entitiesRequest').then(({response}) =>
    {
      expect(response.statusCode).to.equal(200);
    });
    cy.url().should('match', historyUpdatePageUrlPattern);
  });

  it('should load edit HistoryUpdate page', function ()
  {
    cy.visit(historyUpdatePageUrl);
    cy.wait('@entitiesRequest').then(({response}) =>
    {
      if (response.body.length === 0)
      {
        this.skip();
      }
    });
    cy.get(entityEditButtonSelector).first().click({force: true});
    cy.getEntityCreateUpdateHeading('HistoryUpdate');
    cy.get(entityCreateSaveButtonSelector).should('exist');
    cy.get(entityCreateCancelButtonSelector).click({force: true});
    cy.wait('@entitiesRequest').then(({response}) =>
    {
      expect(response.statusCode).to.equal(200);
    });
    cy.url().should('match', historyUpdatePageUrlPattern);
  });

  it('should create an instance of HistoryUpdate', () =>
  {
    cy.visit(historyUpdatePageUrl);
    cy.get(entityCreateButtonSelector).click({force: true});
    cy.getEntityCreateUpdateHeading('HistoryUpdate');

    cy.get(`[data-cy="uuid"]`)
      .type('72bf28cb-bb26-40c1-8124-fe273a6471a4')
      .invoke('val')
      .should('match', new RegExp('72bf28cb-bb26-40c1-8124-fe273a6471a4'));

    cy.get(`[data-cy="version"]`).type('6744').should('have.value', '6744');

    cy.get(`[data-cy="content"]`)
      .type('../fake-data/blob/hipster.txt')
      .invoke('val')
      .should('match', new RegExp('../fake-data/blob/hipster.txt'));

    cy.setFieldSelectToLastOfEntity('baseInfo');

    cy.get(entityCreateSaveButtonSelector).click({force: true});
    cy.scrollTo('top', {ensureScrollable: false});
    cy.get(entityCreateSaveButtonSelector).should('not.exist');
    cy.wait('@postEntityRequest').then(({response}) =>
    {
      expect(response.statusCode).to.equal(201);
    });
    cy.wait('@entitiesRequest').then(({response}) =>
    {
      expect(response.statusCode).to.equal(200);
    });
    cy.url().should('match', historyUpdatePageUrlPattern);
  });

  it('should delete last instance of HistoryUpdate', function ()
  {
    cy.intercept('GET', '/api/history-updates/*').as('dialogDeleteRequest');
    cy.visit(historyUpdatePageUrl);
    cy.wait('@entitiesRequest').then(({response}) =>
    {
      if (response.body.length > 0)
      {
        cy.get(entityTableSelector).should('have.lengthOf', response.body.length);
        cy.get(entityDeleteButtonSelector).last().click({force: true});
        cy.wait('@dialogDeleteRequest');
        cy.getEntityDeleteDialogHeading('historyUpdate').should('exist');
        cy.get(entityConfirmDeleteButtonSelector).click({force: true});
        cy.wait('@deleteEntityRequest').then(({response}) =>
        {
          expect(response.statusCode).to.equal(204);
        });
        cy.wait('@entitiesRequest').then(({response}) =>
        {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', historyUpdatePageUrlPattern);
      }
      else
      {
        this.skip();
      }
    });
  });
});
