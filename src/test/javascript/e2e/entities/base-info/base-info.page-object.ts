import { element, by, ElementFinder, ElementArrayFinder } from 'protractor';

import { waitUntilAnyDisplayed, waitUntilDisplayed, click, waitUntilHidden, isVisible } from '../../util/utils';

import NavBarPage from './../../page-objects/navbar-page';

import BaseInfoUpdatePage from './base-info-update.page-object';

const expect = chai.expect;
export class BaseInfoDeleteDialog {
  deleteModal = element(by.className('modal'));
  private dialogTitle: ElementFinder = element(by.id('catinyApp.baseInfo.delete.question'));
  private confirmButton = element(by.id('jhi-confirm-delete-baseInfo'));

  getDialogTitle() {
    return this.dialogTitle;
  }

  async clickOnConfirmButton() {
    await this.confirmButton.click();
  }
}

export default class BaseInfoComponentsPage {
  createButton: ElementFinder = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('div table .btn-danger'));
  title: ElementFinder = element(by.id('base-info-heading'));
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
    await navBarPage.getEntityPage('base-info');
    await waitUntilAnyDisplayed([this.noRecords, this.table]);
    return this;
  }

  async goToCreateBaseInfo() {
    await this.createButton.click();
    return new BaseInfoUpdatePage();
  }

  async deleteBaseInfo() {
    const deleteButton = this.getDeleteButton(this.records.last());
    await click(deleteButton);

    const baseInfoDeleteDialog = new BaseInfoDeleteDialog();
    await waitUntilDisplayed(baseInfoDeleteDialog.deleteModal);
    expect(await baseInfoDeleteDialog.getDialogTitle().getAttribute('id')).to.match(/catinyApp.baseInfo.delete.question/);
    await baseInfoDeleteDialog.clickOnConfirmButton();

    await waitUntilHidden(baseInfoDeleteDialog.deleteModal);

    expect(await isVisible(baseInfoDeleteDialog.deleteModal)).to.be.false;
  }
}
