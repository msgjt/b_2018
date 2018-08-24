import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {User} from '../../shared/user';
import {Role} from '../../shared/role';
import {Bug} from '../../shared/bug';

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

  addbug(title: string, description: string, version: string, fixedInVersion: string, severityType: string, bugStatusType: string,
         dueDate: string, assignee: User, creator: User) {
    const url = `${this.baseUrl}/bugs/add`;
    console.log(assignee);
    const body = {title, description, version, fixedInVersion, severityType, bugStatusType, dueDate, assignee, creator};
    console.log(body);
    return this.httpClient
      .post<Bug>(url, body);
  }
}
