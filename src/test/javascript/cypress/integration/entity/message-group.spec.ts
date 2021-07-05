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

describe('MessageGroup e2e test', () =>
{
  const messageGroupPageUrl = '/message-group';
  const messageGroupPageUrlPattern = new RegExp('/message-group(\\?.*)?$');
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
    cy.intercept('GET', '/api/message-groups+(?*|)').as('entitiesRequest');
    cy.intercept('POST', '/api/message-groups').as('postEntityRequest');
    cy.intercept('DELETE', '/api/message-groups/*').as('deleteEntityRequest');
  });

  it('should load MessageGroups', () =>
  {
    cy.visit('/');
    cy.clickOnEntityMenuItem('message-group');
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
    cy.getEntityHeading('MessageGroup').should('exist');
    cy.url().should('match', messageGroupPageUrlPattern);
  });

  it('should load details MessageGroup page', function ()
  {
    cy.visit(messageGroupPageUrl);
    cy.wait('@entitiesRequest').then(({response}) =>
    {
      if (response.body.length === 0)
      {
        this.skip();
      }
    });
    cy.get(entityDetailsButtonSelector).first().click({force: true});
    cy.getEntityDetailsHeading('messageGroup');
    cy.get(entityDetailsBackButtonSelector).click({force: true});
    cy.wait('@entitiesRequest').then(({response}) =>
    {
      expect(response.statusCode).to.equal(200);
    });
    cy.url().should('match', messageGroupPageUrlPattern);
  });

  it('should load create MessageGroup page', () =>
  {
    cy.visit(messageGroupPageUrl);
    cy.wait('@entitiesRequest');
    cy.get(entityCreateButtonSelector).click({force: true});
    cy.getEntityCreateUpdateHeading('MessageGroup');
    cy.get(entityCreateSaveButtonSelector).should('exist');
    cy.get(entityCreateCancelButtonSelector).click({force: true});
    cy.wait('@entitiesRequest').then(({response}) =>
    {
      expect(response.statusCode).to.equal(200);
    });
    cy.url().should('match', messageGroupPageUrlPattern);
  });

  it('should load edit MessageGroup page', function ()
  {
    cy.visit(messageGroupPageUrl);
    cy.wait('@entitiesRequest').then(({response}) =>
    {
      if (response.body.length === 0)
      {
        this.skip();
      }
    });
    cy.get(entityEditButtonSelector).first().click({force: true});
    cy.getEntityCreateUpdateHeading('MessageGroup');
    cy.get(entityCreateSaveButtonSelector).should('exist');
    cy.get(entityCreateCancelButtonSelector).click({force: true});
    cy.wait('@entitiesRequest').then(({response}) =>
    {
      expect(response.statusCode).to.equal(200);
    });
    cy.url().should('match', messageGroupPageUrlPattern);
  });

  it('should create an instance of MessageGroup', () =>
  {
    cy.visit(messageGroupPageUrl);
    cy.get(entityCreateButtonSelector).click({force: true});
    cy.getEntityCreateUpdateHeading('MessageGroup');

    cy.get(`[data-cy="uuid"]`)
      .type('213dfe71-d01d-4382-b229-11afcde7b10d')
      .invoke('val')
      .should('match', new RegExp('213dfe71-d01d-4382-b229-11afcde7b10d'));

    cy.get(`[data-cy="userId"]`).type('65025').should('have.value', '65025');

    cy.get(`[data-cy="groupId"]`).type('Supervisor').should('have.value', 'Supervisor');

    cy.get(`[data-cy="groupName"]`).type('Ariary port').should('have.value', 'Ariary port');

    cy.get(`[data-cy="addBy"]`).type('Island Toys').should('have.value', 'Island Toys');

    cy.get(`[data-cy="lastContent"]`)
      .type('../fake-data/blob/hipster.txt')
      .invoke('val')
      .should('match', new RegExp('../fake-data/blob/hipster.txt'));

    cy.get(`[data-cy="searchField"]`)
      .type('../fake-data/blob/hipster.txt')
      .invoke('val')
      .should('match', new RegExp('../fake-data/blob/hipster.txt'));

    cy.get(`[data-cy="role"]`).type('online Engineer').should('have.value', 'online Engineer');

    cy.get(`[data-cy="createdDate"]`).type('2021-05-22T17:23').should('have.value', '2021-05-22T17:23');

    cy.get(`[data-cy="modifiedDate"]`).type('2021-05-23T03:41').should('have.value', '2021-05-23T03:41');

    cy.get(`[data-cy="createdBy"]`).type('modular strategic Outdoors').should('have.value', 'modular strategic Outdoors');

    cy.get(`[data-cy="modifiedBy"]`).type('rich').should('have.value', 'rich');

    cy.get(`[data-cy="comment"]`).type('Ai lime ivory').should('have.value', 'Ai lime ivory');

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
    cy.url().should('match', messageGroupPageUrlPattern);
  });

  it('should delete last instance of MessageGroup', function ()
  {
    cy.intercept('GET', '/api/message-groups/*').as('dialogDeleteRequest');
    cy.visit(messageGroupPageUrl);
    cy.wait('@entitiesRequest').then(({response}) =>
    {
      if (response.body.length > 0)
      {
        cy.get(entityTableSelector).should('have.lengthOf', response.body.length);
        cy.get(entityDeleteButtonSelector).last().click({force: true});
        cy.wait('@dialogDeleteRequest');
        cy.getEntityDeleteDialogHeading('messageGroup').should('exist');
        cy.get(entityConfirmDeleteButtonSelector).click({force: true});
        cy.wait('@deleteEntityRequest').then(({response}) =>
        {
          expect(response.statusCode).to.equal(204);
        });
        cy.wait('@entitiesRequest').then(({response}) =>
        {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', messageGroupPageUrlPattern);
      }
      else
      {
        this.skip();
      }
    });
  });
});
