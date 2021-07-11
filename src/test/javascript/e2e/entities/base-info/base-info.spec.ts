import { browser, element, by } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import SignInPage from './../../page-objects/signin-page';
import BaseInfoComponentsPage from './base-info.page-object';
import BaseInfoUpdatePage from './base-info-update.page-object';
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

describe('BaseInfo e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let baseInfoComponentsPage: BaseInfoComponentsPage;
  let baseInfoUpdatePage: BaseInfoUpdatePage;
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
    baseInfoComponentsPage = new BaseInfoComponentsPage();
    baseInfoComponentsPage = await baseInfoComponentsPage.goToPage(navBarPage);
  });

  it('should load BaseInfos', async () => {
    expect(await baseInfoComponentsPage.title.getText()).to.match(/Base Infos/);
    expect(await baseInfoComponentsPage.createButton.isEnabled()).to.be.true;
  });

  it('should create and delete BaseInfos', async () => {
    const beforeRecordsCount = (await isVisible(baseInfoComponentsPage.noRecords))
      ? 0
      : await getRecordsCount(baseInfoComponentsPage.table);
    baseInfoUpdatePage = await baseInfoComponentsPage.goToCreateBaseInfo();
    await baseInfoUpdatePage.enterData();
    expect(await isVisible(baseInfoUpdatePage.saveButton)).to.be.false;

    expect(await baseInfoComponentsPage.createButton.isEnabled()).to.be.true;
    await waitUntilDisplayed(baseInfoComponentsPage.table);
    await waitUntilCount(baseInfoComponentsPage.records, beforeRecordsCount + 1);
    expect(await baseInfoComponentsPage.records.count()).to.eq(beforeRecordsCount + 1);

    await baseInfoComponentsPage.deleteBaseInfo();
    if (beforeRecordsCount !== 0) {
      await waitUntilCount(baseInfoComponentsPage.records, beforeRecordsCount);
      expect(await baseInfoComponentsPage.records.count()).to.eq(beforeRecordsCount);
    } else {
      await waitUntilDisplayed(baseInfoComponentsPage.noRecords);
    }
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
