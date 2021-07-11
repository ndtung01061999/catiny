import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import NewsFeed from './news-feed';
import NewsFeedDetail from './news-feed-detail';
import NewsFeedUpdate from './news-feed-update';
import NewsFeedDeleteDialog from './news-feed-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={NewsFeedUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={NewsFeedUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={NewsFeedDetail} />
      <ErrorBoundaryRoute path={match.url} component={NewsFeed} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={NewsFeedDeleteDialog} />
  </>
);

export default Routes;
