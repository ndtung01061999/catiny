import React, { useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, UncontrolledTooltip, Row, Col } from 'reactstrap';
import { Translate, byteSize } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { getEntity } from './post.reducer';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const PostDetail = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  useEffect(() => {
    dispatch(getEntity(props.match.params.id));
  }, []);

  const postEntity = useAppSelector(state => state.post.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="postDetailsHeading">
          <Translate contentKey="catinyApp.post.detail.title">Post</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{postEntity.id}</dd>
          <dt>
            <span id="uuid">
              <Translate contentKey="catinyApp.post.uuid">Uuid</Translate>
            </span>
            <UncontrolledTooltip target="uuid">
              <Translate contentKey="catinyApp.post.help.uuid" />
            </UncontrolledTooltip>
          </dt>
          <dd>{postEntity.uuid}</dd>
          <dt>
            <span id="postInType">
              <Translate contentKey="catinyApp.post.postInType">Post In Type</Translate>
            </span>
            <UncontrolledTooltip target="postInType">
              <Translate contentKey="catinyApp.post.help.postInType" />
            </UncontrolledTooltip>
          </dt>
          <dd>{postEntity.postInType}</dd>
          <dt>
            <span id="postType">
              <Translate contentKey="catinyApp.post.postType">Post Type</Translate>
            </span>
            <UncontrolledTooltip target="postType">
              <Translate contentKey="catinyApp.post.help.postType" />
            </UncontrolledTooltip>
          </dt>
          <dd>{postEntity.postType}</dd>
          <dt>
            <span id="content">
              <Translate contentKey="catinyApp.post.content">Content</Translate>
            </span>
            <UncontrolledTooltip target="content">
              <Translate contentKey="catinyApp.post.help.content" />
            </UncontrolledTooltip>
          </dt>
          <dd>{postEntity.content}</dd>
          <dt>
            <span id="searchField">
              <Translate contentKey="catinyApp.post.searchField">Search Field</Translate>
            </span>
          </dt>
          <dd>{postEntity.searchField}</dd>
          <dt>
            <Translate contentKey="catinyApp.post.baseInfo">Base Info</Translate>
          </dt>
          <dd>{postEntity.baseInfo ? postEntity.baseInfo.id : ''}</dd>
          <dt>
            <Translate contentKey="catinyApp.post.groupPost">Group Post</Translate>
          </dt>
          <dd>{postEntity.groupPost ? postEntity.groupPost.id : ''}</dd>
          <dt>
            <Translate contentKey="catinyApp.post.pagePost">Page Post</Translate>
          </dt>
          <dd>{postEntity.pagePost ? postEntity.pagePost.id : ''}</dd>
          <dt>
            <Translate contentKey="catinyApp.post.postShareParent">Post Share Parent</Translate>
          </dt>
          <dd>{postEntity.postShareParent ? postEntity.postShareParent.id : ''}</dd>
          <dt>
            <Translate contentKey="catinyApp.post.poster">Poster</Translate>
          </dt>
          <dd>{postEntity.poster ? postEntity.poster.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/post" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/post/${postEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default PostDetail;
