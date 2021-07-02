import './footer.scss';

import React from 'react';
import {Translate} from 'react-jhipster';
import {Col} from 'reactstrap';

const Footer = props => (
  <div className="footer page-content">
    <Col md="12">
      <p>
        <Translate contentKey="footer">Your footer</Translate>
      </p>
    </Col>
  </div>
);

export default Footer;
