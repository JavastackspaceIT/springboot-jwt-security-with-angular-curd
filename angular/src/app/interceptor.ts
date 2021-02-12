import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest, HttpUserEvent} from '@angular/common/http';
import {Observable} from 'rxjs/internal/Observable';
import {Injectable} from '@angular/core';

@Injectable()
export class TokenInterceptor implements HttpInterceptor {

    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>>  {
        // tslint:disable-next-line:no-debugger
    debugger;
    const token = window.localStorage.getItem('token');
    if (token) {
        req = req.clone({
        setHeaders: {
          Authorization: 'Bearer ' + token
        }
      });
    }
    return next.handle(req);
  }
}
