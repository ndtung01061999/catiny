import { element, by, ElementFinder, protractor } from 'protractor';
import { waitUntilDisplayed, waitUntilHidden, isVisible } from '../../util/utils';

const expect = chai.expect;

export default class EventUpdatePage {
  pageTitle: ElementFinder = element(by.id('catinyApp.event.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  uuidInput: ElementFinder = element(by.css('input#event-uuid'));
  titleInput: ElementFinder = element(by.css('input#event-title'));
  avatarInput: ElementFinder = element(by.css('textarea#event-avatar'));
  contentInput: ElementFinder = element(by.css('textarea#event-content'));
  typeSelect: ElementFinder = element(by.css('select#event-type'));
  descriptionInput: ElementFinder = element(by.css('textarea#event-description'));
  startTimeInput: ElementFinder = element(by.css('input#event-startTime'));
  endTimeInput: ElementFinder = element(by.css('input#event-endTime'));
  tagLineInput: ElementFinder = element(by.css('input#event-tagLine'));
  imageCollectionInput: ElementFinder = element(by.css('textarea#event-imageCollection'));
  videoCollectionInput: ElementFinder = element(by.css('textarea#event-videoCollection'));
  baseInfoSelect: ElementFinder = element(by.css('select#event-baseInfo'));

  getPageTitle() {
    return this.pageTitle;
  }

  async setUuidInput(uuid) {
    await this.uuidInput.sendKeys(uuid);
  }

  async getUuidInput() {
    return this.uuidInput.getAttribute('value');
  }

  async setTitleInput(title) {
    await this.titleInput.sendKeys(title);
  }

  async getTitleInput() {
    return this.titleInput.getAttribute('value');
  }

  async setAvatarInput(avatar) {
    await this.avatarInput.sendKeys(avatar);
  }

  async getAvatarInput() {
    return this.avatarInput.getAttribute('value');
  }

  async setContentInput(content) {
    await this.contentInput.sendKeys(content);
  }

  async getContentInput() {
    return this.contentInput.getAttribute('value');
  }

  async setTypeSelect(type) {
    await this.typeSelect.sendKeys(type);
  }

  async getTypeSelect() {
    return this.typeSelect.element(by.css('option:checked')).getText();
  }

  async typeSelectLastOption() {
    await this.typeSelect.all(by.tagName('option')).last().click();
  }
  async setDescriptionInput(description) {
    await this.descriptionInput.sendKeys(description);
  }

  async getDescriptionInput() {
    return this.descriptionInput.getAttribute('value');
  }

  async setStartTimeInput(startTime) {
    await this.startTimeInput.sendKeys(startTime);
  }

  async getStartTimeInput() {
    return this.startTimeInput.getAttribute('value');
  }

  async setEndTimeInput(endTime) {
    await this.endTimeInput.sendKeys(endTime);
  }

  async getEndTimeInput() {
    return this.endTimeInput.getAttribute('value');
  }

  async setTagLineInput(tagLine) {
    await this.tagLineInput.sendKeys(tagLine);
  }

  async getTagLineInput() {
    return this.tagLineInput.getAttribute('value');
  }

  async setImageCollectionInput(imageCollection) {
    await this.imageCollectionInput.sendKeys(imageCollection);
  }

  async getImageCollectionInput() {
    return this.imageCollectionInput.getAttribute('value');
  }

  async setVideoCollectionInput(videoCollection) {
    await this.videoCollectionInput.sendKeys(videoCollection);
  }

  async getVideoCollectionInput() {
    return this.videoCollectionInput.getAttribute('value');
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
    await waitUntilDisplayed(this.saveButton);
    await this.setTitleInput('title');
    await waitUntilDisplayed(this.saveButton);
    await this.setAvatarInput('avatar');
    await waitUntilDisplayed(this.saveButton);
    await this.setContentInput('content');
    await waitUntilDisplayed(this.saveButton);
    await this.typeSelectLastOption();
    await waitUntilDisplayed(this.saveButton);
    await this.setDescriptionInput('description');
    await waitUntilDisplayed(this.saveButton);
    await this.setStartTimeInput('01/01/2001' + protractor.Key.TAB + '02:30AM');
    await waitUntilDisplayed(this.saveButton);
    await this.setEndTimeInput('01/01/2001' + protractor.Key.TAB + '02:30AM');
    await waitUntilDisplayed(this.saveButton);
    await this.setTagLineInput('tagLine');
    await waitUntilDisplayed(this.saveButton);
    await this.setImageCollectionInput('imageCollection');
    await waitUntilDisplayed(this.saveButton);
    await this.setVideoCollectionInput('videoCollection');
    await this.baseInfoSelectLastOption();
    await this.save();
    await waitUntilHidden(this.saveButton);
  }
}
