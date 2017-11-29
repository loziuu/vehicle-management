import { Vehicle } from '../vehicle';
import { VehicleService } from '../vehicle.service';
import { RepairService } from './repair.service';
import { Component, OnInit, Input } from '@angular/core';
import { ActivatedRoute, ParamMap } from '@angular/router';
import { Location } from '@angular/common';

import 'rxjs/add/operator/switchMap';

@Component({
  selector: 'app-vehicle-detail',
  templateUrl: './vehicle-detail.component.html',
  styleUrls: ['./vehicle-detail.component.css'],
  providers: [VehicleService, RepairService]
})
export class VehicleDetailComponent implements OnInit {
  vehicle: Vehicle;

  constructor(
    private vehicleService: VehicleService,
    private route: ActivatedRoute,
    private repairService: RepairService,
    private location: Location
  ) {}

  ngOnInit(): void {
    this.initVehicle();
  }

  public initVehicle() {
    this.route.paramMap
      .switchMap((params: ParamMap) => this.vehicleService.getVehicle(+params.get('id')))
      .subscribe(vehicle => this.vehicle = vehicle);
  }

  public removeRepair(repairId) {
    this.repairService.removeRepair(this.vehicle.content.id, repairId).then(() => this.initVehicle());
  }

  public goBack() {
    this.location.back();
  }
}
