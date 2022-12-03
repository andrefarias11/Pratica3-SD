package client;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import proto.CreateUserRequest;
import proto.CreateUserResponse;
import proto.DeleteUserRequest;
import proto.DeleteUserResponse;
import proto.GetUserRequest;
import proto.GetUserResponse;
import proto.ListUserRequest;
import proto.ListUserResponse;
import proto.User;
import proto.UserServiceGrpc;

public class UserClient {

	public static void main(String[] args) {
		UserClient main = new UserClient();
		main.run();
	}

	private void run() {
		ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50051).usePlaintext().build();
		UserServiceGrpc.UserServiceBlockingStub userClient = UserServiceGrpc.newBlockingStub(channel);

		//Criando usuario inicial
		User user = User.newBuilder().setTitulo("Ap boa localizacao").setEndereco("Rua Francisco Sa").setTipo("apartamento").setNumeroQuartos(2).setNumeroGaragens(1).setAreaConstruida(60).setAreaTerreno(50).setPreco(5000).build();
		CreateUserResponse createUserResponse = userClient
				.createUser(CreateUserRequest.newBuilder().setUser(user).build());
		System.out.println(createUserResponse.toString());
		
		
		int userId = createUserResponse.getUser().getId();
		
		System.out.println("**********");
		
		User user2 = User.newBuilder().setTitulo("Casa em frente Americanas").setEndereco("Rua Coronel").setTipo("casa").setNumeroQuartos(2).setNumeroGaragens(1).setAreaConstruida(80).setAreaTerreno(80).setPreco(250000).build();
		CreateUserResponse createUserResponse2 = userClient
				.createUser(CreateUserRequest.newBuilder().setUser(user).build());
		System.out.println(createUserResponse.toString());
		
                //Pegando user
		System.out.println("********** GET USER");
                
                GetUserResponse getUserResponse = userClient.getUser(GetUserRequest.newBuilder().setUserId(userId).build());
		System.out.println(getUserResponse.getUser());
                
		GetUserResponse getUserResponse2 = userClient.getUser(GetUserRequest.newBuilder().setUserId(userId).build());
		System.out.println(getUserResponse2.getUser());

		// Apagar user pelo id
		System.out.println("********** DELETE USER");
		DeleteUserResponse deleteUserResponse = userClient.deleteUser(DeleteUserRequest.newBuilder().setUserId(userId).build());;
		System.out.println(deleteUserResponse.getUserId());

		// LIST USERS
		System.out.println("********** LIST USER");
		ListUserResponse listUserResponse = userClient.listUser(ListUserRequest.newBuilder().build());
		System.out.println(listUserResponse.getUserList());
		
		
		
	}
}
