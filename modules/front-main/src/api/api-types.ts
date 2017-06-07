export interface AddTodoItem$Request {
    name: string;
}

export interface AddTodoItem$Response {
}

export interface GetTodoList$Request {
}

export interface GetTodoList$Response {
    lists: TodoListRow[];
}

export interface TodoListRow {
    id: number;
    name: string;
}

