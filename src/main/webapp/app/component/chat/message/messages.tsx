import './messages.scss';
import React, {useState} from 'react';


import {IRootState} from 'app/shared/reducers';

import {connect as reduxConnect} from 'react-redux';
import {Col, Row} from 'reactstrap';
import {RouteComponentProps} from 'react-router-dom';
import MessageGroupComponent from "app/component/chat/message/message-group/message-group";
import MessageContentComponent from "app/component/chat/message/message-content/message-content";

const Messages = (props: IMessageProps) =>
{
  const [listGroupShowState, setListGroupShowState] = useState("");

  return (
    <div>
      <h1>messages </h1>
      <div onClick={() => setListGroupShowState(prev => prev === "" ? "d-none" : "")}>show list group</div>
      <Row className="messages ">
        <Col className={`message-group  col-12 col-md-3 ${listGroupShowState}`}>
          <MessageGroupComponent/>
        </Col>
        <Col className="message-content ">
          <MessageContentComponent/>
        </Col>
      </Row>
    </div>
  );
};


const log = x => window.console.log(x);

const mapStateToProps = (storeState: IRootState) => ({
  account: storeState.authentication.account,
  isAuthenticated: storeState.authentication.isAuthenticated,
});

const mapDispatchToProps = {
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export interface IMessageProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }>
{
}

export default reduxConnect(mapStateToProps, mapDispatchToProps)(Messages);
