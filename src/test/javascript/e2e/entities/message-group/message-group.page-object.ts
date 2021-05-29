import { element, by, ElementFinder, ElementArrayFinder } from 'protractor';

import { waitUntilAnyDisplayed, waitUntilDisplayed, click, waitUntilHidden, isVisible } from '../../util/utils';

import NavBarPage from './../../page-objects/navbar-page';

import MessageGroupUpdatePage from './message-group-update.page-object';

const expect = chai.expect;
export class MessageGroupDeleteDialog {
  deleteModal = element(by.className('modal'));
  private dialogTitle: ElementFinder = element(by.id('catinyApp.messageGroup.delete.question'));
  private confirmButton = element(by.id('jhi-confirm-delete-messageGroup'));

  getDialogTitle() {
    return this.dialogTitle;
  }

  async clickOnConfirmButton() {
    await this.confirmButton.click();
  }
}

export default class MessageGroupComponentsPage {
  createButton: ElementFinder = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('div table .btn-danger'));
  title: ElementFinder = element(by.id('message-group-heading'));
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
    await navBarPage.getEntityPage('message-group');
    await waitUntilAnyDisplayed([this.noRecords, this.table]);
    return this;
  }

  async goToCreateMessageGroup() {
    await this.createButton.click();
    return new MessageGroupUpdatePage();
  }

  async deleteMessageGroup() {
    const deleteButton = this.getDeleteButton(this.records.last());
    await click(deleteButton);

    const messageGroupDeleteDialog = new MessageGroupDeleteDialog();
    await waitUntilDisplayed(messageGroupDeleteDialog.deleteModal);
    expect(await messageGroupDeleteDialog.getDialogTitle().getAttribute('id')).to.match(/catinyApp.messageGroup.delete.question/);
    await messageGroupDeleteDialog.clickOnConfirmButton();

    await waitUntilHidden(messageGroupDeleteDialog.deleteModal);

    expect(await isVisible(messageGroupDeleteDialog.deleteModal)).to.be.false;
  }
}
