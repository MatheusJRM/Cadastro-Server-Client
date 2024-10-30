/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author mathe
 */
@Entity
@Table(name = "Movimentacao_Compra")
@NamedQueries({
    @NamedQuery(name = "MovimentacaoCompra.findAll", query = "SELECT m FROM MovimentacaoCompra m"),
    @NamedQuery(name = "MovimentacaoCompra.findById", query = "SELECT m FROM MovimentacaoCompra m WHERE m.id = :id"),
    @NamedQuery(name = "MovimentacaoCompra.findByQuantidadeProduto", query = "SELECT m FROM MovimentacaoCompra m WHERE m.quantidadeProduto = :quantidadeProduto"),
    @NamedQuery(name = "MovimentacaoCompra.findByValorUnitario", query = "SELECT m FROM MovimentacaoCompra m WHERE m.valorUnitario = :valorUnitario")})
public class MovimentacaoCompra implements Serializable {

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
    private BigDecimal valorUnitario;
    @JoinColumn(name = "id_pessoa_fisica", referencedColumnName = "pessoa_id")
    @ManyToOne(optional = false)
    private PessoaFisica idPessoaFisica;
    @JoinColumn(name = "id_produto", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Produto idProduto;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Usuario idUsuario;

    public MovimentacaoCompra() {
    }

    public MovimentacaoCompra(Integer id) {
        this.id = id;
    }

    public MovimentacaoCompra(Integer id, int quantidadeProduto, BigDecimal valorUnitario) {
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

    public BigDecimal getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(BigDecimal valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public PessoaFisica getIdPessoaFisica() {
        return idPessoaFisica;
    }

    public void setIdPessoaFisica(PessoaFisica idPessoaFisica) {
        this.idPessoaFisica = idPessoaFisica;
    }

    public Produto getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Produto idProduto) {
        this.idProduto = idProduto;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
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
        if (!(object instanceof MovimentacaoCompra)) {
            return false;
        }
        MovimentacaoCompra other = (MovimentacaoCompra) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.MovimentacaoCompra[ id=" + id + " ]";
    }
    
}
