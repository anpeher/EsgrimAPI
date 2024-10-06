--DROP TABLE IF EXISTS flyway_schema_history;
DROP TABLE IF EXISTS Club CASCADE;
-- Creación de la tabla Club
CREATE TABLE Club (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(40) NOT NULL,
    direccion VARCHAR(100) NOT NULL,
    telefono VARCHAR(15) NOT null UNIQUE,
    ciudad VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT null UNIQUE
);


DROP TABLE IF EXISTS Clase CASCADE;
-- Creación de la tabla Clase
CREATE TABLE Clase (
    id SERIAL PRIMARY KEY,
    modalidad VARCHAR(10) NOT null,
    dias TEXT[] NOT NULL,
    horario_inicio TIME NOT null,
    horario_fin TIME NOT null,
    maestro INT

);

DROP TABLE IF EXISTS Usuarios CASCADE;
-- Creación de la tabla Usuarios
CREATE TABLE Usuarios (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(40) NOT NULL,
    apellidos VARCHAR(80) NOT NULL,
    fecha_nacimiento DATE NOT null,
    email VARCHAR(100) NOT null UNIQUE,
    contraseña VARCHAR(100) NOT NULL,
    genero VARCHAR(6) NOT NULL,
    telefono VARCHAR(15) NOT null UNIQUE,
    rol VARCHAR(20) NOT null,
    clasificacion smallint NOT null,
    activo BOOLEAN DEFAULT true,
    id_clase INT,
    id_club INT NOT NULL,
    FOREIGN KEY (id_clase) REFERENCES Clase(id)
    ON UPDATE CASCADE
    ON DELETE SET NULL,
    FOREIGN KEY (id_club) REFERENCES Club(id)
    ON UPDATE CASCADE
    ON DELETE RESTRICT
);

ALTER TABLE Clase
ADD CONSTRAINT fk_maestro FOREIGN KEY (maestro) REFERENCES Usuarios(id)
ON UPDATE CASCADE
ON DELETE SET NULL;


DROP TABLE IF EXISTS Inventario CASCADE;
-- Creación de la tabla Inventario
CREATE TABLE Inventario (
    id SERIAL PRIMARY KEY,
    nombre_objeto VARCHAR(100) NOT NULL,
    fabricante VARCHAR(100) NOT NULL,
    cantidad SMALLINT NOT NULL,
    id_club INT NOT NULL,
    FOREIGN KEY (id_club) REFERENCES Club(id)
    ON UPDATE CASCADE
    ON DELETE RESTRICT
);


DROP TABLE IF EXISTS Torneo CASCADE;
-- Creación de la tabla Torneo
CREATE TABLE Torneo (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(80) NOT NULL,
    fecha_inicio DATE NOT NULL,
    ubicacion VARCHAR(100) NOT NULL,
    estado VARCHAR(50) NOT NULL,
    modalidad VARCHAR(20) NOT NULL,
    edad SMALLINT NOT NULL,
    genero VARCHAR(6) NOT NULL,
    id_club INT NOT NULL,
    FOREIGN KEY (id_club) REFERENCES Club(id)
    ON UPDATE CASCADE
    ON DELETE RESTRICT
);


DROP TABLE IF EXISTS Poule CASCADE;
-- Creación de la tabla Poule
CREATE TABLE Poule (
    id SERIAL PRIMARY KEY,
    num_poule SMALLINT NOT NULL,
    num_participantes SMALLINT NOT NULL,
    eliminatoria BOOLEAN default FALSE,
    id_torneo INT NOT NULL,
    FOREIGN KEY (id_torneo) REFERENCES Torneo(id)
    ON UPDATE CASCADE
    ON DELETE RESTRICT
);


DROP TABLE IF EXISTS Participante CASCADE;
-- Creación de la tabla Participante
CREATE TABLE Participante (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(40) NOT NULL,
    apellidos VARCHAR(80) NOT NULL,
    ranking SMALLINT NOT NULL,
    descalificado BOOLEAN DEFAULT FALSE,
    id_usuario INT,
    id_torneo INT NOT NULL,
    id_poule INT DEFAULT NULL,
    FOREIGN KEY (id_usuario) REFERENCES Usuarios(id)
    ON UPDATE CASCADE
    ON DELETE SET NULL,
    FOREIGN KEY (id_torneo) REFERENCES Torneo(id)
    ON UPDATE CASCADE
    ON DELETE RESTRICT,
    FOREIGN KEY (id_poule) REFERENCES Poule(id)
    ON UPDATE CASCADE
    ON DELETE SET NULL
);


