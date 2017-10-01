import { Vehicle } from '../vehicle';
import { VehicleService } from '../vehicle.service';
import { Component, OnInit } from '@angular/core';

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

  constructor(private vehicleService: VehicleService) { }
  vehicle: Vehicle;
  isSuccess: boolean;

  ngOnInit() {
    this.initVehicle();
  }

  public submit() {
    this.vehicleService.addVehicle(this.vehicle).then(() => {
     this.isSuccess = true;
     this.initVehicle();
    });
  }

  public reset() {
    this.initVehicle();
  }

  private initVehicle() {
    this.vehicle = new Vehicle();
  }
}
