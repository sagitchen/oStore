package oso.server;

import oso.server.remote.HelloServer;

import java.io.IOException;
import java.util.TimeZone;

/**
 * @description:
 * @author: weichen
 * @date: 2019-06-26
 */
public class HelloMain {
    private static final int DEFAULT_PORT = 10000;

    public static void main(String[] args) throws IOException, InterruptedException {

        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));

        int port = getPort(args);
        HelloServer server = new HelloServer();
        server.start(port);
    }

    private static int getPort(String[] args) {
        int port = DEFAULT_PORT;
        if (args.length > 0) {
            try {
                String portNum = args[0];
                int parsedPort = Integer.parseInt(portNum);
                if (parsedPort > 0) {
                    port = parsedPort;
                }
            } catch (NumberFormatException ignored) {
            }
        }
        return port;
    }
}
