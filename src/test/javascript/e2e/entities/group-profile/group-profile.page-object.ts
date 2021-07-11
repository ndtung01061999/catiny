import { element, by, ElementFinder, ElementArrayFinder } from 'protractor';

import { waitUntilAnyDisplayed, waitUntilDisplayed, click, waitUntilHidden, isVisible } from '../../util/utils';

import NavBarPage from './../../page-objects/navbar-page';

import GroupProfileUpdatePage from './group-profile-update.page-object';

const expect = chai.expect;
export class GroupProfileDeleteDialog {
  deleteModal = element(by.className('modal'));
  private dialogTitle: ElementFinder = element(by.id('catinyApp.groupProfile.delete.question'));
  private confirmButton = element(by.id('jhi-confirm-delete-groupProfile'));

  getDialogTitle() {
    return this.dialogTitle;
  }

  async clickOnConfirmButton() {
    await this.confirmButton.click();
  }
}

export default class GroupProfileComponentsPage {
  createButton: ElementFinder = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('div table .btn-danger'));
  title: ElementFinder = element(by.id('group-profile-heading'));
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
    await navBarPage.getEntityPage('group-profile');
    await waitUntilAnyDisplayed([this.noRecords, this.table]);
    return this;
  }

  async goToCreateGroupProfile() {
    await this.createButton.click();
    return new GroupProfileUpdatePage();
  }

  async deleteGroupProfile() {
    const deleteButton = this.getDeleteButton(this.records.last());
    await click(deleteButton);

    const groupProfileDeleteDialog = new GroupProfileDeleteDialog();
    await waitUntilDisplayed(groupProfileDeleteDialog.deleteModal);
    expect(await groupProfileDeleteDialog.getDialogTitle().getAttribute('id')).to.match(/catinyApp.groupProfile.delete.question/);
    await groupProfileDeleteDialog.clickOnConfirmButton();

    await waitUntilHidden(groupProfileDeleteDialog.deleteModal);

    expect(await isVisible(groupProfileDeleteDialog.deleteModal)).to.be.false;
  }
}
