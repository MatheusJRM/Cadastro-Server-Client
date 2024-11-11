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
    @NamedQuery(name = "TiposPessoa.findByTipoPessoa", query = "SELECT t FROM TiposPessoa t WHERE t.tipoPessoa = :tipoPessoa")
})
public class TiposPessoa implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;

    @Column(name = "Tipo_Pessoa", nullable = false) // Definido como não nulo
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
        return (id != null ? id.hashCode() : 0);
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof TiposPessoa)) {
            return false;
        }
        TiposPessoa other = (TiposPessoa) object;
        return (this.id != null || other.id == null) && (this.id == null || this.id.equals(other.id));
    }

    @Override
    public String toString() {
        return "model.TiposPessoa[ id=" + id + ", tipoPessoa=" + tipoPessoa + " ]"; // Inclui o tipoPessoa
    }
}
