import React, { useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, UncontrolledTooltip, Row, Col } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { getEntity } from './page-profile.reducer';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const PageProfileDetail = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  useEffect(() => {
    dispatch(getEntity(props.match.params.id));
  }, []);

  const pageProfileEntity = useAppSelector(state => state.pageProfile.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="pageProfileDetailsHeading">
          <Translate contentKey="catinyApp.pageProfile.detail.title">PageProfile</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{pageProfileEntity.id}</dd>
          <dt>
            <span id="uuid">
              <Translate contentKey="catinyApp.pageProfile.uuid">Uuid</Translate>
            </span>
            <UncontrolledTooltip target="uuid">
              <Translate contentKey="catinyApp.pageProfile.help.uuid" />
            </UncontrolledTooltip>
          </dt>
          <dd>{pageProfileEntity.uuid}</dd>
          <dt>
            <Translate contentKey="catinyApp.pageProfile.baseInfo">Base Info</Translate>
          </dt>
          <dd>{pageProfileEntity.baseInfo ? pageProfileEntity.baseInfo.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/page-profile" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/page-profile/${pageProfileEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default PageProfileDetail;
