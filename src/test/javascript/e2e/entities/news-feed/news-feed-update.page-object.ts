import { element, by, ElementFinder } from 'protractor';
import { waitUntilDisplayed, waitUntilHidden, isVisible } from '../../util/utils';

const expect = chai.expect;

export default class NewsFeedUpdatePage {
  pageTitle: ElementFinder = element(by.id('catinyApp.newsFeed.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  uuidInput: ElementFinder = element(by.css('input#news-feed-uuid'));
  priorityIndexInput: ElementFinder = element(by.css('input#news-feed-priorityIndex'));
  baseInfoSelect: ElementFinder = element(by.css('select#news-feed-baseInfo'));
  postSelect: ElementFinder = element(by.css('select#news-feed-post'));

  getPageTitle() {
    return this.pageTitle;
  }

  async setUuidInput(uuid) {
    await this.uuidInput.sendKeys(uuid);
  }

  async getUuidInput() {
    return this.uuidInput.getAttribute('value');
  }

  async setPriorityIndexInput(priorityIndex) {
    await this.priorityIndexInput.sendKeys(priorityIndex);
  }

  async getPriorityIndexInput() {
    return this.priorityIndexInput.getAttribute('value');
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
    await this.setPriorityIndexInput('5');
    await this.baseInfoSelectLastOption();
    await this.postSelectLastOption();
    await this.save();
    await waitUntilHidden(this.saveButton);
  }
}
