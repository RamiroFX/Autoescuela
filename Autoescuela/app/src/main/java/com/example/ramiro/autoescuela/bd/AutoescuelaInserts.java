package com.example.ramiro.autoescuela.bd;

import static com.example.ramiro.autoescuela.bd.AutoescuelaContract.*;

/**
 * Created by Ramiro on 21/11/2017.
 */

public class AutoescuelaInserts {

        private AutoescuelaInserts() { }

        public static class InsertTablaPruebaCategoria {
            public static final String INSERT = "INSERT INTO "+ TablaPruebaCategoria.NOMBRE_TABLA+"("+ TablaPruebaCategoria.NOMBRE_COLUMNA_DESCRIPCION+")" +
                    "VALUES" +
                    "('Normas y legislación')," +
                    "('Señales de tránsito')," +
                    "('Primeros auxilios')," +
                    "('Mecánica');";
        }

        public static class InsertTablaPregunta {
            public static final String INSERT = "INSERT INTO "+TablaPregunta.NOMBRE_TABLA+" ("+TablaPregunta.NOMBRE_COLUMNA_ID_CATEGORIA+","+TablaPregunta.NOMBRE_COLUMNA_PREGUNTA+")" +
                    "VALUES" +
                    "(1,'La definición de “Lomada” es:'),"+
                    "(1,'Al aproximarse a una esquina, Usted observa peatones intentando cruzar:'),"+
                    "(1,'Al conducir su vehículo, Usted debe circular exclusivamente por:'),"+
                    "(1,'El único documento que habilita a manejar vehículo automotores es:'),"+
                    "(1,'Los niños de entre 5 y 10 años deben:'),"+
                    "(1,'Un estacionamiento reservado en la vía pública:'),"+
                    "(1,'¿En qué caso puede usted conducir un vehiculo que tenga fallas en el sistema de dirección o en el mecanismo de freno?:'),"+
                    "(1,'Circulando en ruta, usted debe llevar encendidas permanentemente:'),"+
                    "(1,'Esta prohibido estacionar en los siguientes casos:'),"+
                    "(1,'Cuando ud. circula por una calle y se aproxima a una avenida:'),"+
                    "(1,'Cuando su vehículo se encuentre estacionado y ud. se disponga a reiniciar la marcha, deberá tomar en cuenta lo siguiente:'),"+
                    "(1,'Si enfrenta la luz roja del semáforo y un agente le indica que continúe avanzando, Usted debe:'),"+
                    "(1,'¿En qué momento debe el conductor mantenerse alerta a las condiciones del tránsito?:'),"+
                    "(1,'¿Está permitido colocar el asiento de retención infantil en la parte delantera del vehículo?:'),"+
                    "(1,'En toda maniobra de giro se debe circular por el costado más próximo al giro a efectuar por lo menos:'),"+
                    "(1,'Está prohibido realizar maniobra de adelantamiento:'),"+
                    "(1,'Cuando haya aglomeración de personas o vehículos, todo conductor debe:'),"+
                    "(1,'El conductor que enfrente un obstáculo y deba desviarlo, está obligado a:'),"+
                    "(1,'Transportar pasajeros en la carrocería de vehículos de carga es una actividad:'),"+
                    "(1,'Todo conductor debe reducir la velocidad de su vehículo a paso de peatón:')";
        }

