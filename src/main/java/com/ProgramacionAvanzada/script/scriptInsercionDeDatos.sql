-- Insertar clientes
INSERT INTO public.cliente (estado, apellido, direccion, dni, email, nombre, telefono)
VALUES
    (true, 'lopez', 'calle 123', '12345678', 'lopez@gmail.com', 'juan', '111222333'),
    (true, 'gomez', 'avenida 456', '23456789', 'gomez@gmail.com', 'maria', '444555666'),
    (true, 'perez', 'calle 789', '34567890', 'perez@gmail.com', 'pedro', '777888999'),
    (true, 'rodriguez', 'avenida 012', '45678901', 'rodriguez@gmail.com', 'laura', '888999000'),
    (true, 'sanchez', 'calle 345', '56789012', 'sanchez@gmail.com', 'javier', '123456789');

-- Insertar marcas
INSERT INTO public.marca (estado,impuesto, nombre)
VALUES
    (true,10, 'toyota'),
    (true,15, 'ford'),
    (true,12, 'chevrolet'),
    (true,20, 'honda'),
    (true,25, 'hyundai');

-- Insertar modelos
INSERT INTO public.modelo (estado, marca_id, anio, nombre)
VALUES
    (true, 1, '2022', 'corolla'),
    (true, 2, '2021', 'focus'),
    (true, 3, '2023', 'cruze'),
    (true, 4, '2020', 'civic'),
    (true, 5, '2022', 'elantra');

-- Insertar vehículos
INSERT INTO public.vehiculo (estado, id_cliente, id_marca, id_modelo, patente)
VALUES
    (true, 1, 1, 1, 'ABC123'),
    (true, 2, 2, 2, 'DEF456'),
    (true, 3, 3, 3, 'GHI789'),
    (true, 4, 4, 4, 'JKL012'),
    (true, 5, 5, 5, 'MNO345');

-- Insertar técnicos
INSERT INTO public.tecnico (estado, apellido, direccion, dni, email, nombre, telefono)
VALUES
    (true, 'garcia', 'calle 678', '67890123', 'garcia@gmail.com', 'carlos', '111222333'),
    (true, 'fernandez', 'avenida 901', '78901234', 'fernandez@gmail.com', 'marta', '444555666'),
    (true, 'martinez', 'calle 234', '89012345', 'martinez@gmail.com', 'lucas', '777888999'),
    (true, 'sanchez', 'avenida 567', '90123456', 'sanchez@gmail.com', 'ana', '888999000'),
    (true, 'lopez', 'calle 890', '12345678', 'lopez@gmail.com', 'diego', '123456789');

-- Insertar repuestos adicionales
INSERT INTO public.repuesto (cantidad, precio, descripcion, nombre)
VALUES
    (10, 18, 'filtro de aire premium', 'filtro de aire premium'),
    (10, 10, 'bujias de alto rendimiento', 'bujias de alto rendimiento'),
    (10, 45, 'aceite sintetico de alto rendimiento', 'aceite sintetico'),
    (10, 30, 'filtro de aceite premium', 'filtro de aceite premium'),
    (10, 20, 'pastillas de freno de ceramica', 'pastillas de freno de ceramica'),
    (10, 15, 'filtro de combustible avanzado', 'filtro de combustible avanzado'),
    (10, 25, 'liquido de frenos de alto rendimiento', 'liquido de frenos de alto rendimiento'),
    (10, 12, 'limpiaparabrisas premium', 'limpiaparabrisas premium'),
    (10, 22, 'bateria de arranque de larga duracion', 'bateria de arranque'),
    (10, 18, 'juego de cables de encendido', 'cables de encendido'),
    (10, 28, 'termostato de refrigeracion avanzado', 'termostato de refrigeracion'),
    (10, 35, 'frenos de disco perforados y ranurados', 'frenos de disco perforados y ranurados'),
    (10, 40, 'aceite de transmision sintetico', 'aceite de transmision sintetico'),
    (10, 16, 'filtro de habitaculo premium', 'filtro de habitaculo premium'),
    (10, 24, 'bujias de iridio de alto rendimiento', 'bujias de iridio'),
    (10, 38, 'pastillas de freno de alto rendimiento', 'pastillas de freno de alto rendimiento');


-- Insertar servicios adicionales
INSERT INTO public.servicio (eliminado, precio, descripcion, nombre)
VALUES
    (false, 120, 'reparacion de sistema de frenos avanzado', 'reparacion avanzada de frenos'),
    (false, 180, 'reparacion y ajuste de sistema de transmision', 'reparacion de transmision'),
    (false, 90, 'inspeccion y ajuste de sistema de direccion', 'ajuste de direccion'),
    (false, 60, 'reemplazo de bujias y cables de encendido', 'mantenimiento de encendido'),
    (false, 150, 'servicio de limpieza de inyectores', 'limpieza de inyectores'),
    (false, 100, 'reparacion de sistema de suspension', 'reparacion de suspension'),
    (false, 130, 'diagnostico y reparacion de sistema electrico avanzado', 'reparacion electrica avanzada'),
    (false, 75, 'cambio de liquido de frenos y purgado', 'cambio de liquido de frenos'),
    (false, 110, 'reparacion de sistema de escape', 'reparacion de escape'),
    (false, 80, 'alineacion de ruedas y balanceo avanzado', 'alineacion y balanceo avanzado');


-- Insertar servicios y repuestos adicionales
INSERT INTO public.servicio_repuesto (servicio_id, repuesto_id)
VALUES
    (1, 8),
    (1, 9),
    (2, 9),
    (2, 10),
    (3, 8),
    (3, 10),
    (3, 9),
    (4, 11),
    (4, 12),
    (5, 8),
    (5, 13),
    (5, 14),
    (6, 11),
    (6, 13),
    (6, 9),
    (6, 10),
    (7, 8),
    (7, 10),
    (7, 9),
    (8, 11),
    (8, 12),
    (8, 8),
    (9, 13),
    (9, 14),
    (10,5),
    (10,7),
    (10,12);
