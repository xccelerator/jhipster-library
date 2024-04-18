import dayjs from 'dayjs/esm';

import { IBorrowedBook, NewBorrowedBook } from './borrowed-book.model';

export const sampleWithRequiredData: IBorrowedBook = {
  id: 4609,
};

export const sampleWithPartialData: IBorrowedBook = {
  id: 4411,
  borrowDate: dayjs('2024-04-16T23:56'),
  updatedDate: dayjs('2024-04-17T13:17'),
};

export const sampleWithFullData: IBorrowedBook = {
  id: 24,
  borrowDate: dayjs('2024-04-17T00:41'),
  updatedDate: dayjs('2024-04-17T09:57'),
};

export const sampleWithNewData: NewBorrowedBook = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
