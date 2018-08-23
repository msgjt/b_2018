import {Component, OnInit} from '@angular/core';
import {User} from '../../shared/user';
import {ContentService} from '../shared/content.service';
import {Router} from '@angular/router';
import {Data} from '../../shared/data';

@Component({
  selector: 'app-show-users',
  templateUrl: './show-users.component.html',
  styleUrls: ['./show-users.component.css']
})
export class ShowUsersComponent implements OnInit {

  user: User;
  previewPhoto;
  users: Array<User>;

  constructor(private contentService: ContentService,
              private router: Router,
              private data: Data) {
  }


  ngOnInit() {
    this.contentService.getAllUsers()
      .subscribe(result => this.users = result,
        error => console.log(JSON.stringify(error)));


  }

  editSelectedUser(id, firstName, lastName, email, mobileNumber) {
    console.log(id);
    this.data.storage = {
      'id': id,
      'firstName': firstName,
      'lastName': lastName,
      'email': email,
      'mobileNumber': mobileNumber
    };
    this.router.navigate(['/user/edit']);
  }

  togglePhotoPreview() {
    this.previewPhoto = !this.previewPhoto;
  }

}
