import { element, by, ElementFinder } from 'protractor';
import { waitUntilDisplayed, waitUntilHidden, isVisible } from '../../util/utils';

const expect = chai.expect;

export default class PostUpdatePage {
  pageTitle: ElementFinder = element(by.id('catinyApp.post.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  uuidInput: ElementFinder = element(by.css('input#post-uuid'));
  postInTypeSelect: ElementFinder = element(by.css('select#post-postInType'));
  postTypeSelect: ElementFinder = element(by.css('select#post-postType'));
  contentInput: ElementFinder = element(by.css('textarea#post-content'));
  searchFieldInput: ElementFinder = element(by.css('textarea#post-searchField'));
  baseInfoSelect: ElementFinder = element(by.css('select#post-baseInfo'));
  groupPostSelect: ElementFinder = element(by.css('select#post-groupPost'));
  pagePostSelect: ElementFinder = element(by.css('select#post-pagePost'));
  postShareParentSelect: ElementFinder = element(by.css('select#post-postShareParent'));

  getPageTitle() {
    return this.pageTitle;
  }

  async setUuidInput(uuid) {
    await this.uuidInput.sendKeys(uuid);
  }

  async getUuidInput() {
    return this.uuidInput.getAttribute('value');
  }

  async setPostInTypeSelect(postInType) {
    await this.postInTypeSelect.sendKeys(postInType);
  }

  async getPostInTypeSelect() {
    return this.postInTypeSelect.element(by.css('option:checked')).getText();
  }

  async postInTypeSelectLastOption() {
    await this.postInTypeSelect.all(by.tagName('option')).last().click();
  }
  async setPostTypeSelect(postType) {
    await this.postTypeSelect.sendKeys(postType);
  }

  async getPostTypeSelect() {
    return this.postTypeSelect.element(by.css('option:checked')).getText();
  }

  async postTypeSelectLastOption() {
    await this.postTypeSelect.all(by.tagName('option')).last().click();
  }
  async setContentInput(content) {
    await this.contentInput.sendKeys(content);
  }

  async getContentInput() {
    return this.contentInput.getAttribute('value');
  }

  async setSearchFieldInput(searchField) {
    await this.searchFieldInput.sendKeys(searchField);
  }

  async getSearchFieldInput() {
    return this.searchFieldInput.getAttribute('value');
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

  async postShareParentSelectLastOption() {
    await this.postShareParentSelect.all(by.tagName('option')).last().click();
  }

  async postShareParentSelectOption(option) {
    await this.postShareParentSelect.sendKeys(option);
  }

  getPostShareParentSelect() {
    return this.postShareParentSelect;
  }

  async getPostShareParentSelectedOption() {
    return this.postShareParentSelect.element(by.css('option:checked')).getText();
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
    await this.postInTypeSelectLastOption();
    await waitUntilDisplayed(this.saveButton);
    await this.postTypeSelectLastOption();
    await waitUntilDisplayed(this.saveButton);
    await this.setContentInput('content');
    await waitUntilDisplayed(this.saveButton);
    await this.setSearchFieldInput('searchField');
    await this.baseInfoSelectLastOption();
    await this.groupPostSelectLastOption();
    await this.pagePostSelectLastOption();
    await this.postShareParentSelectLastOption();
    await this.save();
    await waitUntilHidden(this.saveButton);
  }
}