DROP TABLE IF EXISTS Enfrentamiento CASCADE;
-- Creación de la tabla Enfrentamiento
CREATE TABLE Enfrentamiento (
    id SERIAL PRIMARY KEY,
    resultado_tirador1 smallint NOT NULL,
    resultado_tirador2 smallint NOT NULL,
    id_tirador1 INT NOT NULL,
    id_tirador2 INT NOT NULL,
    id_poule INT NOT NULL,
    FOREIGN KEY (id_tirador1) REFERENCES Participante(id)
    ON UPDATE CASCADE
    ON DELETE RESTRICT,
    FOREIGN KEY (id_tirador2) REFERENCES Participante(id)
    ON UPDATE CASCADE
    ON DELETE RESTRICT,
    FOREIGN KEY (id_poule) REFERENCES Poule(id)
    ON UPDATE CASCADE
    ON DELETE RESTRICT
);


DROP TABLE IF EXISTS Resultado_Poule CASCADE;
-- Creación de la tabla Resultado_Poule
CREATE TABLE Resultado_Poule (
    id SERIAL PRIMARY KEY,
    victorias smallint NOT NULL,
    tocados_diferencia smallint NOT NULL,
    id_participante INT NOT NULL,
    id_poule INT NOT NULL,
    FOREIGN KEY (id_participante) REFERENCES Participante(id)
    ON UPDATE CASCADE
    ON DELETE CASCADE,
    FOREIGN KEY (id_poule) REFERENCES Poule(id)
    ON UPDATE CASCADE
    ON DELETE CASCADE
);


DROP TABLE IF EXISTS Directa CASCADE;
-- Creación de la tabla Directa
CREATE TABLE Directa (
    id SERIAL PRIMARY KEY,
    resultado_tirador1 smallint NOT NULL,
    resultado_tirador2 smallint NOT NULL,
    id_tirador1 INT NOT NULL,
    id_tirador2 INT NOT NULL,
    id_torneo INT NOT NULL,
    FOREIGN KEY (id_tirador1) REFERENCES Participante(id)
    ON UPDATE CASCADE
    ON DELETE RESTRICT,
    FOREIGN KEY (id_tirador2) REFERENCES Participante(id)
    ON UPDATE CASCADE
    ON DELETE RESTRICT,
    FOREIGN KEY (id_torneo) REFERENCES Torneo(id)
    ON UPDATE CASCADE
    ON DELETE RESTRICT
);


DROP TABLE IF EXISTS Chat CASCADE;
-- Creación de la tabla Chat
CREATE TABLE Chat (
    id SERIAL PRIMARY KEY,
    titulo VARCHAR(100) NOT NULL,
    fecha_creacion DATE NOT NULL,
    id_usuario_creador INT NOT NULL,
    id_usuario_participante INT NOT NULL,
    FOREIGN KEY (id_usuario_creador) REFERENCES Usuarios(id)
    ON UPDATE CASCADE
    ON DELETE CASCADE,
    FOREIGN KEY (id_usuario_participante) REFERENCES Usuarios(id)
    ON UPDATE CASCADE
    ON DELETE CASCADE
);


DROP TABLE IF EXISTS Mensaje CASCADE;
-- Creación de la tabla Mensaje
CREATE TABLE Mensaje (
    id SERIAL PRIMARY KEY,
    contenido TEXT NOT NULL,
    fecha_publicacion DATE NOT NULL,
    id_chat INT NOT NULL,
    id_usuario INT NOT NULL,
    FOREIGN KEY (id_chat) REFERENCES Chat(id)
    ON UPDATE CASCADE
    ON DELETE CASCADE,
    FOREIGN KEY (id_usuario) REFERENCES Usuarios(id)
    ON UPDATE CASCADE
    ON DELETE CASCADE
);


DROP TABLE IF EXISTS Notificacion CASCADE;
-- Creación de la tabla Notificación
CREATE TABLE Notificacion (
    id SERIAL PRIMARY KEY,
    contenido TEXT NOT NULL,
    titulo VARCHAR(100),
    fecha DATE,
    id_usuario INT,
    FOREIGN KEY (id_usuario) REFERENCES Usuarios(id)
    ON UPDATE CASCADE
    ON DELETE SET NULL
);