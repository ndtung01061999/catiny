import { element, by, ElementFinder, ElementArrayFinder } from 'protractor';

import { waitUntilAnyDisplayed, waitUntilDisplayed, click, waitUntilHidden, isVisible } from '../../util/utils';

import NavBarPage from './../../page-objects/navbar-page';

import TopicInterestUpdatePage from './topic-interest-update.page-object';

const expect = chai.expect;
export class TopicInterestDeleteDialog {
  deleteModal = element(by.className('modal'));
  private dialogTitle: ElementFinder = element(by.id('catinyApp.topicInterest.delete.question'));
  private confirmButton = element(by.id('jhi-confirm-delete-topicInterest'));

  getDialogTitle() {
    return this.dialogTitle;
  }

  async clickOnConfirmButton() {
    await this.confirmButton.click();
  }
}

export default class TopicInterestComponentsPage {
  createButton: ElementFinder = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('div table .btn-danger'));
  title: ElementFinder = element(by.id('topic-interest-heading'));
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
    await navBarPage.getEntityPage('topic-interest');
    await waitUntilAnyDisplayed([this.noRecords, this.table]);
    return this;
  }

  async goToCreateTopicInterest() {
    await this.createButton.click();
    return new TopicInterestUpdatePage();
  }

  async deleteTopicInterest() {
    const deleteButton = this.getDeleteButton(this.records.last());
    await click(deleteButton);

    const topicInterestDeleteDialog = new TopicInterestDeleteDialog();
    await waitUntilDisplayed(topicInterestDeleteDialog.deleteModal);
    expect(await topicInterestDeleteDialog.getDialogTitle().getAttribute('id')).to.match(/catinyApp.topicInterest.delete.question/);
    await topicInterestDeleteDialog.clickOnConfirmButton();

    await waitUntilHidden(topicInterestDeleteDialog.deleteModal);

    expect(await isVisible(topicInterestDeleteDialog.deleteModal)).to.be.false;
  }
}
