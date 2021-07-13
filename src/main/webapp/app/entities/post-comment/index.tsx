import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import PostComment from './post-comment';
import PostCommentDetail from './post-comment-detail';
import PostCommentUpdate from './post-comment-update';
import PostCommentDeleteDialog from './post-comment-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={PostCommentUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={PostCommentUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={PostCommentDetail} />
      <ErrorBoundaryRoute path={match.url} component={PostComment} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={PostCommentDeleteDialog} />
  </>
);

export default Routes;
