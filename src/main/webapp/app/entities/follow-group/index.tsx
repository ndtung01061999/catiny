import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import FollowGroup from './follow-group';
import FollowGroupDetail from './follow-group-detail';
import FollowGroupUpdate from './follow-group-update';
import FollowGroupDeleteDialog from './follow-group-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={FollowGroupUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={FollowGroupUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={FollowGroupDetail} />
      <ErrorBoundaryRoute path={match.url} component={FollowGroup} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={FollowGroupDeleteDialog} />
  </>
);

export default Routes;
