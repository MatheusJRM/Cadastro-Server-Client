package model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 *
 * @author mathe
 */
@Entity
@Table(name = "Pessoa_Juridica")
@DiscriminatorValue("2")
@PrimaryKeyJoinColumn(name = "pessoa_id")
@NamedQueries({
    @NamedQuery(name = "PessoaJuridica.findAll", query = "SELECT p FROM PessoaJuridica p"),
    @NamedQuery(name = "PessoaJuridica.findById", query = "SELECT pj FROM PessoaJuridica pj WHERE pj.id = :id AND pj.idTipoPessoa.id = 2"),
    @NamedQuery(name = "PessoaJuridica.findByCnpj", query = "SELECT p FROM PessoaJuridica p WHERE p.cnpj = :cnpj")
})
public class PessoaJuridica extends Pessoa implements Serializable {

    private static final long serialVersionUID = 1L;

    @Basic(optional = false)
    @Column(name = "cnpj")
    private String cnpj;

    public PessoaJuridica() {
        super(); // Chama o construtor da classe base
    }

    public PessoaJuridica(Integer id, String nome, String telefone, TiposPessoa idTipoPessoa, String cnpj) {
        super(id, nome, telefone, idTipoPessoa); // Chama o construtor da classe base
        this.cnpj = cnpj;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    @Override
    public int hashCode() {
        int hash = super.hashCode(); // Inclui o hash da classe base
        hash += (getId() != null ? getId().hashCode() : 0); // Usando getId() da classe base
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof PessoaJuridica)) {
            return false;
        }
        PessoaJuridica other = (PessoaJuridica) object;
        return super.equals(object) && (this.getId() != null || other.getId() == null) && (this.getId() == null || this.getId().equals(other.getId())); // Usando getId() da classe base
    }

    @Override
    public String toString() {
        return "model.PessoaJuridica[ id=" + getId() + ", cnpj=" + cnpj + " ]"; // Usando o método da classe base
    }
}
