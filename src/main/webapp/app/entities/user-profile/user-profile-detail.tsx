import React, { useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, UncontrolledTooltip, Row, Col } from 'reactstrap';
import { Translate, byteSize } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { getEntity } from './user-profile.reducer';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const UserProfileDetail = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  useEffect(() => {
    dispatch(getEntity(props.match.params.id));
  }, []);

  const userProfileEntity = useAppSelector(state => state.userProfile.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="userProfileDetailsHeading">
          <Translate contentKey="catinyApp.userProfile.detail.title">UserProfile</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{userProfileEntity.id}</dd>
          <dt>
            <span id="uuid">
              <Translate contentKey="catinyApp.userProfile.uuid">Uuid</Translate>
            </span>
            <UncontrolledTooltip target="uuid">
              <Translate contentKey="catinyApp.userProfile.help.uuid" />
            </UncontrolledTooltip>
          </dt>
          <dd>{userProfileEntity.uuid}</dd>
          <dt>
            <span id="work">
              <Translate contentKey="catinyApp.userProfile.work">Work</Translate>
            </span>
            <UncontrolledTooltip target="work">
              <Translate contentKey="catinyApp.userProfile.help.work" />
            </UncontrolledTooltip>
          </dt>
          <dd>{userProfileEntity.work}</dd>
          <dt>
            <span id="education">
              <Translate contentKey="catinyApp.userProfile.education">Education</Translate>
            </span>
            <UncontrolledTooltip target="education">
              <Translate contentKey="catinyApp.userProfile.help.education" />
            </UncontrolledTooltip>
          </dt>
          <dd>{userProfileEntity.education}</dd>
          <dt>
            <span id="placesLived">
              <Translate contentKey="catinyApp.userProfile.placesLived">Places Lived</Translate>
            </span>
            <UncontrolledTooltip target="placesLived">
              <Translate contentKey="catinyApp.userProfile.help.placesLived" />
            </UncontrolledTooltip>
          </dt>
          <dd>{userProfileEntity.placesLived}</dd>
          <dt>
            <span id="contactInfo">
              <Translate contentKey="catinyApp.userProfile.contactInfo">Contact Info</Translate>
            </span>
            <UncontrolledTooltip target="contactInfo">
              <Translate contentKey="catinyApp.userProfile.help.contactInfo" />
            </UncontrolledTooltip>
          </dt>
          <dd>{userProfileEntity.contactInfo}</dd>
          <dt>
            <span id="webSocialLinks">
              <Translate contentKey="catinyApp.userProfile.webSocialLinks">Web Social Links</Translate>
            </span>
            <UncontrolledTooltip target="webSocialLinks">
              <Translate contentKey="catinyApp.userProfile.help.webSocialLinks" />
            </UncontrolledTooltip>
          </dt>
          <dd>{userProfileEntity.webSocialLinks}</dd>
          <dt>
            <span id="basicInfo">
              <Translate contentKey="catinyApp.userProfile.basicInfo">Basic Info</Translate>
            </span>
            <UncontrolledTooltip target="basicInfo">
              <Translate contentKey="catinyApp.userProfile.help.basicInfo" />
            </UncontrolledTooltip>
          </dt>
          <dd>{userProfileEntity.basicInfo}</dd>
          <dt>
            <span id="relationshipInfo">
              <Translate contentKey="catinyApp.userProfile.relationshipInfo">Relationship Info</Translate>
            </span>
            <UncontrolledTooltip target="relationshipInfo">
              <Translate contentKey="catinyApp.userProfile.help.relationshipInfo" />
            </UncontrolledTooltip>
          </dt>
          <dd>{userProfileEntity.relationshipInfo}</dd>
          <dt>
            <span id="family">
              <Translate contentKey="catinyApp.userProfile.family">Family</Translate>
            </span>
            <UncontrolledTooltip target="family">
              <Translate contentKey="catinyApp.userProfile.help.family" />
            </UncontrolledTooltip>
          </dt>
          <dd>{userProfileEntity.family}</dd>
          <dt>
            <span id="detailAbout">
              <Translate contentKey="catinyApp.userProfile.detailAbout">Detail About</Translate>
            </span>
            <UncontrolledTooltip target="detailAbout">
              <Translate contentKey="catinyApp.userProfile.help.detailAbout" />
            </UncontrolledTooltip>
          </dt>
          <dd>{userProfileEntity.detailAbout}</dd>
          <dt>
            <span id="lifeEvents">
              <Translate contentKey="catinyApp.userProfile.lifeEvents">Life Events</Translate>
            </span>
            <UncontrolledTooltip target="lifeEvents">
              <Translate contentKey="catinyApp.userProfile.help.lifeEvents" />
            </UncontrolledTooltip>
          </dt>
          <dd>{userProfileEntity.lifeEvents}</dd>
          <dt>
            <span id="hobbies">
              <Translate contentKey="catinyApp.userProfile.hobbies">Hobbies</Translate>
            </span>
            <UncontrolledTooltip target="hobbies">
              <Translate contentKey="catinyApp.userProfile.help.hobbies" />
            </UncontrolledTooltip>
          </dt>
          <dd>{userProfileEntity.hobbies}</dd>
          <dt>
            <span id="featured">
              <Translate contentKey="catinyApp.userProfile.featured">Featured</Translate>
            </span>
            <UncontrolledTooltip target="featured">
              <Translate contentKey="catinyApp.userProfile.help.featured" />
            </UncontrolledTooltip>
          </dt>
          <dd>{userProfileEntity.featured}</dd>
          <dt>
            <Translate contentKey="catinyApp.userProfile.baseInfo">Base Info</Translate>
          </dt>
          <dd>{userProfileEntity.baseInfo ? userProfileEntity.baseInfo.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/user-profile" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/user-profile/${userProfileEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default UserProfileDetail;
