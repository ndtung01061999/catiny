import { element, by, ElementFinder } from 'protractor';
import { waitUntilDisplayed, waitUntilHidden, isVisible } from '../../util/utils';

const expect = chai.expect;

export default class VideoStreamUpdatePage {
  pageTitle: ElementFinder = element(by.id('catinyApp.videoStream.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  uuidInput: ElementFinder = element(by.css('input#video-stream-uuid'));
  videoSelect: ElementFinder = element(by.css('select#video-stream-video'));
  baseInfoSelect: ElementFinder = element(by.css('select#video-stream-baseInfo'));

  getPageTitle() {
    return this.pageTitle;
  }

  async setUuidInput(uuid) {
    await this.uuidInput.sendKeys(uuid);
  }

  async getUuidInput() {
    return this.uuidInput.getAttribute('value');
  }

  async videoSelectLastOption() {
    await this.videoSelect.all(by.tagName('option')).last().click();
  }

  async videoSelectOption(option) {
    await this.videoSelect.sendKeys(option);
  }

  getVideoSelect() {
    return this.videoSelect;
  }

  async getVideoSelectedOption() {
    return this.videoSelect.element(by.css('option:checked')).getText();
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
    await this.videoSelectLastOption();
    await this.baseInfoSelectLastOption();
    await this.save();
    await waitUntilHidden(this.saveButton);
  }
}
