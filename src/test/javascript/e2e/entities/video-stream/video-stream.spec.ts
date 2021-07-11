import { browser, element, by } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import SignInPage from './../../page-objects/signin-page';
import VideoStreamComponentsPage from './video-stream.page-object';
import VideoStreamUpdatePage from './video-stream-update.page-object';
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

describe('VideoStream e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let videoStreamComponentsPage: VideoStreamComponentsPage;
  let videoStreamUpdatePage: VideoStreamUpdatePage;
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
    videoStreamComponentsPage = new VideoStreamComponentsPage();
    videoStreamComponentsPage = await videoStreamComponentsPage.goToPage(navBarPage);
  });

  it('should load VideoStreams', async () => {
    expect(await videoStreamComponentsPage.title.getText()).to.match(/Video Streams/);
    expect(await videoStreamComponentsPage.createButton.isEnabled()).to.be.true;
  });

  it('should create and delete VideoStreams', async () => {
    const beforeRecordsCount = (await isVisible(videoStreamComponentsPage.noRecords))
      ? 0
      : await getRecordsCount(videoStreamComponentsPage.table);
    videoStreamUpdatePage = await videoStreamComponentsPage.goToCreateVideoStream();
    await videoStreamUpdatePage.enterData();
    expect(await isVisible(videoStreamUpdatePage.saveButton)).to.be.false;

    expect(await videoStreamComponentsPage.createButton.isEnabled()).to.be.true;
    await waitUntilDisplayed(videoStreamComponentsPage.table);
    await waitUntilCount(videoStreamComponentsPage.records, beforeRecordsCount + 1);
    expect(await videoStreamComponentsPage.records.count()).to.eq(beforeRecordsCount + 1);

    await videoStreamComponentsPage.deleteVideoStream();
    if (beforeRecordsCount !== 0) {
      await waitUntilCount(videoStreamComponentsPage.records, beforeRecordsCount);
      expect(await videoStreamComponentsPage.records.count()).to.eq(beforeRecordsCount);
    } else {
      await waitUntilDisplayed(videoStreamComponentsPage.noRecords);
    }
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
