import {Component, OnInit} from '@angular/core';
import {User} from '../../shared/user';
import {ContentService} from '../shared/content.service';
import {Role} from '../../shared/role';

@Component({
  selector: 'app-edit-user',
  templateUrl: './edit-user.component.html',
  styleUrls: ['./edit-user.component.css']
})
export class EditUserComponent implements OnInit {

  model: User;
  roles: Array<Role>;
  itemList = [];
  selectedItems = [];
  settings = {};

  constructor(private contentService: ContentService) {
    this.model = new User();
  }

  submitForm() {
    console.log('submitted');
  }

  ngOnInit() {
    // this.itemList = [
    //   {'id': 1, 'itemName': 'India'},
    //   {'id': 2, 'itemName': 'Singapore'},
    //   {'id': 3, 'itemName': 'Australia'},
    //   {'id': 4, 'itemName': 'Canada'},
    //   {'id': 5, 'itemName': 'South Korea'},
    //   {'id': 6, 'itemName': 'Brazil'}
    // ];

    this.contentService.getAllRoles()
      .subscribe(roles => this.roles = roles,
        err => console.log(JSON.stringify(err)),
        () => {
        this.roles.forEach(r => this.itemList.push({'id': r.id, 'itemName': r.roleType}));
        });

    // this.selectedItems = [
    //   {'id': 1, 'itemName': 'India'},
    //   {'id': 2, 'itemName': 'Singapore'},
    //   {'id': 3, 'itemName': 'Australia'},
    //   {'id': 4, 'itemName': 'Canada'}];
    this.settings = {
      text: 'Select Roles',
      selectAllText: 'Select All',
      unSelectAllText: 'UnSelect All',
      classes: 'myclass custom-class'
    };

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
