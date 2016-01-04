insert into PERSON
(PER_ID,NAMES,FIRSTNAME,LASTNAME,CI,ADDRESS, PHONE_ADDRESS, PHONE_MOBIL, EMAIL, DEL)
VALUES
(null,'Angel Pedro','Murillo','Nava',5208822,'Av. la patria',4738013,79346585,'arc_quars@hotmail.com',0);
insert into PERSON
(PER_ID,NAMES,FIRSTNAME,LASTNAME,CI,ADDRESS, PHONE_ADDRESS, PHONE_MOBIL, EMAIL, DEL)
VALUES
(null,'Gonsalo','Viraca','',5998822,'P. E. Tejada',4738023,79347484,'angel@hotmail.com',0);

insert into USER 
(USER_ID,PER_ID,LOGIN,PASSWORD, DEL) 
values
(null,1,'pedro','123',0);

insert into USER 
(USER_ID,PER_ID,LOGIN,PASSWORD, DEL) 
values
(null,2,'gonchy','456',0);
insert into ROL
(ROL_ID, NAME_ROL, DEL)
values
(null, 'adminStock', 0);
insert into ROL
(ROL_ID, NAME_ROL, DEL)
values
(null, 'adminBranch', 0);
insert into USER_ROL
(USER_ID, ROL_ID)
values
(1,1);
insert into USER_ROL
(USER_ID, ROL_ID)
values
(2,2);

insert into BRANCH
(BRANCH_ID, NAME, ADDRESS, PHONE, DEL)
VALUES
(NULL, 'tienda 1', 'eSTABNA ARCE', 4589653,false);
insert into BRANCH
(BRANCH_ID, NAME, ADDRESS, PHONE, DEL)
VALUES
(NULL, 'tienda 2', 'torrez Gemelas', 4589335,false);
insert into BRANCH
(BRANCH_ID, NAME, ADDRESS, PHONE, DEL)
VALUES
(NULL, 'tienda 3', 'Mercado la Paz', 4589652,false);

insert into CATEGORY
(CATEGORY_ID,CATEGORY_NAME,DEL) 
values
(null,'Televisores',0);

insert into EMPLOYEE
(EMP_ID,BRANCH_ID, PER_ID, POSITION, DEL) 
values
(null,1, 2, 'Vendedor', FALSE);
insert into EMPLOYEE
(EMP_ID,BRANCH_ID, PER_ID, POSITION, DEL) 
values
(null,1, 1, 'Administrador', FALSE);

insert into PROVIDER
(Provider_ID, BUSINESS_NAME, AGENT, DEL)
VALUES
(NULL, 'Tienda Caceres', 3,false);
insert into PROVIDER
(Provider_ID, BUSINESS_NAME, AGENT, DEL)
VALUES
(NULL, 'Distribuidor Leo', 2,false);
insert into PROVIDER
(Provider_ID, BUSINESS_NAME, AGENT, DEL)
VALUES
(NULL, 'Jose parraga', 10,false);

insert into FACTORY 
(FACTORY_ID,NAME,ORIGIN,DEL)
values
(null, 'LG', 'japon', 0);

