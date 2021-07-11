import { browser, element, by } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import SignInPage from './../../page-objects/signin-page';
import FollowPageComponentsPage from './follow-page.page-object';
import FollowPageUpdatePage from './follow-page-update.page-object';
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

describe('FollowPage e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let followPageComponentsPage: FollowPageComponentsPage;
  let followPageUpdatePage: FollowPageUpdatePage;
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
    followPageComponentsPage = new FollowPageComponentsPage();
    followPageComponentsPage = await followPageComponentsPage.goToPage(navBarPage);
  });

  it('should load FollowPages', async () => {
    expect(await followPageComponentsPage.title.getText()).to.match(/Follow Pages/);
    expect(await followPageComponentsPage.createButton.isEnabled()).to.be.true;
  });

  it('should create and delete FollowPages', async () => {
    const beforeRecordsCount = (await isVisible(followPageComponentsPage.noRecords))
      ? 0
      : await getRecordsCount(followPageComponentsPage.table);
    followPageUpdatePage = await followPageComponentsPage.goToCreateFollowPage();
    await followPageUpdatePage.enterData();
    expect(await isVisible(followPageUpdatePage.saveButton)).to.be.false;

    expect(await followPageComponentsPage.createButton.isEnabled()).to.be.true;
    await waitUntilDisplayed(followPageComponentsPage.table);
    await waitUntilCount(followPageComponentsPage.records, beforeRecordsCount + 1);
    expect(await followPageComponentsPage.records.count()).to.eq(beforeRecordsCount + 1);

    await followPageComponentsPage.deleteFollowPage();
    if (beforeRecordsCount !== 0) {
      await waitUntilCount(followPageComponentsPage.records, beforeRecordsCount);
      expect(await followPageComponentsPage.records.count()).to.eq(beforeRecordsCount);
    } else {
      await waitUntilDisplayed(followPageComponentsPage.noRecords);
    }
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
