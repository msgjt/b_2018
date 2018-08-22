import {Component, OnInit} from '@angular/core';
import {ContentService} from '../shared/content.service';
import {Role} from '../../shared/role';
import {Router} from '@angular/router';

@Component({
  selector: 'app-show-roles',
  templateUrl: './show-roles.component.html',
  styleUrls: ['./show-roles.component.css']
})
export class ShowRolesComponent implements OnInit {

  role: Role;
  roles: Array<Role>;

  constructor(private contentService: ContentService, private router: Router) {

  }

  ngOnInit() {
    this.contentService.getAllRoles()
      .subscribe(result => this.roles = result,
        error => console.log(JSON.stringify(error)));
  }

  editSelectedRole() {
    this.router.navigate(['/content/showRoles/editRole']);

  }

}
