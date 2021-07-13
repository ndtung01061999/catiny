import { browser, element, by } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import SignInPage from './../../page-objects/signin-page';
import AccountStatusComponentsPage from './account-status.page-object';
import AccountStatusUpdatePage from './account-status-update.page-object';
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

describe('AccountStatus e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let accountStatusComponentsPage: AccountStatusComponentsPage;
  let accountStatusUpdatePage: AccountStatusUpdatePage;
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
    accountStatusComponentsPage = new AccountStatusComponentsPage();
    accountStatusComponentsPage = await accountStatusComponentsPage.goToPage(navBarPage);
  });

  it('should load AccountStatuses', async () => {
    expect(await accountStatusComponentsPage.title.getText()).to.match(/Account Statuses/);
    expect(await accountStatusComponentsPage.createButton.isEnabled()).to.be.true;
  });

  it('should create and delete AccountStatuses', async () => {
    const beforeRecordsCount = (await isVisible(accountStatusComponentsPage.noRecords))
      ? 0
      : await getRecordsCount(accountStatusComponentsPage.table);
    accountStatusUpdatePage = await accountStatusComponentsPage.goToCreateAccountStatus();
    await accountStatusUpdatePage.enterData();
    expect(await isVisible(accountStatusUpdatePage.saveButton)).to.be.false;

    expect(await accountStatusComponentsPage.createButton.isEnabled()).to.be.true;
    await waitUntilDisplayed(accountStatusComponentsPage.table);
    await waitUntilCount(accountStatusComponentsPage.records, beforeRecordsCount + 1);
    expect(await accountStatusComponentsPage.records.count()).to.eq(beforeRecordsCount + 1);

    await accountStatusComponentsPage.deleteAccountStatus();
    if (beforeRecordsCount !== 0) {
      await waitUntilCount(accountStatusComponentsPage.records, beforeRecordsCount);
      expect(await accountStatusComponentsPage.records.count()).to.eq(beforeRecordsCount);
    } else {
      await waitUntilDisplayed(accountStatusComponentsPage.noRecords);
    }
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
