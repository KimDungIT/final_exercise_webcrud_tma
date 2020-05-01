import React, { Component } from "react";
import Breadcrumbs from "@material-ui/core/Breadcrumbs";
import Typography from "@material-ui/core/Typography";
import NavigateNextIcon from "@material-ui/icons/NavigateNext";
import AddCircleOutlineIcon from "@material-ui/icons/AddCircleOutline";
import DeleteIcon from "@material-ui/icons/Delete";
import ReplayIcon from "@material-ui/icons/Replay";
import IconButtonCommon from "./common/button/IconButton";
import "../common.css";
import DialogDevice from "./DialogDevice.js";
import DialogDelete from "./DialogDelete.js";
import Tooltip from "@material-ui/core/Tooltip";
import { Link } from "react-router-dom";

class PathDevices extends Component {
  constructor(props) {
    super(props);
    this.state = {
      openAdd: false,
      openDelete: false,
    };
  }

  onClickDeleteButton = () => {
    this.setState({ openDelete: true });
  };

  onClickReloadButton = () => {
    console.log("perform click reload buton");
    this.props.setNeedRefreshTabMenuState(true);
  };

  handleClickOpen = () => {
    this.setState({ openAdd: true });
  };

  handleClose = () => {
    this.setState({ openAdd: false });
  };

  handleCloseDelete = () => {
    this.setState({ openDelete: false });
  };

  pathIconButton = [
    {
      pathName: "Breadcrumb",
      iconButtons: [
        {
          id: 1,
          icon: <AddCircleOutlineIcon />,
          disable: false,
          onClick: this.handleClickOpen,
          title: "Create device",
        },
        {
          id: 2,
          icon: <DeleteIcon />,
          disable: false,
          onClick: this.onClickDeleteButton,
          title: "Delete all devices",
        },
        {
          id: 3,
          icon: <ReplayIcon />,
          disable: false,
          onClick: this.onClickReloadButton,
          title: "Refresh",
        },
      ],
    },
  ];

  pathList = [
    {
      name: "Device holders",
      color: "inherit",
      to: "/",
      onClick: this.handleClick,
      className: "link",
    },
    {
      name: this.props.param,
      color: "textPrimary",
      className: "link",
    },
  ];

  handleClick = (event) => {
    event.preventDefault();
    console.info("You clicked a breadcrumb.");
  };

  render() {
    const length = this.pathList.length;
    return (
      <div className="col-lg-12 col-md-12 col-sm-12">
        <div className="row">
          <div className="col-lg-8 col-md-8 col-sm-8 path">
            <Breadcrumbs
              separator={<NavigateNextIcon fontSize="small" />}
              aria-label="breadcrumb"
            >
               {this.pathList.map((element, index) =>
                length === index + 1 ? (
                  <Tooltip key={index} title={element.name}>
                    <Typography
                      color={element.color}
                      className={element.className}
                    >
                      {element.name}
                    </Typography>
                  </Tooltip>
                ) : (
                  <Tooltip key={index} title={element.name}>
                    <Link
                      color={element.color}
                      to={element.to}
                      onClick={element.onClick}
                      className={element.className}
                    >
                      {element.name}
                    </Link>
                  </Tooltip>
                )
              )}
            </Breadcrumbs>
            <h3 id="currentName">{this.props.param}</h3>
          </div>
          <div className="col-lg-4 col-md-4 col-sm-4 content-flex-end">
            {this.pathIconButton[0].iconButtons.map((iconButton, index) => (
              <IconButtonCommon
                key={index}
                icon={iconButton.icon}
                disable={iconButton.disable}
                onClick={iconButton.onClick}
                title={iconButton.title}
              />
            ))}
            <DialogDevice
              deviceHolder={this.props.param}
              openDialog={this.state.openAdd}
              onCloseDialog={this.handleClose}
              setNeedRefreshTabMenuState={this.props.setNeedRefreshTabMenuState}
            />

            <DialogDelete
              title="devices"
              param={this.props.param}
              history={this.props.history}
              deviceHolder={this.props.param}
              openDialog={this.state.openDelete}
              handleCloseDelete={this.handleCloseDelete}
              setNeedRefreshTabMenuState={this.props.setNeedRefreshTabMenuState}
            />

          </div>
        </div>
      </div>
    );
  }
}

export default PathDevices;
