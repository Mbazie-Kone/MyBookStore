import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LayoutComponent } from './core/components/layout/layout.component';
import { ErrorComponent } from './errors/error/error.component';
import { LoginComponent } from './modules/admin/auth/login/login.component';

const routes: Routes = [
  { path: 'admin/login', component: LoginComponent},
  { path: '', redirectTo: 'home', pathMatch: 'full' },
  {
    path:'home', component: LayoutComponent,
    children: [
      { path:'catalog', loadChildren: () => import('./modules/catalog/catalog.module').then(m => m.CatalogModule) }
    ]
  },
  { path: '**', component: ErrorComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
