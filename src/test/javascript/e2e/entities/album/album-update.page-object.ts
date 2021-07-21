import { element, by, ElementFinder } from 'protractor';
import { waitUntilDisplayed, waitUntilHidden, isVisible } from '../../util/utils';

const expect = chai.expect;

export default class AlbumUpdatePage {
  pageTitle: ElementFinder = element(by.id('catinyApp.album.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  uuidInput: ElementFinder = element(by.css('input#album-uuid'));
  nameInput: ElementFinder = element(by.css('input#album-name'));
  noteInput: ElementFinder = element(by.css('input#album-note'));
  avatarInput: ElementFinder = element(by.css('textarea#album-avatar'));
  baseInfoSelect: ElementFinder = element(by.css('select#album-baseInfo'));
  imageSelect: ElementFinder = element(by.css('select#album-image'));

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

  async setNoteInput(note) {
    await this.noteInput.sendKeys(note);
  }

  async getNoteInput() {
    return this.noteInput.getAttribute('value');
  }

  async setAvatarInput(avatar) {
    await this.avatarInput.sendKeys(avatar);
  }

  async getAvatarInput() {
    return this.avatarInput.getAttribute('value');
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

  async imageSelectLastOption() {
    await this.imageSelect.all(by.tagName('option')).last().click();
  }

  async imageSelectOption(option) {
    await this.imageSelect.sendKeys(option);
  }

  getImageSelect() {
    return this.imageSelect;
  }

  async getImageSelectedOption() {
    return this.imageSelect.element(by.css('option:checked')).getText();
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
    await waitUntilDisplayed(this.saveButton);
    await this.setNoteInput('note');
    await waitUntilDisplayed(this.saveButton);
    await this.setAvatarInput('avatar');
    await this.baseInfoSelectLastOption();
    // this.imageSelectLastOption();
    await this.save();
    await waitUntilHidden(this.saveButton);
  }
}
