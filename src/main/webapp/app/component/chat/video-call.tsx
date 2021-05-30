import React, {useCallback, useEffect, useRef, useState} from 'react';
import {Storage} from 'react-jhipster';
import {Observable} from 'rxjs';
import SockJS from 'sockjs-client';
import Stomp from 'webstomp-client';
import {IUserSettingsProps} from 'app/modules/account/settings/settings';
import {IRootState} from 'app/shared/reducers';
import {getSession} from 'app/shared/reducers/authentication';
import {reset, saveAccountSettings} from 'app/modules/account/settings/settings.reducer';
import {connect as reduxConnect} from 'react-redux';
import {v4} from 'uuid';

let sourceBuffer = null;
let mediaSource = new MediaSource();

let stompClient = null;
let subscriber = null;
let connection: Promise<any>;
let connectedPromise: any = null;
let listener: Observable<any>;
let listenerObserver: any;
let alreadyConnectedOnce = false;
const uuid = v4();

const VideoCall = (props: IUserSettingsProps) => {
  const webcamRef = useRef(null);
  const videoRef = useRef(null);
  const mediaRecorderRef = useRef(null);
  const [capturing, setCapturing] = useState(false);

  const videoConstraints = {
    video: {
      width: {
        max: 1920,
        // ideal:960,
        ideal: 320,
        // min:640
      },
      height: {
        max: 1080,
        // ideal:540,
        ideal: 180,
        // min:360
      },
      facingMode: { exact: 'user' },
      frameRate: 30,
    },
    audio: true,
  };

  const displayConstraints = {
    video: {
      width: {
        max: 1920,

        ideal: 960,
      },
      height: {
        max: 1080,

        ideal: 540,
      },
      frameRate: 30,
    },
    audio: true,
  };

  useEffect(() => {
    connect();
    // subscribe().then();
  }, []);

  useEffect(() => {
    props.getSession();
    return () => {
      props.reset();
    };
  }, []);

  const authToken = Storage.local.get('jhi-authenticationToken') || Storage.session.get('jhi-authenticationToken');

  const createConnection = (): Promise<any> => new Promise((resolve, reject) => (connectedPromise = resolve));

  const createListener = (): Observable<any> => new Observable(observer => (listenerObserver = observer));

  const sendActivity = (data, details) =>
    connection?.then(() =>
      stompClient?.send(
        '/topic/video-call/activity', // destination
        JSON.stringify({ data, details: uuid }), // body
        {} // header
      )
    );

  const subscribe = () =>
    connection.then(() => (subscriber = stompClient.subscribe(`/topic/video-call/group/admin/*`, data => process(data))));

  const connect = () => {
    if (connectedPromise !== null || alreadyConnectedOnce) {
      // the connection is already being established
      return;
    }
    connection = createConnection();
    listener = createListener();

    // building absolute path so that websocket doesn't fail when deploying with a context path
    const loc = window.location;
    const baseHref = document.querySelector('base').getAttribute('href').replace(/\/$/, '');

    const headers = {};
    let url = '//' + loc.host + baseHref + '/websocket/video-call';
    if (authToken) url += '?access_token=' + authToken;

    const socket = new SockJS(url);
    stompClient = Stomp.over(socket, { protocols: ['v12.stomp'] });
    // stompClient.debug=()=>{};// don't show debug console log

    stompClient.connect(headers, () => {
      connectedPromise('success');
      connectedPromise = null;
      // sendActivity(); first message after connect success
      alreadyConnectedOnce = true;
    });
  };

  const receive = () => listener;

  const disconnect = () => {
    if (stompClient !== null) if (stompClient.connected) stompClient.disconnect();
    stompClient = null;
    alreadyConnectedOnce = false;
  };

  const unsubscribe = () => {
    if (subscriber !== null) subscriber.unsubscribe();
    listener = createListener();
  };

  useEffect(() => {
    mediaSource = new MediaSource();
    mediaSource.addEventListener('sourceopen', () => (sourceBuffer = mediaSource.addSourceBuffer('video/webm;codecs=vp8')));
    videoRef.current.src = window.URL.createObjectURL(mediaSource);
  }, [setCapturing]);

  const handleStartCaptureClick = useCallback(
    async type => {
      setCapturing(true);

      mediaSource = new MediaSource();
      if (type === 'camera') {
        mediaRecorderRef.current = new MediaRecorder(await navigator.mediaDevices.getUserMedia(videoConstraints), {
          mimeType: 'video/webm;codecs=vp8,opus',
        });
        mediaSource.addEventListener('sourceopen', () => (sourceBuffer = mediaSource.addSourceBuffer('video/webm;codecs=vp8,opus')));
      } else {
        // eslint-disable-next-line @typescript-eslint/ban-ts-comment
        // @ts-ignore
        mediaRecorderRef.current = new MediaRecorder(await navigator.mediaDevices.getDisplayMedia(displayConstraints), {
          audioBitsPerSecond: 128000,
          videoBitsPerSecond: 50000,
          mimeType: 'video/webm;codecs=vp8',
        });
        mediaSource.addEventListener('sourceopen', () => (sourceBuffer = mediaSource.addSourceBuffer('video/webm;codecs=vp8')));
      }
      webcamRef.current.srcObject = mediaRecorderRef.current.stream;
      // mediaRecorderRef.current = new MediaRecorder(webcamRef.current.stream, {mimeType: "video/webm"});
      // videoRef.current.srcObject=webcamRef.current.stream;
      mediaRecorderRef.current.ondataavailable = handleDataAvailable;
      mediaRecorderRef.current.start(200);
      videoRef.current.src = window.URL.createObjectURL(mediaSource);
    },
    [webcamRef, setCapturing, mediaRecorderRef]
  );

  const handleDataAvailable = useCallback(({ data }) => {
    if (data.size > 0) {
      const reader = new FileReader();
      reader.readAsDataURL(data);
      reader.onloadend = () => sendActivity(reader.result, 'chạy mẹ nó rồi');
    }
  }, []);

  const process = videoData => {
    log(props.account.login);
    const result = JSON.parse(videoData.body);
    let videoBase64 = result.data.split(',');
    videoBase64 = videoBase64[videoBase64.length - 1];
    const data = Uint8Array.from(atob(videoBase64), c => c.charCodeAt(0));
    sourceBuffer.appendBuffer(new Uint8Array(data));
  };

  const handleStopCaptureClick = useCallback(() => {
    mediaRecorderRef.current.stop();
    setCapturing(false);
  }, [mediaRecorderRef, webcamRef, setCapturing]);

  return (
    <div>
      <h1>chat</h1>
      {capturing ? (
        <button onClick={handleStopCaptureClick}>Stop Capture</button>
      ) : (
        <div>
          <button onClick={() => handleStartCaptureClick('camera')}>Start camera</button>
          <button onClick={() => handleStartCaptureClick('display')}>Start display</button>
          <button onClick={subscribe}>subscribe</button>
        </div>
      )}

      {capturing ? <video autoPlay playsInline muted loop controls preload={'none'} ref={webcamRef} /> : <div />}
      <video autoPlay playsInline muted loop controls preload={'none'} ref={videoRef} />
    </div>
  );
};

const log = x => window.console.log(x);

const mapStateToProps = ({ authentication }: IRootState) => ({
  account: authentication.account,
  isAuthenticated: authentication.isAuthenticated,
});

const mapDispatchToProps = { getSession, saveAccountSettings, reset };

export default reduxConnect(mapStateToProps, mapDispatchToProps)(VideoCall);
