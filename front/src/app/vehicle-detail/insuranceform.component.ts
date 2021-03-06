import { Insurance } from '../models/insurance';
import { Repair } from '../models/repair';
import { RepairService } from './repair.service';
import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { InsuranceService } from "./insurance.service";

@Component({
  selector: 'app-insuranceform',
  template: `
  <div>
  <div class="panel panel-default">
    <div class="panel-body">
      <label for="company">Company</label>
      <input type="text" class="form-control" name="company" [(ngModel)]="insurance.company"><br/>
      <label for="cost">Start date</label>
      <input type="date" class="form-control" name="cost" [(ngModel)]="insurance.startDate"><br/>
      <label for="end_date">End date</label>
      <input type="date" class="form-control" name="end_date" [(ngModel)]="insurance.endDate"><br/>
      <button class="btn btn-primary"(click)="submit()">Add new insurance</button>
      <button class="btn btn-warning" (click)="reset()">Reset</button>
    </div>
  </div>
  `,
  styles: ['textarea { resize: none;}'],
  providers: [InsuranceService]
})
export class InsuranceformComponent implements OnInit {

  @Input() fleet_id: number;
  @Input() vehicle_id: number;
  @Output() onAdd = new EventEmitter<boolean>();

  constructor(private insuranceService: InsuranceService) { }

  insurance: Insurance;

  ngOnInit() {
    this.insurance = new Insurance();
  }

  public submit() {
    this.insuranceService.postInsurance(this.fleet_id, this.vehicle_id, this.insurance)
      .then(() => {
        this.onAdd.emit(true);
        this.insurance = new Insurance();
      });
  }

  public reset() {
    this.insurance = new Insurance();
  }
}
