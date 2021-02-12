import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/user';
import { Router } from '@angular/router';
import { ApiService } from 'src/app/api.service';
import { retry, catchError } from 'rxjs/operators';

@Component({
  selector: 'app-list-user',
  templateUrl: './list-user.component.html',
  styleUrls: ['./list-user.component.css']
})
export class ListUserComponent implements OnInit {
  users: User[];

  constructor(private router: Router, private apiService: ApiService) { }
   errorMessage;
  ngOnInit() {
    if (!window.localStorage.getItem('token')) {
      this.router.navigate(['login']);
    }
    // tslint:disable-next-line:no-debugger
    debugger;
    this.apiService.getUsers()
      .subscribe( (data) => {
      this.users = data.result;
      },
      (error) => {
        this.errorMessage = error;
        window.localStorage.setItem('errorMessage', this.errorMessage);
        this.router.navigate(['login']);
      }
      );
  }

  deleteUser(user: User): void {
    this.apiService.deleteUser(user.id)
      .subscribe( data => {
        this.users = this.users.filter(u => u !== user);
      });
  }
  editUser(user: User): void {
    window.localStorage.removeItem('editUserId');
    window.localStorage.setItem('editUserId', user.id + '');
    console.log('EditUserId  : ' + window.localStorage.getItem('editUserId'));
    this.router.navigate(['edit-user']);
  }
  addUser(): void {
    this.router.navigate(['add-user']);
  }
}
