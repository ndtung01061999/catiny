import React, { useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, UncontrolledTooltip, Row, Col } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { getEntity } from './news-feed.reducer';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const NewsFeedDetail = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  useEffect(() => {
    dispatch(getEntity(props.match.params.id));
  }, []);

  const newsFeedEntity = useAppSelector(state => state.newsFeed.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="newsFeedDetailsHeading">
          <Translate contentKey="catinyApp.newsFeed.detail.title">NewsFeed</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{newsFeedEntity.id}</dd>
          <dt>
            <span id="uuid">
              <Translate contentKey="catinyApp.newsFeed.uuid">Uuid</Translate>
            </span>
            <UncontrolledTooltip target="uuid">
              <Translate contentKey="catinyApp.newsFeed.help.uuid" />
            </UncontrolledTooltip>
          </dt>
          <dd>{newsFeedEntity.uuid}</dd>
          <dt>
            <span id="score">
              <Translate contentKey="catinyApp.newsFeed.score">Score</Translate>
            </span>
          </dt>
          <dd>{newsFeedEntity.score}</dd>
          <dt>
            <Translate contentKey="catinyApp.newsFeed.baseInfo">Base Info</Translate>
          </dt>
          <dd>{newsFeedEntity.baseInfo ? newsFeedEntity.baseInfo.id : ''}</dd>
          <dt>
            <Translate contentKey="catinyApp.newsFeed.post">Post</Translate>
          </dt>
          <dd>{newsFeedEntity.post ? newsFeedEntity.post.id : ''}</dd>
          <dt>
            <Translate contentKey="catinyApp.newsFeed.masterUser">Master User</Translate>
          </dt>
          <dd>{newsFeedEntity.masterUser ? newsFeedEntity.masterUser.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/news-feed" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/news-feed/${newsFeedEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default NewsFeedDetail;
