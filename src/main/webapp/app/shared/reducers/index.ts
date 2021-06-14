import {combineReducers} from 'redux';
import {loadingBarReducer as loadingBar} from 'react-redux-loading-bar';

import locale, {LocaleState} from './locale';
import authentication, {AuthenticationState} from './authentication';
import applicationProfile, {ApplicationProfileState} from './application-profile';

import administration, {AdministrationState} from 'app/modules/administration/administration.reducer';
import userManagement, {UserManagementState} from 'app/modules/administration/user-management/user-management.reducer';
import register, {RegisterState} from 'app/modules/account/register/register.reducer';
import activate, {ActivateState} from 'app/modules/account/activate/activate.reducer';
import password, {PasswordState} from 'app/modules/account/password/password.reducer';
import settings, {SettingsState} from 'app/modules/account/settings/settings.reducer';
import passwordReset, {PasswordResetState} from 'app/modules/account/password-reset/password-reset.reducer';
// prettier-ignore
import messageContent, {MessageContentState} from 'app/entities/message-content/message-content.reducer';
// prettier-ignore
import messageGroup, {MessageGroupState} from 'app/entities/message-group/message-group.reducer';
/* jhipster-needle-add-reducer-import - JHipster will add reducer here */
// prettier-ignore
import messageContentComponent, {MessageContentComponentState} from 'app/component/chat/message/message-content/message-content.reducer';
import messageGroupComponent, {MessageGroupComponentState} from 'app/component/chat/message/message-group/message-group.reducer';

export interface IRootState
{
  readonly authentication: AuthenticationState;
  readonly locale: LocaleState;
  readonly applicationProfile: ApplicationProfileState;
  readonly administration: AdministrationState;
  readonly userManagement: UserManagementState;
  readonly register: RegisterState;
  readonly activate: ActivateState;
  readonly passwordReset: PasswordResetState;
  readonly password: PasswordState;
  readonly settings: SettingsState;
  readonly messageGroup: MessageGroupState;
  readonly messageContent: MessageContentState;
  /* jhipster-needle-add-reducer-type - JHipster will add reducer type here */
  readonly messageContentComponent: MessageContentComponentState;
  readonly messageGroupComponent: MessageGroupComponentState;
  readonly loadingBar: any;
}

const rootReducer = combineReducers<IRootState>({
  authentication,
  locale,
  applicationProfile,
  administration,
  userManagement,
  register,
  activate,
  passwordReset,
  password,
  settings,
  messageGroup,
  messageContent,
  /* jhipster-needle-add-reducer-combine - JHipster will add reducer here */
  messageContentComponent,
  messageGroupComponent,
  loadingBar,
});

export default rootReducer;
