import { browser, element, by } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import SignInPage from './../../page-objects/signin-page';
import FriendComponentsPage from './friend.page-object';
import FriendUpdatePage from './friend-update.page-object';
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

describe('Friend e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let friendComponentsPage: FriendComponentsPage;
  let friendUpdatePage: FriendUpdatePage;
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
    friendComponentsPage = new FriendComponentsPage();
    friendComponentsPage = await friendComponentsPage.goToPage(navBarPage);
  });

  it('should load Friends', async () => {
    expect(await friendComponentsPage.title.getText()).to.match(/Friends/);
    expect(await friendComponentsPage.createButton.isEnabled()).to.be.true;
  });

  it('should create and delete Friends', async () => {
    const beforeRecordsCount = (await isVisible(friendComponentsPage.noRecords)) ? 0 : await getRecordsCount(friendComponentsPage.table);
    friendUpdatePage = await friendComponentsPage.goToCreateFriend();
    await friendUpdatePage.enterData();
    expect(await isVisible(friendUpdatePage.saveButton)).to.be.false;

    expect(await friendComponentsPage.createButton.isEnabled()).to.be.true;
    await waitUntilDisplayed(friendComponentsPage.table);
    await waitUntilCount(friendComponentsPage.records, beforeRecordsCount + 1);
    expect(await friendComponentsPage.records.count()).to.eq(beforeRecordsCount + 1);

    await friendComponentsPage.deleteFriend();
    if (beforeRecordsCount !== 0) {
      await waitUntilCount(friendComponentsPage.records, beforeRecordsCount);
      expect(await friendComponentsPage.records.count()).to.eq(beforeRecordsCount);
    } else {
      await waitUntilDisplayed(friendComponentsPage.noRecords);
    }
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
