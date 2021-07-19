import React, {useEffect, useState} from 'react';
import {Link, RouteComponentProps} from 'react-router-dom';
import {Button, Col, Row, UncontrolledTooltip} from 'reactstrap';
import {Translate, translate, ValidatedField, ValidatedForm} from 'react-jhipster';
import {FontAwesomeIcon} from '@fortawesome/react-fontawesome';
import {getEntities as getClassInfos} from 'app/entities/class-info/class-info.reducer';
import {getEntities as getUserProfiles} from 'app/entities/user-profile/user-profile.reducer';
import {getEntities as getAccountStatuses} from 'app/entities/account-status/account-status.reducer';
import {getEntities as getDeviceStatuses} from 'app/entities/device-status/device-status.reducer';
import {getEntities as getFriends} from 'app/entities/friend/friend.reducer';
import {getEntities as getFollowUsers} from 'app/entities/follow-user/follow-user.reducer';
import {getEntities as getFollowGroups} from 'app/entities/follow-group/follow-group.reducer';
import {getEntities as getFollowPages} from 'app/entities/follow-page/follow-page.reducer';
import {getEntities as getFileInfos} from 'app/entities/file-info/file-info.reducer';
import {getEntities as getPagePosts} from 'app/entities/page-post/page-post.reducer';
import {getEntities as getPageProfiles} from 'app/entities/page-profile/page-profile.reducer';
import {getEntities as getGroupPosts} from 'app/entities/group-post/group-post.reducer';
import {getEntities as getPosts} from 'app/entities/post/post.reducer';
import {getEntities as getPostComments} from 'app/entities/post-comment/post-comment.reducer';
import {getEntities as getPostLikes} from 'app/entities/post-like/post-like.reducer';
import {getEntities as getGroupProfiles} from 'app/entities/group-profile/group-profile.reducer';
import {getEntities as getNewsFeeds} from 'app/entities/news-feed/news-feed.reducer';
import {getEntities as getMessageGroups} from 'app/entities/message-group/message-group.reducer';
import {getEntities as getMessageContents} from 'app/entities/message-content/message-content.reducer';
import {getEntities as getRankUsers} from 'app/entities/rank-user/rank-user.reducer';
import {getEntities as getRankGroups} from 'app/entities/rank-group/rank-group.reducer';
import {getEntities as getNotifications} from 'app/entities/notification/notification.reducer';
import {getEntities as getAlbums} from 'app/entities/album/album.reducer';
import {getEntities as getVideos} from 'app/entities/video/video.reducer';
import {getEntities as getImages} from 'app/entities/image/image.reducer';
import {getEntities as getVideoStreams} from 'app/entities/video-stream/video-stream.reducer';
import {getEntities as getVideoLiveStreamBuffers} from 'app/entities/video-live-stream-buffer/video-live-stream-buffer.reducer';
import {getEntities as getTopicInterests} from 'app/entities/topic-interest/topic-interest.reducer';
import {getEntities as getTodoLists} from 'app/entities/todo-list/todo-list.reducer';
import {getEntities as getEvents} from 'app/entities/event/event.reducer';
import {getEntities as getMasterUsers} from 'app/entities/master-user/master-user.reducer';
import {createEntity, getEntity, updateEntity} from './base-info.reducer';
import {convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime} from 'app/shared/util/date-utils';
import {useAppDispatch, useAppSelector} from 'app/config/store';

