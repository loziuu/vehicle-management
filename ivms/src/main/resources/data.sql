insert into fleet(id, name) values (1, 'Fleet number one');
insert into fleet(id, name) values (2, 'Fleet number two');
insert into vehicle(id, manufacturer, model, production_year, local, fleet_id) values (1, 'Renault', 'Clio', 2000, 1, 1);
insert into journal(id, vehicle_id) values (1, 1);
insert into vehicle(id, manufacturer, model, production_year, local, fleet_id) values (2, 'Citroen', 'C3', 2012, 2, 1);
insert into journal(id, vehicle_id) values (2, 2);
insert into vehicle(id, manufacturer, model, production_year, local, fleet_id) values (3, 'Citroen', 'C3', 2012, 1, 2);
insert into journal(id, vehicle_id) values (3, 3);
insert into insurance(id, start_date, end_date, journal_id) values (1, PARSEDATETIME ('2017-01-01', 'yyyy-MM-dd'), PARSEDATETIME ('2050-01-01', 'yyyy-MM-dd'), 1);
insert into insurance(id, start_date, end_date, journal_id) values (2, PARSEDATETIME ('2000-01-01', 'yyyy-MM-dd'), PARSEDATETIME ('2001-01-01', 'yyyy-MM-dd'), 2);
insert into repair(id, cost, journal_id) values (1, 120.0, 1);
insert into repair(id, cost, journal_id) values (2, 150.0, 1);
insert into checkout(id, date, expiration_date, result, journal_id) values (1, PARSEDATETIME ('2000-01-01', 'yyyy-MM-dd'), PARSEDATETIME ('2051-01-01', 'yyyy-MM-dd'), 'POSITIVE', 1);