import { browser, element, by } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import SignInPage from './../../page-objects/signin-page';
import PagePostComponentsPage from './page-post.page-object';
import PagePostUpdatePage from './page-post-update.page-object';
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

describe('PagePost e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let pagePostComponentsPage: PagePostComponentsPage;
  let pagePostUpdatePage: PagePostUpdatePage;
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
    pagePostComponentsPage = new PagePostComponentsPage();
    pagePostComponentsPage = await pagePostComponentsPage.goToPage(navBarPage);
  });

  it('should load PagePosts', async () => {
    expect(await pagePostComponentsPage.title.getText()).to.match(/Page Posts/);
    expect(await pagePostComponentsPage.createButton.isEnabled()).to.be.true;
  });

  it('should create and delete PagePosts', async () => {
    const beforeRecordsCount = (await isVisible(pagePostComponentsPage.noRecords))
      ? 0
      : await getRecordsCount(pagePostComponentsPage.table);
    pagePostUpdatePage = await pagePostComponentsPage.goToCreatePagePost();
    await pagePostUpdatePage.enterData();
    expect(await isVisible(pagePostUpdatePage.saveButton)).to.be.false;

    expect(await pagePostComponentsPage.createButton.isEnabled()).to.be.true;
    await waitUntilDisplayed(pagePostComponentsPage.table);
    await waitUntilCount(pagePostComponentsPage.records, beforeRecordsCount + 1);
    expect(await pagePostComponentsPage.records.count()).to.eq(beforeRecordsCount + 1);

    await pagePostComponentsPage.deletePagePost();
    if (beforeRecordsCount !== 0) {
      await waitUntilCount(pagePostComponentsPage.records, beforeRecordsCount);
      expect(await pagePostComponentsPage.records.count()).to.eq(beforeRecordsCount);
    } else {
      await waitUntilDisplayed(pagePostComponentsPage.noRecords);
    }
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
