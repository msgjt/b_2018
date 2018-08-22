import {Component, OnInit} from '@angular/core';
import {ContentService} from '../shared/content.service';
import {Permission} from '../../shared/permission';
import {Role} from '../../shared/role';

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

  constructor(private contentService: ContentService) {
    this.model = new Role();
  }

  ngOnInit() {
    this.contentService.getAllPermissions()
      .subscribe(permissions => this.permissions = permissions,
        error => console.log(JSON.stringify(error)),
      () => {
        this.permissions.forEach(permission => this.itemList.push({'id' : permission.id, 'itemName' : permission.type}));
      });

    this.settings = {
      text: 'Select Permissions',
      selectAllText: 'Select All',
      unSelectAllText: 'UnSelect All',
      classes: 'myclass custom-class'
    };


  }

  submitForm() {
    console.log('submitted');
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
