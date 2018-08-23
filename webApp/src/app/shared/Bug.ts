import {User} from './user';

export class Bug {
  id: string;
  title: string;
  description: string;
  version: string;
  fixedVersion: string;
  dueDate: string;
  creator: User;
  assignee: User;
  status: string;
  severity: string;
}
