// NB! this file should be generated using `gradlew generateApi`

import {autoinject} from "aurelia-framework";
import {ApiClient} from "./api-client";

import {
  AddTodoItem$Request, AddTodoItem$Response,
  GetTodoList$Request, GetTodoList$Response,
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
  getTodoList(): Promise<GetTodoList$Response> {
    return this.api.execute("getTodoList", {});
  }

}
