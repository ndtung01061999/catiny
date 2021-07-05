import React from 'react';
import {Route, Switch, useLocation} from 'react-router-dom';
import Loadable from 'react-loadable';

import Login from 'app/modules/login/login';
import Register from 'app/modules/account/register/register';
import Activate from 'app/modules/account/activate/activate';
import PasswordResetInit from 'app/modules/account/password-reset/init/password-reset-init';
import PasswordResetFinish from 'app/modules/account/password-reset/finish/password-reset-finish';
import Logout from 'app/modules/login/logout';
import Home from 'app/modules/home/home';
import Entities from 'app/entities';
import PrivateRoute from 'app/shared/auth/private-route';
import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';
import PageNotFound from 'app/shared/error/page-not-found';
import {AUTHORITIES} from 'app/config/constants';
import {sendActivity} from 'app/config/websocket-middleware';

import Chat from 'app/component/chat';

//Sociala
import Demo from "app-js/demo/Demo";
import HomeSociala from 'app-js/pages/Home';
import Badge from "app-js/pages/Badge";
import Group from "app-js/pages/Group";
import Storie from "app-js/pages/Storie";
import Email from "app-js/pages/Email";
import EmailOpen from "app-js/pages/EmailOpen";
import Settings from "app-js/pages/Settings";
import Videos from "app-js/pages/Videos";
import Analytics from "app-js/pages/Analytics";
import Member from "app-js/pages/Member";
import ContactInfo from "app-js/pages/ContactInfo";
import SocialAccount from "app-js/pages/SocialAccount";
import Password from "app-js/pages/Password";
import Payment from "app-js/pages/Payment";
import Notification from "app-js/pages/Notification";
import HelpBox from "app-js/pages/HelpBox";
import Forgot from "app-js/pages/Forgot";
import Notfound from "app-js/pages/Notfound";
import ShopOne from "app-js/pages/ShopOne";
import ShopTwo from "app-js/pages/ShopTwo";
import ShopThree from "app-js/pages/ShopThree";
import SingleProduct from "app-js/pages/SingleProduct";
import Cart from "app-js/pages/Cart";
import Checkout from "app-js/pages/Checkout";
import Live from "app-js/pages/Live";
import Job from "app-js/pages/Job";
import Event from "app-js/pages/Event";
import Hotel from "app-js/pages/Hotel";
import GroupPage from "app-js/pages/GroupPage";
import UserPage from "app-js/pages/UserPage";
import AuthorPage from "app-js/pages/AuthorPage";
import ComingSoon from "app-js/pages/ComingSoon";
import HotelSingle from "app-js/pages/HotelSingle";

const Account = Loadable({
  loader: () => import(/* webpackChunkName: "account" */ 'app/modules/account'),
  loading: () => <div>loading ...</div>,
});

const Admin = Loadable({
  loader: () => import(/* webpackChunkName: "administration" */ 'app/modules/administration'),
  loading: () => <div>loading ...</div>,
});

const Routes = () => {
  const location = useLocation();
  React.useEffect(() => {
    sendActivity(location.pathname);
  }, [location]);
  return (
    <div className="view-routes">
      <Switch>
        <ErrorBoundaryRoute path="/login" component={Login}/>
        <ErrorBoundaryRoute path="/logout" component={Logout}/>
        <ErrorBoundaryRoute path="/account/register" component={Register}/>
        <ErrorBoundaryRoute path="/account/activate/:key?" component={Activate}/>
        <ErrorBoundaryRoute path="/account/reset/request" component={PasswordResetInit}/>
        <ErrorBoundaryRoute path="/account/reset/finish/:key?" component={PasswordResetFinish}/>
        <PrivateRoute path="/admin" component={Admin} hasAnyAuthorities={[AUTHORITIES.ADMIN]}/>
        <PrivateRoute path="/account" component={Account} hasAnyAuthorities={[AUTHORITIES.ADMIN, AUTHORITIES.USER]}/>
        <ErrorBoundaryRoute path="/" exact component={Home}/>
        <PrivateRoute path="/chat" component={Chat} hasAnyAuthorities={[AUTHORITIES.USER]}/>
        {/*<PrivateRoute path="/" component={Entities} hasAnyAuthorities={[AUTHORITIES.ADMIN]}/>/!* tiện ích jhipster chỉ để admin dùng *!/*/}

        <Route exact path={`/demo`} component={Demo}/>
        <Route exact path={`/home`} component={HomeSociala}/>
        <Route exact path={`/defaultbadge`} component={Badge}/>
        <Route exact path={`/defaultgroup`} component={Group}/>
        <Route exact path={`/defaultstorie`} component={Storie}/>
        <Route exact path={`/defaultemailbox`} component={Email}/>
        <Route exact path={`/defaultemailopen`} component={EmailOpen}/>
        <Route exact path={`/defaultsettings`} component={Settings}/>
        <Route exact path={`/defaultvideo`} component={Videos}/>
        <Route exact path={`/defaultanalytics`} component={Analytics}/>
        <Route exact path={`/accountinformation`} component={Account}/>
        <Route exact path={`/defaultmember`} component={Member}/>
        <Route exact path={`/contactinformation`} component={ContactInfo}/>
        <Route exact path={`/socialaccount`} component={SocialAccount}/>
        <Route exact path={`/password`} component={Password}/>
        <Route exact path={`/payment`} component={Payment}/>
        <Route exact path={`/defaultnotification`} component={Notification}/>
        <Route exact path={`/helpbox`} component={HelpBox}/>
        <Route exact path={`/login2`} component={Login}/>
        <Route exact path={`/register`} component={Register}/>
        <Route exact path={`/forgot`} component={Forgot}/>
        <Route exact path={`/notfound`} component={Notfound}/>
        <Route exact path={`/shop1`} component={ShopOne}/>
        <Route exact path={`/shop2`} component={ShopTwo}/>
        <Route exact path={`/shop3`} component={ShopThree}/>
        <Route exact path={`/singleproduct`} component={SingleProduct}/>
        <Route exact path={`/cart`} component={Cart}/>
        <Route exact path={`/checkout`} component={Checkout}/>
        <Route exact path={`/defaultmessage`} component={Chat}/>
        <Route exact path={`/defaultlive`} component={Live}/>
        <Route exact path={`/defaultjob`} component={Job}/>
        <Route exact path={`/defaultevent`} component={Event}/>
        <Route exact path={`/defaulthotel`} component={Hotel}/>
        <Route exact path={`/grouppage`} component={GroupPage}/>
        <Route exact path={`/userpage`} component={UserPage}/>
        <Route exact path={`/groupauthorpage`} component={AuthorPage}/>
        <Route exact path={`/comingsoon`} component={ComingSoon}/>
        <Route exact path={`/defaulthoteldetails`} component={HotelSingle}/>

        <ErrorBoundaryRoute component={PageNotFound}/>
      </Switch>
    </div>
  );
};

export default Routes;
