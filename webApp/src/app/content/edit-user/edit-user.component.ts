import {Component, OnInit} from '@angular/core';
import {User} from '../../shared/user';
import {ContentService} from '../shared/content.service';
import {Role} from '../../shared/role';
import {LangChangeEvent, TranslateService} from '@ngx-translate/core';

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

  constructor(private contentService: ContentService, private translate: TranslateService) {
    this.model = new User();
    this.translate.get('edit.selectroles').subscribe(res => {
      this.settings['text'] = res;
    });
    this.translate.get('edit.selectall').subscribe(res => {
      this.settings['selectAllText'] = res;
    });
    this.translate.get('edit.unselectall').subscribe(res => {
      this.settings['unSelectAllText'] = res;
      console.log(JSON.stringify(this.settings));
    });
  }

  submitForm() {
    console.log('submitted');
  }

  ngOnInit() {
    this.contentService.getAllRoles()
      .subscribe(roles => this.roles = roles,
        err => console.log(JSON.stringify(err)),
        () => {
          this.roles.forEach(r => this.itemList.push({'id': r.id, 'itemName': r.roleType}));
        });
    this.translate.onLangChange.subscribe((event: LangChangeEvent) => {
      this.settings = {
        text: this.translate.instant('edit.selectroles'),
        selectAllText: this.translate.instant('edit.selectall'),
        unSelectAllText: this.translate.instant('edit.unselectall'),
        classes: 'myclass custom-class'
      };
    });
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
