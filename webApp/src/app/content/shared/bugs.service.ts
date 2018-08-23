import { Injectable } from '@angular/core';
import {Observable, of} from 'rxjs';
import {Bug} from '../../shared/Bug';
import {bugs} from '../../shared/mock-bug';
import {Role} from '../../shared/role';
import {HttpClient} from '@angular/common/http';
import {User} from '../../shared/user';
import {SearchCriteria} from '../../shared/SearchCriteria';

@Injectable({
  providedIn: 'root'
})
export class BugsService {

  private baseUrl = 'http://localhost:8080/jbugs/api';

  private bugsCache: Bug[];

  constructor(private httpClient: HttpClient) { }

  getAllBugs(): Observable<Bug[]> {
    return of(bugs);
  }

  getBugs(criteria: any)/*: Observable<Array<Bug>> */{
    const url = `${this.baseUrl}/bugs`;
    const parameters = JSON.stringify({criteria});
    this.httpClient.get(url, {
      params: {criteria},
      observe: 'response'
    })
      .toPromise()
      .then(response => {
        console.log(response);
      })
      .catch(console.log);
  }

}
