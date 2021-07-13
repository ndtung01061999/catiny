import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import PostLike from './post-like';
import PostLikeDetail from './post-like-detail';
import PostLikeUpdate from './post-like-update';
import PostLikeDeleteDialog from './post-like-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={PostLikeUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={PostLikeUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={PostLikeDetail} />
      <ErrorBoundaryRoute path={match.url} component={PostLike} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={PostLikeDeleteDialog} />
  </>
);

export default Routes;
