import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import GroupPost from './group-post';
import GroupPostDetail from './group-post-detail';
import GroupPostUpdate from './group-post-update';
import GroupPostDeleteDialog from './group-post-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={GroupPostUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={GroupPostUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={GroupPostDetail} />
      <ErrorBoundaryRoute path={match.url} component={GroupPost} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={GroupPostDeleteDialog} />
  </>
);

export default Routes;
