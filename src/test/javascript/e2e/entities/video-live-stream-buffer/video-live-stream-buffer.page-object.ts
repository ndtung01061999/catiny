import { element, by, ElementFinder, ElementArrayFinder } from 'protractor';

import { waitUntilAnyDisplayed, waitUntilDisplayed, click, waitUntilHidden, isVisible } from '../../util/utils';

import NavBarPage from './../../page-objects/navbar-page';

import VideoLiveStreamBufferUpdatePage from './video-live-stream-buffer-update.page-object';

const expect = chai.expect;
export class VideoLiveStreamBufferDeleteDialog {
  deleteModal = element(by.className('modal'));
  private dialogTitle: ElementFinder = element(by.id('catinyApp.videoLiveStreamBuffer.delete.question'));
  private confirmButton = element(by.id('jhi-confirm-delete-videoLiveStreamBuffer'));

  getDialogTitle() {
    return this.dialogTitle;
  }

  async clickOnConfirmButton() {
    await this.confirmButton.click();
  }
}

export default class VideoLiveStreamBufferComponentsPage {
  createButton: ElementFinder = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('div table .btn-danger'));
  title: ElementFinder = element(by.id('video-live-stream-buffer-heading'));
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
    await navBarPage.getEntityPage('video-live-stream-buffer');
    await waitUntilAnyDisplayed([this.noRecords, this.table]);
    return this;
  }

  async goToCreateVideoLiveStreamBuffer() {
    await this.createButton.click();
    return new VideoLiveStreamBufferUpdatePage();
  }

  async deleteVideoLiveStreamBuffer() {
    const deleteButton = this.getDeleteButton(this.records.last());
    await click(deleteButton);

    const videoLiveStreamBufferDeleteDialog = new VideoLiveStreamBufferDeleteDialog();
    await waitUntilDisplayed(videoLiveStreamBufferDeleteDialog.deleteModal);
    expect(await videoLiveStreamBufferDeleteDialog.getDialogTitle().getAttribute('id')).to.match(
      /catinyApp.videoLiveStreamBuffer.delete.question/
    );
    await videoLiveStreamBufferDeleteDialog.clickOnConfirmButton();

    await waitUntilHidden(videoLiveStreamBufferDeleteDialog.deleteModal);

    expect(await isVisible(videoLiveStreamBufferDeleteDialog.deleteModal)).to.be.false;
  }
}
