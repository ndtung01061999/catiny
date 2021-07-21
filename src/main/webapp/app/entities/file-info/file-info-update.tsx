import React, { useState, useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, FormText, UncontrolledTooltip } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IBaseInfo } from 'app/shared/model/base-info.model';
import { getEntities as getBaseInfos } from 'app/entities/base-info/base-info.reducer';
import { getEntity, updateEntity, createEntity, reset } from './file-info.reducer';
import { IFileInfo } from 'app/shared/model/file-info.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const FileInfoUpdate = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  const [isNew] = useState(!props.match.params || !props.match.params.id);

  const baseInfos = useAppSelector(state => state.baseInfo.entities);
  const fileInfoEntity = useAppSelector(state => state.fileInfo.entity);
  const loading = useAppSelector(state => state.fileInfo.loading);
  const updating = useAppSelector(state => state.fileInfo.updating);
  const updateSuccess = useAppSelector(state => state.fileInfo.updateSuccess);

  const handleClose = () => {
    props.history.push('/file-info');
  };

  useEffect(() => {
    if (!isNew) {
      dispatch(getEntity(props.match.params.id));
    }

    dispatch(getBaseInfos({}));
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  const saveEntity = values => {
    const entity = {
      ...fileInfoEntity,
      ...values,
      baseInfo: baseInfos.find(it => it.id.toString() === values.baseInfoId.toString()),
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
          ...fileInfoEntity,
          baseInfoId: fileInfoEntity?.baseInfo?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="catinyApp.fileInfo.home.createOrEditLabel" data-cy="FileInfoCreateUpdateHeading">
            <Translate contentKey="catinyApp.fileInfo.home.createOrEditLabel">Create or edit a FileInfo</Translate>
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
                  id="file-info-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('catinyApp.fileInfo.uuid')}
                id="file-info-uuid"
                name="uuid"
                data-cy="uuid"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <UncontrolledTooltip target="uuidLabel">
                <Translate contentKey="catinyApp.fileInfo.help.uuid" />
              </UncontrolledTooltip>
              <ValidatedField
                label={translate('catinyApp.fileInfo.nameFile')}
                id="file-info-nameFile"
                name="nameFile"
                data-cy="nameFile"
                type="text"
              />
              <UncontrolledTooltip target="nameFileLabel">
                <Translate contentKey="catinyApp.fileInfo.help.nameFile" />
              </UncontrolledTooltip>
              <ValidatedField
                label={translate('catinyApp.fileInfo.typeFile')}
                id="file-info-typeFile"
                name="typeFile"
                data-cy="typeFile"
                type="text"
              />
              <UncontrolledTooltip target="typeFileLabel">
                <Translate contentKey="catinyApp.fileInfo.help.typeFile" />
              </UncontrolledTooltip>
              <ValidatedField
                label={translate('catinyApp.fileInfo.path')}
                id="file-info-path"
                name="path"
                data-cy="path"
                type="text"
                validate={{
                  maxLength: { value: 1024, message: translate('entity.validation.maxlength', { max: 1024 }) },
                }}
              />
              <UncontrolledTooltip target="pathLabel">
                <Translate contentKey="catinyApp.fileInfo.help.path" />
              </UncontrolledTooltip>
              <ValidatedField
                label={translate('catinyApp.fileInfo.dataSize')}
                id="file-info-dataSize"
                name="dataSize"
                data-cy="dataSize"
                type="text"
              />
              <UncontrolledTooltip target="dataSizeLabel">
                <Translate contentKey="catinyApp.fileInfo.help.dataSize" />
              </UncontrolledTooltip>
              <ValidatedField
                id="file-info-baseInfo"
                name="baseInfoId"
                data-cy="baseInfo"
                label={translate('catinyApp.fileInfo.baseInfo')}
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
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/file-info" replace color="info">
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

export default FileInfoUpdate;
