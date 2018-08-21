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
  user: User;
  previewPhoto;
  users: Array<User>;

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
