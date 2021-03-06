import {Component, OnInit} from '@angular/core';
import {Role} from '../../shared/role';
import {ContentService} from '../shared/content.service';
import {Permission} from '../../shared/permission';


@Component({
  selector: 'app-show-roles',
  templateUrl: './edit-role.component.html',
  styleUrls: ['./edit-role.component.css']
})
export class EditRoleComponent implements OnInit {

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


  saveId() {
    this.selectedPermissions = [];
    this.contentService.getRolePermissionsById(this.selectedRoles[0].id)
      .subscribe(permissions => this.permissions = permissions,
        error => console.log(JSON.stringify(error)),
        () => {
          this.permissions.forEach(permission => this.selectedPermissions.push({
            'id': permission.id,
            'itemName': permission.permissionType
          }));
        });
  }

  submit() {
    if (this.selectedPermissions.length !== 0 && this.selectedRoles.length !== 0) {
      const permissions = Array<Permission>();
      this.selectedPermissions.forEach(permission => permissions.push(new Permission(permission['id'], permission['itemName'])));
      this.contentService.updateRole(this.selectedRoles[0].id, this.selectedRoles[0].itemName, permissions)
        .subscribe(result => console.log(result),
          error => console.log(error));
    } else {
      alert('A role should have at least one permission');
    }
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
