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
@Table(name = "Pessoa_Juridica")
@NamedQueries({
    @NamedQuery(name = "PessoaJuridica.findAll", query = "SELECT p FROM PessoaJuridica p"),
    @NamedQuery(name = "PessoaJuridica.findByPessoaId", query = "SELECT p FROM PessoaJuridica p WHERE p.pessoaId = :pessoaId"),
    @NamedQuery(name = "PessoaJuridica.findByIdTipoPessoa", query = "SELECT p FROM PessoaJuridica p WHERE p.idTipoPessoa = :idTipoPessoa"),
    @NamedQuery(name = "PessoaJuridica.findByCnpj", query = "SELECT p FROM PessoaJuridica p WHERE p.cnpj = :cnpj")})
public class PessoaJuridica implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "pessoa_id")
    private Integer pessoaId;
    @Basic(optional = false)
    @Column(name = "id_tipo_pessoa")
    private int idTipoPessoa;
    @Basic(optional = false)
    @Column(name = "cnpj")
    private String cnpj;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPessoaJuridica")
    private Collection<MovimentacaoVenda> movimentacaoVendaCollection;
    @JoinColumn(name = "pessoa_id", referencedColumnName = "id", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Pessoa pessoa;

    public PessoaJuridica() {
    }

    public PessoaJuridica(Integer pessoaId) {
        this.pessoaId = pessoaId;
    }

    public PessoaJuridica(Integer pessoaId, int idTipoPessoa, String cnpj) {
        this.pessoaId = pessoaId;
        this.idTipoPessoa = idTipoPessoa;
        this.cnpj = cnpj;
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

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public Collection<MovimentacaoVenda> getMovimentacaoVendaCollection() {
        return movimentacaoVendaCollection;
    }

    public void setMovimentacaoVendaCollection(Collection<MovimentacaoVenda> movimentacaoVendaCollection) {
        this.movimentacaoVendaCollection = movimentacaoVendaCollection;
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
        if (!(object instanceof PessoaJuridica)) {
            return false;
        }
        PessoaJuridica other = (PessoaJuridica) object;
        if ((this.pessoaId == null && other.pessoaId != null) || (this.pessoaId != null && !this.pessoaId.equals(other.pessoaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.PessoaJuridica[ pessoaId=" + pessoaId + " ]";
    }
    
}