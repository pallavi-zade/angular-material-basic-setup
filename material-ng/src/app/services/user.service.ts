import { Injectable, Output } from '@angular/core'
import { Observable } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { environment } from '../../environments/environment';
import { urlConfig } from '../url-config/url-config';
import { HttpClient, HttpHeaders} from '@angular/common/http';


@Injectable()
export class UserService {

    constructor( private  http:  HttpClient) {
    }

    getAllUsers():Observable<any>{
        
        const httpOptions = {
             headers: new HttpHeaders({
              'Content-Type':  'application/json',
              'Authorization': localStorage.getItem( 'token' )
            })
          };
        return this.http.get( environment.parentURL + urlConfig.getAllUserURL, httpOptions);
    }
   

    registerUser( user: any ): Observable<any> {
        let bodyString: string = JSON.stringify( user );
        const httpOptions = {
            headers: new HttpHeaders({
             'Content-Type':  'application/json',
             'Authorization': localStorage.getItem( 'token' )
           })
         };

    return this.http.post<any>(environment.parentURL + urlConfig.register, user, httpOptions)
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
