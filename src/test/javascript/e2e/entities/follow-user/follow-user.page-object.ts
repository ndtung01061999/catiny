import { element, by, ElementFinder, ElementArrayFinder } from 'protractor';

import { waitUntilAnyDisplayed, waitUntilDisplayed, click, waitUntilHidden, isVisible } from '../../util/utils';

import NavBarPage from './../../page-objects/navbar-page';

import FollowUserUpdatePage from './follow-user-update.page-object';

const expect = chai.expect;
export class FollowUserDeleteDialog {
  deleteModal = element(by.className('modal'));
  private dialogTitle: ElementFinder = element(by.id('catinyApp.followUser.delete.question'));
  private confirmButton = element(by.id('jhi-confirm-delete-followUser'));

  getDialogTitle() {
    return this.dialogTitle;
  }

  async clickOnConfirmButton() {
    await this.confirmButton.click();
  }
}

export default class FollowUserComponentsPage {
  createButton: ElementFinder = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('div table .btn-danger'));
  title: ElementFinder = element(by.id('follow-user-heading'));
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
    await navBarPage.getEntityPage('follow-user');
    await waitUntilAnyDisplayed([this.noRecords, this.table]);
    return this;
  }

  async goToCreateFollowUser() {
    await this.createButton.click();
    return new FollowUserUpdatePage();
  }

  async deleteFollowUser() {
    const deleteButton = this.getDeleteButton(this.records.last());
    await click(deleteButton);

    const followUserDeleteDialog = new FollowUserDeleteDialog();
    await waitUntilDisplayed(followUserDeleteDialog.deleteModal);
    expect(await followUserDeleteDialog.getDialogTitle().getAttribute('id')).to.match(/catinyApp.followUser.delete.question/);
    await followUserDeleteDialog.clickOnConfirmButton();

    await waitUntilHidden(followUserDeleteDialog.deleteModal);

    expect(await isVisible(followUserDeleteDialog.deleteModal)).to.be.false;
  }
}
