import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import PageProfile from './page-profile';
import PageProfileDetail from './page-profile-detail';
import PageProfileUpdate from './page-profile-update';
import PageProfileDeleteDialog from './page-profile-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={PageProfileUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={PageProfileUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={PageProfileDetail} />
      <ErrorBoundaryRoute path={match.url} component={PageProfile} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={PageProfileDeleteDialog} />
  </>
);

export default Routes;
