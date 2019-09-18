import { Injectable, Output } from '@angular/core'
import { Subject } from 'rxjs/Subject'
import { Observable } from 'rxjs/Observable'

import { environment } from '../../environments/environment';
import { urlConfig } from '../url-config/url-config';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { catchError } from 'rxjs/operators';


@Injectable()
export class LoginService {

    constructor( private  http:  HttpClient ) {
    }


    login( user: any ): Observable<any> {     
      
      return this.http.post<any>(environment.parentURL + urlConfig.loginURL, user)
      .pipe(
        catchError(err=>{return this.handleError(err);})
      );
  }

  private handleError(error: any) {
    if (error.error instanceof ErrorEvent) {
      // A client-side or network error occurred. Handle it accordingly.
      console.error('An error occurred:', error.error.message);
      return "Please Contact Your Administrator";
    } else {
      // The backend returned an unsuccessful response code.
      // The response body may contain clues as to what went wrong,
      console.error(
        `Backend returned code ${error.status}, ` +
        `body was: ${error.error}`);
        return error.error;
    }
     
   
}
}