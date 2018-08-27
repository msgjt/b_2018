enum FieldType { Title, Version, DueDate}
export class Bugfilter {
  title = '';
  version = '';
  fixedInversion = '';
  dueDate: string;
  severity: string;
  creator: string;
  assignee: string;
  status: string;
}

