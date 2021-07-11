import React from 'react';
import MenuItem from 'app/shared/layout/menus/menu-item';
import { Translate, translate } from 'react-jhipster';
import { NavDropdown } from './menu-components';

export const EntitiesMenu = props => (
  <NavDropdown
    icon="th-list"
    name={translate('global.menu.entities.main')}
    id="entity-menu"
    data-cy="entity"
    style={{ maxHeight: '80vh', overflow: 'auto' }}
  >
    <>{/* to avoid warnings when empty */}</>
    <MenuItem icon="asterisk" to="/message-group">
      <Translate contentKey="global.menu.entities.messageGroup" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/message-content">
      <Translate contentKey="global.menu.entities.messageContent" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/hanh-chinh-vn">
      <Translate contentKey="global.menu.entities.hanhChinhVn" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/master-user">
      <Translate contentKey="global.menu.entities.masterUser" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/user-profile">
      <Translate contentKey="global.menu.entities.userProfile" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/account-status">
      <Translate contentKey="global.menu.entities.accountStatus" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/device-status">
      <Translate contentKey="global.menu.entities.deviceStatus" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/friend">
      <Translate contentKey="global.menu.entities.friend" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/follow-user">
      <Translate contentKey="global.menu.entities.followUser" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/follow-group">
      <Translate contentKey="global.menu.entities.followGroup" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/follow-page">
      <Translate contentKey="global.menu.entities.followPage" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/file-info">
      <Translate contentKey="global.menu.entities.fileInfo" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/page-post">
      <Translate contentKey="global.menu.entities.pagePost" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/page-profile">
      <Translate contentKey="global.menu.entities.pageProfile" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/group-post">
      <Translate contentKey="global.menu.entities.groupPost" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/post">
      <Translate contentKey="global.menu.entities.post" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/post-comment">
      <Translate contentKey="global.menu.entities.postComment" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/post-like">
      <Translate contentKey="global.menu.entities.postLike" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/group-profile">
      <Translate contentKey="global.menu.entities.groupProfile" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/news-feed">
      <Translate contentKey="global.menu.entities.newsFeed" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/rank-user">
      <Translate contentKey="global.menu.entities.rankUser" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/rank-group">
      <Translate contentKey="global.menu.entities.rankGroup" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/notification">
      <Translate contentKey="global.menu.entities.notification" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/album">
      <Translate contentKey="global.menu.entities.album" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/video">
      <Translate contentKey="global.menu.entities.video" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/image">
      <Translate contentKey="global.menu.entities.image" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/video-stream">
      <Translate contentKey="global.menu.entities.videoStream" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/video-live-stream-buffer">
      <Translate contentKey="global.menu.entities.videoLiveStreamBuffer" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/topic-interest">
      <Translate contentKey="global.menu.entities.topicInterest" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/todo-list">
      <Translate contentKey="global.menu.entities.todoList" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/event">
      <Translate contentKey="global.menu.entities.event" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/base-info">
      <Translate contentKey="global.menu.entities.baseInfo" />
    </MenuItem>
    {/* jhipster-needle-add-entity-to-menu - JHipster will add entities to the menu here */}
  </NavDropdown>
);
