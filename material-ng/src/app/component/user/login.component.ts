import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { LoginService } from '../../services/login.service';



@Component({ templateUrl: 'login.component.html' })
export class LoginComponent implements OnInit {
    loginForm: FormGroup;
    loading = false;
    submitted = false;
    responceMsg: any;
    isSuccess:false;
    isError: boolean;
    constructor(
        private formBuilder: FormBuilder,
        private router: Router,private loginService: LoginService
        
    ) {
        
    }

    ngOnInit() {
        this.loginForm = this.formBuilder.group({
            email: ['', Validators.required],
            password: ['', Validators.required]
        });
    }
    login() {     
        this.responceMsg ='';
        let user = this.loginForm.value;
        if ( user.email != '' && user.password != '' ) {
            this.loginService.login( this.loginForm.value ).subscribe( res => {
                if ( res.status === "SUCCESS" ) {
                    localStorage.setItem('token',"Bearer "+res.data.token);
                    this.router.navigate( ['user-list'] );
                   // window.location.reload();
                }
                else {
                    this.isError=true;
                    this.responceMsg = res.message;
                }
            }, error => {
             
                this.responceMsg = "Service Not Available";

            } );
        } else {
           
            if ( user.email == '' && user.password == '' )
            { this.responceMsg = "Enter credentials"; }
            else if ( user.password == '' )
            { this.responceMsg = "Enter password"; }
            else
            { this.responceMsg = "Enter valid email"; }
        }
    }
    hideError(){
        this.isError=false;
        this.isSuccess=false;
        this.responceMsg='';
    }
    onSubmit() {
       this.login();
    }
}