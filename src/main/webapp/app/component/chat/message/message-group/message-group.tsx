import React, {useEffect} from 'react';
import {connect as reduxConnect} from 'react-redux';
import {IRootState} from 'app/shared/reducers';
import {getAllGroupsJoined, setGroupIdCurrent} from './message-group.reducer';
import {FontAwesomeIcon} from '@fortawesome/react-fontawesome';
import {faCircle, faPlusCircle} from '@fortawesome/free-solid-svg-icons';
import Media from 'reactstrap/es/Media';

const MessageGroupComponent = (props: IMessageGroupProps) =>
{
  const ITEMS_PER_PAGE = 5;

  const {messageGroupList, groupIdCurrent} = props;
  useEffect(() =>
  {
    window.console.log(props)
    props.getAllGroupsJoined(0, ITEMS_PER_PAGE * 5);
  }, []);


  const time = (lastTimeVisit) => new Date(Date.now() - new Date(lastTimeVisit).getTime()).toLocaleTimeString();


  const handleClickToGroup = (groupId) =>
  {
    setGroupIdCurrent(groupId);
    props.setGroupIdCurrent(groupId);
  }

  const messageGroups = messageGroup => (
    <a id={messageGroup.groupId} onClick={() => handleClickToGroup(messageGroup.groupId)}>
      <Media className="mb-2">
        <Media left href="">
          <div className="media-object mr-2 "
               style={{backgroundImage: 'url("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRuYNY-NrKoBnpeOC6oDCKAGXZEnHfUVgX9sA&usqp=CAU")'}}>
            <FontAwesomeIcon className="text-primary mt-3 d-none d-md-inline-block " icon={faCircle} size="xs"/>
          </div>
        </Media>
        <Media body>
          <Media heading>{messageGroup.groupName}</Media>
          <div>
            <span className="nowrap text-nowrap">{messageGroup.lastContent}</span><i
            className="d-none d-md-inline-block">{time(messageGroup.modifiedDate)}</i>
          </div>
        </Media>
      </Media>
    </a>
  );

  return (
    <div>
      <div className="d-flex justify-content-between my-2">
        <h5>follow status</h5>
        <FontAwesomeIcon icon={faPlusCircle}/>
      </div>
      <div className="display-block pre-scrollable">
        {messageGroupList
          ? (
            <div
              // loadMore={handleLoadMore}
            >
              {messageGroupList.map(messageGroup => (
                <>{messageGroups(messageGroup)}</>
              ))}
            </div>)
          :
          (<></>)}
      </div>
    </div>
  );
};

const mapStateToProps = ({messageGroupComponent, messageContentComponent}: IRootState) => ({
  messageGroupList: messageGroupComponent.messageGroupList,
  groupIdCurrent: messageContentComponent.groupIdCurrent,
});

const mapDispatchToProps = {
  getAllGroupsJoined,
  setGroupIdCurrent,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export interface IMessageGroupProps extends StateProps, DispatchProps
{
}

export default reduxConnect(mapStateToProps, mapDispatchToProps)(MessageGroupComponent);
