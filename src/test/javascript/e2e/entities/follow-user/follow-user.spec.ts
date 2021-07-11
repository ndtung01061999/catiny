import { browser, element, by } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import SignInPage from './../../page-objects/signin-page';
import FollowUserComponentsPage from './follow-user.page-object';
import FollowUserUpdatePage from './follow-user-update.page-object';
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

describe('FollowUser e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let followUserComponentsPage: FollowUserComponentsPage;
  let followUserUpdatePage: FollowUserUpdatePage;
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
    followUserComponentsPage = new FollowUserComponentsPage();
    followUserComponentsPage = await followUserComponentsPage.goToPage(navBarPage);
  });

  it('should load FollowUsers', async () => {
    expect(await followUserComponentsPage.title.getText()).to.match(/Follow Users/);
    expect(await followUserComponentsPage.createButton.isEnabled()).to.be.true;
  });

  it('should create and delete FollowUsers', async () => {
    const beforeRecordsCount = (await isVisible(followUserComponentsPage.noRecords))
      ? 0
      : await getRecordsCount(followUserComponentsPage.table);
    followUserUpdatePage = await followUserComponentsPage.goToCreateFollowUser();
    await followUserUpdatePage.enterData();
    expect(await isVisible(followUserUpdatePage.saveButton)).to.be.false;

    expect(await followUserComponentsPage.createButton.isEnabled()).to.be.true;
    await waitUntilDisplayed(followUserComponentsPage.table);
    await waitUntilCount(followUserComponentsPage.records, beforeRecordsCount + 1);
    expect(await followUserComponentsPage.records.count()).to.eq(beforeRecordsCount + 1);

    await followUserComponentsPage.deleteFollowUser();
    if (beforeRecordsCount !== 0) {
      await waitUntilCount(followUserComponentsPage.records, beforeRecordsCount);
      expect(await followUserComponentsPage.records.count()).to.eq(beforeRecordsCount);
    } else {
      await waitUntilDisplayed(followUserComponentsPage.noRecords);
    }
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
