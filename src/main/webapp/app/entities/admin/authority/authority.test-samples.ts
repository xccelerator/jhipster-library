import { IAuthority, NewAuthority } from './authority.model';

export const sampleWithRequiredData: IAuthority = {
  name: 'd9decc7f-91a1-4d1b-9ca5-5ca65cc2f69b',
};

export const sampleWithPartialData: IAuthority = {
  name: 'fad65fc2-4a13-42c5-9404-119fc67763f6',
};

export const sampleWithFullData: IAuthority = {
  name: 'd5636fb5-aef6-4fa8-8383-460dcef1780d',
};

export const sampleWithNewData: NewAuthority = {
  name: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
