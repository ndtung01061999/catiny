import React, { useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, UncontrolledTooltip, Row, Col } from 'reactstrap';
import { Translate, byteSize, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { getEntity } from './message-group.reducer';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const MessageGroupDetail = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  useEffect(() => {
    dispatch(getEntity(props.match.params.id));
  }, []);

  const messageGroupEntity = useAppSelector(state => state.messageGroup.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="messageGroupDetailsHeading">
          <Translate contentKey="catinyApp.messageGroup.detail.title">MessageGroup</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{messageGroupEntity.id}</dd>
          <dt>
            <span id="uuid">
              <Translate contentKey="catinyApp.messageGroup.uuid">Uuid</Translate>
            </span>
            <UncontrolledTooltip target="uuid">
              <Translate contentKey="catinyApp.messageGroup.help.uuid" />
            </UncontrolledTooltip>
          </dt>
          <dd>{messageGroupEntity.uuid}</dd>
          <dt>
            <span id="userId">
              <Translate contentKey="catinyApp.messageGroup.userId">User Id</Translate>
            </span>
          </dt>
          <dd>{messageGroupEntity.userId}</dd>
          <dt>
            <span id="groupId">
              <Translate contentKey="catinyApp.messageGroup.groupId">Group Id</Translate>
            </span>
          </dt>
          <dd>{messageGroupEntity.groupId}</dd>
          <dt>
            <span id="groupName">
              <Translate contentKey="catinyApp.messageGroup.groupName">Group Name</Translate>
            </span>
          </dt>
          <dd>{messageGroupEntity.groupName}</dd>
          <dt>
            <span id="addBy">
              <Translate contentKey="catinyApp.messageGroup.addBy">Add By</Translate>
            </span>
          </dt>
          <dd>{messageGroupEntity.addBy}</dd>
          <dt>
            <span id="lastContent">
              <Translate contentKey="catinyApp.messageGroup.lastContent">Last Content</Translate>
            </span>
          </dt>
          <dd>{messageGroupEntity.lastContent}</dd>
          <dt>
            <span id="searchField">
              <Translate contentKey="catinyApp.messageGroup.searchField">Search Field</Translate>
            </span>
            <UncontrolledTooltip target="searchField">
              <Translate contentKey="catinyApp.messageGroup.help.searchField" />
            </UncontrolledTooltip>
          </dt>
          <dd>{messageGroupEntity.searchField}</dd>
          <dt>
            <span id="role">
              <Translate contentKey="catinyApp.messageGroup.role">Role</Translate>
            </span>
            <UncontrolledTooltip target="role">
              <Translate contentKey="catinyApp.messageGroup.help.role" />
            </UncontrolledTooltip>
          </dt>
          <dd>{messageGroupEntity.role}</dd>
          <dt>
            <span id="createdDate">
              <Translate contentKey="catinyApp.messageGroup.createdDate">Created Date</Translate>
            </span>
            <UncontrolledTooltip target="createdDate">
              <Translate contentKey="catinyApp.messageGroup.help.createdDate" />
            </UncontrolledTooltip>
          </dt>
          <dd>
            {messageGroupEntity.createdDate ? (
              <TextFormat value={messageGroupEntity.createdDate} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="modifiedDate">
              <Translate contentKey="catinyApp.messageGroup.modifiedDate">Modified Date</Translate>
            </span>
            <UncontrolledTooltip target="modifiedDate">
              <Translate contentKey="catinyApp.messageGroup.help.modifiedDate" />
            </UncontrolledTooltip>
          </dt>
          <dd>
            {messageGroupEntity.modifiedDate ? (
              <TextFormat value={messageGroupEntity.modifiedDate} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="createdBy">
              <Translate contentKey="catinyApp.messageGroup.createdBy">Created By</Translate>
            </span>
            <UncontrolledTooltip target="createdBy">
              <Translate contentKey="catinyApp.messageGroup.help.createdBy" />
            </UncontrolledTooltip>
          </dt>
          <dd>{messageGroupEntity.createdBy}</dd>
          <dt>
            <span id="modifiedBy">
              <Translate contentKey="catinyApp.messageGroup.modifiedBy">Modified By</Translate>
            </span>
            <UncontrolledTooltip target="modifiedBy">
              <Translate contentKey="catinyApp.messageGroup.help.modifiedBy" />
            </UncontrolledTooltip>
          </dt>
          <dd>{messageGroupEntity.modifiedBy}</dd>
          <dt>
            <span id="comment">
              <Translate contentKey="catinyApp.messageGroup.comment">Comment</Translate>
            </span>
            <UncontrolledTooltip target="comment">
              <Translate contentKey="catinyApp.messageGroup.help.comment" />
            </UncontrolledTooltip>
          </dt>
          <dd>{messageGroupEntity.comment}</dd>
        </dl>
        <Button tag={Link} to="/message-group" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/message-group/${messageGroupEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default MessageGroupDetail;
