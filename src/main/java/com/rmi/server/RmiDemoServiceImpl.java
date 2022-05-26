package com.rmi.server;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * @author kingwarluo
 * @{description}
 * @date 2022/5/26 9:53
 */
public class RmiDemoServiceImpl extends UnicastRemoteObject implements RmiDemoService {

    protected RmiDemoServiceImpl() throws RemoteException {
        super();
    }

    @Override
    public String sayHello(String name) throws RemoteException {
        return "Hello, " + name;
    }
}
