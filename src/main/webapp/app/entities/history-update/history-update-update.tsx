import React, {useEffect, useState} from 'react';
import {Link, RouteComponentProps} from 'react-router-dom';
import {Button, Col, Row, UncontrolledTooltip} from 'reactstrap';
import {Translate, translate, ValidatedField, ValidatedForm} from 'react-jhipster';
import {FontAwesomeIcon} from '@fortawesome/react-fontawesome';
import {getEntities as getBaseInfos} from 'app/entities/base-info/base-info.reducer';
import {createEntity, getEntity, updateEntity} from './history-update.reducer';
import {useAppDispatch, useAppSelector} from 'app/config/store';

export const HistoryUpdateUpdate = (props: RouteComponentProps<{ id: string }>) =>
{
  const dispatch = useAppDispatch();

  const [isNew] = useState(!props.match.params || !props.match.params.id);

  const baseInfos = useAppSelector(state => state.baseInfo.entities);
  const historyUpdateEntity = useAppSelector(state => state.historyUpdate.entity);
  const loading = useAppSelector(state => state.historyUpdate.loading);
  const updating = useAppSelector(state => state.historyUpdate.updating);
  const updateSuccess = useAppSelector(state => state.historyUpdate.updateSuccess);

  const handleClose = () =>
  {
    props.history.push('/history-update');
  };

  useEffect(() =>
  {
    if (!isNew)
    {
      dispatch(getEntity(props.match.params.id));
    }

    dispatch(getBaseInfos({}));
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
    const entity = {
      ...historyUpdateEntity,
      ...values,
      baseInfo: baseInfos.find(it => it.id.toString() === values.baseInfoId.toString()),
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
      ? {}
      : {
        ...historyUpdateEntity,
        baseInfoId: historyUpdateEntity?.baseInfo?.id,
      };

  return (
    <div>
      <Row className='justify-content-center'>
        <Col md='8'>
          <h2 id='catinyApp.historyUpdate.home.createOrEditLabel' data-cy='HistoryUpdateCreateUpdateHeading'>
            <Translate contentKey='catinyApp.historyUpdate.home.createOrEditLabel'>Create or edit a HistoryUpdate</Translate>
          </h2>
        </Col>
      </Row>
      <Row className='justify-content-center'>
        <Col md='8'>
          {loading ? (
            <p>Loading...</p>
          ) : (
            <ValidatedForm defaultValues={defaultValues()} onSubmit={saveEntity}>
              {!isNew ? (
                <ValidatedField
                  name='id'
                  required
                  readOnly
                  id='history-update-id'
                  label={translate('global.field.id')}
                  validate={{required: true}}
                />
              ) : null}
              <ValidatedField
                label={translate('catinyApp.historyUpdate.uuid')}
                id='history-update-uuid'
                name='uuid'
                data-cy='uuid'
                type='text'
                validate={{
                  required: {value: true, message: translate('entity.validation.required')},
                }}
              />
              <UncontrolledTooltip target='uuidLabel'>
                <Translate contentKey='catinyApp.historyUpdate.help.uuid' />
              </UncontrolledTooltip>
              <ValidatedField
                label={translate('catinyApp.historyUpdate.version')}
                id='history-update-version'
                name='version'
                data-cy='version'
                type='text'
              />
              <UncontrolledTooltip target='versionLabel'>
                <Translate contentKey='catinyApp.historyUpdate.help.version' />
              </UncontrolledTooltip>
              <ValidatedField
                label={translate('catinyApp.historyUpdate.content')}
                id='history-update-content'
                name='content'
                data-cy='content'
                type='textarea'
              />
              <UncontrolledTooltip target='contentLabel'>
                <Translate contentKey='catinyApp.historyUpdate.help.content' />
              </UncontrolledTooltip>
              <ValidatedField
                id='history-update-baseInfo'
                name='baseInfoId'
                data-cy='baseInfo'
                label={translate('catinyApp.historyUpdate.baseInfo')}
                type='select'
              >
                <option value='' key='0' />
                {baseInfos
                  ? baseInfos.map(otherEntity => (
                    <option value={otherEntity.id} key={otherEntity.id}>
                      {otherEntity.id}
                    </option>
                  ))
                  : null}
              </ValidatedField>
              <Button tag={Link} id='cancel-save' data-cy='entityCreateCancelButton' to='/history-update' replace color='info'>
                <FontAwesomeIcon icon='arrow-left' />
                &nbsp;
                <span className='d-none d-md-inline'>
                  <Translate contentKey='entity.action.back'>Back</Translate>
                </span>
              </Button>
              &nbsp;
              <Button color='primary' id='save-entity' data-cy='entityCreateSaveButton' type='submit' disabled={updating}>
                <FontAwesomeIcon icon='save' />
                &nbsp;
                <Translate contentKey='entity.action.save'>Save</Translate>
              </Button>
            </ValidatedForm>
          )}
        </Col>
      </Row>
    </div>
  );
};

export default HistoryUpdateUpdate;
