import React, {useState, useEffect} from "react";
import {TodoItem} from "./TodoItem";

export function Todolist() {
  const [todolist, setTodolist] = useState([]);
  const [name, setName] = useState("");
  useEffect(() => {
    fetch("/todolist/").then(res => {
      if (res.ok) {
        return res.json();
      }
    }).then(jsonRes => {
      setTodolist(jsonRes.todolist);
      setName(jsonRes.name);
    })
  })

  return (
      <div>
        <h1>{name}</h1>
        <div>
          {
            todolist.map(
                todoItem => <TodoItem name={todoItem.name}
                                      done={todoItem.done}/>)
          }
        </div>
      </div>

  );
}
