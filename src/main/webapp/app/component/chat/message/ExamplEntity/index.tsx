import React from 'react';
import {Switch} from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import ExamplEntity from './ExamplEntity';

const Routes = ({match}) => (
  <>
    <Switch>
      <ErrorBoundaryRoute path={match.url} component={ExamplEntity}/>
    </Switch>
  </>
);

export default Routes;
