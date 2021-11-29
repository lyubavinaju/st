import './App.css';
import {
  BrowserRouter,
  Routes,
  Route
} from "react-router-dom";
import {Home} from "./components/home/Home";
import {Header} from "./components/header/Header";
import {Todolist} from "./components/todolist/Todolist";
import React from "react";

export class App extends React.Component {
  render() {
    return (
        <BrowserRouter>
          <div className="App">
            <Header/>
            <Routes>
              <Route path="/" element={<Home text="Home page"/>}/>
              <Route path="/todolist"
                     element={<Todolist/>}/>
            </Routes>
          </div>
        </BrowserRouter>

    );
  }
}

