import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { InsuranceformComponent } from './insuranceform.component';

describe('InsuranceformComponent', () => {
  let component: InsuranceformComponent;
  let fixture: ComponentFixture<InsuranceformComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ InsuranceformComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(InsuranceformComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
