----------------------------------一、基本信息查询Begin----------------------------------------------------
select count(1) from user_tables;--1879

select * from user_views;
select count(1) from user_views;--755

select * from user_sequences;
select count(1) from user_sequences;--1238

select * from user_triggers;
select count(1) from user_triggers;--131
----------------------------------一、基本信息查询end----------------------------------------------------




----------------------------------二、存储过程测试Begin----------------------------------------------------
--1）创建测试数据（使用Oracle默认的scott用户的呃的emp表）

--2）.Hello World(需求：通过雇员编号，修改雇员的姓名)
           --2.1)创建脚本（入参不需要指明长度）
            create or replace procedure emp_update_by_empno(eno in NUMBER, update_name in VARCHAR2) as
            begin
              update emp set ename = update_name where empno = eno;
            end emp_update_by_empno;
           --2.2)调用存储过程
            --命令行调用
            exec emp_update_by_empno(7369,'SMITH_000');
            --过程调用
            set serveroutput on;  --没这句话，看不到dmbs_output信息。
            
            declare
                   eno number(4):=7369;
                   update_name varchar2(10):='default';
                   result_name varchar2(10);
            begin
                emp_update_by_empno(eno,update_name => 'SMITH_111');
                commit;--在调用处进行事务提交
                select ename into result_name from emp where empno = eno;
                dbms_output.put_line( '修改后姓名:' || result_name );
            end;
            
            
            DECLARE VAR_ONE VARCHAR2(50) := 'HEllo World~~';  
            BEGIN  
              DBMS_OUTPUT.PUT_LINE(VAR_ONE);  
            END;
            
            
            DECLARE 
            input_empno number(4):=7369;
            result_name VARCHAR2(10) := 'defalut'; 
            BEGIN  
              SELECT ename INTO result_name FROM emp WHERE empno = input_empno; 
              DBMS_OUTPUT.PUT_LINE(result_name); 
              EXCEPTION
              WHEN NO_DATA_FOUND
              THEN
               DBMS_OUTPUT.PUT_LINE('没有找到empno='||input_empno||'的员工'); 
            END;







----------------------------------二、存储过程测试Begin----------------------------------------------------

