import React, {useEffect} from 'react';
import {Link, RouteComponentProps} from 'react-router-dom';
import {Button, Col, Row, UncontrolledTooltip} from 'reactstrap';
import {TextFormat, Translate} from 'react-jhipster';
import {FontAwesomeIcon} from '@fortawesome/react-fontawesome';

import {getEntity} from './message-content.reducer';
import {APP_DATE_FORMAT} from 'app/config/constants';
import {useAppDispatch, useAppSelector} from 'app/config/store';

export const MessageContentDetail = (props: RouteComponentProps<{ id: string }>) =>
{
  const dispatch = useAppDispatch();

  useEffect(() =>
  {
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
            <span id="groupId">
              <Translate contentKey="catinyApp.messageContent.groupId">Group Id</Translate>
            </span>
          </dt>
          <dd>{messageContentEntity.groupId}</dd>
          <dt>
            <span id="content">
              <Translate contentKey="catinyApp.messageContent.content">Content</Translate>
            </span>
          </dt>
          <dd>{messageContentEntity.content}</dd>
          <dt>
            <span id="sender">
              <Translate contentKey="catinyApp.messageContent.sender">Sender</Translate>
            </span>
          </dt>
          <dd>{messageContentEntity.sender}</dd>
          <dt>
            <span id="status">
              <Translate contentKey="catinyApp.messageContent.status">Status</Translate>
            </span>
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
            <span id="role">
              <Translate contentKey="catinyApp.messageContent.role">Role</Translate>
            </span>
            <UncontrolledTooltip target="role">
              <Translate contentKey="catinyApp.messageContent.help.role" />
            </UncontrolledTooltip>
          </dt>
          <dd>{messageContentEntity.role}</dd>
          <dt>
            <span id="createdDate">
              <Translate contentKey="catinyApp.messageContent.createdDate">Created Date</Translate>
            </span>
            <UncontrolledTooltip target="createdDate">
              <Translate contentKey="catinyApp.messageContent.help.createdDate" />
            </UncontrolledTooltip>
          </dt>
          <dd>
            {messageContentEntity.createdDate ? (
              <TextFormat value={messageContentEntity.createdDate} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="modifiedDate">
              <Translate contentKey="catinyApp.messageContent.modifiedDate">Modified Date</Translate>
            </span>
            <UncontrolledTooltip target="modifiedDate">
              <Translate contentKey="catinyApp.messageContent.help.modifiedDate" />
            </UncontrolledTooltip>
          </dt>
          <dd>
            {messageContentEntity.modifiedDate ? (
              <TextFormat value={messageContentEntity.modifiedDate} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="createdBy">
              <Translate contentKey="catinyApp.messageContent.createdBy">Created By</Translate>
            </span>
            <UncontrolledTooltip target="createdBy">
              <Translate contentKey="catinyApp.messageContent.help.createdBy" />
            </UncontrolledTooltip>
          </dt>
          <dd>{messageContentEntity.createdBy}</dd>
          <dt>
            <span id="modifiedBy">
              <Translate contentKey="catinyApp.messageContent.modifiedBy">Modified By</Translate>
            </span>
            <UncontrolledTooltip target="modifiedBy">
              <Translate contentKey="catinyApp.messageContent.help.modifiedBy" />
            </UncontrolledTooltip>
          </dt>
          <dd>{messageContentEntity.modifiedBy}</dd>
          <dt>
            <span id="comment">
              <Translate contentKey="catinyApp.messageContent.comment">Comment</Translate>
            </span>
            <UncontrolledTooltip target="comment">
              <Translate contentKey="catinyApp.messageContent.help.comment" />
            </UncontrolledTooltip>
          </dt>
          <dd>{messageContentEntity.comment}</dd>
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
