import React, {useEffect, useState} from 'react';
import {Link, RouteComponentProps} from 'react-router-dom';
import {Button, Col, Row, UncontrolledTooltip} from 'reactstrap';
import {Translate, translate, ValidatedField, ValidatedForm} from 'react-jhipster';
import {FontAwesomeIcon} from '@fortawesome/react-fontawesome';

import {createEntity, getEntity, updateEntity} from './class-info.reducer';
import {useAppDispatch, useAppSelector} from 'app/config/store';

export const ClassInfoUpdate = (props: RouteComponentProps<{ id: string }>) =>
{
  const dispatch = useAppDispatch();

  const [isNew] = useState(!props.match.params || !props.match.params.id);

  const classInfoEntity = useAppSelector(state => state.classInfo.entity);
  const loading = useAppSelector(state => state.classInfo.loading);
  const updating = useAppSelector(state => state.classInfo.updating);
  const updateSuccess = useAppSelector(state => state.classInfo.updateSuccess);

  const handleClose = () => {
    props.history.push('/class-info');
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
    const entity = {
      ...classInfoEntity,
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
      ? {}
      : {
          ...classInfoEntity,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="catinyApp.classInfo.home.createOrEditLabel" data-cy="ClassInfoCreateUpdateHeading">
            <Translate contentKey="catinyApp.classInfo.home.createOrEditLabel">Create or edit a ClassInfo</Translate>
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
                  id="class-info-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('catinyApp.classInfo.uuid')}
                id="class-info-uuid"
                name="uuid"
                data-cy="uuid"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <UncontrolledTooltip target='uuidLabel'>
                <Translate contentKey='catinyApp.classInfo.help.uuid' />
              </UncontrolledTooltip>
              <ValidatedField
                label={translate('catinyApp.classInfo.namePackage')}
                id='class-info-namePackage'
                name='namePackage'
                data-cy='namePackage'
                type='text'
              />
              <UncontrolledTooltip target='namePackageLabel'>
                <Translate contentKey='catinyApp.classInfo.help.namePackage' />
              </UncontrolledTooltip>
              <ValidatedField
                label={translate('catinyApp.classInfo.fullName')}
                id='class-info-fullName'
                name='fullName'
                data-cy='fullName'
                type='text'
                validate={{
                  required: {value: true, message: translate('entity.validation.required')},
                }}
              />
              <UncontrolledTooltip target="fullNameLabel">
                <Translate contentKey="catinyApp.classInfo.help.fullName" />
              </UncontrolledTooltip>
              <ValidatedField
                label={translate('catinyApp.classInfo.className')}
                id="class-info-className"
                name="className"
                data-cy="className"
                type="text"
              />
              <UncontrolledTooltip target="classNameLabel">
                <Translate contentKey="catinyApp.classInfo.help.className" />
              </UncontrolledTooltip>
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/class-info" replace color="info">
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

export default ClassInfoUpdate;
