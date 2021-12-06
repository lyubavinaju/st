import React from "react";

export class TodoItem extends React.Component {
  render() {
    return (
        <div className="todoItem">{"Todo name: " + this.props.name
        + ", is done: "
        + this.props.done}</div>
    );
  }
}