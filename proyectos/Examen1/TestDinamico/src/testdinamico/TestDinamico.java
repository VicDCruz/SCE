/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testdinamico;

import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author vicda
 */
public class TestDinamico {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Class[] cArg = new Class[1];
        cArg[0] = String[].class;
        try {
            Class cl = null;
            Method m = null;
            cl = Class.forName("example.hola.TestModulo");
            m = cl.getMethod("main", cArg);
            Object result = m.invoke(cl, (Object) new String[0]);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
}
