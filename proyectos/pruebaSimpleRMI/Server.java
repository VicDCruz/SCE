package example.hello;
        
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
        
public class Server implements Hola {
    long cuantos = 0;
	long mu = 4;
    String strHostname = System.getenv("COMPUTERNAME");
    public Server() {}
	
	public void getDeltaT() {
		long deltaT = (long) (-mu * Math.log(Math.random()));
		try {
			Thread.currentThread().sleep(deltaT);
		} catch (Exception e)
		{
			System.err.println("Server exception: " + e.toString());
			e.printStackTrace();
		}
	}

    public synchronized String sayHello() 
    { 
        cuantos++;
        System.out.println("Proporcionando el servicio no. " + cuantos);
		// getDeltaT();
        return "Servicio no. " + cuantos + " proporcionado desde " + strHostname;
    }

    public synchronized long sumaRemota( long a, long b)
	{
       long c = a + b;
	   // getDeltaT();
	   return c;
	}

	public synchronized long restaRemota( long a, long b)
	{
       long c = a - b;
	   getDeltaT();
	   return c;
    }
    
    public synchronized long multiplicacionRemota( long a, long b)
	{
       long c = a * b;
	   getDeltaT();
	   return c;
    }
    
    public synchronized long divisionRemota( long a, long b)
	{
       long c = 0;
       if (b > 0) c = a / b;
	   getDeltaT();
	   return c;
    }
    
    public synchronized long potenciaRemota( long a, long b)
	{
       long c = (long) Math.pow((double) a, (double) b);
	   getDeltaT();
	   return c;
	}
    
    public static void main(String args[]) {
        
        try {
            Server obj = new Server();
            Hola stub = (Hola) UnicastRemoteObject.exportObject(obj, 0);

            // Bind the remote object's stub in the registry
            Registry registry = LocateRegistry.getRegistry();
            registry.rebind("Hola", stub);

            System.err.println("Server ready");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}