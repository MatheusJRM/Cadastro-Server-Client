package cadastroserver;

import controller.ProdutoJpaController;
import controller.UsuarioJpaController;
import controller.MovimentacaoVendaJpaController;
import controller.MovimentacaoCompraJpaController;
import controller.PessoaJpaController;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;

import java.net.Socket;

import model.Usuario;
import model.Produto;
import model.MovimentacaoVenda;
import model.MovimentacaoCompra;

import java.util.List;

public class CadastroThreadV2 extends Thread {

    private final ProdutoJpaController ctrlProd;
    private final UsuarioJpaController ctrlUsu;
    private final MovimentacaoVendaJpaController ctrlMovVenda;
    private final MovimentacaoCompraJpaController ctrlMovCompra;
    private final PessoaJpaController ctrlPessoa;
    private final Socket sckt;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private boolean running = true;

    public CadastroThreadV2(ProdutoJpaController ctrlProd, UsuarioJpaController ctrlUsu,
            MovimentacaoVendaJpaController ctrlMovVenda,
            MovimentacaoCompraJpaController ctrlMovCompra,
            PessoaJpaController ctrlPessoa,
            Socket socket) {
        this.ctrlProd = ctrlProd;
        this.ctrlUsu = ctrlUsu;
        this.ctrlMovVenda = ctrlMovVenda;
        this.ctrlMovCompra = ctrlMovCompra;
        this.ctrlPessoa = ctrlPessoa;
        this.sckt = socket;
    }

    @Override
    public void run() {
        try {
            out = new ObjectOutputStream(sckt.getOutputStream());
            in = new ObjectInputStream(sckt.getInputStream());

            String login = (String) in.readObject();
            String senha = (String) in.readObject();

            Usuario usuario = ctrlUsu.findUsuario(login, senha);
            if (usuario == null) {
                out.writeObject("Usuário inválido. Conexão encerrada.");
                closeConnection();
                return;
            } else {
                out.writeObject("Usuário conectado com sucesso.");
            }

            while (running && !sckt.isClosed()) {
                String comando = "";
                try {
                    comando = (String) in.readObject();
                } catch (Exception e) {
                    out.writeObject("Comando desconhecido.");
                }

                if (comando.equalsIgnoreCase("L")) {
                    List<Produto> produtos = ctrlProd.findAll();
                    out.writeObject(produtos);
                } else {
                    if (comando.equalsIgnoreCase("E") || comando.equalsIgnoreCase("S")) {
                        processarMovimentacao(comando, usuario);
                    } else if (comando.equalsIgnoreCase("X")) {
                        running = false;
                        out.writeObject("Conexão encerrada pelo cliente.");
                        closeConnection();
                    } else {
                        out.writeObject("Comando desconhecido.");
                    }
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }

    private void processarMovimentacao(String tipo, Usuario usuario) throws IOException, ClassNotFoundException {
        System.out.println("Processando movimento do tipo: " + tipo);

        int idPessoa = (Integer) in.readObject();
        System.out.println("Recebido ID da pessoa: " + idPessoa);

        int idProduto = (Integer) in.readObject();
        System.out.println("Recebido ID do produto: " + idProduto);

        int quantidade = (Integer) in.readObject();
        System.out.println("Recebido quantidade: " + quantidade);

        Double valorUnitario = (Double) in.readObject();
        System.out.println("Recebido valor unitário: " + valorUnitario);

        if (usuario == null) {
            out.writeObject("Usuário inválido.");
            return;
        }
        Produto produto = ctrlProd.findById(idProduto);
        if (produto == null) {
            out.writeObject("Produto com ID " + idProduto + " não encontrado.");
            return;
        }
        if (tipo.equalsIgnoreCase("E")) {
            if (ctrlPessoa.getByIdPj(idPessoa) == null) {
                out.writeObject("Pessoa jurídica com ID " + idPessoa + " não encontrada.");
                return;
            }
        } else if (tipo.equalsIgnoreCase("S")) {
            if (ctrlPessoa.getByIdPf(idPessoa) == null) {
                out.writeObject("Pessoa física com ID " + idPessoa + " não encontrada.");
                return;
            }
        }
        try {
            if (tipo.equalsIgnoreCase("E")) {
                MovimentacaoVenda movimentacaoVenda = new MovimentacaoVenda();
                movimentacaoVenda.setIdUsuario(usuario.getId());
                movimentacaoVenda.setIdPessoaJuridica(idPessoa);
                movimentacaoVenda.setIdProduto(idProduto);
                movimentacaoVenda.setQuantidadeProduto(quantidade);
                movimentacaoVenda.setValorUnitario(valorUnitario);

                ctrlMovVenda.create(movimentacaoVenda);

                ctrlProd.atualizarQuantidade(idProduto, quantidade);
            } else if (tipo.equalsIgnoreCase("S")) {
                MovimentacaoCompra movimentacaoCompra = new MovimentacaoCompra();
                movimentacaoCompra.setIdUsuario(usuario.getId());
                movimentacaoCompra.setIdPessoaFisica(idPessoa);
                movimentacaoCompra.setIdProduto(idProduto);
                movimentacaoCompra.setQuantidadeProduto(quantidade);
                movimentacaoCompra.setValorUnitario(valorUnitario);

                ctrlMovCompra.create(movimentacaoCompra);

                ctrlProd.atualizarQuantidade(idProduto, -quantidade);
            }

            out.writeObject("Movimento registrado com sucesso.");
        } catch (IOException e) {
            out.writeObject("Erro ao registrar movimento: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void closeConnection() {
        try {
            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.close();
            }
            if (sckt != null && !sckt.isClosed()) {
                sckt.close();
            }
            System.out.println("Conexão encerrada pelo cliente.");
            running = false;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
