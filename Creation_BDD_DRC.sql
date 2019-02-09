DROP TABLE DRC_PARTICIPE CASCADE CONSTRAINTS;
DROP TABLE DRC_CONTACT CASCADE CONSTRAINTS;
DROP TABLE DRC_UTILISATEUR  CASCADE CONSTRAINTS;
DROP TABLE DRC_MESSAGE CASCADE CONSTRAINTS;
DROP TABLE DRC_CONVERSATION CASCADE CONSTRAINTS;

create table DRC_UTILISATEUR  (
   NO_UTILISATEUR       NUMBER,
   LOGIN                VARCHAR2(20),
   PASSWORD             VARCHAR2(40),
   constraint DRC_UTILISATEUR_PK primary key (NO_UTILISATEUR),
   constraint DRC_UTILISATEUR_UN unique (LOGIN)
);

create table DRC_CONTACT (
    NO_UTILISATEUR    NUMBER,
    NO_CONTACT        NUMBER,
    constraint DRC_CONTACT_PK primary key (NO_UTILISATEUR, NO_CONTACT)
);

create table DRC_CONVERSATION (
    NO_CONVERSATION   NUMBER,
    constraint DRC_CONVERSATION_PK primary key (NO_CONVERSATION)
);

create table DRC_PARTICIPE (
    NO_UTILISATEUR      NUMBER,
    NO_CONVERSATION     NUMBER,
    constraint DRC_PARTICIPE_PK primary key (NO_UTILISATEUR, NO_CONVERSATION)
);

create table DRC_MESSAGE (
    NO_MESSAGE          NUMBER,
    NO_CONVERSATION     NUMBER,
    NOM_FICHIER         VARCHAR(20),
    MESSAGE             CLOB,
    NO_AUTHOR           NUMBER,
    DATEPOSTED          DATE,
    constraint DRC_MESSAGE_PK primary key (NO_MESSAGE, NO_CONVERSATION),
    constraint DRC_MESSAGE_NN CHECK (NO_AUTHOR IS NOT NULL)
);

alter table DRC_CONTACT
   add constraint DRC_CONTACT_NO_UTIL_FK foreign key (NO_UTILISATEUR)
      references DRC_UTILISATEUR;
      
alter table DRC_CONTACT
   add constraint DRC_CONTACT_NO_CONT_FK foreign key (NO_CONTACT)
      references DRC_UTILISATEUR;

alter table DRC_PARTICIPE
   add constraint DRC_PARTICIPE_NO_UTIL_FK foreign key (NO_UTILISATEUR)
      references DRC_UTILISATEUR;
      
alter table DRC_PARTICIPE
   add constraint DRC_PARTICIPE_NO_CONVERS_FK foreign key (NO_CONVERSATION)
      references DRC_CONVERSATION;
          
alter table DRC_MESSAGE
   add constraint DRC_MESSAGE_NO_CONVERS_FK foreign key (NO_CONVERSATION)
      references DRC_CONVERSATION;