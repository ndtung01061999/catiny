import React, { useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, UncontrolledTooltip, Row, Col } from 'reactstrap';
import { Translate, byteSize } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { getEntity } from './page-post.reducer';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const PagePostDetail = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  useEffect(() => {
    dispatch(getEntity(props.match.params.id));
  }, []);

  const pagePostEntity = useAppSelector(state => state.pagePost.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="pagePostDetailsHeading">
          <Translate contentKey="catinyApp.pagePost.detail.title">PagePost</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{pagePostEntity.id}</dd>
          <dt>
            <span id="uuid">
              <Translate contentKey="catinyApp.pagePost.uuid">Uuid</Translate>
            </span>
            <UncontrolledTooltip target="uuid">
              <Translate contentKey="catinyApp.pagePost.help.uuid" />
            </UncontrolledTooltip>
          </dt>
          <dd>{pagePostEntity.uuid}</dd>
          <dt>
            <span id="name">
              <Translate contentKey="catinyApp.pagePost.name">Name</Translate>
            </span>
            <UncontrolledTooltip target="name">
              <Translate contentKey="catinyApp.pagePost.help.name" />
            </UncontrolledTooltip>
          </dt>
          <dd>{pagePostEntity.name}</dd>
          <dt>
            <span id="avatar">
              <Translate contentKey="catinyApp.pagePost.avatar">Avatar</Translate>
            </span>
            <UncontrolledTooltip target="avatar">
              <Translate contentKey="catinyApp.pagePost.help.avatar" />
            </UncontrolledTooltip>
          </dt>
          <dd>{pagePostEntity.avatar}</dd>
          <dt>
            <span id="quickInfo">
              <Translate contentKey="catinyApp.pagePost.quickInfo">Quick Info</Translate>
            </span>
            <UncontrolledTooltip target="quickInfo">
              <Translate contentKey="catinyApp.pagePost.help.quickInfo" />
            </UncontrolledTooltip>
          </dt>
          <dd>{pagePostEntity.quickInfo}</dd>
          <dt>
            <Translate contentKey="catinyApp.pagePost.profile">Profile</Translate>
          </dt>
          <dd>{pagePostEntity.profile ? pagePostEntity.profile.id : ''}</dd>
          <dt>
            <Translate contentKey="catinyApp.pagePost.baseInfo">Base Info</Translate>
          </dt>
          <dd>{pagePostEntity.baseInfo ? pagePostEntity.baseInfo.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/page-post" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/page-post/${pagePostEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default PagePostDetail;
