import { element, by, ElementFinder, protractor } from 'protractor';
import { waitUntilDisplayed, waitUntilHidden, isVisible } from '../../util/utils';

const expect = chai.expect;

export default class BaseInfoUpdatePage {
  pageTitle: ElementFinder = element(by.id('catinyApp.baseInfo.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  processStatusSelect: ElementFinder = element(by.css('select#base-info-processStatus'));
  modifiedClassInput: ElementFinder = element(by.css('input#base-info-modifiedClass'));
  createdDateInput: ElementFinder = element(by.css('input#base-info-createdDate'));
  modifiedDateInput: ElementFinder = element(by.css('input#base-info-modifiedDate'));
  notesInput: ElementFinder = element(by.css('textarea#base-info-notes'));
  historyUpdateInput: ElementFinder = element(by.css('textarea#base-info-historyUpdate'));
  deletedInput: ElementFinder = element(by.css('input#base-info-deleted'));
  priorityIndexInput: ElementFinder = element(by.css('input#base-info-priorityIndex'));
  countUseInput: ElementFinder = element(by.css('input#base-info-countUse'));
  classInfoSelect: ElementFinder = element(by.css('select#base-info-classInfo'));
  createdBySelect: ElementFinder = element(by.css('select#base-info-createdBy'));
  modifiedBySelect: ElementFinder = element(by.css('select#base-info-modifiedBy'));
  ownerSelect: ElementFinder = element(by.css('select#base-info-owner'));

  getPageTitle() {
    return this.pageTitle;
  }

  async setProcessStatusSelect(processStatus) {
    await this.processStatusSelect.sendKeys(processStatus);
  }

  async getProcessStatusSelect() {
    return this.processStatusSelect.element(by.css('option:checked')).getText();
  }

  async processStatusSelectLastOption() {
    await this.processStatusSelect.all(by.tagName('option')).last().click();
  }
  async setModifiedClassInput(modifiedClass) {
    await this.modifiedClassInput.sendKeys(modifiedClass);
  }

  async getModifiedClassInput() {
    return this.modifiedClassInput.getAttribute('value');
  }

  async setCreatedDateInput(createdDate) {
    await this.createdDateInput.sendKeys(createdDate);
  }

  async getCreatedDateInput() {
    return this.createdDateInput.getAttribute('value');
  }

  async setModifiedDateInput(modifiedDate) {
    await this.modifiedDateInput.sendKeys(modifiedDate);
  }

  async getModifiedDateInput() {
    return this.modifiedDateInput.getAttribute('value');
  }

  async setNotesInput(notes) {
    await this.notesInput.sendKeys(notes);
  }

  async getNotesInput() {
    return this.notesInput.getAttribute('value');
  }

  async setHistoryUpdateInput(historyUpdate) {
    await this.historyUpdateInput.sendKeys(historyUpdate);
  }

  async getHistoryUpdateInput() {
    return this.historyUpdateInput.getAttribute('value');
  }

  getDeletedInput() {
    return this.deletedInput;
  }
  async setPriorityIndexInput(priorityIndex) {
    await this.priorityIndexInput.sendKeys(priorityIndex);
  }

  async getPriorityIndexInput() {
    return this.priorityIndexInput.getAttribute('value');
  }

  async setCountUseInput(countUse) {
    await this.countUseInput.sendKeys(countUse);
  }

  async getCountUseInput() {
    return this.countUseInput.getAttribute('value');
  }

  async classInfoSelectLastOption() {
    await this.classInfoSelect.all(by.tagName('option')).last().click();
  }

  async classInfoSelectOption(option) {
    await this.classInfoSelect.sendKeys(option);
  }

  getClassInfoSelect() {
    return this.classInfoSelect;
  }

  async getClassInfoSelectedOption() {
    return this.classInfoSelect.element(by.css('option:checked')).getText();
  }

  async createdBySelectLastOption() {
    await this.createdBySelect.all(by.tagName('option')).last().click();
  }

  async createdBySelectOption(option) {
    await this.createdBySelect.sendKeys(option);
  }

  getCreatedBySelect() {
    return this.createdBySelect;
  }

  async getCreatedBySelectedOption() {
    return this.createdBySelect.element(by.css('option:checked')).getText();
  }

  async modifiedBySelectLastOption() {
    await this.modifiedBySelect.all(by.tagName('option')).last().click();
  }

  async modifiedBySelectOption(option) {
    await this.modifiedBySelect.sendKeys(option);
  }

  getModifiedBySelect() {
    return this.modifiedBySelect;
  }

  async getModifiedBySelectedOption() {
    return this.modifiedBySelect.element(by.css('option:checked')).getText();
  }

  async ownerSelectLastOption() {
    await this.ownerSelect.all(by.tagName('option')).last().click();
  }

  async ownerSelectOption(option) {
    await this.ownerSelect.sendKeys(option);
  }

  getOwnerSelect() {
    return this.ownerSelect;
  }

  async getOwnerSelectedOption() {
    return this.ownerSelect.element(by.css('option:checked')).getText();
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
    await this.processStatusSelectLastOption();
    await waitUntilDisplayed(this.saveButton);
    await this.setModifiedClassInput('modifiedClass');
    await waitUntilDisplayed(this.saveButton);
    await this.setCreatedDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM');
    await waitUntilDisplayed(this.saveButton);
    await this.setModifiedDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM');
    await waitUntilDisplayed(this.saveButton);
    await this.setNotesInput('notes');
    await waitUntilDisplayed(this.saveButton);
    await this.setHistoryUpdateInput('historyUpdate');
    await waitUntilDisplayed(this.saveButton);
    const selectedDeleted = await this.getDeletedInput().isSelected();
    if (selectedDeleted) {
      await this.getDeletedInput().click();
    } else {
      await this.getDeletedInput().click();
    }
    await waitUntilDisplayed(this.saveButton);
    await this.setPriorityIndexInput('5');
    await waitUntilDisplayed(this.saveButton);
    await this.setCountUseInput('5');
    await this.classInfoSelectLastOption();
    await this.createdBySelectLastOption();
    await this.modifiedBySelectLastOption();
    await this.ownerSelectLastOption();
    await this.save();
    await waitUntilHidden(this.saveButton);
  }
}
