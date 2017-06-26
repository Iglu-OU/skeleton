// NB! this file should be generated using `gradlew generateApi`

import {autoinject} from "aurelia-framework";
import {ApiClient} from "./api-client";

import {
  AddTodoItem$Request, AddTodoItem$Response,
  ClearTodoList$Request, ClearTodoList$Response,
  DeleteTodoItem$Request, DeleteTodoItem$Response,
  GetTodoList$Request, GetTodoList$Response,
  SetTodoItemChecked$Request, SetTodoItemChecked$Response,
} from "./api-types";

@autoinject
export class ApiService {
    public todo: TodoOperations;

    constructor(private api: ApiClient) {
        this.todo = new TodoOperations(this.api);
    }

}

export class TodoOperations {
  constructor(private api: ApiClient) {}

  addTodoItem(request: AddTodoItem$Request): Promise<AddTodoItem$Response> {
    return this.api.execute("addTodoItem", request);
  }
  clearTodoList(): Promise<ClearTodoList$Response> {
    return this.api.execute("clearTodoList", {});
  }
  deleteTodoItem(request: DeleteTodoItem$Request): Promise<DeleteTodoItem$Response> {
    return this.api.execute("deleteTodoItem", request);
  }
  getTodoList(): Promise<GetTodoList$Response> {
    return this.api.execute("getTodoList", {});
  }
  setTodoItemChecked(request: SetTodoItemChecked$Request): Promise<SetTodoItemChecked$Response> {
    return this.api.execute("setTodoItemChecked", request);
  }

}
