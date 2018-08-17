import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'app-error',
  template: `
    <label>404</label>
    <p>
      This is not the page you're looking for!
    </p>
  `,
  styles: [
    'p { ' +
    'color: red; ' +
    'font-size: 1.6em;' +
    '}',
    'label { ' +
    'color: red; ' +
    'font-size: 3em;' +
    '}',
  ]
})
export class ErrorComponent implements OnInit {

  constructor() {
  }

  ngOnInit() {
  }

}
