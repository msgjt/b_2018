import {Injectable} from '@angular/core';
import {Observable, of} from 'rxjs';
import {Bug} from '../../shared/Bug';
import {Role} from '../../shared/role';
import {HttpClient, HttpParams} from '@angular/common/http';
import {User} from '../../shared/user';
import {Bugfilter} from '../../shared/bugfilter';

@Injectable({
  providedIn: 'root'
})
export class BugsService {

  private baseUrl = 'http://localhost:8080/jbugs/api/bugs';

  private offset: number;

  private searchParams: HttpParams;

  constructor(private httpClient: HttpClient) {
    this.searchParams = new HttpParams()
      .append('count', '25')
      .append('order', 'ASC')
      .append('offset', '0');
  }


  /**
   * Get 25 bugs
   * @param criteria
   */
  getBugs(): Observable<Array<Bug>> {
    const url = `${this.baseUrl}/query`;
    return this.httpClient.get<Array<Bug>>(url, {params: this.searchParams});
  }

  /**
   * get the next 25 bugs
   * @returns {Observable<Array<Bug>>}
   */
  getNext(): Observable<Array<Bug>> {
    this.offset += 25;
    const url = `${this.baseUrl}/query`;
    return this.httpClient.get<Array<Bug>>(url, {params: {'count': '25', 'offset': String(this.offset), 'order': 'ASC'}});
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

  getSorted(sortByArg: string, orderArg: string): Observable<Array<Bug>> {
    const url = `${this.baseUrl}/query`;
    this.searchParams = this.searchParams.set('sortBy', sortByArg);
    this.searchParams = this.searchParams.set('order', orderArg);
    return this.httpClient.get<Array<Bug>>(url,
      {params: {'count': '25', 'offset': String(this.offset), 'sortby': sortByArg, 'order': orderArg}});
  }

  getFilteredBugs(filter: Bugfilter): Observable<Array<Bug>> {
    const url = `${this.baseUrl}/query`;
    this.offset = 0;
    this.searchParams = new HttpParams();
    Object.keys(filter).forEach(key => {
        if (filter[key].length > 0) {
          this.searchParams = this.searchParams.set(key, filter[key]);
        }
      });
    console.log(this.searchParams.toString());
    return this.httpClient.get<Array<Bug>>(url, {params: this.searchParams});
  }
}
