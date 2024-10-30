package cadastroclient;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import model.Produto;

public class CadastroClient {

    public static void main(String[] args) {
        String host = "localhost"; // Endereço do servidor
        int port = 4321;           // Porta do servidor

        try (Socket socket = new Socket(host, port)) {
            // Encapsular os canais de entrada e saída do Socket
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

            // Escrever o login e a senha
            String login = "op1"; // Login de teste
            String senha = "op1"; // Senha de teste
            out.writeObject(login);
            out.writeObject(senha);

            // Enviar o comando L para listar produtos
            out.writeObject("L");

            Object response = in.readObject();
            List<Produto> produtos = null;
            if (response instanceof List) {
                produtos = (List<Produto>) response;
                // Processar a lista de produtos
            } else if (response instanceof String mensagem) {
                System.out.println("Mensagem do servidor: " + mensagem);
            } else {
                System.out.println("Tipo inesperado recebido: " + response.getClass().getName());
            }

            // Apresentar o nome de cada entidade recebida
            if (produtos != null && !produtos.isEmpty()) {
                System.out.println("Usuario conectado com sucesso");
                System.out.println("Produtos recebidos:");
                for (Produto produto : produtos) {
                    System.out.println(produto.getNome() + ", Preço: " + produto.getPrecoVenda());
                }
            } else {
                System.out.println("Nenhum produto encontrado.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
