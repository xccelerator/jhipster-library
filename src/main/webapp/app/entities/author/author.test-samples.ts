import { IAuthor, NewAuthor } from './author.model';

export const sampleWithRequiredData: IAuthor = {
  id: 6006,
  firstName: 'Chaz',
  lastName: 'Zboncak',
};

export const sampleWithPartialData: IAuthor = {
  id: 18578,
  firstName: 'Georgette',
  lastName: 'Stroman',
};

export const sampleWithFullData: IAuthor = {
  id: 17489,
  firstName: 'Amparo',
  lastName: 'Homenick',
};

export const sampleWithNewData: NewAuthor = {
  firstName: 'Cecelia',
  lastName: 'Cremin-Hoppe',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
