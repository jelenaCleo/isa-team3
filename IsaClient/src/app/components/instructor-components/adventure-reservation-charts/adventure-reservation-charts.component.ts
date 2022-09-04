import { DatePipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import * as Chart from 'chart.js';
import { Adventure } from 'src/app/interfaces/adventure';
import { AdventureService } from 'src/app/services/adventure-service/adventure.service';
import { ReservationService } from 'src/app/services/reservation-service/reservation.service';
import { StorageService } from 'src/app/services/storage-service/storage.service';

@Component({
  selector: 'app-adventure-reservation-charts',
  templateUrl: './adventure-reservation-charts.component.html',
  styleUrls: ['./adventure-reservation-charts.component.css']
})
export class AdventureReservationChartsComponent implements OnInit {

  labelsWeekBoat: any[] = [];
  valuesWeekBoat: number[] = [];
  labelsMonthBoat: any[] = [];
  valuesMonthBoat: number[] = [];
  labelsYearBoat: any[] = ['JAN', 'FEB', 'MAR', 'APR', 'MAY', 'JUN', 'JUL', 'AUG', 'SEP', 'OCT', 'NOV', 'DEC'];
  valuesYearBoat: number[] = [];
  labelsWeek: any[] = [];
  valuesWeek: number[] = [];
  labelsMonth: any[] = [];
  valuesMonth: number[] = [];
  labelsYear: any[] = ['JAN', 'FEB', 'MAR', 'APR', 'MAY', 'JUN', 'JUL', 'AUG', 'SEP', 'OCT', 'NOV', 'DEC'];
  valuesYear: number[] = [];
  email : string = '';
  adventures: Adventure[] = [];
  adventure = new FormControl([Validators.required]);
  selectedAdventure: Adventure = {} as Adventure;

  constructor(private _pipe : DatePipe,
    private _reservationService : ReservationService, private _storageService: StorageService, private _adventureService: AdventureService) {
      this.email = this._storageService.getEmail();
     }

  ngOnInit(): void {
    this._adventureService.getAllByInstructor(this._storageService.getEmail()).subscribe(
      res => {
        this.adventures = res;
        //this.selectedBoat = this.boats[0];
      }
    )
    var now = new Date();
    this._reservationService.getReservationChartForDateRangeOwner(
      this._pipe.transform( new Date(new Date(now).setDate(now.getDate() - 7)), 'yyyy-MM-dd')!, 
      this._pipe.transform( now, 'yyyy-MM-dd')!,
      this._storageService.getEmail(),
      "ADVENTURE").subscribe(
        res => {
          res.forEach(earning => {  
            this.labelsWeek.push(this._pipe.transform(earning.date, 'yyyy-MM-dd'));
            this.valuesWeek.push(earning.income);
          })

          const data = {
            labels: this.labelsWeek,
            datasets: [{
              label: 'Chart for your reservations last week ',
              backgroundColor: 'rgb(120, 93, 227)',
              borderColor: 'rgb(255, 99, 132)',
              data: this.valuesWeek,
            }]
          };
          
            const myChart = new Chart(
              'myChartWeek',
              {
                type: 'bar',
                data: data,
                options: {}
              }
            );
          })

          this._reservationService.getReservationChartForDateRangeOwner(
            this._pipe.transform(new Date(now.getFullYear(), now.getMonth(), 1), 'yyyy-MM-dd')!, 
            this._pipe.transform(now, 'yyyy-MM-dd')!,
            this._storageService.getEmail(),
            "ADVENTURE").subscribe(
              res => {
                res.forEach(earning => {  
                  this.labelsMonth.push(this._pipe.transform(earning.date, 'yyyy-MM-dd'));
                  this.valuesMonth.push(earning.income);
                })
                
                const data = {
                  labels: this.labelsMonth,
                  datasets: [{
                    label: 'Chart for your reservations this month',
                    backgroundColor: 'rgb(120, 93, 227)',
                    borderColor: 'rgb(255, 99, 132)',
                    data: this.valuesMonth,
                  }]
                };
                
                  const myChart = new Chart(
                    'myChartMonth',
                    {
                      type: 'bar',
                      data: data,
                      options: {}
                    }
                  );
                })
                this._reservationService.getReservationChartForDateRangeYearOwner(
                  this._pipe.transform(new Date(now.getFullYear(), 1, 1), 'yyyy-MM-dd')!, 
                  this._pipe.transform(now, 'yyyy-MM-dd')!,
                  this._storageService.getEmail(),
                  "ADVENTURE").subscribe(
                    res => {
                      res.forEach(earning => {  
                       // this.labelsYear.push(this._pipe.transform(earning.month, 'yyyy-MM-dd'));
                        this.valuesYear.push(earning.numOfRes);
                      })
                      
                      const data = {
                        labels: this.labelsYear,
                        datasets: [{
                          label: 'Chart for your reservations this year',
                          backgroundColor: 'rgb(120, 93, 227)',
                          borderColor: 'rgb(255, 99, 132)',
                          data: this.valuesYear,
                        }]
                      };
                      
                        const myChart = new Chart(
                          'myChartYear',
                          {
                            type: 'bar',
                            data: data,
                            options: {}
                          }
                        );
                      })
    
  }
  adventureChosen() {
    this.labelsWeekBoat = [];
    this.valuesWeekBoat = [];
    this.labelsMonthBoat = [];
    this.valuesMonthBoat = [];
    this.labelsYearBoat = ['JAN', 'FEB', 'MAR', 'APR', 'MAY', 'JUN', 'JUL', 'AUG', 'SEP', 'OCT', 'NOV', 'DEC'];
    this.valuesYearBoat = [];
    this.getAdventureCharts();
  }

  getAdventureCharts(){
    var now = new Date();
    this._reservationService.getReservationChartForDateRangeObject(
      this._pipe.transform( new Date(new Date(now).setDate(now.getDate() - 7)), 'yyyy-MM-dd')!, 
      this._pipe.transform( now, 'yyyy-MM-dd')!,
      this.selectedAdventure.id,
      "ADVENTURE").subscribe(
        res => {
          console.log(res);
          res.forEach(earning => {  
            this.labelsWeekBoat.push(this._pipe.transform(earning.date, 'yyyy-MM-dd'));
            this.valuesWeekBoat.push(earning.income);
          })
          
          console.log(this.labelsWeekBoat);
          console.log(this.valuesWeekBoat);

          const data = {
            labels: this.labelsWeekBoat,
            datasets: [{
              label: 'Chart for your reservations last week ',
              backgroundColor: 'rgb(255, 99, 132)',
              borderColor: 'rgb(255, 99, 132)',
              data: this.valuesWeekBoat,
            }]
          };
          
            const myChart = new Chart(
              'myChartWeekBoat',
              {
                type: 'bar',
                data: data,
                options: {}
              }
            );
          })

          this._reservationService.getReservationChartForDateRangeObject(
            this._pipe.transform(new Date(now.getFullYear(), now.getMonth(), 1), 'yyyy-MM-dd')!, 
            this._pipe.transform(now, 'yyyy-MM-dd')!,
            this.selectedAdventure.id,
            "ADVENTURE").subscribe(
              res => {
                res.forEach(earning => {  
                  this.labelsMonthBoat.push(this._pipe.transform(earning.date, 'yyyy-MM-dd'));
                  this.valuesMonthBoat.push(earning.income);
                })
                
                const data = {
                  labels: this.labelsMonthBoat,
                  datasets: [{
                    label: 'Chart for your reservations this month',
                    backgroundColor: 'rgb(255, 99, 132)',
                    borderColor: 'rgb(255, 99, 132)',
                    data: this.valuesMonthBoat,
                  }]
                };
                
                  const myChart = new Chart(
                    'myChartMonthBoat',
                    {
                      type: 'bar',
                      data: data,
                      options: {}
                    }
                  );
                })
                this._reservationService.getReservationChartForDateRangeYearObject(
                  this._pipe.transform(new Date(now.getFullYear(), 1, 1), 'yyyy-MM-dd')!, 
                  this._pipe.transform(now, 'yyyy-MM-dd')!,
                  this.selectedAdventure.id,
                  "ADVENTURE").subscribe(
                    res => {
                      res.forEach(earning => {  
                       // this.labelsYear.push(this._pipe.transform(earning.month, 'yyyy-MM-dd'));
                        this.valuesYearBoat.push(earning.numOfRes);
                      })
                      
                      const data = {
                        labels: this.labelsYearBoat,
                        datasets: [{
                          label: 'Chart for your reservations this year',
                          backgroundColor: 'rgb(255, 99, 132)',
                          borderColor: 'rgb(255, 99, 132)',
                          data: this.valuesYearBoat,
                        }]
                      };
                      
                        const myChart = new Chart(
                          'myChartYearBoat',
                          {
                            type: 'bar',
                            data: data,
                            options: {}
                          }
                        );
                      })

  }

}
