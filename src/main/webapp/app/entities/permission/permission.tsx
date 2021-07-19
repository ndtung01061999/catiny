import React, {useEffect, useState} from 'react';
import InfiniteScroll from 'react-infinite-scroller';
import {Link, RouteComponentProps} from 'react-router-dom';
import {Button, Col, Form, FormGroup, Input, InputGroup, Row, Table} from 'reactstrap';
import {getSortState, translate, Translate} from 'react-jhipster';
import {FontAwesomeIcon} from '@fortawesome/react-fontawesome';

import {getEntities, reset, searchEntities} from './permission.reducer';
import {ASC, DESC, ITEMS_PER_PAGE} from 'app/shared/util/pagination.constants';
import {overridePaginationStateWithQueryParams} from 'app/shared/util/entity-utils';
import {useAppDispatch, useAppSelector} from 'app/config/store';

export const Permission = (props: RouteComponentProps<{ url: string }>) =>
{
  const dispatch = useAppDispatch();

  const [search, setSearch] = useState('');
  const [paginationState, setPaginationState] = useState(
    overridePaginationStateWithQueryParams(getSortState(props.location, ITEMS_PER_PAGE, 'id'), props.location.search)
  );
  const [sorting, setSorting] = useState(false);

  const permissionList = useAppSelector(state => state.permission.entities);
  const loading = useAppSelector(state => state.permission.loading);
  const totalItems = useAppSelector(state => state.permission.totalItems);
  const links = useAppSelector(state => state.permission.links);
  const entity = useAppSelector(state => state.permission.entity);
  const updateSuccess = useAppSelector(state => state.permission.updateSuccess);

  const getAllEntities = () => {
    if (search) {
      dispatch(
        searchEntities({
          query: search,
          page: paginationState.activePage - 1,
          size: paginationState.itemsPerPage,
          sort: `${paginationState.sort},${paginationState.order}`,
        })
      );
    } else {
      dispatch(
        getEntities({
          page: paginationState.activePage - 1,
          size: paginationState.itemsPerPage,
          sort: `${paginationState.sort},${paginationState.order}`,
        })
      );
    }
  };

  const resetAll = () => {
    dispatch(reset());
    setPaginationState({
      ...paginationState,
      activePage: 1,
    });
    dispatch(getEntities({}));
  };

  useEffect(() => {
    resetAll();
  }, []);

  const startSearching = e => {
    if (search) {
      dispatch(reset());
      setPaginationState({
        ...paginationState,
        activePage: 1,
      });
      dispatch(
        searchEntities({
          query: search,
          page: paginationState.activePage - 1,
          size: paginationState.itemsPerPage,
          sort: `${paginationState.sort},${paginationState.order}`,
        })
      );
    }
    e.preventDefault();
  };

  const clear = () => {
    dispatch(reset());
    setSearch('');
    setPaginationState({
      ...paginationState,
      activePage: 1,
    });
    dispatch(getEntities({}));
  };

  const handleSearch = event => setSearch(event.target.value);

  useEffect(() => {
    if (updateSuccess) {
      resetAll();
    }
  }, [updateSuccess]);

  useEffect(() => {
    getAllEntities();
  }, [paginationState.activePage]);

  const handleLoadMore = () => {
    if ((window as any).pageYOffset > 0) {
      setPaginationState({
        ...paginationState,
        activePage: paginationState.activePage + 1,
      });
    }
  };

  useEffect(() => {
    if (sorting) {
      getAllEntities();
      setSorting(false);
    }
  }, [sorting, search]);

  const sort = p => () => {
    dispatch(reset());
    setPaginationState({
      ...paginationState,
      activePage: 1,
      order: paginationState.order === ASC ? DESC : ASC,
      sort: p,
    });
    setSorting(true);
  };

  const handleSyncList = () => {
    resetAll();
  };

  const { match } = props;

  return (
    <div>
      <h2 id="permission-heading" data-cy="PermissionHeading">
        <Translate contentKey="catinyApp.permission.home.title">Permissions</Translate>
        <div className="d-flex justify-content-end">
          <Button className="mr-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="catinyApp.permission.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link to={`${match.url}/new`} className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="catinyApp.permission.home.createLabel">Create new Permission</Translate>
          </Link>
        </div>
      </h2>
      <Row>
        <Col sm="12">
          <Form onSubmit={startSearching}>
            <FormGroup>
              <InputGroup>
                <Input
                  type="text"
                  name="search"
                  defaultValue={search}
                  onChange={handleSearch}
                  placeholder={translate('catinyApp.permission.home.search')}
                />
                <Button className="input-group-addon">
                  <FontAwesomeIcon icon="search" />
                </Button>
                <Button type="reset" className="input-group-addon" onClick={clear}>
                  <FontAwesomeIcon icon="trash" />
                </Button>
              </InputGroup>
            </FormGroup>
          </Form>
        </Col>
      </Row>
      <div className="table-responsive">
        <InfiniteScroll
          pageStart={paginationState.activePage}
          loadMore={handleLoadMore}
          hasMore={paginationState.activePage - 1 < links.next}
          loader={<div className="loader">Loading ...</div>}
          threshold={0}
          initialLoad={false}
        >
          {permissionList && permissionList.length > 0 ? (
            <Table responsive>
              <thead>
              <tr>
                <th className='hand' onClick={sort('id')}>
                  <Translate contentKey='catinyApp.permission.id'>ID</Translate> <FontAwesomeIcon icon='sort' />
                </th>
                <th className='hand' onClick={sort('uuid')}>
                  <Translate contentKey='catinyApp.permission.uuid'>Uuid</Translate> <FontAwesomeIcon icon='sort' />
                </th>
                <th className='hand' onClick={sort('read')}>
                  <Translate contentKey='catinyApp.permission.read'>Read</Translate> <FontAwesomeIcon icon='sort' />
                </th>
                <th className='hand' onClick={sort('write')}>
                  <Translate contentKey='catinyApp.permission.write'>Write</Translate> <FontAwesomeIcon icon='sort' />
                </th>
                <th className='hand' onClick={sort('share')}>
                  <Translate contentKey='catinyApp.permission.share'>Share</Translate> <FontAwesomeIcon icon='sort' />
                </th>
                <th className='hand' onClick={sort('delete')}>
                    <Translate contentKey="catinyApp.permission.delete">Delete</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={sort('add')}>
                    <Translate contentKey="catinyApp.permission.add">Add</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={sort('level')}>
                    <Translate contentKey="catinyApp.permission.level">Level</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th>
                    <Translate contentKey="catinyApp.permission.baseInfo">Base Info</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th>
                    <Translate contentKey="catinyApp.permission.masterUser">Master User</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th />
                </tr>
              </thead>
              <tbody>
                {permissionList.map((permission, i) => (
                  <tr key={`entity-${i}`} data-cy="entityTable">
                    <td>
                      <Button tag={Link} to={`${match.url}/${permission.id}`} color='link' size='sm'>
                        {permission.id}
                      </Button>
                    </td>
                    <td>{permission.uuid}</td>
                    <td>{permission.read ? 'true' : 'false'}</td>
                    <td>{permission.write ? 'true' : 'false'}</td>
                    <td>{permission.share ? 'true' : 'false'}</td>
                    <td>{permission.delete ? 'true' : 'false'}</td>
                    <td>{permission.add ? 'true' : 'false'}</td>
                    <td>{permission.level}</td>
                    <td>{permission.baseInfo ? <Link to={`base-info/${permission.baseInfo.id}`}>{permission.baseInfo.id}</Link> : ''}</td>
                    <td>
                      {permission.masterUser ? <Link to={`master-user/${permission.masterUser.id}`}>{permission.masterUser.id}</Link> : ''}
                    </td>
                    <td className="text-right">
                      <div className="btn-group flex-btn-group-container">
                        <Button tag={Link} to={`${match.url}/${permission.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                          <FontAwesomeIcon icon="eye" />{' '}
                          <span className="d-none d-md-inline">
                            <Translate contentKey="entity.action.view">View</Translate>
                          </span>
                        </Button>
                        <Button tag={Link} to={`${match.url}/${permission.id}/edit`} color="primary" size="sm" data-cy="entityEditButton">
                          <FontAwesomeIcon icon="pencil-alt" />{' '}
                          <span className="d-none d-md-inline">
                            <Translate contentKey="entity.action.edit">Edit</Translate>
                          </span>
                        </Button>
                        <Button
                          tag={Link}
                          to={`${match.url}/${permission.id}/delete`}
                          color="danger"
                          size="sm"
                          data-cy="entityDeleteButton"
                        >
                          <FontAwesomeIcon icon="trash" />{' '}
                          <span className="d-none d-md-inline">
                            <Translate contentKey="entity.action.delete">Delete</Translate>
                          </span>
                        </Button>
                      </div>
                    </td>
                  </tr>
                ))}
              </tbody>
            </Table>
          ) : (
            !loading && (
              <div className="alert alert-warning">
                <Translate contentKey="catinyApp.permission.home.notFound">No Permissions found</Translate>
              </div>
            )
          )}
        </InfiniteScroll>
      </div>
    </div>
  );
};

export default Permission;
