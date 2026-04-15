INSERT INTO stadium (id, name, city, capacity) VALUES
    (nextval('stadium_seq'), 'Morumbi', 'São Paulo', 66000),
    (nextval('stadium_seq'), 'Allianz Parque', 'São Paulo', 43000),
    (nextval('stadium_seq'), 'Neo Química Arena', 'São Paulo', 49000),
    (nextval('stadium_seq'), 'Vila Belmiro', 'Santos', 16000),
    (nextval('stadium_seq'), 'Estádio Brinco de Ouro', 'Campinas', 29000),
    (nextval('stadium_seq'), 'Estádio Moisés Lucarelli', 'Campinas', 19000);

INSERT INTO club (id, name, founded, stadium_id, created_at, active) VALUES
    (nextval('club_seq'), 'São Paulo Futebol Clube', '1930-01-25', 1, now(), true),
    (nextval('club_seq'), 'Sociedade Esportiva Palmeiras', '1914-08-26', 2, now(), true),
    (nextval('club_seq'), 'Sport Club Corinthians Paulista', '1910-09-01', 3, now(), true),
    (nextval('club_seq'), 'Santos Futebol Clube', '1912-04-14', 4, now(), true),
    (nextval('club_seq'), 'Guarani Futebol Clube', '1911-04-02', 5, now(), true),
    (nextval('club_seq'), 'Associação Atlética Ponte Preta', '1900-08-11', 6, now(), true);