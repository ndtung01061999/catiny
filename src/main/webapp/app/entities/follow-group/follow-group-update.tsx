import React, { useState, useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, FormText, UncontrolledTooltip } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IBaseInfo } from 'app/shared/model/base-info.model';
import { getEntities as getBaseInfos } from 'app/entities/base-info/base-info.reducer';
import { IGroupPost } from 'app/shared/model/group-post.model';
import { getEntities as getGroupPosts } from 'app/entities/group-post/group-post.reducer';
import { getEntity, updateEntity, createEntity, reset } from './follow-group.reducer';
import { IFollowGroup } from 'app/shared/model/follow-group.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const FollowGroupUpdate = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  const [isNew] = useState(!props.match.params || !props.match.params.id);

  const baseInfos = useAppSelector(state => state.baseInfo.entities);
  const groupPosts = useAppSelector(state => state.groupPost.entities);
  const followGroupEntity = useAppSelector(state => state.followGroup.entity);
  const loading = useAppSelector(state => state.followGroup.loading);
  const updating = useAppSelector(state => state.followGroup.updating);
  const updateSuccess = useAppSelector(state => state.followGroup.updateSuccess);

  const handleClose = () => {
    props.history.push('/follow-group');
  };

  useEffect(() => {
    if (!isNew) {
      dispatch(getEntity(props.match.params.id));
    }

    dispatch(getBaseInfos({}));
    dispatch(getGroupPosts({}));
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  const saveEntity = values => {
    const entity = {
      ...followGroupEntity,
      ...values,
      baseInfo: baseInfos.find(it => it.id.toString() === values.baseInfoId.toString()),
      followGroupDetails: groupPosts.find(it => it.id.toString() === values.followGroupDetailsId.toString()),
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
          ...followGroupEntity,
          baseInfoId: followGroupEntity?.baseInfo?.id,
          followGroupDetailsId: followGroupEntity?.followGroupDetails?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="catinyApp.followGroup.home.createOrEditLabel" data-cy="FollowGroupCreateUpdateHeading">
            <Translate contentKey="catinyApp.followGroup.home.createOrEditLabel">Create or edit a FollowGroup</Translate>
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
                  id="follow-group-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('catinyApp.followGroup.uuid')}
                id="follow-group-uuid"
                name="uuid"
                data-cy="uuid"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <UncontrolledTooltip target="uuidLabel">
                <Translate contentKey="catinyApp.followGroup.help.uuid" />
              </UncontrolledTooltip>
              <ValidatedField
                id="follow-group-baseInfo"
                name="baseInfoId"
                data-cy="baseInfo"
                label={translate('catinyApp.followGroup.baseInfo')}
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
                id="follow-group-followGroupDetails"
                name="followGroupDetailsId"
                data-cy="followGroupDetails"
                label={translate('catinyApp.followGroup.followGroupDetails')}
                type="select"
              >
                <option value="" key="0" />
                {groupPosts
                  ? groupPosts.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/follow-group" replace color="info">
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

export default FollowGroupUpdate;
