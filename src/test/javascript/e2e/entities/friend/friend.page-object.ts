import { element, by, ElementFinder, ElementArrayFinder } from 'protractor';

import { waitUntilAnyDisplayed, waitUntilDisplayed, click, waitUntilHidden, isVisible } from '../../util/utils';

import NavBarPage from './../../page-objects/navbar-page';

import FriendUpdatePage from './friend-update.page-object';

const expect = chai.expect;
export class FriendDeleteDialog {
  deleteModal = element(by.className('modal'));
  private dialogTitle: ElementFinder = element(by.id('catinyApp.friend.delete.question'));
  private confirmButton = element(by.id('jhi-confirm-delete-friend'));

  getDialogTitle() {
    return this.dialogTitle;
  }

  async clickOnConfirmButton() {
    await this.confirmButton.click();
  }
}

export default class FriendComponentsPage {
  createButton: ElementFinder = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('div table .btn-danger'));
  title: ElementFinder = element(by.id('friend-heading'));
  noRecords: ElementFinder = element(by.css('#app-view-container .table-responsive div.alert.alert-warning'));
  table: ElementFinder = element(by.css('#app-view-container div.table-responsive > table'));

  records: ElementArrayFinder = this.table.all(by.css('tbody tr'));

  getDetailsButton(record: ElementFinder) {
    return record.element(by.css('a.btn.btn-info.btn-sm'));
  }

  getEditButton(record: ElementFinder) {
    return record.element(by.css('a.btn.btn-primary.btn-sm'));
  }

  getDeleteButton(record: ElementFinder) {
    return record.element(by.css('a.btn.btn-danger.btn-sm'));
  }

  async goToPage(navBarPage: NavBarPage) {
    await navBarPage.getEntityPage('friend');
    await waitUntilAnyDisplayed([this.noRecords, this.table]);
    return this;
  }

  async goToCreateFriend() {
    await this.createButton.click();
    return new FriendUpdatePage();
  }

  async deleteFriend() {
    const deleteButton = this.getDeleteButton(this.records.last());
    await click(deleteButton);

    const friendDeleteDialog = new FriendDeleteDialog();
    await waitUntilDisplayed(friendDeleteDialog.deleteModal);
    expect(await friendDeleteDialog.getDialogTitle().getAttribute('id')).to.match(/catinyApp.friend.delete.question/);
    await friendDeleteDialog.clickOnConfirmButton();

    await waitUntilHidden(friendDeleteDialog.deleteModal);

    expect(await isVisible(friendDeleteDialog.deleteModal)).to.be.false;
  }
}