        public static class InsertTablaRespuesta {
            public static final String INSERT = "INSERT INTO "+TablaRespuesta.NOMBRE_TABLA+" ("+TablaRespuesta.NOMBRE_COLUMNA_ID_PREGUNTA+","+TablaRespuesta.NOMBRE_COLUMNA_RESPUESTA+","+TablaRespuesta.NOMBRE_COLUMNA_ES_CORRECTA+")" +
                    "VALUES" +
                    "(1,'Elevación del terreno de poca altura, normalmente de forma redondeada, que viene a ser el primer grado después de la llanura.',0)," +
                    "(1,'Pequeña formación geográfica que es más alta que la llanura, señalizada con doble raya amarilla.',0)," +
                    "(1,'Elevación construida sobre la vía de tránsito en forma transversal, cuya finalidad es reducir la velocidad de circulación.',1),"+
                    "(2,'Debe detenerse y ceder el paso a los peatones, solamente cuando exista franja o cruce peatonal.',0)," +
                    "(2,'Debe detenerse y ceder el paso a los peatones, aunque no exista franja o cruce peatonal.',1)," +
                    "(2,'Debe usar la bocina de manera preventiva, para que el peatón preste mayor atención.',0),"+
                    "(3,'La banquina',0)," +
                    "(3,'La calzada',1)," +
                    "(3,'La acera',0),"+
                    "(4,'La cédula de identidad',0)," +
                    "(4,'La licencia de conducir',1)," +
                    "(4,'Una denuncia policial de extravío de documentos',0),"+
                    "(5,'Viajar en el asiento delantero, al lado del conductor.',0)," +
                    "(5,'Ir en el regazo del acompañante.',0)," +
                    "(5,'Viajar en los asientos traseros, con el cinturón abrochado.',1),"+
                    "(6,'Puede ser utilizado exclusivamente por su titular o quien tenga autorización del mismo.',1)," +
                    "(6,'Puede ser utilizado por cualquier vehículo, hasta que se presente el titular y lo reclame.',0)," +
                    "(6,'Puede ser utilizado libremente en horas pico.',0),"+
                    "(7,'Cuando se presente una situación de emergencia.',0)," +
                    "(7,'Cuando circule exclusivamente en calles empedradas o de tierra.',0)," +
                    "(7,'En ningún caso.',1),"+
                    "(8,'Las luces bajas',1)," +
                    "(8,'Las luces altas',0)," +
                    "(8,'Ninguna de ellas',0),"+
                    "(9,'En avenidas y en las cercanías de centros comerciales.',0)," +
                    "(9,'En paseos centrales y hacia el lado izquierdo de la circulación.',0)," +
                    "(9,'En doble fila y sobre la vereda',1),"+
                    "(10,'Debe disminuir la velocidad y detenerse completamente antes de ingresar al cruce.',1)," +
                    "(10,'Debe disminuir la velocidad y luego continuar la marcha, con precaución.',0)," +
                    "(10,'Debe acelerar para salir de la zona de peligro lo antes posible.',0),"+
                    "(11,'Hacer sonar la bocina antes de reiniciar la marcha.',0)," +
                    "(11,'Sacar el brazo por la ventanilla para exigir que le den paso.',0)," +
                    "(11,'Señalizar con luz de giro y ceder el paso a todo vehículo que se encuentre transitando por la misma vía, ya que Ud. no tiene preferencia.',1),"+
                    "(12,'Continuar avanzando.',1)," +
                    "(12,'Detenerse.',0)," +
                    "(12,'Pedir una explicación al agente de circulación.',0),"+
                    "(13,'En días de lluvia y cuando haya niebla o neblina.',0)," +
                    "(13,'En todo momento, a fin de evitar accidentes.',1)," +
                    "(13,'Cuando transite por avenidas o vías preferenciales.',0),"+
                    "(14,'Está permitido solamente cuando el mecanismo de retención sea confiable.',0)," +
                    "(14,'Está permitido solamente cuando se trate de una sillita o canasto para bebés.',0)," +
                    "(14,'No está permitido en absoluto.',1),"+
                    "(15,'10 metros antes de iniciar la maniobra.',0)," +
                    "(15,'15 metros antes de iniciar la maniobra.',0)," +
                    "(15,'30 metros antes de iniciar la maniobra.',1),"+
                    "(16,'En las avenidas en general.',0)," +
                    "(16,'En vías angostas, puentes, curvas y cerca de cruces peatonales.',1)," +
                    "(16,'En rutas, caminos de tierra y zonas rurales.',0),"+
                    "(17,'Disminuir la velocidad a paso de peatón (5 km/h).',1)," +
                    "(17,'Pedir información sobre el motivo de la situación.',0)," +
                    "(17,'Acelerar para salir de la zona de peligro lo antes posible.',0),"+
                    "(18,'Ceder el paso.',1)," +
                    "(18,'Acelerar.',0)," +
                    "(18,'Hacer uso de la bocina.',0),"+
                    "(19,'Permitida.',0)," +
                    "(19,'Prohibida.',1)," +
                    "(19,'Opcional.',0),"+
                    "(20,'Cuando circule por calles de sentido doble.',0)," +
                    "(20,'Cuando su vehículo no se encuentre en buenas condiciones.',0)," +
                    "(20,'Cuando se acerque a un ómnibus de trasporte colectivo, detenido para alzar o bajar pasajeros.',1)";
        }
    }
