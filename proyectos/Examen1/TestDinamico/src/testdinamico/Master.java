/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package testdinamico;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author RGGH
 */
public class Master {

    static IServDisparo servDisparo = null;

    static void resetea(String strCuantosSegs) {
        long lngDentroDeCuantosMilis;
        System.out.println("Se resetea el servidor de disparo con el argumento " + strCuantosSegs);
        try {
            lngDentroDeCuantosMilis = 1000 * Long.parseLong(strCuantosSegs);
            if (lngDentroDeCuantosMilis <= 0) {
                lngDentroDeCuantosMilis = 10000;
            }
        } catch (Exception e) {
            lngDentroDeCuantosMilis = 10000;
        }
        try {
            servDisparo.reset(lngDentroDeCuantosMilis);
            System.out.println("Se ha reseteado el servidor de disparo a " + lngDentroDeCuantosMilis + " milisegundos");
        } catch (Exception re) {
            re.printStackTrace();
        }
    }

    static void reporta() {
        boolean isOk = false;
        while (!isOk) {
            try {
                if (servDisparo.deltaTmedia() > 0) System.out.println("Iniciando reporte");
                System.out.println("================================================");
                System.out.println("    cantidad de clientes: " + servDisparo.getCuantosCltes());
                System.out.println("media de tiempo de ciclo: " + servDisparo.deltaTmedia());
                System.out.println(" dev std tiempo de ciclo: " + servDisparo.deltaTStdDev());
                System.out.println("  tiempo de ciclo máximo: " + servDisparo.deltaTMax());
                System.out.println("  tiempo de ciclo mínimo: " + servDisparo.deltTMin());
                isOk = true;
            } catch (Exception e) {
                // e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        String host = (args.length < 1) ? null : args[0];

        System.out.println("Master, inciando con:");
        if (args.length == 0) {
            System.out.println("Sin argumentos");
        } else {
            for (int i = 0; i < args.length; i++) {
                System.out.println("args[" + i + "]:" + args[i]);
            }
        }
        System.out.println("------------------------------------------");

        try {
            if (servDisparo == null) {
                Registry registry = LocateRegistry.getRegistry(host);
                servDisparo = (IServDisparo) registry.lookup("ServidorDeDisparo");
            }
            if (args.length > 1) {
                if (args[1].compareToIgnoreCase("RESET") == 0) {
                    Master.resetea(args.length > 2 ? args[2] : "10");
                } else {
                    Master.reporta();
                }
            } else {
                Master.resetea("10");
            }

        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }

}
