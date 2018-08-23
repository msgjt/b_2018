import {Component, OnInit} from '@angular/core';

import {Bug} from '../shared/bug';
import {ContentService} from '../content/shared/content.service';

@Component({
  selector: 'app-add-bug',
  templateUrl: './add-bug.component.html',
  styleUrls: ['./add-bug.component.css']
})
export class AddBugComponent implements OnInit {
  model: Bug = new Bug();
  priorityList = ['LOW', 'MEDIUM', 'HIGH', 'CRITICAL'];
  statusList = ['OPEN', 'CLOSED', 'INFO_NEEDED'];


  constructor(private contentservice: ContentService) {
  }

  ngOnInit() {
  }


  onadd() {
    console.log('test');
    console.log(this.model.title + this.model.description + this.model.version + this.model.dueDate.toString());
    this.contentservice.addbug(this.model.title, this.model.description, this.model.version, this.model.fixedInVersion).subscribe();
  }

}
