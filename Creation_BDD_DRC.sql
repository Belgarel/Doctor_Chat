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

/*Setting up the primary key for drc_utilisateur to auto increment*/
create sequence drc_utilisateur_pk_seq start with 1;
create or replace trigger drc_utilisateur_pk_autoincr
before insert on drc_utilisateur for each row
begin
    select drc_utilisateur_pk_seq.nextval
    into :new.no_utilisateur
    from dual;
end;

/*Inserting values into DRC_UTILISATEUR*/
insert into drc_utilisateur (login, password)
values ('test', '123');
insert into drc_utilisateur (login, password)
values ('victor hugo', '123');
insert into drc_utilisateur (login, password)
values ('gustave flaubert', '123');
insert into drc_utilisateur (login, password)
values ('jules verne', '123');
insert into drc_utilisateur (login, password)
values ('marcel proust', '123');
insert into drc_utilisateur (login, password)
values ('stanislas lem', '123');

/*Inserting values into DRC_CONTACT*/
insert into drc_contact
values (1,6);
insert into drc_contact
values (2,6);
insert into drc_contact
values (3,6);
insert into drc_contact
values (4,6);
insert into drc_contact
values (5,6);
insert into drc_contact
values (1,2);
insert into drc_contact
values (3,2);
insert into drc_contact
values (4,2);
insert into drc_contact
values (5,2);
