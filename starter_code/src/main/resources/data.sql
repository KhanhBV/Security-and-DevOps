insert into item (name, price, description) values ('Round Widget', 2.99, 'A widget that is round');
insert into item (name, price, description) values ('Square Widget', 1.99, 'A widget that is square');

insert into cart (total) values (4);
insert into cart (total) values (5);

insert into user (username, password, cart_id)
values ('khanh', 'khanhvui', 1);
insert into user (username, password, cart_id)
values ('khanhbui', 'vankhanh', 2);