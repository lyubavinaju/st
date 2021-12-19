import './Home.css';
import React from "react";

export class Home extends React.Component {
  render() {
    return (
        <div className="home">
          {this.props.text}
        </div>
    );
  }
};