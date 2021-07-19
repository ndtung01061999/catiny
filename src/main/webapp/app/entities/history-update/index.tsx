import React from 'react';
import {Switch} from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import HistoryUpdate from './history-update';
import HistoryUpdateDetail from './history-update-detail';
import HistoryUpdateUpdate from './history-update-update';
import HistoryUpdateDeleteDialog from './history-update-delete-dialog';

const Routes = ({match}) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={HistoryUpdateUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={HistoryUpdateUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={HistoryUpdateDetail} />
      <ErrorBoundaryRoute path={match.url} component={HistoryUpdate} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={HistoryUpdateDeleteDialog} />
  </>
);

export default Routes;
