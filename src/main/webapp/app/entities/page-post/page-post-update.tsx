import React, { useState, useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, FormText, UncontrolledTooltip } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IPageProfile } from 'app/shared/model/page-profile.model';
import { getEntities as getPageProfiles } from 'app/entities/page-profile/page-profile.reducer';
import { IBaseInfo } from 'app/shared/model/base-info.model';
import { getEntities as getBaseInfos } from 'app/entities/base-info/base-info.reducer';
import { IMasterUser } from 'app/shared/model/master-user.model';
import { getEntities as getMasterUsers } from 'app/entities/master-user/master-user.reducer';
import { ITopicInterest } from 'app/shared/model/topic-interest.model';
import { getEntities as getTopicInterests } from 'app/entities/topic-interest/topic-interest.reducer';
import { getEntity, updateEntity, createEntity, reset } from './page-post.reducer';
import { IPagePost } from 'app/shared/model/page-post.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const PagePostUpdate = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  const [isNew] = useState(!props.match.params || !props.match.params.id);

  const pageProfiles = useAppSelector(state => state.pageProfile.entities);
  const baseInfos = useAppSelector(state => state.baseInfo.entities);
  const masterUsers = useAppSelector(state => state.masterUser.entities);
  const topicInterests = useAppSelector(state => state.topicInterest.entities);
  const pagePostEntity = useAppSelector(state => state.pagePost.entity);
  const loading = useAppSelector(state => state.pagePost.loading);
  const updating = useAppSelector(state => state.pagePost.updating);
  const updateSuccess = useAppSelector(state => state.pagePost.updateSuccess);

  const handleClose = () => {
    props.history.push('/page-post');
  };

  useEffect(() => {
    if (!isNew) {
      dispatch(getEntity(props.match.params.id));
    }

    dispatch(getPageProfiles({}));
    dispatch(getBaseInfos({}));
    dispatch(getMasterUsers({}));
    dispatch(getTopicInterests({}));
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  const saveEntity = values => {
    const entity = {
      ...pagePostEntity,
      ...values,
      profile: pageProfiles.find(it => it.id.toString() === values.profileId.toString()),
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
          ...pagePostEntity,
          profileId: pagePostEntity?.profile?.id,
          baseInfoId: pagePostEntity?.baseInfo?.id,
          masterUserId: pagePostEntity?.masterUser?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="catinyApp.pagePost.home.createOrEditLabel" data-cy="PagePostCreateUpdateHeading">
            <Translate contentKey="catinyApp.pagePost.home.createOrEditLabel">Create or edit a PagePost</Translate>
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
                  id="page-post-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('catinyApp.pagePost.uuid')}
                id="page-post-uuid"
                name="uuid"
                data-cy="uuid"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <UncontrolledTooltip target="uuidLabel">
                <Translate contentKey="catinyApp.pagePost.help.uuid" />
              </UncontrolledTooltip>
              <ValidatedField
                label={translate('catinyApp.pagePost.name')}
                id="page-post-name"
                name="name"
                data-cy="name"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <UncontrolledTooltip target="nameLabel">
                <Translate contentKey="catinyApp.pagePost.help.name" />
              </UncontrolledTooltip>
              <ValidatedField
                label={translate('catinyApp.pagePost.quickInfo')}
                id="page-post-quickInfo"
                name="quickInfo"
                data-cy="quickInfo"
                type="textarea"
              />
              <UncontrolledTooltip target="quickInfoLabel">
                <Translate contentKey="catinyApp.pagePost.help.quickInfo" />
              </UncontrolledTooltip>
              <ValidatedField
                id="page-post-profile"
                name="profileId"
                data-cy="profile"
                label={translate('catinyApp.pagePost.profile')}
                type="select"
              >
                <option value="" key="0" />
                {pageProfiles
                  ? pageProfiles.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField
                id="page-post-baseInfo"
                name="baseInfoId"
                data-cy="baseInfo"
                label={translate('catinyApp.pagePost.baseInfo')}
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
                id="page-post-masterUser"
                name="masterUserId"
                data-cy="masterUser"
                label={translate('catinyApp.pagePost.masterUser')}
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
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/page-post" replace color="info">
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

export default PagePostUpdate;
