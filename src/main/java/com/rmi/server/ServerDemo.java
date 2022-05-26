package com.rmi.server;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

/**
 * @author kingwarluo
 * @{description}
 * @date 2022/5/26 9:53
 */
public class ServerDemo {

    public static void main(String[] args) {
        try {
            RmiDemoService rmiDemoService = new RmiDemoServiceImpl();
            LocateRegistry.createRegistry(8080);
            Naming.bind("rmi://127.0.0.1:8080/rmi-demo", rmiDemoService);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (AlreadyBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

}
