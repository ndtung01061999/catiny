import React, { useState, useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, FormText, UncontrolledTooltip } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IFileInfo } from 'app/shared/model/file-info.model';
import { getEntities as getFileInfos } from 'app/entities/file-info/file-info.reducer';
import { IBaseInfo } from 'app/shared/model/base-info.model';
import { getEntities as getBaseInfos } from 'app/entities/base-info/base-info.reducer';
import { getEntities as getVideos } from 'app/entities/video/video.reducer';
import { IVideoStream } from 'app/shared/model/video-stream.model';
import { getEntities as getVideoStreams } from 'app/entities/video-stream/video-stream.reducer';
import { IEvent } from 'app/shared/model/event.model';
import { getEntities as getEvents } from 'app/entities/event/event.reducer';
import { getEntity, updateEntity, createEntity, reset } from './video.reducer';
import { IVideo } from 'app/shared/model/video.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const VideoUpdate = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  const [isNew] = useState(!props.match.params || !props.match.params.id);

  const fileInfos = useAppSelector(state => state.fileInfo.entities);
  const baseInfos = useAppSelector(state => state.baseInfo.entities);
  const videos = useAppSelector(state => state.video.entities);
  const videoStreams = useAppSelector(state => state.videoStream.entities);
  const events = useAppSelector(state => state.event.entities);
  const videoEntity = useAppSelector(state => state.video.entity);
  const loading = useAppSelector(state => state.video.loading);
  const updating = useAppSelector(state => state.video.updating);
  const updateSuccess = useAppSelector(state => state.video.updateSuccess);

  const handleClose = () => {
    props.history.push('/video');
  };

  useEffect(() => {
    if (!isNew) {
      dispatch(getEntity(props.match.params.id));
    }

    dispatch(getFileInfos({}));
    dispatch(getBaseInfos({}));
    dispatch(getVideos({}));
    dispatch(getVideoStreams({}));
    dispatch(getEvents({}));
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  const saveEntity = values => {
    const entity = {
      ...videoEntity,
      ...values,
      fileInfo: fileInfos.find(it => it.id.toString() === values.fileInfoId.toString()),
      baseInfo: baseInfos.find(it => it.id.toString() === values.baseInfoId.toString()),
      videoOriginal: videos.find(it => it.id.toString() === values.videoOriginalId.toString()),
      event: events.find(it => it.id.toString() === values.eventId.toString()),
    };

    if (isNew) {
      dispatch(createEntity(entity));
    } else {
      dispatch(updateEntity(entity));
    }
  };

  const defaultValues = () =>
    isNew
      ? {}
      : {
          ...videoEntity,
          fileInfoId: videoEntity?.fileInfo?.id,
          baseInfoId: videoEntity?.baseInfo?.id,
          videoOriginalId: videoEntity?.videoOriginal?.id,
          eventId: videoEntity?.event?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="catinyApp.video.home.createOrEditLabel" data-cy="VideoCreateUpdateHeading">
            <Translate contentKey="catinyApp.video.home.createOrEditLabel">Create or edit a Video</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <ValidatedForm defaultValues={defaultValues()} onSubmit={saveEntity}>
              {!isNew ? (
                <ValidatedField
                  name="id"
                  required
                  readOnly
                  id="video-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('catinyApp.video.uuid')}
                id="video-uuid"
                name="uuid"
                data-cy="uuid"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <UncontrolledTooltip target="uuidLabel">
                <Translate contentKey="catinyApp.video.help.uuid" />
              </UncontrolledTooltip>
              <ValidatedField label={translate('catinyApp.video.name')} id="video-name" name="name" data-cy="name" type="text" />
              <ValidatedField
                id="video-fileInfo"
                name="fileInfoId"
                data-cy="fileInfo"
                label={translate('catinyApp.video.fileInfo')}
                type="select"
              >
                <option value="" key="0" />
                {fileInfos
                  ? fileInfos.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField
                id="video-baseInfo"
                name="baseInfoId"
                data-cy="baseInfo"
                label={translate('catinyApp.video.baseInfo')}
                type="select"
              >
                <option value="" key="0" />
                {baseInfos
                  ? baseInfos.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField
                id="video-videoOriginal"
                name="videoOriginalId"
                data-cy="videoOriginal"
                label={translate('catinyApp.video.videoOriginal')}
                type="select"
              >
                <option value="" key="0" />
                {videos
                  ? videos.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField id="video-event" name="eventId" data-cy="event" label={translate('catinyApp.video.event')} type="select">
                <option value="" key="0" />
                {events
                  ? events.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/video" replace color="info">
                <FontAwesomeIcon icon="arrow-left" />
                &nbsp;
                <span className="d-none d-md-inline">
                  <Translate contentKey="entity.action.back">Back</Translate>
                </span>
              </Button>
              &nbsp;
              <Button color="primary" id="save-entity" data-cy="entityCreateSaveButton" type="submit" disabled={updating}>
                <FontAwesomeIcon icon="save" />
                &nbsp;
                <Translate contentKey="entity.action.save">Save</Translate>
              </Button>
            </ValidatedForm>
          )}
        </Col>
      </Row>
    </div>
  );
};

export default VideoUpdate;
