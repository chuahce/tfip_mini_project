import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators, AbstractControl } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from 'src/app/shared/services/auth.service';
import { StorageService } from 'src/app/shared/services/storage.service';

@Component({
  selector: 'app-auth',
  templateUrl: './auth.component.html',
  styleUrls: ['./auth.component.css']
})
export class AuthComponent implements OnInit {
  form: FormGroup;
  isLoginFailed = false;
  isLoginPage = false
  submitted = false;

  constructor(
    private formBuilder: FormBuilder,
    private authService: AuthService,
    private router: Router,
    private route: ActivatedRoute,
    private storageService: StorageService
  ) {
    this.form = this.formBuilder.group({
      username: ['', [
        Validators.required,
        Validators.maxLength(100),
        Validators.email
      ],],
      password: ['', [
        Validators.required,
        Validators.minLength(6),
      ]]
    });

    this.isLoginPage = this.router.url.includes("signin") ?? false;

    if (this.router.url.includes("signout")) {
      this.signOut()
    }
  }

  ngOnInit(): void { }

  onSubmit(): void {
    if (this.form.invalid) {
      return;
    }

    if (this.isLoginPage) {
      this.login();
    } else {
      this.signUp();
    }
  }

  login(): void {

    const username = this.form.value.username;
    const password = this.form.value.password;

    this.authService.login(username, password).subscribe(
      response => {
        // Handle successful login
        this.storageService.saveUser(response);
        this.router.navigate(['/movies']);
      },
      error => {
        this.isLoginFailed = true;

        console.error(error);
      }
    );
  }

  signUp(): void {

    const username = this.form.value.username;
    const password = this.form.value.password;

    this.authService.signUp(username, password).subscribe({
      next: (res) => {
        this.router.navigate(['../signin'], { relativeTo: this.route });
      },
      error: (e) => {
        console.error(e);
      }
    });
  }

  signOut(): void {
    this.authService.signOut().subscribe({
      next: () => {
        this.storageService.clean()
        this.router.navigate(['../signin'], { relativeTo: this.route });
      },
      error: (e) => console.error(e)
    });
  }

  get f(): { [key: string]: AbstractControl } {
    return this.form.controls;
  }

  onReset(): void {
    this.submitted = false;
    this.form.reset();
  }
}
