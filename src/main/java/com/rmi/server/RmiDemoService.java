package com.rmi.server;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author kingwarluo
 * @{description}
 * @date 2022/5/26 9:53
 */
public interface RmiDemoService extends Remote {

    String sayHello(String name) throws RemoteException;

}
