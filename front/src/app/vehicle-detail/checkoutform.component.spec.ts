import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CheckoutformComponent } from './checkoutform.component';

describe('CheckoutformComponent', () => {
  let component: CheckoutformComponent;
  let fixture: ComponentFixture<CheckoutformComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CheckoutformComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CheckoutformComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
