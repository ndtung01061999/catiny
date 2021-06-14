import React, {useEffect, useRef, useState} from 'react';
import {connect} from 'react-redux';
import {Storage} from 'react-jhipster';

import {IRootState} from 'app/shared/reducers';

import Media from 'reactstrap/es/Media';
import {FontAwesomeIcon} from '@fortawesome/react-fontawesome';
import {faPhone, faVideo} from '@fortawesome/free-solid-svg-icons';
import Button from 'reactstrap/es/Button';
import Input from 'reactstrap/es/Input';
import {connect as wsConnect, sendData as wsSendData, subscribe as wsSubscribe} from "app/component/chat/process/websocket-util";
import {getContentInGroup, messageUserNewMessage} from "app/component/chat/message/message-content/message-content.reducer";
import {RouteComponentProps} from 'react-router-dom';
import {IMessageContent} from 'app/shared/model/message-content.model';
import dayjs from 'dayjs'
import utc from "dayjs/plugin/utc";

dayjs.extend(utc)

const MessageContentComponent = (props: IMessageContentProps) =>
{
  const ITEMS_PER_PAGE = 5;
  const {messageContentList} = props;
  const [groupIdCurrent, setGroupIdCurrent] = useState(props.match.params.groupId);

  const [contentTyping, setContentTyping] = useState("");
  const [pageCurrent, setPageCurrent] = useState(0);
  const messageContentRef = useRef(null);
  const [messageContentScrollCurrent, setMessageContentScrollCurrent] = useState(Number.MAX_SAFE_INTEGER);
  const authToken = Storage.local.get('jhi-authenticationToken') || Storage.session.get('jhi-authenticationToken');


  useEffect(() =>
  {
    wsConnect();
    wsSubscribe("message/user/admin/group/new-message", data =>
    {
      props.messageUserNewMessage(data);
      messageContentRef.current.scrollTo(0, Number.MAX_SAFE_INTEGER);
    });
  }, []);

  useEffect(() =>
  {
    const scrollCurrent = messageContentRef.current.scrollHeight - messageContentScrollCurrent;
    if (messageContentRef.current.scrollTop < 500)
      messageContentRef.current.scrollTo(0, scrollCurrent);
    else
      messageContentRef.current.scrollTo(0, Number.MAX_SAFE_INTEGER);
    setMessageContentScrollCurrent(messageContentRef.current.scrollHeight);
  }, [messageContentList]);

  useEffect(() =>
  {
    setPageCurrent(0);
    setGroupIdCurrent(props.match.params.groupId);
    props.getContentInGroup(props.match.params.groupId, 0, ITEMS_PER_PAGE * 3);
    setPageCurrent(prev => prev + 3);
  }, [props.match.params.groupId]);


  // function support

  const time = (messageContent: IMessageContent) =>
  {
    return dayjs(new Date(Date.now() - new Date(messageContent.createdDate).getTime())).utc().format("HH:MM");
  }


  // Handle

  const handleLoadMore = (event) =>
  {
    const target = event.target;
    if (event.target.scrollTop === 0 && target.scrollHeight > target.offsetHeight)
    {
      props.getContentInGroup(groupIdCurrent, pageCurrent, ITEMS_PER_PAGE);
      setPageCurrent(prev => ++prev);
    }
  };

  const handleSend = () =>
  {
    const body = {content: contentTyping, groupId: groupIdCurrent};
    if (contentTyping && /[^ ]+/.exec(contentTyping))
      wsSendData("message/send", body, {});
    else
      window.console.log("nhếch nhêch");
    setContentTyping("");
  }
// Component TSX

  const messageContentsComponent = messageContent =>
  {
    const isSender = props.account.login === messageContent.sender;
    const thisUserIsSender = () => (
      <>
        <Media body>
          <div className="d-flex justify-content-end ">
            <span className="alert-info">{messageContent.content}</span>
          </div>
          <p className="d-flex justify-content-end ">{time(messageContent)}</p>
        </Media>
        <Media object className="mr-2" alt="Generic placeholder image" src="https://i.pinimg.com/564x/e1/24/04/e124041a63e3a93437603fbb93255169.jpg"/>
      </>
    );
    const thisUserNotIsSender = () => (
      <>
        <Media object className="mr-2" alt="Generic placeholder image"
               src="data:image/jpeg;base64,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"/>
        <Media body>
          <div className="d-flex justify-content-start">
            <span className="alert-info">{messageContent.content}</span>
          </div>
          <p className="d-flex justify-content-start">{time(messageContent)}</p>
        </Media>
      </>
    );
    return (
      <Media className={isSender ? 'justify-content-end' : 'justify-content-start'}>
        {isSender ? thisUserIsSender() : thisUserNotIsSender()}
      </Media>
    );
  };


  return (
    <div>
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
      <Media list className="pl-0 display-block  ">
        <div onScroll={handleLoadMore} className="pre-scrollable" ref={messageContentRef}>
          {messageContentList
            ? (messageContentList.map(messageContent => <>{messageContentsComponent(messageContent)}</>))
            : (<></>)}
        </div>

      </Media>
      <div className="d-flex">
        <Input className="col" type={'text'} value={contentTyping} onChange={(event) => setContentTyping(event.target.value)} onKeyUp={(event) =>
        {
          if (event.key === "Enter") handleSend()
        }}/>
        <Button className="" type="submit" onClick={handleSend}>Send</Button>
      </div>
    </div>
  );
};

const mapStateToProps = ({messageGroupComponent, messageContentComponent, authentication}: IRootState) => ({
  groupIdCurrent: messageGroupComponent.groupIdCurrent,
  messageContentList: messageContentComponent.messageContentList,
  account: authentication.account,
  isAuthenticated: authentication.isAuthenticated,
});

const mapDispatchToProps = {
  getContentInGroup,
  messageUserNewMessage
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export interface IMessageContentProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string, groupId: string }>
{
}

export default connect(mapStateToProps, mapDispatchToProps)(MessageContentComponent);
