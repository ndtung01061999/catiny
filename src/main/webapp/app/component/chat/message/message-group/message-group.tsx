// import React, {useEffect, useState} from 'react';
// import {connect as reduxConnect} from 'react-redux';
// import {IRootState} from 'app/shared/reducers';
// import {getAllGroupsJoined} from './message-group.reducer';
// import {FontAwesomeIcon} from '@fortawesome/react-fontawesome';
// import {faCircle, faPlusCircle} from '@fortawesome/free-solid-svg-icons';
// import Media from 'reactstrap/es/Media';
// import {Link} from 'react-router-dom';
// import {Input, Tooltip} from 'reactstrap';
//
// const MessageGroupComponent = (props: IMessageGroupProps) =>
// {
//   const ITEMS_PER_PAGE = 5;
//   const [tooltipOpen, setTooltipOpen] = useState(false);
//   const [userAdded, setUserAdded] = useState([]);
//   const {messageGroupList} = props;
//   useEffect(() =>
//   {
//     window.console.log(props)
//     props.getAllGroupsJoined(0, ITEMS_PER_PAGE * 5);
//   }, []);
//
//
//   const messageGroups = messageGroup =>
//   {
//     const time = (lastTimeVisit) => new Date(Date.now() - new Date(lastTimeVisit).getTime()).toLocaleTimeString();
//     return (
//       <Link to={`/chat/messages/${messageGroup.groupId}`} id={messageGroup.groupId}>
//         <Media className="mb-2">
//           <Media left href="">
//             <div className="media-object mr-2 "
//                  style={{backgroundImage: 'url("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRuYNY-NrKoBnpeOC6oDCKAGXZEnHfUVgX9sA&usqp=CAU")'}}>
//               <FontAwesomeIcon className="text-primary mt-3 d-none d-md-inline-block " icon={faCircle} size="xs"/>
//             </div>
//           </Media>
//           <Media body>
//             <Media heading>{messageGroup.groupName}</Media>
//             <div>
//               <span className="nowrap text-nowrap">{messageGroup.lastContent}</span><i
//               className="d-none d-md-inline-block">{time(messageGroup.modifiedDate)}</i>
//             </div>
//           </Media>
//         </Media>
//       </Link>
//     );
//   }
//
//
//   const searchUser = () =>
//   {
//     const handleAddUser = () =>
//     {
//       window.console.log("sádfdsfdsfdfsd")
//     }
//     const handleTypingSearchUser = (event) =>
//     {
//       window.console.log(event.target.value)
//     }
//
//     return (
//       <div>
//         <div>
//         </div>
//         <Input className="my-2" onKeyUp={handleTypingSearchUser}/>
//         <div>
//           <Media className="my-2 text-center overflow-hidden" onClick={handleAddUser}>
//             <Media left href="">
//               <div className="media-object mr-2"
//                    style={{backgroundImage: 'url("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRuYNY-NrKoBnpeOC6oDCKAGXZEnHfUVgX9sA&usqp=CAU")'}}>
//               </div>
//             </Media>
//             <Media body>
//               <Media heading className="text-nowrap text-white pt-2">dsada sdasfdsfd fe fds fdss fds fsdf sdf sdf sdf sfsdfe ff s ẻ ttert ert ret
//                 rêt</Media>
//             </Media>
//           </Media>
//         </div>
//
//       </div>
//     )
//   }
//
//   return (
//     <div>
//       <div className="d-flex justify-content-between my-2">
//         <h5>follow status</h5>
//         <FontAwesomeIcon id="createNewGroup" icon={faPlusCircle}/>
//         <Tooltip placement="bottom-start" isOpen={tooltipOpen} autohide={false} target="createNewGroup" toggle={() => setTooltipOpen(!tooltipOpen)}
//                  trigger="click" className="search-user">
//           {searchUser()}
//         </Tooltip>
//       </div>
//       <div className="display-block">
//         {messageGroupList
//           ? (
//             <div className="pre-scrollable"// loadMore={handleLoadMore}
//             >
//               {messageGroupList.map(messageGroup => (
//                 <>{messageGroups(messageGroup)}</>
//               ))}
//             </div>)
//           :
//           (<></>)}
//       </div>
//     </div>
//   );
// };
//
// const mapStateToProps = ({messageGroupComponent, messageContentComponent}: IRootState) => ({
//   messageGroupList: messageGroupComponent.messageGroupList,
//   groupIdCurrent: messageContentComponent.groupIdCurrent,
// });
//
// const mapDispatchToProps = {
//   getAllGroupsJoined,
// };
//
// type StateProps = ReturnType<typeof mapStateToProps>;
// type DispatchProps = typeof mapDispatchToProps;
//
// export interface IMessageGroupProps extends StateProps, DispatchProps
// {
// }
//
// export default reduxConnect(mapStateToProps, mapDispatchToProps)(MessageGroupComponent);
