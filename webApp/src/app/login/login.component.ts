import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {LoginService} from './shared/login.service';
import {User} from '../shared/user';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  model: User;

  constructor(private loginService: LoginService,
              private router: Router) {
    this.model = new User();
  }


  ngOnInit() {
  }

  submitForm() {
    if (this.model.username && this.model.password) {
      this.loginService.login(this.model.username, this.model.password)
        .subscribe(result => {
            localStorage.setItem('userId', result.id.toString());
            localStorage.setItem('token', result.token);
            localStorage.setItem('username', result.username);
          },
          error => {
            console.log(JSON.stringify(error['error']));
            alert('login failed!');
          },
          () => alert('login successful'));
      // () => this.router.navigateByUrl('/content'));
    }
  }

  logout() {
    this.loginService.logout(localStorage.getItem('username'))
      .subscribe(result => console.log(result),
        error1 => {
          console.log(JSON.stringify(error1['error']));
          alert('logout failed!');
        },
        () => {
          localStorage.removeItem('userId');
          localStorage.removeItem('token');
          localStorage.removeItem('username');
          alert('logout successful');
        });
  }
}
