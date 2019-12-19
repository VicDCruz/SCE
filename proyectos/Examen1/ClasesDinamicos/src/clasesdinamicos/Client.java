/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
//================================================================
// Código del Cliente:
//================================================================
package clasesdinamicos;

import java.lang.reflect.Method;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {

    //private Client() {}
    public static void main(String[] args) {
        long lngQuienSoy;
        long sumDeltaT, sumDeltaT2, dtMin = 0, dtMax = 0;
        long lngCuantosMilisFaltan;

        if (args.length > 0) {
            System.out.println("Recibí los argumentos");
            for (int i = 0; i < args.length; i++) {
                System.out.println("args[" + i + "]: " + args[i]);
            }
        }

        String host = (args.length < 2) ? null : args[1];
        long i, t0, t1, dt;
        int totalServices, n;
        double response;

        n = (args.length < 3) ? 1000 : Integer.parseInt(args[2]);
        totalServices = 4;

        String className = (args.length < 1) ? 
                "clasesdinamicos.HolaMundo" : args[0];

        try {
            Registry registry = LocateRegistry.getRegistry(host);
            IServDisparo servDisparo = (IServDisparo) registry.lookup("ServidorDeDisparo");
            lngQuienSoy = servDisparo.quienSoy();
            lngCuantosMilisFaltan = servDisparo.deltaTEnMilis();
            System.out.println("Cliente " + lngQuienSoy + " faltan " + lngCuantosMilisFaltan + " milisegundos");
            sumDeltaT = 0;
            sumDeltaT2 = 0;
            Thread.currentThread().sleep(lngCuantosMilisFaltan);

            for (i = 0; i < n; i++) {
                t0 = System.currentTimeMillis();
                try {
                    Class[] cArg = new Class[1];
                    cArg[0] = String[].class;
                    Class cl = null;
                    Method m = null;
                    cl = Class.forName(className);
                    m = cl.getMethod("main", cArg);
                    m.invoke(cl, (Object) new String[1]);
                    System.out.println("Distribuidor ha invocado, notar cuando aparece este mensaje...");
                } catch (Exception ex) {
                    System.err.println("Client exception: " + ex.toString());
                    ex.printStackTrace();
                }
                t1 = System.currentTimeMillis();
                dt = t1 - t0;
                sumDeltaT += dt;
                sumDeltaT2 += dt * dt;
                if (i == 0) {
                    dtMin = dt;
                    dtMax = dt;
                } else {
                    if (dt < dtMin) {
                        dtMin = dt;
                    }
                    if (dt > dtMax) {
                        dtMax = dt;
                    }
                }
                // System.out.println("Clte " + lngQuienSoy + ": " + response);
                System.out.println("Clte " + lngQuienSoy);
            }
            servDisparo.acumula(sumDeltaT, sumDeltaT2, n, dtMax, dtMin);
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
//================================================================

