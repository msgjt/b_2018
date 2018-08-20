import {Component, OnInit} from '@angular/core';
import {ContentService} from '../shared/content.service';
import {User} from '../../shared/user';

@Component({
  selector: 'app-add-user',
  templateUrl: './add-user.component.html',
  styleUrls: ['./add-user.component.css']
})
export class AddUserComponent implements OnInit {

  model: User;

  constructor(private adminService: ContentService) {
    this.model = new User();
  }

  ngOnInit() {
  }

  clearFields() {
    this.model = new User();
  }

  submitForm() {
    if (this.model.firstName && this.model.lastName && this.model.password && this.model.email && this.model.mobileNumber) {
      this.adminService.addUser(this.model.firstName, this.model.lastName, this.model.email, this.model.password, this.model.mobileNumber)
        .subscribe(result => console.log(result),
          error => console.log(JSON.stringify(error)),
          () => this.clearFields());
    }
  }

}
