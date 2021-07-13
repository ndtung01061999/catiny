import React, { useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, UncontrolledTooltip, Row, Col } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { getEntity } from './group-profile.reducer';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const GroupProfileDetail = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  useEffect(() => {
    dispatch(getEntity(props.match.params.id));
  }, []);

  const groupProfileEntity = useAppSelector(state => state.groupProfile.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="groupProfileDetailsHeading">
          <Translate contentKey="catinyApp.groupProfile.detail.title">GroupProfile</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{groupProfileEntity.id}</dd>
          <dt>
            <span id="uuid">
              <Translate contentKey="catinyApp.groupProfile.uuid">Uuid</Translate>
            </span>
            <UncontrolledTooltip target="uuid">
              <Translate contentKey="catinyApp.groupProfile.help.uuid" />
            </UncontrolledTooltip>
          </dt>
          <dd>{groupProfileEntity.uuid}</dd>
          <dt>
            <Translate contentKey="catinyApp.groupProfile.baseInfo">Base Info</Translate>
          </dt>
          <dd>{groupProfileEntity.baseInfo ? groupProfileEntity.baseInfo.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/group-profile" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/group-profile/${groupProfileEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default GroupProfileDetail;
