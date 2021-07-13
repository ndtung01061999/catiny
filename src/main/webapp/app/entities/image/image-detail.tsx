import React, { useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, UncontrolledTooltip, Row, Col } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { getEntity } from './image.reducer';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const ImageDetail = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  useEffect(() => {
    dispatch(getEntity(props.match.params.id));
  }, []);

  const imageEntity = useAppSelector(state => state.image.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="imageDetailsHeading">
          <Translate contentKey="catinyApp.image.detail.title">Image</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{imageEntity.id}</dd>
          <dt>
            <span id="uuid">
              <Translate contentKey="catinyApp.image.uuid">Uuid</Translate>
            </span>
            <UncontrolledTooltip target="uuid">
              <Translate contentKey="catinyApp.image.help.uuid" />
            </UncontrolledTooltip>
          </dt>
          <dd>{imageEntity.uuid}</dd>
          <dt>
            <span id="name">
              <Translate contentKey="catinyApp.image.name">Name</Translate>
            </span>
          </dt>
          <dd>{imageEntity.name}</dd>
          <dt>
            <Translate contentKey="catinyApp.image.fileInfo">File Info</Translate>
          </dt>
          <dd>{imageEntity.fileInfo ? imageEntity.fileInfo.id : ''}</dd>
          <dt>
            <Translate contentKey="catinyApp.image.baseInfo">Base Info</Translate>
          </dt>
          <dd>{imageEntity.baseInfo ? imageEntity.baseInfo.id : ''}</dd>
          <dt>
            <Translate contentKey="catinyApp.image.imageOriginal">Image Original</Translate>
          </dt>
          <dd>{imageEntity.imageOriginal ? imageEntity.imageOriginal.id : ''}</dd>
          <dt>
            <Translate contentKey="catinyApp.image.event">Event</Translate>
          </dt>
          <dd>{imageEntity.event ? imageEntity.event.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/image" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/image/${imageEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default ImageDetail;
