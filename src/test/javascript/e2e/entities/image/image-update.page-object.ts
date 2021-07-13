import { element, by, ElementFinder } from 'protractor';
import { waitUntilDisplayed, waitUntilHidden, isVisible } from '../../util/utils';

const expect = chai.expect;

export default class ImageUpdatePage {
  pageTitle: ElementFinder = element(by.id('catinyApp.image.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  uuidInput: ElementFinder = element(by.css('input#image-uuid'));
  nameInput: ElementFinder = element(by.css('input#image-name'));
  fileInfoSelect: ElementFinder = element(by.css('select#image-fileInfo'));
  baseInfoSelect: ElementFinder = element(by.css('select#image-baseInfo'));
  imageOriginalSelect: ElementFinder = element(by.css('select#image-imageOriginal'));
  eventSelect: ElementFinder = element(by.css('select#image-event'));

  getPageTitle() {
    return this.pageTitle;
  }

  async setUuidInput(uuid) {
    await this.uuidInput.sendKeys(uuid);
  }

  async getUuidInput() {
    return this.uuidInput.getAttribute('value');
  }

  async setNameInput(name) {
    await this.nameInput.sendKeys(name);
  }

  async getNameInput() {
    return this.nameInput.getAttribute('value');
  }

  async fileInfoSelectLastOption() {
    await this.fileInfoSelect.all(by.tagName('option')).last().click();
  }

  async fileInfoSelectOption(option) {
    await this.fileInfoSelect.sendKeys(option);
  }

  getFileInfoSelect() {
    return this.fileInfoSelect;
  }

  async getFileInfoSelectedOption() {
    return this.fileInfoSelect.element(by.css('option:checked')).getText();
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

  async imageOriginalSelectLastOption() {
    await this.imageOriginalSelect.all(by.tagName('option')).last().click();
  }

  async imageOriginalSelectOption(option) {
    await this.imageOriginalSelect.sendKeys(option);
  }

  getImageOriginalSelect() {
    return this.imageOriginalSelect;
  }

  async getImageOriginalSelectedOption() {
    return this.imageOriginalSelect.element(by.css('option:checked')).getText();
  }

  async eventSelectLastOption() {
    await this.eventSelect.all(by.tagName('option')).last().click();
  }

  async eventSelectOption(option) {
    await this.eventSelect.sendKeys(option);
  }

  getEventSelect() {
    return this.eventSelect;
  }

  async getEventSelectedOption() {
    return this.eventSelect.element(by.css('option:checked')).getText();
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
    await this.setNameInput('name');
    await this.fileInfoSelectLastOption();
    await this.baseInfoSelectLastOption();
    await this.imageOriginalSelectLastOption();
    await this.eventSelectLastOption();
    await this.save();
    await waitUntilHidden(this.saveButton);
  }
}
