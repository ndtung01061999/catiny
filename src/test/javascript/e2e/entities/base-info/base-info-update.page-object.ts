import { element, by, ElementFinder, protractor } from 'protractor';
import { waitUntilDisplayed, waitUntilHidden, isVisible } from '../../util/utils';

const expect = chai.expect;

export default class BaseInfoUpdatePage {
  pageTitle: ElementFinder = element(by.id('catinyApp.baseInfo.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  processStatusSelect: ElementFinder = element(by.css('select#base-info-processStatus'));
  ownerInput: ElementFinder = element(by.css('textarea#base-info-owner'));
  roleInput: ElementFinder = element(by.css('textarea#base-info-role'));
  modifiedClassInput: ElementFinder = element(by.css('input#base-info-modifiedClass'));
  createdDateInput: ElementFinder = element(by.css('input#base-info-createdDate'));
  modifiedDateInput: ElementFinder = element(by.css('input#base-info-modifiedDate'));
  createdByInput: ElementFinder = element(by.css('input#base-info-createdBy'));
  modifiedByInput: ElementFinder = element(by.css('input#base-info-modifiedBy'));
  notesInput: ElementFinder = element(by.css('textarea#base-info-notes'));
  historyUpdateInput: ElementFinder = element(by.css('textarea#base-info-historyUpdate'));
  deletedInput: ElementFinder = element(by.css('input#base-info-deleted'));

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
  async setOwnerInput(owner) {
    await this.ownerInput.sendKeys(owner);
  }

  async getOwnerInput() {
    return this.ownerInput.getAttribute('value');
  }

  async setRoleInput(role) {
    await this.roleInput.sendKeys(role);
  }

  async getRoleInput() {
    return this.roleInput.getAttribute('value');
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

  async setCreatedByInput(createdBy) {
    await this.createdByInput.sendKeys(createdBy);
  }

  async getCreatedByInput() {
    return this.createdByInput.getAttribute('value');
  }

  async setModifiedByInput(modifiedBy) {
    await this.modifiedByInput.sendKeys(modifiedBy);
  }

  async getModifiedByInput() {
    return this.modifiedByInput.getAttribute('value');
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
    await this.setOwnerInput('owner');
    await waitUntilDisplayed(this.saveButton);
    await this.setRoleInput('role');
    await waitUntilDisplayed(this.saveButton);
    await this.setModifiedClassInput('modifiedClass');
    await waitUntilDisplayed(this.saveButton);
    await this.setCreatedDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM');
    await waitUntilDisplayed(this.saveButton);
    await this.setModifiedDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM');
    await waitUntilDisplayed(this.saveButton);
    await this.setCreatedByInput('createdBy');
    await waitUntilDisplayed(this.saveButton);
    await this.setModifiedByInput('modifiedBy');
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
    await this.save();
    await waitUntilHidden(this.saveButton);
  }
}
