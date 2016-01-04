/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     18/01/2014 04:29:51 p.m.                     */
/*==============================================================*/


drop table if exists BRANCH;

drop table if exists BUY;

drop table if exists CATEGORY;

drop table if exists CREDIT;

drop table if exists DELIVERY;

drop table if exists DETAILBUY;

drop table if exists DETAIL_REFUND;

drop table if exists DETAIL_SALE;

drop table if exists EMPLOYEE;

drop table if exists FACTORY;

drop table if exists FUNCTION;

drop table if exists LOG_DETAIL_BUY;

drop table if exists LOG_DETAIL_SALE;

drop table if exists LOT_SALE;

drop table if exists PAYMENT;

drop table if exists PERSON;

drop table if exists PRODUCT;

drop table if exists PROVIDER;

drop table if exists RECORD_PRODUCTO;

drop table if exists RECORD_PRODUCT_BRANCH;

drop table if exists REFUND;

drop table if exists ROL;

drop table if exists ROL_FUN;

drop table if exists SALE;

drop table if exists SERVICES_ACCESS;

drop table if exists STOCK;

drop table if exists USER;

drop table if exists USER_ROL;

/*==============================================================*/
/* Table: BRANCH                                                */
/*==============================================================*/
create table BRANCH
(
   BRANCH_ID            int not null auto_increment,
   NAME                 varchar(150),
   ADDRESS              varchar(100),
   PHONE                int,
   DEL                  bool,
   primary key (BRANCH_ID)
);

/*==============================================================*/
/* Table: BUY                                                   */
/*==============================================================*/
create table BUY
(
   BUYID                int not null auto_increment,
   PROVIDER_ID          int,
   USERID               int not null,
   DATE_BUY             datetime not null,
   TOTAL                decimal not null,
   DEL                  bool not null,
   primary key (BUYID)
);

/*==============================================================*/
/* Table: CATEGORY                                              */
/*==============================================================*/
create table CATEGORY
(
   CATEGORY_ID          int not null auto_increment,
   CATEGORY_NAME        varchar(100) not null,
   DEL                  bool,
   primary key (CATEGORY_ID)
);

/*==============================================================*/
/* Table: CREDIT                                                */
/*==============================================================*/
create table CREDIT
(
   CREDIT_ID            int not null auto_increment,
   BUYID                int,
   USERID               int not null,
   TOTAL                decimal not null,
   CANCELED             bool not null,
   DEL                  bool not null,
   primary key (CREDIT_ID)
);

/*==============================================================*/
/* Table: DELIVERY                                              */
/*==============================================================*/
create table DELIVERY
(
   DELI_ID              int not null auto_increment,
   BRANCH_ID            int,
   PRODUCT_ID           int,
   USERID               int not null,
   AMOUNT               decimal not null,
   DATE_DELIVERY        datetime not null,
   DEL                  bool not null,
   primary key (DELI_ID)
);

/*==============================================================*/
/* Table: DETAILBUY                                             */
/*==============================================================*/
create table DETAILBUY
(
   DETAIL_BUY_ID        int not null auto_increment,
   PRODUCT_ID           int,
   BUYID                int,
   AMOUNT               decimal not null,
   PRICE_BUY            float not null,
   TOTAL                decimal not null,
   EXIST                bool not null,
   DEL                  bool not null,
   primary key (DETAIL_BUY_ID)
);

/*==============================================================*/
/* Table: DETAIL_REFUND                                         */
/*==============================================================*/
create table DETAIL_REFUND
(
   DETAIL_REFUND_ID     int not null auto_increment,
   BRANCH_ID            int,
   REF_ID               int,
   PRODUCT_ID           int,
   AMOUNT               decimal not null,
   DEL                  bool not null,
   primary key (DETAIL_REFUND_ID)
);

/*==============================================================*/
/* Table: DETAIL_SALE                                           */
/*==============================================================*/
create table DETAIL_SALE
(
   DETAIL_SALE_ID       int not null auto_increment,
   SAL_ID               int,
   PRODUCT_ID           int,
   BRANCH_ID            int,
   AMOUNT               decimal not null,
   COSTE                float,
   PRICE                float not null,
   GAIN                 float not null,
   TOTAL                decimal not null,
   DEL                  bool not null,
   primary key (DETAIL_SALE_ID)
);

