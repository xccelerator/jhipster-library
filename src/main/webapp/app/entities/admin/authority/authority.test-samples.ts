import { IAuthority, NewAuthority } from './authority.model';

export const sampleWithRequiredData: IAuthority = {
  name: '2f13ac51-b559-45a5-82aa-f67f5a3f239f',
};

export const sampleWithPartialData: IAuthority = {
  name: '192e6b74-e062-454b-a860-277824cc5a19',
};

export const sampleWithFullData: IAuthority = {
  name: 'e696ebe1-fe99-48f6-b7f3-823fbf099529',
};

export const sampleWithNewData: NewAuthority = {
  name: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
