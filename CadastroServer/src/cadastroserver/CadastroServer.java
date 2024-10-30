package cadastroserver;

import controller.ProdutoJpaController;
import controller.UsuarioJpaController;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class CadastroServer {

    public static void main(String[] args) {
        // Instanciar o EntityManagerFactory a partir da unidade de persistência
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("CadastroServerPU");

        // Instanciar os controladores
        ProdutoJpaController ctrl = new ProdutoJpaController(emf);
        UsuarioJpaController ctrlUsu = new UsuarioJpaController(emf);

        // Instanciar o ServerSocket escutando a porta 4321
        try (ServerSocket serverSocket = new ServerSocket(4321)) {
            System.out.println("Servidor iniciado e escutando na porta 4321...");

            // Loop infinito para aceitar conexões de clientes
            while (true) {
                // Obter a requisição de conexão do cliente
                Socket socket = serverSocket.accept();
                System.out.println("Cliente conectado: " + socket.getInetAddress());

                // Instanciar uma nova Thread para tratar a conexão do cliente
                CadastroThread clienteHandler = new CadastroThread(ctrl, ctrlUsu, socket);
                clienteHandler.start(); // Iniciar a Thread
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // Fechar o EntityManagerFactory ao final
            if (emf != null) {
                emf.close();
            }
        }
    }
}
