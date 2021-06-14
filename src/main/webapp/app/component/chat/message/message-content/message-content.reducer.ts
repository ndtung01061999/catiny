import axios from 'axios';
import {parseHeaderForLinks,} from 'react-jhipster';

import {FAILURE, REQUEST, SUCCESS, WS_MESSAGE} from 'app/shared/reducers/action-type.util';

import {IMessageContent} from 'app/shared/model/message-content.model';

export const ACTION_TYPES = {
  FETCH_ALL_GROUPS_JOINED_LIST: 'messages/FETCH_ALL_GROUPS_JOINED_LIST',
  FETCH_CONTENT_IN_GROUP_LIST: 'messages/FETCH_CONTENT_IN_GROUP_LIST',
  MESSAGE_USER_NEW_MESSAGE: "message/MESSAGE_USER_NEW_MESSAGE"
};

const initialState = {
  messageContentList: [] as ReadonlyArray<IMessageContent>,
  groupIdCurrent: "",
};

export type MessageContentComponentState = Readonly<typeof initialState>;

// Reducer

export default (state: MessageContentComponentState = initialState, action): MessageContentComponentState =>
{
  switch (action.type)
  {
    case REQUEST(ACTION_TYPES.FETCH_CONTENT_IN_GROUP_LIST):
      return state;
    case FAILURE(ACTION_TYPES.FETCH_CONTENT_IN_GROUP_LIST):
      return state;
    case SUCCESS(ACTION_TYPES.FETCH_CONTENT_IN_GROUP_LIST):
    {
      const data = action.payload.data;
      const links = parseHeaderForLinks(action.payload.headers.link);
      const messageContentListCurrent = data.length > 0 && state.groupIdCurrent === action.payload.groupId
        ? state.messageContentList : [];
      return {
        ...state,
        groupIdCurrent: action.payload.groupId,
        messageContentList: [...data, ...messageContentListCurrent],
      };
    }
    case WS_MESSAGE(ACTION_TYPES.MESSAGE_USER_NEW_MESSAGE):
    {
      const data = JSON.parse(action.payload.body);
      const messageContentListCurrent = data && state.groupIdCurrent === data.groupId
        ? state.messageContentList : [];
      return {
        ...state,
        messageContentList: [...messageContentListCurrent, data]
      };
    }
    default:
      return state;
  }
};

const apiUrl = 'api/o/message-groups';
const apiUrlMessageContent = 'api/o/message-contents';
const apiSearchUrl = 'api/o/_search/message-groups';

// Actions

export const getContentInGroup = (groupId: string, page: number, size: number) =>
{
  const queryParams = `?${page != null && size != null ? `page=${page}&size=${size}` : ''}`
  const requestUrl = `${apiUrlMessageContent}/message-groups/${groupId}${queryParams}`;
  return {
    type: ACTION_TYPES.FETCH_CONTENT_IN_GROUP_LIST,
    payload: axios.get(`${requestUrl}`).then(data =>
    {
      return {...data, groupId};
    }),
  };
};

export const messageUserNewMessage = data =>
{
  return {
    type: WS_MESSAGE(ACTION_TYPES.MESSAGE_USER_NEW_MESSAGE),
    payload: data
  }
}
