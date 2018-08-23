import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {User} from '../../shared/user';
import {Role} from '../../shared/role';

@Injectable()
export class ContentService {

  private baseUrl = 'http://localhost:8080/jbugs/api';

  constructor(private httpClient: HttpClient) {
  }

  addUser(firstName: string, lastName: string, email: string, password: string, mobileNumber: string): Observable<User> {
    const url = `${this.baseUrl}/users/add`;
    const body = {firstName, lastName, email, password, mobileNumber};
    return this.httpClient
      .post<User>(url, body);
  }

  getAllUsers(): Observable<Array<User>> {
    const url = `${this.baseUrl}/users`;
    return this.httpClient
      .get<Array<User>>(url);
  }

  getAllRoles(): Observable<Array<Role>> {
    const url = `${this.baseUrl}/roles`;
    return this.httpClient
      .get<Array<Role>>(url);
  }

  activateUser(user: User): Observable<boolean> {
    const url = `${this.baseUrl}/users/activate`;
    const body = user;
    return this.httpClient
      .post<boolean>(url, body);
  }

  deactivateUser(user: User): Observable<boolean> {
    const url = `${this.baseUrl}/users/deactivate`;
    const body = user;
    return this.httpClient
      .post<boolean>(url, body);
  }

  hasOpenBugs(user: User): Observable<boolean> {
    const url = `${this.baseUrl}/users/openBugs`;
    const body = user;
    return this.httpClient
      .post<boolean>(url, body);
  }
}
