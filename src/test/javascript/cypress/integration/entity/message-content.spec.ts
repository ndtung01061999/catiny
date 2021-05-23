import {
  entityTableSelector,
  entityDetailsButtonSelector,
  entityDetailsBackButtonSelector,
  entityCreateButtonSelector,
  entityCreateSaveButtonSelector,
  entityEditButtonSelector,
  entityDeleteButtonSelector,
  entityConfirmDeleteButtonSelector,
} from '../../support/entity';

describe('MessageContent e2e test', () => {
  let startingEntitiesCount = 0;

  before(() => {
    cy.window().then(win => {
      win.sessionStorage.clear();
    });

    cy.clearCookies();
    cy.intercept('GET', '/api/message-contents*').as('entitiesRequest');
    cy.visit('');
    cy.login('admin', 'admin');
    cy.clickOnEntityMenuItem('message-content');
    cy.wait('@entitiesRequest').then(({ request, response }) => (startingEntitiesCount = response.body.length));
    cy.visit('/');
  });

  it('should load MessageContents', () => {
    cy.intercept('GET', '/api/message-contents*').as('entitiesRequest');
    cy.visit('/');
    cy.clickOnEntityMenuItem('message-content');
    cy.wait('@entitiesRequest');
    cy.getEntityHeading('MessageContent').should('exist');
    if (startingEntitiesCount === 0) {
      cy.get(entityTableSelector).should('not.exist');
    } else {
      cy.get(entityTableSelector).should('have.lengthOf', startingEntitiesCount);
    }
    cy.visit('/');
  });

  it('should load details MessageContent page', () => {
    cy.intercept('GET', '/api/message-contents*').as('entitiesRequest');
    cy.visit('/');
    cy.clickOnEntityMenuItem('message-content');
    cy.wait('@entitiesRequest');
    if (startingEntitiesCount > 0) {
      cy.get(entityDetailsButtonSelector).first().click({ force: true });
      cy.getEntityDetailsHeading('messageContent');
      cy.get(entityDetailsBackButtonSelector).should('exist');
    }
    cy.visit('/');
  });

  it('should load create MessageContent page', () => {
    cy.intercept('GET', '/api/message-contents*').as('entitiesRequest');
    cy.visit('/');
    cy.clickOnEntityMenuItem('message-content');
    cy.wait('@entitiesRequest');
    cy.get(entityCreateButtonSelector).click({ force: true });
    cy.getEntityCreateUpdateHeading('MessageContent');
    cy.get(entityCreateSaveButtonSelector).should('exist');
    cy.visit('/');
  });

  it('should load edit MessageContent page', () => {
    cy.intercept('GET', '/api/message-contents*').as('entitiesRequest');
    cy.visit('/');
    cy.clickOnEntityMenuItem('message-content');
    cy.wait('@entitiesRequest');
    if (startingEntitiesCount > 0) {
      cy.get(entityEditButtonSelector).first().click({ force: true });
      cy.getEntityCreateUpdateHeading('MessageContent');
      cy.get(entityCreateSaveButtonSelector).should('exist');
    }
    cy.visit('/');
  });

  it('should create an instance of MessageContent', () => {
    cy.intercept('GET', '/api/message-contents*').as('entitiesRequest');
    cy.visit('/');
    cy.clickOnEntityMenuItem('message-content');
    cy.wait('@entitiesRequest').then(({ request, response }) => (startingEntitiesCount = response.body.length));
    cy.get(entityCreateButtonSelector).click({ force: true });
    cy.getEntityCreateUpdateHeading('MessageContent');

    cy.get(`[data-cy="uuid"]`)
      .type('f132eef7-c61d-45f0-8847-79fac47bdbb4')
      .invoke('val')
      .should('match', new RegExp('f132eef7-c61d-45f0-8847-79fac47bdbb4'));

    cy.get(`[data-cy="groupId"]`).type('Haven seize', { force: true }).invoke('val').should('match', new RegExp('Haven seize'));

    cy.get(`[data-cy="content"]`)
      .type('../fake-data/blob/hipster.txt', { force: true })
      .invoke('val')
      .should('match', new RegExp('../fake-data/blob/hipster.txt'));

    cy.get(`[data-cy="sender"]`)
      .type('Interactions New calculate', { force: true })
      .invoke('val')
      .should('match', new RegExp('Interactions New calculate'));

    cy.get(`[data-cy="status"]`).type('Principal', { force: true }).invoke('val').should('match', new RegExp('Principal'));

    cy.get(`[data-cy="searchField"]`)
      .type('../fake-data/blob/hipster.txt', { force: true })
      .invoke('val')
      .should('match', new RegExp('../fake-data/blob/hipster.txt'));

    cy.get(`[data-cy="role"]`).type('calculate', { force: true }).invoke('val').should('match', new RegExp('calculate'));

    cy.get(`[data-cy="createdDate"]`).type('2021-05-22T20:31').invoke('val').should('equal', '2021-05-22T20:31');

    cy.get(`[data-cy="modifiedDate"]`).type('2021-05-22T09:46').invoke('val').should('equal', '2021-05-22T09:46');

    cy.get(`[data-cy="createdBy"]`)
      .type('invoice capacitor', { force: true })
      .invoke('val')
      .should('match', new RegExp('invoice capacitor'));

    cy.get(`[data-cy="modifiedBy"]`)
      .type('Tactics web-enabled Mississippi', { force: true })
      .invoke('val')
      .should('match', new RegExp('Tactics web-enabled Mississippi'));

    cy.get(`[data-cy="comment"]`).type('quantifying', { force: true }).invoke('val').should('match', new RegExp('quantifying'));

    cy.get(entityCreateSaveButtonSelector).click({ force: true });
    cy.scrollTo('top', { ensureScrollable: false });
    cy.get(entityCreateSaveButtonSelector).should('not.exist');
    cy.intercept('GET', '/api/message-contents*').as('entitiesRequestAfterCreate');
    cy.visit('/');
    cy.clickOnEntityMenuItem('message-content');
    cy.wait('@entitiesRequestAfterCreate');
    cy.get(entityTableSelector).should('have.lengthOf', startingEntitiesCount + 1);
    cy.visit('/');
  });

  it('should delete last instance of MessageContent', () => {
    cy.intercept('GET', '/api/message-contents*').as('entitiesRequest');
    cy.intercept('GET', '/api/message-contents/*').as('dialogDeleteRequest');
    cy.intercept('DELETE', '/api/message-contents/*').as('deleteEntityRequest');
    cy.visit('/');
    cy.clickOnEntityMenuItem('message-content');
    cy.wait('@entitiesRequest').then(({ request, response }) => {
      startingEntitiesCount = response.body.length;
      if (startingEntitiesCount > 0) {
        cy.get(entityTableSelector).should('have.lengthOf', startingEntitiesCount);
        cy.get(entityDeleteButtonSelector).last().click({ force: true });
        cy.wait('@dialogDeleteRequest');
        cy.getEntityDeleteDialogHeading('messageContent').should('exist');
        cy.get(entityConfirmDeleteButtonSelector).click({ force: true });
        cy.wait('@deleteEntityRequest');
        cy.intercept('GET', '/api/message-contents*').as('entitiesRequestAfterDelete');
        cy.visit('/');
        cy.clickOnEntityMenuItem('message-content');
        cy.wait('@entitiesRequestAfterDelete');
        cy.get(entityTableSelector).should('have.lengthOf', startingEntitiesCount - 1);
      }
      cy.visit('/');
    });
  });
});
