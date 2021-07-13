import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import VideoLiveStreamBuffer from './video-live-stream-buffer';
import VideoLiveStreamBufferDetail from './video-live-stream-buffer-detail';
import VideoLiveStreamBufferUpdate from './video-live-stream-buffer-update';
import VideoLiveStreamBufferDeleteDialog from './video-live-stream-buffer-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={VideoLiveStreamBufferUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={VideoLiveStreamBufferUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={VideoLiveStreamBufferDetail} />
      <ErrorBoundaryRoute path={match.url} component={VideoLiveStreamBuffer} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={VideoLiveStreamBufferDeleteDialog} />
  </>
);

export default Routes;
