import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import ClassInfo from './class-info';
import ClassInfoDetail from './class-info-detail';
import ClassInfoUpdate from './class-info-update';
import ClassInfoDeleteDialog from './class-info-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={ClassInfoUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={ClassInfoUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={ClassInfoDetail} />
      <ErrorBoundaryRoute path={match.url} component={ClassInfo} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={ClassInfoDeleteDialog} />
  </>
);

export default Routes;
