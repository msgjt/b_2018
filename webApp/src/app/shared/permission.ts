export class Permission {
  id: number;
  permissionType: string;

  constructor(id: number, permissionType: string) {
    this.id = id;
    this.permissionType = permissionType;
  }
}
