/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
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
@Table(name = "Pessoa")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "id_tipo_pessoa", discriminatorType = DiscriminatorType.INTEGER)
@NamedQueries({
    @NamedQuery(name = "Pessoa.findAll", query = "SELECT p FROM Pessoa p"),
    @NamedQuery(name = "Pessoa.findById", query = "SELECT p FROM Pessoa p WHERE p.id = :id"),
    @NamedQuery(name = "Pessoa.findByNome", query = "SELECT p FROM Pessoa p WHERE p.nome = :nome"),
    @NamedQuery(name = "Pessoa.findByTelefone", query = "SELECT p FROM Pessoa p WHERE p.telefone = :telefone")})
public abstract class Pessoa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;

    @Basic(optional = false)
    @Column(name = "nome")
    private String nome;

    @Basic(optional = false)
    @Column(name = "telefone")
    private String telefone;

    @JoinColumn(name = "id_endereco", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Endereco idEndereco;

    @JoinColumn(name = "id_tipo_pessoa", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private TiposPessoa idTipoPessoa;

    public Pessoa() {
    }

    public Pessoa(Integer id) {
        this.id = id;
    }

    public Pessoa(Integer id, String nome, String telefone, TiposPessoa idTipoPessoa) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.idTipoPessoa = idTipoPessoa;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Endereco getIdEndereco() {
        return idEndereco;
    }

    public void setIdEndereco(Endereco idEndereco) {
        this.idEndereco = idEndereco;
    }

    public TiposPessoa getIdTipoPessoa() {
        return idTipoPessoa;
    }

    public void setIdTipoPessoa(TiposPessoa idTipoPessoa) {
        this.idTipoPessoa = idTipoPessoa;
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
        if (!(object instanceof Pessoa)) {
            return false;
        }
        Pessoa other = (Pessoa) object;
        if (this.id != null || other.id == null && (this.id == null || this.id.equals(other.id))) {
        } else {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Pessoa[ id=" + id + " ]";
    }

}
