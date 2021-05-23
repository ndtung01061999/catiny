// import {Storage} from 'react-jhipster';
// import {Observable} from 'rxjs';
// import SockJS from 'sockjs-client';
// import Stomp from 'webstomp-client';
// import {IUserSettingsProps, SettingsPage} from "app/modules/account/settings/settings";
// import {IRootState} from "app/shared/reducers";
// import {getSession} from "app/shared/reducers/authentication";
// import {reset, saveAccountSettings} from "app/modules/account/settings/settings.reducer";
// import {connect as reduxConnect} from 'react-redux';
// import {v4} from 'uuid';
//
// let sourceBuffer = null;
//
// let stompClient = null;
// let subscriber = null;
// let connection: Promise<any>;
// let connectedPromise: any = null;
// let listener: Observable<any>;
// let listenerObserver: any;
// let alreadyConnectedOnce = false;
// const uuid = v4();
//
// const authToken = Storage.local.get('jhi-authenticationToken') || Storage.session.get('jhi-authenticationToken');
//
// const createConnection = (): Promise<any> =>
//   new Promise((resolve, reject) => (connectedPromise = resolve));
//
// const createListener = (): Observable<any> =>
//   new Observable(observer => listenerObserver = observer);
//
// const sendData = (data, details) =>
//   connection?.then(() =>
//     stompClient?.send
//     (
//       '/topic/video-call/activity', // destination
//       JSON.stringify({data, details: uuid}), // body
//       {} // header
//     )
//   );
//
// const subscribe = () =>
//   connection.then(() =>
//     subscriber = stompClient.subscribe
//     (
//       `/topic/video-call/group/${props.account.login}/${uuid}`,
//       data => process(data)
//     )
//   );
//
//
// const connect = () =>
// {
//   if (connectedPromise !== null || alreadyConnectedOnce)
//   {
//     // the connection is already being established
//     return;
//   }
//   connection = createConnection();
//   listener = createListener();
//
//   // building absolute path so that websocket doesn't fail when deploying with a context path
//   const loc = window.location;
//   const baseHref = document.querySelector('base').getAttribute('href').replace(/\/$/, '');
//
//   const headers = {};
//   let url = '//' + loc.host + baseHref + '/websocket/video-call';
//   if (authToken)
//     url += '?access_token=' + authToken;
//
//   const socket = new SockJS(url);
//   stompClient = Stomp.over(socket, {protocols: ['v12.stomp']});
//   // stompClient.debug=()=>{};// don't show debug console log
//
//   stompClient.connect(headers, () =>
//   {
//     connectedPromise('success');
//     connectedPromise = null;
//     // sendActivity(); first message after connect success
//     alreadyConnectedOnce = true;
//   });
// };
//
//
// const receive = () => listener;
//
// const disconnect = () =>
// {
//   if (stompClient !== null)
//     if (stompClient.connected)
//       stompClient.disconnect();
//   stompClient = null;
//   alreadyConnectedOnce = false;
// };
//
// const unsubscribe = () =>
// {
//   if (subscriber !== null)
//     subscriber.unsubscribe();
//   listener = createListener();
// };
//
//
//
//
//
// const handleDataAvailable = useCallback(({data}) =>
// {
//   if (data.size > 0)
//   {
//     const reader = new FileReader();
//     reader.readAsDataURL(data);
//     reader.onloadend = () => sendActivity(reader.result, "chạy mẹ nó rồi");
//   }
// }, []);
//
// const process = (videoData) =>
// {
//   const result = JSON.parse(videoData.body);
//   let videoBase64 = result.data.split(",");
//   videoBase64 = videoBase64[videoBase64.length - 1];
//   const data = Uint8Array.from(atob(videoBase64), c => c.charCodeAt(0));
//   sourceBuffer.appendBuffer(new Uint8Array(data));
// };
//
//
// const log = (x) => window.console.log(x);
//
//
// const mapStateToProps = ({authentication}: IRootState) => ({
//   account: authentication.account,
//   isAuthenticated: authentication.isAuthenticated,
// });
//
// const mapDispatchToProps = {getSession, saveAccountSettings, reset};
//
//
