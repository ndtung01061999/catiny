import React, { useState, useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, FormText, UncontrolledTooltip } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IBaseInfo } from 'app/shared/model/base-info.model';
import { getEntities as getBaseInfos } from 'app/entities/base-info/base-info.reducer';
import { getEntity, updateEntity, createEntity, reset } from './event.reducer';
import { IEvent } from 'app/shared/model/event.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const EventUpdate = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  const [isNew] = useState(!props.match.params || !props.match.params.id);

  const baseInfos = useAppSelector(state => state.baseInfo.entities);
  const eventEntity = useAppSelector(state => state.event.entity);
  const loading = useAppSelector(state => state.event.loading);
  const updating = useAppSelector(state => state.event.updating);
  const updateSuccess = useAppSelector(state => state.event.updateSuccess);

  const handleClose = () => {
    props.history.push('/event');
  };

  useEffect(() => {
    if (!isNew) {
      dispatch(getEntity(props.match.params.id));
    }

    dispatch(getBaseInfos({}));
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  const saveEntity = values => {
    values.startTime = convertDateTimeToServer(values.startTime);
    values.endTime = convertDateTimeToServer(values.endTime);

    const entity = {
      ...eventEntity,
      ...values,
      baseInfo: baseInfos.find(it => it.id.toString() === values.baseInfoId.toString()),
    };

    if (isNew) {
      dispatch(createEntity(entity));
    } else {
      dispatch(updateEntity(entity));
    }
  };

  const defaultValues = () =>
    isNew
      ? {
          startTime: displayDefaultDateTime(),
          endTime: displayDefaultDateTime(),
        }
      : {
          ...eventEntity,
          type: 'DAY',
          startTime: convertDateTimeFromServer(eventEntity.startTime),
          endTime: convertDateTimeFromServer(eventEntity.endTime),
          baseInfoId: eventEntity?.baseInfo?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="catinyApp.event.home.createOrEditLabel" data-cy="EventCreateUpdateHeading">
            <Translate contentKey="catinyApp.event.home.createOrEditLabel">Create or edit a Event</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <ValidatedForm defaultValues={defaultValues()} onSubmit={saveEntity}>
              {!isNew ? (
                <ValidatedField
                  name="id"
                  required
                  readOnly
                  id="event-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('catinyApp.event.uuid')}
                id="event-uuid"
                name="uuid"
                data-cy="uuid"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <UncontrolledTooltip target="uuidLabel">
                <Translate contentKey="catinyApp.event.help.uuid" />
              </UncontrolledTooltip>
              <ValidatedField label={translate('catinyApp.event.title')} id="event-title" name="title" data-cy="title" type="text" />
              <UncontrolledTooltip target="titleLabel">
                <Translate contentKey="catinyApp.event.help.title" />
              </UncontrolledTooltip>
              <ValidatedField
                label={translate('catinyApp.event.avatar')}
                id="event-avatar"
                name="avatar"
                data-cy="avatar"
                type="textarea"
              />
              <UncontrolledTooltip target="avatarLabel">
                <Translate contentKey="catinyApp.event.help.avatar" />
              </UncontrolledTooltip>
              <ValidatedField
                label={translate('catinyApp.event.content')}
                id="event-content"
                name="content"
                data-cy="content"
                type="textarea"
              />
              <UncontrolledTooltip target="contentLabel">
                <Translate contentKey="catinyApp.event.help.content" />
              </UncontrolledTooltip>
              <ValidatedField label={translate('catinyApp.event.type')} id="event-type" name="type" data-cy="type" type="select">
                <option value="DAY">{translate('catinyApp.EventType.DAY')}</option>
                <option value="MONTH">{translate('catinyApp.EventType.MONTH')}</option>
                <option value="YEAR">{translate('catinyApp.EventType.YEAR')}</option>
                <option value="ONLY_ONE">{translate('catinyApp.EventType.ONLY_ONE')}</option>
              </ValidatedField>
              <UncontrolledTooltip target="typeLabel">
                <Translate contentKey="catinyApp.event.help.type" />
              </UncontrolledTooltip>
              <ValidatedField
                label={translate('catinyApp.event.description')}
                id="event-description"
                name="description"
                data-cy="description"
                type="textarea"
              />
              <UncontrolledTooltip target="descriptionLabel">
                <Translate contentKey="catinyApp.event.help.description" />
              </UncontrolledTooltip>
              <ValidatedField
                label={translate('catinyApp.event.startTime')}
                id="event-startTime"
                name="startTime"
                data-cy="startTime"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <UncontrolledTooltip target="startTimeLabel">
                <Translate contentKey="catinyApp.event.help.startTime" />
              </UncontrolledTooltip>
              <ValidatedField
                label={translate('catinyApp.event.endTime')}
                id="event-endTime"
                name="endTime"
                data-cy="endTime"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <UncontrolledTooltip target="endTimeLabel">
                <Translate contentKey="catinyApp.event.help.endTime" />
              </UncontrolledTooltip>
              <ValidatedField
                label={translate('catinyApp.event.tagLine')}
                id="event-tagLine"
                name="tagLine"
                data-cy="tagLine"
                type="text"
              />
              <UncontrolledTooltip target="tagLineLabel">
                <Translate contentKey="catinyApp.event.help.tagLine" />
              </UncontrolledTooltip>
              <ValidatedField
                label={translate('catinyApp.event.imageCollection')}
                id="event-imageCollection"
                name="imageCollection"
                data-cy="imageCollection"
                type="textarea"
              />
              <UncontrolledTooltip target="imageCollectionLabel">
                <Translate contentKey="catinyApp.event.help.imageCollection" />
              </UncontrolledTooltip>
              <ValidatedField
                label={translate('catinyApp.event.videoCollection')}
                id="event-videoCollection"
                name="videoCollection"
                data-cy="videoCollection"
                type="textarea"
              />
              <UncontrolledTooltip target="videoCollectionLabel">
                <Translate contentKey="catinyApp.event.help.videoCollection" />
              </UncontrolledTooltip>
              <ValidatedField
                id="event-baseInfo"
                name="baseInfoId"
                data-cy="baseInfo"
                label={translate('catinyApp.event.baseInfo')}
                type="select"
              >
                <option value="" key="0" />
                {baseInfos
                  ? baseInfos.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/event" replace color="info">
                <FontAwesomeIcon icon="arrow-left" />
                &nbsp;
                <span className="d-none d-md-inline">
                  <Translate contentKey="entity.action.back">Back</Translate>
                </span>
              </Button>
              &nbsp;
              <Button color="primary" id="save-entity" data-cy="entityCreateSaveButton" type="submit" disabled={updating}>
                <FontAwesomeIcon icon="save" />
                &nbsp;
                <Translate contentKey="entity.action.save">Save</Translate>
              </Button>
            </ValidatedForm>
          )}
        </Col>
      </Row>
    </div>
  );
};

export default EventUpdate;
