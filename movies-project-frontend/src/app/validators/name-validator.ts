import { AbstractControl, ValidationErrors } from "@angular/forms";

export class NameValidator{
    static onlyLettersValidator(control: AbstractControl): ValidationErrors | null {
        const value = control.value;
        const regex = /\d/;
        if (regex.test(value)) {
            return {invalidName: true}
        }
        return null;
    }
}