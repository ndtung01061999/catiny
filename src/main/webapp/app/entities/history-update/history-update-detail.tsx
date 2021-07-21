import React, {useEffect} from 'react';
import {Link, RouteComponentProps} from 'react-router-dom';
import {Button, Col, Row, UncontrolledTooltip} from 'reactstrap';
import {Translate} from 'react-jhipster';
import {FontAwesomeIcon} from '@fortawesome/react-fontawesome';

import {getEntity} from './history-update.reducer';
import {useAppDispatch, useAppSelector} from 'app/config/store';

export const HistoryUpdateDetail = (props: RouteComponentProps<{ id: string }>) =>
{
  const dispatch = useAppDispatch();

  useEffect(() =>
  {
    dispatch(getEntity(props.match.params.id));
  }, []);

  const historyUpdateEntity = useAppSelector(state => state.historyUpdate.entity);
  return (
    <Row>
      <Col md='8'>
        <h2 data-cy='historyUpdateDetailsHeading'>
          <Translate contentKey='catinyApp.historyUpdate.detail.title'>HistoryUpdate</Translate>
        </h2>
        <dl className='jh-entity-details'>
          <dt>
            <span id='id'>
              <Translate contentKey='global.field.id'>ID</Translate>
            </span>
          </dt>
          <dd>{historyUpdateEntity.id}</dd>
          <dt>
            <span id='uuid'>
              <Translate contentKey='catinyApp.historyUpdate.uuid'>Uuid</Translate>
            </span>
            <UncontrolledTooltip target='uuid'>
              <Translate contentKey='catinyApp.historyUpdate.help.uuid' />
            </UncontrolledTooltip>
          </dt>
          <dd>{historyUpdateEntity.uuid}</dd>
          <dt>
            <span id='version'>
              <Translate contentKey='catinyApp.historyUpdate.version'>Version</Translate>
            </span>
            <UncontrolledTooltip target='version'>
              <Translate contentKey='catinyApp.historyUpdate.help.version' />
            </UncontrolledTooltip>
          </dt>
          <dd>{historyUpdateEntity.version}</dd>
          <dt>
            <span id='content'>
              <Translate contentKey='catinyApp.historyUpdate.content'>Content</Translate>
            </span>
            <UncontrolledTooltip target='content'>
              <Translate contentKey='catinyApp.historyUpdate.help.content' />
            </UncontrolledTooltip>
          </dt>
          <dd>{historyUpdateEntity.content}</dd>
          <dt>
            <Translate contentKey='catinyApp.historyUpdate.baseInfo'>Base Info</Translate>
          </dt>
          <dd>{historyUpdateEntity.baseInfo ? historyUpdateEntity.baseInfo.id : ''}</dd>
        </dl>
        <Button tag={Link} to='/history-update' replace color='info' data-cy='entityDetailsBackButton'>
          <FontAwesomeIcon icon='arrow-left' />{' '}
          <span className='d-none d-md-inline'>
            <Translate contentKey='entity.action.back'>Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/history-update/${historyUpdateEntity.id}/edit`} replace color='primary'>
          <FontAwesomeIcon icon='pencil-alt' />{' '}
          <span className='d-none d-md-inline'>
            <Translate contentKey='entity.action.edit'>Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default HistoryUpdateDetail;
