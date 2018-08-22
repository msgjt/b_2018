import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-buglister',
  templateUrl: './buglister.component.html',
  styleUrls: ['./buglister.component.css']
})
export class BuglisterComponent implements OnInit {

  public data;
  public filterQuery = '';
  public rowsOnPage = 10;
  public sortBy = 'email';
  public sortOrder = 'asc';

  constructor() {
  }

  ngOnInit(): void {
    setTimeout( () => {
    this.data = [
      {
        'title': 'Stuff doesnt work',
        'version': '1',
        'fixedVersion': '2',
        'dueDate': '2014-02-06',
        'severity': 25,
        'creator' : 'Balazs',
        'assignee': 'Gyula',
        'status': 'Fixed'
      },
      {
        'title': 'Login doesnt work',
        'version': '2',
        'fixedVersion': '3',
        'dueDate': '2014-02-06',
        'severity': 25,
        'creator' : 'Balazs',
        'assignee': 'Gyula',
        'status': 'Fixed'
      },
      {
        'title': 'Register doesnt work',
        'version': '3',
        'fixedVersion': '3',
        'dueDate': '2014-02-07',
        'severity': 25,
        'creator' : 'Balazs',
        'assignee': 'Gyula',
        'status': 'In Progress'
      },      {
        'title': 'Stuff doesnt work',
        'version': '1',
        'fixedVersion': '2',
        'dueDate': '2014-02-06',
        'severity': 25,
        'creator' : 'Balazs',
        'assignee': 'Gyula',
        'status': 'Fixed'
      },
      {
        'title': 'Login doesnt work',
        'version': '2',
        'fixedVersion': '3',
        'dueDate': '2014-02-06',
        'severity': 25,
        'creator' : 'Balazs',
        'assignee': 'Gyula',
        'status': 'Fixed'
      },
      {
        'title': 'Register doesnt work',
        'version': '3',
        'fixedVersion': '3',
        'dueDate': '2014-02-07',
        'severity': 25,
        'creator' : 'Balazs',
        'assignee': 'Gyula',
        'status': 'In Progress'
      },      {
        'title': 'Stuff doesnt work',
        'version': '1',
        'fixedVersion': '2',
        'dueDate': '2014-02-06',
        'severity': 25,
        'creator' : 'Balazs',
        'assignee': 'Gyula',
        'status': 'Fixed'
      },
      {
        'title': 'Login doesnt work',
        'version': '2',
        'fixedVersion': '3',
        'dueDate': '2014-02-06',
        'severity': 25,
        'creator' : 'Balazs',
        'assignee': 'Gyula',
        'status': 'Fixed'
      },
      {
        'title': 'Register doesnt work',
        'version': '3',
        'fixedVersion': '3',
        'dueDate': '2014-02-07',
        'severity': 25,
        'creator' : 'Balazs',
        'assignee': 'Gyula',
        'status': 'In Progress'
      },      {
        'title': 'Stuff doesnt work',
        'version': '1',
        'fixedVersion': '2',
        'dueDate': '2014-02-06',
        'severity': 25,
        'creator' : 'Balazs',
        'assignee': 'Gyula',
        'status': 'Fixed'
      },
      {
        'title': 'Login doesnt work',
        'version': '2',
        'fixedVersion': '3',
        'dueDate': '2014-02-06',
        'severity': 25,
        'creator' : 'Balazs',
        'assignee': 'Gyula',
        'status': 'Fixed'
      },
      {
        'title': 'Register doesnt work',
        'version': '3',
        'fixedVersion': '3',
        'dueDate': '2014-02-07',
        'severity': 25,
        'creator' : 'Balazs',
        'assignee': 'Gyula',
        'status': 'In Progress'
      },      {
        'title': 'Stuff doesnt work',
        'version': '1',
        'fixedVersion': '2',
        'dueDate': '2014-02-06',
        'severity': 25,
        'creator' : 'Balazs',
        'assignee': 'Gyula',
        'status': 'Fixed'
      },
      {
        'title': 'Login doesnt work',
        'version': '2',
        'fixedVersion': '3',
        'dueDate': '2014-02-06',
        'severity': 25,
        'creator' : 'Balazs',
        'assignee': 'Gyula',
        'status': 'Fixed'
      },
      {
        'title': 'Register doesnt work',
        'version': '3',
        'fixedVersion': '3',
        'dueDate': '2014-02-07',
        'severity': 25,
        'creator' : 'Balazs',
        'assignee': 'Gyula',
        'status': 'In Progress'
      }
      ]; //end of list
    }, // end of f*in lambda
      1000);
  }

  public toInt(num: string) {
    return +num;
  }

  public sortByWordLength = (a: any) => {
    return a.city.length;
  }

}
