import React, { useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, UncontrolledTooltip, Row, Col } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { getEntity } from './file-info.reducer';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const FileInfoDetail = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  useEffect(() => {
    dispatch(getEntity(props.match.params.id));
  }, []);

  const fileInfoEntity = useAppSelector(state => state.fileInfo.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="fileInfoDetailsHeading">
          <Translate contentKey="catinyApp.fileInfo.detail.title">FileInfo</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{fileInfoEntity.id}</dd>
          <dt>
            <span id="uuid">
              <Translate contentKey="catinyApp.fileInfo.uuid">Uuid</Translate>
            </span>
            <UncontrolledTooltip target="uuid">
              <Translate contentKey="catinyApp.fileInfo.help.uuid" />
            </UncontrolledTooltip>
          </dt>
          <dd>{fileInfoEntity.uuid}</dd>
          <dt>
            <span id="nameFile">
              <Translate contentKey="catinyApp.fileInfo.nameFile">Name File</Translate>
            </span>
            <UncontrolledTooltip target="nameFile">
              <Translate contentKey="catinyApp.fileInfo.help.nameFile" />
            </UncontrolledTooltip>
          </dt>
          <dd>{fileInfoEntity.nameFile}</dd>
          <dt>
            <span id="typeFile">
              <Translate contentKey="catinyApp.fileInfo.typeFile">Type File</Translate>
            </span>
            <UncontrolledTooltip target="typeFile">
              <Translate contentKey="catinyApp.fileInfo.help.typeFile" />
            </UncontrolledTooltip>
          </dt>
          <dd>{fileInfoEntity.typeFile}</dd>
          <dt>
            <span id="path">
              <Translate contentKey="catinyApp.fileInfo.path">Path</Translate>
            </span>
            <UncontrolledTooltip target="path">
              <Translate contentKey="catinyApp.fileInfo.help.path" />
            </UncontrolledTooltip>
          </dt>
          <dd>{fileInfoEntity.path}</dd>
          <dt>
            <span id="dataSize">
              <Translate contentKey="catinyApp.fileInfo.dataSize">Data Size</Translate>
            </span>
            <UncontrolledTooltip target="dataSize">
              <Translate contentKey="catinyApp.fileInfo.help.dataSize" />
            </UncontrolledTooltip>
          </dt>
          <dd>{fileInfoEntity.dataSize}</dd>
          <dt>
            <Translate contentKey="catinyApp.fileInfo.baseInfo">Base Info</Translate>
          </dt>
          <dd>{fileInfoEntity.baseInfo ? fileInfoEntity.baseInfo.id : ''}</dd>
          <dt>
            <Translate contentKey="catinyApp.fileInfo.masterUser">Master User</Translate>
          </dt>
          <dd>{fileInfoEntity.masterUser ? fileInfoEntity.masterUser.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/file-info" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/file-info/${fileInfoEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default FileInfoDetail;
