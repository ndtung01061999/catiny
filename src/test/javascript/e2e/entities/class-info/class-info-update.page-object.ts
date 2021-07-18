import { element, by, ElementFinder } from 'protractor';
import { waitUntilDisplayed, waitUntilHidden, isVisible } from '../../util/utils';

const expect = chai.expect;

export default class ClassInfoUpdatePage {
  pageTitle: ElementFinder = element(by.id('catinyApp.classInfo.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  uuidInput: ElementFinder = element(by.css('input#class-info-uuid'));
  packageNameInput: ElementFinder = element(by.css('input#class-info-packageName'));
  fullNameInput: ElementFinder = element(by.css('input#class-info-fullName'));
  classNameInput: ElementFinder = element(by.css('input#class-info-className'));

  getPageTitle() {
    return this.pageTitle;
  }

  async setUuidInput(uuid) {
    await this.uuidInput.sendKeys(uuid);
  }

  async getUuidInput() {
    return this.uuidInput.getAttribute('value');
  }

  async setPackageNameInput(packageName) {
    await this.packageNameInput.sendKeys(packageName);
  }

  async getPackageNameInput() {
    return this.packageNameInput.getAttribute('value');
  }

  async setFullNameInput(fullName) {
    await this.fullNameInput.sendKeys(fullName);
  }

  async getFullNameInput() {
    return this.fullNameInput.getAttribute('value');
  }

  async setClassNameInput(className) {
    await this.classNameInput.sendKeys(className);
  }

  async getClassNameInput() {
    return this.classNameInput.getAttribute('value');
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
    await this.setPackageNameInput('packageName');
    await waitUntilDisplayed(this.saveButton);
    await this.setFullNameInput('fullName');
    await waitUntilDisplayed(this.saveButton);
    await this.setClassNameInput('className');
    await this.save();
    await waitUntilHidden(this.saveButton);
  }
}
