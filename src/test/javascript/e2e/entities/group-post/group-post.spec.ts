import { browser, element, by } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import SignInPage from './../../page-objects/signin-page';
import GroupPostComponentsPage from './group-post.page-object';
import GroupPostUpdatePage from './group-post-update.page-object';
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

describe('GroupPost e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let groupPostComponentsPage: GroupPostComponentsPage;
  let groupPostUpdatePage: GroupPostUpdatePage;
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
    groupPostComponentsPage = new GroupPostComponentsPage();
    groupPostComponentsPage = await groupPostComponentsPage.goToPage(navBarPage);
  });

  it('should load GroupPosts', async () => {
    expect(await groupPostComponentsPage.title.getText()).to.match(/Group Posts/);
    expect(await groupPostComponentsPage.createButton.isEnabled()).to.be.true;
  });

  it('should create and delete GroupPosts', async () => {
    const beforeRecordsCount = (await isVisible(groupPostComponentsPage.noRecords))
      ? 0
      : await getRecordsCount(groupPostComponentsPage.table);
    groupPostUpdatePage = await groupPostComponentsPage.goToCreateGroupPost();
    await groupPostUpdatePage.enterData();
    expect(await isVisible(groupPostUpdatePage.saveButton)).to.be.false;

    expect(await groupPostComponentsPage.createButton.isEnabled()).to.be.true;
    await waitUntilDisplayed(groupPostComponentsPage.table);
    await waitUntilCount(groupPostComponentsPage.records, beforeRecordsCount + 1);
    expect(await groupPostComponentsPage.records.count()).to.eq(beforeRecordsCount + 1);

    await groupPostComponentsPage.deleteGroupPost();
    if (beforeRecordsCount !== 0) {
      await waitUntilCount(groupPostComponentsPage.records, beforeRecordsCount);
      expect(await groupPostComponentsPage.records.count()).to.eq(beforeRecordsCount);
    } else {
      await waitUntilDisplayed(groupPostComponentsPage.noRecords);
    }
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
