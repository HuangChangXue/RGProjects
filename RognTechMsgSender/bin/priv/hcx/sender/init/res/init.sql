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
	name varchar2(16) not null unique , 
	url varchar2(64) not null, 
	user varchar2(32) not null,
	pass varchar2(32) not null, 
	driverclass varchar2(128) not null, 
	testsql  varchar2(256) not null
);



create table if not exists server_conf(
	id  VARCHAR_IGNORECASE(32) primary key,
	name varchar2(16) not null unique , 
	host varchar2(64) not null, 
	port varchar2(32) not null,
	protel varchar2(32) not null, 
	encoder varchar2(128) not null, 
	decoder  varchar2(256) not null
);


create table if not exists Field_const_conf(
	 id  VARCHAR_IGNORECASE(32) primary key,
	 fieldID  VARCHAR_IGNORECASE(32) not null,
	 value varchar2(128) ,
	isAllowEmpty boolean ,
	isAllowNewLine boolean
	);

	
create table if not exists  Field_Random_conf (
		id  VARCHAR_IGNORECASE(32) primary key,
		fieldID  VARCHAR_IGNORECASE(32) not null,
		containNum boolean ,
		containCharCap boolean,
		containCharLow boolean,
		containCharOther boolean, 
		numLen varchar2(5),
		charCapLen varchar2(5),
		charLowLen varchar2(5),
		charOtherLen varchar2(5)
)	;

