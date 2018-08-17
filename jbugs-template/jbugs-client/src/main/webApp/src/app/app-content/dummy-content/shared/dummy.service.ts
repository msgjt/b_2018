import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {User} from "../../../shared/user";

@Injectable(
)
export class DummyService {

  private baseUrl = 'http://localhost:8080/jbugs/api';

  constructor(private httpClient: HttpClient) { }

  getAllUsers(): Observable<Array<User>> {
    const url = `${this.baseUrl}/users`;
    return this.httpClient
      .get<Array<User>>(url);
  }
}
