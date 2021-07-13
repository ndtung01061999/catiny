import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import FollowUser from './follow-user';
import FollowUserDetail from './follow-user-detail';
import FollowUserUpdate from './follow-user-update';
import FollowUserDeleteDialog from './follow-user-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={FollowUserUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={FollowUserUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={FollowUserDetail} />
      <ErrorBoundaryRoute path={match.url} component={FollowUser} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={FollowUserDeleteDialog} />
  </>
);

export default Routes;
