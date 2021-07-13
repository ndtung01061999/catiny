import React, { useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, UncontrolledTooltip, Row, Col } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { getEntity } from './friend.reducer';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const FriendDetail = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  useEffect(() => {
    dispatch(getEntity(props.match.params.id));
  }, []);

  const friendEntity = useAppSelector(state => state.friend.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="friendDetailsHeading">
          <Translate contentKey="catinyApp.friend.detail.title">Friend</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{friendEntity.id}</dd>
          <dt>
            <span id="uuid">
              <Translate contentKey="catinyApp.friend.uuid">Uuid</Translate>
            </span>
            <UncontrolledTooltip target="uuid">
              <Translate contentKey="catinyApp.friend.help.uuid" />
            </UncontrolledTooltip>
          </dt>
          <dd>{friendEntity.uuid}</dd>
          <dt>
            <span id="friendType">
              <Translate contentKey="catinyApp.friend.friendType">Friend Type</Translate>
            </span>
          </dt>
          <dd>{friendEntity.friendType}</dd>
          <dt>
            <Translate contentKey="catinyApp.friend.baseInfo">Base Info</Translate>
          </dt>
          <dd>{friendEntity.baseInfo ? friendEntity.baseInfo.id : ''}</dd>
          <dt>
            <Translate contentKey="catinyApp.friend.friendDetails">Friend Details</Translate>
          </dt>
          <dd>{friendEntity.friendDetails ? friendEntity.friendDetails.id : ''}</dd>
          <dt>
            <Translate contentKey="catinyApp.friend.masterUser">Master User</Translate>
          </dt>
          <dd>{friendEntity.masterUser ? friendEntity.masterUser.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/friend" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/friend/${friendEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default FriendDetail;