/*==============================================================*/
/* Table: EMPLOYEE                                              */
/*==============================================================*/
create table EMPLOYEE
(
   EMP_ID               int not null auto_increment,
   PER_ID               int(100),
   BRANCH_ID            int,
   POSITION             varchar(100) not null,
   DEL                  bool not null,
   primary key (EMP_ID)
);

/*==============================================================*/
/* Table: FACTORY                                               */
/*==============================================================*/
create table FACTORY
(
   FACTORY_ID           int not null auto_increment,
   NAME                 varchar(150) not null,
   ORIGIN               varchar(150),
   DEL                  bool not null,
   primary key (FACTORY_ID)
);

/*==============================================================*/
/* Table: FUNCTION                                              */
/*==============================================================*/
create table FUNCTION
(
   FUNCTION_ID          int not null auto_increment,
   NAME_FUNC            varchar(100) not null,
   DEL                  bool not null,
   primary key (FUNCTION_ID)
);

/*==============================================================*/
/* Table: LOG_DETAIL_BUY                                        */
/*==============================================================*/
create table LOG_DETAIL_BUY
(
   ACTION               varchar(45),
   DATE                 datetime,
   DETAIL_BUY_ID_LOG    int not null,
   PRODUCT_ID_LOG       int,
   BUY_ID_LOG           int,
   AMOUNT               decimal,
   PRICE_BUY            float,
   TOTAL                decimal
);

/*==============================================================*/
/* Table: LOG_DETAIL_SALE                                       */
/*==============================================================*/
create table LOG_DETAIL_SALE
(
   ACTION               varchar(45) not null,
   DATE                 datetime not null,
   DETAIL_SALE_ID_LOG   int not null,
   _LOG                 int,
   PRODUCT_ID_LOG       int,
   BRANCH_ID_LOG        int,
   AMOUNT               decimal not null,
   COSTE                float,
   PRICE                float not null,
   GAIN                 float not null,
   TOTAL                decimal
);

/*==============================================================*/
/* Table: LOT_SALE                                              */
/*==============================================================*/
create table LOT_SALE
(
   LS_ID                int not null auto_increment,
   LOT_DETAIL_BUY_ID    bigint not null,
   LOT_DETAIL_SALE_ID   bigint not null,
   AMOUNT               decimal not null,
   DATE                 datetime not null,
   PURCHASE_PRICE       decimal(10,2),
   primary key (LS_ID)
);

/*==============================================================*/
/* Table: PAYMENT                                               */
/*==============================================================*/
create table PAYMENT
(
   PAYMENT_ID           int not null auto_increment,
   CREDIT_ID            int,
   USERID               int not null,
   AMOUNT               decimal not null,
   DATE_PAYMENT         datetime not null,
   DEL                  bool not null,
   primary key (PAYMENT_ID)
);

/*==============================================================*/
/* Table: PERSON                                                */
/*==============================================================*/
create table PERSON
(
   PER_ID               int(100) not null auto_increment,
   NAMES                varchar(100) not null,
   FIRSTNAME            varchar(100) not null,
   LASTNAME             varchar(100) not null,
   CI                   int not null,
   ADDRESS              varchar(100) not null,
   PHONE_ADDRESS        int,
   PHONE_MOBIL          int,
   EMAIL                varchar(70),
   DEL                  bool,
   primary key (PER_ID)
);

/*==============================================================*/
/* Table: PRODUCT                                               */
/*==============================================================*/
create table PRODUCT
(
   PRODUCT_ID           int not null auto_increment,
   CATEGORY_ID          int,
   FACTORY_ID           int,
   NAME                 varchar(150),
   COD_ORIGIN           varchar(70),
   DESCRIPTION          text,
   URL_PHOTO            varchar(200),
   DEL                  bool,
   primary key (PRODUCT_ID)
);

