import { element, by, ElementFinder, ElementArrayFinder } from 'protractor';

import { waitUntilAnyDisplayed, waitUntilDisplayed, click, waitUntilHidden, isVisible } from '../../util/utils';

import NavBarPage from './../../page-objects/navbar-page';

import GroupPostUpdatePage from './group-post-update.page-object';

const expect = chai.expect;
export class GroupPostDeleteDialog {
  deleteModal = element(by.className('modal'));
  private dialogTitle: ElementFinder = element(by.id('catinyApp.groupPost.delete.question'));
  private confirmButton = element(by.id('jhi-confirm-delete-groupPost'));

  getDialogTitle() {
    return this.dialogTitle;
  }

  async clickOnConfirmButton() {
    await this.confirmButton.click();
  }
}

export default class GroupPostComponentsPage {
  createButton: ElementFinder = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('div table .btn-danger'));
  title: ElementFinder = element(by.id('group-post-heading'));
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
    await navBarPage.getEntityPage('group-post');
    await waitUntilAnyDisplayed([this.noRecords, this.table]);
    return this;
  }

  async goToCreateGroupPost() {
    await this.createButton.click();
    return new GroupPostUpdatePage();
  }

  async deleteGroupPost() {
    const deleteButton = this.getDeleteButton(this.records.last());
    await click(deleteButton);

    const groupPostDeleteDialog = new GroupPostDeleteDialog();
    await waitUntilDisplayed(groupPostDeleteDialog.deleteModal);
    expect(await groupPostDeleteDialog.getDialogTitle().getAttribute('id')).to.match(/catinyApp.groupPost.delete.question/);
    await groupPostDeleteDialog.clickOnConfirmButton();

    await waitUntilHidden(groupPostDeleteDialog.deleteModal);

    expect(await isVisible(groupPostDeleteDialog.deleteModal)).to.be.false;
  }
}
