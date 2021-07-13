import React, { useState, useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, FormText, UncontrolledTooltip } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { getEntity, updateEntity, createEntity, reset } from './hanh-chinh-vn.reducer';
import { IHanhChinhVN } from 'app/shared/model/hanh-chinh-vn.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const HanhChinhVNUpdate = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  const [isNew] = useState(!props.match.params || !props.match.params.id);

  const hanhChinhVNEntity = useAppSelector(state => state.hanhChinhVN.entity);
  const loading = useAppSelector(state => state.hanhChinhVN.loading);
  const updating = useAppSelector(state => state.hanhChinhVN.updating);
  const updateSuccess = useAppSelector(state => state.hanhChinhVN.updateSuccess);

  const handleClose = () => {
    props.history.push('/hanh-chinh-vn');
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
      ...hanhChinhVNEntity,
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
          ...hanhChinhVNEntity,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="catinyApp.hanhChinhVN.home.createOrEditLabel" data-cy="HanhChinhVNCreateUpdateHeading">
            <Translate contentKey="catinyApp.hanhChinhVN.home.createOrEditLabel">Create or edit a HanhChinhVN</Translate>
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
                  id="hanh-chinh-vn-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('catinyApp.hanhChinhVN.name')}
                id="hanh-chinh-vn-name"
                name="name"
                data-cy="name"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <UncontrolledTooltip target="nameLabel">
                <Translate contentKey="catinyApp.hanhChinhVN.help.name" />
              </UncontrolledTooltip>
              <ValidatedField
                label={translate('catinyApp.hanhChinhVN.slug')}
                id="hanh-chinh-vn-slug"
                name="slug"
                data-cy="slug"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <UncontrolledTooltip target="slugLabel">
                <Translate contentKey="catinyApp.hanhChinhVN.help.slug" />
              </UncontrolledTooltip>
              <ValidatedField
                label={translate('catinyApp.hanhChinhVN.type')}
                id="hanh-chinh-vn-type"
                name="type"
                data-cy="type"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <UncontrolledTooltip target="typeLabel">
                <Translate contentKey="catinyApp.hanhChinhVN.help.type" />
              </UncontrolledTooltip>
              <ValidatedField
                label={translate('catinyApp.hanhChinhVN.nameWithType')}
                id="hanh-chinh-vn-nameWithType"
                name="nameWithType"
                data-cy="nameWithType"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <UncontrolledTooltip target="nameWithTypeLabel">
                <Translate contentKey="catinyApp.hanhChinhVN.help.nameWithType" />
              </UncontrolledTooltip>
              <ValidatedField
                label={translate('catinyApp.hanhChinhVN.code')}
                id="hanh-chinh-vn-code"
                name="code"
                data-cy="code"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <UncontrolledTooltip target="codeLabel">
                <Translate contentKey="catinyApp.hanhChinhVN.help.code" />
              </UncontrolledTooltip>
              <ValidatedField
                label={translate('catinyApp.hanhChinhVN.parentCode')}
                id="hanh-chinh-vn-parentCode"
                name="parentCode"
                data-cy="parentCode"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <UncontrolledTooltip target="parentCodeLabel">
                <Translate contentKey="catinyApp.hanhChinhVN.help.parentCode" />
              </UncontrolledTooltip>
              <ValidatedField
                label={translate('catinyApp.hanhChinhVN.path')}
                id="hanh-chinh-vn-path"
                name="path"
                data-cy="path"
                type="text"
              />
              <UncontrolledTooltip target="pathLabel">
                <Translate contentKey="catinyApp.hanhChinhVN.help.path" />
              </UncontrolledTooltip>
              <ValidatedField
                label={translate('catinyApp.hanhChinhVN.pathWithType')}
                id="hanh-chinh-vn-pathWithType"
                name="pathWithType"
                data-cy="pathWithType"
                type="text"
              />
              <UncontrolledTooltip target="pathWithTypeLabel">
                <Translate contentKey="catinyApp.hanhChinhVN.help.pathWithType" />
              </UncontrolledTooltip>
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/hanh-chinh-vn" replace color="info">
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

export default HanhChinhVNUpdate;
