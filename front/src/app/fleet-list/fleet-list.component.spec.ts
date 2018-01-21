import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FleetListComponent } from './fleet-list.component';

describe('FleetListComponent', () => {
  let component: FleetListComponent;
  let fixture: ComponentFixture<FleetListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FleetListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FleetListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
