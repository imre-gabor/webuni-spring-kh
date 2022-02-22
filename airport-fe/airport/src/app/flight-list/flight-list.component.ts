import { Component, OnInit } from '@angular/core';
import { Flight } from '../model/Flight';
import { FlightService } from '../services/flight.service';
import { Stomp } from '@stomp/stompjs';
import * as SockJS from 'sockjs-client';
import { Subscriber } from 'rxjs';

@Component({
  selector: 'app-flight-list',
  templateUrl: './flight-list.component.html',
  styleUrls: ['./flight-list.component.scss']
})
export class FlightListComponent implements OnInit {

  flights: Flight[] = [];
  stompClient;

  constructor(
    private flightService: FlightService
  ) { }

  ngOnInit(): void {
    this.refreshFlights();
  }

  refreshFlights() {

    this.flightService.getFlights().subscribe(
      flights => {
        this.flights = flights;
        this.connect();
      }
    );
  } 

  connect() {
    const ws = new SockJS('api/stomp');
    this.stompClient = Stomp.over(ws);
    let that = this;
    this.stompClient.connect({}, frame => {   
      console.log('Connected: ' + frame);
      this.flights.forEach(flight => this.subscribeToDelays(flight.id) );
    });
    
  }

  subscribeToDelays(flightId: number) {
    this.stompClient.subscribe('/topic/delay/' + flightId, 
        message => {
          const delayData = JSON.parse(message.body);
          this.flights.forEach(flight =>{
            if(flight.id == flightId){
              flight.delay = delayData.delay;
            }
          });
        }
    );
  }
}
