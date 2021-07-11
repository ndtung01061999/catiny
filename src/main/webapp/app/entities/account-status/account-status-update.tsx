import React, { useState, useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, FormText, UncontrolledTooltip } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IBaseInfo } from 'app/shared/model/base-info.model';
import { getEntities as getBaseInfos } from 'app/entities/base-info/base-info.reducer';
import { getEntity, updateEntity, createEntity, reset } from './account-status.reducer';
import { IAccountStatus } from 'app/shared/model/account-status.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const AccountStatusUpdate = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  const [isNew] = useState(!props.match.params || !props.match.params.id);

  const baseInfos = useAppSelector(state => state.baseInfo.entities);
  const accountStatusEntity = useAppSelector(state => state.accountStatus.entity);
  const loading = useAppSelector(state => state.accountStatus.loading);
  const updating = useAppSelector(state => state.accountStatus.updating);
  const updateSuccess = useAppSelector(state => state.accountStatus.updateSuccess);

  const handleClose = () => {
    props.history.push('/account-status');
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
    values.lastVisited = convertDateTimeToServer(values.lastVisited);

    const entity = {
      ...accountStatusEntity,
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
      ? {
          lastVisited: displayDefaultDateTime(),
        }
      : {
          ...accountStatusEntity,
          accountStatus: 'ONLINE',
          lastVisited: convertDateTimeFromServer(accountStatusEntity.lastVisited),
          baseInfoId: accountStatusEntity?.baseInfo?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="catinyApp.accountStatus.home.createOrEditLabel" data-cy="AccountStatusCreateUpdateHeading">
            <Translate contentKey="catinyApp.accountStatus.home.createOrEditLabel">Create or edit a AccountStatus</Translate>
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
                  id="account-status-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('catinyApp.accountStatus.uuid')}
                id="account-status-uuid"
                name="uuid"
                data-cy="uuid"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <UncontrolledTooltip target="uuidLabel">
                <Translate contentKey="catinyApp.accountStatus.help.uuid" />
              </UncontrolledTooltip>
              <ValidatedField
                label={translate('catinyApp.accountStatus.accountStatus')}
                id="account-status-accountStatus"
                name="accountStatus"
                data-cy="accountStatus"
                type="select"
              >
                <option value="ONLINE">{translate('catinyApp.StatusName.ONLINE')}</option>
                <option value="OFFLINE">{translate('catinyApp.StatusName.OFFLINE')}</option>
                <option value="BUSY">{translate('catinyApp.StatusName.BUSY')}</option>
                <option value="TEMPORARILY_ABSENT">{translate('catinyApp.StatusName.TEMPORARILY_ABSENT')}</option>
              </ValidatedField>
              <UncontrolledTooltip target="accountStatusLabel">
                <Translate contentKey="catinyApp.accountStatus.help.accountStatus" />
              </UncontrolledTooltip>
              <ValidatedField
                label={translate('catinyApp.accountStatus.lastVisited')}
                id="account-status-lastVisited"
                name="lastVisited"
                data-cy="lastVisited"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <UncontrolledTooltip target="lastVisitedLabel">
                <Translate contentKey="catinyApp.accountStatus.help.lastVisited" />
              </UncontrolledTooltip>
              <ValidatedField
                label={translate('catinyApp.accountStatus.statusComment')}
                id="account-status-statusComment"
                name="statusComment"
                data-cy="statusComment"
                type="text"
              />
              <UncontrolledTooltip target="statusCommentLabel">
                <Translate contentKey="catinyApp.accountStatus.help.statusComment" />
              </UncontrolledTooltip>
              <ValidatedField
                id="account-status-baseInfo"
                name="baseInfoId"
                data-cy="baseInfo"
                label={translate('catinyApp.accountStatus.baseInfo')}
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
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/account-status" replace color="info">
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

export default AccountStatusUpdate;
