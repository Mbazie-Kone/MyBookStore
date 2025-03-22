import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LayoutComponent } from './core/components/layout/layout.component';
import { ErrorComponent } from './errors/error/error.component';
import { AdminLayoutComponent } from './core/components/admin/admin-layout/admin-layout.component';

const routes: Routes = [
  {
    path: 'admin',
    component: AdminLayoutComponent,
    children: [
      { path: 'admin', redirectTo: 'admin/login', pathMatch: 'full'},
      { path: 'admin/login', loadChildren: () => import('./modules/admin/admin.module').then(m => m.AdminModule) }
    ]
  },
  {
    path: '', 
    component: LayoutComponent,
    children: [
      { path: '', redirectTo: 'catalog', pathMatch: 'full' },
      { path: 'catalog', loadChildren: () => import('./modules/catalog/catalog.module').then(m => m.CatalogModule) }
    ]
  },
  { path: '**', component: ErrorComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)], 
  exports: [RouterModule]
})
export class AppRoutingModule { }
