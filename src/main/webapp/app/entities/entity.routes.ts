import { Routes } from '@angular/router';

const routes: Routes = [
  {
    path: 'authority',
    data: { pageTitle: 'libraryWjdlApp.adminAuthority.home.title' },
    loadChildren: () => import('./admin/authority/authority.routes'),
  },
  {
    path: 'book',
    data: { pageTitle: 'libraryWjdlApp.book.home.title' },
    loadChildren: () => import('./book/book.routes'),
  },
  {
    path: 'publisher',
    data: { pageTitle: 'libraryWjdlApp.publisher.home.title' },
    loadChildren: () => import('./publisher/publisher.routes'),
  },
  {
    path: 'author',
    data: { pageTitle: 'libraryWjdlApp.author.home.title' },
    loadChildren: () => import('./author/author.routes'),
  },
  {
    path: 'borrowed-book',
    data: { pageTitle: 'libraryWjdlApp.borrowedBook.home.title' },
    loadChildren: () => import('./borrowed-book/borrowed-book.routes'),
  },
  {
    path: 'client',
    data: { pageTitle: 'libraryWjdlApp.client.home.title' },
    loadChildren: () => import('./client/client.routes'),
  },
  /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
];

export default routes;
