package cadastroserver;

import controller.MovimentacaoCompraJpaController;
import controller.MovimentacaoVendaJpaController;
import controller.PessoaJpaController;
import controller.ProdutoJpaController;
import controller.UsuarioJpaController;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.io.IOException;

import java.net.ServerSocket;
import java.net.Socket;

public class CadastroServer {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("CadastroServerPU");

        UsuarioJpaController ctrlUsu = new UsuarioJpaController(emf);
        ProdutoJpaController ctrlProd = new ProdutoJpaController(emf);
        MovimentacaoCompraJpaController ctrlMovCompra = new MovimentacaoCompraJpaController(emf);
        MovimentacaoVendaJpaController ctrlMovVenda = new MovimentacaoVendaJpaController(emf);
        PessoaJpaController ctrlPessoa = new PessoaJpaController(emf);

        try (ServerSocket serverSocket = new ServerSocket(4321)) {
            System.out.println("Servidor iniciado e escutando na porta 4321...");

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Cliente conectado: " + socket.getInetAddress());

                CadastroThreadV2 clienteHandler = new CadastroThreadV2(ctrlProd, ctrlUsu, ctrlMovVenda, ctrlMovCompra, ctrlPessoa, socket
                );
                clienteHandler.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (emf != null) {
                emf.close();
            }
        }
    }
}
