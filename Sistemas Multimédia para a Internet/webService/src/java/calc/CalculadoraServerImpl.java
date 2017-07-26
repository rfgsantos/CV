/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calc;

import javax.jws.WebService;

/**
 *
 * @author Denga
 */
@WebService(endpointInterface = "calc.CalculadoraServer")
public class CalculadoraServerImpl implements CalculadoraServer {

    
    public float soma(float num1, float num2) {
        return num1 + num2;
    }

    
    public float subtraccao(float num1, float num2) {
        return num1 - num2;    
    }

    
    public float multiplicacao(float num1, float num2) {
        return num1 * num2;
    }

    
    public float divisao(float num1, float num2) {
        return num1 / num2;
    }
       
}
