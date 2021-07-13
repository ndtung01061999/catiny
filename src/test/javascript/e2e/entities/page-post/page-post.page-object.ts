import { element, by, ElementFinder, ElementArrayFinder } from 'protractor';

import { waitUntilAnyDisplayed, waitUntilDisplayed, click, waitUntilHidden, isVisible } from '../../util/utils';

import NavBarPage from './../../page-objects/navbar-page';

import PagePostUpdatePage from './page-post-update.page-object';

const expect = chai.expect;
export class PagePostDeleteDialog {
  deleteModal = element(by.className('modal'));
  private dialogTitle: ElementFinder = element(by.id('catinyApp.pagePost.delete.question'));
  private confirmButton = element(by.id('jhi-confirm-delete-pagePost'));

  getDialogTitle() {
    return this.dialogTitle;
  }

  async clickOnConfirmButton() {
    await this.confirmButton.click();
  }
}

export default class PagePostComponentsPage {
  createButton: ElementFinder = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('div table .btn-danger'));
  title: ElementFinder = element(by.id('page-post-heading'));
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
    await navBarPage.getEntityPage('page-post');
    await waitUntilAnyDisplayed([this.noRecords, this.table]);
    return this;
  }

  async goToCreatePagePost() {
    await this.createButton.click();
    return new PagePostUpdatePage();
  }

  async deletePagePost() {
    const deleteButton = this.getDeleteButton(this.records.last());
    await click(deleteButton);

    const pagePostDeleteDialog = new PagePostDeleteDialog();
    await waitUntilDisplayed(pagePostDeleteDialog.deleteModal);
    expect(await pagePostDeleteDialog.getDialogTitle().getAttribute('id')).to.match(/catinyApp.pagePost.delete.question/);
    await pagePostDeleteDialog.clickOnConfirmButton();

    await waitUntilHidden(pagePostDeleteDialog.deleteModal);

    expect(await isVisible(pagePostDeleteDialog.deleteModal)).to.be.false;
  }
}
