import {Component, OnInit} from '@angular/core';
import {Role} from '../../shared/role';
import {ContentService} from '../shared/content.service';
import {Permission} from '../../shared/permission';


@Component({
  selector: 'app-show-roles',
  templateUrl: './show-roles.component.html',
  styleUrls: ['./show-roles.component.css']
})
export class ShowRolesComponent implements OnInit {

  model: Role;
  roles: Array<Role>;
  permissions: Array<Permission>;
  roleList = [];
  permissionList = [];
  selectedRoles = [];
  selectedPermissions = [];
  settingsRole = {
    singleSelection: true
  };
  settingsPermission = {};

  constructor(private contentService: ContentService) {
    this.model = new Role(null, null);

    this.contentService.getAllRoles()
      .subscribe(roles => this.roles = roles,
        error => console.log(JSON.stringify(error)),
        () => {
          this.roles.forEach(role => this.roleList.push({'id': role.id, 'itemName': role.roleType}));

        });


  }

  ngOnInit() {
    this.contentService.getAllPermissions()
      .subscribe(permissions => this.permissions = permissions,
        error => console.log(JSON.stringify(error)),
        () => {
          this.permissions.forEach(permission => this.permissionList.push({'id': permission.id, 'itemName': permission.permissionType}));
        });
  }


  saveId(id) {
    this.model.id = id;
    this.selectedPermissions = [];
    this.contentService.getRolePermissionsById(id)
      .subscribe(permissions => this.permissions = permissions,
        error => console.log(JSON.stringify(error)),
        () => {
          this.permissions.forEach(permission => this.selectedPermissions.push({
            'id': permission.id,
            'itemName': permission.permissionType
          }));
        });
  }

  onRoleSelect() {
    console.log('on select');
    console.log(this.selectedRoles[0].id);
  }

  OnRoleDeSelect() {
    console.log('OnItemDeSelect');
    console.log(this.selectedRoles);
  }

  onSelectAllRoles() {
    console.log('onSelectAll');
    console.log(this.selectedRoles);
  }

  onDeSelectAllRoles() {
    console.log('onDeSelectAll');
    console.log(this.selectedRoles);
  }

  onPermissionSelect() {
    console.log('on select');
    console.log(this.selectedPermissions);
  }

  OnPermissionDeSelect() {
    console.log('OnItemDeSelect');
    console.log(this.selectedPermissions);
  }

  onSelectAllPermissions() {
    console.log('onSelectAll');
    console.log(this.selectedPermissions);
  }

  onDeSelectAllPermissions() {
    console.log('onDeSelectAll');
    console.log(this.selectedPermissions);
  }
}
