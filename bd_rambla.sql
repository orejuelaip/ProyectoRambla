-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 07-12-2016 a las 18:33:05
-- Versión del servidor: 10.1.16-MariaDB
-- Versión de PHP: 5.6.24

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `bd_rambla`
--

DELIMITER $$
--
-- Procedimientos
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `agregarCitas` (IN `pc_fecha` DATETIME, IN `pc_medico` VARCHAR(160), IN `pc_paciente` VARCHAR(160), IN `pc_usuario` CHAR(15), IN `pc_codigo` CHAR(20), IN `pc_detalle` TEXT)  BEGIN
DECLARE pc_med char(20);
DECLARE pc_pac char(20);
set pc_pac = (SELECT p.id_paciente FROM pacientes p WHERE concat(p.apellido_paciente,', ',p.nombre_paciente)  = pc_paciente);
set pc_med = (SELECT p.id_medico FROM medicos p WHERE concat(p.apellido_medico,', ',p.nombre_medico)  = pc_medico);
INSERT INTO `citas`(`id_cita`, `fecha_cita`, `id_medico`, `id_paciente`, `fecha_registro`,  `id_usuario`,detalle) 
VALUES ( pc_codigo,pc_fecha,pc_med,pc_pac ,now() ,pc_usuario,pc_detalle );
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `agregarEspecialidad` (IN `pc_nombre` VARCHAR(80), IN `pc_usuario` CHAR(15), IN `pc_codigo` CHAR(7))  BEGIN

insert into especialidades (nombre_especialidad,id_usuario,id_especialidad)value(pc_nombre,pc_usuario,pc_codigo);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `deleteCitas` (`pc_codigo` CHAR(20))  BEGIN
update citas set  l_activo = 'N',fecha_debaja= now() where id_cita = pc_codigo;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `deleteEspecialidad` (IN `pc_codigo` CHAR(7))  BEGIN
update especialidades  set  l_activo = 'N' where id_especialidad = pc_codigo;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `ListarEspecialidad` ()  BEGIN
SELECT id_especialidad as Codigo, nombre_especialidad  as nombre 
from Especialidades 
where l_activo = 'S'
order by Codigo ;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `updateCitas` (`pc_codigo` CHAR(20), `pc_medico` CHAR(20), `pc_paciente` CHAR(20), `pc_fecha_cita` DATETIME)  BEGIN
update  citas set 
		fecha_cita  =pc_fecha_cita  ,
        id_medico	=pc_medico,
        id_paciente	=pc_paciente,
        fecha_modificacion= now() 
 where id_cita = pc_codigo;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `updateEspecialidad` (IN `pc_codigo` CHAR(7), IN `pc_nombre` VARCHAR(30))  BEGIN
