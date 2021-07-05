import axios from 'axios';
import {createAsyncThunk, isFulfilled, isPending} from '@reduxjs/toolkit';
import {loadMoreDataWhenScrolled, parseHeaderForLinks} from 'react-jhipster';

import {cleanEntity} from 'app/shared/util/entity-utils';
import {createEntitySlice, EntityState, IQueryParams, serializeAxiosError} from 'app/shared/reducers/reducer.utils';
import {defaultValue, IMessageContent} from 'app/shared/model/message-content.model';

const initialState: EntityState<IMessageContent> = {
  loading: false,
  errorMessage: null,
  entities: [],
  entity: defaultValue,
  links: {next: 0},
  updating: false,
  totalItems: 0,
  updateSuccess: false,
};

const apiUrl = 'api/message-contents';
const apiSearchUrl = 'api/_search/message-contents';

// Actions

export const searchEntities = createAsyncThunk('messageContent/search_entity', async ({query, page, size, sort}: IQueryParams) =>
{
  const requestUrl = `${apiSearchUrl}?query=${query}${sort ? `&page=${page}&size=${size}&sort=${sort}` : ''}`;
  return axios.get<IMessageContent[]>(requestUrl);
});

export const getEntities = createAsyncThunk('messageContent/fetch_entity_list', async ({page, size, sort}: IQueryParams) =>
{
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}&` : '?'}cacheBuster=${new Date().getTime()}`;
  return axios.get<IMessageContent[]>(requestUrl);
});

export const getEntity = createAsyncThunk(
  'messageContent/fetch_entity',
  async (id: string | number) =>
  {
    const requestUrl = `${apiUrl}/${id}`;
    return axios.get<IMessageContent>(requestUrl);
  },
  {serializeError: serializeAxiosError}
);

export const createEntity = createAsyncThunk(
  'messageContent/create_entity',
  async (entity: IMessageContent, thunkAPI) =>
  {
    return axios.post<IMessageContent>(apiUrl, cleanEntity(entity));
  },
  {serializeError: serializeAxiosError}
);

export const updateEntity = createAsyncThunk(
  'messageContent/update_entity',
  async (entity: IMessageContent, thunkAPI) =>
  {
    return axios.put<IMessageContent>(`${apiUrl}/${entity.id}`, cleanEntity(entity));
  },
  {serializeError: serializeAxiosError}
);

export const partialUpdateEntity = createAsyncThunk(
  'messageContent/partial_update_entity',
  async (entity: IMessageContent, thunkAPI) =>
  {
    return axios.patch<IMessageContent>(`${apiUrl}/${entity.id}`, cleanEntity(entity));
  },
  {serializeError: serializeAxiosError}
);

export const deleteEntity = createAsyncThunk(
  'messageContent/delete_entity',
  async (id: string | number, thunkAPI) =>
  {
    const requestUrl = `${apiUrl}/${id}`;
    return await axios.delete<IMessageContent>(requestUrl);
  },
  {serializeError: serializeAxiosError}
);

// slice

export const MessageContentSlice = createEntitySlice({
  name: 'messageContent',
  initialState,
  extraReducers(builder)
  {
    builder
      .addCase(getEntity.fulfilled, (state, action) =>
      {
        state.loading = false;
        state.entity = action.payload.data;
      })
      .addCase(deleteEntity.fulfilled, state =>
      {
        state.updating = false;
        state.updateSuccess = true;
        state.entity = {};
      })
      .addMatcher(isFulfilled(getEntities, searchEntities), (state, action) =>
      {
        const links = parseHeaderForLinks(action.payload.headers.link);

        return {
          ...state,
          loading: false,
          links,
          entities: loadMoreDataWhenScrolled(state.entities, action.payload.data, links),
          totalItems: parseInt(action.payload.headers['x-total-count'], 10),
        };
      })
      .addMatcher(isFulfilled(createEntity, updateEntity, partialUpdateEntity), (state, action) =>
      {
        state.updating = false;
        state.loading = false;
        state.updateSuccess = true;
        state.entity = action.payload.data;
      })
      .addMatcher(isPending(getEntities, getEntity, searchEntities), state =>
      {
        state.errorMessage = null;
        state.updateSuccess = false;
        state.loading = true;
      })
      .addMatcher(isPending(createEntity, updateEntity, partialUpdateEntity, deleteEntity), state =>
      {
        state.errorMessage = null;
        state.updateSuccess = false;
        state.updating = true;
      });
  },
});

export const {reset} = MessageContentSlice.actions;

// Reducer
export default MessageContentSlice.reducer;
