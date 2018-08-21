import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'app-error',
  template: `
    <br>
    <br>
    <br>
    <h2 class="text-center text-uppercase text-secondary mb-0"><label class="help-block text-danger" style="font-size: 2em">404</label></h2>
    <hr class="star-dark mb-5">
    <div class="row justify-content-center">
      <p class="help-block text-danger" style="font-size: 2em">This is not the page you're looking for!</p>
    </div>
    <br>
    <br>
    <br>
  `,
  styles: []
})
export class ErrorComponent implements OnInit {

  constructor() {
  }

  ngOnInit() {
  }

}
