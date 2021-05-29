import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label, UncontrolledTooltip } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { setFileData, byteSize, Translate, translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, setBlob, reset } from './message-content.reducer';
import { IMessageContent } from 'app/shared/model/message-content.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IMessageContentUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const MessageContentUpdate = (props: IMessageContentUpdateProps) => {
  const [isNew] = useState(!props.match.params || !props.match.params.id);

  const { messageContentEntity, loading, updating } = props;

  const { content, searchField } = messageContentEntity;

  const handleClose = () => {
    props.history.push('/message-content');
  };

  useEffect(() => {
    if (!isNew) {
      props.getEntity(props.match.params.id);
    }
  }, []);

  const onBlobChange = (isAnImage, name) => event => {
    setFileData(event, (contentType, data) => props.setBlob(name, data, contentType), isAnImage);
  };

  const clearBlob = name => () => {
    props.setBlob(name, undefined, undefined);
  };

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    values.createdDate = convertDateTimeToServer(values.createdDate);
    values.modifiedDate = convertDateTimeToServer(values.modifiedDate);

    if (errors.length === 0) {
      const entity = {
        ...messageContentEntity,
        ...values,
      };

      if (isNew) {
        props.createEntity(entity);
      } else {
        props.updateEntity(entity);
      }
    }
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
            <AvForm model={isNew ? {} : messageContentEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="message-content-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="message-content-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="uuidLabel" for="message-content-uuid">
                  <Translate contentKey="catinyApp.messageContent.uuid">Uuid</Translate>
                </Label>
                <AvField
                  id="message-content-uuid"
                  data-cy="uuid"
                  type="text"
                  name="uuid"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                  }}
                />
                <UncontrolledTooltip target="uuidLabel">
                  <Translate contentKey="catinyApp.messageContent.help.uuid" />
                </UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label id="groupIdLabel" for="message-content-groupId">
                  <Translate contentKey="catinyApp.messageContent.groupId">Group Id</Translate>
                </Label>
                <AvField
                  id="message-content-groupId"
                  data-cy="groupId"
                  type="text"
                  name="groupId"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="contentLabel" for="message-content-content">
                  <Translate contentKey="catinyApp.messageContent.content">Content</Translate>
                </Label>
                <AvInput id="message-content-content" data-cy="content" type="textarea" name="content" />
              </AvGroup>
              <AvGroup>
                <Label id="senderLabel" for="message-content-sender">
                  <Translate contentKey="catinyApp.messageContent.sender">Sender</Translate>
                </Label>
                <AvField id="message-content-sender" data-cy="sender" type="text" name="sender" />
              </AvGroup>
              <AvGroup>
                <Label id="statusLabel" for="message-content-status">
                  <Translate contentKey="catinyApp.messageContent.status">Status</Translate>
                </Label>
                <AvField id="message-content-status" data-cy="status" type="text" name="status" />
              </AvGroup>
              <AvGroup>
                <Label id="searchFieldLabel" for="message-content-searchField">
                  <Translate contentKey="catinyApp.messageContent.searchField">Search Field</Translate>
                </Label>
                <AvInput id="message-content-searchField" data-cy="searchField" type="textarea" name="searchField" />
                <UncontrolledTooltip target="searchFieldLabel">
                  <Translate contentKey="catinyApp.messageContent.help.searchField" />
                </UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label id="roleLabel" for="message-content-role">
                  <Translate contentKey="catinyApp.messageContent.role">Role</Translate>
                </Label>
                <AvField
                  id="message-content-role"
                  data-cy="role"
                  type="text"
                  name="role"
                  validate={{
                    maxLength: { value: 511, errorMessage: translate('entity.validation.maxlength', { max: 511 }) },
                  }}
                />
                <UncontrolledTooltip target="roleLabel">
                  <Translate contentKey="catinyApp.messageContent.help.role" />
                </UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label id="createdDateLabel" for="message-content-createdDate">
                  <Translate contentKey="catinyApp.messageContent.createdDate">Created Date</Translate>
                </Label>
                <AvInput
                  id="message-content-createdDate"
                  data-cy="createdDate"
                  type="datetime-local"
                  className="form-control"
                  name="createdDate"
                  placeholder={'YYYY-MM-DD HH:mm'}
                  value={isNew ? displayDefaultDateTime() : convertDateTimeFromServer(props.messageContentEntity.createdDate)}
                />
                <UncontrolledTooltip target="createdDateLabel">
                  <Translate contentKey="catinyApp.messageContent.help.createdDate" />
                </UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label id="modifiedDateLabel" for="message-content-modifiedDate">
                  <Translate contentKey="catinyApp.messageContent.modifiedDate">Modified Date</Translate>
                </Label>
                <AvInput
                  id="message-content-modifiedDate"
                  data-cy="modifiedDate"
                  type="datetime-local"
                  className="form-control"
                  name="modifiedDate"
                  placeholder={'YYYY-MM-DD HH:mm'}
                  value={isNew ? displayDefaultDateTime() : convertDateTimeFromServer(props.messageContentEntity.modifiedDate)}
                />
                <UncontrolledTooltip target="modifiedDateLabel">
                  <Translate contentKey="catinyApp.messageContent.help.modifiedDate" />
                </UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label id="createdByLabel" for="message-content-createdBy">
                  <Translate contentKey="catinyApp.messageContent.createdBy">Created By</Translate>
                </Label>
                <AvField id="message-content-createdBy" data-cy="createdBy" type="text" name="createdBy" />
                <UncontrolledTooltip target="createdByLabel">
                  <Translate contentKey="catinyApp.messageContent.help.createdBy" />
                </UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label id="modifiedByLabel" for="message-content-modifiedBy">
                  <Translate contentKey="catinyApp.messageContent.modifiedBy">Modified By</Translate>
                </Label>
                <AvField id="message-content-modifiedBy" data-cy="modifiedBy" type="text" name="modifiedBy" />
                <UncontrolledTooltip target="modifiedByLabel">
                  <Translate contentKey="catinyApp.messageContent.help.modifiedBy" />
                </UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label id="commentLabel" for="message-content-comment">
                  <Translate contentKey="catinyApp.messageContent.comment">Comment</Translate>
                </Label>
                <AvField
                  id="message-content-comment"
                  data-cy="comment"
                  type="text"
                  name="comment"
                  validate={{
                    maxLength: { value: 511, errorMessage: translate('entity.validation.maxlength', { max: 511 }) },
                  }}
                />
                <UncontrolledTooltip target="commentLabel">
                  <Translate contentKey="catinyApp.messageContent.help.comment" />
                </UncontrolledTooltip>
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/message-content" replace color="info">
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
            </AvForm>
          )}
        </Col>
      </Row>
    </div>
  );
};

const mapStateToProps = (storeState: IRootState) => ({
  messageContentEntity: storeState.messageContent.entity,
  loading: storeState.messageContent.loading,
  updating: storeState.messageContent.updating,
  updateSuccess: storeState.messageContent.updateSuccess,
});

const mapDispatchToProps = {
  getEntity,
  updateEntity,
  setBlob,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(MessageContentUpdate);
