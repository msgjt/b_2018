import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {User} from '../../shared/user';
import {Role} from '../../shared/role';
import {Permission} from '../../shared/permission';

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

  getAllPermissions(): Observable<Array<Permission>> {
    const url = `${this.baseUrl}/permissions`;
    return this.httpClient
      .get<Array<Permission>>(url);
  }
}
