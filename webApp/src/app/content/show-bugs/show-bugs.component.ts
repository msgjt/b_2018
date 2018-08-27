import { Component, OnInit } from '@angular/core';
import {BugsService} from '../shared/bugs.service';
import {Bug} from '../../shared/Bug';
import {Bugfilter} from '../../shared/bugfilter';

@Component({
  selector: 'app-show-bugs',
  templateUrl: './show-bugs.component.html',
  styleUrls: ['./show-bugs.component.css']
})

export class ShowBugsComponent implements OnInit {

  public data: Bug[];
  public filterQuery = '';
  public sortOrder = 'ASCENDING';
  public lastSorter = 'title';
  public disableNext = false;
  public currentFilter: Bugfilter;
  public previousFilter: Bugfilter;
  timerId: string;
  public severityTypes = ['', 'CRITICAL', 'HIGH', 'MEDIUM', 'LOW'];
  public statusTypes = ['', 'NEW', 'IN_PROGRESS', 'FIXED', 'CLOSED', 'REJECTED', 'INFO_NEEDED'];


  constructor(private bugsService: BugsService) {
  }

  ngOnInit(): void {
    this.getBugs();
    this.currentFilter = new Bugfilter();
    this.previousFilter = new Bugfilter();
  }

  public toInt(num: string) {
    return +num;
  }

  public sortByWordLength = (a: any) => {
    return a.city.length;
  }

  getBugs(): void {
    this.bugsService.getBugs().subscribe(bugs => this.data = bugs);
  }

  onNext() {
    console.log('onNext');
    this.bugsService.getNext().subscribe(bugs => {this.data = bugs; this.disableNext = this.data.length < 25; });

  }

  onPrev() {
    console.log('onPrev');
    this.bugsService.getPrev().subscribe(bugs => this.data = bugs);
  }

  doQuery() {
    console.log('doQuery');
  }

  sortTable(sortArg: string) {
    console.log(sortArg);

    if (sortArg == this.lastSorter) {
      this.sortOrder = (this.sortOrder == 'ASCENDING') ? 'DESCENDING' : 'ASCENDING';
    } else {
      this.lastSorter = sortArg;
      this.sortOrder = 'ASCENDING';
    }
    this.bugsService.getSorted(this.lastSorter, this.sortOrder).subscribe(bugs => this.data = bugs);
  }

  handleFilter() {
    console.log('Current filter: ');
    console.log(JSON.stringify(this.currentFilter));
    console.log('Previous filter: ');
    console.log(JSON.stringify(this.previousFilter));
    if (JSON.stringify(this.currentFilter) ===  JSON.stringify(this.previousFilter)) {
      console.log( 'nothing to do')
    } else {
      Object.assign(this.previousFilter, this.currentFilter);
      this.bugsService.getFilteredBugs(this.currentFilter).subscribe(bugs => this.data = bugs);
    }
  }
}
