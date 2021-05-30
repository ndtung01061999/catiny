import './home.scss';

import React, {useEffect, useRef, useState} from 'react';
import {connect} from 'react-redux';
import {Card, CardBody, CardHeader, CardText, CardTitle, Col, Progress, Row, Tooltip} from 'reactstrap';
import {faAngleRight, faEllipsisH} from '@fortawesome/free-solid-svg-icons';
import {FontAwesomeIcon} from '@fortawesome/react-fontawesome';

export type IHomeProp = StateProps;
let prevScrollProp = window.pageYOffset;
export const Home = (props: IHomeProp) =>
{
  const {account} = props;

  // return (
  //   <Row>
  //     <Col md="3" className="pad">
  //       <span className="hipster rounded" />
  //     </Col>
  //     <Col md="9">
  //       <h2>
  //         <Translate contentKey="home.title">Welcome, Java Hipster!</Translate>
  //       </h2>
  //       <p className="lead">
  //         <Translate contentKey="home.subtitle">This is your homepage</Translate>
  //       </p>
  //       {account && account.login ? (
  //         <div>
  //           <Alert color="success">
  //             <Translate contentKey="home.logged.message" interpolate={{ username: account.login }}>
  //               You are logged in as user {account.login}.
  //             </Translate>
  //           </Alert>
  //         </div>
  //       ) : (
  //         <div>
  //           <Alert color="warning">
  //             <Translate contentKey="global.messages.info.authenticated.prefix">If you want to </Translate>
  //
  //             <Link to="/login" className="alert-link">
  //               <Translate contentKey="global.messages.info.authenticated.link"> sign in</Translate>
  //             </Link>
  //             <Translate contentKey="global.messages.info.authenticated.suffix">
  //               , you can try the default accounts:
  //               <br />- Administrator (login=&quot;admin&quot; and password=&quot;admin&quot;)
  //               <br />- User (login=&quot;user&quot; and password=&quot;user&quot;).
  //             </Translate>
  //           </Alert>
  //
  //           <Alert color="warning">
  //             <Translate contentKey="global.messages.info.register.noaccount">You do not have an account yet?</Translate>&nbsp;
  //             <Link to="/account/register" className="alert-link">
  //               <Translate contentKey="global.messages.info.register.link">Register a new account</Translate>
  //             </Link>
  //           </Alert>
  //         </div>
  //       )}
  //       <p>
  //         <Translate contentKey="home.question">If you have any question on JHipster:</Translate>
  //       </p>
  //
  //       <ul>
  //         <li>
  //           <a href="https://www.jhipster.tech/" target="_blank" rel="noopener noreferrer">
  //             <Translate contentKey="home.link.homepage">JHipster homepage</Translate>
  //           </a>
  //         </li>
  //         <li>
  //           <a href="http://stackoverflow.com/tags/jhipster/info" target="_blank" rel="noopener noreferrer">
  //             <Translate contentKey="home.link.stackoverflow">JHipster on Stack Overflow</Translate>
  //           </a>
  //         </li>
  //         <li>
  //           <a href="https://github.com/jhipster/generator-jhipster/issues?state=open" target="_blank" rel="noopener noreferrer">
  //             <Translate contentKey="home.link.bugtracker">JHipster bug tracker</Translate>
  //           </a>
  //         </li>
  //         <li>
  //           <a href="https://gitter.im/jhipster/generator-jhipster" target="_blank" rel="noopener noreferrer">
  //             <Translate contentKey="home.link.chat">JHipster public chat room</Translate>
  //           </a>
  //         </li>
  //         <li>
  //           <a href="https://twitter.com/jhipster" target="_blank" rel="noopener noreferrer">
  //             <Translate contentKey="home.link.follow">follow @jhipster on Twitter</Translate>
  //           </a>
  //         </li>
  //       </ul>
  //
  //       <p>
  //         <Translate contentKey="home.like">If you like JHipster, do not forget to give us a star on</Translate>{' '}
  //         <a href="https://github.com/jhipster/generator-jhipster" target="_blank" rel="noopener noreferrer">
  //           GitHub
  //         </a>
  //         !
  //       </p>
  //     </Col>
  //   </Row>
  // );
  const homeRightRef = useRef(null);
  useEffect(() => {
    window.onscroll = () => {
      const currentScrollProp = window.pageYOffset;
      log('c' + currentScrollProp);
      log('p' + prevScrollProp);
      if (currentScrollProp > prevScrollProp) {
        // homeRightRef.current.style.visibility  = "hidden";

        homeRightRef.current.style.height = '0px';
      } else {
        // homeRightRef.current.style.visibility = "visible";
        homeRightRef.current.style.height = '150px';
      }
      // homeRightRef.current.style.display  = "none";
      prevScrollProp = currentScrollProp;
    };
  }, []);

  const log = x => window.console.log(x);

  const [tooltipOpen, setTooltipOpen] = useState(false);

  const toggle = () => setTooltipOpen(!tooltipOpen);

  return (
    <div>
      <Row className="home">
        <Col className="home-left col-12 col-sm-7 col-lg-8  pr-0 overflow-hidden">
          <Card>
            <CardHeader className="p-1 d-inline-flex">
              <img className="card-image-user " src="https://www.publicdomainpictures.net/pictures/320000/velka/background-image.png" />
              <div className="ml-2 w-100 ">
                <CardTitle className="mb-0 mt-1 text-nowrap ">
                  <h6 id="TooltipExample">
                    <span>Con nhếch</span>
                    <FontAwesomeIcon icon={faAngleRight} />
                    <span>chạy loăng quằng kiểu gì con lơn béo lú</span>
                  </h6>
                  <Tooltip placement="top-start" isOpen={tooltipOpen} target="TooltipExample" toggle={toggle}>
                    <p className="text-primary">Hello world!</p>
                  </Tooltip>
                </CardTitle>

                <CardText>dsa</CardText>
              </div>
              <div className="justify-content-end">
                <FontAwesomeIcon icon={faEllipsisH} />
              </div>
            </CardHeader>
            <CardText className="p-2 text-nowrap">
              <h5 className="mb-0 text-dark">ấy chà chà con nhếch đi chơi về trễ quá</h5>
            </CardText>
            <img className="card-img-top px-1" src="https://www.publicdomainpictures.net/pictures/320000/velka/background-image.png"></img>
            <CardBody className="p-2">
              <CardText>
                <div>
                  Lorem ipsum dolor sit amet, consectetur adipisicing elit. Accusantium aliquid doloribus eius eveniet fugiat id itaque odio
                  possimus ratione velit? Aliquid autem culpa dolor, eius officiis repellat saepe vitae voluptate?
                </div>
              </CardText>
            </CardBody>
          </Card>
        </Col>
        <Col className="col home-right col pl-3 pl-sm-2 pt-sm-2 pr-3">
          <div ref={homeRightRef} style={{ height: '150px', transition: 'height 2s', display: 'block' }}>
            <p className="text-primary">Your-Rank: 19/30</p>
            <p>Team-Rank: 1261/3215</p>
            <Progress multi>
              <Progress bar value="5">
                Meh
              </Progress>
              <Progress bar color="success" value="30">
                Wow!
              </Progress>
              <Progress bar color="info" value="25">
                Cool
              </Progress>
              <Progress bar color="warning" value="20">
                20%
              </Progress>
              <Progress bar color="danger" value="100">
                !!
              </Progress>
            </Progress>
          </div>
          <div style={{ backgroundColor: '#e8ded9', position: 'relative' }}>
            <h6 className="text-warning">bãy là gì?</h6>
            <li>là thứ mà tôi răng ra để dụ ông và ...</li>
            <li>ông sập tôi được lợi</li>
          </div>
        </Col>
      </Row>
    </div>
  );
};

const mapStateToProps = storeState => ({
  account: storeState.authentication.account,
  isAuthenticated: storeState.authentication.isAuthenticated,
});

type StateProps = ReturnType<typeof mapStateToProps>;

export default connect(mapStateToProps)(Home);
