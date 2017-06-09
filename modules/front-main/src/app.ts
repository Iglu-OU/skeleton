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
    return this.api.todo.getTodoList({
      session:this.getCookie("SESSIONCUSTOM")
    })
      .then(todoList => this.todoList = todoList)
  }

  message = 'Hello World!';

  submit() {
    this.api.todo.addTodoItem({
      name: this.itemName,
      sessionCookie: this.getCookie("SESSIONCUSTOM")
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

  deleteItem(itemId){
    this.api.todo.deleteTodoItem({
      itemID: itemId
    }).then(response => {
      this.reload();
    });
  }

  clear(){
    this.api.todo.clearTodoList().then(response => {
      this.reload();
    });
  }

  getCookie(name: string): string {
    const nameLenPlus = (name.length + 1);
    alert(document.cookie);
    return document.cookie
        .split(';')
        .map(c => c.trim())
        .filter(cookie => {
          return cookie.substring(0, nameLenPlus) === `${name}=`;
        })
        .map(cookie => {
          return decodeURIComponent(cookie.substring(nameLenPlus));
        })[0] || null;
  }
}
