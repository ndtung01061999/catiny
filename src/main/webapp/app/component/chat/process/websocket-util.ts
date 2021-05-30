import {Storage} from 'react-jhipster';
import {Observable} from 'rxjs';
import SockJS from 'sockjs-client';
import Stomp from 'webstomp-client';
import {IRootState} from "app/shared/reducers";
import {getSession} from "app/shared/reducers/authentication";
import {reset, saveAccountSettings} from "app/modules/account/settings/settings.reducer";
import {v4} from 'uuid';


let stompClient = null;
let subscriber = null;
let connection: Promise<any>;
let connectedPromise: any = null;
let listener: Observable<any>;
let listenerObserver: any;
let alreadyConnectedOnce = false;
const uuid = v4();

const authToken = Storage.local.get('jhi-authenticationToken') || Storage.session.get('jhi-authenticationToken');

const createConnection = (): Promise<any> =>
  new Promise((resolve, reject) => (connectedPromise = resolve));

const createListener = (): Observable<any> =>
  new Observable(observer => listenerObserver = observer);

export const sendData = (groupSend, body, header) =>
  connection?.then(() =>
    stompClient?.send
    (
      `/topic/${groupSend}`, // destination
      JSON.stringify(body), // body
      // body, // body
      header // header
    ));

export const subscribe = (groupSubscribe, functionProcess) =>
  connection.then(() =>
    subscriber = stompClient.subscribe
    (
      `/topic/${groupSubscribe}`,
      data => functionProcess(data)
    )
  );

export const connect = () =>
{
  if (connectedPromise !== null || alreadyConnectedOnce)
  {
    // the connection is already being established
    return;
  }
  connection = createConnection();
  listener = createListener();

  // building absolute path so that websocket doesn't fail when deploying with a context path
  const loc = window.location;
  const baseHref = document.querySelector('base').getAttribute('href').replace(/\/$/, '');

  const headers = {};
  let url = '//' + loc.host + baseHref + '/websocket/message';
  if (authToken)
    url += '?access_token=' + authToken;

  const socket = new SockJS(url);
  stompClient = Stomp.over(socket, {protocols: ['v12.stomp']});
  // stompClient.debug=()=>{};// don't show debug console log

  stompClient.connect(headers, () =>
  {
    connectedPromise('success');
    connectedPromise = null;
    // sendActivity(); first message after connect success
    alreadyConnectedOnce = true;
  });
};


const receive = () => listener;

const disconnect = () =>
{
  if (stompClient !== null)
    if (stompClient.connected)
      stompClient.disconnect();
  stompClient = null;
  alreadyConnectedOnce = false;
};

const unsubscribe = () =>
{
  if (subscriber !== null)
    subscriber.unsubscribe();
  listener = createListener();
};


const log = (x) => window.console.log(x);


const mapStateToProps = ({authentication}: IRootState) => ({
  account: authentication.account,
  isAuthenticated: authentication.isAuthenticated,
});

const mapDispatchToProps = {getSession, saveAccountSettings, reset};


