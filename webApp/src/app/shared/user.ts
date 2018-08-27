import {Role} from './role';

export class User {
  id: number;
  firstName: string;
  lastName: string;
  username: string;
  password: string;
  mobileNumber: string;
  email: string;
  status: string;
  roles: Array<Role>;
}
