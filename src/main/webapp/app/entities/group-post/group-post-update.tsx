import React, { useState, useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, FormText, UncontrolledTooltip } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IGroupProfile } from 'app/shared/model/group-profile.model';
import { getEntities as getGroupProfiles } from 'app/entities/group-profile/group-profile.reducer';
import { IBaseInfo } from 'app/shared/model/base-info.model';
import { getEntities as getBaseInfos } from 'app/entities/base-info/base-info.reducer';
import { ITopicInterest } from 'app/shared/model/topic-interest.model';
import { getEntities as getTopicInterests } from 'app/entities/topic-interest/topic-interest.reducer';
import { IMasterUser } from 'app/shared/model/master-user.model';
import { getEntities as getMasterUsers } from 'app/entities/master-user/master-user.reducer';
import { getEntity, updateEntity, createEntity, reset } from './group-post.reducer';
import { IGroupPost } from 'app/shared/model/group-post.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const GroupPostUpdate = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  const [isNew] = useState(!props.match.params || !props.match.params.id);

  const groupProfiles = useAppSelector(state => state.groupProfile.entities);
  const baseInfos = useAppSelector(state => state.baseInfo.entities);
  const topicInterests = useAppSelector(state => state.topicInterest.entities);
  const masterUsers = useAppSelector(state => state.masterUser.entities);
  const groupPostEntity = useAppSelector(state => state.groupPost.entity);
  const loading = useAppSelector(state => state.groupPost.loading);
  const updating = useAppSelector(state => state.groupPost.updating);
  const updateSuccess = useAppSelector(state => state.groupPost.updateSuccess);

  const handleClose = () => {
    props.history.push('/group-post');
  };

  useEffect(() => {
    if (!isNew) {
      dispatch(getEntity(props.match.params.id));
    }

    dispatch(getGroupProfiles({}));
    dispatch(getBaseInfos({}));
    dispatch(getTopicInterests({}));
    dispatch(getMasterUsers({}));
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  const saveEntity = values => {
    const entity = {
      ...groupPostEntity,
      ...values,
      profile: groupProfiles.find(it => it.id.toString() === values.profileId.toString()),
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
          ...groupPostEntity,
          profileId: groupPostEntity?.profile?.id,
          baseInfoId: groupPostEntity?.baseInfo?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="catinyApp.groupPost.home.createOrEditLabel" data-cy="GroupPostCreateUpdateHeading">
            <Translate contentKey="catinyApp.groupPost.home.createOrEditLabel">Create or edit a GroupPost</Translate>
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
                  id="group-post-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('catinyApp.groupPost.uuid')}
                id="group-post-uuid"
                name="uuid"
                data-cy="uuid"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <UncontrolledTooltip target="uuidLabel">
                <Translate contentKey="catinyApp.groupPost.help.uuid" />
              </UncontrolledTooltip>
              <ValidatedField
                label={translate('catinyApp.groupPost.name')}
                id="group-post-name"
                name="name"
                data-cy="name"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <UncontrolledTooltip target="nameLabel">
                <Translate contentKey="catinyApp.groupPost.help.name" />
              </UncontrolledTooltip>
              <ValidatedField
                label={translate('catinyApp.groupPost.quickInfo')}
                id="group-post-quickInfo"
                name="quickInfo"
                data-cy="quickInfo"
                type="textarea"
              />
              <UncontrolledTooltip target="quickInfoLabel">
                <Translate contentKey="catinyApp.groupPost.help.quickInfo" />
              </UncontrolledTooltip>
              <ValidatedField
                id="group-post-profile"
                name="profileId"
                data-cy="profile"
                label={translate('catinyApp.groupPost.profile')}
                type="select"
              >
                <option value="" key="0" />
                {groupProfiles
                  ? groupProfiles.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField
                id="group-post-baseInfo"
                name="baseInfoId"
                data-cy="baseInfo"
                label={translate('catinyApp.groupPost.baseInfo')}
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
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/group-post" replace color="info">
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

export default GroupPostUpdate;