export const BaseInfoUpdate = (props: RouteComponentProps<{ id: string }>) =>
{
  const dispatch = useAppDispatch();

  const [isNew] = useState(!props.match.params || !props.match.params.id);

  const classInfos = useAppSelector(state => state.classInfo.entities);
  const userProfiles = useAppSelector(state => state.userProfile.entities);
  const accountStatuses = useAppSelector(state => state.accountStatus.entities);
  const deviceStatuses = useAppSelector(state => state.deviceStatus.entities);
  const friends = useAppSelector(state => state.friend.entities);
  const followUsers = useAppSelector(state => state.followUser.entities);
  const followGroups = useAppSelector(state => state.followGroup.entities);
  const followPages = useAppSelector(state => state.followPage.entities);
  const fileInfos = useAppSelector(state => state.fileInfo.entities);
  const pagePosts = useAppSelector(state => state.pagePost.entities);
  const pageProfiles = useAppSelector(state => state.pageProfile.entities);
  const groupPosts = useAppSelector(state => state.groupPost.entities);
  const posts = useAppSelector(state => state.post.entities);
  const postComments = useAppSelector(state => state.postComment.entities);
  const postLikes = useAppSelector(state => state.postLike.entities);
  const groupProfiles = useAppSelector(state => state.groupProfile.entities);
  const newsFeeds = useAppSelector(state => state.newsFeed.entities);
  const messageGroups = useAppSelector(state => state.messageGroup.entities);
  const messageContents = useAppSelector(state => state.messageContent.entities);
  const rankUsers = useAppSelector(state => state.rankUser.entities);
  const rankGroups = useAppSelector(state => state.rankGroup.entities);
  const notifications = useAppSelector(state => state.notification.entities);
  const albums = useAppSelector(state => state.album.entities);
  const videos = useAppSelector(state => state.video.entities);
  const images = useAppSelector(state => state.image.entities);
  const videoStreams = useAppSelector(state => state.videoStream.entities);
  const videoLiveStreamBuffers = useAppSelector(state => state.videoLiveStreamBuffer.entities);
  const topicInterests = useAppSelector(state => state.topicInterest.entities);
  const todoLists = useAppSelector(state => state.todoList.entities);
  const events = useAppSelector(state => state.event.entities);
  const masterUsers = useAppSelector(state => state.masterUser.entities);
  const baseInfoEntity = useAppSelector(state => state.baseInfo.entity);
  const loading = useAppSelector(state => state.baseInfo.loading);
  const updating = useAppSelector(state => state.baseInfo.updating);
  const updateSuccess = useAppSelector(state => state.baseInfo.updateSuccess);

  const handleClose = () => {
    props.history.push('/base-info');
  };

  useEffect(() => {
    if (!isNew) {
      dispatch(getEntity(props.match.params.id));
    }

    dispatch(getClassInfos({}));
    dispatch(getUserProfiles({}));
    dispatch(getAccountStatuses({}));
    dispatch(getDeviceStatuses({}));
    dispatch(getFriends({}));
    dispatch(getFollowUsers({}));
    dispatch(getFollowGroups({}));
    dispatch(getFollowPages({}));
    dispatch(getFileInfos({}));
    dispatch(getPagePosts({}));
    dispatch(getPageProfiles({}));
    dispatch(getGroupPosts({}));
    dispatch(getPosts({}));
    dispatch(getPostComments({}));
    dispatch(getPostLikes({}));
    dispatch(getGroupProfiles({}));
    dispatch(getNewsFeeds({}));
    dispatch(getMessageGroups({}));
    dispatch(getMessageContents({}));
    dispatch(getRankUsers({}));
    dispatch(getRankGroups({}));
    dispatch(getNotifications({}));
    dispatch(getAlbums({}));
    dispatch(getVideos({}));
    dispatch(getImages({}));
    dispatch(getVideoStreams({}));
    dispatch(getVideoLiveStreamBuffers({}));
    dispatch(getTopicInterests({}));
    dispatch(getTodoLists({}));
    dispatch(getEvents({}));
    dispatch(getMasterUsers({}));
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  const saveEntity = values => {
    values.createdDate = convertDateTimeToServer(values.createdDate);
    values.modifiedDate = convertDateTimeToServer(values.modifiedDate);

    const entity = {
      ...baseInfoEntity,
      ...values,
      classInfo: classInfos.find(it => it.id.toString() === values.classInfoId.toString()),
      createdBy: masterUsers.find(it => it.id.toString() === values.createdById.toString()),
      modifiedBy: masterUsers.find(it => it.id.toString() === values.modifiedById.toString()),
      owner: masterUsers.find(it => it.id.toString() === values.ownerId.toString()),
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
          createdDate: displayDefaultDateTime(),
          modifiedDate: displayDefaultDateTime(),
        }
      : {
          ...baseInfoEntity,
          processStatus: 'NOT_PROCESSED',
          createdDate: convertDateTimeFromServer(baseInfoEntity.createdDate),
          modifiedDate: convertDateTimeFromServer(baseInfoEntity.modifiedDate),
          classInfoId: baseInfoEntity?.classInfo?.id,
          createdById: baseInfoEntity?.createdBy?.id,
          modifiedById: baseInfoEntity?.modifiedBy?.id,
          ownerId: baseInfoEntity?.owner?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="catinyApp.baseInfo.home.createOrEditLabel" data-cy="BaseInfoCreateUpdateHeading">
            <Translate contentKey="catinyApp.baseInfo.home.createOrEditLabel">Create or edit a BaseInfo</Translate>
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
                  name='id'
                  required
                  readOnly
                  id='base-info-id'
                  label={translate('global.field.id')}
                  validate={{required: true}}
                />
              ) : null}
              <ValidatedField
                label={translate('catinyApp.baseInfo.uuid')}
                id='base-info-uuid'
                name='uuid'
                data-cy='uuid'
                type='text'
                validate={{
                  required: {value: true, message: translate('entity.validation.required')},
                }}
              />
              <UncontrolledTooltip target='uuidLabel'>
                <Translate contentKey='catinyApp.baseInfo.help.uuid' />
              </UncontrolledTooltip>
              <ValidatedField
                label={translate('catinyApp.baseInfo.processStatus')}
                id='base-info-processStatus'
                name='processStatus'
                data-cy='processStatus'
                type='select'
              >
                <option value='NOT_PROCESSED'>{translate('catinyApp.ProcessStatus.NOT_PROCESSED')}</option>
                <option value='PROCESSING'>{translate('catinyApp.ProcessStatus.PROCESSING')}</option>
                <option value='PROCESSED'>{translate('catinyApp.ProcessStatus.PROCESSED')}</option>
                <option value="NEED_PROCESS">{translate('catinyApp.ProcessStatus.NEED_PROCESS')}</option>
                <option value="NEED_PROCESS_NOW">{translate('catinyApp.ProcessStatus.NEED_PROCESS_NOW')}</option>
                <option value="NEED_HANDLE">{translate('catinyApp.ProcessStatus.NEED_HANDLE')}</option>
                <option value="NEED_HANDLE_NOW">{translate('catinyApp.ProcessStatus.NEED_HANDLE_NOW')}</option>
                <option value="ERROR">{translate('catinyApp.ProcessStatus.ERROR')}</option>
                <option value="DONE">{translate('catinyApp.ProcessStatus.DONE')}</option>
              </ValidatedField>
              <UncontrolledTooltip target="processStatusLabel">
                <Translate contentKey="catinyApp.baseInfo.help.processStatus" />
              </UncontrolledTooltip>
              <ValidatedField
                label={translate('catinyApp.baseInfo.modifiedClass')}
                id="base-info-modifiedClass"
                name="modifiedClass"
                data-cy="modifiedClass"
                type="text"
              />
              <UncontrolledTooltip target="modifiedClassLabel">
                <Translate contentKey="catinyApp.baseInfo.help.modifiedClass" />
              </UncontrolledTooltip>
              <ValidatedField
                label={translate('catinyApp.baseInfo.createdDate')}
                id="base-info-createdDate"
                name="createdDate"
                data-cy="createdDate"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <UncontrolledTooltip target="createdDateLabel">
                <Translate contentKey="catinyApp.baseInfo.help.createdDate" />
              </UncontrolledTooltip>
              <ValidatedField
                label={translate('catinyApp.baseInfo.modifiedDate')}
                id="base-info-modifiedDate"
                name="modifiedDate"
                data-cy="modifiedDate"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <UncontrolledTooltip target="modifiedDateLabel">
                <Translate contentKey="catinyApp.baseInfo.help.modifiedDate" />
              </UncontrolledTooltip>
              <ValidatedField
                label={translate('catinyApp.baseInfo.notes')}
                id="base-info-notes"
                name="notes"
                data-cy="notes"
                type="textarea"
              />
              <UncontrolledTooltip target="notesLabel">
                <Translate contentKey="catinyApp.baseInfo.help.notes" />
              </UncontrolledTooltip>
              <ValidatedField
                label={translate('catinyApp.baseInfo.deleted')}
                id="base-info-deleted"
                name="deleted"
                data-cy="deleted"
                check
                type="checkbox"
              />
              <UncontrolledTooltip target="deletedLabel">
                <Translate contentKey="catinyApp.baseInfo.help.deleted" />
              </UncontrolledTooltip>
              <ValidatedField
                label={translate('catinyApp.baseInfo.priorityIndex')}
                id="base-info-priorityIndex"
                name="priorityIndex"
                data-cy="priorityIndex"
                type="text"
              />
              <UncontrolledTooltip target="priorityIndexLabel">
                <Translate contentKey="catinyApp.baseInfo.help.priorityIndex" />
              </UncontrolledTooltip>
              <ValidatedField
                label={translate('catinyApp.baseInfo.countUse')}
                id="base-info-countUse"
                name="countUse"
                data-cy="countUse"
                type="text"
              />
              <UncontrolledTooltip target="countUseLabel">
                <Translate contentKey="catinyApp.baseInfo.help.countUse" />
              </UncontrolledTooltip>
              <ValidatedField
                id="base-info-classInfo"
                name="classInfoId"
                data-cy="classInfo"
                label={translate('catinyApp.baseInfo.classInfo')}
                type="select"
              >
                <option value="" key="0" />
                {classInfos
                  ? classInfos.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField
                id="base-info-createdBy"
                name="createdById"
                data-cy="createdBy"
                label={translate('catinyApp.baseInfo.createdBy')}
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
                id="base-info-modifiedBy"
                name="modifiedById"
                data-cy="modifiedBy"
                label={translate('catinyApp.baseInfo.modifiedBy')}
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
                id="base-info-owner"
                name="ownerId"
                data-cy="owner"
                label={translate('catinyApp.baseInfo.owner')}
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
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/base-info" replace color="info">
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

export default BaseInfoUpdate;
