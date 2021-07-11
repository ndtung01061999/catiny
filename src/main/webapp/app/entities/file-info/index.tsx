import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import FileInfo from './file-info';
import FileInfoDetail from './file-info-detail';
import FileInfoUpdate from './file-info-update';
import FileInfoDeleteDialog from './file-info-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={FileInfoUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={FileInfoUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={FileInfoDetail} />
      <ErrorBoundaryRoute path={match.url} component={FileInfo} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={FileInfoDeleteDialog} />
  </>
);

export default Routes;
