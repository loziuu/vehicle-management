import { Vehicle } from '../models/vehicle';
import { VehicleService } from '../vehicle.service';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, ParamMap, Router } from '@angular/router';
import { Location } from '@angular/common';

@Component({
  selector: 'app-creation',
  template: `
    <div *ngIf="isSuccess" class="alert alert-success">
      Successfuly added vehicle!
    </div>

    <label for="model">Model</label>
    <input [(ngModel)]="vehicle.model" type="text" class="form-control" name="model"><br/>
    <label for="manufacturer">Manufacturer</label>
    <input [(ngModel)]="vehicle.manufacturer" type="text" class="form-control" name="manufacturer"><br/>
    <label for="productionYear">Production Year</label>
    <input [(ngModel)]="vehicle.productionYear" type="number" class="form-control" name="productionYear"><br/>
    <button (click)="submit()" class="btn btn-primary">Add new vehicle</button>
    <button (click)="reset()" class="btn btn-warning">Reset</button>
  `,
  styles: [],
  providers: [VehicleService]
})
export class CreationComponent implements OnInit {

  constructor(private vehicleService: VehicleService,
              private route: ActivatedRoute,
              private location: Location,
              private router: Router) { }
  vehicle: Vehicle;
  isSuccess: boolean;
  fleetId: number;

  ngOnInit() {
    this.route.paramMap.subscribe(params => this.fleetId =  + params.get("id"));
    this.initVehicle();
  }

  public submit() {
    this.vehicleService.createVehicle(this.fleetId, this.vehicle).subscribe(() => {
     this.isSuccess = true;
     this.initVehicle();
    });
  }

  public reset() {
    this.initVehicle();
  }

  private initVehicle() {
    if (this.isSuccess)
      this.router.navigate(['/fleet', this.fleetId]);
    this.vehicle = new Vehicle();
  }
}
