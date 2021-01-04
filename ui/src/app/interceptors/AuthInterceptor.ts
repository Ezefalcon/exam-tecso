import { Injectable } from '@angular/core';
import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Observable, of, throwError } from 'rxjs';
import { catchError, map } from "rxjs/operators";
import { SessionService } from '../services/session.service';



@Injectable()
export class AuthInterceptor implements HttpInterceptor {

  constructor(private sessionService: SessionService) {
  }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const token = this.sessionService.getToken();
    const reqWithHeader = token ? req.clone({
      headers: req.headers.set('Authorization', token)
    }) : req;
    return next.handle(reqWithHeader).pipe(
      map(res => {
        return res;
      }),
      catchError(err => {
        return throwError(err);
      })
    );
  }

}
