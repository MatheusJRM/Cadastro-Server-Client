/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author mathe
 */
@Entity
@Table(name = "Movimentacao_Venda")
@NamedQueries({
    @NamedQuery(name = "MovimentacaoVenda.findAll", query = "SELECT m FROM MovimentacaoVenda m"),
    @NamedQuery(name = "MovimentacaoVenda.findById", query = "SELECT m FROM MovimentacaoVenda m WHERE m.id = :id"),
    @NamedQuery(name = "MovimentacaoVenda.findByQuantidadeProduto", query = "SELECT m FROM MovimentacaoVenda m WHERE m.quantidadeProduto = :quantidadeProduto"),
    @NamedQuery(name = "MovimentacaoVenda.findByValorUnitario", query = "SELECT m FROM MovimentacaoVenda m WHERE m.valorUnitario = :valorUnitario")})
public class MovimentacaoVenda implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "quantidade_produto")
    private int quantidadeProduto;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "valor_unitario")
    private Double valorUnitario;
    @Basic(optional = false)
    @Column(name = "id_pessoa_juridica")
    private Integer idPessoaJuridica; // Alterado para armazenar apenas o ID

    @Basic(optional = false)
    @Column(name = "id_produto")
    private Integer idProduto; // Alterado para armazenar apenas o ID

    @Basic(optional = false)
    @Column(name = "id_usuario")
    private Integer idUsuario; // Alterado para armazenar apenas o ID

    public MovimentacaoVenda() {
    }

    public MovimentacaoVenda(Integer id) {
        this.id = id;
    }

    public MovimentacaoVenda(Integer id, int quantidadeProduto, Double valorUnitario) {
        this.id = id;
        this.quantidadeProduto = quantidadeProduto;
        this.valorUnitario = valorUnitario;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getQuantidadeProduto() {
        return quantidadeProduto;
    }

    public void setQuantidadeProduto(int quantidadeProduto) {
        this.quantidadeProduto = quantidadeProduto;
    }

    public Double getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(Double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public Integer getIdPessoaJuridica() {
        return idPessoaJuridica;
    }

    public void setIdPessoaJuridica(Integer idPessoaJuridica) {
        this.idPessoaJuridica = idPessoaJuridica;
    }

    public Integer getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Integer idProduto) {
        this.idProduto = idProduto;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MovimentacaoVenda)) {
            return false;
        }
        MovimentacaoVenda other = (MovimentacaoVenda) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.MovimentacaoVenda[ id=" + id + " ]";
    }

}
