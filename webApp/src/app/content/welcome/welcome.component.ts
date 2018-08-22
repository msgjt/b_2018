import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'app-welcome',
  templateUrl: './welcome.component.html',
  styleUrls: ['./welcome.component.css']
})
export class WelcomeComponent implements OnInit {

  itemList = [];
  selectedItems = [];
  settings = {};

  constructor() {
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
