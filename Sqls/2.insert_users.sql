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
