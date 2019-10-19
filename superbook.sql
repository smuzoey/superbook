/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2019/8/20 19:44:20                           */
/*==============================================================*/


drop table if exists book;

drop table if exists box;

drop table if exists category;

drop table if exists machine;

drop table if exists orderItem;

drop table if exists orders;

drop table if exists product;

drop table if exists productImage;

drop table if exists review;

drop table if exists User;

/*==============================================================*/
/* Table: book                                                  */
/*==============================================================*/
create table book
(
   isbn                 varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci not null ,
   publisher            varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci,
   binding              varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci,
   page                 int,
   author               varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci,
   paper                varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci,
   title                varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci,
   price                double,
   img                  varchar(250) CHARACTER SET utf8 COLLATE utf8_unicode_ci,
   gist                 text CHARACTER SET utf8 COLLATE utf8_unicode_ci,
   primary key (isbn)
);

alter table book comment '书籍详情表，例如出版商等信息';

/*==============================================================*/
/* Table: box                                                   */
/*==============================================================*/
create table box
(
   bid                  int not null auto_increment,
   mid                  int,
   boxState             int,
   size                 int,
   primary key (bid)
);

alter table box comment '箱子信息，包括箱子状态，以及大小等信息
';

/*==============================================================*/
/* Table: Category                                              */
/*==============================================================*/
create table category
(
   cid                  int not null auto_increment,
   name                 varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci,
   primary key (cid)
);

alter table category comment '分类表，存放分类信息，例如四级、六级';

/*==============================================================*/
/* Table: Machine                                               */
/*==============================================================*/
create table machine
(
   mid                  int not null auto_increment,
   position             varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci,
   machineState         int,
   primary key (mid)
);

alter table machine comment '机器信息，查询机器的状态以及的证等信息';

/*==============================================================*/
/* Table: OrderItem                                             */
/*==============================================================*/
create table orderItem
(
   id                   int not null auto_increment,
   oid                  int,
   uid                  int,
   number               int,
   primary key (id)
);

alter table orderItem comment '订单项表，也就是购物车里面的内容，购买产品的种类以及信息

';

/*==============================================================*/
/* Table: Orders                                                */
/*==============================================================*/
create table orders
(
   oid                  int not null auto_increment,
   uid                  int,
   bid                  int,
   orderCode            varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci,
   receiver             int,
   phone                varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci,
   userMessage          varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci,
   createTime           datetime,
   payDate              datetime,
   deliverDate          datetime,
   confirmDate           datetime,
   orderState           int,
   pid                  int,
   primary key (oid)
);

alter table orders comment '订单表，包括邮寄地址订单信息等';

/*==============================================================*/
/* Table: Product                                               */
/*==============================================================*/
create table product
(
   pid                  int not null auto_increment,
   cid                  int,
   isbn                 varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci,
   promotePrice         double,
   createDate           datetime,
   subTitle             varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci,
   degree               int,
   primary key (pid)
);

alter table product comment '展示商品表，包括商品的价格以及发布者等信息';

/*==============================================================*/
/* Table: ProductImage                                          */
/*==============================================================*/
create table productImage
(
   piid                 int not null auto_increment,
   pid                  int,
   type                 varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci,
   imgposition          text CHARACTER SET utf8 COLLATE utf8_unicode_ci,
   primary key (piid)
);

alter table productImage comment '书籍的图片，用户上传的图片,采用存储到文件夹的形式进行存储
';

/*==============================================================*/
/* Table: Review                                                */
/*==============================================================*/
create table review
(
   rid                  int not null auto_increment,
   pid                  int,
   uid                  int,
   content              varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci,
   createDate           datetime,
   primary key (rid)
);

alter table review comment '对于一个产品的评价信息';

/*==============================================================*/
/* Table: User                                                  */
/*==============================================================*/
create table user
(
   openId               varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci,
   uid                  int not null auto_increment ,
   nickName             varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci,
   uuid                 varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci,
   primary key (uid)
);

alter table user comment '用户信息，包括OpenId，以及uuid';

ALTER TABLE book DEFAULT CHARACTER SET utf8;
ALTER TABLE box DEFAULT CHARACTER SET utf8;
ALTER TABLE category DEFAULT CHARACTER SET utf8;
ALTER TABLE machine DEFAULT CHARACTER SET utf8;
ALTER TABLE orderItem DEFAULT CHARACTER SET utf8;
ALTER TABLE orders DEFAULT CHARACTER SET utf8;
ALTER TABLE product DEFAULT CHARACTER SET utf8;
ALTER TABLE productImage DEFAULT CHARACTER SET utf8;
ALTER TABLE review DEFAULT CHARACTER SET utf8;




alter table box add constraint FK_Relationship_7 foreign key (mid)
      references machine (mid) on delete restrict on update restrict;

alter table orderItem add constraint FK_Relationship_11 foreign key (uid)
      references user (uid) on delete restrict on update restrict;

alter table orders add constraint FK_Relationship_4 foreign key (pid)
      references product (pid) on delete restrict on update restrict;

alter table orderItem add constraint FK_Relationship_6 foreign key (oid)
      references orders (oid) on delete restrict on update restrict;

alter table orders add constraint FK_Relationship_12 foreign key (uid)
      references user (uid) on delete restrict on update restrict;
	  
/*alter table Orders add constraint FK_Relationship_21 foreign key (bid)
      references Box  (bid) on delete restrict on update restrict;*/
	  
/*alter table Orders add constraint FK_Relationship_20 foreign key (receiver)
      references User (uid) on delete restrict on update restrict;*/

alter table product add constraint FK_Relationship_1 foreign key (cid)
      references category (cid) on delete restrict on update restrict;

alter table product add constraint FK_Relationship_2 foreign key (isbn)
      references book (isbn) on delete restrict on update restrict;

alter table productImage add constraint FK_Relationship_3 foreign key (pid)
      references product (pid) on delete restrict on update restrict;

alter table review add constraint FK_Relationship_10 foreign key (uid)
      references user (uid) on delete restrict on update restrict;

alter table review add constraint FK_Relationship_5 foreign key (pid)
      references product (pid) on delete restrict on update restrict;

