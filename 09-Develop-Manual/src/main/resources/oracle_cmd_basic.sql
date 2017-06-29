--1.表结构
desc emp;

--2.使用别名
select ename empName from emp;

--3.格式化时间
select empno,ename,to_char(HIREDATE,'yyyy-MM-dd') from emp;

--4.distinct的用法
select distinct deptno from emp;

--5.nvl函数的使用(第一个参数为空那么显示第二个参数的值，如果第一个参数的值不为空，则显示第一个参数本来的值)
select emp.*,nvl(comm,0) from emp emp;

--6.使用字符串连接符号
select ename||'的工资有'||sal from emp where empno = '7369';
--结果：SMITH的工资有800

--7.使用between、where、like、in、and、or not、order by
 select * from emp where sal between 800 and 1300 and ename like 'M%' and deptno in(10,30);--闭区间
 select * from emp order by deptno asc,empno desc;--按照deptno升序并且按照empno降序

 --8.多表连接查询（左外连接，右外连接，测试内连接，全连接，自然连接，嵌套查询）
 --8.1左外连接
 select d.*,e.* from dept d left join emp e on d.deptno = e.deptno;
 --8.2右外连接
 select d.*,e.* from emp e right join dept d on d.deptno = e.deptno;
--8.3内连接
 select d.*,e.* from emp e inner join dept d on d.deptno = e.deptno;
--8.4全连接
 select d.*,e.* from emp e full join dept d on d.deptno = e.deptno;
--8.5 自然查询
 select *
 from emp e,dept d
 where e.deptno = d.deptno;
 --8.6嵌套查询
select e.* from emp e where e.deptno in (select deptno from dept);

 --9.常用的系统函数
select ABS(-15) "绝对值" from dual;
SELECT CEIL(15.7) "Ceiling" FROM DUAL;
SELECT MOD(11,4) "Modulus" FROM DUAL;
SELECT ROUND(15.193,1) "Round" FROM DUAL; --四舍五入，精确小数点，若为-1表示整数取舍


--10.序列相关操作
CREATE SEQUENCE emp_sequence  --序列名
INCREMENT BY 1   -- 每次加几个
START WITH 1000       -- 从1开始计数
NOMAXVALUE        -- 不设置最大值
NOCYCLE;               -- 一直累加，不循环
select * from user_sequences;
select emp_sequence.currval from dual;
select emp_sequence.nextval from dual;


--11.Oracle用户与表空间
-- 优秀博文：
-- http://blog.csdn.net/jmilk/article/details/51599260
-- http://jingyan.baidu.com/article/86112f137785232737978736.html

--11.1 登陆system用户和登陆sys（该用户需要sysdba获取sysoper权限）用户
system/123456
connect sys/123456 as sysdba
sys

--11.2 查看system用户的默认表空间和临时表空间（dba_users,user_users）
select * from dba_users where ACCOUNT_STATUS = 'OPEN'
/*系统用户dba_users*/
SQL> select DEFAULT_TABLESPACE,TEMPORARY_TABLESPACE from dba_users where username='SYSTEM';
DEFAULT_TABLESPACE         TEMPORARY_TABLESPACE
------------------------------ ------------------------------
SYSTEM                      TEMP
/*普通用户user_users*/
SQL> SELECT USERNAME, TEMPORARY_TABLESPACE FROM DBA_USERS

--11.3创建表空间
SQL> show user;
USER is "SYSTEM"
SQL> create tablespace test1_tablespace datafile 'test1file.dbf' size 10m;
Tablespace created.
SQL> create temporary tablespace temptest1_tablespace tempfile 'tempfile.dbf' size 10m;
Tablespace created.

--11.4 查看表空间（dba_data_files）
SQL> select file_name from dba_data_files where tablespace_name='TEST1_TABLESPACE';
FILE_NAME
--------------------------------------------------------------------------------
/u01/oracle/dbs/test1file.dbf

--11.5 修改表空间
ALTER USER username defalut|temporary TABLESPACE tablespace_name;
SQL> show user;
USER is "SYSTEM"
SQL> select DEFAULT_TABLESPACE,TEMPORARY_TABLESPACE from dba_users where username='SYSTEM';
DEFAULT_TABLESPACE         TEMPORARY_TABLESPACE
------------------------------ ------------------------------
USERS               TEMP

--11.6 设置表空间的联机或脱机状态
ALTER TABLESPACE tablespace_name ONLINE|OFFLINE;
SQL> alter tablespace test1_tablespace offline;
Tablespace altered.
SQL> select STATUS from dba_tablespaces where tablespace_name='TEST1_TABLESPACE';
STATUS
---------
OFFLINE

--11.7 设置表空间的只读|可读写状态
ALTER TABLESPACE tablespace_name [read only][read write];
SQL> alter tablespace test1_tablespace read only;
Tablespace altered.
SQL> select STATUS from dba_tablespaces where tablespace_name='TEST1_TABLESPACE';
STATUS
---------
READ ONLY

