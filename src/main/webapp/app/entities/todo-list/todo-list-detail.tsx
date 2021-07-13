import React, { useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, UncontrolledTooltip, Row, Col } from 'reactstrap';
import { Translate, byteSize } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { getEntity } from './todo-list.reducer';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const TodoListDetail = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  useEffect(() => {
    dispatch(getEntity(props.match.params.id));
  }, []);

  const todoListEntity = useAppSelector(state => state.todoList.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="todoListDetailsHeading">
          <Translate contentKey="catinyApp.todoList.detail.title">TodoList</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{todoListEntity.id}</dd>
          <dt>
            <span id="uuid">
              <Translate contentKey="catinyApp.todoList.uuid">Uuid</Translate>
            </span>
            <UncontrolledTooltip target="uuid">
              <Translate contentKey="catinyApp.todoList.help.uuid" />
            </UncontrolledTooltip>
          </dt>
          <dd>{todoListEntity.uuid}</dd>
          <dt>
            <span id="title">
              <Translate contentKey="catinyApp.todoList.title">Title</Translate>
            </span>
          </dt>
          <dd>{todoListEntity.title}</dd>
          <dt>
            <span id="content">
              <Translate contentKey="catinyApp.todoList.content">Content</Translate>
            </span>
          </dt>
          <dd>{todoListEntity.content}</dd>
          <dt>
            <Translate contentKey="catinyApp.todoList.baseInfo">Base Info</Translate>
          </dt>
          <dd>{todoListEntity.baseInfo ? todoListEntity.baseInfo.id : ''}</dd>
          <dt>
            <Translate contentKey="catinyApp.todoList.masterUser">Master User</Translate>
          </dt>
          <dd>{todoListEntity.masterUser ? todoListEntity.masterUser.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/todo-list" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/todo-list/${todoListEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default TodoListDetail;
