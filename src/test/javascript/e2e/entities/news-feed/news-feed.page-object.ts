import { element, by, ElementFinder, ElementArrayFinder } from 'protractor';

import { waitUntilAnyDisplayed, waitUntilDisplayed, click, waitUntilHidden, isVisible } from '../../util/utils';

import NavBarPage from './../../page-objects/navbar-page';

import NewsFeedUpdatePage from './news-feed-update.page-object';

const expect = chai.expect;
export class NewsFeedDeleteDialog {
  deleteModal = element(by.className('modal'));
  private dialogTitle: ElementFinder = element(by.id('catinyApp.newsFeed.delete.question'));
  private confirmButton = element(by.id('jhi-confirm-delete-newsFeed'));

  getDialogTitle() {
    return this.dialogTitle;
  }

  async clickOnConfirmButton() {
    await this.confirmButton.click();
  }
}

export default class NewsFeedComponentsPage {
  createButton: ElementFinder = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('div table .btn-danger'));
  title: ElementFinder = element(by.id('news-feed-heading'));
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
    await navBarPage.getEntityPage('news-feed');
    await waitUntilAnyDisplayed([this.noRecords, this.table]);
    return this;
  }

  async goToCreateNewsFeed() {
    await this.createButton.click();
    return new NewsFeedUpdatePage();
  }

  async deleteNewsFeed() {
    const deleteButton = this.getDeleteButton(this.records.last());
    await click(deleteButton);

    const newsFeedDeleteDialog = new NewsFeedDeleteDialog();
    await waitUntilDisplayed(newsFeedDeleteDialog.deleteModal);
    expect(await newsFeedDeleteDialog.getDialogTitle().getAttribute('id')).to.match(/catinyApp.newsFeed.delete.question/);
    await newsFeedDeleteDialog.clickOnConfirmButton();

    await waitUntilHidden(newsFeedDeleteDialog.deleteModal);

    expect(await isVisible(newsFeedDeleteDialog.deleteModal)).to.be.false;
  }
}
