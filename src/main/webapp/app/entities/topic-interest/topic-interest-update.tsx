import React, { useState, useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, FormText, UncontrolledTooltip } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IBaseInfo } from 'app/shared/model/base-info.model';
import { getEntities as getBaseInfos } from 'app/entities/base-info/base-info.reducer';
import { IPost } from 'app/shared/model/post.model';
import { getEntities as getPosts } from 'app/entities/post/post.reducer';
import { IPagePost } from 'app/shared/model/page-post.model';
import { getEntities as getPagePosts } from 'app/entities/page-post/page-post.reducer';
import { IGroupPost } from 'app/shared/model/group-post.model';
import { getEntities as getGroupPosts } from 'app/entities/group-post/group-post.reducer';
import { IMasterUser } from 'app/shared/model/master-user.model';
import { getEntities as getMasterUsers } from 'app/entities/master-user/master-user.reducer';
import { getEntity, updateEntity, createEntity, reset } from './topic-interest.reducer';
import { ITopicInterest } from 'app/shared/model/topic-interest.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const TopicInterestUpdate = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  const [isNew] = useState(!props.match.params || !props.match.params.id);

  const baseInfos = useAppSelector(state => state.baseInfo.entities);
  const posts = useAppSelector(state => state.post.entities);
  const pagePosts = useAppSelector(state => state.pagePost.entities);
  const groupPosts = useAppSelector(state => state.groupPost.entities);
  const masterUsers = useAppSelector(state => state.masterUser.entities);
  const topicInterestEntity = useAppSelector(state => state.topicInterest.entity);
  const loading = useAppSelector(state => state.topicInterest.loading);
  const updating = useAppSelector(state => state.topicInterest.updating);
  const updateSuccess = useAppSelector(state => state.topicInterest.updateSuccess);

  const handleClose = () => {
    props.history.push('/topic-interest');
  };

  useEffect(() => {
    if (!isNew) {
      dispatch(getEntity(props.match.params.id));
    }

    dispatch(getBaseInfos({}));
    dispatch(getPosts({}));
    dispatch(getPagePosts({}));
    dispatch(getGroupPosts({}));
    dispatch(getMasterUsers({}));
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  const saveEntity = values => {
    const entity = {
      ...topicInterestEntity,
      ...values,
      posts: mapIdList(values.posts),
      pagePosts: mapIdList(values.pagePosts),
      groupPosts: mapIdList(values.groupPosts),
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
          ...topicInterestEntity,
          baseInfoId: topicInterestEntity?.baseInfo?.id,
          posts: topicInterestEntity?.posts?.map(e => e.id.toString()),
          pagePosts: topicInterestEntity?.pagePosts?.map(e => e.id.toString()),
          groupPosts: topicInterestEntity?.groupPosts?.map(e => e.id.toString()),
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="catinyApp.topicInterest.home.createOrEditLabel" data-cy="TopicInterestCreateUpdateHeading">
            <Translate contentKey="catinyApp.topicInterest.home.createOrEditLabel">Create or edit a TopicInterest</Translate>
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
                  id="topic-interest-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('catinyApp.topicInterest.uuid')}
                id="topic-interest-uuid"
                name="uuid"
                data-cy="uuid"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <UncontrolledTooltip target="uuidLabel">
                <Translate contentKey="catinyApp.topicInterest.help.uuid" />
              </UncontrolledTooltip>
              <ValidatedField
                label={translate('catinyApp.topicInterest.title')}
                id="topic-interest-title"
                name="title"
                data-cy="title"
                type="text"
              />
              <ValidatedField
                label={translate('catinyApp.topicInterest.content')}
                id="topic-interest-content"
                name="content"
                data-cy="content"
                type="textarea"
              />
              <ValidatedField
                id="topic-interest-baseInfo"
                name="baseInfoId"
                data-cy="baseInfo"
                label={translate('catinyApp.topicInterest.baseInfo')}
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
                label={translate('catinyApp.topicInterest.post')}
                id="topic-interest-post"
                data-cy="post"
                type="select"
                multiple
                name="posts"
              >
                <option value="" key="0" />
                {posts
                  ? posts.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField
                label={translate('catinyApp.topicInterest.pagePost')}
                id="topic-interest-pagePost"
                data-cy="pagePost"
                type="select"
                multiple
                name="pagePosts"
              >
                <option value="" key="0" />
                {pagePosts
                  ? pagePosts.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField
                label={translate('catinyApp.topicInterest.groupPost')}
                id="topic-interest-groupPost"
                data-cy="groupPost"
                type="select"
                multiple
                name="groupPosts"
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
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/topic-interest" replace color="info">
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

export default TopicInterestUpdate;
