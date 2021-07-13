import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import MasterUser from './master-user';
import MasterUserDetail from './master-user-detail';
import MasterUserUpdate from './master-user-update';
import MasterUserDeleteDialog from './master-user-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={MasterUserUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={MasterUserUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={MasterUserDetail} />
      <ErrorBoundaryRoute path={match.url} component={MasterUser} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={MasterUserDeleteDialog} />
  </>
);

export default Routes;
