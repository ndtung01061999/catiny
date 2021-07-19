import React, {useEffect} from 'react';
import {RouteComponentProps} from 'react-router-dom';
import {Button, Modal, ModalBody, ModalFooter, ModalHeader} from 'reactstrap';
import {Translate} from 'react-jhipster';
import {FontAwesomeIcon} from '@fortawesome/react-fontawesome';

import {useAppDispatch, useAppSelector} from 'app/config/store';
import {deleteEntity, getEntity} from './history-update.reducer';

export const HistoryUpdateDeleteDialog = (props: RouteComponentProps<{ id: string }>) =>
{
  const dispatch = useAppDispatch();

  useEffect(() =>
  {
    dispatch(getEntity(props.match.params.id));
  }, []);

  const historyUpdateEntity = useAppSelector(state => state.historyUpdate.entity);
  const updateSuccess = useAppSelector(state => state.historyUpdate.updateSuccess);

  const handleClose = () =>
  {
    props.history.push('/history-update');
  };

  useEffect(() =>
  {
    if (updateSuccess)
    {
      handleClose();
    }
  }, [updateSuccess]);

  const confirmDelete = () =>
  {
    dispatch(deleteEntity(historyUpdateEntity.id));
  };

  return (
    <Modal isOpen toggle={handleClose}>
      <ModalHeader toggle={handleClose} data-cy='historyUpdateDeleteDialogHeading'>
        <Translate contentKey='entity.delete.title'>Confirm delete operation</Translate>
      </ModalHeader>
      <ModalBody id='catinyApp.historyUpdate.delete.question'>
        <Translate contentKey='catinyApp.historyUpdate.delete.question' interpolate={{id: historyUpdateEntity.id}}>
          Are you sure you want to delete this HistoryUpdate?
        </Translate>
      </ModalBody>
      <ModalFooter>
        <Button color='secondary' onClick={handleClose}>
          <FontAwesomeIcon icon='ban' />
          &nbsp;
          <Translate contentKey='entity.action.cancel'>Cancel</Translate>
        </Button>
        <Button id='jhi-confirm-delete-historyUpdate' data-cy='entityConfirmDeleteButton' color='danger' onClick={confirmDelete}>
          <FontAwesomeIcon icon='trash' />
          &nbsp;
          <Translate contentKey='entity.action.delete'>Delete</Translate>
        </Button>
      </ModalFooter>
    </Modal>
  );
};

export default HistoryUpdateDeleteDialog;
