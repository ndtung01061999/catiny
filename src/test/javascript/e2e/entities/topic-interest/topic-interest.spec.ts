import { browser, element, by } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import SignInPage from './../../page-objects/signin-page';
import TopicInterestComponentsPage from './topic-interest.page-object';
import TopicInterestUpdatePage from './topic-interest-update.page-object';
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

describe('TopicInterest e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let topicInterestComponentsPage: TopicInterestComponentsPage;
  let topicInterestUpdatePage: TopicInterestUpdatePage;
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
    topicInterestComponentsPage = new TopicInterestComponentsPage();
    topicInterestComponentsPage = await topicInterestComponentsPage.goToPage(navBarPage);
  });

  it('should load TopicInterests', async () => {
    expect(await topicInterestComponentsPage.title.getText()).to.match(/Topic Interests/);
    expect(await topicInterestComponentsPage.createButton.isEnabled()).to.be.true;
  });

  it('should create and delete TopicInterests', async () => {
    const beforeRecordsCount = (await isVisible(topicInterestComponentsPage.noRecords))
      ? 0
      : await getRecordsCount(topicInterestComponentsPage.table);
    topicInterestUpdatePage = await topicInterestComponentsPage.goToCreateTopicInterest();
    await topicInterestUpdatePage.enterData();
    expect(await isVisible(topicInterestUpdatePage.saveButton)).to.be.false;

    expect(await topicInterestComponentsPage.createButton.isEnabled()).to.be.true;
    await waitUntilDisplayed(topicInterestComponentsPage.table);
    await waitUntilCount(topicInterestComponentsPage.records, beforeRecordsCount + 1);
    expect(await topicInterestComponentsPage.records.count()).to.eq(beforeRecordsCount + 1);

    await topicInterestComponentsPage.deleteTopicInterest();
    if (beforeRecordsCount !== 0) {
      await waitUntilCount(topicInterestComponentsPage.records, beforeRecordsCount);
      expect(await topicInterestComponentsPage.records.count()).to.eq(beforeRecordsCount);
    } else {
      await waitUntilDisplayed(topicInterestComponentsPage.noRecords);
    }
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
