import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import VideoStream from './video-stream';
import VideoStreamDetail from './video-stream-detail';
import VideoStreamUpdate from './video-stream-update';
import VideoStreamDeleteDialog from './video-stream-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={VideoStreamUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={VideoStreamUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={VideoStreamDetail} />
      <ErrorBoundaryRoute path={match.url} component={VideoStream} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={VideoStreamDeleteDialog} />
  </>
);

export default Routes;
