import { browser, element, by } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import SignInPage from './../../page-objects/signin-page';
import RankUserComponentsPage from './rank-user.page-object';
import RankUserUpdatePage from './rank-user-update.page-object';
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

describe('RankUser e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let rankUserComponentsPage: RankUserComponentsPage;
  let rankUserUpdatePage: RankUserUpdatePage;
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
    rankUserComponentsPage = new RankUserComponentsPage();
    rankUserComponentsPage = await rankUserComponentsPage.goToPage(navBarPage);
  });

  it('should load RankUsers', async () => {
    expect(await rankUserComponentsPage.title.getText()).to.match(/Rank Users/);
    expect(await rankUserComponentsPage.createButton.isEnabled()).to.be.true;
  });

  it('should create and delete RankUsers', async () => {
    const beforeRecordsCount = (await isVisible(rankUserComponentsPage.noRecords))
      ? 0
      : await getRecordsCount(rankUserComponentsPage.table);
    rankUserUpdatePage = await rankUserComponentsPage.goToCreateRankUser();
    await rankUserUpdatePage.enterData();
    expect(await isVisible(rankUserUpdatePage.saveButton)).to.be.false;

    expect(await rankUserComponentsPage.createButton.isEnabled()).to.be.true;
    await waitUntilDisplayed(rankUserComponentsPage.table);
    await waitUntilCount(rankUserComponentsPage.records, beforeRecordsCount + 1);
    expect(await rankUserComponentsPage.records.count()).to.eq(beforeRecordsCount + 1);

    await rankUserComponentsPage.deleteRankUser();
    if (beforeRecordsCount !== 0) {
      await waitUntilCount(rankUserComponentsPage.records, beforeRecordsCount);
      expect(await rankUserComponentsPage.records.count()).to.eq(beforeRecordsCount);
    } else {
      await waitUntilDisplayed(rankUserComponentsPage.noRecords);
    }
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
