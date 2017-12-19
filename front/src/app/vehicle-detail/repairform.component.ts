import { Repair } from '../models/repair';
import { RepairService } from './repair.service';
import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'app-repairform',
  template: `
  <div class="panel panel-default">
    <div class="panel-body">
      <label for="date">Date</label>
      <input type="date" class="form-control" name="date" [(ngModel)]="repair.date"><br/>
      <label for="cost">Cost</label>
      <input type="number" class="form-control" name="cost" [(ngModel)]="repair.cost"><br/>
      <label for="description">Description</label>
      <textarea name="description" class="form-control" rows="5" [(ngModel)]="repair.description"></textarea><br/>
      <button class="btn btn-primary"(click)="submit()">Add new repair</button>
      <button class="btn btn-warning" (click)="reset()">Reset</button>
    </div>
  </div>
  `,
  styles: ['textarea { resize: none;}'],
  providers: [RepairService]
})
export class RepairformComponent implements OnInit {

  @Input() fleet_id: number;
  @Input() vehicle_id: number;
  @Output() onAdd = new EventEmitter<boolean>();

  constructor(private repairService: RepairService) { }

  repair: Repair;

  ngOnInit() {
    this.repair = new Repair();
  }

  public submit() {
    this.repairService.postRepair(this.fleet_id, this.vehicle_id, this.repair).then(() => {
     this.onAdd.emit(true);
     this.repair = new Repair();
    });
  }

  public reset() {
    this.repair = new Repair();
  }
}
