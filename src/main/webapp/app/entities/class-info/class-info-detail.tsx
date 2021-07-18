import React, { useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, UncontrolledTooltip, Row, Col } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { getEntity } from './class-info.reducer';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const ClassInfoDetail = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  useEffect(() => {
    dispatch(getEntity(props.match.params.id));
  }, []);

  const classInfoEntity = useAppSelector(state => state.classInfo.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="classInfoDetailsHeading">
          <Translate contentKey="catinyApp.classInfo.detail.title">ClassInfo</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{classInfoEntity.id}</dd>
          <dt>
            <span id="uuid">
              <Translate contentKey="catinyApp.classInfo.uuid">Uuid</Translate>
            </span>
            <UncontrolledTooltip target="uuid">
              <Translate contentKey="catinyApp.classInfo.help.uuid" />
            </UncontrolledTooltip>
          </dt>
          <dd>{classInfoEntity.uuid}</dd>
          <dt>
            <span id="packageName">
              <Translate contentKey="catinyApp.classInfo.packageName">Package Name</Translate>
            </span>
            <UncontrolledTooltip target="packageName">
              <Translate contentKey="catinyApp.classInfo.help.packageName" />
            </UncontrolledTooltip>
          </dt>
          <dd>{classInfoEntity.packageName}</dd>
          <dt>
            <span id="fullName">
              <Translate contentKey="catinyApp.classInfo.fullName">Full Name</Translate>
            </span>
            <UncontrolledTooltip target="fullName">
              <Translate contentKey="catinyApp.classInfo.help.fullName" />
            </UncontrolledTooltip>
          </dt>
          <dd>{classInfoEntity.fullName}</dd>
          <dt>
            <span id="className">
              <Translate contentKey="catinyApp.classInfo.className">Class Name</Translate>
            </span>
            <UncontrolledTooltip target="className">
              <Translate contentKey="catinyApp.classInfo.help.className" />
            </UncontrolledTooltip>
          </dt>
          <dd>{classInfoEntity.className}</dd>
        </dl>
        <Button tag={Link} to="/class-info" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/class-info/${classInfoEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default ClassInfoDetail;
