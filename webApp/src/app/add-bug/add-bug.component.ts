import {Component, OnInit} from '@angular/core';

import {Bug} from '../shared/bug';
import {ContentService} from '../content/shared/content.service';
import {User} from '../shared/user';

@Component({
  selector: 'app-add-bug',
  templateUrl: './add-bug.component.html',
  styleUrls: ['./add-bug.component.css']
})
export class AddBugComponent implements OnInit {
  model: Bug = new Bug();
  user: User = new User();
  priorityList = ['LOW', 'MEDIUM', 'HIGH', 'CRITICAL'];
  users: Array<User>;
  priority = 'LOW';
  bugStatusType = 'OPEN';
  assignee = null;


  constructor(private contentservice: ContentService) {
  }

  ngOnInit() {
    this.contentservice.getAllUsers()
      .subscribe(result => this.users = result,
        error => console.log(JSON.stringify(error)));
  }


  onadd() {
    console.log(this.model.title + this.model.description + this.model.version
      + this.model.dueDate.toString() + this.priority + this.bugStatusType + this.assignee);
    this.contentservice.addbug(this.model.title, this.model.description,
      this.model.version, this.model.fixedInVersion, this.priority, this.bugStatusType, this.model.dueDate, this.assignee).subscribe();
  }

}
