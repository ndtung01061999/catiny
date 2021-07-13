import React, { useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, UncontrolledTooltip, Row, Col } from 'reactstrap';
import { Translate, openFile, byteSize } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { getEntity } from './video-live-stream-buffer.reducer';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const VideoLiveStreamBufferDetail = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  useEffect(() => {
    dispatch(getEntity(props.match.params.id));
  }, []);

  const videoLiveStreamBufferEntity = useAppSelector(state => state.videoLiveStreamBuffer.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="videoLiveStreamBufferDetailsHeading">
          <Translate contentKey="catinyApp.videoLiveStreamBuffer.detail.title">VideoLiveStreamBuffer</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{videoLiveStreamBufferEntity.id}</dd>
          <dt>
            <span id="uuid">
              <Translate contentKey="catinyApp.videoLiveStreamBuffer.uuid">Uuid</Translate>
            </span>
            <UncontrolledTooltip target="uuid">
              <Translate contentKey="catinyApp.videoLiveStreamBuffer.help.uuid" />
            </UncontrolledTooltip>
          </dt>
          <dd>{videoLiveStreamBufferEntity.uuid}</dd>
          <dt>
            <span id="bufferData">
              <Translate contentKey="catinyApp.videoLiveStreamBuffer.bufferData">Buffer Data</Translate>
            </span>
          </dt>
          <dd>
            {videoLiveStreamBufferEntity.bufferData ? (
              <div>
                {videoLiveStreamBufferEntity.bufferDataContentType ? (
                  <a onClick={openFile(videoLiveStreamBufferEntity.bufferDataContentType, videoLiveStreamBufferEntity.bufferData)}>
                    <Translate contentKey="entity.action.open">Open</Translate>&nbsp;
                  </a>
                ) : null}
                <span>
                  {videoLiveStreamBufferEntity.bufferDataContentType}, {byteSize(videoLiveStreamBufferEntity.bufferData)}
                </span>
              </div>
            ) : null}
          </dd>
          <dt>
            <Translate contentKey="catinyApp.videoLiveStreamBuffer.baseInfo">Base Info</Translate>
          </dt>
          <dd>{videoLiveStreamBufferEntity.baseInfo ? videoLiveStreamBufferEntity.baseInfo.id : ''}</dd>
          <dt>
            <Translate contentKey="catinyApp.videoLiveStreamBuffer.videoStream">Video Stream</Translate>
          </dt>
          <dd>{videoLiveStreamBufferEntity.videoStream ? videoLiveStreamBufferEntity.videoStream.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/video-live-stream-buffer" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/video-live-stream-buffer/${videoLiveStreamBufferEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default VideoLiveStreamBufferDetail;
