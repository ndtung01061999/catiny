import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import FollowPage from './follow-page';
import FollowPageDetail from './follow-page-detail';
import FollowPageUpdate from './follow-page-update';
import FollowPageDeleteDialog from './follow-page-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={FollowPageUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={FollowPageUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={FollowPageDetail} />
      <ErrorBoundaryRoute path={match.url} component={FollowPage} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={FollowPageDeleteDialog} />
  </>
);

export default Routes;
