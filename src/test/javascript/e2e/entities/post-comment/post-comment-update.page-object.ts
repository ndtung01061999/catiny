import { element, by, ElementFinder } from 'protractor';
import { waitUntilDisplayed, waitUntilHidden, isVisible } from '../../util/utils';

const expect = chai.expect;

export default class PostCommentUpdatePage {
  pageTitle: ElementFinder = element(by.id('catinyApp.postComment.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  uuidInput: ElementFinder = element(by.css('input#post-comment-uuid'));
  contentInput: ElementFinder = element(by.css('textarea#post-comment-content'));
  baseInfoSelect: ElementFinder = element(by.css('select#post-comment-baseInfo'));
  userCommentSelect: ElementFinder = element(by.css('select#post-comment-userComment'));
  postSelect: ElementFinder = element(by.css('select#post-comment-post'));
  commentParentSelect: ElementFinder = element(by.css('select#post-comment-commentParent'));

  getPageTitle() {
    return this.pageTitle;
  }

  async setUuidInput(uuid) {
    await this.uuidInput.sendKeys(uuid);
  }

  async getUuidInput() {
    return this.uuidInput.getAttribute('value');
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

  async userCommentSelectLastOption() {
    await this.userCommentSelect.all(by.tagName('option')).last().click();
  }

  async userCommentSelectOption(option) {
    await this.userCommentSelect.sendKeys(option);
  }

  getUserCommentSelect() {
    return this.userCommentSelect;
  }

  async getUserCommentSelectedOption() {
    return this.userCommentSelect.element(by.css('option:checked')).getText();
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

  async commentParentSelectLastOption() {
    await this.commentParentSelect.all(by.tagName('option')).last().click();
  }

  async commentParentSelectOption(option) {
    await this.commentParentSelect.sendKeys(option);
  }

  getCommentParentSelect() {
    return this.commentParentSelect;
  }

  async getCommentParentSelectedOption() {
    return this.commentParentSelect.element(by.css('option:checked')).getText();
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
    await this.setContentInput('content');
    await this.baseInfoSelectLastOption();
    await this.userCommentSelectLastOption();
    await this.postSelectLastOption();
    await this.commentParentSelectLastOption();
    await this.save();
    await waitUntilHidden(this.saveButton);
  }
}
