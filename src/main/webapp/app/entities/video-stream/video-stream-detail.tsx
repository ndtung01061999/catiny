import React, { useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, UncontrolledTooltip, Row, Col } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { getEntity } from './video-stream.reducer';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const VideoStreamDetail = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  useEffect(() => {
    dispatch(getEntity(props.match.params.id));
  }, []);

  const videoStreamEntity = useAppSelector(state => state.videoStream.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="videoStreamDetailsHeading">
          <Translate contentKey="catinyApp.videoStream.detail.title">VideoStream</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{videoStreamEntity.id}</dd>
          <dt>
            <span id="uuid">
              <Translate contentKey="catinyApp.videoStream.uuid">Uuid</Translate>
            </span>
            <UncontrolledTooltip target="uuid">
              <Translate contentKey="catinyApp.videoStream.help.uuid" />
            </UncontrolledTooltip>
          </dt>
          <dd>{videoStreamEntity.uuid}</dd>
          <dt>
            <Translate contentKey="catinyApp.videoStream.video">Video</Translate>
          </dt>
          <dd>{videoStreamEntity.video ? videoStreamEntity.video.id : ''}</dd>
          <dt>
            <Translate contentKey="catinyApp.videoStream.baseInfo">Base Info</Translate>
          </dt>
          <dd>{videoStreamEntity.baseInfo ? videoStreamEntity.baseInfo.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/video-stream" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/video-stream/${videoStreamEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default VideoStreamDetail;
