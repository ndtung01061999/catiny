import React, { useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, UncontrolledTooltip, Row, Col } from 'reactstrap';
import { Translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { getEntity } from './device-status.reducer';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const DeviceStatusDetail = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  useEffect(() => {
    dispatch(getEntity(props.match.params.id));
  }, []);

  const deviceStatusEntity = useAppSelector(state => state.deviceStatus.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="deviceStatusDetailsHeading">
          <Translate contentKey="catinyApp.deviceStatus.detail.title">DeviceStatus</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{deviceStatusEntity.id}</dd>
          <dt>
            <span id="uuid">
              <Translate contentKey="catinyApp.deviceStatus.uuid">Uuid</Translate>
            </span>
            <UncontrolledTooltip target="uuid">
              <Translate contentKey="catinyApp.deviceStatus.help.uuid" />
            </UncontrolledTooltip>
          </dt>
          <dd>{deviceStatusEntity.uuid}</dd>
          <dt>
            <span id="deviceName">
              <Translate contentKey="catinyApp.deviceStatus.deviceName">Device Name</Translate>
            </span>
            <UncontrolledTooltip target="deviceName">
              <Translate contentKey="catinyApp.deviceStatus.help.deviceName" />
            </UncontrolledTooltip>
          </dt>
          <dd>{deviceStatusEntity.deviceName}</dd>
          <dt>
            <span id="deviceType">
              <Translate contentKey="catinyApp.deviceStatus.deviceType">Device Type</Translate>
            </span>
            <UncontrolledTooltip target="deviceType">
              <Translate contentKey="catinyApp.deviceStatus.help.deviceType" />
            </UncontrolledTooltip>
          </dt>
          <dd>{deviceStatusEntity.deviceType}</dd>
          <dt>
            <span id="deviceStatus">
              <Translate contentKey="catinyApp.deviceStatus.deviceStatus">Device Status</Translate>
            </span>
            <UncontrolledTooltip target="deviceStatus">
              <Translate contentKey="catinyApp.deviceStatus.help.deviceStatus" />
            </UncontrolledTooltip>
          </dt>
          <dd>{deviceStatusEntity.deviceStatus}</dd>
          <dt>
            <span id="lastVisited">
              <Translate contentKey="catinyApp.deviceStatus.lastVisited">Last Visited</Translate>
            </span>
            <UncontrolledTooltip target="lastVisited">
              <Translate contentKey="catinyApp.deviceStatus.help.lastVisited" />
            </UncontrolledTooltip>
          </dt>
          <dd>
            {deviceStatusEntity.lastVisited ? (
              <TextFormat value={deviceStatusEntity.lastVisited} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="statusComment">
              <Translate contentKey="catinyApp.deviceStatus.statusComment">Status Comment</Translate>
            </span>
            <UncontrolledTooltip target="statusComment">
              <Translate contentKey="catinyApp.deviceStatus.help.statusComment" />
            </UncontrolledTooltip>
          </dt>
          <dd>{deviceStatusEntity.statusComment}</dd>
          <dt>
            <Translate contentKey="catinyApp.deviceStatus.baseInfo">Base Info</Translate>
          </dt>
          <dd>{deviceStatusEntity.baseInfo ? deviceStatusEntity.baseInfo.id : ''}</dd>
          <dt>
            <Translate contentKey="catinyApp.deviceStatus.accountStatus">Account Status</Translate>
          </dt>
          <dd>{deviceStatusEntity.accountStatus ? deviceStatusEntity.accountStatus.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/device-status" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/device-status/${deviceStatusEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default DeviceStatusDetail;
