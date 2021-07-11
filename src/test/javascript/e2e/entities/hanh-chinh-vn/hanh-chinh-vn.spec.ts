import { browser, element, by } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import SignInPage from './../../page-objects/signin-page';
import HanhChinhVNComponentsPage from './hanh-chinh-vn.page-object';
import HanhChinhVNUpdatePage from './hanh-chinh-vn-update.page-object';
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

describe('HanhChinhVN e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let hanhChinhVNComponentsPage: HanhChinhVNComponentsPage;
  let hanhChinhVNUpdatePage: HanhChinhVNUpdatePage;
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
    hanhChinhVNComponentsPage = new HanhChinhVNComponentsPage();
    hanhChinhVNComponentsPage = await hanhChinhVNComponentsPage.goToPage(navBarPage);
  });

  it('should load HanhChinhVNS', async () => {
    expect(await hanhChinhVNComponentsPage.title.getText()).to.match(/Hanh Chinh VNS/);
    expect(await hanhChinhVNComponentsPage.createButton.isEnabled()).to.be.true;
  });

  it('should create and delete HanhChinhVNS', async () => {
    const beforeRecordsCount = (await isVisible(hanhChinhVNComponentsPage.noRecords))
      ? 0
      : await getRecordsCount(hanhChinhVNComponentsPage.table);
    hanhChinhVNUpdatePage = await hanhChinhVNComponentsPage.goToCreateHanhChinhVN();
    await hanhChinhVNUpdatePage.enterData();
    expect(await isVisible(hanhChinhVNUpdatePage.saveButton)).to.be.false;

    expect(await hanhChinhVNComponentsPage.createButton.isEnabled()).to.be.true;
    await waitUntilDisplayed(hanhChinhVNComponentsPage.table);
    await waitUntilCount(hanhChinhVNComponentsPage.records, beforeRecordsCount + 1);
    expect(await hanhChinhVNComponentsPage.records.count()).to.eq(beforeRecordsCount + 1);

    await hanhChinhVNComponentsPage.deleteHanhChinhVN();
    if (beforeRecordsCount !== 0) {
      await waitUntilCount(hanhChinhVNComponentsPage.records, beforeRecordsCount);
      expect(await hanhChinhVNComponentsPage.records.count()).to.eq(beforeRecordsCount);
    } else {
      await waitUntilDisplayed(hanhChinhVNComponentsPage.noRecords);
    }
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
