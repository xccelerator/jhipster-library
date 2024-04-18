import { IPublisher, NewPublisher } from './publisher.model';

export const sampleWithRequiredData: IPublisher = {
  id: 16091,
  name: 'drawl than',
};

export const sampleWithPartialData: IPublisher = {
  id: 3646,
  name: 'mewl',
};

export const sampleWithFullData: IPublisher = {
  id: 18781,
  name: 'slip marvel less',
};

export const sampleWithNewData: NewPublisher = {
  name: 'bop knowingly sentiment',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
