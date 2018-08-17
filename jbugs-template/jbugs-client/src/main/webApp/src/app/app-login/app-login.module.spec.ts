import { AppLoginModule } from './app-login.module';

describe('AppLoginModule', () => {
  let appLoginModule: AppLoginModule;

  beforeEach(() => {
    appLoginModule = new AppLoginModule();
  });

  it('should create an instance', () => {
    expect(appLoginModule).toBeTruthy();
  });
});
