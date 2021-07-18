import React, { useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, UncontrolledTooltip, Row, Col } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { getEntity } from './permission.reducer';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const PermissionDetail = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  useEffect(() => {
    dispatch(getEntity(props.match.params.id));
  }, []);

  const permissionEntity = useAppSelector(state => state.permission.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="permissionDetailsHeading">
          <Translate contentKey="catinyApp.permission.detail.title">Permission</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{permissionEntity.id}</dd>
          <dt>
            <span id="read">
              <Translate contentKey="catinyApp.permission.read">Read</Translate>
            </span>
            <UncontrolledTooltip target="read">
              <Translate contentKey="catinyApp.permission.help.read" />
            </UncontrolledTooltip>
          </dt>
          <dd>{permissionEntity.read ? 'true' : 'false'}</dd>
          <dt>
            <span id="write">
              <Translate contentKey="catinyApp.permission.write">Write</Translate>
            </span>
            <UncontrolledTooltip target="write">
              <Translate contentKey="catinyApp.permission.help.write" />
            </UncontrolledTooltip>
          </dt>
          <dd>{permissionEntity.write ? 'true' : 'false'}</dd>
          <dt>
            <span id="share">
              <Translate contentKey="catinyApp.permission.share">Share</Translate>
            </span>
            <UncontrolledTooltip target="share">
              <Translate contentKey="catinyApp.permission.help.share" />
            </UncontrolledTooltip>
          </dt>
          <dd>{permissionEntity.share ? 'true' : 'false'}</dd>
          <dt>
            <span id="delete">
              <Translate contentKey="catinyApp.permission.delete">Delete</Translate>
            </span>
            <UncontrolledTooltip target="delete">
              <Translate contentKey="catinyApp.permission.help.delete" />
            </UncontrolledTooltip>
          </dt>
          <dd>{permissionEntity.delete ? 'true' : 'false'}</dd>
          <dt>
            <span id="add">
              <Translate contentKey="catinyApp.permission.add">Add</Translate>
            </span>
            <UncontrolledTooltip target="add">
              <Translate contentKey="catinyApp.permission.help.add" />
            </UncontrolledTooltip>
          </dt>
          <dd>{permissionEntity.add ? 'true' : 'false'}</dd>
          <dt>
            <span id="level">
              <Translate contentKey="catinyApp.permission.level">Level</Translate>
            </span>
            <UncontrolledTooltip target="level">
              <Translate contentKey="catinyApp.permission.help.level" />
            </UncontrolledTooltip>
          </dt>
          <dd>{permissionEntity.level}</dd>
          <dt>
            <Translate contentKey="catinyApp.permission.baseInfo">Base Info</Translate>
          </dt>
          <dd>{permissionEntity.baseInfo ? permissionEntity.baseInfo.id : ''}</dd>
          <dt>
            <Translate contentKey="catinyApp.permission.masterUser">Master User</Translate>
          </dt>
          <dd>{permissionEntity.masterUser ? permissionEntity.masterUser.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/permission" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/permission/${permissionEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default PermissionDetail;
