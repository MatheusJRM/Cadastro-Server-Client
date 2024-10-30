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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author mathe
 */
@Entity
@Table(name = "Tipos_Pessoa")
@NamedQueries({
    @NamedQuery(name = "TiposPessoa.findAll", query = "SELECT t FROM TiposPessoa t"),
    @NamedQuery(name = "TiposPessoa.findById", query = "SELECT t FROM TiposPessoa t WHERE t.id = :id"),
    @NamedQuery(name = "TiposPessoa.findByTipoPessoa", query = "SELECT t FROM TiposPessoa t WHERE t.tipoPessoa = :tipoPessoa")})
public class TiposPessoa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "Tipo_Pessoa")
    private String tipoPessoa;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoPessoa")
    private Collection<Pessoa> pessoaCollection;

    public TiposPessoa() {
    }

    public TiposPessoa(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTipoPessoa() {
        return tipoPessoa;
    }

    public void setTipoPessoa(String tipoPessoa) {
        this.tipoPessoa = tipoPessoa;
    }

    public Collection<Pessoa> getPessoaCollection() {
        return pessoaCollection;
    }

    public void setPessoaCollection(Collection<Pessoa> pessoaCollection) {
        this.pessoaCollection = pessoaCollection;
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
        if (!(object instanceof TiposPessoa)) {
            return false;
        }
        TiposPessoa other = (TiposPessoa) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.TiposPessoa[ id=" + id + " ]";
    }
    
}
