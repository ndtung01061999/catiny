import React, { useState, useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, FormText, UncontrolledTooltip } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IBaseInfo } from 'app/shared/model/base-info.model';
import { getEntities as getBaseInfos } from 'app/entities/base-info/base-info.reducer';
import { getEntities as getPosts } from 'app/entities/post/post.reducer';
import { IGroupPost } from 'app/shared/model/group-post.model';
import { getEntities as getGroupPosts } from 'app/entities/group-post/group-post.reducer';
import { IPagePost } from 'app/shared/model/page-post.model';
import { getEntities as getPagePosts } from 'app/entities/page-post/page-post.reducer';
import { ITopicInterest } from 'app/shared/model/topic-interest.model';
import { getEntities as getTopicInterests } from 'app/entities/topic-interest/topic-interest.reducer';
import { getEntity, updateEntity, createEntity, reset } from './post.reducer';
import { IPost } from 'app/shared/model/post.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const PostUpdate = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  const [isNew] = useState(!props.match.params || !props.match.params.id);

  const baseInfos = useAppSelector(state => state.baseInfo.entities);
  const posts = useAppSelector(state => state.post.entities);
  const groupPosts = useAppSelector(state => state.groupPost.entities);
  const pagePosts = useAppSelector(state => state.pagePost.entities);
  const topicInterests = useAppSelector(state => state.topicInterest.entities);
  const postEntity = useAppSelector(state => state.post.entity);
  const loading = useAppSelector(state => state.post.loading);
  const updating = useAppSelector(state => state.post.updating);
  const updateSuccess = useAppSelector(state => state.post.updateSuccess);

  const handleClose = () => {
    props.history.push('/post');
  };

  useEffect(() => {
    if (!isNew) {
      dispatch(getEntity(props.match.params.id));
    }

    dispatch(getBaseInfos({}));
    dispatch(getPosts({}));
    dispatch(getGroupPosts({}));
    dispatch(getPagePosts({}));
    dispatch(getTopicInterests({}));
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  const saveEntity = values => {
    const entity = {
      ...postEntity,
      ...values,
      baseInfo: baseInfos.find(it => it.id.toString() === values.baseInfoId.toString()),
      postShareParent: posts.find(it => it.id.toString() === values.postShareParentId.toString()),
      groupPost: groupPosts.find(it => it.id.toString() === values.groupPostId.toString()),
      pagePost: pagePosts.find(it => it.id.toString() === values.pagePostId.toString()),
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
          ...postEntity,
          postInType: 'GROUP',
          postType: 'SIMPLE',
          baseInfoId: postEntity?.baseInfo?.id,
          groupPostId: postEntity?.groupPost?.id,
          pagePostId: postEntity?.pagePost?.id,
          postShareParentId: postEntity?.postShareParent?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="catinyApp.post.home.createOrEditLabel" data-cy="PostCreateUpdateHeading">
            <Translate contentKey="catinyApp.post.home.createOrEditLabel">Create or edit a Post</Translate>
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
                  id="post-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('catinyApp.post.uuid')}
                id="post-uuid"
                name="uuid"
                data-cy="uuid"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <UncontrolledTooltip target="uuidLabel">
                <Translate contentKey="catinyApp.post.help.uuid" />
              </UncontrolledTooltip>
              <ValidatedField
                label={translate('catinyApp.post.postInType')}
                id="post-postInType"
                name="postInType"
                data-cy="postInType"
                type="select"
              >
                <option value="GROUP">{translate('catinyApp.PostInType.GROUP')}</option>
                <option value="USER">{translate('catinyApp.PostInType.USER')}</option>
                <option value="PAGE">{translate('catinyApp.PostInType.PAGE')}</option>
              </ValidatedField>
              <UncontrolledTooltip target="postInTypeLabel">
                <Translate contentKey="catinyApp.post.help.postInType" />
              </UncontrolledTooltip>
              <ValidatedField
                label={translate('catinyApp.post.postType')}
                id="post-postType"
                name="postType"
                data-cy="postType"
                type="select"
              >
                <option value="SIMPLE">{translate('catinyApp.PostType.SIMPLE')}</option>
                <option value="ADVANCE">{translate('catinyApp.PostType.ADVANCE')}</option>
                <option value="FROALA">{translate('catinyApp.PostType.FROALA')}</option>
              </ValidatedField>
              <UncontrolledTooltip target="postTypeLabel">
                <Translate contentKey="catinyApp.post.help.postType" />
              </UncontrolledTooltip>
              <ValidatedField
                label={translate('catinyApp.post.content')}
                id="post-content"
                name="content"
                data-cy="content"
                type="textarea"
              />
              <UncontrolledTooltip target="contentLabel">
                <Translate contentKey="catinyApp.post.help.content" />
              </UncontrolledTooltip>
              <ValidatedField
                label={translate('catinyApp.post.searchField')}
                id="post-searchField"
                name="searchField"
                data-cy="searchField"
                type="textarea"
              />
              <ValidatedField
                id="post-baseInfo"
                name="baseInfoId"
                data-cy="baseInfo"
                label={translate('catinyApp.post.baseInfo')}
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
                id="post-groupPost"
                name="groupPostId"
                data-cy="groupPost"
                label={translate('catinyApp.post.groupPost')}
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
              <ValidatedField
                id="post-pagePost"
                name="pagePostId"
                data-cy="pagePost"
                label={translate('catinyApp.post.pagePost')}
                type="select"
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
                id="post-postShareParent"
                name="postShareParentId"
                data-cy="postShareParent"
                label={translate('catinyApp.post.postShareParent')}
                type="select"
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
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/post" replace color="info">
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

export default PostUpdate;
