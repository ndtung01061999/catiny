import React, { useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, UncontrolledTooltip, Row, Col } from 'reactstrap';
import { Translate, byteSize } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { getEntity } from './group-post.reducer';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const GroupPostDetail = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  useEffect(() => {
    dispatch(getEntity(props.match.params.id));
  }, []);

  const groupPostEntity = useAppSelector(state => state.groupPost.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="groupPostDetailsHeading">
          <Translate contentKey="catinyApp.groupPost.detail.title">GroupPost</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{groupPostEntity.id}</dd>
          <dt>
            <span id="uuid">
              <Translate contentKey="catinyApp.groupPost.uuid">Uuid</Translate>
            </span>
            <UncontrolledTooltip target="uuid">
              <Translate contentKey="catinyApp.groupPost.help.uuid" />
            </UncontrolledTooltip>
          </dt>
          <dd>{groupPostEntity.uuid}</dd>
          <dt>
            <span id="name">
              <Translate contentKey="catinyApp.groupPost.name">Name</Translate>
            </span>
            <UncontrolledTooltip target="name">
              <Translate contentKey="catinyApp.groupPost.help.name" />
            </UncontrolledTooltip>
          </dt>
          <dd>{groupPostEntity.name}</dd>
          <dt>
            <span id="avatar">
              <Translate contentKey="catinyApp.groupPost.avatar">Avatar</Translate>
            </span>
            <UncontrolledTooltip target="avatar">
              <Translate contentKey="catinyApp.groupPost.help.avatar" />
            </UncontrolledTooltip>
          </dt>
          <dd>{groupPostEntity.avatar}</dd>
          <dt>
            <span id="quickInfo">
              <Translate contentKey="catinyApp.groupPost.quickInfo">Quick Info</Translate>
            </span>
            <UncontrolledTooltip target="quickInfo">
              <Translate contentKey="catinyApp.groupPost.help.quickInfo" />
            </UncontrolledTooltip>
          </dt>
          <dd>{groupPostEntity.quickInfo}</dd>
          <dt>
            <Translate contentKey="catinyApp.groupPost.profile">Profile</Translate>
          </dt>
          <dd>{groupPostEntity.profile ? groupPostEntity.profile.id : ''}</dd>
          <dt>
            <Translate contentKey="catinyApp.groupPost.baseInfo">Base Info</Translate>
          </dt>
          <dd>{groupPostEntity.baseInfo ? groupPostEntity.baseInfo.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/group-post" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/group-post/${groupPostEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default GroupPostDetail;
