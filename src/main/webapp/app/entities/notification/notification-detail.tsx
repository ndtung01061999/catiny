import React, { useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, UncontrolledTooltip, Row, Col } from 'reactstrap';
import { Translate, byteSize } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { getEntity } from './notification.reducer';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const NotificationDetail = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  useEffect(() => {
    dispatch(getEntity(props.match.params.id));
  }, []);

  const notificationEntity = useAppSelector(state => state.notification.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="notificationDetailsHeading">
          <Translate contentKey="catinyApp.notification.detail.title">Notification</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{notificationEntity.id}</dd>
          <dt>
            <span id="uuid">
              <Translate contentKey="catinyApp.notification.uuid">Uuid</Translate>
            </span>
            <UncontrolledTooltip target="uuid">
              <Translate contentKey="catinyApp.notification.help.uuid" />
            </UncontrolledTooltip>
          </dt>
          <dd>{notificationEntity.uuid}</dd>
          <dt>
            <span id="notifyType">
              <Translate contentKey="catinyApp.notification.notifyType">Notify Type</Translate>
            </span>
            <UncontrolledTooltip target="notifyType">
              <Translate contentKey="catinyApp.notification.help.notifyType" />
            </UncontrolledTooltip>
          </dt>
          <dd>{notificationEntity.notifyType}</dd>
          <dt>
            <span id="title">
              <Translate contentKey="catinyApp.notification.title">Title</Translate>
            </span>
          </dt>
          <dd>{notificationEntity.title}</dd>
          <dt>
            <span id="content">
              <Translate contentKey="catinyApp.notification.content">Content</Translate>
            </span>
          </dt>
          <dd>{notificationEntity.content}</dd>
          <dt>
            <Translate contentKey="catinyApp.notification.baseInfo">Base Info</Translate>
          </dt>
          <dd>{notificationEntity.baseInfo ? notificationEntity.baseInfo.id : ''}</dd>
          <dt>
            <Translate contentKey="catinyApp.notification.masterUser">Master User</Translate>
          </dt>
          <dd>{notificationEntity.masterUser ? notificationEntity.masterUser.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/notification" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/notification/${notificationEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default NotificationDetail;
