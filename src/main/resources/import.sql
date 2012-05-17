insert into status (value, reference_index, remaining) values ("free", "B1", 1);
insert into status (value, reference_index, remaining) values ("associated", "B1", 2);
insert into status (value, reference_index, remaining) values ("active", "B1", 3);

insert into user (username, status_id) values ("user", 1);