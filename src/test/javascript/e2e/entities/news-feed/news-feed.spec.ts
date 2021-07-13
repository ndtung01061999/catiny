import { browser, element, by } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import SignInPage from './../../page-objects/signin-page';
import NewsFeedComponentsPage from './news-feed.page-object';
import NewsFeedUpdatePage from './news-feed-update.page-object';
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

describe('NewsFeed e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let newsFeedComponentsPage: NewsFeedComponentsPage;
  let newsFeedUpdatePage: NewsFeedUpdatePage;
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
    newsFeedComponentsPage = new NewsFeedComponentsPage();
    newsFeedComponentsPage = await newsFeedComponentsPage.goToPage(navBarPage);
  });

  it('should load NewsFeeds', async () => {
    expect(await newsFeedComponentsPage.title.getText()).to.match(/News Feeds/);
    expect(await newsFeedComponentsPage.createButton.isEnabled()).to.be.true;
  });

  it('should create and delete NewsFeeds', async () => {
    const beforeRecordsCount = (await isVisible(newsFeedComponentsPage.noRecords))
      ? 0
      : await getRecordsCount(newsFeedComponentsPage.table);
    newsFeedUpdatePage = await newsFeedComponentsPage.goToCreateNewsFeed();
    await newsFeedUpdatePage.enterData();
    expect(await isVisible(newsFeedUpdatePage.saveButton)).to.be.false;

    expect(await newsFeedComponentsPage.createButton.isEnabled()).to.be.true;
    await waitUntilDisplayed(newsFeedComponentsPage.table);
    await waitUntilCount(newsFeedComponentsPage.records, beforeRecordsCount + 1);
    expect(await newsFeedComponentsPage.records.count()).to.eq(beforeRecordsCount + 1);

    await newsFeedComponentsPage.deleteNewsFeed();
    if (beforeRecordsCount !== 0) {
      await waitUntilCount(newsFeedComponentsPage.records, beforeRecordsCount);
      expect(await newsFeedComponentsPage.records.count()).to.eq(beforeRecordsCount);
    } else {
      await waitUntilDisplayed(newsFeedComponentsPage.noRecords);
    }
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
