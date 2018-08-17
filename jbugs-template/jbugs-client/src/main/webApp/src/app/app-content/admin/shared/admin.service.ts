import {Injectable} from '@angular/core';
import {Token} from '../../../shared/token';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {User} from '../../../shared/user';

@Injectable()
export class AdminService {

  private baseUrl = 'http://localhost:8080/jbugs/api';

  constructor(private httpClient: HttpClient) {
  }

  addUser(firstName: string, lastName: string, email: string, password: string, mobileNumber: string): Observable<User> {
    const url = `${this.baseUrl}/users/add`;
    const body = {firstName, lastName, email, password, mobileNumber};
    return this.httpClient
      .post<User>(url, body);
  }
}
