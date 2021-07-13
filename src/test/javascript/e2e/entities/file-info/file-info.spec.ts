import { browser, element, by } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import SignInPage from './../../page-objects/signin-page';
import FileInfoComponentsPage from './file-info.page-object';
import FileInfoUpdatePage from './file-info-update.page-object';
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

describe('FileInfo e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let fileInfoComponentsPage: FileInfoComponentsPage;
  let fileInfoUpdatePage: FileInfoUpdatePage;
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
    fileInfoComponentsPage = new FileInfoComponentsPage();
    fileInfoComponentsPage = await fileInfoComponentsPage.goToPage(navBarPage);
  });

  it('should load FileInfos', async () => {
    expect(await fileInfoComponentsPage.title.getText()).to.match(/File Infos/);
    expect(await fileInfoComponentsPage.createButton.isEnabled()).to.be.true;
  });

  it('should create and delete FileInfos', async () => {
    const beforeRecordsCount = (await isVisible(fileInfoComponentsPage.noRecords))
      ? 0
      : await getRecordsCount(fileInfoComponentsPage.table);
    fileInfoUpdatePage = await fileInfoComponentsPage.goToCreateFileInfo();
    await fileInfoUpdatePage.enterData();
    expect(await isVisible(fileInfoUpdatePage.saveButton)).to.be.false;

    expect(await fileInfoComponentsPage.createButton.isEnabled()).to.be.true;
    await waitUntilDisplayed(fileInfoComponentsPage.table);
    await waitUntilCount(fileInfoComponentsPage.records, beforeRecordsCount + 1);
    expect(await fileInfoComponentsPage.records.count()).to.eq(beforeRecordsCount + 1);

    await fileInfoComponentsPage.deleteFileInfo();
    if (beforeRecordsCount !== 0) {
      await waitUntilCount(fileInfoComponentsPage.records, beforeRecordsCount);
      expect(await fileInfoComponentsPage.records.count()).to.eq(beforeRecordsCount);
    } else {
      await waitUntilDisplayed(fileInfoComponentsPage.noRecords);
    }
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
