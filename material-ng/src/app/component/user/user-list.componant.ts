import { Component, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { UserService } from '../../services/user.service';
import {MatPaginator} from '@angular/material/paginator';
import {MatSort} from '@angular/material/sort';
import {MatTableDataSource} from '@angular/material/table';
export interface UserData {
    id:number;
    email: string,
    firstName: string,
    lastName: string,
    password: string,
    confirmPassword: string,
    phoneNo: string,
    role:string
  }

@Component({ templateUrl: 'user-list.componant.html' })
export class UserListComponent implements OnInit {
    userDataList:any= [];
    displayedColumns: string[] = ['id','firstName','lastName','email','phoneNo','role'];
    dataSource=new MatTableDataSource<UserData>();
    @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;
    @ViewChild(MatSort, {static: true}) sort: MatSort;
   

    constructor(
        private formBuilder: FormBuilder,
        private router: Router,private userService:UserService) {
           
           
        }

    ngOnInit() {
       
        }

        ngAfterViewInit(){
            this.getUserDataList() ;
            this.dataSource.sort = this.sort;
        }
    getUserDataList() {
        this.userService.getAllUsers()
            .subscribe( response => {
                let rs=response;
                this.userDataList = rs.data;
                this.dataSource = new MatTableDataSource(this.userDataList);
                this.dataSource.paginator = this.paginator;
                
                console.log(this.dataSource);

            }, error => {
                console.log( 'Unable to get list, Please contact system administrator' );
            } );
    }

    applyFilter(filterValue: string) {
        this.dataSource.filter = filterValue.trim().toLowerCase();
    
        if (this.dataSource.paginator) {
          this.dataSource.paginator.firstPage();
        }
      }
}
    

    
