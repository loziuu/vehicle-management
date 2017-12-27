import { Router } from '@angular/router';
import { Observable } from 'rxjs/Observable';
import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpRequest, HttpHandler, HttpXsrfTokenExtractor, HttpEvent } from "@angular/common/http";
import { HttpErrorResponse } from "@angular/common/http";
import 'rxjs/add/operator/do';
import 'rxjs/add/operator/catch';
import 'rxjs/Observable';
import 'rxjs/add/observable/throw';
import { AlertsService } from './alerts.service';

@Injectable()
export class HttpXsrfInterceptor implements HttpInterceptor {

  constructor(private tokenExtractor: HttpXsrfTokenExtractor,
              private router: Router,
              private service: AlertsService) {
  }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const headerName = 'X-XSRF-TOKEN';
    let token = this.tokenExtractor.getToken() as string;
    if (token !== null && !req.headers.has(headerName)) {
      req = req.clone({ headers: req.headers.set(headerName, token) });
    }
    req = req.clone( { withCredentials: true });
    return next.handle(req)
      .catch(err => {
        this.handleError(err);
        return Observable.throw(err);
      });
  }

  private handleError(error) {
    if (error instanceof HttpErrorResponse) {
      if (error.status == 403) {
        this.router.navigate(['/403']);
      }
      if (error.status == 400) {
        this.service.addAlert(error.error.content);
      }
    }
  }
}