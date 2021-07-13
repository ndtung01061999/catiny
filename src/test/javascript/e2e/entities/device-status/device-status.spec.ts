import { browser, element, by } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import SignInPage from './../../page-objects/signin-page';
import DeviceStatusComponentsPage from './device-status.page-object';
import DeviceStatusUpdatePage from './device-status-update.page-object';
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

describe('DeviceStatus e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let deviceStatusComponentsPage: DeviceStatusComponentsPage;
  let deviceStatusUpdatePage: DeviceStatusUpdatePage;
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
    deviceStatusComponentsPage = new DeviceStatusComponentsPage();
    deviceStatusComponentsPage = await deviceStatusComponentsPage.goToPage(navBarPage);
  });

  it('should load DeviceStatuses', async () => {
    expect(await deviceStatusComponentsPage.title.getText()).to.match(/Device Statuses/);
    expect(await deviceStatusComponentsPage.createButton.isEnabled()).to.be.true;
  });

  it('should create and delete DeviceStatuses', async () => {
    const beforeRecordsCount = (await isVisible(deviceStatusComponentsPage.noRecords))
      ? 0
      : await getRecordsCount(deviceStatusComponentsPage.table);
    deviceStatusUpdatePage = await deviceStatusComponentsPage.goToCreateDeviceStatus();
    await deviceStatusUpdatePage.enterData();
    expect(await isVisible(deviceStatusUpdatePage.saveButton)).to.be.false;

    expect(await deviceStatusComponentsPage.createButton.isEnabled()).to.be.true;
    await waitUntilDisplayed(deviceStatusComponentsPage.table);
    await waitUntilCount(deviceStatusComponentsPage.records, beforeRecordsCount + 1);
    expect(await deviceStatusComponentsPage.records.count()).to.eq(beforeRecordsCount + 1);

    await deviceStatusComponentsPage.deleteDeviceStatus();
    if (beforeRecordsCount !== 0) {
      await waitUntilCount(deviceStatusComponentsPage.records, beforeRecordsCount);
      expect(await deviceStatusComponentsPage.records.count()).to.eq(beforeRecordsCount);
    } else {
      await waitUntilDisplayed(deviceStatusComponentsPage.noRecords);
    }
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