/*==============================================================*/
/* Table: PROVIDER                                              */
/*==============================================================*/
create table PROVIDER
(
   PROVIDER_ID          int not null auto_increment,
   BUSINESS_NAME        varchar(150) not null,
   AGENT                int,
   DEL                  bool,
   primary key (PROVIDER_ID)
);

/*==============================================================*/
/* Table: RECORD_PRODUCTO                                       */
/*==============================================================*/
create table RECORD_PRODUCTO
(
   RP_ID                int not null auto_increment,
   PRODUCT_ID           int,
   ACTIVE               varchar(150),
   AMOUNT               decimal,
   DATE                 datetime,
   TOTAL                decimal,
   DELIVERY_ID          int,
   BUY_ID               int,
   REFUND_ID            int,
   SALE_ID              int,
   DEL                  bool,
   primary key (RP_ID)
);

/*==============================================================*/
/* Table: RECORD_PRODUCT_BRANCH                                 */
/*==============================================================*/
create table RECORD_PRODUCT_BRANCH
(
   RPB_ID               int not null auto_increment,
   STOCK_ID             int,
   ACTIVE               varchar(150),
   AMOUNT               decimal,
   DELIVERY_ID          int,
   DETAIL_SALEID        int,
   REFUND_ID            int,
   FECHA                datetime,
   TOTAL                decimal,
   DEL                  bool,
   primary key (RPB_ID)
);

/*==============================================================*/
/* Table: REFUND                                                */
/*==============================================================*/
create table REFUND
(
   REF_ID               int not null auto_increment,
   USERID               int not null,
   DATE_REFUND          date not null,
   TOTAL                decimal not null,
   DEL                  bool not null,
   primary key (REF_ID)
);

/*==============================================================*/
/* Table: ROL                                                   */
/*==============================================================*/
create table ROL
(
   ROL_ID               int not null auto_increment,
   NAME_ROL             varchar(100) not null,
   DEL                  bool,
   primary key (ROL_ID)
);

/*==============================================================*/
/* Table: ROL_FUN                                               */
/*==============================================================*/
create table ROL_FUN
(
   ROL_ID               int not null,
   FUNCTION_ID          int not null,
   primary key (ROL_ID, FUNCTION_ID)
);

/*==============================================================*/
/* Table: SALE                                                  */
/*==============================================================*/
create table SALE
(
   SAL_ID               int not null auto_increment,
   USERID               int not null,
   DATE_SALE            datetime not null,
   TOTAL                decimal not null,
   DEL                  bool not null,
   primary key (SAL_ID)
);

/*==============================================================*/
/* Table: SERVICES_ACCESS                                       */
/*==============================================================*/
create table SERVICES_ACCESS
(
   SER_ID               int not null auto_increment,
   USER                 varchar(100) not null,
   PASSWORD             varchar(100) not null,
   primary key (SER_ID)
);

/*==============================================================*/
/* Table: STOCK                                                 */
/*==============================================================*/
create table STOCK
(
   STOCK_ID             int not null auto_increment,
   BRANCH_ID            int,
   PRODUCT_ID           int,
   DEL                  bool,
   primary key (STOCK_ID)
);

/*==============================================================*/
/* Table: USER                                                  */
/*==============================================================*/
create table USER
(
   USER_ID              int not null auto_increment,
   PER_ID               int(100),
   LOGIN                varchar(100) not null,
   PASSWORD             varchar(100) not null,
   DEL                  bool not null,
   primary key (USER_ID)
);

/*==============================================================*/
/* Table: USER_ROL                                              */
/*==============================================================*/
create table USER_ROL
(
   USER_ID              int not null,
   ROL_ID               int not null,
   primary key (USER_ID, ROL_ID)
);

alter table BUY add constraint FK_RELATIONSHIP_21 foreign key (PROVIDER_ID)
      references PROVIDER (PROVIDER_ID) on delete restrict on update restrict;

alter table CREDIT add constraint FK_RELATIONSHIP_22 foreign key (BUYID)
      references BUY (BUYID) on delete restrict on update restrict;

alter table DELIVERY add constraint FK_RELATIONSHIP_13 foreign key (BRANCH_ID)
      references BRANCH (BRANCH_ID) on delete restrict on update restrict;

