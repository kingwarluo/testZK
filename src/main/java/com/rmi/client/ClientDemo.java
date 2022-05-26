package com.rmi.client;

import com.rmi.server.RmiDemoService;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * @author kingwarluo
 * @{description}
 * @date 2022/5/26 9:58
 */
public class ClientDemo {

    public static void main(String[] args) {
        try {
            RmiDemoService rmiDemoService = (RmiDemoService) Naming.lookup("rmi://127.0.0.1:8080/rmi-demo");
            System.out.println(rmiDemoService.sayHello("kingwarluo"));
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

}
