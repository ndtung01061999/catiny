import React, {useEffect, useState} from 'react';
import {Link, RouteComponentProps} from 'react-router-dom';
import {Button, Col, Row, UncontrolledTooltip} from 'reactstrap';
import {Translate, translate, ValidatedField, ValidatedForm} from 'react-jhipster';
import {FontAwesomeIcon} from '@fortawesome/react-fontawesome';
import {getEntities as getBaseInfos} from 'app/entities/base-info/base-info.reducer';
import {getEntities as getMasterUsers} from 'app/entities/master-user/master-user.reducer';
import {createEntity, getEntity, updateEntity} from './permission.reducer';
import {useAppDispatch, useAppSelector} from 'app/config/store';

export const PermissionUpdate = (props: RouteComponentProps<{ id: string }>) =>
{
  const dispatch = useAppDispatch();

  const [isNew] = useState(!props.match.params || !props.match.params.id);

  const baseInfos = useAppSelector(state => state.baseInfo.entities);
  const masterUsers = useAppSelector(state => state.masterUser.entities);
  const permissionEntity = useAppSelector(state => state.permission.entity);
  const loading = useAppSelector(state => state.permission.loading);
  const updating = useAppSelector(state => state.permission.updating);
  const updateSuccess = useAppSelector(state => state.permission.updateSuccess);

  const handleClose = () => {
    props.history.push('/permission');
  };

  useEffect(() => {
    if (!isNew) {
      dispatch(getEntity(props.match.params.id));
    }

    dispatch(getBaseInfos({}));
    dispatch(getMasterUsers({}));
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  const saveEntity = values => {
    const entity = {
      ...permissionEntity,
      ...values,
      baseInfo: baseInfos.find(it => it.id.toString() === values.baseInfoId.toString()),
      masterUser: masterUsers.find(it => it.id.toString() === values.masterUserId.toString()),
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
          ...permissionEntity,
          baseInfoId: permissionEntity?.baseInfo?.id,
          masterUserId: permissionEntity?.masterUser?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="catinyApp.permission.home.createOrEditLabel" data-cy="PermissionCreateUpdateHeading">
            <Translate contentKey="catinyApp.permission.home.createOrEditLabel">Create or edit a Permission</Translate>
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
                  name='id'
                  required
                  readOnly
                  id='permission-id'
                  label={translate('global.field.id')}
                  validate={{required: true}}
                />
              ) : null}
              <ValidatedField
                label={translate('catinyApp.permission.uuid')}
                id='permission-uuid'
                name='uuid'
                data-cy='uuid'
                type='text'
                validate={{
                  required: {value: true, message: translate('entity.validation.required')},
                }}
              />
              <UncontrolledTooltip target='uuidLabel'>
                <Translate contentKey='catinyApp.permission.help.uuid' />
              </UncontrolledTooltip>
              <ValidatedField
                label={translate('catinyApp.permission.read')}
                id='permission-read'
                name='read'
                data-cy='read'
                check
                type='checkbox'
              />
              <UncontrolledTooltip target='readLabel'>
                <Translate contentKey='catinyApp.permission.help.read' />
              </UncontrolledTooltip>
              <ValidatedField
                label={translate('catinyApp.permission.write')}
                id="permission-write"
                name="write"
                data-cy="write"
                check
                type="checkbox"
              />
              <UncontrolledTooltip target="writeLabel">
                <Translate contentKey="catinyApp.permission.help.write" />
              </UncontrolledTooltip>
              <ValidatedField
                label={translate('catinyApp.permission.share')}
                id="permission-share"
                name="share"
                data-cy="share"
                check
                type="checkbox"
              />
              <UncontrolledTooltip target="shareLabel">
                <Translate contentKey="catinyApp.permission.help.share" />
              </UncontrolledTooltip>
              <ValidatedField
                label={translate('catinyApp.permission.delete')}
                id="permission-delete"
                name="delete"
                data-cy="delete"
                check
                type="checkbox"
              />
              <UncontrolledTooltip target="deleteLabel">
                <Translate contentKey="catinyApp.permission.help.delete" />
              </UncontrolledTooltip>
              <ValidatedField
                label={translate('catinyApp.permission.add')}
                id="permission-add"
                name="add"
                data-cy="add"
                check
                type="checkbox"
              />
              <UncontrolledTooltip target="addLabel">
                <Translate contentKey="catinyApp.permission.help.add" />
              </UncontrolledTooltip>
              <ValidatedField
                label={translate('catinyApp.permission.level')}
                id="permission-level"
                name="level"
                data-cy="level"
                type="text"
              />
              <UncontrolledTooltip target="levelLabel">
                <Translate contentKey="catinyApp.permission.help.level" />
              </UncontrolledTooltip>
              <ValidatedField
                id="permission-baseInfo"
                name="baseInfoId"
                data-cy="baseInfo"
                label={translate('catinyApp.permission.baseInfo')}
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
                id="permission-masterUser"
                name="masterUserId"
                data-cy="masterUser"
                label={translate('catinyApp.permission.masterUser')}
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
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/permission" replace color="info">
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

export default PermissionUpdate;
