import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { OverallViewComponent } from './overall-view.component';

describe('OverallViewComponent', () => {
  let component: OverallViewComponent;
  let fixture: ComponentFixture<OverallViewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ OverallViewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(OverallViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
