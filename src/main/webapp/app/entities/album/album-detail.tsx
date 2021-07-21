import React, { useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, UncontrolledTooltip, Row, Col } from 'reactstrap';
import { Translate, byteSize } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { getEntity } from './album.reducer';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const AlbumDetail = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  useEffect(() => {
    dispatch(getEntity(props.match.params.id));
  }, []);

  const albumEntity = useAppSelector(state => state.album.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="albumDetailsHeading">
          <Translate contentKey="catinyApp.album.detail.title">Album</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{albumEntity.id}</dd>
          <dt>
            <span id="uuid">
              <Translate contentKey="catinyApp.album.uuid">Uuid</Translate>
            </span>
            <UncontrolledTooltip target="uuid">
              <Translate contentKey="catinyApp.album.help.uuid" />
            </UncontrolledTooltip>
          </dt>
          <dd>{albumEntity.uuid}</dd>
          <dt>
            <span id="name">
              <Translate contentKey="catinyApp.album.name">Name</Translate>
            </span>
            <UncontrolledTooltip target="name">
              <Translate contentKey="catinyApp.album.help.name" />
            </UncontrolledTooltip>
          </dt>
          <dd>{albumEntity.name}</dd>
          <dt>
            <span id="note">
              <Translate contentKey="catinyApp.album.note">Note</Translate>
            </span>
            <UncontrolledTooltip target="note">
              <Translate contentKey="catinyApp.album.help.note" />
            </UncontrolledTooltip>
          </dt>
          <dd>{albumEntity.note}</dd>
          <dt>
            <span id="avatar">
              <Translate contentKey="catinyApp.album.avatar">Avatar</Translate>
            </span>
            <UncontrolledTooltip target="avatar">
              <Translate contentKey="catinyApp.album.help.avatar" />
            </UncontrolledTooltip>
          </dt>
          <dd>{albumEntity.avatar}</dd>
          <dt>
            <Translate contentKey="catinyApp.album.baseInfo">Base Info</Translate>
          </dt>
          <dd>{albumEntity.baseInfo ? albumEntity.baseInfo.id : ''}</dd>
          <dt>
            <Translate contentKey="catinyApp.album.image">Image</Translate>
          </dt>
          <dd>
            {albumEntity.images
              ? albumEntity.images.map((val, i) => (
                  <span key={val.id}>
                    <a>{val.id}</a>
                    {albumEntity.images && i === albumEntity.images.length - 1 ? '' : ', '}
                  </span>
                ))
              : null}
          </dd>
        </dl>
        <Button tag={Link} to="/album" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/album/${albumEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default AlbumDetail;
