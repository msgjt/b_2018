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
  users: Array<User>;
  hasBugs: Map<string, boolean> = new Map<string, boolean>();

  constructor(private contentService: ContentService, private router: Router) {
  }


  ngOnInit() {
    this.contentService.getAllUsers()
      .subscribe(result => {
          this.users = result;
          for (let i = 0; i < this.users.length; i++) {
            this.contentService.hasOpenBugs(this.users[i]).subscribe(value => this.hasBugs.set(this.users[i].username, value),
              error => console.log(JSON.stringify(error)));
          }
        },
        error => console.log(JSON.stringify(error)));
  }

  editSelectedUser() {
    this.router.navigate(['/editUser']);

  }

  activateUser(user: User) {
    this.contentService.activateUser(user)
      .subscribe(result => {
          console.log(result);
          user.status = 'ACTIVE';
        },
        error => console.log(JSON.stringify(error)));
  }

  isActive(user: User): boolean {
    return user.status === 'ACTIVE';
  }

  deactivateUser(user: User) {
    this.contentService.deactivateUser(user)
      .subscribe(result => {
          console.log(result);
          user.status = 'DEACTIVATED';
        },
        error => console.log(JSON.stringify(error)));
  }
}
