// import axios from 'axios';
// import {ICrudDeleteAction, ICrudSearchAction,} from 'react-jhipster';
// import {FAILURE, REQUEST, SUCCESS} from 'app/shared/reducers/action-type.util';
//
// import {IMessageGroup} from 'app/shared/model/message-group.model';
//
// export const ACTION_TYPES = {
//   SEARCH_MESSAGEGROUPS: 'messageGroup/SEARCH_MESSAGEGROUPS',
//   FETCH_MESSAGEGROUP_LIST: 'messageGroup/FETCH_MESSAGEGROUP_LIST',
//   FETCH_MESSAGEGROUP: 'messageGroup/FETCH_MESSAGEGROUP',
//   CREATE_MESSAGEGROUP: 'messageGroup/CREATE_MESSAGEGROUP',
//   UPDATE_MESSAGEGROUP: 'messageGroup/UPDATE_MESSAGEGROUP',
//   PARTIAL_UPDATE_MESSAGEGROUP: 'messageGroup/PARTIAL_UPDATE_MESSAGEGROUP',
//   DELETE_MESSAGEGROUP: 'messageGroup/DELETE_MESSAGEGROUP',
//   SET_BLOB: 'messageGroup/SET_BLOB',
//   RESET: 'messageGroup/RESET',
//
// };
//
// const initialState = {
// };
//
// export type MessageComponentState = Readonly<typeof initialState>;
//
// // Reducer
//
// export default (state: MessageComponentState = initialState, action): MessageComponentState =>
// {
//   switch (action.type)
//   {
//     case REQUEST(ACTION_TYPES.FETCH_MESSAGEGROUP):
//       return {
//         ...state,
//         errorMessage: null,
//         updateSuccess: false,
//         loading: true,
//       };
//     case REQUEST(ACTION_TYPES.PARTIAL_UPDATE_MESSAGEGROUP):
//       return {
//         ...state,
//         errorMessage: null,
//         updateSuccess: false,
//         updating: true,
//       };
//     case FAILURE(ACTION_TYPES.DELETE_MESSAGEGROUP):
//       return {
//         ...state,
//         loading: false,
//         updating: false,
//         updateSuccess: false,
//         errorMessage: action.payload,
//       };
//
//
//     case SUCCESS(ACTION_TYPES.FETCH_MESSAGEGROUP):
//       return {
//         ...state,
//         loading: false,
//         entity: action.payload.data,
//       };
//     case SUCCESS(ACTION_TYPES.DELETE_MESSAGEGROUP):
//       return {
//         ...state,
//         updating: false,
//         updateSuccess: true,
//         entity: {},
//       };
//     case ACTION_TYPES.RESET:
//       return {
//         ...initialState,
//       };
//     default:
//       return state;
//   }
// };
//
// const apiUrl = 'api/message-groups';
// const apiUrlMessageContent = 'api/message-contents';
// const apiSearchUrl = 'api/_search/message-groups';
//
// // Actions
//
// export const getSearchEntities: ICrudSearchAction<IMessageGroup> = (query, page, size, sort) => ({
//   type: ACTION_TYPES.SEARCH_MESSAGEGROUPS,
//   payload: axios.get<IMessageGroup>(`${apiSearchUrl}?query=${query}${sort ? `&page=${page}&size=${size}&sort=${sort}` : ''}`),
// });
//
// export const deleteEntity: ICrudDeleteAction<IMessageGroup> = id => async dispatch =>
// {
//   const requestUrl = `${apiUrl}/${id}`;
//   const result = await dispatch({
//     type: ACTION_TYPES.DELETE_MESSAGEGROUP,
//     payload: axios.delete(requestUrl),
//   });
//   return result;
// };
//
//
// export const setBlob = (name, data, contentType?) => ({
//   type: ACTION_TYPES.SET_BLOB,
//   payload: {
//     name,
//     data,
//     contentType,
//   },
// });
//
// export const reset = () => ({
//   type: ACTION_TYPES.RESET,
// });
