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