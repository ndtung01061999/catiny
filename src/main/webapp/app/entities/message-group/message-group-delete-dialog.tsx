import React, {useEffect} from 'react';
import {connect} from 'react-redux';
import {RouteComponentProps} from 'react-router-dom';
import {Button, Modal, ModalBody, ModalFooter, ModalHeader} from 'reactstrap';
import {Translate} from 'react-jhipster';
import {FontAwesomeIcon} from '@fortawesome/react-fontawesome';

import {IRootState} from 'app/shared/reducers';
import {deleteEntity, getEntity} from './message-group.reducer';

export interface IMessageGroupDeleteDialogProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }>
{
}

export const MessageGroupDeleteDialog = (props: IMessageGroupDeleteDialogProps) =>
{
  useEffect(() =>
  {
    props.getEntity(props.match.params.id);
  }, []);

  const handleClose = () =>
  {
    props.history.push('/message-group');
  };

  useEffect(() =>
  {
    if (props.updateSuccess)
    {
      handleClose();
    }
  }, [props.updateSuccess]);

  const confirmDelete = () => {
    props.deleteEntity(props.messageGroupEntity.id);
  };

  const { messageGroupEntity } = props;
  return (
    <Modal isOpen toggle={handleClose}>
      <ModalHeader toggle={handleClose} data-cy="messageGroupDeleteDialogHeading">
        <Translate contentKey="entity.delete.title">Confirm delete operation</Translate>
      </ModalHeader>
      <ModalBody id="catinyApp.messageGroup.delete.question">
        <Translate contentKey="catinyApp.messageGroup.delete.question" interpolate={{ id: messageGroupEntity.id }}>
          Are you sure you want to delete this MessageGroup?
        </Translate>
      </ModalBody>
      <ModalFooter>
        <Button color="secondary" onClick={handleClose}>
          <FontAwesomeIcon icon="ban" />
          &nbsp;
          <Translate contentKey="entity.action.cancel">Cancel</Translate>
        </Button>
        <Button id="jhi-confirm-delete-messageGroup" data-cy="entityConfirmDeleteButton" color="danger" onClick={confirmDelete}>
          <FontAwesomeIcon icon="trash" />
          &nbsp;
          <Translate contentKey="entity.action.delete">Delete</Translate>
        </Button>
      </ModalFooter>
    </Modal>
  );
};

const mapStateToProps = ({ messageGroup }: IRootState) => ({
  messageGroupEntity: messageGroup.entity,
  updateSuccess: messageGroup.updateSuccess,
});

const mapDispatchToProps = { getEntity, deleteEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(MessageGroupDeleteDialog);
