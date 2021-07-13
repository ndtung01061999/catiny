import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import HanhChinhVN from './hanh-chinh-vn';
import HanhChinhVNDetail from './hanh-chinh-vn-detail';
import HanhChinhVNUpdate from './hanh-chinh-vn-update';
import HanhChinhVNDeleteDialog from './hanh-chinh-vn-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={HanhChinhVNUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={HanhChinhVNUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={HanhChinhVNDetail} />
      <ErrorBoundaryRoute path={match.url} component={HanhChinhVN} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={HanhChinhVNDeleteDialog} />
  </>
);

export default Routes;
