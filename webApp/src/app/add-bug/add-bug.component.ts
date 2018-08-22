import {Component, OnInit} from '@angular/core';

import {Bug} from '../shared/bug';

@Component({
  selector: 'app-add-bug',
  templateUrl: './add-bug.component.html',
  styleUrls: ['./add-bug.component.css']
})
export class AddBugComponent implements OnInit {
  model: Bug;
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
