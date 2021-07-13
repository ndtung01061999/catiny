import { browser, element, by } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import SignInPage from './../../page-objects/signin-page';
import GroupProfileComponentsPage from './group-profile.page-object';
import GroupProfileUpdatePage from './group-profile-update.page-object';
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

describe('GroupProfile e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let groupProfileComponentsPage: GroupProfileComponentsPage;
  let groupProfileUpdatePage: GroupProfileUpdatePage;
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
    groupProfileComponentsPage = new GroupProfileComponentsPage();
    groupProfileComponentsPage = await groupProfileComponentsPage.goToPage(navBarPage);
  });

  it('should load GroupProfiles', async () => {
    expect(await groupProfileComponentsPage.title.getText()).to.match(/Group Profiles/);
    expect(await groupProfileComponentsPage.createButton.isEnabled()).to.be.true;
  });

  it('should create and delete GroupProfiles', async () => {
    const beforeRecordsCount = (await isVisible(groupProfileComponentsPage.noRecords))
      ? 0
      : await getRecordsCount(groupProfileComponentsPage.table);
    groupProfileUpdatePage = await groupProfileComponentsPage.goToCreateGroupProfile();
    await groupProfileUpdatePage.enterData();
    expect(await isVisible(groupProfileUpdatePage.saveButton)).to.be.false;

    expect(await groupProfileComponentsPage.createButton.isEnabled()).to.be.true;
    await waitUntilDisplayed(groupProfileComponentsPage.table);
    await waitUntilCount(groupProfileComponentsPage.records, beforeRecordsCount + 1);
    expect(await groupProfileComponentsPage.records.count()).to.eq(beforeRecordsCount + 1);

    await groupProfileComponentsPage.deleteGroupProfile();
    if (beforeRecordsCount !== 0) {
      await waitUntilCount(groupProfileComponentsPage.records, beforeRecordsCount);
      expect(await groupProfileComponentsPage.records.count()).to.eq(beforeRecordsCount);
    } else {
      await waitUntilDisplayed(groupProfileComponentsPage.noRecords);
    }
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
