import { browser, element, by } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import SignInPage from './../../page-objects/signin-page';
import TodoListComponentsPage from './todo-list.page-object';
import TodoListUpdatePage from './todo-list-update.page-object';
import {
  waitUntilDisplayed,
  waitUntilAnyDisplayed,
  click,
  getRecordsCount,
  waitUntilHidden,
  waitUntilCount,
  isVisible,
} from '../../util/utils';

const expect = chai.expect;

describe('TodoList e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let todoListComponentsPage: TodoListComponentsPage;
  let todoListUpdatePage: TodoListUpdatePage;
  const username = process.env.E2E_USERNAME ?? 'admin';
  const password = process.env.E2E_PASSWORD ?? 'admin';

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.waitUntilDisplayed();
    await signInPage.username.sendKeys(username);
    await signInPage.password.sendKeys(password);
    await signInPage.loginButton.click();
    await signInPage.waitUntilHidden();
    await waitUntilDisplayed(navBarPage.entityMenu);
    await waitUntilDisplayed(navBarPage.adminMenu);
    await waitUntilDisplayed(navBarPage.accountMenu);
  });

  beforeEach(async () => {
    await browser.get('/');
    await waitUntilDisplayed(navBarPage.entityMenu);
    todoListComponentsPage = new TodoListComponentsPage();
    todoListComponentsPage = await todoListComponentsPage.goToPage(navBarPage);
  });

  it('should load TodoLists', async () => {
    expect(await todoListComponentsPage.title.getText()).to.match(/Todo Lists/);
    expect(await todoListComponentsPage.createButton.isEnabled()).to.be.true;
  });

  it('should create and delete TodoLists', async () => {
    const beforeRecordsCount = (await isVisible(todoListComponentsPage.noRecords))
      ? 0
      : await getRecordsCount(todoListComponentsPage.table);
    todoListUpdatePage = await todoListComponentsPage.goToCreateTodoList();
    await todoListUpdatePage.enterData();
    expect(await isVisible(todoListUpdatePage.saveButton)).to.be.false;

    expect(await todoListComponentsPage.createButton.isEnabled()).to.be.true;
    await waitUntilDisplayed(todoListComponentsPage.table);
    await waitUntilCount(todoListComponentsPage.records, beforeRecordsCount + 1);
    expect(await todoListComponentsPage.records.count()).to.eq(beforeRecordsCount + 1);

    await todoListComponentsPage.deleteTodoList();
    if (beforeRecordsCount !== 0) {
      await waitUntilCount(todoListComponentsPage.records, beforeRecordsCount);
      expect(await todoListComponentsPage.records.count()).to.eq(beforeRecordsCount);
    } else {
      await waitUntilDisplayed(todoListComponentsPage.noRecords);
    }
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
