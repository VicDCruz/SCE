package example.hello;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {

    private Client() {
    }

    public static long getOperation(long x, long y, int service) {
        long output = 0;
        switch (service) {
        case 1:
            output = x + y;
            break;
        case 2:
            output = x - y;
            break;
        case 3:
            output = x * y;
            break;
        case 4:
            if (y > 0)
                output = x / y;
            break;
        case 5:
            output = (long) Math.pow(x, y);
            break;
        default:
            break;
        }
        return output;
    }

    public static long getStub(Hola stub, long x, long y, int service) {
        long output = 0;
        try {
            switch (service) {
            case 1:
                output = stub.sumaRemota(x, y);
                break;
            case 2:
                output = stub.restaRemota(x, y);
                break;
            case 3:
                output = stub.multiplicacionRemota(x, y);
                break;
            case 4:
                if (y > 0)
                    output = stub.divisionRemota(x, y);
                break;
            case 5:
                output = stub.potenciaRemota(x, y);
                break;
            default:
                break;
            }
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
        return output;
    }

    public static long runServices(String[] args) {
        long output = (long) 0.0;
        long x, y, z, zStub;
        long errores = 0;
        // String response;
        String host = (args.length < 1) ? null : args[0];
        long N = (args.length < 2) ? 500 : Long.parseLong(args[1]);
        try {
            Registry registry = LocateRegistry.getRegistry(host);
            Hola stub = (Hola) registry.lookup("Hola");
            long start = System.currentTimeMillis();
            int service = (int) Math.floor(Math.random() * 5);
            for (int i = 0; i < N; i++) {
                // response = stub.sayHello();
                x = (long) (10000.0 * Math.random());
                y = (long) (10000.0 * Math.random());
                z = Client.getOperation(x, y, service);
                zStub = Client.getStub(stub, x, y, service);
                if (z != zStub)
                    errores++;
                // System.out.println("response: " + response);
                service = (int) Math.floor(Math.random() * 5);
            }
            if (errores == 0)
                System.out.println("Sin errores");
            else
                System.out.println("OCURRIERON " + errores + " ERRORES");
            long end = System.currentTimeMillis();
            output = (long) ((end - start) / (N + 0.0));
            System.out.println("Tardo " + ((end - start) * 0.001) + " seg");
            System.out.println("Media: " + output + " mSeg");
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
        return output;
    }

    public static void main(String[] args) {
        int repeat = 20;
        for (int i = 0; i < repeat; i++) {
            Client.runServices(args);
        }
    }
}
