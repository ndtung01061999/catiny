import { browser, element, by } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import SignInPage from './../../page-objects/signin-page';
import PostCommentComponentsPage from './post-comment.page-object';
import PostCommentUpdatePage from './post-comment-update.page-object';
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

describe('PostComment e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let postCommentComponentsPage: PostCommentComponentsPage;
  let postCommentUpdatePage: PostCommentUpdatePage;
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
    postCommentComponentsPage = new PostCommentComponentsPage();
    postCommentComponentsPage = await postCommentComponentsPage.goToPage(navBarPage);
  });

  it('should load PostComments', async () => {
    expect(await postCommentComponentsPage.title.getText()).to.match(/Post Comments/);
    expect(await postCommentComponentsPage.createButton.isEnabled()).to.be.true;
  });

  it('should create and delete PostComments', async () => {
    const beforeRecordsCount = (await isVisible(postCommentComponentsPage.noRecords))
      ? 0
      : await getRecordsCount(postCommentComponentsPage.table);
    postCommentUpdatePage = await postCommentComponentsPage.goToCreatePostComment();
    await postCommentUpdatePage.enterData();
    expect(await isVisible(postCommentUpdatePage.saveButton)).to.be.false;

    expect(await postCommentComponentsPage.createButton.isEnabled()).to.be.true;
    await waitUntilDisplayed(postCommentComponentsPage.table);
    await waitUntilCount(postCommentComponentsPage.records, beforeRecordsCount + 1);
    expect(await postCommentComponentsPage.records.count()).to.eq(beforeRecordsCount + 1);

    await postCommentComponentsPage.deletePostComment();
    if (beforeRecordsCount !== 0) {
      await waitUntilCount(postCommentComponentsPage.records, beforeRecordsCount);
      expect(await postCommentComponentsPage.records.count()).to.eq(beforeRecordsCount);
    } else {
      await waitUntilDisplayed(postCommentComponentsPage.noRecords);
    }
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
