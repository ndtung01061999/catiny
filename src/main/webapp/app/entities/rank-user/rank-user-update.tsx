import React, { useState, useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, FormText, UncontrolledTooltip } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IBaseInfo } from 'app/shared/model/base-info.model';
import { getEntities as getBaseInfos } from 'app/entities/base-info/base-info.reducer';
import { IRankGroup } from 'app/shared/model/rank-group.model';
import { getEntities as getRankGroups } from 'app/entities/rank-group/rank-group.reducer';
import { getEntity, updateEntity, createEntity, reset } from './rank-user.reducer';
import { IRankUser } from 'app/shared/model/rank-user.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const RankUserUpdate = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  const [isNew] = useState(!props.match.params || !props.match.params.id);

  const baseInfos = useAppSelector(state => state.baseInfo.entities);
  const rankGroups = useAppSelector(state => state.rankGroup.entities);
  const rankUserEntity = useAppSelector(state => state.rankUser.entity);
  const loading = useAppSelector(state => state.rankUser.loading);
  const updating = useAppSelector(state => state.rankUser.updating);
  const updateSuccess = useAppSelector(state => state.rankUser.updateSuccess);

  const handleClose = () => {
    props.history.push('/rank-user');
  };

  useEffect(() => {
    if (!isNew) {
      dispatch(getEntity(props.match.params.id));
    }

    dispatch(getBaseInfos({}));
    dispatch(getRankGroups({}));
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  const saveEntity = values => {
    const entity = {
      ...rankUserEntity,
      ...values,
      baseInfo: baseInfos.find(it => it.id.toString() === values.baseInfoId.toString()),
      rankGroup: rankGroups.find(it => it.id.toString() === values.rankGroupId.toString()),
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
          ...rankUserEntity,
          baseInfoId: rankUserEntity?.baseInfo?.id,
          rankGroupId: rankUserEntity?.rankGroup?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="catinyApp.rankUser.home.createOrEditLabel" data-cy="RankUserCreateUpdateHeading">
            <Translate contentKey="catinyApp.rankUser.home.createOrEditLabel">Create or edit a RankUser</Translate>
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
                  id="rank-user-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('catinyApp.rankUser.uuid')}
                id="rank-user-uuid"
                name="uuid"
                data-cy="uuid"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <UncontrolledTooltip target="uuidLabel">
                <Translate contentKey="catinyApp.rankUser.help.uuid" />
              </UncontrolledTooltip>
              <ValidatedField
                label={translate('catinyApp.rankUser.ratingPoints')}
                id="rank-user-ratingPoints"
                name="ratingPoints"
                data-cy="ratingPoints"
                type="text"
              />
              <ValidatedField
                id="rank-user-baseInfo"
                name="baseInfoId"
                data-cy="baseInfo"
                label={translate('catinyApp.rankUser.baseInfo')}
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
                id="rank-user-rankGroup"
                name="rankGroupId"
                data-cy="rankGroup"
                label={translate('catinyApp.rankUser.rankGroup')}
                type="select"
              >
                <option value="" key="0" />
                {rankGroups
                  ? rankGroups.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/rank-user" replace color="info">
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

export default RankUserUpdate;
