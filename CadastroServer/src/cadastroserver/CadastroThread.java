package cadastroserver;

import controller.ProdutoJpaController;
import controller.UsuarioJpaController;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.net.Socket;

import model.Usuario;
import model.Produto;

import java.util.List;

public class CadastroThread extends Thread {

    private final ProdutoJpaController ctrl;
    private final UsuarioJpaController ctrlUsu;
    private final Socket s1;
    private ObjectOutputStream out;
    private ObjectInputStream in;

    // Construtor
    public CadastroThread(ProdutoJpaController ctrl, UsuarioJpaController ctrlUsu, Socket socket) {
        this.ctrl = ctrl;
        this.ctrlUsu = ctrlUsu;
        this.s1 = socket;
    }

    @Override
    public void run() {
        try {
            // Inicializa os canais de entrada e saída
            out = new ObjectOutputStream(s1.getOutputStream());
            in = new ObjectInputStream(s1.getInputStream());

            // Obtém login e senha
            String login = (String) in.readObject();
            String senha = (String) in.readObject();

            // Verifica o usuário
            Usuario usuario = ctrlUsu.findUsuario(login, senha);
            if (usuario == null) {
                out.writeObject("Usuário inválido. Conexão encerrada.");
                closeConnection();
                return;
            }

            // Loop de resposta
            while (true) {
                String comando = (String) in.readObject();

                if (comando.equalsIgnoreCase("L")) {
                    // Retorna o conjunto de produtos
                    List<Produto> produtos = ctrl.findAll(); // Método que deve retornar todos os produtos
                    out.writeObject(produtos);
                } else {
                    out.writeObject("Comando desconhecido.");
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }

    // Método para fechar a conexão
    private void closeConnection() {
        try {
            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.close();
            }
            if (s1 != null && !s1.isClosed()) {
                s1.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
