/*!

=========================================================
* BLK Design System React - v1.1.0
=========================================================

* Product Page: https://www.creative-tim.com/product/blk-design-system-react
* Copyright 2020 Creative Tim (https://www.creative-tim.com)
* Licensed under MIT (https://github.com/creativetimofficial/blk-design-system-react/blob/master/LICENSE.md)

* Coded by Creative Tim

=========================================================

* The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

*/
import React from "react";
import classnames from "classnames";
// javascript plugin used to create scrollbars on windows
import PerfectScrollbar from "perfect-scrollbar";
import CandidateCard from "./JobCard/CandidateCard";
import {
    Button,
    Form,
    FormGroup,
    Input,
    InputGroup,
    InputGroupAddon,
    InputGroupText,
    Label,
    Modal,
} from "reactstrap";
// reactstrap components


const carouselItems = [
  {
    src: require("assets/img/denys.jpg"),
    altText: "Slide 1",
    caption: "Big City Life, United States"
  },
  {
    src: require("assets/img/fabien-bazanegue.jpg"),
    altText: "Slide 2",
    caption: "Somewhere Beyond, United States"
  },
  {
    src: require("assets/img/mark-finn.jpg"),
    altText: "Slide 3",
    caption: "Stocks, United States"
  }
];

let ps = null;

class Candidates extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      tabs: 1,
      formModal: false
    };
  }
  toggleModal = modalState => {
      this.setState({
          [modalState]: !this.state[modalState]
      });
  };
  componentDidMount() {
    if (navigator.platform.indexOf("Win") > -1) {
      document.documentElement.className += " perfect-scrollbar-on";
      document.documentElement.classList.remove("perfect-scrollbar-off");
      let tables = document.querySelectorAll(".table-responsive");
      for (let i = 0; i < tables.length; i++) {
        ps = new PerfectScrollbar(tables[i]);
      }
    }
    document.body.classList.toggle("profile-page");
  }
  componentWillUnmount() {
    if (navigator.platform.indexOf("Win") > -1) {
      ps.destroy();
      document.documentElement.className += " perfect-scrollbar-off";
      document.documentElement.classList.remove("perfect-scrollbar-on");
    }
    document.body.classList.toggle("profile-page");
  }
  toggleTabs = (e, stateName, index) => {
    e.preventDefault();
    this.setState({
      [stateName]: index
    });
  };
  render() {
    return (
      <>
        {/*<div className="wrapper">*/}
          {/*<div className="page-header">*/}
            <img
              alt="..."
              className="dots"
              src={require("assets/img/dots.png")}
            />
            <img
              alt="..."
              className="path"
              src={require("assets/img/path4.png")}
            />

        <div className="padding">
                <Button
                    color="success"
                    onClick={() => this.toggleModal("formModal")}
                >
                    Launch Modal Form
                </Button>
            <CandidateCard/>
            <CandidateCard/>
            <CandidateCard/>
        </div>



          <Modal
              modalClassName="modal-black"
              isOpen={this.state.formModal}
              toggle={() => this.toggleModal("formModal")}
          >
              <div className="modal-header justify-content-center">
                  <button
                      className="close"
                      onClick={() => this.toggleModal("formModal")}
                  >
                      <i className="tim-icons icon-simple-remove text-white" />
                  </button>
                  <div className="text-muted text-center ml-auto mr-auto">
                      <h3 className="mb-0">Sign in with</h3>
                  </div>
              </div>
              <div className="modal-body">
                  <div className="btn-wrapper text-center">
                      <Button
                          className="btn-neutral btn-icon"
                          color="default"
                          href="#pablo"
                          onClick={e => e.preventDefault()}
                      >
                          <img alt="..." src={require("assets/img/github.svg")} />
                      </Button>
                      <Button
                          className="btn-neutral btn-icon"
                          color="default"
                          href="#pablo"
                          onClick={e => e.preventDefault()}
                      >
                          <img alt="..." src={require("assets/img/google.svg")} />
                      </Button>
                  </div>
                  <div className="text-center text-muted mb-4 mt-3">
                      <small>Or sign in with credentials</small>
                  </div>
                  <Form role="form">
                      <FormGroup className="mb-3">
                          <InputGroup
                              className={classnames("input-group-alternative", {
                                  "input-group-focus": this.state.emailFocus
                              })}
                          >
                              <InputGroupAddon addonType="prepend">
                                  <InputGroupText>
                                      <i className="tim-icons icon-email-85" />
                                  </InputGroupText>
                              </InputGroupAddon>
                              <Input
                                  placeholder="Email"
                                  type="email"
                                  onFocus={e => this.setState({ emailFocus: true })}
                                  onBlur={e => this.setState({ emailFocus: false })}
                              />
                          </InputGroup>
                      </FormGroup>
                      <FormGroup>
                          <InputGroup
                              className={classnames("input-group-alternative", {
                                  "input-group-focus": this.state.passwordFocus
                              })}
                          >
                              <InputGroupAddon addonType="prepend">
                                  <InputGroupText>
                                      <i className="tim-icons icon-key-25" />
                                  </InputGroupText>
                              </InputGroupAddon>
                              <Input
                                  placeholder="Password"
                                  type="password"
                                  onFocus={e => this.setState({ passwordFocus: true })}
                                  onBlur={e => this.setState({ passwordFocus: false })}
                              />
                          </InputGroup>
                      </FormGroup>
                      <FormGroup check className="mt-3">
                          <Label check>
                              <Input defaultChecked type="checkbox" />
                              <span className="form-check-sign" />
                              Remember me!
                          </Label>
                      </FormGroup>
                      <div className="text-center">
                          <Button className="my-4" color="primary" type="button">
                              Sign in
                          </Button>
                      </div>
                  </Form>
              </div>
          </Modal>

      </>
    );
  }
}

export default Candidates;
