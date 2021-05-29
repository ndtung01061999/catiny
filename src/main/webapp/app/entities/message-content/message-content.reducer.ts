import axios from 'axios';
import {
  ICrudSearchAction,
  parseHeaderForLinks,
  loadMoreDataWhenScrolled,
  ICrudGetAction,
  ICrudGetAllAction,
  ICrudPutAction,
  ICrudDeleteAction,
} from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IMessageContent, defaultValue } from 'app/shared/model/message-content.model';

export const ACTION_TYPES = {
  SEARCH_MESSAGECONTENTS: 'messageContent/SEARCH_MESSAGECONTENTS',
  FETCH_MESSAGECONTENT_LIST: 'messageContent/FETCH_MESSAGECONTENT_LIST',
  FETCH_MESSAGECONTENT: 'messageContent/FETCH_MESSAGECONTENT',
  CREATE_MESSAGECONTENT: 'messageContent/CREATE_MESSAGECONTENT',
  UPDATE_MESSAGECONTENT: 'messageContent/UPDATE_MESSAGECONTENT',
  PARTIAL_UPDATE_MESSAGECONTENT: 'messageContent/PARTIAL_UPDATE_MESSAGECONTENT',
  DELETE_MESSAGECONTENT: 'messageContent/DELETE_MESSAGECONTENT',
  SET_BLOB: 'messageContent/SET_BLOB',
  RESET: 'messageContent/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IMessageContent>,
  entity: defaultValue,
  links: { next: 0 },
  updating: false,
  totalItems: 0,
  updateSuccess: false,
};

export type MessageContentState = Readonly<typeof initialState>;

// Reducer

export default (state: MessageContentState = initialState, action): MessageContentState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.SEARCH_MESSAGECONTENTS):
    case REQUEST(ACTION_TYPES.FETCH_MESSAGECONTENT_LIST):
    case REQUEST(ACTION_TYPES.FETCH_MESSAGECONTENT):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_MESSAGECONTENT):
    case REQUEST(ACTION_TYPES.UPDATE_MESSAGECONTENT):
    case REQUEST(ACTION_TYPES.DELETE_MESSAGECONTENT):
    case REQUEST(ACTION_TYPES.PARTIAL_UPDATE_MESSAGECONTENT):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.SEARCH_MESSAGECONTENTS):
    case FAILURE(ACTION_TYPES.FETCH_MESSAGECONTENT_LIST):
    case FAILURE(ACTION_TYPES.FETCH_MESSAGECONTENT):
    case FAILURE(ACTION_TYPES.CREATE_MESSAGECONTENT):
    case FAILURE(ACTION_TYPES.UPDATE_MESSAGECONTENT):
    case FAILURE(ACTION_TYPES.PARTIAL_UPDATE_MESSAGECONTENT):
    case FAILURE(ACTION_TYPES.DELETE_MESSAGECONTENT):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.SEARCH_MESSAGECONTENTS):
    case SUCCESS(ACTION_TYPES.FETCH_MESSAGECONTENT_LIST): {
      const links = parseHeaderForLinks(action.payload.headers.link);

      return {
        ...state,
        loading: false,
        links,
        entities: loadMoreDataWhenScrolled(state.entities, action.payload.data, links),
        totalItems: parseInt(action.payload.headers['x-total-count'], 10),
      };
    }
    case SUCCESS(ACTION_TYPES.FETCH_MESSAGECONTENT):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_MESSAGECONTENT):
    case SUCCESS(ACTION_TYPES.UPDATE_MESSAGECONTENT):
    case SUCCESS(ACTION_TYPES.PARTIAL_UPDATE_MESSAGECONTENT):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_MESSAGECONTENT):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: {},
      };
    case ACTION_TYPES.SET_BLOB: {
      const { name, data, contentType } = action.payload;
      return {
        ...state,
        entity: {
          ...state.entity,
          [name]: data,
          [name + 'ContentType']: contentType,
        },
      };
    }
    case ACTION_TYPES.RESET:
      return {
        ...initialState,
      };
    default:
      return state;
  }
};

const apiUrl = 'api/message-contents';
const apiSearchUrl = 'api/_search/message-contents';

// Actions

export const getSearchEntities: ICrudSearchAction<IMessageContent> = (query, page, size, sort) => ({
  type: ACTION_TYPES.SEARCH_MESSAGECONTENTS,
  payload: axios.get<IMessageContent>(`${apiSearchUrl}?query=${query}${sort ? `&page=${page}&size=${size}&sort=${sort}` : ''}`),
});

export const getEntities: ICrudGetAllAction<IMessageContent> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_MESSAGECONTENT_LIST,
    payload: axios.get<IMessageContent>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`),
  };
};

export const getEntity: ICrudGetAction<IMessageContent> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_MESSAGECONTENT,
    payload: axios.get<IMessageContent>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<IMessageContent> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_MESSAGECONTENT,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  return result;
};

export const updateEntity: ICrudPutAction<IMessageContent> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_MESSAGECONTENT,
    payload: axios.put(`${apiUrl}/${entity.id}`, cleanEntity(entity)),
  });
  return result;
};

export const partialUpdate: ICrudPutAction<IMessageContent> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.PARTIAL_UPDATE_MESSAGECONTENT,
    payload: axios.patch(`${apiUrl}/${entity.id}`, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<IMessageContent> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_MESSAGECONTENT,
    payload: axios.delete(requestUrl),
  });
  return result;
};

export const setBlob = (name, data, contentType?) => ({
  type: ACTION_TYPES.SET_BLOB,
  payload: {
    name,
    data,
    contentType,
  },
});

export const reset = () => ({
  type: ACTION_TYPES.RESET,
});
