import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from '@angular/common/http';
import {Observable} from 'rxjs';

const TOKEN = 'token';
export class UserHttpInterceptor implements HttpInterceptor {
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const tocken = localStorage.getItem(TOKEN);
    const cloneRequest = req.clone({
      setHeaders: {'Authorization': `Bearer ${tocken}`}
    });
    return next.handle(cloneRequest);
  }
}
