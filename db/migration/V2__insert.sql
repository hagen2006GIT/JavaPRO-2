
insert into clients (name) values ('TestClientX_1');
insert into clients (name) values ('TestClientX_2');
insert into clients (name) values ('TestClientX_3');
insert into clients (name) values ('TestClientX_4');

insert into products (account, balance, product_type, client_id)
	values ('TestAccount_1', 158.8, 0, 1L);
insert into products (account, balance, product_type, client_id)
	values ('TestAccount_2', 0.0, 1, 1L);
insert into products (account, balance, product_type, client_id)
	values ('TestAccount_3', 1058.1, 0, 2L);
insert into products (account, balance, product_type, client_id)
	values ('TestAccount_4', 34.2, 0, 3L);
insert into products (account, balance, product_type, client_id)
	values ('TestAccount_5', 0.0, 0, 2L);
