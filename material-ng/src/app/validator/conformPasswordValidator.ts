import { FormControl, AbstractControl, ValidatorFn} from '@angular/forms';

export function ConfirmPassword(control: AbstractControl ): {[key: string]: any} {
   const group = control.parent;
   let password ;
   let confirmPassword;
   if(group) {
    password = group.controls['password'].value;
    confirmPassword = group.controls['confirmPassword'].value;
   }
    return confirmPassword === password ? null : {'confirmPassword': {value: control.value}};
}


