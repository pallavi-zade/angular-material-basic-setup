import {MatButtonModule,MatCheckboxModule, MatSelectModule, MatToolbarModule, MatIconModule, MatSidenavModule, MatBadgeModule, MatListModule, MatGridListModule, MatFormFieldModule, MatInputModule, MatRadioModule, MatDatepickerModule, MatNativeDateModule, MatChipsModule, MatTooltipModule, MatTableModule, MatPaginatorModule, MatCardModule} from '@angular/material';
import {MatButtonToggleModule} from '@angular/material/button-toggle';
import { CommonModule } from '@angular/common';
import {NgModule} from '@angular/core';

@NgModule({
    imports:[MatButtonModule,MatCheckboxModule,MatButtonToggleModule,CommonModule,
        MatButtonModule,
        MatToolbarModule,
        MatIconModule,
        MatSidenavModule,
        MatBadgeModule,
        MatListModule,
        MatGridListModule,
        MatFormFieldModule,
        MatInputModule,
        MatSelectModule,
        MatRadioModule,
        MatDatepickerModule,
        MatNativeDateModule,
        MatChipsModule,
        MatTooltipModule,
        MatTableModule,
        MatPaginatorModule,MatCardModule],
    exports:[MatButtonModule,MatCheckboxModule,MatButtonToggleModule,CommonModule,
        MatButtonModule,
        MatToolbarModule,
        MatIconModule,
        MatSidenavModule,
        MatBadgeModule,
        MatListModule,
        MatGridListModule,
        MatFormFieldModule,
        MatInputModule,
        MatSelectModule,
        MatRadioModule,
        MatDatepickerModule,
        MatNativeDateModule,
        MatChipsModule,
        MatTooltipModule,
        MatTableModule,
        MatPaginatorModule,MatCardModule]
})
export class MaterialModule{}