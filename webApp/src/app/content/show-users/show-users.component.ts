import {Component, OnInit} from '@angular/core';
import {User} from '../../shared/user';
import {ContentService} from '../shared/content.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-show-users',
  templateUrl: './show-users.component.html',
  styleUrls: ['./show-users.component.css']
})
export class ShowUsersComponent implements OnInit {
  model: User;
  previewPhoto;
  users: Array<User>;

  usersa = [
    {username: 'lungoa', firstName: 'Oana', lastName: 'Lung', mobileNumber: '0754326754', email: 'oana@msggroup.com', status: 'ACTIVE'},
    {username: 'oanalu', firstName: 'ana', lastName: 'ung', mobileNumber: '0754326754', email: 'oana@msggroup.com', status: 'ACTIVE'}

  ];

  constructor(private contentService: ContentService, private router: Router) {
  }


  ngOnInit() {
    this.contentService.getAllUsers()
      .subscribe(result => this.users = result,
        error => console.log(JSON.stringify(error)));


  }

  editSelectedUser() {
    this.router.navigate(['/editUser']);

  }

  togglePhotoPreview() {
    this.previewPhoto = !this.previewPhoto;
  }

}
