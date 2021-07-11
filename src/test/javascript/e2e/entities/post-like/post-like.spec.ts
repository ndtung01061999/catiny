import { browser, element, by } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import SignInPage from './../../page-objects/signin-page';
import PostLikeComponentsPage from './post-like.page-object';
import PostLikeUpdatePage from './post-like-update.page-object';
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

describe('PostLike e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let postLikeComponentsPage: PostLikeComponentsPage;
  let postLikeUpdatePage: PostLikeUpdatePage;
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
    postLikeComponentsPage = new PostLikeComponentsPage();
    postLikeComponentsPage = await postLikeComponentsPage.goToPage(navBarPage);
  });

  it('should load PostLikes', async () => {
    expect(await postLikeComponentsPage.title.getText()).to.match(/Post Likes/);
    expect(await postLikeComponentsPage.createButton.isEnabled()).to.be.true;
  });

  it('should create and delete PostLikes', async () => {
    const beforeRecordsCount = (await isVisible(postLikeComponentsPage.noRecords))
      ? 0
      : await getRecordsCount(postLikeComponentsPage.table);
    postLikeUpdatePage = await postLikeComponentsPage.goToCreatePostLike();
    await postLikeUpdatePage.enterData();
    expect(await isVisible(postLikeUpdatePage.saveButton)).to.be.false;

    expect(await postLikeComponentsPage.createButton.isEnabled()).to.be.true;
    await waitUntilDisplayed(postLikeComponentsPage.table);
    await waitUntilCount(postLikeComponentsPage.records, beforeRecordsCount + 1);
    expect(await postLikeComponentsPage.records.count()).to.eq(beforeRecordsCount + 1);

    await postLikeComponentsPage.deletePostLike();
    if (beforeRecordsCount !== 0) {
      await waitUntilCount(postLikeComponentsPage.records, beforeRecordsCount);
      expect(await postLikeComponentsPage.records.count()).to.eq(beforeRecordsCount);
    } else {
      await waitUntilDisplayed(postLikeComponentsPage.noRecords);
    }
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
