import {Component, OnInit} from '@angular/core';
import {User} from '../../shared/user';
import {ContentService} from '../shared/content.service';

@Component({
  selector: 'app-edit-user',
  templateUrl: './edit-user.component.html',
  styleUrls: ['./edit-user.component.css']
})
export class EditUserComponent implements OnInit {

  model: User;
  userArray: Array<User>;

  constructor(private userList: ContentService) {
  }

  ngOnInit() {
  }

  submitForm() {

  }

}
