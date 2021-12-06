import './Header.css'
import React from "react";

export class Header extends React.Component {
  render() {
    return (
        <header>
          <nav>
            <a href="/">Home</a>
            <a href="/todolist">Todolist</a>
          </nav>
        </header>);
  }
};