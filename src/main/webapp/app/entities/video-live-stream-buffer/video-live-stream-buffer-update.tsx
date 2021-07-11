import React, { useState, useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, FormText, UncontrolledTooltip } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm, ValidatedBlobField } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IBaseInfo } from 'app/shared/model/base-info.model';
import { getEntities as getBaseInfos } from 'app/entities/base-info/base-info.reducer';
import { IVideoStream } from 'app/shared/model/video-stream.model';
import { getEntities as getVideoStreams } from 'app/entities/video-stream/video-stream.reducer';
import { getEntity, updateEntity, createEntity, reset } from './video-live-stream-buffer.reducer';
import { IVideoLiveStreamBuffer } from 'app/shared/model/video-live-stream-buffer.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const VideoLiveStreamBufferUpdate = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  const [isNew] = useState(!props.match.params || !props.match.params.id);

  const baseInfos = useAppSelector(state => state.baseInfo.entities);
  const videoStreams = useAppSelector(state => state.videoStream.entities);
  const videoLiveStreamBufferEntity = useAppSelector(state => state.videoLiveStreamBuffer.entity);
  const loading = useAppSelector(state => state.videoLiveStreamBuffer.loading);
  const updating = useAppSelector(state => state.videoLiveStreamBuffer.updating);
  const updateSuccess = useAppSelector(state => state.videoLiveStreamBuffer.updateSuccess);

  const handleClose = () => {
    props.history.push('/video-live-stream-buffer');
  };

  useEffect(() => {
    if (!isNew) {
      dispatch(getEntity(props.match.params.id));
    }

    dispatch(getBaseInfos({}));
    dispatch(getVideoStreams({}));
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  const saveEntity = values => {
    const entity = {
      ...videoLiveStreamBufferEntity,
      ...values,
      baseInfo: baseInfos.find(it => it.id.toString() === values.baseInfoId.toString()),
      videoStream: videoStreams.find(it => it.id.toString() === values.videoStreamId.toString()),
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
          ...videoLiveStreamBufferEntity,
          baseInfoId: videoLiveStreamBufferEntity?.baseInfo?.id,
          videoStreamId: videoLiveStreamBufferEntity?.videoStream?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="catinyApp.videoLiveStreamBuffer.home.createOrEditLabel" data-cy="VideoLiveStreamBufferCreateUpdateHeading">
            <Translate contentKey="catinyApp.videoLiveStreamBuffer.home.createOrEditLabel">
              Create or edit a VideoLiveStreamBuffer
            </Translate>
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
                  id="video-live-stream-buffer-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('catinyApp.videoLiveStreamBuffer.uuid')}
                id="video-live-stream-buffer-uuid"
                name="uuid"
                data-cy="uuid"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <UncontrolledTooltip target="uuidLabel">
                <Translate contentKey="catinyApp.videoLiveStreamBuffer.help.uuid" />
              </UncontrolledTooltip>
              <ValidatedBlobField
                label={translate('catinyApp.videoLiveStreamBuffer.bufferData')}
                id="video-live-stream-buffer-bufferData"
                name="bufferData"
                data-cy="bufferData"
                openActionLabel={translate('entity.action.open')}
              />
              <ValidatedField
                id="video-live-stream-buffer-baseInfo"
                name="baseInfoId"
                data-cy="baseInfo"
                label={translate('catinyApp.videoLiveStreamBuffer.baseInfo')}
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
                id="video-live-stream-buffer-videoStream"
                name="videoStreamId"
                data-cy="videoStream"
                label={translate('catinyApp.videoLiveStreamBuffer.videoStream')}
                type="select"
              >
                <option value="" key="0" />
                {videoStreams
                  ? videoStreams.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/video-live-stream-buffer" replace color="info">
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

export default VideoLiveStreamBufferUpdate;
