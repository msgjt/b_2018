import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BuglisterComponent } from './buglister.component';

describe('BuglisterComponent', () => {
  let component: BuglisterComponent;
  let fixture: ComponentFixture<BuglisterComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BuglisterComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BuglisterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
