import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpModule } from '@angular/http';
import { FormsModule } from '@angular/forms';

import { AppComponent } from './app.component';
import { VehicleDetailComponent } from './vehicle-detail/vehicle-detail.component';
import { VehiclesComponent } from './vehicles/vehicles.component';
import { RouterModule } from '@angular/router';
import { DashboardComponent } from './dashboard/dashboard.component';
import { RepairformComponent } from './vehicle-detail/repairform.component';
import { CreationComponent } from './vehicles/creation.component';
import { InsuranceformComponent } from './vehicle-detail/insuranceform.component';

@NgModule({
  declarations: [
    AppComponent,
    VehicleDetailComponent,
    VehiclesComponent,
    DashboardComponent,
    CreationComponent,
    RepairformComponent,
    InsuranceformComponent
  ],
  imports: [
    BrowserModule,
    HttpModule,
    RouterModule.forRoot([
      {
        path: 'vehicles',
        component: VehiclesComponent
      },
      {
        path: 'vehicle/:id',
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
        path: 'vehicles/new',
        component: CreationComponent
      }
    ]),
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
