import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import DeviceStatus from './device-status';
import DeviceStatusDetail from './device-status-detail';
import DeviceStatusUpdate from './device-status-update';
import DeviceStatusDeleteDialog from './device-status-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={DeviceStatusUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={DeviceStatusUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={DeviceStatusDetail} />
      <ErrorBoundaryRoute path={match.url} component={DeviceStatus} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={DeviceStatusDeleteDialog} />
  </>
);

export default Routes;
