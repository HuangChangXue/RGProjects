create table if not exists sender_folder(
	id  VARCHAR_IGNORECASE(32) primary key,
	name  varchar2(16)not null,
	desc varchar2(64),
	parentid VARCHAR_IGNORECASE(32),
    reserved1 varchar2(64),
  	reserved2 varchar2(64),
  	reserved3 varchar2(64)
);

create table if not exists  sender_transaction (
	id  VARCHAR_IGNORECASE(32) primary key,
    name  varchar2(16)not null,
    desc varchar2(64),
    folderid VARCHAR_IGNORECASE(32),
    headerEditor varchar2(64),
	headerEditorConfigName VARCHAR_IGNORECASE(32),
    reserved1 varchar2(64),
  	reserved2 varchar2(64),
  	reserved3 varchar2(64)
);
create table if not exists sender_transaction_field(
	id  VARCHAR_IGNORECASE(32) primary key,
 	name  varchar2(16) not null,
	desc varchar2(64),
	type varchar2(16),
	src varchar2(32),
	transactionid  VARCHAR_IGNORECASE(32),

    reserved1 varchar2(64),
  	reserved2 varchar2(64),
  	reserved3 varchar2(64)
);
create table if not exists sender_dbconf(
	id  VARCHAR_IGNORECASE(32) primary key,
	name varchar2(16) not null , 
	url varchar2(64) not null, 
	user varchar2(32) not null,
	pass varchar2(32) not null, 
	driverclass varchar2(128) not null, 
	testsql  varchar2(256) not null
);
