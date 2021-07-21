import React, { useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, UncontrolledTooltip, Row, Col } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { getEntity } from './post-like.reducer';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const PostLikeDetail = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  useEffect(() => {
    dispatch(getEntity(props.match.params.id));
  }, []);

  const postLikeEntity = useAppSelector(state => state.postLike.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="postLikeDetailsHeading">
          <Translate contentKey="catinyApp.postLike.detail.title">PostLike</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{postLikeEntity.id}</dd>
          <dt>
            <span id="uuid">
              <Translate contentKey="catinyApp.postLike.uuid">Uuid</Translate>
            </span>
            <UncontrolledTooltip target="uuid">
              <Translate contentKey="catinyApp.postLike.help.uuid" />
            </UncontrolledTooltip>
          </dt>
          <dd>{postLikeEntity.uuid}</dd>
          <dt>
            <Translate contentKey="catinyApp.postLike.baseInfo">Base Info</Translate>
          </dt>
          <dd>{postLikeEntity.baseInfo ? postLikeEntity.baseInfo.id : ''}</dd>
          <dt>
            <Translate contentKey="catinyApp.postLike.post">Post</Translate>
          </dt>
          <dd>{postLikeEntity.post ? postLikeEntity.post.id : ''}</dd>
          <dt>
            <Translate contentKey="catinyApp.postLike.postComment">Post Comment</Translate>
          </dt>
          <dd>{postLikeEntity.postComment ? postLikeEntity.postComment.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/post-like" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/post-like/${postLikeEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default PostLikeDetail;
