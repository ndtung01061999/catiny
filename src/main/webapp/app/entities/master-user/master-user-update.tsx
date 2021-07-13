import React, { useState, useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, FormText, UncontrolledTooltip } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IUser } from 'app/shared/model/user.model';
import { getUsers } from 'app/modules/administration/user-management/user-management.reducer';
import { IUserProfile } from 'app/shared/model/user-profile.model';
import { getEntities as getUserProfiles } from 'app/entities/user-profile/user-profile.reducer';
import { IAccountStatus } from 'app/shared/model/account-status.model';
import { getEntities as getAccountStatuses } from 'app/entities/account-status/account-status.reducer';
import { IRankUser } from 'app/shared/model/rank-user.model';
import { getEntities as getRankUsers } from 'app/entities/rank-user/rank-user.reducer';
import { IImage } from 'app/shared/model/image.model';
import { getEntities as getImages } from 'app/entities/image/image.reducer';
import { IBaseInfo } from 'app/shared/model/base-info.model';
import { getEntities as getBaseInfos } from 'app/entities/base-info/base-info.reducer';
import { IGroupPost } from 'app/shared/model/group-post.model';
import { getEntities as getGroupPosts } from 'app/entities/group-post/group-post.reducer';
import { IMessageGroup } from 'app/shared/model/message-group.model';
import { getEntities as getMessageGroups } from 'app/entities/message-group/message-group.reducer';
import { ITopicInterest } from 'app/shared/model/topic-interest.model';
import { getEntities as getTopicInterests } from 'app/entities/topic-interest/topic-interest.reducer';
import { getEntity, updateEntity, createEntity, reset } from './master-user.reducer';
import { IMasterUser } from 'app/shared/model/master-user.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const MasterUserUpdate = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  const [isNew] = useState(!props.match.params || !props.match.params.id);

  const users = useAppSelector(state => state.userManagement.users);
  const userProfiles = useAppSelector(state => state.userProfile.entities);
  const accountStatuses = useAppSelector(state => state.accountStatus.entities);
  const rankUsers = useAppSelector(state => state.rankUser.entities);
  const images = useAppSelector(state => state.image.entities);
  const baseInfos = useAppSelector(state => state.baseInfo.entities);
  const groupPosts = useAppSelector(state => state.groupPost.entities);
  const messageGroups = useAppSelector(state => state.messageGroup.entities);
  const topicInterests = useAppSelector(state => state.topicInterest.entities);
  const masterUserEntity = useAppSelector(state => state.masterUser.entity);
  const loading = useAppSelector(state => state.masterUser.loading);
  const updating = useAppSelector(state => state.masterUser.updating);
  const updateSuccess = useAppSelector(state => state.masterUser.updateSuccess);

  const handleClose = () => {
    props.history.push('/master-user');
  };

  useEffect(() => {
    if (!isNew) {
      dispatch(getEntity(props.match.params.id));
    }

    dispatch(getUsers({}));
    dispatch(getUserProfiles({}));
    dispatch(getAccountStatuses({}));
    dispatch(getRankUsers({}));
    dispatch(getImages({}));
    dispatch(getBaseInfos({}));
    dispatch(getGroupPosts({}));
    dispatch(getMessageGroups({}));
    dispatch(getTopicInterests({}));
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  const saveEntity = values => {
    const entity = {
      ...masterUserEntity,
      ...values,
      myGroupPosts: mapIdList(values.myGroupPosts),
      messageGroups: mapIdList(values.messageGroups),
      topicInterests: mapIdList(values.topicInterests),
      user: users.find(it => it.id.toString() === values.userId.toString()),
      myProfile: userProfiles.find(it => it.id.toString() === values.myProfileId.toString()),
      myAccountStatus: accountStatuses.find(it => it.id.toString() === values.myAccountStatusId.toString()),
      myRank: rankUsers.find(it => it.id.toString() === values.myRankId.toString()),
      avatar: images.find(it => it.id.toString() === values.avatarId.toString()),
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
          ...masterUserEntity,
          userId: masterUserEntity?.user?.id,
          myProfileId: masterUserEntity?.myProfile?.id,
          myAccountStatusId: masterUserEntity?.myAccountStatus?.id,
          myRankId: masterUserEntity?.myRank?.id,
          avatarId: masterUserEntity?.avatar?.id,
          baseInfoId: masterUserEntity?.baseInfo?.id,
          myGroupPosts: masterUserEntity?.myGroupPosts?.map(e => e.id.toString()),
          messageGroups: masterUserEntity?.messageGroups?.map(e => e.id.toString()),
          topicInterests: masterUserEntity?.topicInterests?.map(e => e.id.toString()),
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="catinyApp.masterUser.home.createOrEditLabel" data-cy="MasterUserCreateUpdateHeading">
            <Translate contentKey="catinyApp.masterUser.home.createOrEditLabel">Create or edit a MasterUser</Translate>
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
                  id="master-user-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('catinyApp.masterUser.uuid')}
                id="master-user-uuid"
                name="uuid"
                data-cy="uuid"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <UncontrolledTooltip target="uuidLabel">
                <Translate contentKey="catinyApp.masterUser.help.uuid" />
              </UncontrolledTooltip>
              <ValidatedField
                label={translate('catinyApp.masterUser.fullName')}
                id="master-user-fullName"
                name="fullName"
                data-cy="fullName"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <UncontrolledTooltip target="fullNameLabel">
                <Translate contentKey="catinyApp.masterUser.help.fullName" />
              </UncontrolledTooltip>
              <ValidatedField
                label={translate('catinyApp.masterUser.nickname')}
                id="master-user-nickname"
                name="nickname"
                data-cy="nickname"
                type="text"
              />
              <UncontrolledTooltip target="nicknameLabel">
                <Translate contentKey="catinyApp.masterUser.help.nickname" />
              </UncontrolledTooltip>
              <ValidatedField
                label={translate('catinyApp.masterUser.quickInfo')}
                id="master-user-quickInfo"
                name="quickInfo"
                data-cy="quickInfo"
                type="textarea"
              />
              <UncontrolledTooltip target="quickInfoLabel">
                <Translate contentKey="catinyApp.masterUser.help.quickInfo" />
              </UncontrolledTooltip>
              <ValidatedField
                id="master-user-user"
                name="userId"
                data-cy="user"
                label={translate('catinyApp.masterUser.user')}
                type="select"
              >
                <option value="" key="0" />
                {users
                  ? users.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField
                id="master-user-myProfile"
                name="myProfileId"
                data-cy="myProfile"
                label={translate('catinyApp.masterUser.myProfile')}
                type="select"
              >
                <option value="" key="0" />
                {userProfiles
                  ? userProfiles.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField
                id="master-user-myAccountStatus"
                name="myAccountStatusId"
                data-cy="myAccountStatus"
                label={translate('catinyApp.masterUser.myAccountStatus')}
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
              <ValidatedField
                id="master-user-myRank"
                name="myRankId"
                data-cy="myRank"
                label={translate('catinyApp.masterUser.myRank')}
                type="select"
              >
                <option value="" key="0" />
                {rankUsers
                  ? rankUsers.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField
                id="master-user-avatar"
                name="avatarId"
                data-cy="avatar"
                label={translate('catinyApp.masterUser.avatar')}
                type="select"
              >
                <option value="" key="0" />
                {images
                  ? images.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField
                id="master-user-baseInfo"
                name="baseInfoId"
                data-cy="baseInfo"
                label={translate('catinyApp.masterUser.baseInfo')}
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
                label={translate('catinyApp.masterUser.myGroupPost')}
                id="master-user-myGroupPost"
                data-cy="myGroupPost"
                type="select"
                multiple
                name="myGroupPosts"
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
              <ValidatedField
                label={translate('catinyApp.masterUser.messageGroup')}
                id="master-user-messageGroup"
                data-cy="messageGroup"
                type="select"
                multiple
                name="messageGroups"
              >
                <option value="" key="0" />
                {messageGroups
                  ? messageGroups.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField
                label={translate('catinyApp.masterUser.topicInterest')}
                id="master-user-topicInterest"
                data-cy="topicInterest"
                type="select"
                multiple
                name="topicInterests"
              >
                <option value="" key="0" />
                {topicInterests
                  ? topicInterests.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/master-user" replace color="info">
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

export default MasterUserUpdate;
