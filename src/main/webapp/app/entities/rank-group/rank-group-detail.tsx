import React, { useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, UncontrolledTooltip, Row, Col } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { getEntity } from './rank-group.reducer';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const RankGroupDetail = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  useEffect(() => {
    dispatch(getEntity(props.match.params.id));
  }, []);

  const rankGroupEntity = useAppSelector(state => state.rankGroup.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="rankGroupDetailsHeading">
          <Translate contentKey="catinyApp.rankGroup.detail.title">RankGroup</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{rankGroupEntity.id}</dd>
          <dt>
            <span id="uuid">
              <Translate contentKey="catinyApp.rankGroup.uuid">Uuid</Translate>
            </span>
            <UncontrolledTooltip target="uuid">
              <Translate contentKey="catinyApp.rankGroup.help.uuid" />
            </UncontrolledTooltip>
          </dt>
          <dd>{rankGroupEntity.uuid}</dd>
          <dt>
            <Translate contentKey="catinyApp.rankGroup.baseInfo">Base Info</Translate>
          </dt>
          <dd>{rankGroupEntity.baseInfo ? rankGroupEntity.baseInfo.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/rank-group" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/rank-group/${rankGroupEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default RankGroupDetail;
