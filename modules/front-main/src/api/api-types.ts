export interface AddTodoItem$Request {
    name: string;
}

export interface AddTodoItem$Response {
}

export interface CheckTodoItem$Request {
}

export interface CheckTodoItem$Response {
}

export interface ClearTodoList$Request {
}

export interface ClearTodoList$Response {
}

export interface DeleteTodoItem$Request {
}

export interface DeleteTodoItem$Response {
}

export interface GetTodoList$Request {
}

export interface GetTodoList$Response {
    items: GetTodoList$Response$Item[];
}

export interface GetTodoList$Response$Item {
    checked: boolean;
    id: number;
    name: string;
}

export interface UncheckTodoItem$Request {
}

export interface UncheckTodoItem$Response {
}