update  especialidades set  nombre_especialidad =pc_nombre  where id_especialidad = pc_codigo;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `usp_agregarMedico` (IN `pc_id` CHAR(20), IN `pc_nom` VARCHAR(30), IN `pc_ape` VARCHAR(30), IN `pc_fnaci` DATE, IN `pc_email` VARCHAR(120), IN `pc_docu` CHAR(8), IN `pc_dir` VARCHAR(120), IN `pc_espe` VARCHAR(90), IN `pc_user` CHAR(15), IN `pc_fingreso` DATE, IN `pc_sexo` CHAR(1))  BEGIN 
DECLARE idespe char(7);
set idespe = (SELECT e.id_especialidad from especialidades e where e.nombre_especialidad = pc_espe );
INSERT INTO `medicos`(`id_medico`, `nombre_medico`, `apellido_medico`, `f_nacimiento`, `email`, `n_documento`, `direccion`,  `id_especialidad`, `id_usuario`, `fecha_ingreso`,sexo) 
VALUES (pc_id ,pc_nom,pc_ape,pc_fnaci,pc_email,pc_docu,pc_dir,idespe,pc_user,pc_fingreso,pc_sexo);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `usp_agregarPaciente` (IN `pc_id` CHAR(20), `pc_nom` VARCHAR(30), `pc_ape` VARCHAR(60), `pc_naci` DATE, IN `pc_dni` CHAR(8), IN `pc_dir` VARCHAR(120), IN `pc_email` VARCHAR(120), IN `pc_user` CHAR(15))  BEGIN
INSERT INTO  `pacientes`
   (`id_paciente`,
	`nombre_paciente` ,
    `apellido_paciente`,
    `fecha_nacimiento`,
    `numero_documetnp`,
    `x_direccion_real`,
    `correo_electronico`,
    `id_usuario`)
 values( pc_id,pc_nom,pc_ape ,pc_naci,pc_dni, pc_dir,pc_email, pc_user  );
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `usp_buscarCita` (IN `pc_cita` CHAR(20))  NO SQL
SELECT c.id_cita,
    concat(e.apellido_paciente ,', ',e.nombre_paciente),
    concat( m.apellido_medico,', ',m.nombre_medico),
    date_format(c.fecha_cita,'%d/%m/%Y'),
    date_format(c.fecha_cita,'%h:%i'),
    date_format(c.fecha_registro,'%d/%m/%Y'),
    c.detalle,
    es.nombre_especialidad as espe
    FROM citas c 
    INNER JOIN pacientes e on 
    e.id_paciente = c.id_paciente
    INNER JOIN medicos m on 
     m.id_medico = c.id_medico
     INNER JOIN especialidades es on 
     es.id_especialidad = m.id_especialidad 
    WHERe c.l_activo = 'S' and c.id_cita = pc_cita
    ORDER by c.fecha_cita DESC$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `usp_buscarPaciente` (IN `pc_id` CHAR(20))  BEGIN
	SELECT 
    	p.id_paciente as id ,
        p.apellido_paciente  as ape,
        p.nombre_paciente  as nombre,
       date_format(p.fecha_nacimiento,'%d/%m/%Y') as nacimiento,
        p.numero_documetnp as dni,
        p.x_direccion_real as direccion,
        p.correo_electronico as email      
    from pacientes p
    WHERE p.l_activo ='S' and p.id_paciente = pc_id
    ORDER by p.apellido_paciente;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `usp_cmbMedicos` (IN `pc_especialidad` VARCHAR(160))  NO SQL
BEGIN
DECLARE idespe char(7);
set idespe = (SELECT e.id_especialidad from especialidades e where e.nombre_especialidad = trim(pc_especialidad) );
SELECT concat( `apellido_medico`,', ',`nombre_medico`) as nom 
FROM `medicos` 
where l_activo = 'S' and  id_especialidad = idespe 
order BY `apellido_medico`,`nombre_medico`;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `usp_cmbPaciente` ()  NO SQL
BEGIN
SELECT concat(`apellido_paciente`,', ',`nombre_paciente`) as nom
FROM `pacientes` 
WHERE `l_activo` = 'S' 
order by `apellido_paciente`,`nombre_paciente` ;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `usp_codigoCita` ()  NO SQL
BEGIN 
 DECLARE codigo char(20);
 set codigo = (SELECT count(*)+1 from citas);
 set codigo = concat('000000', trim( codigo) );
 set codigo = concat('C-',year(now()),'-', RIGHT(codigo,7) );
 select codigo;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `usp_codigoMedico` ()  BEGIN 
 DECLARE codigo char(20);
 set codigo = (SELECT count(*)+1 from medicos);
 set codigo = concat('000000', trim( codigo) );
 set codigo = concat('MED-',year(now()),'-', RIGHT(codigo,5) );
 select codigo;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `usp_codigorPaciente` ()  BEGIN 
	DECLARE codigo char(20);
    declare mes char(2);
    DECLARE dias char(2);
    set dias =(SELECT  (case length(day(now())) when 1 then concat('0',day(now()) )else day(now()) end));
    set mes = (SELECT  (case length(month(now())) when 1 then concat('0',month(now()) )else month(now()) end));
    set codigo = (SELECT count(*)+1 from pacientes);
    set codigo = concat('000000', trim( codigo) );
    set codigo = concat(year(now()),mes,dias, RIGHT(codigo,5) );
    select codigo;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `usp_consulta2` (IN `pc_fecha` DATE)  NO SQL
