import { element, by, ElementFinder, ElementArrayFinder } from 'protractor';

import { waitUntilAnyDisplayed, waitUntilDisplayed, click, waitUntilHidden, isVisible } from '../../util/utils';

import NavBarPage from './../../page-objects/navbar-page';

import AccountStatusUpdatePage from './account-status-update.page-object';

const expect = chai.expect;
export class AccountStatusDeleteDialog {
  deleteModal = element(by.className('modal'));
  private dialogTitle: ElementFinder = element(by.id('catinyApp.accountStatus.delete.question'));
  private confirmButton = element(by.id('jhi-confirm-delete-accountStatus'));

  getDialogTitle() {
    return this.dialogTitle;
  }

  async clickOnConfirmButton() {
    await this.confirmButton.click();
  }
}

export default class AccountStatusComponentsPage {
  createButton: ElementFinder = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('div table .btn-danger'));
  title: ElementFinder = element(by.id('account-status-heading'));
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
    await navBarPage.getEntityPage('account-status');
    await waitUntilAnyDisplayed([this.noRecords, this.table]);
    return this;
  }

  async goToCreateAccountStatus() {
    await this.createButton.click();
    return new AccountStatusUpdatePage();
  }

  async deleteAccountStatus() {
    const deleteButton = this.getDeleteButton(this.records.last());
    await click(deleteButton);

    const accountStatusDeleteDialog = new AccountStatusDeleteDialog();
    await waitUntilDisplayed(accountStatusDeleteDialog.deleteModal);
    expect(await accountStatusDeleteDialog.getDialogTitle().getAttribute('id')).to.match(/catinyApp.accountStatus.delete.question/);
    await accountStatusDeleteDialog.clickOnConfirmButton();

    await waitUntilHidden(accountStatusDeleteDialog.deleteModal);

    expect(await isVisible(accountStatusDeleteDialog.deleteModal)).to.be.false;
  }
}
