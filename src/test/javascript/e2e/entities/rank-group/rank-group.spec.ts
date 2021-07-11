import { browser, element, by } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import SignInPage from './../../page-objects/signin-page';
import RankGroupComponentsPage from './rank-group.page-object';
import RankGroupUpdatePage from './rank-group-update.page-object';
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

describe('RankGroup e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let rankGroupComponentsPage: RankGroupComponentsPage;
  let rankGroupUpdatePage: RankGroupUpdatePage;
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
    rankGroupComponentsPage = new RankGroupComponentsPage();
    rankGroupComponentsPage = await rankGroupComponentsPage.goToPage(navBarPage);
  });

  it('should load RankGroups', async () => {
    expect(await rankGroupComponentsPage.title.getText()).to.match(/Rank Groups/);
    expect(await rankGroupComponentsPage.createButton.isEnabled()).to.be.true;
  });

  it('should create and delete RankGroups', async () => {
    const beforeRecordsCount = (await isVisible(rankGroupComponentsPage.noRecords))
      ? 0
      : await getRecordsCount(rankGroupComponentsPage.table);
    rankGroupUpdatePage = await rankGroupComponentsPage.goToCreateRankGroup();
    await rankGroupUpdatePage.enterData();
    expect(await isVisible(rankGroupUpdatePage.saveButton)).to.be.false;

    expect(await rankGroupComponentsPage.createButton.isEnabled()).to.be.true;
    await waitUntilDisplayed(rankGroupComponentsPage.table);
    await waitUntilCount(rankGroupComponentsPage.records, beforeRecordsCount + 1);
    expect(await rankGroupComponentsPage.records.count()).to.eq(beforeRecordsCount + 1);

    await rankGroupComponentsPage.deleteRankGroup();
    if (beforeRecordsCount !== 0) {
      await waitUntilCount(rankGroupComponentsPage.records, beforeRecordsCount);
      expect(await rankGroupComponentsPage.records.count()).to.eq(beforeRecordsCount);
    } else {
      await waitUntilDisplayed(rankGroupComponentsPage.noRecords);
    }
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
