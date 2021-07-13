import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import RankUser from './rank-user';
import RankUserDetail from './rank-user-detail';
import RankUserUpdate from './rank-user-update';
import RankUserDeleteDialog from './rank-user-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={RankUserUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={RankUserUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={RankUserDetail} />
      <ErrorBoundaryRoute path={match.url} component={RankUser} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={RankUserDeleteDialog} />
  </>
);

export default Routes;
