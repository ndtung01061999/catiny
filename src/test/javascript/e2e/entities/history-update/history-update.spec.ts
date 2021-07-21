import {browser} from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import SignInPage from './../../page-objects/signin-page';
import HistoryUpdateComponentsPage from './history-update.page-object';
import HistoryUpdateUpdatePage from './history-update-update.page-object';
import {getRecordsCount, isVisible, waitUntilCount, waitUntilDisplayed,} from '../../util/utils';

const expect = chai.expect;

describe('HistoryUpdate e2e test', () =>
{
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let historyUpdateComponentsPage: HistoryUpdateComponentsPage;
  let historyUpdateUpdatePage: HistoryUpdateUpdatePage;
  const username = process.env.E2E_USERNAME ?? 'admin';
  const password = process.env.E2E_PASSWORD ?? 'admin';

  before(async () =>
  {
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

  beforeEach(async () =>
  {
    await browser.get('/');
    await waitUntilDisplayed(navBarPage.entityMenu);
    historyUpdateComponentsPage = new HistoryUpdateComponentsPage();
    historyUpdateComponentsPage = await historyUpdateComponentsPage.goToPage(navBarPage);
  });

  it('should load HistoryUpdates', async () =>
  {
    expect(await historyUpdateComponentsPage.title.getText()).to.match(/History Updates/);
    expect(await historyUpdateComponentsPage.createButton.isEnabled()).to.be.true;
  });

  it('should create and delete HistoryUpdates', async () =>
  {
    const beforeRecordsCount = (await isVisible(historyUpdateComponentsPage.noRecords))
      ? 0
      : await getRecordsCount(historyUpdateComponentsPage.table);
    historyUpdateUpdatePage = await historyUpdateComponentsPage.goToCreateHistoryUpdate();
    await historyUpdateUpdatePage.enterData();
    expect(await isVisible(historyUpdateUpdatePage.saveButton)).to.be.false;

    expect(await historyUpdateComponentsPage.createButton.isEnabled()).to.be.true;
    await waitUntilDisplayed(historyUpdateComponentsPage.table);
    await waitUntilCount(historyUpdateComponentsPage.records, beforeRecordsCount + 1);
    expect(await historyUpdateComponentsPage.records.count()).to.eq(beforeRecordsCount + 1);

    await historyUpdateComponentsPage.deleteHistoryUpdate();
    if (beforeRecordsCount !== 0)
    {
      await waitUntilCount(historyUpdateComponentsPage.records, beforeRecordsCount);
      expect(await historyUpdateComponentsPage.records.count()).to.eq(beforeRecordsCount);
    }
    else
    {
      await waitUntilDisplayed(historyUpdateComponentsPage.noRecords);
    }
  });

  after(async () =>
  {
    await navBarPage.autoSignOut();
  });
});
