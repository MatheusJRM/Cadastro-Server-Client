/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author mathe
 */
@Entity
@Table(name = "Pessoa_Fisica")
@NamedQueries({
    @NamedQuery(name = "PessoaFisica.findAll", query = "SELECT p FROM PessoaFisica p"),
    @NamedQuery(name = "PessoaFisica.findByPessoaId", query = "SELECT p FROM PessoaFisica p WHERE p.pessoaId = :pessoaId"),
    @NamedQuery(name = "PessoaFisica.findByIdTipoPessoa", query = "SELECT p FROM PessoaFisica p WHERE p.idTipoPessoa = :idTipoPessoa"),
    @NamedQuery(name = "PessoaFisica.findByCpf", query = "SELECT p FROM PessoaFisica p WHERE p.cpf = :cpf")})
public class PessoaFisica implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "pessoa_id")
    private Integer pessoaId;
    @Basic(optional = false)
    @Column(name = "id_tipo_pessoa")
    private int idTipoPessoa;
    @Basic(optional = false)
    @Column(name = "cpf")
    private String cpf;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPessoaFisica")
    private Collection<MovimentacaoCompra> movimentacaoCompraCollection;
    @JoinColumn(name = "pessoa_id", referencedColumnName = "id", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Pessoa pessoa;

    public PessoaFisica() {
    }

    public PessoaFisica(Integer pessoaId) {
        this.pessoaId = pessoaId;
    }

    public PessoaFisica(Integer pessoaId, int idTipoPessoa, String cpf) {
        this.pessoaId = pessoaId;
        this.idTipoPessoa = idTipoPessoa;
        this.cpf = cpf;
    }

    public Integer getPessoaId() {
        return pessoaId;
    }

    public void setPessoaId(Integer pessoaId) {
        this.pessoaId = pessoaId;
    }

    public int getIdTipoPessoa() {
        return idTipoPessoa;
    }

    public void setIdTipoPessoa(int idTipoPessoa) {
        this.idTipoPessoa = idTipoPessoa;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Collection<MovimentacaoCompra> getMovimentacaoCompraCollection() {
        return movimentacaoCompraCollection;
    }

    public void setMovimentacaoCompraCollection(Collection<MovimentacaoCompra> movimentacaoCompraCollection) {
        this.movimentacaoCompraCollection = movimentacaoCompraCollection;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pessoaId != null ? pessoaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PessoaFisica)) {
            return false;
        }
        PessoaFisica other = (PessoaFisica) object;
        if ((this.pessoaId == null && other.pessoaId != null) || (this.pessoaId != null && !this.pessoaId.equals(other.pessoaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.PessoaFisica[ pessoaId=" + pessoaId + " ]";
    }
    
}
