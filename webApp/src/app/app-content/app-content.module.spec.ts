import { AppContentModule } from './app-content.module';

describe('AppContentModule', () => {
  let appContentModule: AppContentModule;

  beforeEach(() => {
    appContentModule = new AppContentModule();
  });

  it('should create an instance', () => {
    expect(appContentModule).toBeTruthy();
  });
});
