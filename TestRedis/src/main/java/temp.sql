select * from tbman

select  from dual

insert into tbman (myDate,info) values (sysdate,'1233')

select seq_tbman.nextval from dual

drop table tbman

create table tbman(
       myId number(4) primary key,
       myDate date,
       info varchar2(20)
)

alter sequence seq_tbman increment by 1