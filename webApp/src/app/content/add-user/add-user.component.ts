import {Component, OnInit} from '@angular/core';
import {ContentService} from '../shared/content.service';
import {User} from '../../shared/user';

@Component({
  selector: 'app-add-user',
  templateUrl: './add-user.component.html',
  styleUrls: ['./add-user.component.css']
})
export class AddUserComponent implements OnInit {

  model: User;
  itemList = [];
  selectedItems = [];
  settings = {};

  constructor(private adminService: ContentService) {
    this.model = new User();
  }

  ngOnInit() {
    this.itemList = [
      {'id': 1, 'itemName': 'India'},
      {'id': 2, 'itemName': 'Singapore'},
      {'id': 3, 'itemName': 'Australia'},
      {'id': 4, 'itemName': 'Canada'},
      {'id': 5, 'itemName': 'South Korea'},
      {'id': 6, 'itemName': 'Brazil'}
    ];

    this.selectedItems = [
      {'id': 1, 'itemName': 'India'},
      {'id': 2, 'itemName': 'Singapore'},
      {'id': 3, 'itemName': 'Australia'},
      {'id': 4, 'itemName': 'Canada'}];
    this.settings = {
      text: 'Select Countries',
      selectAllText: 'Select All',
      unSelectAllText: 'UnSelect All',
      classes: 'myclass custom-class'
    };
  }

  clearFields() {
    this.model = new User();
  }

  submitForm() {
    if (this.model.firstName && this.model.lastName && this.model.password && this.model.email && this.model.mobileNumber) {
      this.adminService.addUser(this.model.firstName, this.model.lastName, this.model.email, this.model.password, this.model.mobileNumber)
        .subscribe(result => console.log(result),
          error => console.log(JSON.stringify(error)),
          () => this.clearFields());
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
