import axios from 'axios';
import {createAsyncThunk, isFulfilled, isPending} from '@reduxjs/toolkit';
import {loadMoreDataWhenScrolled, parseHeaderForLinks} from 'react-jhipster';

import {cleanEntity} from 'app/shared/util/entity-utils';
import {createEntitySlice, EntityState, IQueryParams, serializeAxiosError} from 'app/shared/reducers/reducer.utils';
import {defaultValue, IHistoryUpdate} from 'app/shared/model/history-update.model';

const initialState: EntityState<IHistoryUpdate> = {
  loading: false,
  errorMessage: null,
  entities: [],
  entity: defaultValue,
  links: {next: 0},
  updating: false,
  totalItems: 0,
  updateSuccess: false,
};

const apiUrl = 'api/history-updates';
const apiSearchUrl = 'api/_search/history-updates';

// Actions

export const searchEntities = createAsyncThunk('historyUpdate/search_entity', async ({query, page, size, sort}: IQueryParams) =>
{
  const requestUrl = `${apiSearchUrl}?query=${query}${sort ? `&page=${page}&size=${size}&sort=${sort}` : ''}`;
  return axios.get<IHistoryUpdate[]>(requestUrl);
});

export const getEntities = createAsyncThunk('historyUpdate/fetch_entity_list', async ({page, size, sort}: IQueryParams) =>
{
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}&` : '?'}cacheBuster=${new Date().getTime()}`;
  return axios.get<IHistoryUpdate[]>(requestUrl);
});

export const getEntity = createAsyncThunk(
  'historyUpdate/fetch_entity',
  async (id: string | number) =>
  {
    const requestUrl = `${apiUrl}/${id}`;
    return axios.get<IHistoryUpdate>(requestUrl);
  },
  {serializeError: serializeAxiosError}
);

export const createEntity = createAsyncThunk(
  'historyUpdate/create_entity',
  async (entity: IHistoryUpdate, thunkAPI) =>
  {
    return axios.post<IHistoryUpdate>(apiUrl, cleanEntity(entity));
  },
  {serializeError: serializeAxiosError}
);

export const updateEntity = createAsyncThunk(
  'historyUpdate/update_entity',
  async (entity: IHistoryUpdate, thunkAPI) =>
  {
    return axios.put<IHistoryUpdate>(`${apiUrl}/${entity.id}`, cleanEntity(entity));
  },
  {serializeError: serializeAxiosError}
);

export const partialUpdateEntity = createAsyncThunk(
  'historyUpdate/partial_update_entity',
  async (entity: IHistoryUpdate, thunkAPI) =>
  {
    return axios.patch<IHistoryUpdate>(`${apiUrl}/${entity.id}`, cleanEntity(entity));
  },
  {serializeError: serializeAxiosError}
);

export const deleteEntity = createAsyncThunk(
  'historyUpdate/delete_entity',
  async (id: string | number, thunkAPI) =>
  {
    const requestUrl = `${apiUrl}/${id}`;
    return await axios.delete<IHistoryUpdate>(requestUrl);
  },
  {serializeError: serializeAxiosError}
);

// slice

export const HistoryUpdateSlice = createEntitySlice({
  name: 'historyUpdate',
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

export const {reset} = HistoryUpdateSlice.actions;

// Reducer
export default HistoryUpdateSlice.reducer;
