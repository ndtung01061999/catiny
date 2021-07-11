import React, { useState, useEffect } from 'react';
import InfiniteScroll from 'react-infinite-scroller';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Input, InputGroup, FormGroup, Form, Col, Row, Table } from 'reactstrap';
import { openFile, byteSize, Translate, translate, getSortState } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { searchEntities, getEntities, reset } from './video-live-stream-buffer.reducer';
import { IVideoLiveStreamBuffer } from 'app/shared/model/video-live-stream-buffer.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { ASC, DESC, ITEMS_PER_PAGE, SORT } from 'app/shared/util/pagination.constants';
import { overridePaginationStateWithQueryParams } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const VideoLiveStreamBuffer = (props: RouteComponentProps<{ url: string }>) => {
  const dispatch = useAppDispatch();

  const [search, setSearch] = useState('');
  const [paginationState, setPaginationState] = useState(
    overridePaginationStateWithQueryParams(getSortState(props.location, ITEMS_PER_PAGE, 'id'), props.location.search)
  );
  const [sorting, setSorting] = useState(false);

  const videoLiveStreamBufferList = useAppSelector(state => state.videoLiveStreamBuffer.entities);
  const loading = useAppSelector(state => state.videoLiveStreamBuffer.loading);
  const totalItems = useAppSelector(state => state.videoLiveStreamBuffer.totalItems);
  const links = useAppSelector(state => state.videoLiveStreamBuffer.links);
  const entity = useAppSelector(state => state.videoLiveStreamBuffer.entity);
  const updateSuccess = useAppSelector(state => state.videoLiveStreamBuffer.updateSuccess);

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
      <h2 id="video-live-stream-buffer-heading" data-cy="VideoLiveStreamBufferHeading">
        <Translate contentKey="catinyApp.videoLiveStreamBuffer.home.title">Video Live Stream Buffers</Translate>
        <div className="d-flex justify-content-end">
          <Button className="mr-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="catinyApp.videoLiveStreamBuffer.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link to={`${match.url}/new`} className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="catinyApp.videoLiveStreamBuffer.home.createLabel">Create new Video Live Stream Buffer</Translate>
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
                  placeholder={translate('catinyApp.videoLiveStreamBuffer.home.search')}
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
          {videoLiveStreamBufferList && videoLiveStreamBufferList.length > 0 ? (
            <Table responsive>
              <thead>
                <tr>
                  <th className="hand" onClick={sort('id')}>
                    <Translate contentKey="catinyApp.videoLiveStreamBuffer.id">ID</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={sort('uuid')}>
                    <Translate contentKey="catinyApp.videoLiveStreamBuffer.uuid">Uuid</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={sort('bufferData')}>
                    <Translate contentKey="catinyApp.videoLiveStreamBuffer.bufferData">Buffer Data</Translate>{' '}
                    <FontAwesomeIcon icon="sort" />
                  </th>
                  <th>
                    <Translate contentKey="catinyApp.videoLiveStreamBuffer.baseInfo">Base Info</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th>
                    <Translate contentKey="catinyApp.videoLiveStreamBuffer.videoStream">Video Stream</Translate>{' '}
                    <FontAwesomeIcon icon="sort" />
                  </th>
                  <th />
                </tr>
              </thead>
              <tbody>
                {videoLiveStreamBufferList.map((videoLiveStreamBuffer, i) => (
                  <tr key={`entity-${i}`} data-cy="entityTable">
                    <td>
                      <Button tag={Link} to={`${match.url}/${videoLiveStreamBuffer.id}`} color="link" size="sm">
                        {videoLiveStreamBuffer.id}
                      </Button>
                    </td>
                    <td>{videoLiveStreamBuffer.uuid}</td>
                    <td>
                      {videoLiveStreamBuffer.bufferData ? (
                        <div>
                          {videoLiveStreamBuffer.bufferDataContentType ? (
                            <a onClick={openFile(videoLiveStreamBuffer.bufferDataContentType, videoLiveStreamBuffer.bufferData)}>
                              <Translate contentKey="entity.action.open">Open</Translate>
                              &nbsp;
                            </a>
                          ) : null}
                          <span>
                            {videoLiveStreamBuffer.bufferDataContentType}, {byteSize(videoLiveStreamBuffer.bufferData)}
                          </span>
                        </div>
                      ) : null}
                    </td>
                    <td>
                      {videoLiveStreamBuffer.baseInfo ? (
                        <Link to={`base-info/${videoLiveStreamBuffer.baseInfo.id}`}>{videoLiveStreamBuffer.baseInfo.id}</Link>
                      ) : (
                        ''
                      )}
                    </td>
                    <td>
                      {videoLiveStreamBuffer.videoStream ? (
                        <Link to={`video-stream/${videoLiveStreamBuffer.videoStream.id}`}>{videoLiveStreamBuffer.videoStream.id}</Link>
                      ) : (
                        ''
                      )}
                    </td>
                    <td className="text-right">
                      <div className="btn-group flex-btn-group-container">
                        <Button
                          tag={Link}
                          to={`${match.url}/${videoLiveStreamBuffer.id}`}
                          color="info"
                          size="sm"
                          data-cy="entityDetailsButton"
                        >
                          <FontAwesomeIcon icon="eye" />{' '}
                          <span className="d-none d-md-inline">
                            <Translate contentKey="entity.action.view">View</Translate>
                          </span>
                        </Button>
                        <Button
                          tag={Link}
                          to={`${match.url}/${videoLiveStreamBuffer.id}/edit`}
                          color="primary"
                          size="sm"
                          data-cy="entityEditButton"
                        >
                          <FontAwesomeIcon icon="pencil-alt" />{' '}
                          <span className="d-none d-md-inline">
                            <Translate contentKey="entity.action.edit">Edit</Translate>
                          </span>
                        </Button>
                        <Button
                          tag={Link}
                          to={`${match.url}/${videoLiveStreamBuffer.id}/delete`}
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
                <Translate contentKey="catinyApp.videoLiveStreamBuffer.home.notFound">No Video Live Stream Buffers found</Translate>
              </div>
            )
          )}
        </InfiniteScroll>
      </div>
    </div>
  );
};

export default VideoLiveStreamBuffer;
