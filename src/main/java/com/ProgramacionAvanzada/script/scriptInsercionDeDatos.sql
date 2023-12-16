-- Insertar clientes
INSERT INTO public.cliente (estado, apellido, direccion, dni, email, nombre, telefono)
VALUES
    (true, 'López', 'Calle 123', '12345678', 'lopez@gmail.com', 'Juan', '111222333'),
    (true, 'Gómez', 'Avenida 456', '23456789', 'gomez@gmail.com', 'María', '444555666'),
    (true, 'Pérez', 'Calle 789', '34567890', 'perez@gmail.com', 'Pedro', '777888999'),
    (true, 'Rodríguez', 'Avenida 012', '45678901', 'rodriguez@gmail.com', 'Laura', '888999000'),
    (true, 'Sánchez', 'Calle 345', '56789012', 'sanchez@gmail.com', 'Javier', '123456789');

-- Insertar marcas
INSERT INTO public.marca (estado,impuesto, nombre)
VALUES
    (true,10, 'Toyota'),
    (true,15, 'Ford'),
    (true,12, 'Chevrolet'),
    (true,20, 'Honda'),
    (true,25, 'Hyundai');

-- Insertar modelos
INSERT INTO public.modelo (estado, marca_id, anio, nombre)
VALUES
    (true, 1, '2022', 'Corolla'),
    (true, 2, '2021', 'Focus'),
    (true, 3, '2023', 'Cruze'),
    (true, 4, '2020', 'Civic'),
    (true, 5, '2022', 'Elantra');

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
    (true, 'García', 'Calle 678', '67890123', 'garcia@gmail.com', 'Carlos', '111222333'),
    (true, 'Fernández', 'Avenida 901', '78901234', 'fernandez@gmail.com', 'Marta', '444555666'),
    (true, 'Martínez', 'Calle 234', '89012345', 'martinez@gmail.com', 'Lucas', '777888999'),
    (true, 'Sánchez', 'Avenida 567', '90123456', 'sanchez@gmail.com', 'Ana', '888999000'),
    (true, 'López', 'Calle 890', '12345678', 'lopez@gmail.com', 'Diego', '123456789');

-- Insertar repuestos adicionales
INSERT INTO public.repuesto (cantidad, precio, descripcion, nombre)
VALUES
    (10, 18, 'Filtro de aire premium', 'Filtro de aire premium'),
    (10, 10, 'Bujías de alto rendimiento', 'Bujías de alto rendimiento'),
    (10, 45, 'Aceite sintético de alto rendimiento', 'Aceite sintético'),
    (10, 30, 'Filtro de aceite premium', 'Filtro de aceite premium'),
    (10, 20, 'Pastillas de freno de cerámica', 'Pastillas de freno de cerámica'),
    (10, 15, 'Filtro de combustible avanzado', 'Filtro de combustible avanzado'),
    (10, 25, 'Líquido de frenos de alto rendimiento', 'Líquido de frenos de alto rendimiento'),
    (10, 12, 'Limpiaparabrisas premium', 'Limpiaparabrisas premium'),
    (10, 22, 'Batería de arranque de larga duración', 'Batería de arranque'),
    (10, 18, 'Juego de cables de encendido', 'Cables de encendido'),
    (10, 28, 'Termostato de refrigeración avanzado', 'Termostato de refrigeración'),
    (10, 35, 'Frenos de disco perforados y ranurados', 'Frenos de disco perforados y ranurados'),
    (10, 40, 'Aceite de transmisión sintético', 'Aceite de transmisión sintético'),
    (10, 16, 'Filtro de habitáculo premium', 'Filtro de habitáculo premium'),
    (10, 24, 'Bujías de iridio de alto rendimiento', 'Bujías de iridio'),
    (10, 38, 'Pastillas de freno de alto rendimiento', 'Pastillas de freno de alto rendimiento');


-- Insertar servicios adicionales
INSERT INTO public.servicio (eliminado, precio, descripcion, nombre)
VALUES
    (false, 120, 'Reparación de sistema de frenos avanzado', 'Reparación avanzada de frenos'),
    (false, 180, 'Reparación y ajuste de sistema de transmisión', 'Reparación de transmisión'),
    (false, 90, 'Inspección y ajuste de sistema de dirección', 'Ajuste de dirección'),
    (false, 60, 'Reemplazo de bujías y cables de encendido', 'Mantenimiento de encendido'),
    (false, 150, 'Servicio de limpieza de inyectores', 'Limpieza de inyectores'),
    (false, 100, 'Reparación de sistema de suspensión', 'Reparación de suspensión'),
    (false, 130, 'Diagnóstico y reparación de sistema eléctrico avanzado', 'Reparación eléctrica avanzada'),
    (false, 75, 'Cambio de líquido de frenos y purgado', 'Cambio de líquido de frenos'),
    (false, 110, 'Reparación de sistema de escape', 'Reparación de escape'),
    (false, 80, 'Alineación de ruedas y balanceo avanzado', 'Alineación y balanceo avanzado');


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

-- Insertar órdenes de trabajo actualizadas
INSERT INTO public.orden_de_trabajo (descuento, fecha_creacion, fecha_entrega, sub_total, total, id_vehiculo, tecnico_id, impuesto, informacion_relevante)
VALUES
    (10, '2023-12-02', '2023-12-21', 150, 135, 1, 1, 0.1, 'Reparación de motor y frenos'),
    (5, '2023-12-05', '2023-12-28', 80, 76, 2, 2, 0.1, 'Cambio de aceite y filtro'),
    (15, '2023-12-10', '2023-12-28', 200, 170, 1, 1, 0.1, 'Reparación eléctrica y cambio de batería'),
    (8, '2023-12-15', '2023-12-30', 250, 230, 2, 2, 0.1, 'Balanceo de neumáticos y alineación de ruedas'),
    (12, '2023-12-20', '2024-01-05', 180, 168, 3, 1, 0.1, 'Reparación de motor y cambio de aceite avanzado');


-- Insertar detalles de órdenes de trabajo
INSERT INTO public.orden_servicio (orden_id, servicio_id)
VALUES
    (1, 1),
    (1, 2),
    (2, 2),
    (2, 3),
    (3, 1),
    (3, 3),
    (3, 2),
    (4, 4),
    (4, 5),
    (5, 1),
    (5, 6),
    (5, 7);
