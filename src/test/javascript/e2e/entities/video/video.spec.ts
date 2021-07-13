import { browser, element, by } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import SignInPage from './../../page-objects/signin-page';
import VideoComponentsPage from './video.page-object';
import VideoUpdatePage from './video-update.page-object';
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

describe('Video e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let videoComponentsPage: VideoComponentsPage;
  let videoUpdatePage: VideoUpdatePage;
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
    videoComponentsPage = new VideoComponentsPage();
    videoComponentsPage = await videoComponentsPage.goToPage(navBarPage);
  });

  it('should load Videos', async () => {
    expect(await videoComponentsPage.title.getText()).to.match(/Videos/);
    expect(await videoComponentsPage.createButton.isEnabled()).to.be.true;
  });

  it('should create and delete Videos', async () => {
    const beforeRecordsCount = (await isVisible(videoComponentsPage.noRecords)) ? 0 : await getRecordsCount(videoComponentsPage.table);
    videoUpdatePage = await videoComponentsPage.goToCreateVideo();
    await videoUpdatePage.enterData();
    expect(await isVisible(videoUpdatePage.saveButton)).to.be.false;

    expect(await videoComponentsPage.createButton.isEnabled()).to.be.true;
    await waitUntilDisplayed(videoComponentsPage.table);
    await waitUntilCount(videoComponentsPage.records, beforeRecordsCount + 1);
    expect(await videoComponentsPage.records.count()).to.eq(beforeRecordsCount + 1);

    await videoComponentsPage.deleteVideo();
    if (beforeRecordsCount !== 0) {
      await waitUntilCount(videoComponentsPage.records, beforeRecordsCount);
      expect(await videoComponentsPage.records.count()).to.eq(beforeRecordsCount);
    } else {
      await waitUntilDisplayed(videoComponentsPage.noRecords);
    }
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
