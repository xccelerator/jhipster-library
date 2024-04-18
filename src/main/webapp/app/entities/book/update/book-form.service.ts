import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import dayjs from 'dayjs/esm';
import { DATE_TIME_FORMAT } from 'app/config/input.constants';
import { IBook, NewBook } from '../book.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IBook for edit and NewBookFormGroupInput for create.
 */
type BookFormGroupInput = IBook | PartialWithRequiredKeyOf<NewBook>;

/**
 * Type that converts some properties for forms.
 */
type FormValueOf<T extends IBook | NewBook> = Omit<T, 'publishYear'> & {
  publishYear?: string | null;
};

type BookFormRawValue = FormValueOf<IBook>;

type NewBookFormRawValue = FormValueOf<NewBook>;

type BookFormDefaults = Pick<NewBook, 'id' | 'publishYear' | 'authors'>;

type BookFormGroupContent = {
  id: FormControl<BookFormRawValue['id'] | NewBook['id']>;
  isbn: FormControl<BookFormRawValue['isbn']>;
  name: FormControl<BookFormRawValue['name']>;
  publishYear: FormControl<BookFormRawValue['publishYear']>;
  copies: FormControl<BookFormRawValue['copies']>;
  picture: FormControl<BookFormRawValue['picture']>;
  pictureContentType: FormControl<BookFormRawValue['pictureContentType']>;
  publisher: FormControl<BookFormRawValue['publisher']>;
  authors: FormControl<BookFormRawValue['authors']>;
};

export type BookFormGroup = FormGroup<BookFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class BookFormService {
  createBookFormGroup(book: BookFormGroupInput = { id: null }): BookFormGroup {
    const bookRawValue = this.convertBookToBookRawValue({
      ...this.getFormDefaults(),
      ...book,
    });
    return new FormGroup<BookFormGroupContent>({
      id: new FormControl(
        { value: bookRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      isbn: new FormControl(bookRawValue.isbn, {
        validators: [Validators.required, Validators.minLength(13), Validators.maxLength(13)],
      }),
      name: new FormControl(bookRawValue.name, {
        validators: [Validators.maxLength(100)],
      }),
      publishYear: new FormControl(bookRawValue.publishYear),
      copies: new FormControl(bookRawValue.copies),
      picture: new FormControl(bookRawValue.picture),
      pictureContentType: new FormControl(bookRawValue.pictureContentType),
      publisher: new FormControl(bookRawValue.publisher),
      authors: new FormControl(bookRawValue.authors ?? []),
    });
  }

  getBook(form: BookFormGroup): IBook | NewBook {
    return this.convertBookRawValueToBook(form.getRawValue() as BookFormRawValue | NewBookFormRawValue);
  }

  resetForm(form: BookFormGroup, book: BookFormGroupInput): void {
    const bookRawValue = this.convertBookToBookRawValue({ ...this.getFormDefaults(), ...book });
    form.reset(
      {
        ...bookRawValue,
        id: { value: bookRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): BookFormDefaults {
    const currentTime = dayjs();

    return {
      id: null,
      publishYear: currentTime,
      authors: [],
    };
  }

  private convertBookRawValueToBook(rawBook: BookFormRawValue | NewBookFormRawValue): IBook | NewBook {
    return {
      ...rawBook,
      publishYear: dayjs(rawBook.publishYear, DATE_TIME_FORMAT),
    };
  }

  private convertBookToBookRawValue(
    book: IBook | (Partial<NewBook> & BookFormDefaults),
  ): BookFormRawValue | PartialWithRequiredKeyOf<NewBookFormRawValue> {
    return {
      ...book,
      publishYear: book.publishYear ? book.publishYear.format(DATE_TIME_FORMAT) : undefined,
      authors: book.authors ?? [],
    };
  }
}
