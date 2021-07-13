import { browser, element, by } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import SignInPage from './../../page-objects/signin-page';
import PageProfileComponentsPage from './page-profile.page-object';
import PageProfileUpdatePage from './page-profile-update.page-object';
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

describe('PageProfile e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let pageProfileComponentsPage: PageProfileComponentsPage;
  let pageProfileUpdatePage: PageProfileUpdatePage;
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
    pageProfileComponentsPage = new PageProfileComponentsPage();
    pageProfileComponentsPage = await pageProfileComponentsPage.goToPage(navBarPage);
  });

  it('should load PageProfiles', async () => {
    expect(await pageProfileComponentsPage.title.getText()).to.match(/Page Profiles/);
    expect(await pageProfileComponentsPage.createButton.isEnabled()).to.be.true;
  });

  it('should create and delete PageProfiles', async () => {
    const beforeRecordsCount = (await isVisible(pageProfileComponentsPage.noRecords))
      ? 0
      : await getRecordsCount(pageProfileComponentsPage.table);
    pageProfileUpdatePage = await pageProfileComponentsPage.goToCreatePageProfile();
    await pageProfileUpdatePage.enterData();
    expect(await isVisible(pageProfileUpdatePage.saveButton)).to.be.false;

    expect(await pageProfileComponentsPage.createButton.isEnabled()).to.be.true;
    await waitUntilDisplayed(pageProfileComponentsPage.table);
    await waitUntilCount(pageProfileComponentsPage.records, beforeRecordsCount + 1);
    expect(await pageProfileComponentsPage.records.count()).to.eq(beforeRecordsCount + 1);

    await pageProfileComponentsPage.deletePageProfile();
    if (beforeRecordsCount !== 0) {
      await waitUntilCount(pageProfileComponentsPage.records, beforeRecordsCount);
      expect(await pageProfileComponentsPage.records.count()).to.eq(beforeRecordsCount);
    } else {
      await waitUntilDisplayed(pageProfileComponentsPage.noRecords);
    }
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
