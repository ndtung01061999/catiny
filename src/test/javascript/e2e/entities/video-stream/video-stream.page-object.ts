import { element, by, ElementFinder, ElementArrayFinder } from 'protractor';

import { waitUntilAnyDisplayed, waitUntilDisplayed, click, waitUntilHidden, isVisible } from '../../util/utils';

import NavBarPage from './../../page-objects/navbar-page';

import VideoStreamUpdatePage from './video-stream-update.page-object';

const expect = chai.expect;
export class VideoStreamDeleteDialog {
  deleteModal = element(by.className('modal'));
  private dialogTitle: ElementFinder = element(by.id('catinyApp.videoStream.delete.question'));
  private confirmButton = element(by.id('jhi-confirm-delete-videoStream'));

  getDialogTitle() {
    return this.dialogTitle;
  }

  async clickOnConfirmButton() {
    await this.confirmButton.click();
  }
}

export default class VideoStreamComponentsPage {
  createButton: ElementFinder = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('div table .btn-danger'));
  title: ElementFinder = element(by.id('video-stream-heading'));
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
    await navBarPage.getEntityPage('video-stream');
    await waitUntilAnyDisplayed([this.noRecords, this.table]);
    return this;
  }

  async goToCreateVideoStream() {
    await this.createButton.click();
    return new VideoStreamUpdatePage();
  }

  async deleteVideoStream() {
    const deleteButton = this.getDeleteButton(this.records.last());
    await click(deleteButton);

    const videoStreamDeleteDialog = new VideoStreamDeleteDialog();
    await waitUntilDisplayed(videoStreamDeleteDialog.deleteModal);
    expect(await videoStreamDeleteDialog.getDialogTitle().getAttribute('id')).to.match(/catinyApp.videoStream.delete.question/);
    await videoStreamDeleteDialog.clickOnConfirmButton();

    await waitUntilHidden(videoStreamDeleteDialog.deleteModal);

    expect(await isVisible(videoStreamDeleteDialog.deleteModal)).to.be.false;
  }
}
