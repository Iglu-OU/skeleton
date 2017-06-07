import {autoinject} from "aurelia-framework";
import {ApiService} from "./api/api.service";

@autoinject
export class App {
  private todoList;
  itemName;

  constructor(private api: ApiService) {
  }

  attached() {
    return this.reload();
  }

  private reload() {
    return this.api.todo.getTodoList()
      .then(todoList => this.todoList = todoList)
  }

  message = 'Hello World!';

  submit() {
    this.api.todo.addTodoItem({
      name: this.itemName
    }).then(response => {
      this.reload();
    });
  }

  check(itemId, checked) {
    this.api.todo.setTodoItemChecked({
      itemId: itemId,
      checked: !checked,
    }).then(response => {
      this.reload();
    });
  }

  clear(){
    this.api.todo.clearTodoList().then(response => {
      this.reload();
    });
  }
  // clear(){
  //   alert("Clear")
  // }

}
