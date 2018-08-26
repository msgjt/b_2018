import {User} from './user';

export class Bug {
  id: number;
  title: string;
  description: string;
  fixedInVersion: string;
  version: string;
  creator: User;
  dueDate: string;

}
