/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebatestdinamicosuma;

/**
 *
 * @author vicda
 */
public class PruebaTestDinamicoSuma {

    private static final int TOTALSERVICES = 4;

    private static int getOperation() {
        return 1 + ((int) Math.round(Math.random() * (TOTALSERVICES - 1)));
    }

    public static double doRequest() {
        example.hi.CalculatorCAService1 service
                = new example.hi.CalculatorCAService1();
        example.hi.MyCalculatorPortType port = service.getCasaPort1();
        example.hi.InputComplexType input;
        example.hi.OutputComplexType output = null;

        input = new example.hi.InputComplexType();
        input.setParam01((int) (Math.random() * 20));
        // input.setParam02((int) (Math.random() * 20));
        input.setParam02(0);
        switch (getOperation()) {
            case 1:
                output = additionOperation(input, port);
                break;
            case 2:
                output = subtractionOperation(input, port);
                break;
            case 3:
                output = multiplicationOperation(input, port);
                break;
            case 4: {
                try {
                    output = divisionOperation(input, port);
                } catch (example.hi.OperationFault ex) {
                    System.out.println(ex.getFaultInfo().getFaultDetail());
                    return 0;
                }
            }
            break;
            default:
                throw new AssertionError();
        }
        return output.getResponse01();
    }

    public static void main(String args[]) {
        System.out.println("Resultado " + PruebaTestDinamicoSuma.doRequest());
    }

    private static example.hi.OutputComplexType additionOperation(
            example.hi.InputComplexType part1,
            example.hi.MyCalculatorPortType port) {
        return port.additionOperation(part1);
    }

    private static example.hi.OutputComplexType divisionOperation(
            example.hi.InputComplexType part1,
            example.hi.MyCalculatorPortType port) throws example.hi.OperationFault {
        return port.divisionOperation(part1);
    }

    private static example.hi.OutputComplexType multiplicationOperation(
            example.hi.InputComplexType part1,
            example.hi.MyCalculatorPortType port) {
        return port.multiplicationOperation(part1);
    }

    private static example.hi.OutputComplexType subtractionOperation(
            example.hi.InputComplexType part1,
            example.hi.MyCalculatorPortType port) {
        return port.subtractionOperation(part1);
    }
    
}
