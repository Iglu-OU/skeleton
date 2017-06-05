export interface GetTodoList$Request {
}

export interface GetTodoList$Response {
    lists: TodoListRow[];
}

export interface TodoListRow {
    id: number;
    name: string;
}

