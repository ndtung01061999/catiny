import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import RankGroup from './rank-group';
import RankGroupDetail from './rank-group-detail';
import RankGroupUpdate from './rank-group-update';
import RankGroupDeleteDialog from './rank-group-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={RankGroupUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={RankGroupUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={RankGroupDetail} />
      <ErrorBoundaryRoute path={match.url} component={RankGroup} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={RankGroupDeleteDialog} />
  </>
);

export default Routes;
