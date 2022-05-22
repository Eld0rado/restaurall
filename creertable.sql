CREATE TABLE restaurant
(
    id      INTEGER PRIMARY KEY NOT NULL,
    nom     TEXT                NOT NULL,
    adresse TEXT,
    cp      INTEGER,
    ville   TEXT,
    type    TEXT                NOT NULL
);

CREATE TABLE plat
(
    idPlat  INTEGER PRIMARY KEY NOT NULL,
    idResto INTEGER,
    nom     TEXT                NOT NULL,
    prix    NUMERIC             NOT NULL,
    type    TEXT                NOT NULL,
    FOREIGN KEY (idResto) REFERENCES restaurant (id)
);

--Eventuellement quelques données mais des formulaires sur toutes les pages
INSERT INTO restaurant (id, nom, adresse, cp, ville, type)
VALUES (1, 'Mangez Vert', 'Rue de la Campagne', 57250, 'CAMPA', 'vegan');

INSERT INTO restaurant (id, nom, adresse, cp, ville, type)
VALUES (2, 'RestLille', 'Rue de lIsle', 59000, 'LILLE', 'Lillois');



