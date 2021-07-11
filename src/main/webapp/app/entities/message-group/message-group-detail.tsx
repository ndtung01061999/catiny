import React, { useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, UncontrolledTooltip, Row, Col } from 'reactstrap';
import { Translate } from 'react-jhipster';
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
            <span id="groupName">
              <Translate contentKey="catinyApp.messageGroup.groupName">Group Name</Translate>
            </span>
            <UncontrolledTooltip target="groupName">
              <Translate contentKey="catinyApp.messageGroup.help.groupName" />
            </UncontrolledTooltip>
          </dt>
          <dd>{messageGroupEntity.groupName}</dd>
          <dt>
            <span id="addBy">
              <Translate contentKey="catinyApp.messageGroup.addBy">Add By</Translate>
            </span>
            <UncontrolledTooltip target="addBy">
              <Translate contentKey="catinyApp.messageGroup.help.addBy" />
            </UncontrolledTooltip>
          </dt>
          <dd>{messageGroupEntity.addBy}</dd>
          <dt>
            <Translate contentKey="catinyApp.messageGroup.baseInfo">Base Info</Translate>
          </dt>
          <dd>{messageGroupEntity.baseInfo ? messageGroupEntity.baseInfo.id : ''}</dd>
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
