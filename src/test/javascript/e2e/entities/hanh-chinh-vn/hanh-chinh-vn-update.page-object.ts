import { element, by, ElementFinder } from 'protractor';
import { waitUntilDisplayed, waitUntilHidden, isVisible } from '../../util/utils';

const expect = chai.expect;

export default class HanhChinhVNUpdatePage {
  pageTitle: ElementFinder = element(by.id('catinyApp.hanhChinhVN.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  nameInput: ElementFinder = element(by.css('input#hanh-chinh-vn-name'));
  slugInput: ElementFinder = element(by.css('input#hanh-chinh-vn-slug'));
  typeInput: ElementFinder = element(by.css('input#hanh-chinh-vn-type'));
  nameWithTypeInput: ElementFinder = element(by.css('input#hanh-chinh-vn-nameWithType'));
  codeInput: ElementFinder = element(by.css('input#hanh-chinh-vn-code'));
  parentCodeInput: ElementFinder = element(by.css('input#hanh-chinh-vn-parentCode'));
  pathInput: ElementFinder = element(by.css('input#hanh-chinh-vn-path'));
  pathWithTypeInput: ElementFinder = element(by.css('input#hanh-chinh-vn-pathWithType'));

  getPageTitle() {
    return this.pageTitle;
  }

  async setNameInput(name) {
    await this.nameInput.sendKeys(name);
  }

  async getNameInput() {
    return this.nameInput.getAttribute('value');
  }

  async setSlugInput(slug) {
    await this.slugInput.sendKeys(slug);
  }

  async getSlugInput() {
    return this.slugInput.getAttribute('value');
  }

  async setTypeInput(type) {
    await this.typeInput.sendKeys(type);
  }

  async getTypeInput() {
    return this.typeInput.getAttribute('value');
  }

  async setNameWithTypeInput(nameWithType) {
    await this.nameWithTypeInput.sendKeys(nameWithType);
  }

  async getNameWithTypeInput() {
    return this.nameWithTypeInput.getAttribute('value');
  }

  async setCodeInput(code) {
    await this.codeInput.sendKeys(code);
  }

  async getCodeInput() {
    return this.codeInput.getAttribute('value');
  }

  async setParentCodeInput(parentCode) {
    await this.parentCodeInput.sendKeys(parentCode);
  }

  async getParentCodeInput() {
    return this.parentCodeInput.getAttribute('value');
  }

  async setPathInput(path) {
    await this.pathInput.sendKeys(path);
  }

  async getPathInput() {
    return this.pathInput.getAttribute('value');
  }

  async setPathWithTypeInput(pathWithType) {
    await this.pathWithTypeInput.sendKeys(pathWithType);
  }

  async getPathWithTypeInput() {
    return this.pathWithTypeInput.getAttribute('value');
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
    await this.setNameInput('name');
    await waitUntilDisplayed(this.saveButton);
    await this.setSlugInput('slug');
    await waitUntilDisplayed(this.saveButton);
    await this.setTypeInput('type');
    await waitUntilDisplayed(this.saveButton);
    await this.setNameWithTypeInput('nameWithType');
    await waitUntilDisplayed(this.saveButton);
    await this.setCodeInput('code');
    await waitUntilDisplayed(this.saveButton);
    await this.setParentCodeInput('parentCode');
    await waitUntilDisplayed(this.saveButton);
    await this.setPathInput('path');
    await waitUntilDisplayed(this.saveButton);
    await this.setPathWithTypeInput('pathWithType');
    await this.save();
    await waitUntilHidden(this.saveButton);
  }
}
