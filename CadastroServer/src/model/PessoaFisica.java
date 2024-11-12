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
@Table(name = "Pessoa_Fisica")
@DiscriminatorValue("1")
@PrimaryKeyJoinColumn(name = "pessoa_id")
@NamedQueries({
    @NamedQuery(name = "PessoaFisica.findAll", query = "SELECT p FROM PessoaFisica p"),
    @NamedQuery(name = "PessoaFisica.findByPessoaId", query = "SELECT pf FROM PessoaFisica pf WHERE pf.id = :id AND pf.idTipoPessoa.id = 1"),
    @NamedQuery(name = "PessoaFisica.findByCpf", query = "SELECT p FROM PessoaFisica p WHERE p.cpf = :cpf")
})
public class PessoaFisica extends Pessoa implements Serializable {

    private static final long serialVersionUID = 1L;

    @Basic(optional = false)
    @Column(name = "cpf")
    private String cpf;

    public PessoaFisica() {
        super(); // Chama o construtor da classe base
    }

    public PessoaFisica(Integer id, String nome, String telefone, TiposPessoa idTipoPessoa, String cpf) {
        super(id, nome, telefone, idTipoPessoa);
        this.cpf = cpf;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @Override
    public int hashCode() {
        int hash = super.hashCode(); // Inclui o hash da classe base
        hash += (getId() != null ? getId().hashCode() : 0); // Alterado para getId()
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof PessoaFisica)) {
            return false;
        }
        PessoaFisica other = (PessoaFisica) object;
        return super.equals(object) && (this.getId() != null || other.getId() == null) && (this.getId() == null || this.getId().equals(other.getId())); // Alterado para getId()
    }

    @Override
    public String toString() {
        return "model.PessoaFisica[ pessoaId=" + getId() + ", cpf=" + cpf + " ]"; // Usa o método da classe base
    }
}
