import React, { useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, UncontrolledTooltip, Row, Col } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { getEntity } from './follow-user.reducer';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const FollowUserDetail = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  useEffect(() => {
    dispatch(getEntity(props.match.params.id));
  }, []);

  const followUserEntity = useAppSelector(state => state.followUser.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="followUserDetailsHeading">
          <Translate contentKey="catinyApp.followUser.detail.title">FollowUser</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{followUserEntity.id}</dd>
          <dt>
            <span id="uuid">
              <Translate contentKey="catinyApp.followUser.uuid">Uuid</Translate>
            </span>
            <UncontrolledTooltip target="uuid">
              <Translate contentKey="catinyApp.followUser.help.uuid" />
            </UncontrolledTooltip>
          </dt>
          <dd>{followUserEntity.uuid}</dd>
          <dt>
            <Translate contentKey="catinyApp.followUser.baseInfo">Base Info</Translate>
          </dt>
          <dd>{followUserEntity.baseInfo ? followUserEntity.baseInfo.id : ''}</dd>
          <dt>
            <Translate contentKey="catinyApp.followUser.followUserDetails">Follow User Details</Translate>
          </dt>
          <dd>{followUserEntity.followUserDetails ? followUserEntity.followUserDetails.id : ''}</dd>
          <dt>
            <Translate contentKey="catinyApp.followUser.masterUser">Master User</Translate>
          </dt>
          <dd>{followUserEntity.masterUser ? followUserEntity.masterUser.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/follow-user" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/follow-user/${followUserEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default FollowUserDetail;
