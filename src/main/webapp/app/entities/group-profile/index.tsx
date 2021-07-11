import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import GroupProfile from './group-profile';
import GroupProfileDetail from './group-profile-detail';
import GroupProfileUpdate from './group-profile-update';
import GroupProfileDeleteDialog from './group-profile-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={GroupProfileUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={GroupProfileUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={GroupProfileDetail} />
      <ErrorBoundaryRoute path={match.url} component={GroupProfile} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={GroupProfileDeleteDialog} />
  </>
);

export default Routes;
