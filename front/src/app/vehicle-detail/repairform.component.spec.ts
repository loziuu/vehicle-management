import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RepairformComponent } from './repairform.component';

describe('RepairformComponent', () => {
  let component: RepairformComponent;
  let fixture: ComponentFixture<RepairformComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RepairformComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RepairformComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
