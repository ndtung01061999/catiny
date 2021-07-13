import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import PagePost from './page-post';
import PagePostDetail from './page-post-detail';
import PagePostUpdate from './page-post-update';
import PagePostDeleteDialog from './page-post-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={PagePostUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={PagePostUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={PagePostDetail} />
      <ErrorBoundaryRoute path={match.url} component={PagePost} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={PagePostDeleteDialog} />
  </>
);

export default Routes;
