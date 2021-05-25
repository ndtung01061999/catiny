import {
  entityConfirmDeleteButtonSelector,
  entityCreateButtonSelector,
  entityCreateSaveButtonSelector,
  entityDeleteButtonSelector,
  entityDetailsBackButtonSelector,
  entityDetailsButtonSelector,
  entityEditButtonSelector,
  entityTableSelector,
} from '../../support/entity';

describe('MessageGroup e2e test', () => {
  let startingEntitiesCount = 0;

  before(() => {
    cy.window().then(win => {
      win.sessionStorage.clear();
    });

    cy.clearCookies();
    cy.intercept('GET', '/api/message-groups*').as('entitiesRequest');
    cy.visit('');
    cy.login('admin', 'admin');
    cy.clickOnEntityMenuItem('message-group');
    cy.wait('@entitiesRequest').then(({ request, response }) => (startingEntitiesCount = response.body.length));
    cy.visit('/');
  });

  it('should load MessageGroups', () => {
    cy.intercept('GET', '/api/message-groups*').as('entitiesRequest');
    cy.visit('/');
    cy.clickOnEntityMenuItem('message-group');
    cy.wait('@entitiesRequest');
    cy.getEntityHeading('MessageGroup').should('exist');
    if (startingEntitiesCount === 0) {
      cy.get(entityTableSelector).should('not.exist');
    } else {
      cy.get(entityTableSelector).should('have.lengthOf', startingEntitiesCount);
    }
    cy.visit('/');
  });

  it('should load details MessageGroup page', () => {
    cy.intercept('GET', '/api/message-groups*').as('entitiesRequest');
    cy.visit('/');
    cy.clickOnEntityMenuItem('message-group');
    cy.wait('@entitiesRequest');
    if (startingEntitiesCount > 0) {
      cy.get(entityDetailsButtonSelector).first().click({ force: true });
      cy.getEntityDetailsHeading('messageGroup');
      cy.get(entityDetailsBackButtonSelector).should('exist');
    }
    cy.visit('/');
  });

  it('should load create MessageGroup page', () => {
    cy.intercept('GET', '/api/message-groups*').as('entitiesRequest');
    cy.visit('/');
    cy.clickOnEntityMenuItem('message-group');
    cy.wait('@entitiesRequest');
    cy.get(entityCreateButtonSelector).click({ force: true });
    cy.getEntityCreateUpdateHeading('MessageGroup');
    cy.get(entityCreateSaveButtonSelector).should('exist');
    cy.visit('/');
  });

  it('should load edit MessageGroup page', () => {
    cy.intercept('GET', '/api/message-groups*').as('entitiesRequest');
    cy.visit('/');
    cy.clickOnEntityMenuItem('message-group');
    cy.wait('@entitiesRequest');
    if (startingEntitiesCount > 0) {
      cy.get(entityEditButtonSelector).first().click({ force: true });
      cy.getEntityCreateUpdateHeading('MessageGroup');
      cy.get(entityCreateSaveButtonSelector).should('exist');
    }
    cy.visit('/');
  });

  it('should create an instance of MessageGroup', () => {
    cy.intercept('GET', '/api/message-groups*').as('entitiesRequest');
    cy.visit('/');
    cy.clickOnEntityMenuItem('message-group');
    cy.wait('@entitiesRequest').then(({ request, response }) => (startingEntitiesCount = response.body.length));
    cy.get(entityCreateButtonSelector).click({ force: true });
    cy.getEntityCreateUpdateHeading('MessageGroup');

    cy.get(`[data-cy="uuid"]`)
      .type('aae8c0be-e8c3-4c05-868f-e66a7a9956b4')
      .invoke('val')
      .should('match', new RegExp('aae8c0be-e8c3-4c05-868f-e66a7a9956b4'));

    cy.get(`[data-cy="userId"]`).type('42108').should('have.value', '42108');

    cy.get(`[data-cy="groupId"]`).type('Borders', { force: true }).invoke('val').should('match', new RegExp('Borders'));

    cy.get(`[data-cy="groupName"]`)
      .type('Shirt Moroccan Lead', { force: true })
      .invoke('val')
      .should('match', new RegExp('Shirt Moroccan Lead'));

    cy.get(`[data-cy="addBy"]`)
      .type('content-based architectures', { force: true })
      .invoke('val')
      .should('match', new RegExp('content-based architectures'));

    cy.get(`[data-cy="lastContent"]`)
      .type('../fake-data/blob/hipster.txt', { force: true })
      .invoke('val')
      .should('match', new RegExp('../fake-data/blob/hipster.txt'));

    cy.get(`[data-cy="searchField"]`)
      .type('../fake-data/blob/hipster.txt', { force: true })
      .invoke('val')
      .should('match', new RegExp('../fake-data/blob/hipster.txt'));

    cy.get(`[data-cy="role"]`)
      .type('bifurcated Profit-focused', { force: true })
      .invoke('val')
      .should('match', new RegExp('bifurcated Profit-focused'));

    cy.get(`[data-cy="createdDate"]`).type('2021-05-23T06:00').invoke('val').should('equal', '2021-05-23T06:00');

    cy.get(`[data-cy="modifiedDate"]`).type('2021-05-22T17:09').invoke('val').should('equal', '2021-05-22T17:09');

    cy.get(`[data-cy="createdBy"]`)
      .type('Dinar e-business circuit', { force: true })
      .invoke('val')
      .should('match', new RegExp('Dinar e-business circuit'));

    cy.get(`[data-cy="modifiedBy"]`).type('Home Mông', { force: true }).invoke('val').should('match', new RegExp('Home Mông'));

    cy.get(`[data-cy="comment"]`).type('deposit online', { force: true }).invoke('val').should('match', new RegExp('deposit online'));

    cy.get(entityCreateSaveButtonSelector).click({ force: true });
    cy.scrollTo('top', { ensureScrollable: false });
    cy.get(entityCreateSaveButtonSelector).should('not.exist');
    cy.intercept('GET', '/api/message-groups*').as('entitiesRequestAfterCreate');
    cy.visit('/');
    cy.clickOnEntityMenuItem('message-group');
    cy.wait('@entitiesRequestAfterCreate');
    cy.get(entityTableSelector).should('have.lengthOf', startingEntitiesCount + 1);
    cy.visit('/');
  });

  it('should delete last instance of MessageGroup', () => {
    cy.intercept('GET', '/api/message-groups*').as('entitiesRequest');
    cy.intercept('GET', '/api/message-groups/*').as('dialogDeleteRequest');
    cy.intercept('DELETE', '/api/message-groups/*').as('deleteEntityRequest');
    cy.visit('/');
    cy.clickOnEntityMenuItem('message-group');
    cy.wait('@entitiesRequest').then(({ request, response }) => {
      startingEntitiesCount = response.body.length;
      if (startingEntitiesCount > 0) {
        cy.get(entityTableSelector).should('have.lengthOf', startingEntitiesCount);
        cy.get(entityDeleteButtonSelector).last().click({ force: true });
        cy.wait('@dialogDeleteRequest');
        cy.getEntityDeleteDialogHeading('messageGroup').should('exist');
        cy.get(entityConfirmDeleteButtonSelector).click({ force: true });
        cy.wait('@deleteEntityRequest');
        cy.intercept('GET', '/api/message-groups*').as('entitiesRequestAfterDelete');
        cy.visit('/');
        cy.clickOnEntityMenuItem('message-group');
        cy.wait('@entitiesRequestAfterDelete');
        cy.get(entityTableSelector).should('have.lengthOf', startingEntitiesCount - 1);
      }
      cy.visit('/');
    });
  });
});
