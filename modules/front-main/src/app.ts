import {autoinject} from "aurelia-framework";
import {ApiService} from "./api/api.service";

@autoinject
export class App {

  todoListPromise;
  private todoList;


  constructor(private api: ApiService) {
    this.todoListPromise = api.todo.getTodoList();
  }

  attached(){
    this.todoListPromise.then(todoList => this.todoList = todoList)
  }

  message = 'Hello World!';
}
