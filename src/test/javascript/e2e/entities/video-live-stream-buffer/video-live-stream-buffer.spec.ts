import { browser, element, by } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import SignInPage from './../../page-objects/signin-page';
import VideoLiveStreamBufferComponentsPage from './video-live-stream-buffer.page-object';
import VideoLiveStreamBufferUpdatePage from './video-live-stream-buffer-update.page-object';
import {
  waitUntilDisplayed,
  waitUntilAnyDisplayed,
  click,
  getRecordsCount,
  waitUntilHidden,
  waitUntilCount,
  isVisible,
} from '../../util/utils';
import path from 'path';

const expect = chai.expect;

describe('VideoLiveStreamBuffer e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let videoLiveStreamBufferComponentsPage: VideoLiveStreamBufferComponentsPage;
  let videoLiveStreamBufferUpdatePage: VideoLiveStreamBufferUpdatePage;
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
    videoLiveStreamBufferComponentsPage = new VideoLiveStreamBufferComponentsPage();
    videoLiveStreamBufferComponentsPage = await videoLiveStreamBufferComponentsPage.goToPage(navBarPage);
  });

  it('should load VideoLiveStreamBuffers', async () => {
    expect(await videoLiveStreamBufferComponentsPage.title.getText()).to.match(/Video Live Stream Buffers/);
    expect(await videoLiveStreamBufferComponentsPage.createButton.isEnabled()).to.be.true;
  });

  it('should create and delete VideoLiveStreamBuffers', async () => {
    const beforeRecordsCount = (await isVisible(videoLiveStreamBufferComponentsPage.noRecords))
      ? 0
      : await getRecordsCount(videoLiveStreamBufferComponentsPage.table);
    videoLiveStreamBufferUpdatePage = await videoLiveStreamBufferComponentsPage.goToCreateVideoLiveStreamBuffer();
    await videoLiveStreamBufferUpdatePage.enterData();
    expect(await isVisible(videoLiveStreamBufferUpdatePage.saveButton)).to.be.false;

    expect(await videoLiveStreamBufferComponentsPage.createButton.isEnabled()).to.be.true;
    await waitUntilDisplayed(videoLiveStreamBufferComponentsPage.table);
    await waitUntilCount(videoLiveStreamBufferComponentsPage.records, beforeRecordsCount + 1);
    expect(await videoLiveStreamBufferComponentsPage.records.count()).to.eq(beforeRecordsCount + 1);

    await videoLiveStreamBufferComponentsPage.deleteVideoLiveStreamBuffer();
    if (beforeRecordsCount !== 0) {
      await waitUntilCount(videoLiveStreamBufferComponentsPage.records, beforeRecordsCount);
      expect(await videoLiveStreamBufferComponentsPage.records.count()).to.eq(beforeRecordsCount);
    } else {
      await waitUntilDisplayed(videoLiveStreamBufferComponentsPage.noRecords);
    }
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
