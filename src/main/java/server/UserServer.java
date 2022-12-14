package server;

import java.io.IOException;

import io.grpc.Server;
import io.grpc.ServerBuilder;

public class UserServer {
    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("Servidor iniciado");
        Server server = ServerBuilder.forPort(50051)
                .addService(new UserServiceImpl())
                .build();
        server.start();
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            server.shutdown();
        }));
        server.awaitTermination();
    }
}
