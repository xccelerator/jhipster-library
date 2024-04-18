import { Component, input } from '@angular/core';
import { RouterModule } from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import { DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe } from 'app/shared/date';
import { IPublisher } from '../publisher.model';

@Component({
  standalone: true,
  selector: 'jhi-publisher-detail',
  templateUrl: './publisher-detail.component.html',
  imports: [SharedModule, RouterModule, DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe],
})
export class PublisherDetailComponent {
  publisher = input<IPublisher | null>(null);

  previousState(): void {
    window.history.back();
  }
}
