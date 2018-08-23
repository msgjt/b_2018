import { Component, OnInit } from '@angular/core';
import {BugsService} from '../shared/bugs.service';
import {Bug} from '../../shared/Bug';
import {SearchCriteria} from '../../shared/SearchCriteria';

@Component({
  selector: 'app-show-bugs',
  templateUrl: './show-bugs.component.html',
  styleUrls: ['./show-bugs.component.css']
})
export class ShowBugsComponent implements OnInit {

  public data: Bug[];
  public filterQuery = '';
  public rowsOnPage = 10;
  public sortBy = 'email';
  public sortOrder = 'asc';

  constructor(private bugsService: BugsService) {
  }

  ngOnInit(): void {
    this.getBugs();
  }

  public toInt(num: string) {
    return +num;
  }

  public sortByWordLength = (a: any) => {
    return a.city.length;
  }

  getBugs(): void {
    this.bugsService.getAllBugs()
      .subscribe(bugs => this.data = bugs);
  }

  onNext() {
    console.log('onNext');
  }

  onPrev() {
    console.log('onPrev');
  }

  doQuery() {
    console.log('doQuery');
    const crit: SearchCriteria = {title: 'alma', fixedVersion: '0.11', status: 'CLOSED'};
    this.bugsService.getBugs(crit);
  }
}
