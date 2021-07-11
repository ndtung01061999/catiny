import React, { useState, useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, FormText, UncontrolledTooltip } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IBaseInfo } from 'app/shared/model/base-info.model';
import { getEntities as getBaseInfos } from 'app/entities/base-info/base-info.reducer';
import { IMasterUser } from 'app/shared/model/master-user.model';
import { getEntities as getMasterUsers } from 'app/entities/master-user/master-user.reducer';
import { getEntity, updateEntity, createEntity, reset } from './friend.reducer';
import { IFriend } from 'app/shared/model/friend.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const FriendUpdate = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  const [isNew] = useState(!props.match.params || !props.match.params.id);

  const baseInfos = useAppSelector(state => state.baseInfo.entities);
  const masterUsers = useAppSelector(state => state.masterUser.entities);
  const friendEntity = useAppSelector(state => state.friend.entity);
  const loading = useAppSelector(state => state.friend.loading);
  const updating = useAppSelector(state => state.friend.updating);
  const updateSuccess = useAppSelector(state => state.friend.updateSuccess);

  const handleClose = () => {
    props.history.push('/friend');
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
      ...friendEntity,
      ...values,
      baseInfo: baseInfos.find(it => it.id.toString() === values.baseInfoId.toString()),
      friendDetails: masterUsers.find(it => it.id.toString() === values.friendDetailsId.toString()),
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
          ...friendEntity,
          friendType: 'FRIEND',
          baseInfoId: friendEntity?.baseInfo?.id,
          friendDetailsId: friendEntity?.friendDetails?.id,
          masterUserId: friendEntity?.masterUser?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="catinyApp.friend.home.createOrEditLabel" data-cy="FriendCreateUpdateHeading">
            <Translate contentKey="catinyApp.friend.home.createOrEditLabel">Create or edit a Friend</Translate>
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
                  id="friend-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('catinyApp.friend.uuid')}
                id="friend-uuid"
                name="uuid"
                data-cy="uuid"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <UncontrolledTooltip target="uuidLabel">
                <Translate contentKey="catinyApp.friend.help.uuid" />
              </UncontrolledTooltip>
              <ValidatedField
                label={translate('catinyApp.friend.friendType')}
                id="friend-friendType"
                name="friendType"
                data-cy="friendType"
                type="select"
              >
                <option value="FRIEND">{translate('catinyApp.FriendType.FRIEND')}</option>
                <option value="BEST_FRIEND">{translate('catinyApp.FriendType.BEST_FRIEND')}</option>
                <option value="FAMILY">{translate('catinyApp.FriendType.FAMILY')}</option>
              </ValidatedField>
              <ValidatedField
                id="friend-baseInfo"
                name="baseInfoId"
                data-cy="baseInfo"
                label={translate('catinyApp.friend.baseInfo')}
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
                id="friend-friendDetails"
                name="friendDetailsId"
                data-cy="friendDetails"
                label={translate('catinyApp.friend.friendDetails')}
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
              <ValidatedField
                id="friend-masterUser"
                name="masterUserId"
                data-cy="masterUser"
                label={translate('catinyApp.friend.masterUser')}
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
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/friend" replace color="info">
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

export default FriendUpdate;
