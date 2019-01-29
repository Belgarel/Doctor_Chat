create table DRC_UTILISATEUR  (
   NO_UTILISATEUR       NUMBER,
   LOGIN                VARCHAR2(20),
   PASSWORD             VARCHAR2(40),
   constraint DRC_UTILISATEUR primary key (NO_UTILISATEUR)
);

create table DRC_CONTACT (
    NO_UTILISATEUR_1    NUMBER,
    NO_UTILISATEUR_2    NUMBER,
    constraint DRC_CONTACT_pk primary key (NO_UTILISATEUR_1, NO_UTILISATEUR_2)
);

create table DRC_CONVERSATION (
    NO_CONVERSATION     NUMBER,
    constraint DRC_CONVERSATION primary key (NO_CONVERSATION)
);

create table DRC_PARTICIPE (
    NO_UTILISATEUR      NUMBER,
    NO_CONVERSATION     NUMBER,
    constraint DRC_PARTICIPE_pk primary key (NO_UTILISATEUR, NO_CONVERSATION)
);

create table DRC_MESSAGE (
    NO_MESSAGE          NUMBER,
    NO_CONVERSATION     NUMBER,
    NOM_FICHIER         VARCHAR(20),
    constraint DRC_MESSAGE_pk primary key (NO_MESSAGE, NO_CONVERSATION)
);

alter table DRC_CONTACT
   add constraint DRC_CONTACT_NO_UTIL1_fk foreign key (UTILISATEUR_1)
      references DRC_UTILISATEUR;
      
alter table DRC_CONTACT
   add constraint DRC_CONTACT_NO_UTIL2_fk foreign key (UTILISATEUR_2)
      references DRC_UTILISATEUR;

alter table DRC_PARTICIPE
   add constraint DRC_PARTICIPE_NO_UTIL_fk foreign key (NO_UTILISATEUR)
      references DRC_UTILISATEUR;
      
alter table DRC_PARTICIPE
   add constraint DRC_PARTICIPE_NO_CONVERS_fk foreign key (NO_CONVERSATION)
      references DRC_CONVERSATION;
          
alter table DRC_MESSAGE
   add constraint DRC_MESSAGE_NO_CONVERS_fk foreign key (NO_CONVERSATION)
      references DRC_CONVERSATION;

ALTER TABLE drc_utilisateur ADD CONSTRAINT un_login UNIQUE (login);

ALTER TABLE drc_contact
RENAME COLUMN no_utilisateur_1 TO no_utilisateur;

ALTER TABLE drc_contact
RENAME COLUMN no_utilisateur_2 TO no_contact;

COMMIT;

/*Ajout de colonnes dans la table Message*/
alter table drc_message add(
    NO_AUTHOR   NUMBER  NOT NULL,
    DATEPOSTED  DATE
);
alter table drc_message
   add constraint DRC_MESSAGE_NO_AUTHOR_fk foreign key (NO_AUTHOR)
      references DRC_UTILISATEUR;

/*Autoincrémentation des messages*/
CREATE SEQUENCE DRC_MESSAGE_PK_SEQ START WITH 1 INCREMENT BY 1 NOCACHE NOCYCLE;
create or replace trigger drc_message_pk_autoincr
before insert on drc_message for each row
begin
    select drc_message_pk_seq.nextval
    into :new.no_message
    from dual;
end;
/*Autoincrémentation des utilisateur*/
CREATE SEQUENCE DRC_UTILISATEUR_PK_SEQ START WITH 1 INCREMENT BY 1 NOCACHE NOCYCLE;
create or replace trigger drc_utilisateur_pk_autoincr
before insert on drc_utilisateur for each row
begin
    select drc_utilisateur_pk_seq.nextval
    into :new.no_utilisateur
    from dual;
end;

/*Insertion de valeurs*/

INSERT INTO drc_conversation VALUES (1);
INSERT INTO drc_conversation VALUES (2);
INSERT INTO drc_conversation VALUES (3);
INSERT INTO drc_conversation VALUES (4);
INSERT INTO drc_conversation VALUES (5);

INSERT INTO drc_participe VALUES (1, 1);
INSERT INTO drc_participe VALUES (1, 3);
INSERT INTO drc_participe VALUES (1, 5);

INSERT INTO drc_participe VALUES (2, 1);
INSERT INTO drc_participe VALUES (2, 3);
INSERT INTO drc_participe VALUES (2, 5);


INSERT INTO drc_participe VALUES (3, 1);
INSERT INTO drc_participe VALUES (5, 3);
INSERT INTO drc_participe VALUES (6, 5);

ALTER TABLE drc_message ADD message CLOB;

INSERT INTO DRC_MESSAGE (NO_CONVERSATION, NOM_FICHIER, MESSAGE, DATEPOSTED, NO_AUTHOR)
 VALUES (1, NULL, 'test message convers 1', TO_DATE('05/12/1992', 'DD/MM/YYYY'), 1);
INSERT INTO DRC_MESSAGE (NO_CONVERSATION, NOM_FICHIER, MESSAGE, DATEPOSTED, NO_AUTHOR)
 VALUES (3, NULL, 'test message convers 2', TO_DATE('27/10/2017', 'DD/MM/YYYY'), 3);
INSERT INTO DRC_MESSAGE (NO_CONVERSATION, NOM_FICHIER, MESSAGE, DATEPOSTED, NO_AUTHOR)
 VALUES (5, NULL, 'test message convers 3', TO_DATE('29/02/2000', 'DD/MM/YYYY'), 5);

COMMIT;