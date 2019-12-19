/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
//================================================================
// Código del Cliente:
//================================================================
package testdinamico;

import java.lang.reflect.Method;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client {
    static Hello serv = null;

    //private Client() {}
    public static void main(String[] args) {
        long lngQuienSoy;
        long sumDeltaT, sumDeltaT2, dtMin = 0, dtMax = 0;
        long lngCuantosMilisFaltan;

        String host = (args.length < 1) ? null : args[0];
        long i, t0, t1, dt;
        int totalServices, n;
        String response = null;

        n = (args.length < 2) ? 1000 : Integer.parseInt(args[1]);
        totalServices = 4;
        
        Registry registry = null;
        try {
            registry = LocateRegistry.getRegistry(host);
            serv = (Hello) registry.lookup("Hello");
            serv.sayHello();
            
            IServDisparo servDisparo = (IServDisparo) registry.lookup("ServidorDeDisparo");
            lngQuienSoy = servDisparo.quienSoy();
            lngCuantosMilisFaltan = servDisparo.deltaTEnMilis();
            System.out.println("Cliente " + lngQuienSoy + " faltan " + lngCuantosMilisFaltan + " milisegundos");
            sumDeltaT = 0;
            sumDeltaT2 = 0;
            Thread.currentThread().sleep(lngCuantosMilisFaltan);
            
            Class[] cArg = new Class[1];
            cArg[0] = String[].class;
            String[] masterArgs = new String[0];
            String nameClass = (args.length < 3) ? "testdinamico.HelloWorld" : args[2];

            for (i = 0; i < n; i++) {
                t0 = System.currentTimeMillis();
             
                if (nameClass != null) {
                    Class cl = null;
                    Method m = null;
                    cl = Class.forName("example.hola.TestModulo");
                    m = cl.getMethod("main", cArg);
                    Object result = m.invoke(cl, (Object) masterArgs);
                    if (result != null)
                        response = (String) result;
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
                if (response != null)
                    System.out.println("Clte " + lngQuienSoy + ": " + response);
                else
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

