import { browser, element, by } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import SignInPage from './../../page-objects/signin-page';
import MessageGroupComponentsPage from './message-group.page-object';
import MessageGroupUpdatePage from './message-group-update.page-object';
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

describe('MessageGroup e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let messageGroupComponentsPage: MessageGroupComponentsPage;
  let messageGroupUpdatePage: MessageGroupUpdatePage;
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
    messageGroupComponentsPage = new MessageGroupComponentsPage();
    messageGroupComponentsPage = await messageGroupComponentsPage.goToPage(navBarPage);
  });

  it('should load MessageGroups', async () => {
    expect(await messageGroupComponentsPage.title.getText()).to.match(/Message Groups/);
    expect(await messageGroupComponentsPage.createButton.isEnabled()).to.be.true;
  });

  it('should create and delete MessageGroups', async () => {
    const beforeRecordsCount = (await isVisible(messageGroupComponentsPage.noRecords))
      ? 0
      : await getRecordsCount(messageGroupComponentsPage.table);
    messageGroupUpdatePage = await messageGroupComponentsPage.goToCreateMessageGroup();
    await messageGroupUpdatePage.enterData();

    expect(await messageGroupComponentsPage.createButton.isEnabled()).to.be.true;
    await waitUntilDisplayed(messageGroupComponentsPage.table);
    await waitUntilCount(messageGroupComponentsPage.records, beforeRecordsCount + 1);
    expect(await messageGroupComponentsPage.records.count()).to.eq(beforeRecordsCount + 1);

    await messageGroupComponentsPage.deleteMessageGroup();
    if (beforeRecordsCount !== 0) {
      await waitUntilCount(messageGroupComponentsPage.records, beforeRecordsCount);
      expect(await messageGroupComponentsPage.records.count()).to.eq(beforeRecordsCount);
    } else {
      await waitUntilDisplayed(messageGroupComponentsPage.noRecords);
    }
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
