import { Component, input } from '@angular/core';
import { RouterModule } from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import { DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe } from 'app/shared/date';
import { IBorrowedBook } from '../borrowed-book.model';

@Component({
  standalone: true,
  selector: 'jhi-borrowed-book-detail',
  templateUrl: './borrowed-book-detail.component.html',
  imports: [SharedModule, RouterModule, DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe],
})
export class BorrowedBookDetailComponent {
  borrowedBook = input<IBorrowedBook | null>(null);

  previousState(): void {
    window.history.back();
  }
}
