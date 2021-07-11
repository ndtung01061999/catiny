import React, { useState, useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, FormText, UncontrolledTooltip } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IBaseInfo } from 'app/shared/model/base-info.model';
import { getEntities as getBaseInfos } from 'app/entities/base-info/base-info.reducer';
import { IMasterUser } from 'app/shared/model/master-user.model';
import { getEntities as getMasterUsers } from 'app/entities/master-user/master-user.reducer';
import { IMessageGroup } from 'app/shared/model/message-group.model';
import { getEntities as getMessageGroups } from 'app/entities/message-group/message-group.reducer';
import { getEntity, updateEntity, createEntity, reset } from './message-content.reducer';
import { IMessageContent } from 'app/shared/model/message-content.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const MessageContentUpdate = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  const [isNew] = useState(!props.match.params || !props.match.params.id);

  const baseInfos = useAppSelector(state => state.baseInfo.entities);
  const masterUsers = useAppSelector(state => state.masterUser.entities);
  const messageGroups = useAppSelector(state => state.messageGroup.entities);
  const messageContentEntity = useAppSelector(state => state.messageContent.entity);
  const loading = useAppSelector(state => state.messageContent.loading);
  const updating = useAppSelector(state => state.messageContent.updating);
  const updateSuccess = useAppSelector(state => state.messageContent.updateSuccess);

  const handleClose = () => {
    props.history.push('/message-content');
  };

  useEffect(() => {
    if (!isNew) {
      dispatch(getEntity(props.match.params.id));
    }

    dispatch(getBaseInfos({}));
    dispatch(getMasterUsers({}));
    dispatch(getMessageGroups({}));
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  const saveEntity = values => {
    const entity = {
      ...messageContentEntity,
      ...values,
      baseInfo: baseInfos.find(it => it.id.toString() === values.baseInfoId.toString()),
      messageSender: masterUsers.find(it => it.id.toString() === values.messageSenderId.toString()),
      messageGroup: messageGroups.find(it => it.id.toString() === values.messageGroupId.toString()),
    };

    if (isNew) {
      dispatch(createEntity(entity));
    } else {
      dispatch(updateEntity(entity));
    }
  };

  const defaultValues = () =>
    isNew
      ? {}
      : {
          ...messageContentEntity,
          baseInfoId: messageContentEntity?.baseInfo?.id,
          messageSenderId: messageContentEntity?.messageSender?.id,
          messageGroupId: messageContentEntity?.messageGroup?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="catinyApp.messageContent.home.createOrEditLabel" data-cy="MessageContentCreateUpdateHeading">
            <Translate contentKey="catinyApp.messageContent.home.createOrEditLabel">Create or edit a MessageContent</Translate>
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
                  id="message-content-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('catinyApp.messageContent.uuid')}
                id="message-content-uuid"
                name="uuid"
                data-cy="uuid"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <UncontrolledTooltip target="uuidLabel">
                <Translate contentKey="catinyApp.messageContent.help.uuid" />
              </UncontrolledTooltip>
              <ValidatedField
                label={translate('catinyApp.messageContent.content')}
                id="message-content-content"
                name="content"
                data-cy="content"
                type="textarea"
              />
              <ValidatedField
                label={translate('catinyApp.messageContent.status')}
                id="message-content-status"
                name="status"
                data-cy="status"
                type="textarea"
              />
              <UncontrolledTooltip target="statusLabel">
                <Translate contentKey="catinyApp.messageContent.help.status" />
              </UncontrolledTooltip>
              <ValidatedField
                label={translate('catinyApp.messageContent.searchField')}
                id="message-content-searchField"
                name="searchField"
                data-cy="searchField"
                type="textarea"
              />
              <UncontrolledTooltip target="searchFieldLabel">
                <Translate contentKey="catinyApp.messageContent.help.searchField" />
              </UncontrolledTooltip>
              <ValidatedField
                id="message-content-baseInfo"
                name="baseInfoId"
                data-cy="baseInfo"
                label={translate('catinyApp.messageContent.baseInfo')}
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
              <ValidatedField
                id="message-content-messageSender"
                name="messageSenderId"
                data-cy="messageSender"
                label={translate('catinyApp.messageContent.messageSender')}
                type="select"
              >
                <option value="" key="0" />
                {masterUsers
                  ? masterUsers.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField
                id="message-content-messageGroup"
                name="messageGroupId"
                data-cy="messageGroup"
                label={translate('catinyApp.messageContent.messageGroup')}
                type="select"
              >
                <option value="" key="0" />
                {messageGroups
                  ? messageGroups.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/message-content" replace color="info">
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

export default MessageContentUpdate;
