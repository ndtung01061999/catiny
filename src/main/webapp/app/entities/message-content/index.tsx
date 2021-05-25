import React from 'react';
import {Switch} from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import MessageContent from './message-content';
import MessageContentDetail from './message-content-detail';
import MessageContentUpdate from './message-content-update';
import MessageContentDeleteDialog from './message-content-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={MessageContentUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={MessageContentUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={MessageContentDetail} />
      <ErrorBoundaryRoute path={match.url} component={MessageContent} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={MessageContentDeleteDialog} />
  </>
);

export default Routes;
