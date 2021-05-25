import {browser} from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import SignInPage from './../../page-objects/signin-page';
import MessageContentComponentsPage from './message-content.page-object';
import MessageContentUpdatePage from './message-content-update.page-object';
import {getRecordsCount, isVisible, waitUntilCount, waitUntilDisplayed,} from '../../util/utils';

const expect = chai.expect;

describe('MessageContent e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let messageContentComponentsPage: MessageContentComponentsPage;
  let messageContentUpdatePage: MessageContentUpdatePage;
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
    messageContentComponentsPage = new MessageContentComponentsPage();
    messageContentComponentsPage = await messageContentComponentsPage.goToPage(navBarPage);
  });

  it('should load MessageContents', async () => {
    expect(await messageContentComponentsPage.title.getText()).to.match(/Message Contents/);
    expect(await messageContentComponentsPage.createButton.isEnabled()).to.be.true;
  });

  it('should create and delete MessageContents', async () => {
    const beforeRecordsCount = (await isVisible(messageContentComponentsPage.noRecords))
      ? 0
      : await getRecordsCount(messageContentComponentsPage.table);
    messageContentUpdatePage = await messageContentComponentsPage.goToCreateMessageContent();
    await messageContentUpdatePage.enterData();

    expect(await messageContentComponentsPage.createButton.isEnabled()).to.be.true;
    await waitUntilDisplayed(messageContentComponentsPage.table);
    await waitUntilCount(messageContentComponentsPage.records, beforeRecordsCount + 1);
    expect(await messageContentComponentsPage.records.count()).to.eq(beforeRecordsCount + 1);

    await messageContentComponentsPage.deleteMessageContent();
    if (beforeRecordsCount !== 0) {
      await waitUntilCount(messageContentComponentsPage.records, beforeRecordsCount);
      expect(await messageContentComponentsPage.records.count()).to.eq(beforeRecordsCount);
    } else {
      await waitUntilDisplayed(messageContentComponentsPage.noRecords);
    }
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
