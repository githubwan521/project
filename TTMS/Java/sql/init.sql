drop database if exists ttms;
create database ttms;
use ttms;

create table data_dict(
   dict_id              int not null auto_increment,
   dict_parent_id       int comment '所属字典的id',
   dict_index           int not null comment '字典的序号',
   dict_name            nvarchar(200) not null comment '字典名称',
   dict_value           nvarchar(100) not null comment '字典的值',
   primary key (dict_id)
);

create table user(
   user_id               int not null auto_increment,
   user_identity         nvarchar(50) not null comment '用户身份',
   user_name             nvarchar(50) not null comment '用户name',
   user_pass             nvarchar(50) not null comment '用户密码',
   user_tel_num          nvarchar(20) comment '联系电话',
   user_addr             nvarchar(200) comment '住址',
   user_email            nvarchar(100) comment 'Email',
   user_status           int not null comment '0离线1在线',
   user_time             timestamp default now() comment '用户创建时间',
   primary key (user_id)
);

create table play(
   play_id              int not null auto_increment,
   play_type            int not null comment '剧目类型',
   play_language        int not null comment  '剧目语言',
   play_name            nvarchar(200) not null comment '剧目名称',
   play_introduction    nvarchar(2000) not null comment '剧目信息',
   play_image           longblob comment '剧目图片',
   play_duration        int not null comment '剧目时长',
   primary key (play_id)
);

create table sale(
   sale_id              int not null auto_increment,
   user_id              int comment '用户id',
   sale_time            timestamp default now() comment '创建时间',
   sale_payment         float  comment '支付金额',
   sale_refund          float  comment '找回金额',
   sale_status          int comment '订单状态如下  0：销售 1：退款',
   primary key (sale_ID)
);
create table sale_item
(
   sale_item_id         int not null auto_increment,
   ticket_id            int comment '票id',
   sale_id              int comment '所属订单id',
   sale_price           float comment '售价',
   primary key (sale_item_id)
);
create table schedule(
   schedule_id          int not null auto_increment,
   studio_id            int not null comment '所在演出厅',
   play_id              int not null comment '所属剧目',
   schedule_time        datetime not null comment '演出时间',
   schedule_price       float not null comment '票价',
   schedule_status      int not null comment '0：待安排演出  1：已安排演',
   primary key (schedule_id)
);

create table seat(
   seat_id              int not null auto_increment,
   studio_id            int not null comment '所属演出厅',
   seat_row             int not null comment '行',
   seat_column          int not null comment '列',
   seat_status          int not null comment '0:正在使用  1：未使用  2：座位损坏',
   primary key (seat_id)
);

create table studio(
   studio_id            int not null auto_increment,
   studio_name          nvarchar(100) not null comment '演出厅名',
   studio_row_count     int not null comment '总行数',
   studio_col_count     int not null comment '总列数',
   studio_introduction  varchar(2000) comment '演出厅简介',
   primary key (studio_id)
);

create table ticket(
   ticket_id               int not null auto_increment,
   seat_id                 int comment '票所属的座位',
   schedule_id             int comment '票所属的演出计划',
   ticket_price            float  comment '票价',
   ticket_status           int comment '0：待销售 1：锁定 2：卖出 3不存在',
   primary key (ticket_id)
);

alter table user add unique(user_identity,user_name); 
alter table play add unique(play_name,play_language); 
alter table schedule add unique(studio_id,play_id,schedule_time); 
alter table studio add unique(studio_name);

alter table data_dict add constraint FK_super_child_dict foreign key (dict_parent_id)
      references data_dict (dict_id) on delete restrict on update restrict;

alter table play add constraint FK_dict_lan_play foreign key (play_language)
      references data_dict (dict_id) on delete restrict on update restrict;

alter table play add constraint FK_dict_type_play foreign key (play_type)
      references data_dict (dict_id) on delete restrict on update restrict;

alter table sale add constraint FK_employee_sale foreign key (user_id)
      references user (user_id) on delete restrict on update restrict;

alter table sale_item add constraint FK_sale_sale_item foreign key (sale_id)
      references sale (sale_id) on delete restrict on update restrict;

alter table sale_item add constraint FK_ticket_sale_item foreign key (ticket_id)
      references ticket (ticket_id) on delete restrict on update restrict;

alter table schedule add constraint FK_play_schedule foreign key (play_id)
      references play (play_id) on delete restrict on update restrict;

alter table schedule add constraint FK_studio_sched foreign key (studio_id)
      references studio (studio_id) on delete restrict on update restrict;

alter table seat add constraint FK_studio_seat foreign key (studio_id)
      references studio (studio_id) on delete restrict on update restrict;

alter table ticket add constraint FK_schedule_ticket foreign key (schedule_id)
      references schedule (schedule_id) on delete restrict on update restrict;

alter table ticket add constraint FK_seat_ticket foreign key (seat_id)
      references seat (seat_id) on delete restrict on update restrict;

/*默认值插入*/
insert into data_dict(dict_parent_id,dict_index,dict_name,dict_value) VALUES (1,1,'root','root');
 insert into data_dict(dict_parent_id,dict_index,dict_name,dict_value) VALUES (1,1,'用户身份类型','用户身份类型');
 insert into data_dict(dict_parent_id,dict_index,dict_name,dict_value) VALUES (1,2,'剧目语种','剧目语种类型');
 insert into data_dict(dict_parent_id,dict_index,dict_name,dict_value) VALUES (1,3,'剧目类型','剧目类型');

 insert into data_dict(dict_parent_id,dict_index,dict_name,dict_value) VALUES (2,1,'sa','管理员');
 insert into data_dict(dict_parent_id,dict_index,dict_name,dict_value) VALUES (2,2,'tm','经理');
insert into data_dict(dict_parent_id,dict_index,dict_name,dict_value) VALUES (2,3,'conductor','售票员');

insert into data_dict(dict_parent_id,dict_index,dict_name,dict_value) VALUES (3,1,'Chinese','中文');
insert into data_dict(dict_parent_id,dict_index,dict_name,dict_value) VALUES (3,2,'English','英文');

insert into data_dict(dict_parent_id,dict_index,dict_name,dict_value) VALUES (4,1,'Comedy','喜剧');
insert into data_dict(dict_parent_id,dict_index,dict_name,dict_value) VALUES (4,2,'Adventure','冒险');
insert into data_dict(dict_parent_id,dict_index,dict_name,dict_value) VALUES (4,3,'Mystery','悬疑');
insert into data_dict(dict_parent_id,dict_index,dict_name,dict_value) VALUES (4,4,'Documentary','记录');

insert into `user`(user_identity,user_name,user_pass,user_tel_num,user_status) VALUES('管理员','606','606','15129******',0);
insert into `user`(user_identity,user_name,user_pass,user_tel_num,user_status) VALUES('经理','606','606','15129******',0);
insert into `user`(user_identity,user_name,user_pass,user_tel_num,user_status) VALUES('售票员','606','606','15129******',0);
/*
insert into studio(studio_name,studio_row_count,studio_col_count,studio_introduction) values('606',9,14,'专供测试用');
insert into play(play_type,play_language,play_name,play_introduction,play_duration) values(1,1,'三傻大闹宝莱坞','智慧的人生',170);

insert into schedule(studio_id,play_id,schedule_time,schedule_price,schedule_status)values(1,1,'2017-6-6',50,0);

*/

