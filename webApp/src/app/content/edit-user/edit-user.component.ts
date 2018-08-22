import {Component, OnInit} from '@angular/core';
import {User} from '../../shared/user';
import {ContentService} from '../shared/content.service';
import {Role} from '../../shared/role';
import {Data} from '../../shared/data';
import {Location} from '@angular/common';
import {Router} from '@angular/router';
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

  constructor(private contentService: ContentService,
              private data: Data,
              private location: Location,
              private router: Router,
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
    if (this.data.storage !== undefined) {
      this.model.id = this.data.storage['id'];
      this.model.firstName = this.data.storage['firstName'];
      this.model.lastName = this.data.storage['lastName'];
      this.model.email = this.data.storage['email'];
      this.model.mobileNumber = this.data.storage['mobileNumber'];

      this.contentService.getUserRolesById(this.model.id)
        .subscribe(roles => this.roles = roles,
          err => console.log(JSON.stringify(err)),
          () => {
            this.roles.forEach(r => this.selectedItems.push({'id': r.id, 'itemName': r.roleType}));
          });
    } else {
      this.router.navigate(['/user/show']);
    }
  }

  submitForm() {
    if (this.selectedItems.length !== 0) {
      const roles = Array<Role>();
      this.selectedItems.forEach(r => roles.push(new Role(r['id'], r['itemName'])));
      this.contentService.updateUser(this.model.id, this.model.firstName,
        this.model.lastName, this.model.email, this.model.password,
        this.model.mobileNumber, roles)
        .subscribe(result => console.log(result),
          error1 => console.log(error1),
          () => this.location.back());
    } else {
      alert('An user should have at least one role.');
    }
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
