import { Injectable } from '@angular/core';
import { Observable } from "rxjs/Rx";
import { Subject } from 'rxjs/Subject';

@Injectable()
export class AlertsService {
  private alert = new Subject<any>();
  constructor() { }

  public addAlert(message) {
    this.alert.next(<any>{ msg: message });
  }

  public getAlert(): Observable<any> {
    return this.alert.asObservable();
  }
}
