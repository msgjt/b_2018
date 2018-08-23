import { Injectable } from '@angular/core';
import {Observable, of} from 'rxjs';
import {Bug} from '../../shared/Bug';
import {Role} from '../../shared/role';
import {HttpClient} from '@angular/common/http';
import {User} from '../../shared/user';
import {SearchCriteria} from '../../shared/SearchCriteria';

@Injectable({
  providedIn: 'root'
})
export class BugsService {

  private baseUrl = 'http://localhost:8080/jbugs/api/bugs';


  private offset: number;

  constructor(private httpClient: HttpClient) {
  }

  /**
   * Get 25 bugs
   * @param criteria
   */
  getBugs(): Observable<Array<Bug>> {
    const url = `${this.baseUrl}/query`;
    return this.httpClient.get<Array<Bug>>(url, {params: {'count': '25', 'offset': '0', 'order': 'ASC'}});
  }

  /**
   * get the next 25 bugs
   * @returns {Observable<Array<Bug>>}
   */
  getNext(): Observable<Array<Bug>> {
    this.offset += 25;
    const url = `${this.baseUrl}/query`;
    return this.httpClient.get<Array<Bug>>(url, {params: {'count': '25', 'offset': String(this.offset), 'order': 'ASC'}})
  }

  /**
   * get the previous 25
   * @returns {Observable<Array<Bug>>}
   */
  getPrev(): Observable<Array<Bug>> {
    if (this.offset !== 0) {
      this.offset -= 25;
    }
    const url = `${this.baseUrl}/query`;
    return this.httpClient.get<Array<Bug>>(url, {params: {'count': '25', 'offset': String(this.offset), 'order': 'ASC'}});
  }
}
