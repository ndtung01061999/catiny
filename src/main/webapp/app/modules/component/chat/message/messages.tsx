import '../messages.scss';
import React, {useEffect, useState} from 'react';
import {getSortState, Storage} from 'react-jhipster';
import {Observable} from 'rxjs';
import SockJS from 'sockjs-client';
import Stomp from 'webstomp-client';
import InfiniteScroll from 'react-infinite-scroller';

import {IRootState} from 'app/shared/reducers';
import {getSession} from 'app/shared/reducers/authentication';
import {reset} from 'app/modules/account/settings/settings.reducer';
import {connect as reduxConnect} from 'react-redux';
import {v4} from 'uuid';
import {Col, Input, Media, Row} from 'reactstrap';
import {FontAwesomeIcon} from '@fortawesome/react-fontawesome';
import {faCircle, faPhone, faPlusCircle, faVideo} from '@fortawesome/free-solid-svg-icons';
import {overridePaginationStateWithQueryParams} from 'app/shared/util/entity-utils';
import {getAllGroupsJoined, getContentInGroup, getEntities, getSearchEntities} from 'app/modules/component/chat/message/messages.reducer';
import {RouteComponentProps} from 'react-router-dom';

let stompClient = null;
let subscriber = null;
let connection: Promise<any>;
let connectedPromise: any = null;
let listener: Observable<any>;
let listenerObserver: any;
let alreadyConnectedOnce = false;
const uuid = v4();

const Messages = (props: IMessageProps) =>
{
  const [search, setSearch] = useState('');
  const [paginationState, setPaginationState] = useState(
    overridePaginationStateWithQueryParams(getSortState(props.location, 5, 'id'), props.location.search)
  );
  const [sorting, setSorting] = useState(false);

  const getAllEntities = () =>
  {
    // props.getContentInGroup("2a61ba51e16ab0f0d5705c01c393cfac",0,2);
    props.getAllGroupsJoined(
      0,
      3,
    );

  };
  useEffect(() =>
  {
    getAllEntities();
  }, [paginationState.activePage]);
  useEffect(() =>
  {
    if (sorting)
    {
      getAllEntities();
      setSorting(false);
    }
  }, [sorting, search]);

  useEffect(() =>
  {
    connect();
    subscribe().then();
  }, []);

  useEffect(() =>
  {
    props.getSession();
    return () =>
    {
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
        JSON.stringify({data, details: uuid}), // body
        {} // header
      )
    );

  const subscribe = () =>
    connection.then(
      () => (subscriber = stompClient.subscribe(`/topic/video-call/group/${props.account.login}/${uuid}`, data => process(data)))
    );

  const connect = () =>
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
    let url = '//' + loc.host + baseHref + '/websocket/video-call';
    if (authToken) url += '?access_token=' + authToken;

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
    if (stompClient !== null) if (stompClient.connected) stompClient.disconnect();
    stompClient = null;
    alreadyConnectedOnce = false;
  };

  const unsubscribe = () =>
  {
    if (subscriber !== null) subscriber.unsubscribe();
    listener = createListener();
  };

  const process = videoData =>
  {
    log(props.account.login);
    const result = JSON.parse(videoData.body);
    let videoBase64 = result.data.split(',');
    videoBase64 = videoBase64[videoBase64.length - 1];
    const data = Uint8Array.from(atob(videoBase64), c => c.charCodeAt(0));
  };

  const time = (lastTimeVisit) => new Date(Date.now() - new Date(lastTimeVisit).getTime()).toLocaleTimeString();

  const {messageGroupList, messageContentList} = props;
  const handleLoadMore = () =>
  {
    if ((window as any).pageYOffset > 0)
    {
      setPaginationState({
        ...paginationState,
        activePage: paginationState.activePage + 1,
      });
    }
  };

  const handleClickToGroup = (groupId) =>
  {
    props.getContentInGroup(groupId, 0, 2);
    // props.getAllGroupsJoined(0,3,null)
  }


  const userStatusComponent = messageGroup => (
    <a id={messageGroup.groupId} onClick={() => handleClickToGroup(messageGroup.groupId)}>
      <Media className="mb-2">
        <Media left href="#">
          <Media
            className="mr-2"
            object
            src="data:image/jpeg;base64,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"
            alt="Generic placeholder image"
          />
        </Media>
        <Media body>
          <Media heading>{messageGroup.groupName}</Media>
          <div>
            <span className="nowrap text-nowrap">{messageGroup.lastContent}</span><i
            className="d-none d-md-inline-block">{time(messageGroup.modifiedDate)}</i>
          </div>
        </Media>
        <FontAwesomeIcon className="text-primary mt-3 d-none d-md-inline-block " icon={faCircle} size="xs"></FontAwesomeIcon>
      </Media>
    </a>
  );

  const message = messageContent =>
  {
    return (
      <Media className={props.account.login === messageContent.sender ? 'justify-content-end' : 'justify-content-start'}>
        <Media
          className="mr-2"
          object
          src="data:image/jpeg;base64,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"
          alt="Generic placeholder image"
        />
        <Media body>{messageContent.content}</Media>
        <p>{messageContent.createdDate}</p>
      </Media>
    );
  };


  return (
    <div>
      <h1>messages </h1>
      <Row className="messages ">
        <Col className="left  col-3 alert-danger">
          <div className="d-flex justify-content-between my-2">
            <h5>follow status</h5>
            <FontAwesomeIcon icon={faPlusCircle}/>
          </div>

          <div className="display-block pre-scrollable">
            {messageGroupList ? (
              <InfiniteScroll
                pageStart={paginationState.activePage}
                loadMore={handleLoadMore}
                hasMore={paginationState.activePage - 1 < props.links.next}
                loader={<div className="loader">Loading ...</div>}
                threshold={0}
                initialLoad={false}
              >
                {messageGroupList.map(messageGroup => (
                  <>{userStatusComponent(messageGroup)}</>
                ))}
              </InfiniteScroll>
            ) : (
              <></>
            )}
          </div>
        </Col>
        <Col className="right alert-dark">
          <div className="d-flex justify-content-between pt-2">
            <div className="d-inline">
              <Media className="pl-0 display-block ">
                <Media className="justify-content-start">
                  <Media
                    className="mr-2"
                    object
                    src="data:image/jpeg;base64,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"
                    alt="Generic placeholder image"
                  />
                  <Media body>
                    <Media heading>{props.account.login}</Media>
                  </Media>
                </Media>
              </Media>
            </div>
            <Media className="justify-content-end">
              <FontAwesomeIcon className="m-1" icon={faPhone}/>
              <FontAwesomeIcon className="m-1" icon={faVideo}/>
            </Media>
          </div>
          <Media list className="pl-0 display-block pre-scrollable vh-100">
            {messageContentList ? (
              messageContentList.map(messageContent => <>{message(messageContent)}</>)
            ) : (
              <></>
            )}
          </Media>

          <Input className="" type={'text'}></Input>
        </Col>
      </Row>
    </div>
  );
};

const log = x => window.console.log(x);

const mapStateToProps = (storeState: IRootState) => ({
  account: storeState.authentication.account,
  isAuthenticated: storeState.authentication.isAuthenticated,
  messageGroupList: storeState.messageComponent.messageGroupList,
  messageContentList: storeState.messageComponent.messageContentList,
  links: storeState.messageComponent.links,
});

const mapDispatchToProps = {
  getSession,
  reset,
  getSearchEntities,
  getEntities,
  getAllGroupsJoined,
  getContentInGroup,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export interface IMessageProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }>
{
}

export default reduxConnect(mapStateToProps, mapDispatchToProps)(Messages);
