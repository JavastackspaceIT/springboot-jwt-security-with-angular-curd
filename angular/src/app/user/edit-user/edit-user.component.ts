import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/user';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ApiService } from 'src/app/api.service';

@Component({
  selector: 'app-edit-user',
  templateUrl: './edit-user.component.html',
  styleUrls: ['./edit-user.component.css']
})
export class EditUserComponent implements OnInit {

  user: User[];
  editForm: FormGroup;
  constructor(private formBuilder: FormBuilder, private router: Router, private apiService: ApiService) { }

  ngOnInit() {
    const userId = window.localStorage.getItem('editUserId');
    if (!userId) {
      alert('Invalid action.');
      this.router.navigate(['list-user']);
      return;
    }
    // tslint:disable-next-line:no-debugger
    debugger;
    this.editForm = this.formBuilder.group({
      id: ['', Validators.required],
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      emailAddress: ['', Validators.required],
      mobileNo: ['', Validators.required]
    });
    this.apiService.getUserById(+userId)
      .subscribe( data => {
        console.log(data);
        this.user = data.result;
        this.editForm.setValue(this.user.pop());
      });
    // tslint:disable-next-line:no-debugger
    debugger;
  }

  onSubmit() {
    this.apiService.updateUser(this.editForm.value)
      .subscribe(
        data => {
          if (data.status === 200) {
            alert('User updated successfully.');
            this.router.navigate(['list-user']);
          } else {
            alert(data.message);
          }
        },
        error => {
          alert(error);
        });
  }


}
