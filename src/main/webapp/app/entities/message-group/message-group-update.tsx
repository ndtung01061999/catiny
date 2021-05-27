import React, {useEffect, useState} from 'react';
import {connect} from 'react-redux';
import {Link, RouteComponentProps} from 'react-router-dom';
import {Button, Col, Label, Row, UncontrolledTooltip} from 'reactstrap';
import {AvField, AvForm, AvGroup, AvInput} from 'availity-reactstrap-validation';
import {setFileData, Translate, translate} from 'react-jhipster';
import {FontAwesomeIcon} from '@fortawesome/react-fontawesome';
import {IRootState} from 'app/shared/reducers';

import {createEntity, getEntity, reset, setBlob, updateEntity} from './message-group.reducer';
import {convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime} from 'app/shared/util/date-utils';

export interface IMessageGroupUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }>
{
}

export const MessageGroupUpdate = (props: IMessageGroupUpdateProps) =>
{
  const [isNew] = useState(!props.match.params || !props.match.params.id);

  const {messageGroupEntity, loading, updating} = props;

  const {lastContent, searchField} = messageGroupEntity;

  const handleClose = () =>
  {
    props.history.push('/message-group');
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
        ...messageGroupEntity,
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
            <AvForm model={isNew ? {} : messageGroupEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="message-group-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="message-group-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="uuidLabel" for="message-group-uuid">
                  <Translate contentKey="catinyApp.messageGroup.uuid">Uuid</Translate>
                </Label>
                <AvField
                  id="message-group-uuid"
                  data-cy="uuid"
                  type="text"
                  name="uuid"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                  }}
                />
                <UncontrolledTooltip target="uuidLabel">
                  <Translate contentKey="catinyApp.messageGroup.help.uuid" />
                </UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label id="userIdLabel" for="message-group-userId">
                  <Translate contentKey="catinyApp.messageGroup.userId">User Id</Translate>
                </Label>
                <AvField
                  id="message-group-userId"
                  data-cy="userId"
                  type="string"
                  className="form-control"
                  name="userId"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                    number: { value: true, errorMessage: translate('entity.validation.number') },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="groupIdLabel" for="message-group-groupId">
                  <Translate contentKey="catinyApp.messageGroup.groupId">Group Id</Translate>
                </Label>
                <AvField
                  id="message-group-groupId"
                  data-cy="groupId"
                  type="text"
                  name="groupId"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="groupNameLabel" for="message-group-groupName">
                  <Translate contentKey="catinyApp.messageGroup.groupName">Group Name</Translate>
                </Label>
                <AvField id="message-group-groupName" data-cy="groupName" type="text" name="groupName" />
              </AvGroup>
              <AvGroup>
                <Label id="addByLabel" for="message-group-addBy">
                  <Translate contentKey="catinyApp.messageGroup.addBy">Add By</Translate>
                </Label>
                <AvField id="message-group-addBy" data-cy="addBy" type="text" name="addBy" />
              </AvGroup>
              <AvGroup>
                <Label id="lastContentLabel" for="message-group-lastContent">
                  <Translate contentKey="catinyApp.messageGroup.lastContent">Last Content</Translate>
                </Label>
                <AvInput id="message-group-lastContent" data-cy="lastContent" type="textarea" name="lastContent" />
              </AvGroup>
              <AvGroup>
                <Label id="searchFieldLabel" for="message-group-searchField">
                  <Translate contentKey="catinyApp.messageGroup.searchField">Search Field</Translate>
                </Label>
                <AvInput id="message-group-searchField" data-cy="searchField" type="textarea" name="searchField" />
                <UncontrolledTooltip target="searchFieldLabel">
                  <Translate contentKey="catinyApp.messageGroup.help.searchField" />
                </UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label id="roleLabel" for="message-group-role">
                  <Translate contentKey="catinyApp.messageGroup.role">Role</Translate>
                </Label>
                <AvField
                  id="message-group-role"
                  data-cy="role"
                  type="text"
                  name="role"
                  validate={{
                    maxLength: {value: 511, errorMessage: translate('entity.validation.maxlength', {max: 511})},
                  }}
                />
                <UncontrolledTooltip target="roleLabel">
                  <Translate contentKey="catinyApp.messageGroup.help.role" />
                </UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label id="createdDateLabel" for="message-group-createdDate">
                  <Translate contentKey="catinyApp.messageGroup.createdDate">Created Date</Translate>
                </Label>
                <AvInput
                  id="message-group-createdDate"
                  data-cy="createdDate"
                  type="datetime-local"
                  className="form-control"
                  name="createdDate"
                  placeholder={'YYYY-MM-DD HH:mm'}
                  value={isNew ? displayDefaultDateTime() : convertDateTimeFromServer(props.messageGroupEntity.createdDate)}
                />
                <UncontrolledTooltip target="createdDateLabel">
                  <Translate contentKey="catinyApp.messageGroup.help.createdDate" />
                </UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label id="modifiedDateLabel" for="message-group-modifiedDate">
                  <Translate contentKey="catinyApp.messageGroup.modifiedDate">Modified Date</Translate>
                </Label>
                <AvInput
                  id="message-group-modifiedDate"
                  data-cy="modifiedDate"
                  type="datetime-local"
                  className="form-control"
                  name="modifiedDate"
                  placeholder={'YYYY-MM-DD HH:mm'}
                  value={isNew ? displayDefaultDateTime() : convertDateTimeFromServer(props.messageGroupEntity.modifiedDate)}
                />
                <UncontrolledTooltip target="modifiedDateLabel">
                  <Translate contentKey="catinyApp.messageGroup.help.modifiedDate" />
                </UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label id="createdByLabel" for="message-group-createdBy">
                  <Translate contentKey="catinyApp.messageGroup.createdBy">Created By</Translate>
                </Label>
                <AvField id="message-group-createdBy" data-cy="createdBy" type="text" name="createdBy" />
                <UncontrolledTooltip target="createdByLabel">
                  <Translate contentKey="catinyApp.messageGroup.help.createdBy" />
                </UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label id="modifiedByLabel" for="message-group-modifiedBy">
                  <Translate contentKey="catinyApp.messageGroup.modifiedBy">Modified By</Translate>
                </Label>
                <AvField id="message-group-modifiedBy" data-cy="modifiedBy" type="text" name="modifiedBy" />
                <UncontrolledTooltip target="modifiedByLabel">
                  <Translate contentKey="catinyApp.messageGroup.help.modifiedBy" />
                </UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label id="commentLabel" for="message-group-comment">
                  <Translate contentKey="catinyApp.messageGroup.comment">Comment</Translate>
                </Label>
                <AvField
                  id="message-group-comment"
                  data-cy="comment"
                  type="text"
                  name="comment"
                  validate={{
                    maxLength: {value: 511, errorMessage: translate('entity.validation.maxlength', {max: 511})},
                  }}
                />
                <UncontrolledTooltip target="commentLabel">
                  <Translate contentKey="catinyApp.messageGroup.help.comment" />
                </UncontrolledTooltip>
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/message-group" replace color="info">
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
  messageGroupEntity: storeState.messageGroup.entity,
  loading: storeState.messageGroup.loading,
  updating: storeState.messageGroup.updating,
  updateSuccess: storeState.messageGroup.updateSuccess,
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

export default connect(mapStateToProps, mapDispatchToProps)(MessageGroupUpdate);
