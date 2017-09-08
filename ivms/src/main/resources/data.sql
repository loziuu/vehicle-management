insert into vehicle(id, manufacturer, model, production_year) values (1, 'Renault', 'Clio', 2000);
insert into vehicle(id, manufacturer, model, production_year) values (2, 'Citroen', 'C3', 2012);
insert into insurance(id, start_date, end_date, vehicle_id) values (1, null, null, 1);
insert into insurance(id, start_date, end_date, vehicle_id) values (2, null, null, 1);
insert into repair(id, cost, vehicle_id) values (1, 120.0, 1);
insert into repair(id, cost, vehicle_id) values (2, 150.0, 1);