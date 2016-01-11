# this application can be understood as a translation tool for tracking your progress 
# in learning languages and also can be used as your own translation tool 
# of course it would be goot if it can be used to connect it to google translate
# and provide explanation which could be entered instantly into your database
# However, major use of this application is to keep track of your progress in learning all your 
# languages and to provide regular recapitulation of your words 
# Of course it could be extended by some nosql system for archiving articles and for audios and
# other material during your process of learning.
# Maybe the best way for making some entailments is to use link from explanation to words.
############################################################################################

alter database language default character set utf8 default collate utf8_general_ci;
ALTER TABLE language CONVERT TO CHARACTER SET utf8 COLLATE utf8_general_ci;
alter table word convert to character set utf8 collate utf8_general_ci;
alter table wordlink convert to character set utf8 collate utf8_general_ci;
alter table explanation convert to character set utf8 collate utf8_general_ci;
alter table wordtype convert to character set utf8 collate utf8_general_ci;

insert into language(type) values ("Српски");

insert into word (word,time_creation,time_modification,language_id)
values ("",NOW(),NOW(),1);
  
insert into wordlink (word_from, word_to,link_type) values (1, 1, "synonym")

insert into explanation (text, language_id) values ("",4);
insert into explanation (text, language_id, word_id) values ("",4,);
insert into explanation (text, language_id, word_id, wordtype_id) values ("", 4, , 

insert into wordtype (text) values ("masculine"); -- masculine 2

select * from word where language_id =3;
select * from word where language_id = 3 and word_type_id =2;
select last_insert_id();


DELIMITER //
CREATE PROCEDURE insertw(IN word1 VARCHAR(50), 
IN lan VARCHAR(30), IN exp VARCHAR(500), 
IN lan_exp INTEGER, IN word_type INTEGER, 
IN word2 VARCHAR(50), 
IN lan2 VARCHAR(30))
BEGIN
  DECLARE wordqid bigint;
  DECLARE mean1id bigint;
  DECLARE word2id bigint;
  DECLARE mean2id bigint;
  DECLARE lan2id bigint;

  DECLARE exit handler for sqlexception
  BEGIN
    -- ERROR
  ROLLBACK;
  END;

DECLARE exit handler for sqlwarning
 BEGIN
    -- WARNING
 ROLLBACK;
END;

START TRANSACTION;
  insert into word (word,time_creation,language_id)
  values (word1,NOW(),lan);

  SET wordqid = last_insert_id(); 
  insert into meaning (explanation, language_id, word,  wordtype_id) 
  values (exp, lan_exp, wordqid, word_type); 
  SET mean1id = last_insert_id(); 
  
  SET lan2id = (select id from language where type = lan2);
  IF lan2id is not NULL THEN
   BEGIN
        insert into word (word,time_creation,language_id)
        values (word2,NOW(),lan2id);

        SET word2id = last_insert_id(); 
        insert into meaning (explanation, language_id, word,  wordtype_id) 
        values ("", lan2id, word2id, NULL);
        SET mean2id = last_insert_id(); 
  
        insert into wordlink (meaning_from, meaning_to, link_type ) 
        values (mean1id, mean2id, "synonym");
   END; 
 END IF;
COMMIT;
END //
DELIMITER ;

call insertw ("eigentlich", 3, "adverb", 4, 5, "actual", "English");
