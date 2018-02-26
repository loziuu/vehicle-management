export class CreateVehicleRequest { 
    registration: String;
    details: Details;
}

export class Details {
    model: String;
    manufacturer: String;
    productionYear: number;
}