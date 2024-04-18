import dayjs from 'dayjs/esm';

import { IBook, NewBook } from './book.model';

export const sampleWithRequiredData: IBook = {
  id: 7360,
  isbn: 'while aboardX',
};

export const sampleWithPartialData: IBook = {
  id: 29613,
  isbn: 'wrest failing',
  publishYear: dayjs('2024-04-17T18:48'),
  copies: 31802,
};

export const sampleWithFullData: IBook = {
  id: 1164,
  isbn: 'unfortunately',
  name: 'unlock via above',
  publishYear: dayjs('2024-04-17T17:44'),
  copies: 28039,
  picture: '../fake-data/blob/hipster.png',
  pictureContentType: 'unknown',
};

export const sampleWithNewData: NewBook = {
  isbn: 'across well-o',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
