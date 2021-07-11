import React, { useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, UncontrolledTooltip, Row, Col } from 'reactstrap';
import { Translate, byteSize } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { getEntity } from './topic-interest.reducer';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const TopicInterestDetail = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  useEffect(() => {
    dispatch(getEntity(props.match.params.id));
  }, []);

  const topicInterestEntity = useAppSelector(state => state.topicInterest.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="topicInterestDetailsHeading">
          <Translate contentKey="catinyApp.topicInterest.detail.title">TopicInterest</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{topicInterestEntity.id}</dd>
          <dt>
            <span id="uuid">
              <Translate contentKey="catinyApp.topicInterest.uuid">Uuid</Translate>
            </span>
            <UncontrolledTooltip target="uuid">
              <Translate contentKey="catinyApp.topicInterest.help.uuid" />
            </UncontrolledTooltip>
          </dt>
          <dd>{topicInterestEntity.uuid}</dd>
          <dt>
            <span id="title">
              <Translate contentKey="catinyApp.topicInterest.title">Title</Translate>
            </span>
          </dt>
          <dd>{topicInterestEntity.title}</dd>
          <dt>
            <span id="content">
              <Translate contentKey="catinyApp.topicInterest.content">Content</Translate>
            </span>
          </dt>
          <dd>{topicInterestEntity.content}</dd>
          <dt>
            <Translate contentKey="catinyApp.topicInterest.baseInfo">Base Info</Translate>
          </dt>
          <dd>{topicInterestEntity.baseInfo ? topicInterestEntity.baseInfo.id : ''}</dd>
          <dt>
            <Translate contentKey="catinyApp.topicInterest.post">Post</Translate>
          </dt>
          <dd>
            {topicInterestEntity.posts
              ? topicInterestEntity.posts.map((val, i) => (
                  <span key={val.id}>
                    <a>{val.id}</a>
                    {topicInterestEntity.posts && i === topicInterestEntity.posts.length - 1 ? '' : ', '}
                  </span>
                ))
              : null}
          </dd>
          <dt>
            <Translate contentKey="catinyApp.topicInterest.pagePost">Page Post</Translate>
          </dt>
          <dd>
            {topicInterestEntity.pagePosts
              ? topicInterestEntity.pagePosts.map((val, i) => (
                  <span key={val.id}>
                    <a>{val.id}</a>
                    {topicInterestEntity.pagePosts && i === topicInterestEntity.pagePosts.length - 1 ? '' : ', '}
                  </span>
                ))
              : null}
          </dd>
          <dt>
            <Translate contentKey="catinyApp.topicInterest.groupPost">Group Post</Translate>
          </dt>
          <dd>
            {topicInterestEntity.groupPosts
              ? topicInterestEntity.groupPosts.map((val, i) => (
                  <span key={val.id}>
                    <a>{val.id}</a>
                    {topicInterestEntity.groupPosts && i === topicInterestEntity.groupPosts.length - 1 ? '' : ', '}
                  </span>
                ))
              : null}
          </dd>
        </dl>
        <Button tag={Link} to="/topic-interest" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/topic-interest/${topicInterestEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default TopicInterestDetail;
