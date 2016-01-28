create table language(
   id bigint not null auto_increment,
   type varchar(50) not null,
   primary key (id),
   unique (type)
);


create table word (
   id bigint not null auto_increment,
   word varchar(100) not null,
   time_creation DATETIME not null,
   language_id bigint not null,
   primary key (id),
   constraint fk_word_language foreign key (language_id) references language (id)   
);
  
create table meaning (
    id bigint not null auto_increment,
    explanation varchar(1000),
    language_id bigint not null,
    word bigint not null,
    primary key (id),
    constraint fk_meaning_language foreign key (language_id) references language (id),
    constraint fk_meaning_word foreign key (word) references word(id)
); 
  
create table wordlink (
	id bigint not null auto_increment,
    meaning_from bigint not null,
    meaning_to bigint not null,
    link_type bigint not null,
    primary key (id),
    unique key unique_wordlink(meaning_to, meaning_from, link_type),
    constraint fk_wordlink_meaning_from foreign key (meaning_from) references meaning (id),
    constraint fk_wordlink_meaning_to foreign key (meaning_to) references meaning (id),
    constraint fk_wordlink_linktype foreign key (link_type) references linktype (id)
);



create table wordusage (
    id bigint not null auto_increment,
    text varchar(500) not null,
    primary key (id)
);

create table wordtype(
    id bigint not null auto_increment, 
    text varchar(70),
    primary key (id)
);

create table linktype(
    id bigint not null auto_increment, 
    name varchar(70),
    primary key (id)
);

create table wordusage_type(
    id bigint not null auto_increment, 
    name varchar(70),
    primary key (id)
);

create table meaning_wordtype(
	meaning_id bigint not null,
	wordtype_id bigint not null,
	primary key (meaning_id, wordtype_id),
	constraint fk_meaning_wordtype_meaning foreign key (meaning_id) references meaning (id),
    constraint fk_meaning_wordtype_wordtype foreign key (wordtype_id) references  wordtype (id)
);

create table meaning_wordusage(
	meaning_id bigint not null,
	wordusage_id bigint not null,
	primary key (meaning_id, wordusage_id),
	constraint fk_meaning_wordusage_meaning foreign key (meaning_id) references meaning (id),
    constraint fk_meaning_wordusage_wordusage foreign key (wordusage_id) references  wordusage (id)
);

create table wordusage_wordusagetype(
	wordusage_id bigint not null,
	wordusagetype_id bigint not null,
	primary key (wordusage_id, wordusagetype_id),
	constraint fk_wordusage_wordusagetype_wordusage foreign key (wordusage_id) references wordusage (id),
    constraint fk_wordusage_wordusagetype_wordusagetype foreign key (wordusagetype_id) references  wordusage_type (id)
);

create table log_word (
   id bigint not null auto_increment, 
   time_visit DATETIME not null,
   word_id bigint not null,
   active int,
   primary key (id),
   constraint fk_log_word_word foreign key (word_id) references word (id),
   unique key uq_time_word (word_id, time_visit)
);