CREATE TABLE  Cobranza  (
   idcobranza  int NOT NULL AUTO_INCREMENT,
   fecha_imputado  datetime NOT NULL,
   fecha_pagado  datetime DEFAULT NULL,
   idhc  int NOT NULL,
   monto  decimal(10,2) DEFAULT NULL,
   estado  char(1) DEFAULT 'P',
   observacion  text,
  PRIMARY KEY ( idcobranza )
) ;

CREATE TABLE  Empleado  (
   idemp  int NOT NULL AUTO_INCREMENT,
   dni  varchar(11) NOT NULL,
   apellido  varchar(255) NOT NULL,
   nombres  varchar(255) NOT NULL,
   matricula  varchar(255) DEFAULT NULL,
   especialidad  varchar(255) DEFAULT NULL,
   sector  int DEFAULT NULL,
   direccion  varchar(255) NOT NULL,
   telefono  varchar(255) NOT NULL,
   email  varchar(255) NOT NULL,
  PRIMARY KEY ( idemp )
);	

CREATE TABLE  Entidad  (
   idet  int NOT NULL AUTO_INCREMENT,
   idrol  int NOT NULL,
   idemp  int NOT NULL,
  PRIMARY KEY ( idet )
) ;

CREATE TABLE  Especie  (
   idespecie  int NOT NULL AUTO_INCREMENT,
   nombre  varchar(255) DEFAULT NULL,
  PRIMARY KEY ( idespecie )
);

CREATE TABLE  Estudio  (
   idestudio  int NOT NULL AUTO_INCREMENT,
   tipoestudio  int NOT NULL,
   fecha  datetime NOT NULL,
   laboratorio  int NOT NULL,
   observacion  text,
  PRIMARY KEY ( idestudio )
) ;

CREATE TABLE  ExamenFisico  (
   idexamen  int NOT NULL AUTO_INCREMENT,
   idhc  int NOT NULL,
   peso  decimal(5,2) DEFAULT NULL,
   temperatura  decimal(3,1) DEFAULT NULL,
   estado_general  text,
   foto  longblob,
  PRIMARY KEY ( idexamen )
) ;

CREATE TABLE  FilaTurno  (
   id  int NOT NULL AUTO_INCREMENT,
   fecha  varchar(10) NOT NULL,
   completada  tinyint(1) DEFAULT NULL,
  PRIMARY KEY ( id )
) ;

CREATE TABLE  HistoriaClinica  (
   idhc  int NOT NULL AUTO_INCREMENT,
   idpaciente  int NOT NULL,
   idmedico  int NOT NULL,
   fecha  datetime NOT NULL,
   tipo  int NOT NULL,
   anamnesis  text,
   tratamiento  varchar(255) DEFAULT NULL,
   estudio  int DEFAULT NULL,
   observaciones  text,
  PRIMARY KEY ( idhc )
) ;

CREATE TABLE  Inventario  (
   idinventario  int NOT NULL AUTO_INCREMENT,
   idproducto  int NOT NULL,
   fecha  datetime NOT NULL,
   existencia  int NOT NULL,
  PRIMARY KEY ( idinventario ),
  CONSTRAINT  Inventario_chk_1  CHECK (( existencia  > 0))
) ;

CREATE TABLE  Laboratorio  (
   idlab  int NOT NULL AUTO_INCREMENT,
   nombre  varchar(255) NOT NULL,
   responsable  varchar(255) NOT NULL,
   telefono  varchar(255) NOT NULL,
   email  varchar(255) DEFAULT NULL,
  PRIMARY KEY ( idlab )
) ;

CREATE TABLE  Movimiento  (
   idmovimiento  int NOT NULL AUTO_INCREMENT,
   idinventario  int NOT NULL,
   variacion  int NOT NULL,
   idempleado  int NOT NULL,
   idhc  int NOT NULL,
   comentario  varchar(255) DEFAULT NULL,
  PRIMARY KEY ( idmovimiento )
) ;

