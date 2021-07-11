import React, { useState, useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, FormText, UncontrolledTooltip } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IBaseInfo } from 'app/shared/model/base-info.model';
import { getEntities as getBaseInfos } from 'app/entities/base-info/base-info.reducer';
import { IAccountStatus } from 'app/shared/model/account-status.model';
import { getEntities as getAccountStatuses } from 'app/entities/account-status/account-status.reducer';
import { getEntity, updateEntity, createEntity, reset } from './device-status.reducer';
import { IDeviceStatus } from 'app/shared/model/device-status.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const DeviceStatusUpdate = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  const [isNew] = useState(!props.match.params || !props.match.params.id);

  const baseInfos = useAppSelector(state => state.baseInfo.entities);
  const accountStatuses = useAppSelector(state => state.accountStatus.entities);
  const deviceStatusEntity = useAppSelector(state => state.deviceStatus.entity);
  const loading = useAppSelector(state => state.deviceStatus.loading);
  const updating = useAppSelector(state => state.deviceStatus.updating);
  const updateSuccess = useAppSelector(state => state.deviceStatus.updateSuccess);

  const handleClose = () => {
    props.history.push('/device-status');
  };

  useEffect(() => {
    if (!isNew) {
      dispatch(getEntity(props.match.params.id));
    }

    dispatch(getBaseInfos({}));
    dispatch(getAccountStatuses({}));
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  const saveEntity = values => {
    values.lastVisited = convertDateTimeToServer(values.lastVisited);

    const entity = {
      ...deviceStatusEntity,
      ...values,
      baseInfo: baseInfos.find(it => it.id.toString() === values.baseInfoId.toString()),
      accountStatus: accountStatuses.find(it => it.id.toString() === values.accountStatusId.toString()),
    };

    if (isNew) {
      dispatch(createEntity(entity));
    } else {
      dispatch(updateEntity(entity));
    }
  };

  const defaultValues = () =>
    isNew
      ? {
          lastVisited: displayDefaultDateTime(),
        }
      : {
          ...deviceStatusEntity,
          deviceType: 'MOBILE',
          deviceStatus: 'ONLINE',
          lastVisited: convertDateTimeFromServer(deviceStatusEntity.lastVisited),
          baseInfoId: deviceStatusEntity?.baseInfo?.id,
          accountStatusId: deviceStatusEntity?.accountStatus?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="catinyApp.deviceStatus.home.createOrEditLabel" data-cy="DeviceStatusCreateUpdateHeading">
            <Translate contentKey="catinyApp.deviceStatus.home.createOrEditLabel">Create or edit a DeviceStatus</Translate>
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
                  id="device-status-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('catinyApp.deviceStatus.uuid')}
                id="device-status-uuid"
                name="uuid"
                data-cy="uuid"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <UncontrolledTooltip target="uuidLabel">
                <Translate contentKey="catinyApp.deviceStatus.help.uuid" />
              </UncontrolledTooltip>
              <ValidatedField
                label={translate('catinyApp.deviceStatus.deviceName')}
                id="device-status-deviceName"
                name="deviceName"
                data-cy="deviceName"
                type="text"
              />
              <UncontrolledTooltip target="deviceNameLabel">
                <Translate contentKey="catinyApp.deviceStatus.help.deviceName" />
              </UncontrolledTooltip>
              <ValidatedField
                label={translate('catinyApp.deviceStatus.deviceType')}
                id="device-status-deviceType"
                name="deviceType"
                data-cy="deviceType"
                type="select"
              >
                <option value="MOBILE">{translate('catinyApp.DeviceType.MOBILE')}</option>
                <option value="TABLE">{translate('catinyApp.DeviceType.TABLE')}</option>
                <option value="DESKTOP">{translate('catinyApp.DeviceType.DESKTOP')}</option>
                <option value="LAPTOP">{translate('catinyApp.DeviceType.LAPTOP')}</option>
                <option value="OTHER_DEVICE">{translate('catinyApp.DeviceType.OTHER_DEVICE')}</option>
              </ValidatedField>
              <UncontrolledTooltip target="deviceTypeLabel">
                <Translate contentKey="catinyApp.deviceStatus.help.deviceType" />
              </UncontrolledTooltip>
              <ValidatedField
                label={translate('catinyApp.deviceStatus.deviceStatus')}
                id="device-status-deviceStatus"
                name="deviceStatus"
                data-cy="deviceStatus"
                type="select"
              >
                <option value="ONLINE">{translate('catinyApp.StatusName.ONLINE')}</option>
                <option value="OFFLINE">{translate('catinyApp.StatusName.OFFLINE')}</option>
                <option value="BUSY">{translate('catinyApp.StatusName.BUSY')}</option>
                <option value="TEMPORARILY_ABSENT">{translate('catinyApp.StatusName.TEMPORARILY_ABSENT')}</option>
              </ValidatedField>
              <UncontrolledTooltip target="deviceStatusLabel">
                <Translate contentKey="catinyApp.deviceStatus.help.deviceStatus" />
              </UncontrolledTooltip>
              <ValidatedField
                label={translate('catinyApp.deviceStatus.lastVisited')}
                id="device-status-lastVisited"
                name="lastVisited"
                data-cy="lastVisited"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <UncontrolledTooltip target="lastVisitedLabel">
                <Translate contentKey="catinyApp.deviceStatus.help.lastVisited" />
              </UncontrolledTooltip>
              <ValidatedField
                label={translate('catinyApp.deviceStatus.statusComment')}
                id="device-status-statusComment"
                name="statusComment"
                data-cy="statusComment"
                type="text"
              />
              <UncontrolledTooltip target="statusCommentLabel">
                <Translate contentKey="catinyApp.deviceStatus.help.statusComment" />
              </UncontrolledTooltip>
              <ValidatedField
                id="device-status-baseInfo"
                name="baseInfoId"
                data-cy="baseInfo"
                label={translate('catinyApp.deviceStatus.baseInfo')}
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
                id="device-status-accountStatus"
                name="accountStatusId"
                data-cy="accountStatus"
                label={translate('catinyApp.deviceStatus.accountStatus')}
                type="select"
              >
                <option value="" key="0" />
                {accountStatuses
                  ? accountStatuses.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/device-status" replace color="info">
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

export default DeviceStatusUpdate;
