import React, { useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, UncontrolledTooltip, Row, Col } from 'reactstrap';
import { Translate, byteSize, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { getEntity } from './event.reducer';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const EventDetail = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  useEffect(() => {
    dispatch(getEntity(props.match.params.id));
  }, []);

  const eventEntity = useAppSelector(state => state.event.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="eventDetailsHeading">
          <Translate contentKey="catinyApp.event.detail.title">Event</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{eventEntity.id}</dd>
          <dt>
            <span id="uuid">
              <Translate contentKey="catinyApp.event.uuid">Uuid</Translate>
            </span>
            <UncontrolledTooltip target="uuid">
              <Translate contentKey="catinyApp.event.help.uuid" />
            </UncontrolledTooltip>
          </dt>
          <dd>{eventEntity.uuid}</dd>
          <dt>
            <span id="title">
              <Translate contentKey="catinyApp.event.title">Title</Translate>
            </span>
            <UncontrolledTooltip target="title">
              <Translate contentKey="catinyApp.event.help.title" />
            </UncontrolledTooltip>
          </dt>
          <dd>{eventEntity.title}</dd>
          <dt>
            <span id="avatar">
              <Translate contentKey="catinyApp.event.avatar">Avatar</Translate>
            </span>
            <UncontrolledTooltip target="avatar">
              <Translate contentKey="catinyApp.event.help.avatar" />
            </UncontrolledTooltip>
          </dt>
          <dd>{eventEntity.avatar}</dd>
          <dt>
            <span id="content">
              <Translate contentKey="catinyApp.event.content">Content</Translate>
            </span>
            <UncontrolledTooltip target="content">
              <Translate contentKey="catinyApp.event.help.content" />
            </UncontrolledTooltip>
          </dt>
          <dd>{eventEntity.content}</dd>
          <dt>
            <span id="type">
              <Translate contentKey="catinyApp.event.type">Type</Translate>
            </span>
            <UncontrolledTooltip target="type">
              <Translate contentKey="catinyApp.event.help.type" />
            </UncontrolledTooltip>
          </dt>
          <dd>{eventEntity.type}</dd>
          <dt>
            <span id="description">
              <Translate contentKey="catinyApp.event.description">Description</Translate>
            </span>
            <UncontrolledTooltip target="description">
              <Translate contentKey="catinyApp.event.help.description" />
            </UncontrolledTooltip>
          </dt>
          <dd>{eventEntity.description}</dd>
          <dt>
            <span id="startTime">
              <Translate contentKey="catinyApp.event.startTime">Start Time</Translate>
            </span>
            <UncontrolledTooltip target="startTime">
              <Translate contentKey="catinyApp.event.help.startTime" />
            </UncontrolledTooltip>
          </dt>
          <dd>{eventEntity.startTime ? <TextFormat value={eventEntity.startTime} type="date" format={APP_DATE_FORMAT} /> : null}</dd>
          <dt>
            <span id="endTime">
              <Translate contentKey="catinyApp.event.endTime">End Time</Translate>
            </span>
            <UncontrolledTooltip target="endTime">
              <Translate contentKey="catinyApp.event.help.endTime" />
            </UncontrolledTooltip>
          </dt>
          <dd>{eventEntity.endTime ? <TextFormat value={eventEntity.endTime} type="date" format={APP_DATE_FORMAT} /> : null}</dd>
          <dt>
            <span id="tagLine">
              <Translate contentKey="catinyApp.event.tagLine">Tag Line</Translate>
            </span>
            <UncontrolledTooltip target="tagLine">
              <Translate contentKey="catinyApp.event.help.tagLine" />
            </UncontrolledTooltip>
          </dt>
          <dd>{eventEntity.tagLine}</dd>
          <dt>
            <span id="imageCollection">
              <Translate contentKey="catinyApp.event.imageCollection">Image Collection</Translate>
            </span>
            <UncontrolledTooltip target="imageCollection">
              <Translate contentKey="catinyApp.event.help.imageCollection" />
            </UncontrolledTooltip>
          </dt>
          <dd>{eventEntity.imageCollection}</dd>
          <dt>
            <span id="videoCollection">
              <Translate contentKey="catinyApp.event.videoCollection">Video Collection</Translate>
            </span>
            <UncontrolledTooltip target="videoCollection">
              <Translate contentKey="catinyApp.event.help.videoCollection" />
            </UncontrolledTooltip>
          </dt>
          <dd>{eventEntity.videoCollection}</dd>
          <dt>
            <Translate contentKey="catinyApp.event.baseInfo">Base Info</Translate>
          </dt>
          <dd>{eventEntity.baseInfo ? eventEntity.baseInfo.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/event" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/event/${eventEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default EventDetail;
