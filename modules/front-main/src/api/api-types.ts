export interface AddTodoItem$Request {
    name: string;
    sessionCookie: string;
}

export interface AddTodoItem$Response {
}

export interface ClearTodoList$Request {
}

export interface ClearTodoList$Response {
}

export interface DeleteTodoItem$Request {
    itemID: number;
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

export interface SetTodoItemChecked$Request {
    checked: boolean;
    itemId: number;
}

export interface SetTodoItemChecked$Response {
}

