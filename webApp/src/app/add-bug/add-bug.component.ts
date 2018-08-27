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
  creator: User;
  atachement = null;
  file = null;

  constructor(private contentservice: ContentService) {
  }

  ngOnInit() {
    this.contentservice.getAllUsers()
      .subscribe(result => this.users = result,
        error => console.log(JSON.stringify(error)));

  }

  onFile(event) {
    this.file = event.target.files[0];
  }

  onadd() {
    const loggedUser: string = localStorage.getItem('username');
    const user = this.users.filter((value) => {
      return value.username === loggedUser;
    });
    this.model.creator = user[0];
    console.log(this.model.title + this.model.description + this.model.version
      + this.model.dueDate.toString() + this.priority + this.bugStatusType + this.assignee + this.model.creator);
    this.contentservice.addbug(this.model.title, this.model.description,
      this.model.version, this.model.fixedInVersion, this.priority,
      this.bugStatusType, this.model.dueDate.toString(), this.assignee, this.model.creator).subscribe();
  }

}