BEGIN 
	SELECT
    	date_format(c.fecha_cita,'%h:%i') as hora,
        u.nombre_usuario ,
        concat(p.apellido_paciente,' ',p.nombre_paciente) as nom,
        date_format(c.fecha_registro ,'%d/%m/%Y') as freg
    FROM citas c  
    INNER JOIN pacientes p ON
    	p.id_paciente = c.id_paciente
    INNER JOIN usuario u ON
    	u.id_usuario = c.id_usuario
    where  date(c.fecha_cita) = pc_fecha and c.l_activo ='S';
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `usp_deletePaciente` (IN `pc_id` CHAR(20))  BEGIN
update   `pacientes` set l_activo ='N',fecha_debaja=now() where id_paciente = pc_id ;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `usp_eliminarMedico` (`pc_id` CHAR(20))  BEGIN 
 update  `medicos` SET l_activo = 'N' and fecha_debaja = now() WHERE  id_medico =  pc_id;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `usp_generarCodigo` ()  BEGIN 
	DECLARE codigo char(7);
    set codigo = (SELECT count(*)+1 from especialidades);
    set codigo = concat('000000', trim( codigo) );
    set codigo = concat('E-', RIGHT(codigo,5) );
    select codigo;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `usp_listaPorID` (IN `pc_id` CHAR(20))  NO SQL
BEGIN
	SELECT 
    	p.id_paciente as id ,
        concat(p.apellido_paciente,' ',p.nombre_paciente)as nombre,
        p.fecha_nacimiento as nacimiento,
        p.numero_documetnp as dni,
        p.x_direccion_real as direccion,
        p.correo_electronico as email      
    from pacientes p
    WHERE p.l_activo ='S' and p.id_paciente LIKE  pc_id 
    ORDER by p.apellido_paciente;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `usp_listarCitas` ()  NO SQL
