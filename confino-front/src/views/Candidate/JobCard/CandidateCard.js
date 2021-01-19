import {
    Button,
    Card,
    CardBody,
    CardFooter,
    CardHeader,
    CardImg,
    CardTitle,
    Col, Container,
    Form,
    FormGroup,
    Input,
    InputGroup,
    InputGroupAddon,
    InputGroupText, Label,
    Row,
} from "reactstrap";
import "assets/css/job.scss";
import React from "react";
import classnames from "classnames";
import {Link} from "react-router-dom";

class CandidateCard extends React.Component {
    state = {};
    render() {
        return (
            <div className="section section-signup">
                <Container>
                    <Row className="row-grid justify-content-between align-items-center">
                        <Col className="mb-lg-auto" lg="12">
                            <Card className="card-register">
                                <CardBody>
                                    <Row>
                                        <Col sm="2" xs="6">
                                            <img
                                                alt="..."
                                                className="img-fluid rounded-circle shadow"
                                                src={require("assets/img/james.jpg")}
                                                style={{ width: "80px" }}
                                            />
                                        </Col>
                                        <Col sm="2" xs="6">
                                         <small className="text-uppercase">   <strong>Mohamed DIOP</strong> </small><br/>
                                            <small className="text-muted">
                                                <div>Developpeur Web at DDB</div>
                                                En poste
                                            </small>
                                            <small className="d-block font-weight-bold">
                                                (**** rating ****)
                                            </small>

                                        </Col>
                                        <Col className="mt-5 mt-sm-0" sm="2" xs="6">
                                            <div>
                                                <small className="text-muted">
                                                    <strong>1</strong> poste(s) à pourvoir
                                                </small>
                                            </div>
                                            <div>
                                                <small className="text-muted">
                                                    localisation: <strong>Dakar</strong> (6400/54000)
                                                </small>
                                            </div>
                                            <small className="text-muted">
                                                <strong>CDD</strong>
                                            </small>
                                        </Col>
                                        <Col className="mt-5 mt-sm-0" sm="2" xs="6">
                                            <div className="container p-5">
                                                <div className="progress" data-percentage="20">
                                                    <span className="progress-left">
                                                        <span className="progress-bar"></span>
                                                    </span>
                                                    <span className="progress-right">
                                                        <span className="progress-bar"></span>
                                                    </span>
                                                    <div className="progress-value text-white">
                                                        <div>
                                                            30%
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </Col>
                                        <Col sm="2" xs="6">
                                            <small className="text-uppercase">   <strong>Mohamed DIOP</strong> </small><br/>
                                            <small className="text-muted">
                                                <div>Developpeur Web at DDB</div>
                                                En poste
                                            </small>
                                            <small className="d-block font-weight-bold">
                                                (**** rating ****)
                                            </small>

                                        </Col>
                                        <Col className="mt-5 mt-sm-0 text-muted" sm="2" xs="6">
                                            <small>Publié depuis</small>
                                            <br/>
                                            <strong>7 mois</strong>
                                            <br/>
                                            <small>le 24/11/2020</small>

                                        </Col>
                                    </Row>

                                </CardBody>
                                {/*<CardFooter>*/}
                                    {/*<Button className="btn-round" color="primary" size="lg">*/}
                                        {/*Details*/}
                                    {/*</Button>*/}
                                {/*</CardFooter>*/}
                            </Card>
                        </Col>
                    </Row>
                </Container>
            </div>
        );
    }
}


export default CandidateCard;