import React, { useState, useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, FormText, UncontrolledTooltip } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IBaseInfo } from 'app/shared/model/base-info.model';
import { getEntities as getBaseInfos } from 'app/entities/base-info/base-info.reducer';
import { getEntity, updateEntity, createEntity, reset } from './user-profile.reducer';
import { IUserProfile } from 'app/shared/model/user-profile.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const UserProfileUpdate = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  const [isNew] = useState(!props.match.params || !props.match.params.id);

  const baseInfos = useAppSelector(state => state.baseInfo.entities);
  const userProfileEntity = useAppSelector(state => state.userProfile.entity);
  const loading = useAppSelector(state => state.userProfile.loading);
  const updating = useAppSelector(state => state.userProfile.updating);
  const updateSuccess = useAppSelector(state => state.userProfile.updateSuccess);

  const handleClose = () => {
    props.history.push('/user-profile');
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
      ...userProfileEntity,
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
          ...userProfileEntity,
          baseInfoId: userProfileEntity?.baseInfo?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="catinyApp.userProfile.home.createOrEditLabel" data-cy="UserProfileCreateUpdateHeading">
            <Translate contentKey="catinyApp.userProfile.home.createOrEditLabel">Create or edit a UserProfile</Translate>
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
                  id="user-profile-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('catinyApp.userProfile.uuid')}
                id="user-profile-uuid"
                name="uuid"
                data-cy="uuid"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <UncontrolledTooltip target="uuidLabel">
                <Translate contentKey="catinyApp.userProfile.help.uuid" />
              </UncontrolledTooltip>
              <ValidatedField
                label={translate('catinyApp.userProfile.work')}
                id="user-profile-work"
                name="work"
                data-cy="work"
                type="textarea"
              />
              <UncontrolledTooltip target="workLabel">
                <Translate contentKey="catinyApp.userProfile.help.work" />
              </UncontrolledTooltip>
              <ValidatedField
                label={translate('catinyApp.userProfile.education')}
                id="user-profile-education"
                name="education"
                data-cy="education"
                type="textarea"
              />
              <UncontrolledTooltip target="educationLabel">
                <Translate contentKey="catinyApp.userProfile.help.education" />
              </UncontrolledTooltip>
              <ValidatedField
                label={translate('catinyApp.userProfile.placesLived')}
                id="user-profile-placesLived"
                name="placesLived"
                data-cy="placesLived"
                type="textarea"
              />
              <UncontrolledTooltip target="placesLivedLabel">
                <Translate contentKey="catinyApp.userProfile.help.placesLived" />
              </UncontrolledTooltip>
              <ValidatedField
                label={translate('catinyApp.userProfile.contactInfo')}
                id="user-profile-contactInfo"
                name="contactInfo"
                data-cy="contactInfo"
                type="textarea"
              />
              <UncontrolledTooltip target="contactInfoLabel">
                <Translate contentKey="catinyApp.userProfile.help.contactInfo" />
              </UncontrolledTooltip>
              <ValidatedField
                label={translate('catinyApp.userProfile.webSocialLinks')}
                id="user-profile-webSocialLinks"
                name="webSocialLinks"
                data-cy="webSocialLinks"
                type="textarea"
              />
              <UncontrolledTooltip target="webSocialLinksLabel">
                <Translate contentKey="catinyApp.userProfile.help.webSocialLinks" />
              </UncontrolledTooltip>
              <ValidatedField
                label={translate('catinyApp.userProfile.basicInfo')}
                id="user-profile-basicInfo"
                name="basicInfo"
                data-cy="basicInfo"
                type="textarea"
              />
              <UncontrolledTooltip target="basicInfoLabel">
                <Translate contentKey="catinyApp.userProfile.help.basicInfo" />
              </UncontrolledTooltip>
              <ValidatedField
                label={translate('catinyApp.userProfile.relationshipInfo')}
                id="user-profile-relationshipInfo"
                name="relationshipInfo"
                data-cy="relationshipInfo"
                type="textarea"
              />
              <UncontrolledTooltip target="relationshipInfoLabel">
                <Translate contentKey="catinyApp.userProfile.help.relationshipInfo" />
              </UncontrolledTooltip>
              <ValidatedField
                label={translate('catinyApp.userProfile.family')}
                id="user-profile-family"
                name="family"
                data-cy="family"
                type="textarea"
              />
              <UncontrolledTooltip target="familyLabel">
                <Translate contentKey="catinyApp.userProfile.help.family" />
              </UncontrolledTooltip>
              <ValidatedField
                label={translate('catinyApp.userProfile.detailAbout')}
                id="user-profile-detailAbout"
                name="detailAbout"
                data-cy="detailAbout"
                type="textarea"
              />
              <UncontrolledTooltip target="detailAboutLabel">
                <Translate contentKey="catinyApp.userProfile.help.detailAbout" />
              </UncontrolledTooltip>
              <ValidatedField
                label={translate('catinyApp.userProfile.lifeEvents')}
                id="user-profile-lifeEvents"
                name="lifeEvents"
                data-cy="lifeEvents"
                type="textarea"
              />
              <UncontrolledTooltip target="lifeEventsLabel">
                <Translate contentKey="catinyApp.userProfile.help.lifeEvents" />
              </UncontrolledTooltip>
              <ValidatedField
                label={translate('catinyApp.userProfile.hobbies')}
                id="user-profile-hobbies"
                name="hobbies"
                data-cy="hobbies"
                type="textarea"
              />
              <UncontrolledTooltip target="hobbiesLabel">
                <Translate contentKey="catinyApp.userProfile.help.hobbies" />
              </UncontrolledTooltip>
              <ValidatedField
                label={translate('catinyApp.userProfile.featured')}
                id="user-profile-featured"
                name="featured"
                data-cy="featured"
                type="textarea"
              />
              <UncontrolledTooltip target="featuredLabel">
                <Translate contentKey="catinyApp.userProfile.help.featured" />
              </UncontrolledTooltip>
              <ValidatedField
                id="user-profile-baseInfo"
                name="baseInfoId"
                data-cy="baseInfo"
                label={translate('catinyApp.userProfile.baseInfo')}
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
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/user-profile" replace color="info">
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

export default UserProfileUpdate;
