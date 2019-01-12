import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BugsFormComponent } from './bugs-form.component';

describe('BugsFormComponent', () => {
  let component: BugsFormComponent;
  let fixture: ComponentFixture<BugsFormComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BugsFormComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BugsFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
