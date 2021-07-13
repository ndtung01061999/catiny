import { element, by, ElementFinder, ElementArrayFinder } from 'protractor';

import { waitUntilAnyDisplayed, waitUntilDisplayed, click, waitUntilHidden, isVisible } from '../../util/utils';

import NavBarPage from './../../page-objects/navbar-page';

import HanhChinhVNUpdatePage from './hanh-chinh-vn-update.page-object';

const expect = chai.expect;
export class HanhChinhVNDeleteDialog {
  deleteModal = element(by.className('modal'));
  private dialogTitle: ElementFinder = element(by.id('catinyApp.hanhChinhVN.delete.question'));
  private confirmButton = element(by.id('jhi-confirm-delete-hanhChinhVN'));

  getDialogTitle() {
    return this.dialogTitle;
  }

  async clickOnConfirmButton() {
    await this.confirmButton.click();
  }
}

export default class HanhChinhVNComponentsPage {
  createButton: ElementFinder = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('div table .btn-danger'));
  title: ElementFinder = element(by.id('hanh-chinh-vn-heading'));
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
    await navBarPage.getEntityPage('hanh-chinh-vn');
    await waitUntilAnyDisplayed([this.noRecords, this.table]);
    return this;
  }

  async goToCreateHanhChinhVN() {
    await this.createButton.click();
    return new HanhChinhVNUpdatePage();
  }

  async deleteHanhChinhVN() {
    const deleteButton = this.getDeleteButton(this.records.last());
    await click(deleteButton);

    const hanhChinhVNDeleteDialog = new HanhChinhVNDeleteDialog();
    await waitUntilDisplayed(hanhChinhVNDeleteDialog.deleteModal);
    expect(await hanhChinhVNDeleteDialog.getDialogTitle().getAttribute('id')).to.match(/catinyApp.hanhChinhVN.delete.question/);
    await hanhChinhVNDeleteDialog.clickOnConfirmButton();

    await waitUntilHidden(hanhChinhVNDeleteDialog.deleteModal);

    expect(await isVisible(hanhChinhVNDeleteDialog.deleteModal)).to.be.false;
  }
}
