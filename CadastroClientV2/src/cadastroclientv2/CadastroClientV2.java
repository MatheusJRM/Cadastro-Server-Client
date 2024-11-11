package cadastroclientv2;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.net.Socket;

public class CadastroClientV2 {

    public static void main(String[] args) {
        String host = "localhost";
        int port = 4321;

        try (Socket socket = new Socket(host, port); ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream()); ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {

            SaidaFrame saidaFrame = new SaidaFrame(null, in);
            saidaFrame.setVisible(true);

            out.writeObject("op1");
            out.writeObject("op1");

            boolean executando = true;
            while (executando) {
                System.out.println("Menu:");
                System.out.println("L - Listar produtos");
                System.out.println("E - Entrada");
                System.out.println("S - Saída");
                System.out.println("X - Finalizar");
                System.out.print("Escolha uma opção: ");
                String comando = new java.util.Scanner(System.in).nextLine().toUpperCase();

                switch (comando) {
                    case "L" ->
                        out.writeObject("L");
                    case "E" ->
                        processarMovimentacao("E", out, in);
                    case "S" ->
                        processarMovimentacao("S", out, in);
                    case "X" -> {
                        out.writeObject("X");
                        executando = false;
                        break;
                    }
                    default ->
                        System.out.println("Comando inválido. Tente novamente.");
                }
            }
            saidaFrame.dispose();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void processarMovimentacao(String tipo, ObjectOutputStream out, ObjectInputStream in) throws Exception {
        out.writeObject(tipo);
        System.out.println("Enviando tipo de movimentação: " + tipo);
        enviarDetalhesMovimentacao(out);
    }

    private static void enviarDetalhesMovimentacao(ObjectOutputStream out) throws Exception {
        java.util.Scanner scanner = new java.util.Scanner(System.in);

        System.out.print("Digite o ID da pessoa: ");
        int idPessoa = Integer.parseInt(scanner.nextLine());
        out.writeObject(idPessoa);

        System.out.print("Digite o ID do produto: ");
        int idProduto = Integer.parseInt(scanner.nextLine());
        out.writeObject(idProduto);

        System.out.print("Digite a quantidade: ");
        int quantidade = Integer.parseInt(scanner.nextLine());
        out.writeObject(quantidade);

        System.out.print("Digite o valor unitário: ");
        double valorUnitario = Double.parseDouble(scanner.nextLine());
        out.writeObject(valorUnitario);
    }
}
