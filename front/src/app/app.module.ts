import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpModule } from '@angular/http';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { NgxChartsModule } from '@swimlane/ngx-charts';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

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
import { AlertComponent } from './alert/alert.component';
import { AlertsService } from './alerts.service';
import { EmptyComponent } from './empty/empty.component';
import { FleetListComponent } from './fleet-list/fleet-list.component';

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
    PermissionDeniedComponent,
    AlertComponent,
    EmptyComponent,
    FleetListComponent
  ],
  imports: [
    BrowserModule,
    NgxChartsModule,
    BrowserAnimationsModule,
    HttpModule,
    HttpClientModule,
    HttpClientXsrfModule,
    RouterModule.forRoot([
      {
        path: 'fleet',
        component: FleetListComponent
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
  providers: [{ provide: HTTP_INTERCEPTORS, useClass: HttpXsrfInterceptor, multi: true},
              { provide: AlertsService, useClass: AlertsService }],
  bootstrap: [AppComponent]
})
export class AppModule { }
