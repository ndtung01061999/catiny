import React, { useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, UncontrolledTooltip, Row, Col } from 'reactstrap';
import { Translate, byteSize } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { getEntity } from './post-comment.reducer';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const PostCommentDetail = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  useEffect(() => {
    dispatch(getEntity(props.match.params.id));
  }, []);

  const postCommentEntity = useAppSelector(state => state.postComment.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="postCommentDetailsHeading">
          <Translate contentKey="catinyApp.postComment.detail.title">PostComment</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{postCommentEntity.id}</dd>
          <dt>
            <span id="uuid">
              <Translate contentKey="catinyApp.postComment.uuid">Uuid</Translate>
            </span>
            <UncontrolledTooltip target="uuid">
              <Translate contentKey="catinyApp.postComment.help.uuid" />
            </UncontrolledTooltip>
          </dt>
          <dd>{postCommentEntity.uuid}</dd>
          <dt>
            <span id="content">
              <Translate contentKey="catinyApp.postComment.content">Content</Translate>
            </span>
          </dt>
          <dd>{postCommentEntity.content}</dd>
          <dt>
            <Translate contentKey="catinyApp.postComment.baseInfo">Base Info</Translate>
          </dt>
          <dd>{postCommentEntity.baseInfo ? postCommentEntity.baseInfo.id : ''}</dd>
          <dt>
            <Translate contentKey="catinyApp.postComment.post">Post</Translate>
          </dt>
          <dd>{postCommentEntity.post ? postCommentEntity.post.id : ''}</dd>
          <dt>
            <Translate contentKey="catinyApp.postComment.commentParent">Comment Parent</Translate>
          </dt>
          <dd>{postCommentEntity.commentParent ? postCommentEntity.commentParent.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/post-comment" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/post-comment/${postCommentEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default PostCommentDetail;