BEGIN 
	SELECT c.id_cita,
    concat(e.apellido_paciente ,' ',e.nombre_paciente),
    concat( m.apellido_medico,' ',m.nombre_medico),
    date_format(c.fecha_cita,'%d/%m/%Y'),
    date_format(c.fecha_cita,'%h:%i'),
    date_format(c.fecha_registro,'%d/%m/%Y')
    FROM citas c 
    INNER JOIN pacientes e on 
    e.id_paciente = c.id_paciente
    INNER JOIN medicos m on 
     m.id_medico = c.id_medico
    WHERe c.l_activo = 'S'
    ORDER by c.fecha_cita DESC
    ;
    
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `usp_listarConsulta1` (IN `pc_medico` CHAR(20), IN `pc_fecha` DATE)  NO SQL
BEGIN
	SELECT
    	date_format(c.fecha_cita,'%h:%i') as hora,
        u.nombre_usuario ,
        concat(p.apellido_paciente,' ',p.nombre_paciente) as nom,
        date_format(c.fecha_registro ,'%d/%m/%Y') as freg
    FROM citas c  
    INNER JOIN pacientes p ON
    	p.id_paciente = c.id_paciente
    INNER JOIN usuario u ON
    	u.id_usuario = c.id_usuario
    where c.id_medico = pc_medico and date(c.fecha_cita) = pc_fecha
    	and c.l_activo = 'S';
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `usp_listarMedico` ()  BEGIN 
	SELECT
    	m.id_medico,
       concat(    m.apellido_medico,"",m.nombre_medico),
        m.f_nacimiento,
        m.email,
        m.n_documento,
        m.direccion,
        m.id_especialidad
        ,e.nombre_especialidad
     FROM medicos m 
     INNER JOIN especialidades e on 
     	m.id_especialidad = e.id_especialidad
     where m.l_activo = 'S';
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `usp_listarMedicoPorId` (IN `pc_id` CHAR(20))  BEGIN 
	SELECT
    	m.id_medico,
        m.nombre_medico,
        m.apellido_medico,
       date_format( m.f_nacimiento,'%d/%m/%Y'),
        m.email,
        m.n_documento,
        m.direccion 
        ,e.nombre_especialidad
        ,m.sexo,
       date_format(  m.fecha_ingreso,'%d/%m/%Y')
     FROM medicos m 
     INNER JOIN especialidades e on 
     	m.id_especialidad = e.id_especialidad
     where m.l_activo = 'S' and m.id_medico  like pc_id;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `usp_listarPaciente` ()  BEGIN
	SELECT 
    	p.id_paciente as id ,
        concat(p.apellido_paciente,', ',p.nombre_paciente)as nombre,
        p.fecha_nacimiento as nacimiento,
        p.numero_documetnp as dni,
        p.x_direccion_real as direccion,
        p.correo_electronico as email      
    from pacientes p
    WHERE p.l_activo ='S'
    ORDER by p.apellido_paciente;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `usp_modificarCita` (IN `pc_cita` CHAR(20), IN `pc_fechaCita` DATETIME, IN `pc_medicoape` VARCHAR(160), IN `pc_mediconom` VARCHAR(160), IN `pc_pacienteape` VARCHAR(160), IN `pc_pacientenom` VARCHAR(160), IN `pc_usuario` CHAR(15), IN `pc_detalle` TEXT)  NO SQL
BEGIN
DECLARE id_medico char(20);
DECLARE id_paciente char(20);

set  id_medico =(select  m.id_medico FROM medicos m WHERE m.nombre_medico =pc_mediconom AND m.apellido_medico =pc_medicoape  );
set id_paciente = (select p.id_paciente FROM pacientes p WHERE p.nombre_paciente =pc_pacientenom AND  p.apellido_paciente =pc_pacienteape );

UPDATE `citas`
SET 
    `fecha_cita`= pc_fechaCita,
    `id_medico`= id_medico,
    `id_paciente`= id_paciente,
    `fecha_modificacion`= now(),
     detalle =pc_detalle
   
     WHERE `id_cita`=pc_cita;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `usp_modificarMedico` (IN `pc_id` CHAR(20), IN `pc_nom` VARCHAR(30), IN `pc_ape` VARCHAR(30), IN `pc_fnaci` DATE, IN `pc_email` VARCHAR(120), IN `pc_docu` CHAR(8), IN `pc_dir` VARCHAR(120), IN `pc_espe` VARCHAR(60), IN `pc_ingreso` DATE, IN `pc_sexo` CHAR(1))  BEGIN 
DECLARE idespe char(7);
set idespe = (SELECT e.id_especialidad from especialidades e where e.nombre_especialidad = pc_espe );
	UPDATE `medicos` 
    SET 
    	`nombre_medico`= pc_nom,
        `apellido_medico`= pc_ape,
        `f_nacimiento`= pc_fnaci,
        `email`= pc_email ,
        `n_documento`= pc_docu,
        `direccion`= pc_dir,
        `fecha_modificacion`= now(),
        `id_especialidad`=  idespe,
         fecha_ingreso = pc_ingreso,sexo = pc_sexo
        WHERE  id_medico =pc_id;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `usp_updatePaciente` (IN `pc_id` CHAR(20), `pc_nom` VARCHAR(30), `pc_ape` VARCHAR(60), `pc_naci` DATE, IN `pc_dni` CHAR(8), IN `pc_dir` VARCHAR(120), IN `pc_email` VARCHAR(120))  BEGIN
