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
            <span id="width">
              <Translate contentKey="catinyApp.video.width">Width</Translate>
            </span>
            <UncontrolledTooltip target="width">
              <Translate contentKey="catinyApp.video.help.width" />
            </UncontrolledTooltip>
          </dt>
          <dd>{videoEntity.width}</dd>
          <dt>
            <span id="height">
              <Translate contentKey="catinyApp.video.height">Height</Translate>
            </span>
            <UncontrolledTooltip target="height">
              <Translate contentKey="catinyApp.video.help.height" />
            </UncontrolledTooltip>
          </dt>
          <dd>{videoEntity.height}</dd>
          <dt>
            <span id="qualityImage">
              <Translate contentKey="catinyApp.video.qualityImage">Quality Image</Translate>
            </span>
            <UncontrolledTooltip target="qualityImage">
              <Translate contentKey="catinyApp.video.help.qualityImage" />
            </UncontrolledTooltip>
          </dt>
          <dd>{videoEntity.qualityImage}</dd>
          <dt>
            <span id="qualityAudio">
              <Translate contentKey="catinyApp.video.qualityAudio">Quality Audio</Translate>
            </span>
            <UncontrolledTooltip target="qualityAudio">
              <Translate contentKey="catinyApp.video.help.qualityAudio" />
            </UncontrolledTooltip>
          </dt>
          <dd>{videoEntity.qualityAudio}</dd>
          <dt>
            <span id="quality">
              <Translate contentKey="catinyApp.video.quality">Quality</Translate>
            </span>
            <UncontrolledTooltip target="quality">
              <Translate contentKey="catinyApp.video.help.quality" />
            </UncontrolledTooltip>
          </dt>
          <dd>{videoEntity.quality}</dd>
          <dt>
            <span id="pixelSize">
              <Translate contentKey="catinyApp.video.pixelSize">Pixel Size</Translate>
            </span>
            <UncontrolledTooltip target="pixelSize">
              <Translate contentKey="catinyApp.video.help.pixelSize" />
            </UncontrolledTooltip>
          </dt>
          <dd>{videoEntity.pixelSize}</dd>
          <dt>
            <span id="priorityIndex">
              <Translate contentKey="catinyApp.video.priorityIndex">Priority Index</Translate>
            </span>
            <UncontrolledTooltip target="priorityIndex">
              <Translate contentKey="catinyApp.video.help.priorityIndex" />
            </UncontrolledTooltip>
          </dt>
          <dd>{videoEntity.priorityIndex}</dd>
          <dt>
            <span id="dataSize">
              <Translate contentKey="catinyApp.video.dataSize">Data Size</Translate>
            </span>
            <UncontrolledTooltip target="dataSize">
              <Translate contentKey="catinyApp.video.help.dataSize" />
            </UncontrolledTooltip>
          </dt>
          <dd>{videoEntity.dataSize}</dd>
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
