import React from 'react';
import {Switch} from 'react-router-dom';

// eslint-disable-next-line @typescript-eslint/no-unused-vars
import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';
import VideoCall from './video-call';
import Messages from './message/messages';

/* jhipster-needle-add-route-import - JHipster will add routes here */

const Routes = ({ match }) => {
  window.console.log(`${match.url}post`);
  return (
    <div>
      <Switch>
        {/* prettier-ignore */}
        <ErrorBoundaryRoute path={`${match.url}/video-call`} component={VideoCall}/>
        <ErrorBoundaryRoute path={`${match.url}/messages`} component={Messages} />
      </Switch>
    </div>
  );
};

export default Routes;
