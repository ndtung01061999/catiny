import { element, by, ElementFinder } from 'protractor';
import { waitUntilDisplayed, waitUntilHidden, isVisible } from '../../util/utils';

const expect = chai.expect;

export default class TopicInterestUpdatePage {
  pageTitle: ElementFinder = element(by.id('catinyApp.topicInterest.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  uuidInput: ElementFinder = element(by.css('input#topic-interest-uuid'));
  titleInput: ElementFinder = element(by.css('input#topic-interest-title'));
  contentInput: ElementFinder = element(by.css('textarea#topic-interest-content'));
  baseInfoSelect: ElementFinder = element(by.css('select#topic-interest-baseInfo'));
  postSelect: ElementFinder = element(by.css('select#topic-interest-post'));
  pagePostSelect: ElementFinder = element(by.css('select#topic-interest-pagePost'));
  groupPostSelect: ElementFinder = element(by.css('select#topic-interest-groupPost'));

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

  async setContentInput(content) {
    await this.contentInput.sendKeys(content);
  }

  async getContentInput() {
    return this.contentInput.getAttribute('value');
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

  async postSelectLastOption() {
    await this.postSelect.all(by.tagName('option')).last().click();
  }

  async postSelectOption(option) {
    await this.postSelect.sendKeys(option);
  }

  getPostSelect() {
    return this.postSelect;
  }

  async getPostSelectedOption() {
    return this.postSelect.element(by.css('option:checked')).getText();
  }

  async pagePostSelectLastOption() {
    await this.pagePostSelect.all(by.tagName('option')).last().click();
  }

  async pagePostSelectOption(option) {
    await this.pagePostSelect.sendKeys(option);
  }

  getPagePostSelect() {
    return this.pagePostSelect;
  }

  async getPagePostSelectedOption() {
    return this.pagePostSelect.element(by.css('option:checked')).getText();
  }

  async groupPostSelectLastOption() {
    await this.groupPostSelect.all(by.tagName('option')).last().click();
  }

  async groupPostSelectOption(option) {
    await this.groupPostSelect.sendKeys(option);
  }

  getGroupPostSelect() {
    return this.groupPostSelect;
  }

  async getGroupPostSelectedOption() {
    return this.groupPostSelect.element(by.css('option:checked')).getText();
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
    await this.setContentInput('content');
    await this.baseInfoSelectLastOption();
    // this.postSelectLastOption();
    // this.pagePostSelectLastOption();
    // this.groupPostSelectLastOption();
    await this.save();
    await waitUntilHidden(this.saveButton);
  }
}
