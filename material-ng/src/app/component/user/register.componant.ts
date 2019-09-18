import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators, FormGroupDirective } from '@angular/forms';
import { first } from 'rxjs/operators';
import { UserService } from '../../services/user.service';
import { ConfirmPassword } from 'src/app/validator/conformPasswordValidator';


@Component({ templateUrl: 'register.componant.html' })
export class RegisterComponent implements OnInit {
    registerForm: FormGroup;
    loading = false;
    submitted = false;
    Roles: any = ['Admin', 'Author', 'Reader'];
    isSuccess: boolean;
    responceMsg: any;
    isErrorPopup: boolean;

    constructor(
        private formBuilder: FormBuilder,
        private router: Router,private userService:UserService) {
        
    }
    setRegistrationForm(){
        this.registerForm = this.formBuilder.group({
            email: ['', [Validators.required,Validators.email]],
            password: ['', Validators.required],
            confirmPassword: ['', [Validators.required,ConfirmPassword]],
            lastName: ['', [Validators.required,Validators.maxLength(15)]],
            firstName: ['', [Validators.required,Validators.maxLength(15)]],
            phoneNo: ['', [Validators.required,Validators.maxLength(10)]],
            role: ['', [Validators.required]]
        });
    }

    ngOnInit() {

    this.setRegistrationForm();
    }

    registerUser(formDirective: FormGroupDirective) {
        this.hideError();
        let user = this.registerForm.value;
        this.userService.registerUser( user ).subscribe( response => {
            if ( response.status === "SUCCESS" ) {
                this.isSuccess = true;
                this.responceMsg = response.message;
                formDirective.resetForm();
                this.registerForm.reset();
                let user = response.data;
            } else {
               
                this.isErrorPopup = true;
                this.responceMsg = response.message;
            }

        }, error => {
            console.log('Unable to add user details, please contact system administrator.');
        } );

    }
    public hasError = (controlName: string, errorName: string) =>{
        return this.registerForm.controls[controlName].hasError(errorName);
      }
      hideError(){
        this.isErrorPopup=false;
        this.isSuccess=false;
        this.responceMsg='';
    }

   onSubmit(formDirective: FormGroupDirective) {
       this.hideError();
       this.registerUser(formDirective);
    }
}