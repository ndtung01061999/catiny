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
              <ValidatedField label={translate('catinyApp.video.width')} id="video-width" name="width" data-cy="width" type="text" />
              <UncontrolledTooltip target="widthLabel">
                <Translate contentKey="catinyApp.video.help.width" />
              </UncontrolledTooltip>
              <ValidatedField label={translate('catinyApp.video.height')} id="video-height" name="height" data-cy="height" type="text" />
              <UncontrolledTooltip target="heightLabel">
                <Translate contentKey="catinyApp.video.help.height" />
              </UncontrolledTooltip>
              <ValidatedField
                label={translate('catinyApp.video.qualityImage')}
                id="video-qualityImage"
                name="qualityImage"
                data-cy="qualityImage"
                type="text"
                validate={{
                  min: { value: 0, message: translate('entity.validation.min', { min: 0 }) },
                  max: { value: 1, message: translate('entity.validation.max', { max: 1 }) },
                  validate: v => isNumber(v) || translate('entity.validation.number'),
                }}
              />
              <UncontrolledTooltip target="qualityImageLabel">
                <Translate contentKey="catinyApp.video.help.qualityImage" />
              </UncontrolledTooltip>
              <ValidatedField
                label={translate('catinyApp.video.qualityAudio')}
                id="video-qualityAudio"
                name="qualityAudio"
                data-cy="qualityAudio"
                type="text"
                validate={{
                  min: { value: 0, message: translate('entity.validation.min', { min: 0 }) },
                  max: { value: 1, message: translate('entity.validation.max', { max: 1 }) },
                  validate: v => isNumber(v) || translate('entity.validation.number'),
                }}
              />
              <UncontrolledTooltip target="qualityAudioLabel">
                <Translate contentKey="catinyApp.video.help.qualityAudio" />
              </UncontrolledTooltip>
              <ValidatedField
                label={translate('catinyApp.video.quality')}
                id="video-quality"
                name="quality"
                data-cy="quality"
                type="text"
                validate={{
                  min: { value: 0, message: translate('entity.validation.min', { min: 0 }) },
                  max: { value: 1, message: translate('entity.validation.max', { max: 1 }) },
                  validate: v => isNumber(v) || translate('entity.validation.number'),
                }}
              />
              <UncontrolledTooltip target="qualityLabel">
                <Translate contentKey="catinyApp.video.help.quality" />
              </UncontrolledTooltip>
              <ValidatedField
                label={translate('catinyApp.video.pixelSize')}
                id="video-pixelSize"
                name="pixelSize"
                data-cy="pixelSize"
                type="text"
              />
              <UncontrolledTooltip target="pixelSizeLabel">
                <Translate contentKey="catinyApp.video.help.pixelSize" />
              </UncontrolledTooltip>
              <ValidatedField
                label={translate('catinyApp.video.priorityIndex')}
                id="video-priorityIndex"
                name="priorityIndex"
                data-cy="priorityIndex"
                type="text"
              />
              <UncontrolledTooltip target="priorityIndexLabel">
                <Translate contentKey="catinyApp.video.help.priorityIndex" />
              </UncontrolledTooltip>
              <ValidatedField
                label={translate('catinyApp.video.dataSize')}
                id="video-dataSize"
                name="dataSize"
                data-cy="dataSize"
                type="text"
              />
              <UncontrolledTooltip target="dataSizeLabel">
                <Translate contentKey="catinyApp.video.help.dataSize" />
              </UncontrolledTooltip>
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
