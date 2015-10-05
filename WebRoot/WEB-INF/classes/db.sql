--不知道oracle密码，咋整？
sqlplus /nolog
connect / @orcl   as sysdba;
alter user system identified by root;

--创建空间 H:\jansonxu\tablespace
create tablespace MyOnlineBookStore 
 datafile 'H:\jansonxu\tablespace\MyOnlineBookStore.dbf'
 size 50M;
 
--创建用户
create user MyOnlineBookStore 
 identified by MyOnlineBookStore
 default tablespace MyOnlineBookStore;
 
--授予管理员的权限
grant dba to MyOnlineBookStore;

--测试事务
drop table  tb_trasaction;
create table tb_trasaction(
   id number(4) primary key,
   name varchar2(20)
);

-- 用户表：tb_User 
create table tb_User(
  id varchar2(40) primary key,
  name  varchar2(40) not null,  
  password  varchar2(40) not null,
  email  varchar2(40) not null
);


-- 图书表：tb_book
--drop table tb_book;
create table tb_book(
  id varchar2(40) primary key,
  name varchar2(40) not null,
  price number(5,2) not null,
  stock number(4,0) not null,
  image varchar2(30) not null
);




-- 订单表:tb_order
CREATE SEQUENCE  tb_order_seq  MINVALUE 10000 
  MAXVALUE 9999999
  INCREMENT BY 1 START WITH 10000 
  NOCACHE  NOORDER  NOCYCLE; 
  
 drop table tb_order;
 create table tb_order(
  id number(7,0) primary key,
  reciever varchar2(20) not null,
  price number(6,2) not null,
  orderTime Date default sysdate,
  userid varchar2(40),
  foreign key (userid) references tb_User(id),
  status char(1)--0:未发货；1：已完成
);

select * from tb_order where id = '10007';

update tb_order set ordertime=to_date('2014-02-1','yyyy-MM-dd') where id=10007;
update tb_order set ordertime=to_date('2014-02-28','yyyy-MM-dd') where id=10054;

select count(*) from tb_order where (ordertime between add_months(sysdate,-1) and sysdate ) and ;

--前一个月
3.1~3.31
2.1~2.28

select * from tb_order



select tb_order_seq.nextVal from dual;

-- 订单项表:tb_OrderItem
drop  table tb_OrderItem;
create table tb_OrderItem(
  id varchar2(40) primary key,
  booid  varchar2(40) ,
  amount number(5,0) not null,
  price number(7,2) not null,
  orderid number(7,0) ,
  foreign key (booid) references tb_book(id),
  foreign key (orderid) references tb_order(id)
);


--分页查询sql
pageSize:3 ---常量
currentPage:1 --页面传递的
                               
startIndex   1      4     7--》查询开始索引
endIndex     3      6     9-->查询结束索引 
currentPage  1      2     3


startIndex=(currentPage-1)*pageSize+1
endIndex = startIndex+pageSize-1

rownum:总是从1开始
select rownum ,b.* from tb_book b where rownum<=3 and rownum<=3
select rownum ,b.* from tb_book b where  rownum>2

rownum>2伪列总是从1开始

curreentpage:
1

endIndex = (currentpage-1)*pageSize+1

2


select count(*) from tb_book;

select * from tb_book b where name like '%钱%' 

 select * from 
  (select rownum r,b.* from tb_book b where b.name like '%钱%' and rownum <=3 ) 
 where r >=1; 
 
 
 select * from  (select rownum r,b.* from tb_book b where b.name like '%钱%' and rownum <=3 )
 where r >=1; 
 
 
 
select * from (select rownum r,b.* from tb_book b where rownum <=?) where r >=?

百度分页逻辑：

pageBarSize:10

startPageNum     endPageNum   currentPage
1                     10         1
1                     10         2
1                     10         3
1                     10         4
1                     10         5
1                     10         6
-->
3                     12         8



if(currentPage<=pageBarSize/2+1){
}

startPageNum = currentPage-pageBarSize/2
endPageNum = currentPage+ (pageBarSize/2-1)

  
  
--根据id查询图书

select * from tb_book where id in('8fecd962-2227-4d26-a3a7-954774f0000c','f7ad3476-eb26-4a11-a9c5-1ed2c86292fa');

select * from tb_book where id in('8fecd962-2227-4d26-a3a7-954774f0000c','f7ad3476-eb26-4a11-a9c5-1ed2c86292fa')  