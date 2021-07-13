import { element, by, ElementFinder, ElementArrayFinder } from 'protractor';

import { waitUntilAnyDisplayed, waitUntilDisplayed, click, waitUntilHidden, isVisible } from '../../util/utils';

import NavBarPage from './../../page-objects/navbar-page';

import MasterUserUpdatePage from './master-user-update.page-object';

const expect = chai.expect;
export class MasterUserDeleteDialog {
  deleteModal = element(by.className('modal'));
  private dialogTitle: ElementFinder = element(by.id('catinyApp.masterUser.delete.question'));
  private confirmButton = element(by.id('jhi-confirm-delete-masterUser'));

  getDialogTitle() {
    return this.dialogTitle;
  }

  async clickOnConfirmButton() {
    await this.confirmButton.click();
  }
}

export default class MasterUserComponentsPage {
  createButton: ElementFinder = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('div table .btn-danger'));
  title: ElementFinder = element(by.id('master-user-heading'));
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
    await navBarPage.getEntityPage('master-user');
    await waitUntilAnyDisplayed([this.noRecords, this.table]);
    return this;
  }

  async goToCreateMasterUser() {
    await this.createButton.click();
    return new MasterUserUpdatePage();
  }

  async deleteMasterUser() {
    const deleteButton = this.getDeleteButton(this.records.last());
    await click(deleteButton);

    const masterUserDeleteDialog = new MasterUserDeleteDialog();
    await waitUntilDisplayed(masterUserDeleteDialog.deleteModal);
    expect(await masterUserDeleteDialog.getDialogTitle().getAttribute('id')).to.match(/catinyApp.masterUser.delete.question/);
    await masterUserDeleteDialog.clickOnConfirmButton();

    await waitUntilHidden(masterUserDeleteDialog.deleteModal);

    expect(await isVisible(masterUserDeleteDialog.deleteModal)).to.be.false;
  }
}
