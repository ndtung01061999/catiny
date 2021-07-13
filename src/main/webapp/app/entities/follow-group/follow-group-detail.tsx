import React, { useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, UncontrolledTooltip, Row, Col } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { getEntity } from './follow-group.reducer';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const FollowGroupDetail = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  useEffect(() => {
    dispatch(getEntity(props.match.params.id));
  }, []);

  const followGroupEntity = useAppSelector(state => state.followGroup.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="followGroupDetailsHeading">
          <Translate contentKey="catinyApp.followGroup.detail.title">FollowGroup</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{followGroupEntity.id}</dd>
          <dt>
            <span id="uuid">
              <Translate contentKey="catinyApp.followGroup.uuid">Uuid</Translate>
            </span>
            <UncontrolledTooltip target="uuid">
              <Translate contentKey="catinyApp.followGroup.help.uuid" />
            </UncontrolledTooltip>
          </dt>
          <dd>{followGroupEntity.uuid}</dd>
          <dt>
            <Translate contentKey="catinyApp.followGroup.baseInfo">Base Info</Translate>
          </dt>
          <dd>{followGroupEntity.baseInfo ? followGroupEntity.baseInfo.id : ''}</dd>
          <dt>
            <Translate contentKey="catinyApp.followGroup.followGroupDetails">Follow Group Details</Translate>
          </dt>
          <dd>{followGroupEntity.followGroupDetails ? followGroupEntity.followGroupDetails.id : ''}</dd>
          <dt>
            <Translate contentKey="catinyApp.followGroup.masterUser">Master User</Translate>
          </dt>
          <dd>{followGroupEntity.masterUser ? followGroupEntity.masterUser.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/follow-group" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/follow-group/${followGroupEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default FollowGroupDetail;
