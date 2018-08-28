import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {User} from '../../shared/user';
import {Role} from '../../shared/role';
import {Bug} from '../../shared/bug';
import {Permission} from '../../shared/permission';
import {BugStatusType} from '../../shared/bugstatustype';
import {BugSeverityType} from '../../shared/bugseveritytype';

@Injectable()
export class ContentService {

  private baseUrl = 'http://localhost:8080/jbugs/api';

  constructor(private httpClient: HttpClient) {
  }

  addUser(firstName: string, lastName: string, email: string, password: string,
          mobileNumber: string, roles: Array<Role>): Observable<User> {
    const url = `${this.baseUrl}/users/add`;
    const body = {firstName, lastName, email, password, mobileNumber, roles};
    return this.httpClient
      .post<User>(url, body);
  }

  updateUser(id: number, firstName: string, lastName: string, email: string,
             password: string, mobileNumber: string, roles: Array<Role>): Observable<User> {
    const url = `${this.baseUrl}/users/update`;
    const body = {id, firstName, lastName, email, password, mobileNumber, roles};
    return this.httpClient
      .put<User>(url, body);
  }

  updateRole(id: number, roleType: string, permissions: Array<Permission>): Observable<Role> {
    const url = `${this.baseUrl}/roles/update`;
    const body = {id, roleType, permissions};
    return this.httpClient
      .put<Role>(url, body);

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

  getUserRolesById(id: number): Observable<Array<Role>> {
    const url = `${this.baseUrl}/roles/${id}`;
    return this.httpClient
      .get<Array<Role>>(url);
  }

  getRolePermissionsById(id: number): Observable<Array<Permission>> {
    const url = `${this.baseUrl}/permissions/${id}`;
    return this.httpClient
      .get<Array<Permission>>(url);

  }

  getAllPermissions(): Observable<Array<Permission>> {
    const url = `${this.baseUrl}/permissions`;
    return this.httpClient
      .get<Array<Permission>>(url);
  }

  updateBug(id: number, title: string, description: string, version: string,
            fixedInVersion: string, dueDate: string, bugStatusType: string,
            creator: User, assignee: User, severityType: string): Observable<Bug> {
    const url = `${this.baseUrl}/bugs/update`;
    const body = {id, title, description, version, fixedInVersion, dueDate, bugStatusType, creator, assignee, severityType};
    console.log('status: ' + bugStatusType);
    console.log('severity: ' + severityType);
    console.log('body: ' + body);
    return this.httpClient
      .put<Bug>(url, body);
  }
}
