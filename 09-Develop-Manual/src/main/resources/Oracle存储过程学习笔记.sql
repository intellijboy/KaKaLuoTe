----------------------------------һ��������Ϣ��ѯBegin----------------------------------------------------
select count(1) from user_tables;--1879

select * from user_views;
select count(1) from user_views;--755

select * from user_sequences;
select count(1) from user_sequences;--1238

select * from user_triggers;
select count(1) from user_triggers;--131
----------------------------------һ��������Ϣ��ѯend----------------------------------------------------




----------------------------------�����洢���̲���Begin----------------------------------------------------
--1�������������ݣ�ʹ��OracleĬ�ϵ�scott�û�������emp��

--2��.Hello World(����ͨ����Ա��ţ��޸Ĺ�Ա������)
           --2.1)�����ű�����β���Ҫָ�����ȣ�
            create or replace procedure emp_update_by_empno(eno in NUMBER, update_name in VARCHAR2) as
            begin
              update emp set ename = update_name where empno = eno;
            end emp_update_by_empno;
           --2.2)���ô洢����
            --�����е���
            exec emp_update_by_empno(7369,'SMITH_000');
            --���̵���
            set serveroutput on;  --û��仰��������dmbs_output��Ϣ��
            
            declare
                   eno number(4):=7369;
                   update_name varchar2(10):='default';
                   result_name varchar2(10);
            begin
                emp_update_by_empno(eno,update_name => 'SMITH_111');
                commit;--�ڵ��ô����������ύ
                select ename into result_name from emp where empno = eno;
                dbms_output.put_line( '�޸ĺ�����:' || result_name );
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
               DBMS_OUTPUT.PUT_LINE('û���ҵ�empno='||input_empno||'��Ա��'); 
            END;







----------------------------------�����洢���̲���Begin----------------------------------------------------