alter table DELIVERY add constraint FK_RELATIONSHIP_14 foreign key (PRODUCT_ID)
      references PRODUCT (PRODUCT_ID) on delete restrict on update restrict;

alter table DETAILBUY add constraint FK_RELATIONSHIP_20 foreign key (BUYID)
      references BUY (BUYID) on delete restrict on update restrict;

alter table DETAILBUY add constraint FK_RELATIONSHIP_24 foreign key (PRODUCT_ID)
      references PRODUCT (PRODUCT_ID) on delete restrict on update restrict;

alter table DETAIL_REFUND add constraint FK_RELATIONSHIP_28 foreign key (PRODUCT_ID)
      references PRODUCT (PRODUCT_ID) on delete restrict on update restrict;

alter table DETAIL_REFUND add constraint FK_RELATIONSHIP_29 foreign key (BRANCH_ID)
      references BRANCH (BRANCH_ID) on delete restrict on update restrict;

alter table DETAIL_REFUND add constraint FK_RELATIONSHIP_30 foreign key (REF_ID)
      references REFUND (REF_ID) on delete restrict on update restrict;

alter table DETAIL_SALE add constraint FK_RELATIONSHIP_11 foreign key (BRANCH_ID)
      references BRANCH (BRANCH_ID) on delete restrict on update restrict;

alter table DETAIL_SALE add constraint FK_RELATIONSHIP_12 foreign key (PRODUCT_ID)
      references PRODUCT (PRODUCT_ID) on delete restrict on update restrict;

alter table DETAIL_SALE add constraint FK_RELATIONSHIP_27 foreign key (SAL_ID)
      references SALE (SAL_ID) on delete restrict on update restrict;

alter table EMPLOYEE add constraint FK_HAVE foreign key (BRANCH_ID)
      references BRANCH (BRANCH_ID) on delete restrict on update restrict;

alter table EMPLOYEE add constraint FK_RELATIONSHIP_15 foreign key (PER_ID)
      references PERSON (PER_ID) on delete restrict on update restrict;

alter table PAYMENT add constraint FK_RELATIONSHIP_23 foreign key (CREDIT_ID)
      references CREDIT (CREDIT_ID) on delete restrict on update restrict;

alter table PRODUCT add constraint FK_RELATIONSHIP_19 foreign key (CATEGORY_ID)
      references CATEGORY (CATEGORY_ID) on delete restrict on update restrict;

alter table PRODUCT add constraint FK_RELATIONSHIP_26 foreign key (FACTORY_ID)
      references FACTORY (FACTORY_ID) on delete restrict on update restrict;

alter table RECORD_PRODUCTO add constraint FK_RELATIONSHIP_10 foreign key (PRODUCT_ID)
      references PRODUCT (PRODUCT_ID) on delete restrict on update restrict;

alter table RECORD_PRODUCT_BRANCH add constraint FK_RELATIONSHIP_25 foreign key (STOCK_ID)
      references STOCK (STOCK_ID) on delete restrict on update restrict;

alter table ROL_FUN add constraint FK_ROL_FUN foreign key (ROL_ID)
      references ROL (ROL_ID) on delete restrict on update restrict;

alter table ROL_FUN add constraint FK_ROL_FUN2 foreign key (FUNCTION_ID)
      references FUNCTION (FUNCTION_ID) on delete restrict on update restrict;

alter table STOCK add constraint FK_RELATIONSHIP_16 foreign key (PRODUCT_ID)
      references PRODUCT (PRODUCT_ID) on delete restrict on update restrict;

alter table STOCK add constraint FK_RELATIONSHIP_17 foreign key (BRANCH_ID)
      references BRANCH (BRANCH_ID) on delete restrict on update restrict;

alter table USER add constraint FK_IS_A foreign key (PER_ID)
      references PERSON (PER_ID) on delete restrict on update restrict;

alter table USER_ROL add constraint FK_USER_ROL foreign key (USER_ID)
      references USER (USER_ID) on delete restrict on update restrict;

alter table USER_ROL add constraint FK_USER_ROL2 foreign key (ROL_ID)
      references ROL (ROL_ID) on delete restrict on update restrict;

