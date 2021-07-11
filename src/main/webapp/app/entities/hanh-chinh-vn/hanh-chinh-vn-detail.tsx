import React, { useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, UncontrolledTooltip, Row, Col } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { getEntity } from './hanh-chinh-vn.reducer';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const HanhChinhVNDetail = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  useEffect(() => {
    dispatch(getEntity(props.match.params.id));
  }, []);

  const hanhChinhVNEntity = useAppSelector(state => state.hanhChinhVN.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="hanhChinhVNDetailsHeading">
          <Translate contentKey="catinyApp.hanhChinhVN.detail.title">HanhChinhVN</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{hanhChinhVNEntity.id}</dd>
          <dt>
            <span id="name">
              <Translate contentKey="catinyApp.hanhChinhVN.name">Name</Translate>
            </span>
            <UncontrolledTooltip target="name">
              <Translate contentKey="catinyApp.hanhChinhVN.help.name" />
            </UncontrolledTooltip>
          </dt>
          <dd>{hanhChinhVNEntity.name}</dd>
          <dt>
            <span id="slug">
              <Translate contentKey="catinyApp.hanhChinhVN.slug">Slug</Translate>
            </span>
            <UncontrolledTooltip target="slug">
              <Translate contentKey="catinyApp.hanhChinhVN.help.slug" />
            </UncontrolledTooltip>
          </dt>
          <dd>{hanhChinhVNEntity.slug}</dd>
          <dt>
            <span id="type">
              <Translate contentKey="catinyApp.hanhChinhVN.type">Type</Translate>
            </span>
            <UncontrolledTooltip target="type">
              <Translate contentKey="catinyApp.hanhChinhVN.help.type" />
            </UncontrolledTooltip>
          </dt>
          <dd>{hanhChinhVNEntity.type}</dd>
          <dt>
            <span id="nameWithType">
              <Translate contentKey="catinyApp.hanhChinhVN.nameWithType">Name With Type</Translate>
            </span>
            <UncontrolledTooltip target="nameWithType">
              <Translate contentKey="catinyApp.hanhChinhVN.help.nameWithType" />
            </UncontrolledTooltip>
          </dt>
          <dd>{hanhChinhVNEntity.nameWithType}</dd>
          <dt>
            <span id="code">
              <Translate contentKey="catinyApp.hanhChinhVN.code">Code</Translate>
            </span>
            <UncontrolledTooltip target="code">
              <Translate contentKey="catinyApp.hanhChinhVN.help.code" />
            </UncontrolledTooltip>
          </dt>
          <dd>{hanhChinhVNEntity.code}</dd>
          <dt>
            <span id="parentCode">
              <Translate contentKey="catinyApp.hanhChinhVN.parentCode">Parent Code</Translate>
            </span>
            <UncontrolledTooltip target="parentCode">
              <Translate contentKey="catinyApp.hanhChinhVN.help.parentCode" />
            </UncontrolledTooltip>
          </dt>
          <dd>{hanhChinhVNEntity.parentCode}</dd>
          <dt>
            <span id="path">
              <Translate contentKey="catinyApp.hanhChinhVN.path">Path</Translate>
            </span>
            <UncontrolledTooltip target="path">
              <Translate contentKey="catinyApp.hanhChinhVN.help.path" />
            </UncontrolledTooltip>
          </dt>
          <dd>{hanhChinhVNEntity.path}</dd>
          <dt>
            <span id="pathWithType">
              <Translate contentKey="catinyApp.hanhChinhVN.pathWithType">Path With Type</Translate>
            </span>
            <UncontrolledTooltip target="pathWithType">
              <Translate contentKey="catinyApp.hanhChinhVN.help.pathWithType" />
            </UncontrolledTooltip>
          </dt>
          <dd>{hanhChinhVNEntity.pathWithType}</dd>
        </dl>
        <Button tag={Link} to="/hanh-chinh-vn" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/hanh-chinh-vn/${hanhChinhVNEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default HanhChinhVNDetail;
