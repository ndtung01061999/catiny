import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import TopicInterest from './topic-interest';
import TopicInterestDetail from './topic-interest-detail';
import TopicInterestUpdate from './topic-interest-update';
import TopicInterestDeleteDialog from './topic-interest-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={TopicInterestUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={TopicInterestUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={TopicInterestDetail} />
      <ErrorBoundaryRoute path={match.url} component={TopicInterest} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={TopicInterestDeleteDialog} />
  </>
);

export default Routes;
