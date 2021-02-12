import { Injectable } from '@angular/core';
import { ApiResponse } from './apiresponse';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { User } from './user';
import { Loginapiresponse } from './loginapiresponse';

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  constructor(private httpClient: HttpClient) { }
  // tslint:disable-next-line:no-inferrable-types
  baseUrl: string = 'http://localhost:8080/';

  login(loginPayload): Observable<Loginapiresponse> {
    // tslint:disable-next-line:no-debugger
    debugger;
    return this.httpClient.post<Loginapiresponse>('http://localhost:8080/' + 'authenticate', loginPayload);
  }

  getUsers(): Observable<ApiResponse> {
    return this.httpClient.get<ApiResponse>(this.baseUrl);
  }

  getUserById(id: number): Observable<ApiResponse> {
    return this.httpClient.get<ApiResponse>(this.baseUrl + 'details/' + id);
  }

  createUser(user: User): Observable<ApiResponse> {
    // tslint:disable-next-line:no-debugger
    debugger;
    return this.httpClient.post<ApiResponse>(this.baseUrl + 'create', user);
  }

  updateUser(user: User): Observable<ApiResponse> {
    return this.httpClient.put<ApiResponse>(this.baseUrl + 'update', user);
  }

  deleteUser(id: number): Observable<ApiResponse> {
     // tslint:disable-next-line:no-debugger
     debugger;
     return this.httpClient.delete<ApiResponse>(this.baseUrl + 'delete/' + id);
  }
}
