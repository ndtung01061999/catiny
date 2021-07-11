import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import AccountStatus from './account-status';
import AccountStatusDetail from './account-status-detail';
import AccountStatusUpdate from './account-status-update';
import AccountStatusDeleteDialog from './account-status-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={AccountStatusUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={AccountStatusUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={AccountStatusDetail} />
      <ErrorBoundaryRoute path={match.url} component={AccountStatus} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={AccountStatusDeleteDialog} />
  </>
);

export default Routes;
