import { browser, element, by } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import SignInPage from './../../page-objects/signin-page';
import MasterUserComponentsPage from './master-user.page-object';
import MasterUserUpdatePage from './master-user-update.page-object';
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

describe('MasterUser e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let masterUserComponentsPage: MasterUserComponentsPage;
  let masterUserUpdatePage: MasterUserUpdatePage;
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
    masterUserComponentsPage = new MasterUserComponentsPage();
    masterUserComponentsPage = await masterUserComponentsPage.goToPage(navBarPage);
  });

  it('should load MasterUsers', async () => {
    expect(await masterUserComponentsPage.title.getText()).to.match(/Master Users/);
    expect(await masterUserComponentsPage.createButton.isEnabled()).to.be.true;
  });

  /* it('should create and delete MasterUsers', async () => {
        const beforeRecordsCount = await isVisible(masterUserComponentsPage.noRecords) ? 0 : await getRecordsCount(masterUserComponentsPage.table);
        masterUserUpdatePage = await masterUserComponentsPage.goToCreateMasterUser();
        await masterUserUpdatePage.enterData();
        expect(await isVisible(masterUserUpdatePage.saveButton)).to.be.false;

        expect(await masterUserComponentsPage.createButton.isEnabled()).to.be.true;
        await waitUntilDisplayed(masterUserComponentsPage.table);
        await waitUntilCount(masterUserComponentsPage.records, beforeRecordsCount + 1);
        expect(await masterUserComponentsPage.records.count()).to.eq(beforeRecordsCount + 1);

        await masterUserComponentsPage.deleteMasterUser();
        if(beforeRecordsCount !== 0) {
          await waitUntilCount(masterUserComponentsPage.records, beforeRecordsCount);
          expect(await masterUserComponentsPage.records.count()).to.eq(beforeRecordsCount);
        } else {
          await waitUntilDisplayed(masterUserComponentsPage.noRecords);
        }
    }); */

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
