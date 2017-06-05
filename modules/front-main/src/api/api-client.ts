import {autoinject} from "aurelia-framework";
import {HttpClient, json} from "aurelia-fetch-client";

@autoinject
export class ApiClient {

  constructor(private http: HttpClient) {
  }

  public execute(method: string, request: any = {}): Promise<any> {
    const init: RequestInit = {
      method: 'post',
      body: json(request),
      headers: {},
    };

    return this.http.fetch("/api/rpc/" + method, init)
      .then((httpResponse: Response) => {
        return httpResponse.json();
      }).then(result => {
        if (result.ok) {
          return result.response;
        }
      });
  }
}
