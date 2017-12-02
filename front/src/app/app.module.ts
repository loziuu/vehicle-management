import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpModule } from '@angular/http';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import { VehicleDetailComponent } from './vehicle-detail/vehicle-detail.component';
import { VehiclesComponent } from './vehicles/vehicles.component';
import { RouterModule } from '@angular/router';
import { DashboardComponent } from './dashboard/dashboard.component';
import { RepairformComponent } from './vehicle-detail/repairform.component';
import { CreationComponent } from './vehicles/creation.component';
import { InsuranceformComponent } from './vehicle-detail/insuranceform.component';
import { FleetComponent } from './fleet/fleet.component';
import { FleetDetailComponent } from './fleet-detail/fleet-detail.component';
import { CheckoutformComponent } from './vehicle-detail/checkoutform.component';

@NgModule({
  declarations: [
    AppComponent,
    VehicleDetailComponent,
    VehiclesComponent,
    DashboardComponent,
    CreationComponent,
    RepairformComponent,
    InsuranceformComponent,
    FleetComponent,
    FleetDetailComponent,
    CheckoutformComponent
  ],
  imports: [
    BrowserModule,
    HttpModule,
    HttpClientModule,
    RouterModule.forRoot([
      {
        path: 'fleets',
        component: FleetComponent
      },
      {
        path: 'fleet/:id',
        component: FleetDetailComponent
      },
      {
        path: 'fleets/new',
        component: FleetComponent
      },
      {
        path: 'vehicles',
        component: VehiclesComponent
      },
      {
        path: 'fleet/:fleetId/vehicle/:vehicleId',
        component: VehicleDetailComponent
      },
      {
        path: 'dashboard',
        component: DashboardComponent
      },
      {
        path: '',
        redirectTo: 'dashboard',
        pathMatch: 'full'
      },
      {
        path: 'fleet/:id/vehicles/new',
        component: CreationComponent
      }
    ]),
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
