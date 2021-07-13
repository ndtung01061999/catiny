import React, { useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, UncontrolledTooltip, Row, Col } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { getEntity } from './rank-user.reducer';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const RankUserDetail = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  useEffect(() => {
    dispatch(getEntity(props.match.params.id));
  }, []);

  const rankUserEntity = useAppSelector(state => state.rankUser.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="rankUserDetailsHeading">
          <Translate contentKey="catinyApp.rankUser.detail.title">RankUser</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{rankUserEntity.id}</dd>
          <dt>
            <span id="uuid">
              <Translate contentKey="catinyApp.rankUser.uuid">Uuid</Translate>
            </span>
            <UncontrolledTooltip target="uuid">
              <Translate contentKey="catinyApp.rankUser.help.uuid" />
            </UncontrolledTooltip>
          </dt>
          <dd>{rankUserEntity.uuid}</dd>
          <dt>
            <span id="ratingPoints">
              <Translate contentKey="catinyApp.rankUser.ratingPoints">Rating Points</Translate>
            </span>
          </dt>
          <dd>{rankUserEntity.ratingPoints}</dd>
          <dt>
            <Translate contentKey="catinyApp.rankUser.baseInfo">Base Info</Translate>
          </dt>
          <dd>{rankUserEntity.baseInfo ? rankUserEntity.baseInfo.id : ''}</dd>
          <dt>
            <Translate contentKey="catinyApp.rankUser.rankGroup">Rank Group</Translate>
          </dt>
          <dd>{rankUserEntity.rankGroup ? rankUserEntity.rankGroup.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/rank-user" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/rank-user/${rankUserEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default RankUserDetail;
