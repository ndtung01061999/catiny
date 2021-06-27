import React, { useState, useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, FormText, UncontrolledTooltip } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { getEntity, updateEntity, createEntity, reset } from './message-group.reducer';
import { IMessageGroup } from 'app/shared/model/message-group.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const MessageGroupUpdate = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  const [isNew] = useState(!props.match.params || !props.match.params.id);

  const messageGroupEntity = useAppSelector(state => state.messageGroup.entity);
  const loading = useAppSelector(state => state.messageGroup.loading);
  const updating = useAppSelector(state => state.messageGroup.updating);
  const updateSuccess = useAppSelector(state => state.messageGroup.updateSuccess);

  const handleClose = () => {
    props.history.push('/message-group');
  };

  useEffect(() => {
    if (!isNew) {
      dispatch(getEntity(props.match.params.id));
    }
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  const saveEntity = values => {
    values.createdDate = convertDateTimeToServer(values.createdDate);
    values.modifiedDate = convertDateTimeToServer(values.modifiedDate);

    const entity = {
      ...messageGroupEntity,
      ...values,
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
          createdDate: displayDefaultDateTime(),
          modifiedDate: displayDefaultDateTime(),
        }
      : {
          ...messageGroupEntity,
          createdDate: convertDateTimeFromServer(messageGroupEntity.createdDate),
          modifiedDate: convertDateTimeFromServer(messageGroupEntity.modifiedDate),
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="catinyApp.messageGroup.home.createOrEditLabel" data-cy="MessageGroupCreateUpdateHeading">
            <Translate contentKey="catinyApp.messageGroup.home.createOrEditLabel">Create or edit a MessageGroup</Translate>
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
                  id="message-group-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('catinyApp.messageGroup.uuid')}
                id="message-group-uuid"
                name="uuid"
                data-cy="uuid"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <UncontrolledTooltip target="uuidLabel">
                <Translate contentKey="catinyApp.messageGroup.help.uuid" />
              </UncontrolledTooltip>
              <ValidatedField
                label={translate('catinyApp.messageGroup.userId')}
                id="message-group-userId"
                name="userId"
                data-cy="userId"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                  validate: v => isNumber(v) || translate('entity.validation.number'),
                }}
              />
              <ValidatedField
                label={translate('catinyApp.messageGroup.groupId')}
                id="message-group-groupId"
                name="groupId"
                data-cy="groupId"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('catinyApp.messageGroup.groupName')}
                id="message-group-groupName"
                name="groupName"
                data-cy="groupName"
                type="text"
              />
              <ValidatedField
                label={translate('catinyApp.messageGroup.addBy')}
                id="message-group-addBy"
                name="addBy"
                data-cy="addBy"
                type="text"
              />
              <ValidatedField
                label={translate('catinyApp.messageGroup.lastContent')}
                id="message-group-lastContent"
                name="lastContent"
                data-cy="lastContent"
                type="textarea"
              />
              <ValidatedField
                label={translate('catinyApp.messageGroup.searchField')}
                id="message-group-searchField"
                name="searchField"
                data-cy="searchField"
                type="textarea"
              />
              <UncontrolledTooltip target="searchFieldLabel">
                <Translate contentKey="catinyApp.messageGroup.help.searchField" />
              </UncontrolledTooltip>
              <ValidatedField
                label={translate('catinyApp.messageGroup.role')}
                id="message-group-role"
                name="role"
                data-cy="role"
                type="text"
              />
              <UncontrolledTooltip target="roleLabel">
                <Translate contentKey="catinyApp.messageGroup.help.role" />
              </UncontrolledTooltip>
              <ValidatedField
                label={translate('catinyApp.messageGroup.createdDate')}
                id="message-group-createdDate"
                name="createdDate"
                data-cy="createdDate"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <UncontrolledTooltip target="createdDateLabel">
                <Translate contentKey="catinyApp.messageGroup.help.createdDate" />
              </UncontrolledTooltip>
              <ValidatedField
                label={translate('catinyApp.messageGroup.modifiedDate')}
                id="message-group-modifiedDate"
                name="modifiedDate"
                data-cy="modifiedDate"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <UncontrolledTooltip target="modifiedDateLabel">
                <Translate contentKey="catinyApp.messageGroup.help.modifiedDate" />
              </UncontrolledTooltip>
              <ValidatedField
                label={translate('catinyApp.messageGroup.createdBy')}
                id="message-group-createdBy"
                name="createdBy"
                data-cy="createdBy"
                type="text"
              />
              <UncontrolledTooltip target="createdByLabel">
                <Translate contentKey="catinyApp.messageGroup.help.createdBy" />
              </UncontrolledTooltip>
              <ValidatedField
                label={translate('catinyApp.messageGroup.modifiedBy')}
                id="message-group-modifiedBy"
                name="modifiedBy"
                data-cy="modifiedBy"
                type="text"
              />
              <UncontrolledTooltip target="modifiedByLabel">
                <Translate contentKey="catinyApp.messageGroup.help.modifiedBy" />
              </UncontrolledTooltip>
              <ValidatedField
                label={translate('catinyApp.messageGroup.comment')}
                id="message-group-comment"
                name="comment"
                data-cy="comment"
                type="text"
              />
              <UncontrolledTooltip target="commentLabel">
                <Translate contentKey="catinyApp.messageGroup.help.comment" />
              </UncontrolledTooltip>
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/message-group" replace color="info">
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

export default MessageGroupUpdate;
