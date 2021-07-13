import { element, by, ElementFinder } from 'protractor';
import { waitUntilDisplayed, waitUntilHidden, isVisible } from '../../util/utils';

import path from 'path';

const expect = chai.expect;

const fileToUpload = '../../../../../../src/main/webapp/content/images/logo-jhipster.png';
const absolutePath = path.resolve(__dirname, fileToUpload);
export default class VideoLiveStreamBufferUpdatePage {
  pageTitle: ElementFinder = element(by.id('catinyApp.videoLiveStreamBuffer.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  uuidInput: ElementFinder = element(by.css('input#video-live-stream-buffer-uuid'));
  bufferDataInput: ElementFinder = element(by.css('input#video-live-stream-buffer-bufferData'));
  baseInfoSelect: ElementFinder = element(by.css('select#video-live-stream-buffer-baseInfo'));
  videoStreamSelect: ElementFinder = element(by.css('select#video-live-stream-buffer-videoStream'));

  getPageTitle() {
    return this.pageTitle;
  }

  async setUuidInput(uuid) {
    await this.uuidInput.sendKeys(uuid);
  }

  async getUuidInput() {
    return this.uuidInput.getAttribute('value');
  }

  async setBufferDataInput(bufferData) {
    await this.bufferDataInput.sendKeys(bufferData);
  }

  async getBufferDataInput() {
    return this.bufferDataInput.getAttribute('value');
  }

  async baseInfoSelectLastOption() {
    await this.baseInfoSelect.all(by.tagName('option')).last().click();
  }

  async baseInfoSelectOption(option) {
    await this.baseInfoSelect.sendKeys(option);
  }

  getBaseInfoSelect() {
    return this.baseInfoSelect;
  }

  async getBaseInfoSelectedOption() {
    return this.baseInfoSelect.element(by.css('option:checked')).getText();
  }

  async videoStreamSelectLastOption() {
    await this.videoStreamSelect.all(by.tagName('option')).last().click();
  }

  async videoStreamSelectOption(option) {
    await this.videoStreamSelect.sendKeys(option);
  }

  getVideoStreamSelect() {
    return this.videoStreamSelect;
  }

  async getVideoStreamSelectedOption() {
    return this.videoStreamSelect.element(by.css('option:checked')).getText();
  }

  async save() {
    await this.saveButton.click();
  }

  async cancel() {
    await this.cancelButton.click();
  }

  getSaveButton() {
    return this.saveButton;
  }

  async enterData() {
    await waitUntilDisplayed(this.saveButton);
    await this.setUuidInput('64c99148-3908-465d-8c4a-e510e3ade974');
    await waitUntilDisplayed(this.saveButton);
    await this.setBufferDataInput(absolutePath);
    await this.baseInfoSelectLastOption();
    await this.videoStreamSelectLastOption();
    await this.save();
    await waitUntilHidden(this.saveButton);
  }
}
