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
  public rowsOnPage = 10;
  public sortBy = 'email';
  public sortOrder = 'ASCENDING';
  public lastSorter = 'title';

  public disableNext = false;

  public fiterList: Array<Bugfilter> = [];

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


  addFilter() {
    this.fiterList.push(new Bugfilter());
  }
}
