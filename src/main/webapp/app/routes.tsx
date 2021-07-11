import React from 'react';
import {Switch, useLocation} from 'react-router-dom';
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

        <ErrorBoundaryRoute exact path={`/demo`} component={Demo}/>
        <ErrorBoundaryRoute exact path={`/home`} component={HomeSociala}/>
        <ErrorBoundaryRoute exact path={`/defaultbadge`} component={Badge}/>
        <ErrorBoundaryRoute exact path={`/defaultgroup`} component={Group}/>
        <ErrorBoundaryRoute exact path={`/defaultstorie`} component={Storie}/>
        <ErrorBoundaryRoute exact path={`/defaultemailbox`} component={Email}/>
        <ErrorBoundaryRoute exact path={`/defaultemailopen`} component={EmailOpen}/>
        <ErrorBoundaryRoute exact path={`/defaultsettings`} component={Settings}/>
        <ErrorBoundaryRoute exact path={`/defaultvideo`} component={Videos}/>
        <ErrorBoundaryRoute exact path={`/defaultanalytics`} component={Analytics}/>
        <ErrorBoundaryRoute exact path={`/accountinformation`} component={Account}/>
        <ErrorBoundaryRoute exact path={`/defaultmember`} component={Member}/>
        <ErrorBoundaryRoute exact path={`/contactinformation`} component={ContactInfo}/>
        <ErrorBoundaryRoute exact path={`/socialaccount`} component={SocialAccount}/>
        <ErrorBoundaryRoute exact path={`/password`} component={Password}/>
        <ErrorBoundaryRoute exact path={`/payment`} component={Payment}/>
        <ErrorBoundaryRoute exact path={`/defaultnotification`} component={Notification}/>
        <ErrorBoundaryRoute exact path={`/helpbox`} component={HelpBox}/>
        <ErrorBoundaryRoute exact path={`/login2`} component={Login}/>
        <ErrorBoundaryRoute exact path={`/register`} component={Register}/>
        <ErrorBoundaryRoute exact path={`/forgot`} component={Forgot}/>
        <ErrorBoundaryRoute exact path={`/notfound`} component={Notfound}/>
        <ErrorBoundaryRoute exact path={`/shop1`} component={ShopOne}/>
        <ErrorBoundaryRoute exact path={`/shop2`} component={ShopTwo}/>
        <ErrorBoundaryRoute exact path={`/shop3`} component={ShopThree}/>
        <ErrorBoundaryRoute exact path={`/singleproduct`} component={SingleProduct}/>
        <ErrorBoundaryRoute exact path={`/cart`} component={Cart}/>
        <ErrorBoundaryRoute exact path={`/checkout`} component={Checkout}/>
        <ErrorBoundaryRoute exact path={`/defaultmessage`} component={Chat}/>
        <ErrorBoundaryRoute exact path={`/defaultlive`} component={Live}/>
        <ErrorBoundaryRoute exact path={`/defaultjob`} component={Job}/>
        <ErrorBoundaryRoute exact path={`/defaultevent`} component={Event}/>
        <ErrorBoundaryRoute exact path={`/defaulthotel`} component={Hotel}/>
        <ErrorBoundaryRoute exact path={`/grouppage`} component={GroupPage}/>
        <ErrorBoundaryRoute exact path={`/userpage`} component={UserPage}/>
        <ErrorBoundaryRoute exact path={`/groupauthorpage`} component={AuthorPage}/>
        <ErrorBoundaryRoute exact path={`/comingsoon`} component={ComingSoon}/>
        <ErrorBoundaryRoute exact path={`/defaulthoteldetails`} component={HotelSingle}/>

        <PrivateRoute path="/" component={Entities} hasAnyAuthorities={[AUTHORITIES.ADMIN]}/>{/* tiện ích jhipster chỉ để admin dùng */}
        <ErrorBoundaryRoute component={PageNotFound}/>
      </Switch>
    </div>
  );
};

export default Routes;
