import React, { useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, UncontrolledTooltip, Row, Col } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { getEntity } from './video.reducer';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const VideoDetail = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  useEffect(() => {
    dispatch(getEntity(props.match.params.id));
  }, []);

  const videoEntity = useAppSelector(state => state.video.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="videoDetailsHeading">
          <Translate contentKey="catinyApp.video.detail.title">Video</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{videoEntity.id}</dd>
          <dt>
            <span id="uuid">
              <Translate contentKey="catinyApp.video.uuid">Uuid</Translate>
            </span>
            <UncontrolledTooltip target="uuid">
              <Translate contentKey="catinyApp.video.help.uuid" />
            </UncontrolledTooltip>
          </dt>
          <dd>{videoEntity.uuid}</dd>
          <dt>
            <span id="name">
              <Translate contentKey="catinyApp.video.name">Name</Translate>
            </span>
          </dt>
          <dd>{videoEntity.name}</dd>
          <dt>
            <Translate contentKey="catinyApp.video.fileInfo">File Info</Translate>
          </dt>
          <dd>{videoEntity.fileInfo ? videoEntity.fileInfo.id : ''}</dd>
          <dt>
            <Translate contentKey="catinyApp.video.baseInfo">Base Info</Translate>
          </dt>
          <dd>{videoEntity.baseInfo ? videoEntity.baseInfo.id : ''}</dd>
          <dt>
            <Translate contentKey="catinyApp.video.videoOriginal">Video Original</Translate>
          </dt>
          <dd>{videoEntity.videoOriginal ? videoEntity.videoOriginal.id : ''}</dd>
          <dt>
            <Translate contentKey="catinyApp.video.event">Event</Translate>
          </dt>
          <dd>{videoEntity.event ? videoEntity.event.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/video" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/video/${videoEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default VideoDetail;
