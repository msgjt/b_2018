import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Token} from '../../shared/token';

@Injectable(
)
export class LoginService {

  private baseUrl = 'http://localhost:8080/jbugs/api';

  constructor(private httpClient: HttpClient) {
  }

  login(username: string, password: string): Observable<Token> {
    const url = `${this.baseUrl}/users`;
    const body = {username, password};
    return this.httpClient
      .post<Token>(url, body);
  }


}
