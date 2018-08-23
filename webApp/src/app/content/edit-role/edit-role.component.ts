import {Component, OnInit} from '@angular/core';
import {ContentService} from '../shared/content.service';
import {Permission} from '../../shared/permission';
import {Role} from '../../shared/role';
import {Router} from '@angular/router';
import {Data} from '../../shared/data';
import {Location} from '@angular/common';
import {TranslateService} from '@ngx-translate/core';

@Component({
  selector: 'app-edit-role',
  templateUrl: './edit-role.component.html',
  styleUrls: ['./edit-role.component.css']
})
export class EditRoleComponent implements OnInit {

  model: Role;
  permissions: Array<Permission>;
  itemList = [];
  selectedItems = [];
  settings = {};

  constructor(private contentService: ContentService,
              private data: Data,
              private location: Location,
              private router: Router,
              private translate: TranslateService) {

    this.model = new Role(null, null);
    if (this.data.storage !== undefined) {
      this.model.id = this.data.storage['id'];

      this.contentService.getRolePermissionsById(this.model.id)
        .subscribe(permissions => this.permissions = permissions,
          error => console.log(JSON.stringify(error)),
          () => {
            this.permissions.forEach(permission => this.selectedItems.push({'id': permission.id, 'itemName': permission.type}));
          });
    } // else {
   //   this.router.navigate(['content/editRole']);
   // }
  }

  ngOnInit() {
    this.contentService.getAllPermissions()
      .subscribe(permissions => this.permissions = permissions,
        error => console.log(JSON.stringify(error)),
        () => {
          this.permissions.forEach(permission => this.itemList.push({'id': permission.id, 'itemName': permission.type}));
        });

    this.settings = {
      text: 'Select Permissions',
      selectAllText: 'Select All',
      unSelectAllText: 'UnSelect All',
      classes: 'myclass custom-class'
    };


  }

  submitForm() {
    if (this.selectedItems.length !== 0) {
      const permissions = Array<Permission>();
      this.selectedItems.forEach(permission => permissions.push(new Permission(permission['id'], permission['itemName'])));
      this.contentService.updateRole(this.model.id, permissions)
        .subscribe(result => console.log(result),
          error => console.log(error),
          () => this.location.back());
    } else {
      alert('A role should have at least one permission.');
    }
  }

  onItemSelect() {
    console.log('on select');
    console.log(this.selectedItems);
  }

  OnItemDeSelect() {
    console.log('OnItemDeSelect');
    console.log(this.selectedItems);
  }

  onSelectAll() {
    console.log('onSelectAll');
    console.log(this.selectedItems);
  }

  onDeSelectAll() {
    console.log('onDeSelectAll');
    console.log(this.selectedItems);
  }

}
