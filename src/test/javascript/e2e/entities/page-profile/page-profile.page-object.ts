import { element, by, ElementFinder, ElementArrayFinder } from 'protractor';

import { waitUntilAnyDisplayed, waitUntilDisplayed, click, waitUntilHidden, isVisible } from '../../util/utils';

import NavBarPage from './../../page-objects/navbar-page';

import PageProfileUpdatePage from './page-profile-update.page-object';

const expect = chai.expect;
export class PageProfileDeleteDialog {
  deleteModal = element(by.className('modal'));
  private dialogTitle: ElementFinder = element(by.id('catinyApp.pageProfile.delete.question'));
  private confirmButton = element(by.id('jhi-confirm-delete-pageProfile'));

  getDialogTitle() {
    return this.dialogTitle;
  }

  async clickOnConfirmButton() {
    await this.confirmButton.click();
  }
}

export default class PageProfileComponentsPage {
  createButton: ElementFinder = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('div table .btn-danger'));
  title: ElementFinder = element(by.id('page-profile-heading'));
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
    await navBarPage.getEntityPage('page-profile');
    await waitUntilAnyDisplayed([this.noRecords, this.table]);
    return this;
  }

  async goToCreatePageProfile() {
    await this.createButton.click();
    return new PageProfileUpdatePage();
  }

  async deletePageProfile() {
    const deleteButton = this.getDeleteButton(this.records.last());
    await click(deleteButton);

    const pageProfileDeleteDialog = new PageProfileDeleteDialog();
    await waitUntilDisplayed(pageProfileDeleteDialog.deleteModal);
    expect(await pageProfileDeleteDialog.getDialogTitle().getAttribute('id')).to.match(/catinyApp.pageProfile.delete.question/);
    await pageProfileDeleteDialog.clickOnConfirmButton();

    await waitUntilHidden(pageProfileDeleteDialog.deleteModal);

    expect(await isVisible(pageProfileDeleteDialog.deleteModal)).to.be.false;
  }
}
