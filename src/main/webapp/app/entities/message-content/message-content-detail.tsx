import React, { useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, UncontrolledTooltip, Row, Col } from 'reactstrap';
import { Translate, byteSize } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { getEntity } from './message-content.reducer';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const MessageContentDetail = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  useEffect(() => {
    dispatch(getEntity(props.match.params.id));
  }, []);

  const messageContentEntity = useAppSelector(state => state.messageContent.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="messageContentDetailsHeading">
          <Translate contentKey="catinyApp.messageContent.detail.title">MessageContent</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{messageContentEntity.id}</dd>
          <dt>
            <span id="uuid">
              <Translate contentKey="catinyApp.messageContent.uuid">Uuid</Translate>
            </span>
            <UncontrolledTooltip target="uuid">
              <Translate contentKey="catinyApp.messageContent.help.uuid" />
            </UncontrolledTooltip>
          </dt>
          <dd>{messageContentEntity.uuid}</dd>
          <dt>
            <span id="content">
              <Translate contentKey="catinyApp.messageContent.content">Content</Translate>
            </span>
          </dt>
          <dd>{messageContentEntity.content}</dd>
          <dt>
            <span id="status">
              <Translate contentKey="catinyApp.messageContent.status">Status</Translate>
            </span>
            <UncontrolledTooltip target="status">
              <Translate contentKey="catinyApp.messageContent.help.status" />
            </UncontrolledTooltip>
          </dt>
          <dd>{messageContentEntity.status}</dd>
          <dt>
            <span id="searchField">
              <Translate contentKey="catinyApp.messageContent.searchField">Search Field</Translate>
            </span>
            <UncontrolledTooltip target="searchField">
              <Translate contentKey="catinyApp.messageContent.help.searchField" />
            </UncontrolledTooltip>
          </dt>
          <dd>{messageContentEntity.searchField}</dd>
          <dt>
            <Translate contentKey="catinyApp.messageContent.baseInfo">Base Info</Translate>
          </dt>
          <dd>{messageContentEntity.baseInfo ? messageContentEntity.baseInfo.id : ''}</dd>
          <dt>
            <Translate contentKey="catinyApp.messageContent.sender">Sender</Translate>
          </dt>
          <dd>{messageContentEntity.sender ? messageContentEntity.sender.id : ''}</dd>
          <dt>
            <Translate contentKey="catinyApp.messageContent.messageGroup">Message Group</Translate>
          </dt>
          <dd>{messageContentEntity.messageGroup ? messageContentEntity.messageGroup.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/message-content" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/message-content/${messageContentEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default MessageContentDetail;
