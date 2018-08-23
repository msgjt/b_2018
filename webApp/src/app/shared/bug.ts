export class Bug {
  private _id: number;
  private _title: string;
  private _description: string;
  private _fixedInVersion:string;
  private _version: string;
  private _CreatedBy: string;
  private _dueDate: Date;
  private _statusType: string;
  private _severityType:string;


  get id(): number {
    return this._id;
  }

  set id(value: number) {
    this._id = value;
  }

  get title(): string {
    return this._title;
  }

  set title(value: string) {
    this._title = value;
  }

  get description(): string {
    return this._description;
  }

  set description(value: string) {
    this._description = value;
  }

  get version(): string {
    return this._version;
  }


  get fixedInVersion(): string {
    return this._fixedInVersion;
  }

  set fixedInVersion(value: string) {
    this._fixedInVersion = value;
  }

  set version(value: string) {
    this._version = value;
  }

  get CreatedBy(): string {
    return this._CreatedBy;
  }

  set CreatedBy(value: string) {
    this._CreatedBy = value;
  }

  get dueDate(): Date {
    return this._dueDate;
  }

  set dueDate(value: Date) {
    this._dueDate = value;
  }

  get statusType(): string {
    return this._statusType;
  }

  set statusType(value: string) {
    this._statusType = value;
  }

  get severityType(): string {
    return this._severityType;
  }

  set severityType(value: string) {
    this._severityType = value;
  }
}
