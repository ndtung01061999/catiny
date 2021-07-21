import { element, by, ElementFinder, ElementArrayFinder } from 'protractor';

import { waitUntilAnyDisplayed, waitUntilDisplayed, click, waitUntilHidden, isVisible } from '../../util/utils';

import NavBarPage from './../../page-objects/navbar-page';

import ClassInfoUpdatePage from './class-info-update.page-object';

const expect = chai.expect;
export class ClassInfoDeleteDialog {
  deleteModal = element(by.className('modal'));
  private dialogTitle: ElementFinder = element(by.id('catinyApp.classInfo.delete.question'));
  private confirmButton = element(by.id('jhi-confirm-delete-classInfo'));

  getDialogTitle() {
    return this.dialogTitle;
  }

  async clickOnConfirmButton() {
    await this.confirmButton.click();
  }
}

export default class ClassInfoComponentsPage {
  createButton: ElementFinder = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('div table .btn-danger'));
  title: ElementFinder = element(by.id('class-info-heading'));
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
    await navBarPage.getEntityPage('class-info');
    await waitUntilAnyDisplayed([this.noRecords, this.table]);
    return this;
  }

  async goToCreateClassInfo() {
    await this.createButton.click();
    return new ClassInfoUpdatePage();
  }

  async deleteClassInfo() {
    const deleteButton = this.getDeleteButton(this.records.last());
    await click(deleteButton);

    const classInfoDeleteDialog = new ClassInfoDeleteDialog();
    await waitUntilDisplayed(classInfoDeleteDialog.deleteModal);
    expect(await classInfoDeleteDialog.getDialogTitle().getAttribute('id')).to.match(/catinyApp.classInfo.delete.question/);
    await classInfoDeleteDialog.clickOnConfirmButton();

    await waitUntilHidden(classInfoDeleteDialog.deleteModal);

    expect(await isVisible(classInfoDeleteDialog.deleteModal)).to.be.false;
  }
}