UPDATE `pacientes` SET 
	`nombre_paciente`=pc_nom ,
    `apellido_paciente`=pc_ape,
    `fecha_nacimiento`=pc_naci,
    `numero_documetnp`=pc_dni ,
    `x_direccion_real`= pc_dir,
    `correo_electronico`=pc_email,
    `fecha_modificacion`=now() 
WHERE `id_paciente`=pc_id ;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `usp_validarCita` (IN `pc_medico` VARCHAR(180), IN `pc_fecha` DATETIME)  NO SQL
BEGIN
declare pc_med char(20);
set pc_med = (SELECT p.id_medico FROM medicos p WHERE concat(p.apellido_medico,', ',p.nombre_medico)  =pc_medico);
	SELECT c.id_cita 
    FROM citas C 
    WHERE C.id_medico = pc_med AND  C.fecha_cita = pc_fecha;

END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `usp_validaUser` (IN `pc_usuario` CHAR(15), IN `pc_pass` VARCHAR(255))  BEGIN 
	SELECT id_usuario as id ,clave as pass , nombre_usuario as nom FROM usuario WHERE id_usuario = pc_usuario AND clave = pc_pass;
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `citas`
--

CREATE TABLE `citas` (
  `id_cita` char(20) NOT NULL,
  `fecha_cita` datetime NOT NULL,
  `id_medico` char(20) NOT NULL,
  `id_paciente` char(20) NOT NULL,
  `fecha_registro` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `fecha_modificacion` datetime DEFAULT NULL,
  `id_usuario` char(15) NOT NULL,
  `l_activo` char(1) DEFAULT 'S',
  `fecha_debaja` datetime DEFAULT NULL,
  `detalle` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `citas`
--

INSERT INTO `citas` (`id_cita`, `fecha_cita`, `id_medico`, `id_paciente`, `fecha_registro`, `fecha_modificacion`, `id_usuario`, `l_activo`, `fecha_debaja`, `detalle`) VALUES
('C-2016-0000001', '2016-12-12 10:00:00', 'MED-2016-00003', '2016113000003', '2016-12-05 04:40:51', '2016-12-05 20:44:35', 'orejuela', 'N', '2016-12-05 20:52:55', 'sadasdasdasdasd'),
('C-2016-0000002', '2016-12-12 12:00:00', 'MED-2016-00003', '2016112900001', '2016-12-06 00:49:29', '2016-12-07 15:58:19', 'orejuela', 'S', NULL, 'asdasdsadasdasdasd'),
('C-2016-0000003', '2016-05-12 04:00:00', 'MED-2016-00003', '2016113000003', '2016-12-06 01:55:04', '2016-12-05 21:31:37', 'orejuela', 'S', NULL, 'PRESENTAR DOCUMENTOS'),
('C-2016-0000004', '2016-12-13 10:00:00', 'MED-2016-00004', '2016113000003', '2016-12-07 20:57:50', '2016-12-07 16:02:09', 'orejuela', 'S', NULL, 'ASDASDASDASDASD'),
('C-2016-0000005', '2016-12-13 10:00:00', 'MED-2016-00004', '2016113000003', '2016-12-07 23:13:03', NULL, 'orejuela', 'S', NULL, 'sdasdasdasd'),
('C-2016-0000006', '2016-12-13 10:00:00', 'MED-2016-00004', '2016112900001', '2016-12-07 23:16:49', NULL, 'orejuela', 'S', NULL, 'sdasdasdasd'),
('C-2016-0000007', '2016-12-13 10:00:00', 'MED-2016-00004', '2016112900001', '2016-12-07 23:21:24', NULL, 'orejuela', 'S', NULL, 'sdsdasdasdasdasdasd');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `especialidades`
--

CREATE TABLE `especialidades` (
  `id_especialidad` char(7) NOT NULL,
  `nombre_especialidad` varchar(80) NOT NULL,
  `l_activo` char(1) DEFAULT 'S',
  `id_usuario` char(15) NOT NULL,
  `f_registro` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `especialidades`
--

INSERT INTO `especialidades` (`id_especialidad`, `nombre_especialidad`, `l_activo`, `id_usuario`, `f_registro`) VALUES
('E-00001', 'NEUROLOGIA', 'S', 'orejuela', '2016-11-30 01:22:27'),
('E-00002', 'OTORRINOLARINGOLOGIA', 'N', 'orejuela', '2016-11-30 02:14:41'),
('E-00003', 'TRAUMATOLOGIA Y ORTOPEDIA', 'N', 'orejuela', '2016-11-30 02:15:17'),
('E-00004', 'CIRUGIA GENERAL Y LAPAROSCOPICA', 'N', 'orejuela', '2016-11-30 02:29:24'),
('E-00005', 'MEDICINA FISICA Y REHABILITACION', 'S', 'orejuela', '2016-11-30 02:33:21'),
('E-00006', 'REUMATOLOGIA', 'S', 'orejuela', '2016-12-01 04:22:05');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `medicos`
--

CREATE TABLE `medicos` (
  `id_medico` char(20) NOT NULL,
  `nombre_medico` varchar(30) NOT NULL,
  `apellido_medico` varchar(60) NOT NULL,
  `f_nacimiento` date NOT NULL,
  `sexo` char(1) NOT NULL,
  `email` varchar(220) NOT NULL,
  `n_documento` char(8) NOT NULL,
  `direccion` varchar(120) NOT NULL,
  `fecha_ingreso` datetime NOT NULL,
  `fecha_modificacion` datetime DEFAULT NULL,
  `id_especialidad` char(7) NOT NULL,
  `id_usuario` char(15) NOT NULL,
  `l_activo` char(1) DEFAULT 'S',
  `fecha_debaja` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `medicos`
--

INSERT INTO `medicos` (`id_medico`, `nombre_medico`, `apellido_medico`, `f_nacimiento`, `sexo`, `email`, `n_documento`, `direccion`, `fecha_ingreso`, `fecha_modificacion`, `id_especialidad`, `id_usuario`, `l_activo`, `fecha_debaja`) VALUES
('MED-2016-00001', 'LLERENA MORAN', 'JUAN MORALES', '1971-12-12', 'M', 'LLERENA@GMAILÑ.COM', '98761271', 'AV BRASIL 671', '2011-11-12 00:00:00', '2016-12-01 01:51:04', 'E-00005', 'orejuela', '0', NULL),
('MED-2016-00002', 'MARIA KARINA', 'OLMEDO CARWANCHO', '2016-02-12', 'M', 'OLMEDO@CLINICA.COM', '21233659', 'AV. BRASIL 569', '2016-06-12 00:00:00', '2016-12-01 12:08:45', 'E-00005', 'orejuela', 'S', NULL),
('MED-2016-00003', 'CARLOS JUAN', 'ALVITES MEDINA', '1956-05-12', 'M', 'AV. AREQUIPA N° 1256', '12358964', 'AV. BRASIL 258', '2016-12-12 00:00:00', '2016-12-01 12:30:51', 'E-00005', 'orejuela', 'S', NULL),
('MED-2016-00004', 'RICARDO', 'MONTOYA MORALES', '1956-05-12', 'M', 'MORALES@GMAIL.COM', '23568945', 'AV. BRASIL S/N', '2016-05-12 00:00:00', NULL, 'E-00001', 'orejuela', 'S', NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pacientes`
--

CREATE TABLE `pacientes` (
  `id_paciente` char(20) NOT NULL,
  `nombre_paciente` varchar(30) NOT NULL,
  `apellido_paciente` varchar(60) NOT NULL,
  `fecha_nacimiento` date NOT NULL,
  `numero_documetnp` char(8) NOT NULL,
  `x_direccion_real` varchar(120) DEFAULT NULL,
  `correo_electronico` varchar(120) DEFAULT NULL,
  `fecha_modificacion` datetime DEFAULT NULL,
  `id_usuario` char(15) NOT NULL,
  `l_activo` char(1) DEFAULT 'S',
  `fecha_debaja` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `pacientes`
--

INSERT INTO `pacientes` (`id_paciente`, `nombre_paciente`, `apellido_paciente`, `fecha_nacimiento`, `numero_documetnp`, `x_direccion_real`, `correo_electronico`, `fecha_modificacion`, `id_usuario`, `l_activo`, `fecha_debaja`) VALUES
('2016112900001', 'JUAN CARLOS', 'MORENO REATEGUI', '1956-01-12', '56892312', 'AV. BRASIL N° 556', 'MORENO@GMAIL.COM', '2016-11-30 16:20:55', 'orejuela', 'S', '2016-11-30 23:19:05'),
('2016113000002', 'RIDA SANDRA', 'BALAREZO MELO', '1980-03-15', '23568974', 'CHORRILOS SIN CALLES N° 201', 'MELO@GMAIL.COM', '2016-11-30 23:18:52', 'orejuela', 'N', '2016-11-30 23:20:54'),
('2016113000003', 'EMA SANDRA', 'ORTIZ LOPEZ', '1988-10-12', '78451256', 'AV. BRASIL 256', 'ORTIZ@GMAIL.COM', NULL, 'orejuela', 'S', NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `id_usuario` char(15) NOT NULL,
  `nombre_usuario` varchar(60) NOT NULL,
  `clave` varchar(255) NOT NULL,
  `fecha_registro` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`id_usuario`, `nombre_usuario`, `clave`, `fecha_registro`) VALUES
('orejuela', 'Paul Orejuela Infante', '123456', '2016-11-30 01:21:30');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `citas`
--
ALTER TABLE `citas`
  ADD PRIMARY KEY (`id_cita`),
  ADD KEY `id_usuario` (`id_usuario`),
  ADD KEY `id_medico` (`id_medico`),
  ADD KEY `id_paciente` (`id_paciente`);

--
-- Indices de la tabla `especialidades`
--
ALTER TABLE `especialidades`
  ADD PRIMARY KEY (`id_especialidad`),
  ADD KEY `id_usuario` (`id_usuario`);

--
-- Indices de la tabla `medicos`
--
ALTER TABLE `medicos`
  ADD PRIMARY KEY (`id_medico`),
  ADD KEY `id_usuario` (`id_usuario`),
  ADD KEY `id_especialidad` (`id_especialidad`);

--
-- Indices de la tabla `pacientes`
--
ALTER TABLE `pacientes`
  ADD PRIMARY KEY (`id_paciente`),
  ADD KEY `id_usuario` (`id_usuario`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`id_usuario`);

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `citas`
--
ALTER TABLE `citas`
  ADD CONSTRAINT `citas_ibfk_1` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`),
  ADD CONSTRAINT `citas_ibfk_2` FOREIGN KEY (`id_medico`) REFERENCES `medicos` (`id_medico`),
  ADD CONSTRAINT `citas_ibfk_3` FOREIGN KEY (`id_paciente`) REFERENCES `pacientes` (`id_paciente`);

--
-- Filtros para la tabla `especialidades`
--
ALTER TABLE `especialidades`
  ADD CONSTRAINT `especialidades_ibfk_1` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`);

--
-- Filtros para la tabla `medicos`
--
ALTER TABLE `medicos`
  ADD CONSTRAINT `medicos_ibfk_1` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`),
  ADD CONSTRAINT `medicos_ibfk_2` FOREIGN KEY (`id_especialidad`) REFERENCES `especialidades` (`id_especialidad`);

--
-- Filtros para la tabla `pacientes`
--
ALTER TABLE `pacientes`
  ADD CONSTRAINT `pacientes_ibfk_1` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
