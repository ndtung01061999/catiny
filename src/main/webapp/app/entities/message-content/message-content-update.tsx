import React, {useEffect, useState} from 'react';
import {Link, RouteComponentProps} from 'react-router-dom';
import {Button, Col, Row, UncontrolledTooltip} from 'reactstrap';
import {Translate, translate, ValidatedField, ValidatedForm} from 'react-jhipster';
import {FontAwesomeIcon} from '@fortawesome/react-fontawesome';

import {createEntity, getEntity, updateEntity} from './message-content.reducer';
import {convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime} from 'app/shared/util/date-utils';
import {useAppDispatch, useAppSelector} from 'app/config/store';

export const MessageContentUpdate = (props: RouteComponentProps<{ id: string }>) =>
{
  const dispatch = useAppDispatch();

  const [isNew] = useState(!props.match.params || !props.match.params.id);

  const messageContentEntity = useAppSelector(state => state.messageContent.entity);
  const loading = useAppSelector(state => state.messageContent.loading);
  const updating = useAppSelector(state => state.messageContent.updating);
  const updateSuccess = useAppSelector(state => state.messageContent.updateSuccess);

  const handleClose = () =>
  {
    props.history.push('/message-content');
  };

  useEffect(() =>
  {
    if (!isNew)
    {
      dispatch(getEntity(props.match.params.id));
    }
  }, []);

  useEffect(() =>
  {
    if (updateSuccess)
    {
      handleClose();
    }
  }, [updateSuccess]);

  const saveEntity = values =>
  {
    values.createdDate = convertDateTimeToServer(values.createdDate);
    values.modifiedDate = convertDateTimeToServer(values.modifiedDate);

    const entity = {
      ...messageContentEntity,
      ...values,
    };

    if (isNew)
    {
      dispatch(createEntity(entity));
    }
    else
    {
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
        ...messageContentEntity,
        createdDate: convertDateTimeFromServer(messageContentEntity.createdDate),
        modifiedDate: convertDateTimeFromServer(messageContentEntity.modifiedDate),
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
                  validate={{required: true}}
                />
              ) : null}
              <ValidatedField
                label={translate('catinyApp.messageContent.uuid')}
                id="message-content-uuid"
                name="uuid"
                data-cy="uuid"
                type="text"
                validate={{
                  required: {value: true, message: translate('entity.validation.required')},
                }}
              />
              <UncontrolledTooltip target="uuidLabel">
                <Translate contentKey="catinyApp.messageContent.help.uuid"/>
              </UncontrolledTooltip>
              <ValidatedField
                label={translate('catinyApp.messageContent.groupId')}
                id="message-content-groupId"
                name="groupId"
                data-cy="groupId"
                type="text"
                validate={{
                  required: {value: true, message: translate('entity.validation.required')},
                }}
              />
              <ValidatedField
                label={translate('catinyApp.messageContent.content')}
                id="message-content-content"
                name="content"
                data-cy="content"
                type="textarea"
              />
              <ValidatedField
                label={translate('catinyApp.messageContent.sender')}
                id="message-content-sender"
                name="sender"
                data-cy="sender"
                type="text"
              />
              <ValidatedField
                label={translate('catinyApp.messageContent.status')}
                id="message-content-status"
                name="status"
                data-cy="status"
                type="text"
              />
              <ValidatedField
                label={translate('catinyApp.messageContent.searchField')}
                id="message-content-searchField"
                name="searchField"
                data-cy="searchField"
                type="textarea"
              />
              <UncontrolledTooltip target="searchFieldLabel">
                <Translate contentKey="catinyApp.messageContent.help.searchField"/>
              </UncontrolledTooltip>
              <ValidatedField
                label={translate('catinyApp.messageContent.role')}
                id="message-content-role"
                name="role"
                data-cy="role"
                type="text"
              />
              <UncontrolledTooltip target="roleLabel">
                <Translate contentKey="catinyApp.messageContent.help.role"/>
              </UncontrolledTooltip>
              <ValidatedField
                label={translate('catinyApp.messageContent.createdDate')}
                id="message-content-createdDate"
                name="createdDate"
                data-cy="createdDate"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <UncontrolledTooltip target="createdDateLabel">
                <Translate contentKey="catinyApp.messageContent.help.createdDate"/>
              </UncontrolledTooltip>
              <ValidatedField
                label={translate('catinyApp.messageContent.modifiedDate')}
                id="message-content-modifiedDate"
                name="modifiedDate"
                data-cy="modifiedDate"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <UncontrolledTooltip target="modifiedDateLabel">
                <Translate contentKey="catinyApp.messageContent.help.modifiedDate"/>
              </UncontrolledTooltip>
              <ValidatedField
                label={translate('catinyApp.messageContent.createdBy')}
                id="message-content-createdBy"
                name="createdBy"
                data-cy="createdBy"
                type="text"
              />
              <UncontrolledTooltip target="createdByLabel">
                <Translate contentKey="catinyApp.messageContent.help.createdBy"/>
              </UncontrolledTooltip>
              <ValidatedField
                label={translate('catinyApp.messageContent.modifiedBy')}
                id="message-content-modifiedBy"
                name="modifiedBy"
                data-cy="modifiedBy"
                type="text"
              />
              <UncontrolledTooltip target="modifiedByLabel">
                <Translate contentKey="catinyApp.messageContent.help.modifiedBy"/>
              </UncontrolledTooltip>
              <ValidatedField
                label={translate('catinyApp.messageContent.comment')}
                id="message-content-comment"
                name="comment"
                data-cy="comment"
                type="text"
              />
              <UncontrolledTooltip target="commentLabel">
                <Translate contentKey="catinyApp.messageContent.help.comment"/>
              </UncontrolledTooltip>
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/message-content" replace color="info">
                <FontAwesomeIcon icon="arrow-left"/>
                &nbsp;
                <span className="d-none d-md-inline">
                  <Translate contentKey="entity.action.back">Back</Translate>
                </span>
              </Button>
              &nbsp;
              <Button color="primary" id="save-entity" data-cy="entityCreateSaveButton" type="submit" disabled={updating}>
                <FontAwesomeIcon icon="save"/>
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
