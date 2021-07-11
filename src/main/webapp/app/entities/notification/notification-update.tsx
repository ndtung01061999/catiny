import React, { useState, useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, FormText, UncontrolledTooltip } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IBaseInfo } from 'app/shared/model/base-info.model';
import { getEntities as getBaseInfos } from 'app/entities/base-info/base-info.reducer';
import { IMasterUser } from 'app/shared/model/master-user.model';
import { getEntities as getMasterUsers } from 'app/entities/master-user/master-user.reducer';
import { getEntity, updateEntity, createEntity, reset } from './notification.reducer';
import { INotification } from 'app/shared/model/notification.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const NotificationUpdate = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  const [isNew] = useState(!props.match.params || !props.match.params.id);

  const baseInfos = useAppSelector(state => state.baseInfo.entities);
  const masterUsers = useAppSelector(state => state.masterUser.entities);
  const notificationEntity = useAppSelector(state => state.notification.entity);
  const loading = useAppSelector(state => state.notification.loading);
  const updating = useAppSelector(state => state.notification.updating);
  const updateSuccess = useAppSelector(state => state.notification.updateSuccess);

  const handleClose = () => {
    props.history.push('/notification');
  };

  useEffect(() => {
    if (!isNew) {
      dispatch(getEntity(props.match.params.id));
    }

    dispatch(getBaseInfos({}));
    dispatch(getMasterUsers({}));
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  const saveEntity = values => {
    const entity = {
      ...notificationEntity,
      ...values,
      baseInfo: baseInfos.find(it => it.id.toString() === values.baseInfoId.toString()),
      masterUser: masterUsers.find(it => it.id.toString() === values.masterUserId.toString()),
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
          ...notificationEntity,
          notifyType: 'SYSTEM',
          baseInfoId: notificationEntity?.baseInfo?.id,
          masterUserId: notificationEntity?.masterUser?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="catinyApp.notification.home.createOrEditLabel" data-cy="NotificationCreateUpdateHeading">
            <Translate contentKey="catinyApp.notification.home.createOrEditLabel">Create or edit a Notification</Translate>
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
                  id="notification-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('catinyApp.notification.uuid')}
                id="notification-uuid"
                name="uuid"
                data-cy="uuid"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <UncontrolledTooltip target="uuidLabel">
                <Translate contentKey="catinyApp.notification.help.uuid" />
              </UncontrolledTooltip>
              <ValidatedField
                label={translate('catinyApp.notification.notifyType')}
                id="notification-notifyType"
                name="notifyType"
                data-cy="notifyType"
                type="select"
              >
                <option value="SYSTEM">{translate('catinyApp.NotifyType.SYSTEM')}</option>
                <option value="MANAGER">{translate('catinyApp.NotifyType.MANAGER')}</option>
                <option value="ADMIN">{translate('catinyApp.NotifyType.ADMIN')}</option>
                <option value="USER">{translate('catinyApp.NotifyType.USER')}</option>
                <option value="ANONYMOUS">{translate('catinyApp.NotifyType.ANONYMOUS')}</option>
              </ValidatedField>
              <UncontrolledTooltip target="notifyTypeLabel">
                <Translate contentKey="catinyApp.notification.help.notifyType" />
              </UncontrolledTooltip>
              <ValidatedField
                label={translate('catinyApp.notification.title')}
                id="notification-title"
                name="title"
                data-cy="title"
                type="text"
              />
              <ValidatedField
                label={translate('catinyApp.notification.content')}
                id="notification-content"
                name="content"
                data-cy="content"
                type="textarea"
              />
              <ValidatedField
                id="notification-baseInfo"
                name="baseInfoId"
                data-cy="baseInfo"
                label={translate('catinyApp.notification.baseInfo')}
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
                id="notification-masterUser"
                name="masterUserId"
                data-cy="masterUser"
                label={translate('catinyApp.notification.masterUser')}
                type="select"
              >
                <option value="" key="0" />
                {masterUsers
                  ? masterUsers.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/notification" replace color="info">
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

export default NotificationUpdate;
