import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LayoutComponent } from './core/components/layout/layout.component';
import { ErrorComponent } from './errors/error/error.component';
import { AdminLayoutComponent } from './core/components/admin/admin-layout/admin-layout.component';
import { LoginComponent } from './modules/admin/auth/login/login.component';
import { RegisterComponent } from './modules/admin/auth/register/register.component';

const routes: Routes = [
  { path: 'admin/login', component: LoginComponent},
  { path: 'admin/register', component: RegisterComponent},
  {
    path: 'admin/dashboard',
    component: AdminLayoutComponent,
    children: [
      { path: 'admin/dashboard', redirectTo: 'dashboard', pathMatch: 'full'},
      { path: 'dashboard', loadChildren: () => import('./modules/admin/admin.module').then(m => m.AdminModule) }
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
