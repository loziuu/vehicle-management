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
import { HttpXsrfInterceptor } from './HttpXsrfInterceptor';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { HttpClientXsrfModule } from '@angular/common/http';
import { UsersComponent } from './users/users.component';
import { PermissionDeniedComponent } from './permission-denied/permission-denied.component';

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
    CheckoutformComponent,
    UsersComponent,
    PermissionDeniedComponent
  ],
  imports: [
    BrowserModule,
    HttpModule,
    HttpClientModule,
    HttpClientXsrfModule,
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
      },
      {
        path: 'users',
        component: UsersComponent
      },
      {
        path: '403',
        component: PermissionDeniedComponent
      }
    ]),
    FormsModule
  ],
  providers: [{ provide: HTTP_INTERCEPTORS, useClass: HttpXsrfInterceptor, multi: true}],
  bootstrap: [AppComponent]
})
export class AppModule { }
