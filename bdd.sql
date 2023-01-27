drop table Place;
drop table Categorie;
drop table Reservation;
---Categorie---
create table Categorie(
    Id varchar(1) primary key,
    Ndebut int,
    Nfin int
);

---Reservation---
create table Reservation(
    Id varchar(10),
    Currdate date,
    Heure varchar(20),
    Attente int
);

---Place---
create table Place(
    Id varchar(1),
    PlaceLibre int,
    PlacePrise int,
    Prix decimal(10,3),
    foreign key(Id) references Categorie(Id)
);
    

---Donnee de Test---

--Categorie--
insert into Categorie values ('A',1,10); 
insert into Categorie values ('B',11,20);
insert into Categorie values ('C',21,30);

--Place--

insert into Place values ('A',10,0,20000); 
insert into Place values ('B',10,0,15000);
insert into Place values ('C',10,0,8000);

commit;