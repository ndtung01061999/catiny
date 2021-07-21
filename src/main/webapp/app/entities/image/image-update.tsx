import React, { useState, useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, FormText, UncontrolledTooltip } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IFileInfo } from 'app/shared/model/file-info.model';
import { getEntities as getFileInfos } from 'app/entities/file-info/file-info.reducer';
import { IBaseInfo } from 'app/shared/model/base-info.model';
import { getEntities as getBaseInfos } from 'app/entities/base-info/base-info.reducer';
import { getEntities as getImages } from 'app/entities/image/image.reducer';
import { IAlbum } from 'app/shared/model/album.model';
import { getEntities as getAlbums } from 'app/entities/album/album.reducer';
import { getEntity, updateEntity, createEntity, reset } from './image.reducer';
import { IImage } from 'app/shared/model/image.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const ImageUpdate = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  const [isNew] = useState(!props.match.params || !props.match.params.id);

  const fileInfos = useAppSelector(state => state.fileInfo.entities);
  const baseInfos = useAppSelector(state => state.baseInfo.entities);
  const images = useAppSelector(state => state.image.entities);
  const albums = useAppSelector(state => state.album.entities);
  const imageEntity = useAppSelector(state => state.image.entity);
  const loading = useAppSelector(state => state.image.loading);
  const updating = useAppSelector(state => state.image.updating);
  const updateSuccess = useAppSelector(state => state.image.updateSuccess);

  const handleClose = () => {
    props.history.push('/image');
  };

  useEffect(() => {
    if (!isNew) {
      dispatch(getEntity(props.match.params.id));
    }

    dispatch(getFileInfos({}));
    dispatch(getBaseInfos({}));
    dispatch(getImages({}));
    dispatch(getAlbums({}));
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  const saveEntity = values => {
    const entity = {
      ...imageEntity,
      ...values,
      fileInfo: fileInfos.find(it => it.id.toString() === values.fileInfoId.toString()),
      baseInfo: baseInfos.find(it => it.id.toString() === values.baseInfoId.toString()),
      imageOriginal: images.find(it => it.id.toString() === values.imageOriginalId.toString()),
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
          ...imageEntity,
          fileInfoId: imageEntity?.fileInfo?.id,
          baseInfoId: imageEntity?.baseInfo?.id,
          imageOriginalId: imageEntity?.imageOriginal?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="catinyApp.image.home.createOrEditLabel" data-cy="ImageCreateUpdateHeading">
            <Translate contentKey="catinyApp.image.home.createOrEditLabel">Create or edit a Image</Translate>
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
                  id="image-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('catinyApp.image.uuid')}
                id="image-uuid"
                name="uuid"
                data-cy="uuid"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <UncontrolledTooltip target="uuidLabel">
                <Translate contentKey="catinyApp.image.help.uuid" />
              </UncontrolledTooltip>
              <ValidatedField label={translate('catinyApp.image.name')} id="image-name" name="name" data-cy="name" type="text" />
              <UncontrolledTooltip target="nameLabel">
                <Translate contentKey="catinyApp.image.help.name" />
              </UncontrolledTooltip>
              <ValidatedField label={translate('catinyApp.image.width')} id="image-width" name="width" data-cy="width" type="text" />
              <UncontrolledTooltip target="widthLabel">
                <Translate contentKey="catinyApp.image.help.width" />
              </UncontrolledTooltip>
              <ValidatedField label={translate('catinyApp.image.height')} id="image-height" name="height" data-cy="height" type="text" />
              <UncontrolledTooltip target="heightLabel">
                <Translate contentKey="catinyApp.image.help.height" />
              </UncontrolledTooltip>
              <ValidatedField
                label={translate('catinyApp.image.quality')}
                id="image-quality"
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
                <Translate contentKey="catinyApp.image.help.quality" />
              </UncontrolledTooltip>
              <ValidatedField
                label={translate('catinyApp.image.pixelSize')}
                id="image-pixelSize"
                name="pixelSize"
                data-cy="pixelSize"
                type="text"
              />
              <UncontrolledTooltip target="pixelSizeLabel">
                <Translate contentKey="catinyApp.image.help.pixelSize" />
              </UncontrolledTooltip>
              <ValidatedField
                label={translate('catinyApp.image.priorityIndex')}
                id="image-priorityIndex"
                name="priorityIndex"
                data-cy="priorityIndex"
                type="text"
              />
              <UncontrolledTooltip target="priorityIndexLabel">
                <Translate contentKey="catinyApp.image.help.priorityIndex" />
              </UncontrolledTooltip>
              <ValidatedField
                label={translate('catinyApp.image.dataSize')}
                id="image-dataSize"
                name="dataSize"
                data-cy="dataSize"
                type="text"
              />
              <UncontrolledTooltip target="dataSizeLabel">
                <Translate contentKey="catinyApp.image.help.dataSize" />
              </UncontrolledTooltip>
              <ValidatedField
                id="image-fileInfo"
                name="fileInfoId"
                data-cy="fileInfo"
                label={translate('catinyApp.image.fileInfo')}
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
                id="image-baseInfo"
                name="baseInfoId"
                data-cy="baseInfo"
                label={translate('catinyApp.image.baseInfo')}
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
                id="image-imageOriginal"
                name="imageOriginalId"
                data-cy="imageOriginal"
                label={translate('catinyApp.image.imageOriginal')}
                type="select"
              >
                <option value="" key="0" />
                {images
                  ? images.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/image" replace color="info">
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

export default ImageUpdate;
