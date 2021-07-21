import React, { useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, UncontrolledTooltip, Row, Col } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { getEntity } from './follow-page.reducer';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const FollowPageDetail = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  useEffect(() => {
    dispatch(getEntity(props.match.params.id));
  }, []);

  const followPageEntity = useAppSelector(state => state.followPage.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="followPageDetailsHeading">
          <Translate contentKey="catinyApp.followPage.detail.title">FollowPage</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{followPageEntity.id}</dd>
          <dt>
            <span id="uuid">
              <Translate contentKey="catinyApp.followPage.uuid">Uuid</Translate>
            </span>
            <UncontrolledTooltip target="uuid">
              <Translate contentKey="catinyApp.followPage.help.uuid" />
            </UncontrolledTooltip>
          </dt>
          <dd>{followPageEntity.uuid}</dd>
          <dt>
            <Translate contentKey="catinyApp.followPage.baseInfo">Base Info</Translate>
          </dt>
          <dd>{followPageEntity.baseInfo ? followPageEntity.baseInfo.id : ''}</dd>
          <dt>
            <Translate contentKey="catinyApp.followPage.followPageDetails">Follow Page Details</Translate>
          </dt>
          <dd>{followPageEntity.followPageDetails ? followPageEntity.followPageDetails.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/follow-page" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/follow-page/${followPageEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default FollowPageDetail;
