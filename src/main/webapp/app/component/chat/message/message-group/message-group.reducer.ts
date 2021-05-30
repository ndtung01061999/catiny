import axios from 'axios';
import {ICrudGetAllAction, loadMoreDataWhenScrolled, parseHeaderForLinks,} from 'react-jhipster';
import {FAILURE, REQUEST, SUCCESS} from 'app/shared/reducers/action-type.util';

import {IMessageGroup} from 'app/shared/model/message-group.model';

export const ACTION_TYPES = {
  FETCH_ALLGROUPSJOINEDLIST: 'messageGroup/FETCH_ALL_GROUPS_JOINED_LIST',
  SET_GROUP_ID_CURRENT: 'messageGroup/SET_GROUP_ID_CURRENT',
};

const initialState = {
  messageGroupList: [] as ReadonlyArray<IMessageGroup>,
  groupIdCurrent: null,
};

export type MessageGroupComponentState = Readonly<typeof initialState>;

// Reducer

export default (state: MessageGroupComponentState = initialState, action): MessageGroupComponentState =>
{

  switch (action.type)
  {
    case REQUEST(ACTION_TYPES.FETCH_ALLGROUPSJOINEDLIST):
      return state;
    case FAILURE(ACTION_TYPES.FETCH_ALLGROUPSJOINEDLIST):
      return state;
    case SUCCESS(ACTION_TYPES.FETCH_ALLGROUPSJOINEDLIST):
    {
      const links = parseHeaderForLinks(action.payload.headers.link);
      return {
        ...state,
        messageGroupList: loadMoreDataWhenScrolled(state.messageGroupList, action.payload.data, links),
      };
    }
    case ACTION_TYPES.SET_GROUP_ID_CURRENT:
      return {
        ...state,
        groupIdCurrent: (action.payload.groupId)
      }
    default:
      return state;
  }
};

const apiUrl = 'api/message-groups';
const apiSearchUrl = 'api/_search/message-groups';

// Actions

export const getAllGroupsJoined: ICrudGetAllAction<IMessageGroup> = (page, size, sort) =>
{
  const requestUrl = `${apiUrl}/joined`;
  return {
    type: ACTION_TYPES.FETCH_ALLGROUPSJOINEDLIST,
    payload: axios.get(`${requestUrl}?${sort ? `page=${page}&size=${size}` : ''}`),
  };
};

export const setGroupIdCurrent = (groupId: string) =>
{
  return {
    type: ACTION_TYPES.SET_GROUP_ID_CURRENT,
    payload: {groupId},
  }
};

const log = xx => window.console.log(xx);