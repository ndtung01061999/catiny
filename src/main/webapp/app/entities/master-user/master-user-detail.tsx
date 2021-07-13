import React, { useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, UncontrolledTooltip, Row, Col } from 'reactstrap';
import { Translate, byteSize } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { getEntity } from './master-user.reducer';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const MasterUserDetail = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  useEffect(() => {
    dispatch(getEntity(props.match.params.id));
  }, []);

  const masterUserEntity = useAppSelector(state => state.masterUser.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="masterUserDetailsHeading">
          <Translate contentKey="catinyApp.masterUser.detail.title">MasterUser</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{masterUserEntity.id}</dd>
          <dt>
            <span id="uuid">
              <Translate contentKey="catinyApp.masterUser.uuid">Uuid</Translate>
            </span>
            <UncontrolledTooltip target="uuid">
              <Translate contentKey="catinyApp.masterUser.help.uuid" />
            </UncontrolledTooltip>
          </dt>
          <dd>{masterUserEntity.uuid}</dd>
          <dt>
            <span id="fullName">
              <Translate contentKey="catinyApp.masterUser.fullName">Full Name</Translate>
            </span>
            <UncontrolledTooltip target="fullName">
              <Translate contentKey="catinyApp.masterUser.help.fullName" />
            </UncontrolledTooltip>
          </dt>
          <dd>{masterUserEntity.fullName}</dd>
          <dt>
            <span id="nickname">
              <Translate contentKey="catinyApp.masterUser.nickname">Nickname</Translate>
            </span>
            <UncontrolledTooltip target="nickname">
              <Translate contentKey="catinyApp.masterUser.help.nickname" />
            </UncontrolledTooltip>
          </dt>
          <dd>{masterUserEntity.nickname}</dd>
          <dt>
            <span id="quickInfo">
              <Translate contentKey="catinyApp.masterUser.quickInfo">Quick Info</Translate>
            </span>
            <UncontrolledTooltip target="quickInfo">
              <Translate contentKey="catinyApp.masterUser.help.quickInfo" />
            </UncontrolledTooltip>
          </dt>
          <dd>{masterUserEntity.quickInfo}</dd>
          <dt>
            <Translate contentKey="catinyApp.masterUser.user">User</Translate>
          </dt>
          <dd>{masterUserEntity.user ? masterUserEntity.user.id : ''}</dd>
          <dt>
            <Translate contentKey="catinyApp.masterUser.myProfile">My Profile</Translate>
          </dt>
          <dd>{masterUserEntity.myProfile ? masterUserEntity.myProfile.id : ''}</dd>
          <dt>
            <Translate contentKey="catinyApp.masterUser.myAccountStatus">My Account Status</Translate>
          </dt>
          <dd>{masterUserEntity.myAccountStatus ? masterUserEntity.myAccountStatus.id : ''}</dd>
          <dt>
            <Translate contentKey="catinyApp.masterUser.myRank">My Rank</Translate>
          </dt>
          <dd>{masterUserEntity.myRank ? masterUserEntity.myRank.id : ''}</dd>
          <dt>
            <Translate contentKey="catinyApp.masterUser.avatar">Avatar</Translate>
          </dt>
          <dd>{masterUserEntity.avatar ? masterUserEntity.avatar.id : ''}</dd>
          <dt>
            <Translate contentKey="catinyApp.masterUser.baseInfo">Base Info</Translate>
          </dt>
          <dd>{masterUserEntity.baseInfo ? masterUserEntity.baseInfo.id : ''}</dd>
          <dt>
            <Translate contentKey="catinyApp.masterUser.myGroupPost">My Group Post</Translate>
          </dt>
          <dd>
            {masterUserEntity.myGroupPosts
              ? masterUserEntity.myGroupPosts.map((val, i) => (
                  <span key={val.id}>
                    <a>{val.id}</a>
                    {masterUserEntity.myGroupPosts && i === masterUserEntity.myGroupPosts.length - 1 ? '' : ', '}
                  </span>
                ))
              : null}
          </dd>
          <dt>
            <Translate contentKey="catinyApp.masterUser.messageGroup">Message Group</Translate>
          </dt>
          <dd>
            {masterUserEntity.messageGroups
              ? masterUserEntity.messageGroups.map((val, i) => (
                  <span key={val.id}>
                    <a>{val.id}</a>
                    {masterUserEntity.messageGroups && i === masterUserEntity.messageGroups.length - 1 ? '' : ', '}
                  </span>
                ))
              : null}
          </dd>
          <dt>
            <Translate contentKey="catinyApp.masterUser.topicInterest">Topic Interest</Translate>
          </dt>
          <dd>
            {masterUserEntity.topicInterests
              ? masterUserEntity.topicInterests.map((val, i) => (
                  <span key={val.id}>
                    <a>{val.id}</a>
                    {masterUserEntity.topicInterests && i === masterUserEntity.topicInterests.length - 1 ? '' : ', '}
                  </span>
                ))
              : null}
          </dd>
        </dl>
        <Button tag={Link} to="/master-user" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/master-user/${masterUserEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default MasterUserDetail;
