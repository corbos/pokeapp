drop database if exists poke;

create database poke;

use poke;

create table poke_type (
    poke_type_id int primary key auto_increment,
    `name` varchar(50) not null,
    constraint uq_poketype_name unique (`name`)
);

create table pokemon (
    dex_number int primary key,
    `name` varchar(50) not null,
    poke_type_id int not null,
    constraint fk_pokemon_poke_type_id
		foreign key (poke_type_id) references poke_type(poke_type_id),
    constraint uq_pokenmon_name unique (`name`)
);

create table collector (
    collector_id int primary key auto_increment,
    first_name varchar(50) not null,
    last_name varchar(50) not null
);

create table collector_pokemon (
    dex_number int not null,
    collector_id int not null,
    constraint pk_collector_pokemon
        primary key (dex_number, collector_id),
    constraint fk_collector_pokemon_dex_number
        foreign key (dex_number) references pokemon(dex_number),
    constraint fk_collector_pokemon_collector_id
        foreign key (collector_id) references collector(collector_id)
);

insert into poke_type (poke_type_id, `name`) values
    (1, 'Normal'),
    (2, 'Fighting'),
    (3, 'Flying'),
    (4, 'Poison'),
    (5, 'Ground'),
    (6, 'Rock'),
    (7, 'Bug'),
    (8, 'Ghost'),
    (9, 'Steel'),
    (10, 'Fire'),
    (11, 'Water'),
    (12, 'Grass'),
    (13, 'Electric'),
    (14, 'Psychic'),
    (15, 'Ice'),
    (16, 'Dragon'),
    (17, 'Dark'),
    (18, 'Fairy');
    
insert into pokemon (dex_number, `name`, poke_type_id)
	values
    (1, 'Bulbasaur', 12),
    (2, 'Ivysaur', 12),
    (3, 'Venusaur', 12),
    (4, 'Charmander', 10),
    (5, 'Charmeleon', 10),
    (6, 'Charizard', 10),
    (7, 'Squirtle', 11),
    (8, 'Wartortle', 11),
    (9, 'Blastoise', 11),
    (10, 'Caterpie', 7);
    
    