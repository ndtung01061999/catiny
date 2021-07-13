import React, { useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, UncontrolledTooltip, Row, Col } from 'reactstrap';
import { Translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { getEntity } from './account-status.reducer';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const AccountStatusDetail = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  useEffect(() => {
    dispatch(getEntity(props.match.params.id));
  }, []);

  const accountStatusEntity = useAppSelector(state => state.accountStatus.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="accountStatusDetailsHeading">
          <Translate contentKey="catinyApp.accountStatus.detail.title">AccountStatus</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{accountStatusEntity.id}</dd>
          <dt>
            <span id="uuid">
              <Translate contentKey="catinyApp.accountStatus.uuid">Uuid</Translate>
            </span>
            <UncontrolledTooltip target="uuid">
              <Translate contentKey="catinyApp.accountStatus.help.uuid" />
            </UncontrolledTooltip>
          </dt>
          <dd>{accountStatusEntity.uuid}</dd>
          <dt>
            <span id="accountStatus">
              <Translate contentKey="catinyApp.accountStatus.accountStatus">Account Status</Translate>
            </span>
            <UncontrolledTooltip target="accountStatus">
              <Translate contentKey="catinyApp.accountStatus.help.accountStatus" />
            </UncontrolledTooltip>
          </dt>
          <dd>{accountStatusEntity.accountStatus}</dd>
          <dt>
            <span id="lastVisited">
              <Translate contentKey="catinyApp.accountStatus.lastVisited">Last Visited</Translate>
            </span>
            <UncontrolledTooltip target="lastVisited">
              <Translate contentKey="catinyApp.accountStatus.help.lastVisited" />
            </UncontrolledTooltip>
          </dt>
          <dd>
            {accountStatusEntity.lastVisited ? (
              <TextFormat value={accountStatusEntity.lastVisited} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="statusComment">
              <Translate contentKey="catinyApp.accountStatus.statusComment">Status Comment</Translate>
            </span>
            <UncontrolledTooltip target="statusComment">
              <Translate contentKey="catinyApp.accountStatus.help.statusComment" />
            </UncontrolledTooltip>
          </dt>
          <dd>{accountStatusEntity.statusComment}</dd>
          <dt>
            <Translate contentKey="catinyApp.accountStatus.baseInfo">Base Info</Translate>
          </dt>
          <dd>{accountStatusEntity.baseInfo ? accountStatusEntity.baseInfo.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/account-status" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/account-status/${accountStatusEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default AccountStatusDetail;
