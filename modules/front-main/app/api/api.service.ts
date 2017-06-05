// NB! this file should be generated using `gradlew generateApi`

import {Injectable} from "@angular/core";
import {ApiClient} from "./api-client";
import {Observable} from "rxjs";

import {
  GetTodoList$Request, GetTodoList$Response,
} from "./api-types";

@Injectable()
export class ApiService {
    public todo: TodoOperations;

    constructor(private api: ApiClient) {
        this.todo = new TodoOperations(this.api);
    }

}

export class TodoOperations {
  constructor(private api: ApiClient) {}

  getTodoList(): Observable<GetTodoList$Response> {
    return this.api.execute("getTodoList", {});
  }

}
