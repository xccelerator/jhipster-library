import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import dayjs from 'dayjs/esm';
import { DATE_TIME_FORMAT } from 'app/config/input.constants';
import { IBorrowedBook, NewBorrowedBook } from '../borrowed-book.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IBorrowedBook for edit and NewBorrowedBookFormGroupInput for create.
 */
type BorrowedBookFormGroupInput = IBorrowedBook | PartialWithRequiredKeyOf<NewBorrowedBook>;

/**
 * Type that converts some properties for forms.
 */
type FormValueOf<T extends IBorrowedBook | NewBorrowedBook> = Omit<T, 'borrowDate' | 'updatedDate'> & {
  borrowDate?: string | null;
  updatedDate?: string | null;
};

type BorrowedBookFormRawValue = FormValueOf<IBorrowedBook>;

type NewBorrowedBookFormRawValue = FormValueOf<NewBorrowedBook>;

type BorrowedBookFormDefaults = Pick<NewBorrowedBook, 'id' | 'borrowDate' | 'updatedDate'>;

type BorrowedBookFormGroupContent = {
  id: FormControl<BorrowedBookFormRawValue['id'] | NewBorrowedBook['id']>;
  borrowDate: FormControl<BorrowedBookFormRawValue['borrowDate']>;
  updatedDate: FormControl<BorrowedBookFormRawValue['updatedDate']>;
  book: FormControl<BorrowedBookFormRawValue['book']>;
  clinet: FormControl<BorrowedBookFormRawValue['clinet']>;
};

export type BorrowedBookFormGroup = FormGroup<BorrowedBookFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class BorrowedBookFormService {
  createBorrowedBookFormGroup(borrowedBook: BorrowedBookFormGroupInput = { id: null }): BorrowedBookFormGroup {
    const borrowedBookRawValue = this.convertBorrowedBookToBorrowedBookRawValue({
      ...this.getFormDefaults(),
      ...borrowedBook,
    });
    return new FormGroup<BorrowedBookFormGroupContent>({
      id: new FormControl(
        { value: borrowedBookRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      borrowDate: new FormControl(borrowedBookRawValue.borrowDate),
      updatedDate: new FormControl(borrowedBookRawValue.updatedDate),
      book: new FormControl(borrowedBookRawValue.book),
      clinet: new FormControl(borrowedBookRawValue.clinet),
    });
  }

  getBorrowedBook(form: BorrowedBookFormGroup): IBorrowedBook | NewBorrowedBook {
    return this.convertBorrowedBookRawValueToBorrowedBook(form.getRawValue() as BorrowedBookFormRawValue | NewBorrowedBookFormRawValue);
  }

  resetForm(form: BorrowedBookFormGroup, borrowedBook: BorrowedBookFormGroupInput): void {
    const borrowedBookRawValue = this.convertBorrowedBookToBorrowedBookRawValue({ ...this.getFormDefaults(), ...borrowedBook });
    form.reset(
      {
        ...borrowedBookRawValue,
        id: { value: borrowedBookRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): BorrowedBookFormDefaults {
    const currentTime = dayjs();

    return {
      id: null,
      borrowDate: currentTime,
      updatedDate: currentTime,
    };
  }

  private convertBorrowedBookRawValueToBorrowedBook(
    rawBorrowedBook: BorrowedBookFormRawValue | NewBorrowedBookFormRawValue,
  ): IBorrowedBook | NewBorrowedBook {
    return {
      ...rawBorrowedBook,
      borrowDate: dayjs(rawBorrowedBook.borrowDate, DATE_TIME_FORMAT),
      updatedDate: dayjs(rawBorrowedBook.updatedDate, DATE_TIME_FORMAT),
    };
  }

  private convertBorrowedBookToBorrowedBookRawValue(
    borrowedBook: IBorrowedBook | (Partial<NewBorrowedBook> & BorrowedBookFormDefaults),
  ): BorrowedBookFormRawValue | PartialWithRequiredKeyOf<NewBorrowedBookFormRawValue> {
    return {
      ...borrowedBook,
      borrowDate: borrowedBook.borrowDate ? borrowedBook.borrowDate.format(DATE_TIME_FORMAT) : undefined,
      updatedDate: borrowedBook.updatedDate ? borrowedBook.updatedDate.format(DATE_TIME_FORMAT) : undefined,
    };
  }
}
