-- Inserción de datos en la tabla Club
INSERT INTO Club (nombre, direccion, telefono, ciudad, email) VALUES
('Club Esgrima Murcia', 'c/ Auditorium, 5, 30008 Murcia - España', '722150766', 'Murcia', 'federacion@esgrimamurcia.com');

-- Inserción de datos en la tabla Clase
INSERT INTO Clase (modalidad, dias, horario_inicio, horario_fin, maestro) VALUES
('ESPADA', ARRAY['Lunes', 'Miercoles'], '18:00', '20:00', NULL),
('FLORETE', ARRAY['Martes', 'Jueves'], '19:00', '21:00', NULL),
('ESPADA', ARRAY['Viernes'], '18:30', '21:00', NULL);


-- Inserción de datos en la tabla Usuarios
-- Usuarios con rol 'Maestro'
INSERT INTO Usuarios (nombre, apellidos, fecha_nacimiento, email, contraseña, genero, telefono, rol, clasificacion, id_clase, id_club) VALUES
('Carlos', 'García López', '1980-05-15', 'carlos.garcia@clubesgrimamadrid.com', 'password123', 'Hombre', '600111111', 'Maestro', 100, 1, 1),
('María', 'Pérez Sánchez', '1985-08-22', 'maria.perez@clubesgrimabarcelona.com', 'password123', 'Mujer', '600222222', 'Maestro', 100, 2, 1),
('Luis', 'Martínez Fernández', '1975-03-30', 'luis.martinez@clubesgrimamadrid.com', 'password123', 'Hombre', '600333333', 'Maestro', 100, 3, 1);

-- Usuarios con rol 'Alumno'
INSERT INTO Usuarios (nombre, apellidos, fecha_nacimiento, email, contraseña, genero, telefono, rol, clasificacion, id_clase, id_club) VALUES
('Ana', 'López Gómez', '1995-12-10', 'ana.lopez@gmail.com', 'password123', 'Mujer', '600444444', 'Alumno', 500, 1, 1),
('David', 'Sánchez Ruiz', '1998-07-05', 'david.sanchez@gmail.com', 'password123', 'Hombre', '600555555', 'Alumno', 400, 1, 1),
('Elena', 'Hernández Díaz', '2000-01-20', 'elena.hernandez@gmail.com', 'password123', 'Mujer', '600666666', 'Alumno', 300, 2, 1),
('Fernando', 'Jiménez Morales', '1992-11-11', 'fernando.jimenez@gmail.com', 'password123', 'Hombre', '600777777', 'Alumno', 600, 2, 1),
('Isabel', 'Romero Álvarez', '1997-09-18', 'isabel.romero@gmail.com', 'password123', 'Mujer', '600888888', 'Alumno', 200, 3, 1),
('Javier', 'Navarro García', '1993-04-25', 'javier.navarro@gmail.com', 'password123', 'Hombre', '600999999', 'Alumno', 700, 3, 1),
('Laura', 'Domínguez Torres', '1999-06-14', 'laura.dominguez@gmail.com', 'password123', 'Mujer', '601000000', 'Alumno', 500, 1, 1),
('Miguel', 'Vázquez Sánchez', '1996-02-08', 'miguel.vazquez@gmail.com', 'password123', 'Hombre', '601111111', 'Alumno', 400, 2, 1),
('Natalia', 'Ramos Castillo', '1994-10-30', 'natalia.ramos@gmail.com', 'password123', 'Mujer', '601222222', 'Alumno', 300, 3, 1),
('Oscar', 'Gil Ortega', '1991-03-17', 'oscar.gil@gmail.com', 'password123', 'Hombre', '601333333', 'Alumno', 600, 3, 1),
('Patricia', 'Iglesias López', '1990-12-05', 'patricia.iglesias@gmail.com', 'password123', 'Mujer', '601444444', 'Alumno', 200, 2, 1);

-- Asignamos los maestros a sus respectivas clases
UPDATE Clase SET maestro = (SELECT id FROM Usuarios WHERE email = 'carlos.garcia@clubesgrimamadrid.com') WHERE id = 1;
UPDATE Clase SET maestro = (SELECT id FROM Usuarios WHERE email = 'maria.perez@clubesgrimabarcelona.com') WHERE id = 2;


-- Inserción de datos en la tabla Inventario
INSERT INTO Inventario (nombre_objeto, fabricante, cantidad, id_club) VALUES
('Espada de entrenamiento', 'Leon Paul', 20, 1),
('Careta de esgrima', 'Allstar', 15, 1),
('Chaqueta de esgrima', 'PBT', 10, 1),
('Guantes de esgrima', 'PBT', 25, 1),
('Peto protector', 'PBT', 12, 1),
('Pantalones de esgrima', 'Blue Gauntlet', 5, 1);

-- Inserción de datos en la tabla Chat
INSERT INTO Chat (titulo, fecha_creacion, id_usuario_creador, id_usuario_participante) VALUES
('Chat General', '2024-01-01', 1, 2),
('Torneo Nacional', '2024-02-15', 2, 3),
('Clase de Espada', '2024-03-10', 3, 1);

-- Inserción de datos en la tabla Mensaje
INSERT INTO Mensaje (contenido, fecha_publicacion, id_chat, id_usuario) VALUES
('Bienvenidos al chat general del club!', '2024-01-01', 1, 1),
('El proximo torneo sera en Barcelona el 15 de abril.', '2024-02-16', 2, 2),
('Recuerden traer todo el equipo para la clase de espada.', '2024-03-11', 3, 3),
('Hola a todos, soy nuevo en el club!', '2024-01-02', 1, 4),
('Me gustaria saber mas detalles sobre el torneo.', '2024-02-17', 2, 5),
('Estoy emocionado por la clase de espada.', '2024-03-12', 3, 6);

-- Inserción de datos en la tabla Notificacion
INSERT INTO Notificacion (contenido, titulo, fecha, id_usuario) VALUES
('Recordatorio: Clase de Florete esta tarde.', 'Clase Florete', '2024-04-05', 7),
('Actualizacion: El torneo ha cambiado de fecha.', 'Cambio de Fecha Torneo', '2024-04-10', 8),
('Nuevo inventario disponible en el club.', 'Inventario Nuevo', '2024-04-15', 9),
('Se ha agregado un nuevo instructor al equipo.', 'Nuevo Instructor', '2024-04-20', 10),
('Recordatorio: Traer proteccion adicional para la practica.', 'Proteccion Adicional', '2024-04-25', 11),
('Felicitaciones a todos los ganadores del ultimo torneo.', 'Ganadores Torneo', '2024-04-30', 12);