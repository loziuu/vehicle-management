import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { Checkout } from "../models/checkout";
import { CheckoutService } from "./checkout.service";

@Component({
  selector: 'app-checkoutform',
  templateUrl: './checkoutform.component.html',
  styleUrls: ['./checkoutform.component.css'],
  providers: [CheckoutService]
})
export class CheckoutformComponent implements OnInit {

  @Input()
  fleetId: number;
  @Input()
  vehicleId: number;
  @Output()
  onAdd = new EventEmitter<boolean>();
  checkout: Checkout;
  

  constructor(private service: CheckoutService) { }

  ngOnInit() {
    this.checkout = new Checkout();
  }

  private submit() {
    this.service.postCheckout(this.fleetId, this.vehicleId, this.checkout)
      .then(result => {
        this.onAdd.emit(true);
        this.checkout = new Checkout();
      });
  }

  private reset() {
  }
}
