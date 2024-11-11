package cadastroclientv2;

import java.io.EOFException;
import java.io.ObjectInputStream;
import java.net.SocketException;
import java.util.List;
import javax.swing.JTextArea;

public class ThreadClient extends Thread {

    private final ObjectInputStream entrada;
    private final JTextArea textArea;
    private boolean running = true;

    public ThreadClient(ObjectInputStream entrada, JTextArea textArea) {
        this.entrada = entrada;
        this.textArea = textArea;
    }

    @Override
    public void run() {
        try {
            while (running) {
                Object objeto;
                try {
                    objeto = entrada.readObject();
                } catch (EOFException e) {
                    adicionarTexto("Conexão com o servidor foi encerrada.");
                    break;
                } catch (SocketException e) {
                    adicionarTexto("Conexão com o servidor foi encerrada.");
                    break;
                }

                if (objeto instanceof String mensagem) {
                    adicionarTexto(mensagem);

                    if ("Conexão encerrada pelo cliente.".equals(mensagem) || "Usuário inválido. Conexão encerrada.".equals(mensagem)) {
                        running = false;
                        break;
                    }
                } else if (objeto instanceof List<?> lista) {
                    adicionarTexto("Lista de Produtos:");
                    for (Object item : lista) {
                        adicionarTexto(item.toString());
                    }
                } else {
                    adicionarTexto("Objeto de tipo desconhecido recebido: " + objeto.getClass().getName());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            adicionarTexto("Erro ao receber dados do servidor: " + e.getMessage());
        } finally {
            closeConnection();
        }
    }

    private void adicionarTexto(String texto) {
        javax.swing.SwingUtilities.invokeLater(() -> textArea.append(texto + "\n"));
    }

    private void closeConnection() {
        try {
            if (entrada != null) {
                entrada.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            adicionarTexto("Erro ao fechar a conexão: " + e.getMessage());

        }
    }
}
