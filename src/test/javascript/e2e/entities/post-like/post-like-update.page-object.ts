import { element, by, ElementFinder } from 'protractor';
import { waitUntilDisplayed, waitUntilHidden, isVisible } from '../../util/utils';

const expect = chai.expect;

export default class PostLikeUpdatePage {
  pageTitle: ElementFinder = element(by.id('catinyApp.postLike.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  uuidInput: ElementFinder = element(by.css('input#post-like-uuid'));
  baseInfoSelect: ElementFinder = element(by.css('select#post-like-baseInfo'));
  postSelect: ElementFinder = element(by.css('select#post-like-post'));
  postCommentSelect: ElementFinder = element(by.css('select#post-like-postComment'));

  getPageTitle() {
    return this.pageTitle;
  }

  async setUuidInput(uuid) {
    await this.uuidInput.sendKeys(uuid);
  }

  async getUuidInput() {
    return this.uuidInput.getAttribute('value');
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

  async postCommentSelectLastOption() {
    await this.postCommentSelect.all(by.tagName('option')).last().click();
  }

  async postCommentSelectOption(option) {
    await this.postCommentSelect.sendKeys(option);
  }

  getPostCommentSelect() {
    return this.postCommentSelect;
  }

  async getPostCommentSelectedOption() {
    return this.postCommentSelect.element(by.css('option:checked')).getText();
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
    await this.baseInfoSelectLastOption();
    await this.postSelectLastOption();
    await this.postCommentSelectLastOption();
    await this.save();
    await waitUntilHidden(this.saveButton);
  }
}
