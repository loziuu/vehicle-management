import { Component, OnInit } from '@angular/core';
import { AlertsService } from '../alerts.service';
import {Observable} from 'rxjs/Rx';

@Component({
  selector: 'app-alert',
  templateUrl: './alert.component.html',
  styleUrls: ['./alert.component.css']
})
export class AlertComponent implements OnInit {

  constructor(private service: AlertsService) { }
  alert:any;

  ngOnInit() {
    this.init();
  }

  public init() {
    this.service.getAlert().subscribe(result => {
        this.alert = result; 
        Observable.interval(2000).take(1).subscribe(() => {this.alert = null;});
      }
    );
  }
}
