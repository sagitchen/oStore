package oso.server;

import oso.server.remote.HelloWorldClient;

/**
 * @description:
 * @author: weichen
 * @date: 2019-06-27
 */
public class HelloClientMain {
    public static void main(String[] args) {
        HelloWorldClient client = new HelloWorldClient();
        System.out.println(client.sayHello());
    }
}
