import {User} from './user';
import {BugStatusType} from './bugstatustype';
import {BugSeverityType} from './bugseveritytype';

export class Bug {
  id: number;
  title: string;
  description: string;
  fixedInVersion: string;
  version: string;
  creator: User;
  dueDate: string;
  status: BugStatusType;
  assignedTo: User;
  severityType: BugSeverityType;
}
