import { Component, OnInit } from '@angular/core';
import {User} from '../../shared/user';
import {ContentService} from '../shared/content.service';

@Component({
  selector: 'app-dummy-content',
  templateUrl: './dummy-content.component.html',
  styleUrls: ['./dummy-content.component.css']
})
export class DummyContentComponent implements OnInit {

  users: Array<User>;

  constructor(private contentService: ContentService) { }

  ngOnInit() {
    this.contentService.getAllUsers()
      .subscribe(result => this.users = result,
        error => console.log(JSON.stringify(error)));
  }

}
