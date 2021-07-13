import React, { useState, useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, FormText, UncontrolledTooltip } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IUserProfile } from 'app/shared/model/user-profile.model';
import { getEntities as getUserProfiles } from 'app/entities/user-profile/user-profile.reducer';
import { IAccountStatus } from 'app/shared/model/account-status.model';
import { getEntities as getAccountStatuses } from 'app/entities/account-status/account-status.reducer';
import { IDeviceStatus } from 'app/shared/model/device-status.model';
import { getEntities as getDeviceStatuses } from 'app/entities/device-status/device-status.reducer';
import { IFriend } from 'app/shared/model/friend.model';
import { getEntities as getFriends } from 'app/entities/friend/friend.reducer';
import { IFollowUser } from 'app/shared/model/follow-user.model';
import { getEntities as getFollowUsers } from 'app/entities/follow-user/follow-user.reducer';
import { IFollowGroup } from 'app/shared/model/follow-group.model';
import { getEntities as getFollowGroups } from 'app/entities/follow-group/follow-group.reducer';
import { IFollowPage } from 'app/shared/model/follow-page.model';
import { getEntities as getFollowPages } from 'app/entities/follow-page/follow-page.reducer';
import { IFileInfo } from 'app/shared/model/file-info.model';
import { getEntities as getFileInfos } from 'app/entities/file-info/file-info.reducer';
import { IPagePost } from 'app/shared/model/page-post.model';
import { getEntities as getPagePosts } from 'app/entities/page-post/page-post.reducer';
import { IPageProfile } from 'app/shared/model/page-profile.model';
import { getEntities as getPageProfiles } from 'app/entities/page-profile/page-profile.reducer';
import { IGroupPost } from 'app/shared/model/group-post.model';
import { getEntities as getGroupPosts } from 'app/entities/group-post/group-post.reducer';
import { IPost } from 'app/shared/model/post.model';
import { getEntities as getPosts } from 'app/entities/post/post.reducer';
import { IPostComment } from 'app/shared/model/post-comment.model';
import { getEntities as getPostComments } from 'app/entities/post-comment/post-comment.reducer';
import { IPostLike } from 'app/shared/model/post-like.model';
import { getEntities as getPostLikes } from 'app/entities/post-like/post-like.reducer';
import { IGroupProfile } from 'app/shared/model/group-profile.model';
import { getEntities as getGroupProfiles } from 'app/entities/group-profile/group-profile.reducer';
import { INewsFeed } from 'app/shared/model/news-feed.model';
import { getEntities as getNewsFeeds } from 'app/entities/news-feed/news-feed.reducer';
import { IMessageGroup } from 'app/shared/model/message-group.model';
import { getEntities as getMessageGroups } from 'app/entities/message-group/message-group.reducer';
import { IMessageContent } from 'app/shared/model/message-content.model';
import { getEntities as getMessageContents } from 'app/entities/message-content/message-content.reducer';
import { IRankUser } from 'app/shared/model/rank-user.model';
import { getEntities as getRankUsers } from 'app/entities/rank-user/rank-user.reducer';
import { IRankGroup } from 'app/shared/model/rank-group.model';
import { getEntities as getRankGroups } from 'app/entities/rank-group/rank-group.reducer';
import { INotification } from 'app/shared/model/notification.model';
import { getEntities as getNotifications } from 'app/entities/notification/notification.reducer';
import { IAlbum } from 'app/shared/model/album.model';
import { getEntities as getAlbums } from 'app/entities/album/album.reducer';
import { IVideo } from 'app/shared/model/video.model';
import { getEntities as getVideos } from 'app/entities/video/video.reducer';
import { IImage } from 'app/shared/model/image.model';
import { getEntities as getImages } from 'app/entities/image/image.reducer';
import { IVideoStream } from 'app/shared/model/video-stream.model';
import { getEntities as getVideoStreams } from 'app/entities/video-stream/video-stream.reducer';
import { IVideoLiveStreamBuffer } from 'app/shared/model/video-live-stream-buffer.model';
import { getEntities as getVideoLiveStreamBuffers } from 'app/entities/video-live-stream-buffer/video-live-stream-buffer.reducer';
import { ITopicInterest } from 'app/shared/model/topic-interest.model';
import { getEntities as getTopicInterests } from 'app/entities/topic-interest/topic-interest.reducer';
import { ITodoList } from 'app/shared/model/todo-list.model';
import { getEntities as getTodoLists } from 'app/entities/todo-list/todo-list.reducer';
import { IEvent } from 'app/shared/model/event.model';
import { getEntities as getEvents } from 'app/entities/event/event.reducer';
import { getEntity, updateEntity, createEntity, reset } from './base-info.reducer';
import { IBaseInfo } from 'app/shared/model/base-info.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const BaseInfoUpdate = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  const [isNew] = useState(!props.match.params || !props.match.params.id);

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
                  name="id"
                  required
                  readOnly
                  id="base-info-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('catinyApp.baseInfo.processStatus')}
                id="base-info-processStatus"
                name="processStatus"
                data-cy="processStatus"
                type="select"
              >
                <option value="NOT_PROCESSED">{translate('catinyApp.ProcessStatus.NOT_PROCESSED')}</option>
                <option value="PROCESSING">{translate('catinyApp.ProcessStatus.PROCESSING')}</option>
                <option value="PROCESSED">{translate('catinyApp.ProcessStatus.PROCESSED')}</option>
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
                label={translate('catinyApp.baseInfo.owner')}
                id="base-info-owner"
                name="owner"
                data-cy="owner"
                type="textarea"
              />
              <UncontrolledTooltip target="ownerLabel">
                <Translate contentKey="catinyApp.baseInfo.help.owner" />
              </UncontrolledTooltip>
              <ValidatedField label={translate('catinyApp.baseInfo.role')} id="base-info-role" name="role" data-cy="role" type="textarea" />
              <UncontrolledTooltip target="roleLabel">
                <Translate contentKey="catinyApp.baseInfo.help.role" />
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
                label={translate('catinyApp.baseInfo.createdBy')}
                id="base-info-createdBy"
                name="createdBy"
                data-cy="createdBy"
                type="text"
              />
              <UncontrolledTooltip target="createdByLabel">
                <Translate contentKey="catinyApp.baseInfo.help.createdBy" />
              </UncontrolledTooltip>
              <ValidatedField
                label={translate('catinyApp.baseInfo.modifiedBy')}
                id="base-info-modifiedBy"
                name="modifiedBy"
                data-cy="modifiedBy"
                type="text"
              />
              <UncontrolledTooltip target="modifiedByLabel">
                <Translate contentKey="catinyApp.baseInfo.help.modifiedBy" />
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
                label={translate('catinyApp.baseInfo.historyUpdate')}
                id="base-info-historyUpdate"
                name="historyUpdate"
                data-cy="historyUpdate"
                type="textarea"
              />
              <UncontrolledTooltip target="historyUpdateLabel">
                <Translate contentKey="catinyApp.baseInfo.help.historyUpdate" />
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
