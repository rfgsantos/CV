/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webserviceSMI;

import java.util.ArrayList;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

/**
 *
 * @author Denga
 */
@WebService
@SOAPBinding(style = Style.RPC)
public interface SMIConnection {
    @WebMethod String[] getParametrosCliente(String username);
    @WebMethod String getNome(String username);
    @WebMethod String getUsername(String username);
    @WebMethod String getFoto(String username);
    @WebMethod String getTipo(String username);
    @WebMethod String getEmail(String username);
}
