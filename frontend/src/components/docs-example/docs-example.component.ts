import {
  AfterContentInit,
  AfterViewInit,
  ChangeDetectionStrategy,
  ChangeDetectorRef,
  Component,
  HostBinding,
  Input
} from '@angular/core';

import { IconDirective } from '@coreui/icons-angular';
import { RouterLink } from '@angular/router';
import { NavComponent, NavItemComponent, NavLinkDirective } from '@coreui/angular';

@Component({
    selector: 'app-docs-example',
    templateUrl: './docs-example.component.html',
    styleUrls: ['./docs-example.component.scss'],
    changeDetection: ChangeDetectionStrategy.OnPush,
    standalone: true,
    imports: [NavComponent, NavItemComponent, NavLinkDirective, RouterLink, IconDirective]
})
export class DocsExampleComponent implements AfterContentInit, AfterViewInit {

  constructor(
    private changeDetectorRef: ChangeDetectorRef
  ) {}

  @Input() fragment?: string;

  @HostBinding('class.example')
  get exampleClass() {
    return true;
  };



  ngAfterContentInit(): void {
    // this.changeDetectorRef.detectChanges();
  }

  ngAfterViewInit(): void {
    this.changeDetectorRef.markForCheck();
  }
}
