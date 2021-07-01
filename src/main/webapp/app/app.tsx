import 'react-toastify/dist/ReactToastify.css';
import './app.scss';
import 'app/config/dayjs.ts';

import React, { useEffect } from 'react';
import { BrowserRouter as Router } from 'react-router-dom';
import { ToastContainer, toast } from 'react-toastify';
import { hot } from 'react-hot-loader';

import { useAppDispatch, useAppSelector } from 'app/config/store';
import { getSession } from 'app/shared/reducers/authentication';
import { getProfile } from 'app/shared/reducers/application-profile';
import { setLocale } from 'app/shared/reducers/locale';
import Header from 'app/shared/layout/header/header';
import Footer from 'app/shared/layout/footer/footer';
import { hasAnyAuthority } from 'app/shared/auth/private-route';
import ErrorBoundary from 'app/shared/error/error-boundary';
import { AUTHORITIES } from 'app/config/constants';
import AppRoutes from 'app/routes';

//sociala
import '../assets/scss/main.scss';

// import Demo from 'app-js/demo/Demo';
// import Home from 'app-js/pages/Home';
//
// import Badge from 'app-js/pages/Badge';
// import Group from 'app-js/pages/Group';
// import Storie from 'app-js/pages/Storie';
// import Member from 'app-js/pages/Member';
// import Email from 'app-js/pages/Email';
// import Emailopen from 'app-js/pages/Emailopen';
// import Settings from 'app-js/pages/Settings';
// import Account from 'app-js/pages/Account';
// import Contactinfo from 'app-js/pages/Contactinfo';
// import Socialaccount from 'app-js/pages/Socialaccount';
// import Password from 'app-js/pages/Password';
// import Payment from 'app-js/pages/Payment';
// import Notification from 'app-js/pages/Notification';
// import Helpbox from 'app-js/pages/Helpbox';
// import Login from 'app-js/pages/Login';
// import Register from 'app-js/pages/Register';
// import Forgot from 'app-js/pages/Forgot';
// import Notfound from 'app-js/pages/Notfound';
//
// import ShopOne from 'app-js/pages/ShopOne';
// import ShopTwo from 'app-js/pages/ShopTwo';
// import ShopThree from 'app-js/pages/ShopThree';
// import Singleproduct from 'app-js/pages/Singleproduct';
// import Cart from 'app-js/pages/Cart';
// import Checkout from 'app-js/pages/Checkout';
// import Chat from 'app-js/pages/Chat';
// import Live from 'app-js/pages/Live';
// import Job from 'app-js/pages/Job';
// import Event from 'app-js/pages/Event';
// import Hotel from 'app-js/pages/Hotel';
// import Videos from 'app-js/pages/Videos';
// import Comingsoon from 'app-js/pages/Comingsoon';
//
//
// import Grouppage from 'app-js/pages/Grouppage';
// import Userpage from 'app-js/pages/Userpage';
// import Authorpage from 'app-js/pages/Authorpage';
// import Hotelsingle from 'app-js/pages/Hotelsingle';
// import Analytics from 'app-js/pages/Analytics';

const baseHref = document.querySelector('base').getAttribute('href').replace(/\/$/, '');

export const App = () => {
  const dispatch = useAppDispatch();

  useEffect(() => {
    dispatch(getSession());
    dispatch(getProfile());
  }, []);

  const currentLocale = useAppSelector(state => state.locale.currentLocale);
  const isAuthenticated = useAppSelector(state => state.authentication.isAuthenticated);
  const isAdmin = useAppSelector(state => hasAnyAuthority(state.authentication.account.authorities, [AUTHORITIES.ADMIN]));
  const ribbonEnv = useAppSelector(state => state.applicationProfile.ribbonEnv);
  const isInProduction = useAppSelector(state => state.applicationProfile.inProduction);
  const isOpenAPIEnabled = useAppSelector(state => state.applicationProfile.isOpenAPIEnabled);

  const paddingTop = '52px';
  return (
    <Router basename={baseHref}>
      <div className="app-container" style={{ paddingTop }}>
        <ToastContainer position={toast.POSITION.TOP_LEFT} className="toastify-container" toastClassName="toastify-toast" />
        <ErrorBoundary>
          <Header
            isAuthenticated={isAuthenticated}
            isAdmin={isAdmin}
            currentLocale={currentLocale}
            ribbonEnv={ribbonEnv}
            isInProduction={isInProduction}
            isOpenAPIEnabled={isOpenAPIEnabled}
          />
        </ErrorBoundary>
        <div className="container-xl px-0 px-md-1" id="app-view-container">
          <div className="jh-card">
            <ErrorBoundary>
              <AppRoutes />
            </ErrorBoundary>
          </div>
          <Footer />
        </div>
      </div>
    </Router>
  );
};

export default hot(module)(App);


//  // Sociala

// <BrowserRouter basename={'/'}>
//   <Switch>
//     <Route exact path={`/`} component={Demo}/>
//     <Route exact path={`/home`} component={Home}/>
//
//     <Route exact path={`/defaultbadge`} component={Badge}/>
//     <Route exact path={`/defaultgroup`} component={Group}/>
//     <Route exact path={`/defaultstorie`} component={Storie}/>
//     <Route exact path={`/defaultemailbox`} component={Email}/>
//     <Route exact path={`/defaultemailopen`} component={Emailopen}/>
//     <Route exact path={`/defaultsettings`} component={Settings}/>
//     <Route exact path={`/defaultvideo`} component={Videos}/>
//     <Route exact path={`/defaultanalytics`} component={Analytics}/>
//
//
//     <Route exact path={`/accountinformation`} component={Account}/>
//     <Route exact path={`/defaultmember`} component={Member}/>
//     <Route exact path={`/contactinformation`} component={Contactinfo}/>
//     <Route exact path={`/socialaccount`} component={Socialaccount}/>
//     <Route exact path={`/password`} component={Password}/>
//     <Route exact path={`/payment`} component={Payment}/>
//     <Route exact path={`/defaultnotification`} component={Notification}/>
//     <Route exact path={`/helpbox`} component={Helpbox}/>
//     <Route exact path={`/login`} component={Login}/>
//     <Route exact path={`/register`} component={Register}/>
//     <Route exact path={`/forgot`} component={Forgot}/>
//     <Route exact path={`/notfound`} component={Notfound}/>
//
//     <Route exact path={`/shop1`} component={ShopOne}/>
//     <Route exact path={`/shop2`} component={ShopTwo}/>
//     <Route exact path={`/shop3`} component={ShopThree}/>
//     <Route exact path={`/singleproduct`} component={Singleproduct}/>
//     <Route exact path={`/cart`} component={Cart}/>
//     <Route exact path={`/checkout`} component={Checkout}/>
//     <Route exact path={`/defaultmessage`} component={Chat}/>
//     <Route exact path={`/defaultlive`} component={Live}/>
//
//     <Route exact path={`/defaultjob`} component={Job}/>
//     <Route exact path={`/defaultevent`} component={Event}/>
//     <Route exact path={`/defaulthotel`} component={Hotel}/>
//     <Route exact path={`/grouppage`} component={Grouppage}/>
//     <Route exact path={`/userpage`} component={Userpage}/>
//     <Route exact path={`/groupauthorpage`} component={Authorpage}/>
//     <Route exact path={`/comingsoon`} component={Comingsoon}/>
//     <Route exact path={`/defaulthoteldetails`} component={Hotelsingle}/>
//
//   </Switch>
// </BrowserRouter>
