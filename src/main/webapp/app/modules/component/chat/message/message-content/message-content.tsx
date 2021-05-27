import React, {useEffect, useState} from 'react';
import {connect} from 'react-redux';
import {RouteComponentProps} from 'react-router-dom';
import {getSortState} from 'react-jhipster';

import {IRootState} from 'app/shared/reducers';
import {getEntities, getSearchEntities, reset} from './message-content.reducer';
import {ITEMS_PER_PAGE} from 'app/shared/util/pagination.constants';
import {overridePaginationStateWithQueryParams} from 'app/shared/util/entity-utils';

export interface IMessageContentProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }>
{
}

export const MessageContent = (props: IMessageContentProps) =>
{
  const [paginationState, setPaginationState] = useState(
    overridePaginationStateWithQueryParams(getSortState(props.location, ITEMS_PER_PAGE, 'id'), props.location.search)
  );


  const resetAll = () =>
  {
    props.reset();
    setPaginationState({
      ...paginationState,
      activePage: 1,
    });
    props.getEntities();
  };

  useEffect(() => {
    resetAll();
  }, []);


  const clear = () => {
    props.reset();
    setPaginationState({
      ...paginationState,
      activePage: 1,
    });
    props.getEntities();
  };


  const handleLoadMore = () => {
    if ((window as any).pageYOffset > 0) {
      setPaginationState({
        ...paginationState,
        activePage: paginationState.activePage + 1,
      });
    }
  };


  const handleSyncList = () => {
    resetAll();
  };

  const { messageContentList, match, loading } = props;
  return (
    <div>
    </div>
  );
};

const mapStateToProps = ({ messageContent }: IRootState) => ({
  messageContentList: messageContent.entities,
  loading: messageContent.loading,
  totalItems: messageContent.totalItems,
  links: messageContent.links,
  entity: messageContent.entity,
  updateSuccess: messageContent.updateSuccess,
});

const mapDispatchToProps = {
  getSearchEntities,
  getEntities,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(MessageContent);