--11.8 增加表空间数据文件
ALTER TABLESPACE tablespace_name ADD DATAFILE'xx.dbf' SIZE 10m;
SQL> alter tablespace test1_tablespace add datafile 'test2_file.dbf' size 10m;
Tablespace altered.
  SQL> select file_name from dba_data_fileS where tablespace_name='TEST1_TABLESPACE';
FILE_NAME
--------------------------------------------------------------------------------
/u01/oracle/dbs/test1file.dbf
/u01/oracle/dbs/test2_file.dbf

--11.9调整数据文件的大小
ALTER DATABASE DATAFILE '/database/oracle/oradata/gsp/tbs_dm_data_002.dbf' RESIZE 500M;
SQL> alter tablespace test1_tablespace drop datafile 'test2_file.dbf';
Tablespace altered.
SQL> select file_name from dba_data_fileS where tablespace_name='TEST1_TABLESPACE';
FILE_NAME
--------------------------------------------------------------------------------
/u01/oracle/dbs/test1file.dbf

--11.10 删除表空间
DROP TABLESPAC tablespace_name [INCLUDING CONTENTS];
SQL> drop tablespace test1_tablespace including contents;
Tablespace dropped.
SQL> select file_name from dba_data_fileS where tablespace_name='TEST1_TABLESPACE';
no rows selected

--11.11 笔记
/*
１　oracle的数据模式是：用户建在表空间上，表建在用户上
２　一个用户的表就象自己的私有财产一样，没有自己或管理员授权别的用户是不能查询或修改的；
３　对于不同用户下的同名表，都是独立的数据对象，如user1.table1和user2.table1是相互独立的，用户分别操作
自己的表是不影响其他用户的；
４　对于同一用户下的同一个表，所有有权限的用户对其进行数据操作时，是会相互影响的，如对user1.table1来说，
user1修改了它的一行，user２又对该表的该行进行了修改，那么该表的该行的实际内容是在user１修改后基础上user2
修改的结果（注意，所有修改以最后成功提交修改请求的用户的内容为准），多个用户对同一个用户下的同一个表的同
时修改和锁定会造成锁等待
*/


--11.12 创建用户
--1.首先我们可以用scott用户以sysdba的身份登录oracle.
conn scott/tiger as sysdba
--2.然后我就可以来创建用户了.
create user zs identified by zs;
--3.修改用户的密码.
alter user zs identified by 123456;
--4.创建一个表空间.
create tablespace zs_zs datafile 'f:\zs_zs.dbf' size 200M;
--5.创建好表空间,还需要将表空间分配给用户.
alter user zs default tablespace zs_zs;
--6.给用户分配了表空间,用户还不能登陆（没有登录权限）,因此还需要为用户分配权限
grant create session,create table,create view,create sequence,unlimited tablespace to zs;

--11.23 表空间使用率查看
--方法1
SELECT total.tablespace_name,
       Round(total.MB, 2)           AS Total_MB,
       Round(total.MB - free.MB, 2) AS Used_MB,
       Round(( 1 - free.MB / total.MB ) * 100, 2)
       || '%'                       AS Used_Pct
FROM   (SELECT tablespace_name,
               Sum(bytes) / 1024 / 1024 AS MB
        FROM   dba_free_space
        GROUP  BY tablespace_name) free,
       (SELECT tablespace_name,
               Sum(bytes) / 1024 / 1024 AS MB
        FROM   dba_data_files
        GROUP  BY tablespace_name) total
WHERE  free.tablespace_name = total.tablespace_name;
--方法二
SELECT TABLESPACE_NAME "表空间",
       To_char(Round(BYTES / 1024, 2), '99990.00')
       || ''           "实有",
       To_char(Round(FREE / 1024, 2), '99990.00')
       || 'G'          "现有",
       To_char(Round(( BYTES - FREE ) / 1024, 2), '99990.00')
       || 'G'          "使用",
       To_char(Round(10000 * USED / BYTES) / 100, '99990.00')
       || '%'          "比例"
FROM   (SELECT A.TABLESPACE_NAME                             TABLESPACE_NAME,
               Floor(A.BYTES / ( 1024 * 1024 ))              BYTES,
               Floor(B.FREE / ( 1024 * 1024 ))               FREE,
               Floor(( A.BYTES - B.FREE ) / ( 1024 * 1024 )) USED
        FROM   (SELECT TABLESPACE_NAME TABLESPACE_NAME,
                       Sum(BYTES)      BYTES
                FROM   DBA_DATA_FILES
                GROUP  BY TABLESPACE_NAME) A,
               (SELECT TABLESPACE_NAME TABLESPACE_NAME,
                       Sum(BYTES)      FREE
                FROM   DBA_FREE_SPACE
                GROUP  BY TABLESPACE_NAME) B
        WHERE  A.TABLESPACE_NAME = B.TABLESPACE_NAME)
--WHERE TABLESPACE_NAME LIKE 'CDR%' --这一句用于指定表空间名称
ORDER  BY Floor(10000 * USED / BYTES) DESC;
