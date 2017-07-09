select * from sysuser;

--下面命令应该使用scott的sysdba权限进行创建
SQL> show user;
--USER is "SYSTEM"

--查看所有的用户的默认表空间
select * from dba_users where ACCOUNT_STATUS = 'OPEN'
--查看指定用户的默认表空间
select DEFAULT_TABLESPACE,TEMPORARY_TABLESPACE from dba_users where username='SYSTEM';
select DEFAULT_TABLESPACE,TEMPORARY_TABLESPACE from dba_users where username='LEAF_PORTAL';
--查看已经创建的表空间信息
select * from dba_data_files
--创建表空间，耗时3s
create tablespace eva_zs datafile 'eva_zs.dbf' size 100m;
--删除表空间以及其内容
--(只是从数据库中删除，同时需要进入到第对磁盘文件进行删除：D:\APP\LIUBURU\PRODUCT\11.2.0\DBHOME_1\DATABASE\EVA_ZS.DBF)
 drop tablespace eva_zs including contents;
--设置表空间脱机或者联机状态 ONLINE|OFFLINE 以及可读可写状态[read only][read write];
 alter tablespace eva_zs ONLINE;
 alter tablespace eva_zs OFFLINE;
 alter tablespace eva_zs read only;
 alter tablespace eva_zs read write;  --等价于ONLINE模式状态
--查看表空的状态信息
select tablespace_name,status from dba_tablespaces where tablespace_name='EVA_ZS';

--创建用户
create user eva_zs identified by eva_zs;
--修改密码
alter user eva_zs identified by eva_zs;
--分配表空间
alter user eva_zs default tablespace EVA_ZS;
--为用户分配权限
grant create session,create table,create view,create sequence,unlimited tablespace to eva_zs;

--查看表空间使用率
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

--查看用户默认表空间
select DEFAULT_TABLESPACE,TEMPORARY_TABLESPACE from dba_users where username='EVA_ZS';

--若发现表空间不足，可以扩充表空间大小，方法一
select file_name,tablespace_name,bytes/1000000 file_size from dba_data_files where tablespace_name = 'EVA_ZS';
ALTER DATABASE DATAFILE 'D:\APP\LIUBURU\PRODUCT\11.2.0\DBHOME_1\DATABASE\EVA_ZS.DBF' RESIZE 100M;

--若发现表空间不足，可以扩充表空间大小，方法二
alter tablespace EVA_ZS add datafile 'eva_add.dbf' size 10m;
select file_name from dba_data_fileS where tablespace_name='EVA_ZS';