CREATE TABLE  Paciente  (
   idpaciente  int NOT NULL AUTO_INCREMENT,
   idtutor  int NOT NULL,
   nombre  varchar(255) NOT NULL,
   idrazaespecie  int NOT NULL,
   sexo  char(1) DEFAULT NULL,
   fecha_nacimiento  varchar(10) DEFAULT NULL,
   peso  decimal(6,3) DEFAULT NULL,
   microchip  varchar(255) DEFAULT NULL,
   foto  varchar(255) DEFAULT NULL,
   idreferido  int DEFAULT NULL,
   fecha_ultima_visita  varchar(10) DEFAULT NULL,
   pelaje  varchar(255) DEFAULT NULL,
   plumaje  varchar(255) DEFAULT NULL,
   tipo_paciente  varchar(255) NOT NULL,
  PRIMARY KEY ( idpaciente )
);

CREATE TABLE  Producto  (
   idproducto  int NOT NULL AUTO_INCREMENT,
   tipo  int NOT NULL,
   nombre  varchar(255) NOT NULL,
   marca  varchar(255) NOT NULL,
   proveedor  int NOT NULL,
   costo  decimal(10,2) DEFAULT NULL,
  PRIMARY KEY ( idproducto )
) ;

CREATE TABLE  Proveedor  (
   idproveedor  int NOT NULL AUTO_INCREMENT,
   nombre  varchar(255) DEFAULT NULL,
   responsable  varchar(255) NOT NULL,
   telefono  varchar(255) NOT NULL,
   email  varchar(255) DEFAULT NULL,
  PRIMARY KEY ( idproveedor )
) ;

CREATE TABLE  PuestoFilaTurno  (
   id  int NOT NULL AUTO_INCREMENT,
   idfilaturno  int NOT NULL,
   fecha_ingreso  varchar(16),
   idtutor  int NOT NULL,
   idpaciente  int NOT NULL,
   numero_asignado  int NOT NULL,
   fecha_egreso  varchar(16) DEFAULT NULL,
   cancelado  tinyint(1) DEFAULT NULL,
   atendido  tinyint(1) DEFAULT NULL,
  PRIMARY KEY ( id )
) ;

CREATE TABLE  RazaEspecie  (
   idrazaespecie  int NOT NULL AUTO_INCREMENT,
   idespecie  int NOT NULL,
   nombre  varchar(255) DEFAULT NULL,
  PRIMARY KEY ( idrazaespecie )
);

CREATE TABLE  Referido  (
   idreferido  int NOT NULL AUTO_INCREMENT,
   nombre  varchar(255) NOT NULL,
   telefono  varchar(255) NOT NULL,
   email  varchar(255) DEFAULT NULL,
  PRIMARY KEY ( idreferido )
) ;

CREATE TABLE  Rol  (
   idrol  int NOT NULL AUTO_INCREMENT,
   nombre  varchar(255) NOT NULL,
   menu_gral  varchar(255) DEFAULT NULL,
  PRIMARY KEY ( idrol )
);

CREATE TABLE  Sector  (
   idsector  int NOT NULL AUTO_INCREMENT,
   nombre  varchar(255) NOT NULL,
  PRIMARY KEY ( idsector )
) ;

CREATE TABLE  TipoConsulta  (
   idtc  int NOT NULL AUTO_INCREMENT,
   nombre  varchar(255) NOT NULL,
  PRIMARY KEY ( idtc )
) ;

CREATE TABLE  TipoEstudio  (
   idte  int NOT NULL AUTO_INCREMENT,
   nombre  varchar(255) NOT NULL,
  PRIMARY KEY ( idte )
) ;

CREATE TABLE  TipoProducto  (
   idtp  int NOT NULL AUTO_INCREMENT,
   nombre  varchar(255) NOT NULL,
  PRIMARY KEY ( idtp )
) ;

CREATE TABLE  Turno  (
   idturno  int NOT NULL AUTO_INCREMENT,
   idpaciente  int NOT NULL,
   idmedico  int NOT NULL,
   fecha  datetime NOT NULL,
   estado  char(1) DEFAULT 'A',
  PRIMARY KEY ( idturno )
) ;

