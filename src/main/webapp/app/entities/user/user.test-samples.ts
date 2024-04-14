import { IUser } from './user.model';

export const sampleWithRequiredData: IUser = {
  id: 13153,
  login: 'FB',
};

export const sampleWithPartialData: IUser = {
  id: 4151,
  login: 'Ud',
};

export const sampleWithFullData: IUser = {
  id: 20691,
  login: 'Bde',
};
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
