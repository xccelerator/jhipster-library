import dayjs from 'dayjs/esm';
import { IPublisher } from 'app/entities/publisher/publisher.model';
import { IAuthor } from 'app/entities/author/author.model';

export interface IBook {
  id: number;
  isbn?: string | null;
  name?: string | null;
  publishYear?: dayjs.Dayjs | null;
  copies?: number | null;
  picture?: string | null;
  pictureContentType?: string | null;
  publisher?: IPublisher | null;
  authors?: IAuthor[] | null;
}

export type NewBook = Omit<IBook, 'id'> & { id: null };
