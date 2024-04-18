import { IClient, NewClient } from './client.model';

export const sampleWithRequiredData: IClient = {
  id: 9105,
  firstName: 'Brayan',
};

export const sampleWithPartialData: IClient = {
  id: 16510,
  firstName: 'Jocelyn',
  phone: '(688) 386-1378 x0058',
};

export const sampleWithFullData: IClient = {
  id: 15219,
  firstName: 'Cynthia',
  lastName: 'Cummerata',
  address: 'during boo secret',
  phone: '1-323-529-8152 x05762',
};

export const sampleWithNewData: NewClient = {
  firstName: 'Polly',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
