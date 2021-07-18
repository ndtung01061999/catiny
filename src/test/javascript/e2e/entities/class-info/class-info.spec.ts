import { browser, element, by } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import SignInPage from './../../page-objects/signin-page';
import ClassInfoComponentsPage from './class-info.page-object';
import ClassInfoUpdatePage from './class-info-update.page-object';
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

describe('ClassInfo e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let classInfoComponentsPage: ClassInfoComponentsPage;
  let classInfoUpdatePage: ClassInfoUpdatePage;
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
    classInfoComponentsPage = new ClassInfoComponentsPage();
    classInfoComponentsPage = await classInfoComponentsPage.goToPage(navBarPage);
  });

  it('should load ClassInfos', async () => {
    expect(await classInfoComponentsPage.title.getText()).to.match(/Class Infos/);
    expect(await classInfoComponentsPage.createButton.isEnabled()).to.be.true;
  });

  it('should create and delete ClassInfos', async () => {
    const beforeRecordsCount = (await isVisible(classInfoComponentsPage.noRecords))
      ? 0
      : await getRecordsCount(classInfoComponentsPage.table);
    classInfoUpdatePage = await classInfoComponentsPage.goToCreateClassInfo();
    await classInfoUpdatePage.enterData();
    expect(await isVisible(classInfoUpdatePage.saveButton)).to.be.false;

    expect(await classInfoComponentsPage.createButton.isEnabled()).to.be.true;
    await waitUntilDisplayed(classInfoComponentsPage.table);
    await waitUntilCount(classInfoComponentsPage.records, beforeRecordsCount + 1);
    expect(await classInfoComponentsPage.records.count()).to.eq(beforeRecordsCount + 1);

    await classInfoComponentsPage.deleteClassInfo();
    if (beforeRecordsCount !== 0) {
      await waitUntilCount(classInfoComponentsPage.records, beforeRecordsCount);
      expect(await classInfoComponentsPage.records.count()).to.eq(beforeRecordsCount);
    } else {
      await waitUntilDisplayed(classInfoComponentsPage.noRecords);
    }
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
