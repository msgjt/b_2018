import {Component, OnInit} from '@angular/core';
import {ContentService} from '../shared/content.service';
import {User} from '../../shared/user';
import {Role} from '../../shared/role';
import {LangChangeEvent, TranslateService} from '@ngx-translate/core';

@Component({
  selector: 'app-add-user',
  templateUrl: './add-user.component.html',
  styleUrls: ['./add-user.component.css']
})
export class AddUserComponent implements OnInit {

  model: User;
  roles: Array<Role>;
  itemList = [];
  selectedItems = [];
  settings = {};
  reenteredPassword;

  constructor(private contentService: ContentService,
              private translate: TranslateService) {
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

  clearFields() {
    this.model = new User();
    this.reenteredPassword = null;
    this.selectedItems = [];
  }

  submitForm() {
    if (this.model.firstName && this.model.lastName && this.model.password && this.model.email && this.model.mobileNumber) {
      if (this.selectedItems.length !== 0) {
        const roles = Array<Role>();
        this.selectedItems.forEach(r => roles.push(new Role(r['id'], r['itemName'])));
        this.contentService.addUser(this.model.firstName, this.model.lastName, this.model.email,
          this.model.password, this.model.mobileNumber, roles)
          .subscribe(result => console.log(result),
            error => console.log(JSON.stringify(error)),
            () => this.clearFields());
      } else {
        alert('An user should have at least one role.');
      }
    }
  }
}