CREATE TABLE  Tutor  (
   idtutor  int NOT NULL AUTO_INCREMENT,
   dni  varchar(11) DEFAULT NULL,
   nombres  varchar(255) NOT NULL,
   apellido  varchar(255) NOT NULL,
   direccion  varchar(255) NOT NULL,
   email  varchar(255) DEFAULT NULL,
   telefono  varchar(255) NOT NULL,
   comentario  varchar(255) DEFAULT NULL,
  PRIMARY KEY ( idtutor )
);

CREATE TABLE  Usuario  (
   idusuario  int NOT NULL AUTO_INCREMENT,
   login  varchar(255) DEFAULT NULL,
   passw  varchar(255) DEFAULT NULL,
   id_entidad  int NOT NULL,
   estado  char(1) DEFAULT 'A',
  PRIMARY KEY ( idusuario ),
  UNIQUE KEY  login  ( login )
);

INSERT INTO  Usuario  VALUES (3,'mestevez','123',1,'A'),(4,'amorales','456',2,'A');
INSERT INTO  Empleado  VALUES (1,'10000000','ESTEVEZ','MARIO RAFAEL','1234','CLINICA',NULL,'AV LA PLATA 100','4993-9191','estevez@gmail.com'),(2,'2000000','MORALES','ANABEL MERCEDES',NULL,NULL,1,'ROSARIO 748','4823-8800','morales@gmail.com');
INSERT INTO  Especie  VALUES (1,'Canino'),(2,'Felino'),(3,'Ave'),(4,'Ave silvestre'),(5,'Equino'),(6,'Pez'),(7,'Caprino'),(8,'Leporido'),(9,'Ovino'),(10,'Bovino');
INSERT INTO  FilaTurno  VALUES (1,'2024-06-29',NULL);
INSERT INTO  PuestoFilaTurno  VALUES (1,1,'2024-06-29 13:00',4,3,20,NULL,NULL,NULL),(2,1,'2024-06-29 13:15',5,1,1,NULL,NULL,NULL);
INSERT INTO  RazaEspecie  VALUES (1,1,'Rottweiler'),(2,1,'Caniche'),(3,1,'Dauchshund'),(4,1,'Terrier'),(5,1,'Ovejero aleman'),(6,2,'Siames'),(7,2,'Persa'),(8,2,'Bombay'),(9,2,'Abisinio'),(10,3,'Gallina Sussex'),(11,3,'Gallina Colorada'),(12,3,'Pato Pekin'),(13,3,'Pato Rouen'),(14,3,'Golondrina Comun'),(15,3,'Gorrion');
INSERT INTO  Referido  VALUES (1,'DR COSTAS','4300-2000',NULL),(2,'VETERINARIA LA RINCONADA','221 410-5550','vetlarinconada@gmail.com');
INSERT INTO  Rol  VALUES (1,'Medico','menu_medico'),(2,'Administrativo','menu_administrativo'),(3,'Directivo','menu_directivo'),(4,'Auxiliar medico','menu_auxiliar');
INSERT INTO  TipoConsulta  VALUES (1,'Consulta'),(2,'Control');
INSERT INTO  Tutor  VALUES (1,'1111','Adalberto Manuel','Pereira','Santamarina 100 - Laprida',NULL,'34939',NULL),(3,'20004005','Isabel','Costas Patron','Rivadavia 823 - Laprida','','423-088',''),(4,'55','ANGEL','ROZAS','CALLE 80 NRO 844 - SAN MARTIN','','44324',''),(5,'54','ALBERTO','CARABAJAL','CIUDAD DE LA PAZ','','44',''),(6,'555','MARIA','BARRAZA','LAS LOMAS 499','','54',''),(10,'','LIZ','FASSI LAVALLE','LAS PIZZETAS 499','','45',''),(13,'','CARLITOS','TREJO','MISMO DOMICILIO','trejo@carlitos.info','56213',''),(14,'648','MANUEL','SORAKO','LAS CALAS 488','sorako@gmail.com','488',''),(17,'7','efa','bbs','da','','2','');

