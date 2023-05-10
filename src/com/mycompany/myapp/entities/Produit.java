/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

import java.io.Serializable;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author lenovo
 */
@Entity
@Table(name = "produit")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Produit.findAll", query = "SELECT p FROM Produit p")
    , @NamedQuery(name = "Produit.findById", query = "SELECT p FROM Produit p WHERE p.id = :id")
    , @NamedQuery(name = "Produit.findByNomProduit", query = "SELECT p FROM Produit p WHERE p.nomProduit = :nomProduit")
    , @NamedQuery(name = "Produit.findByPrixProduit", query = "SELECT p FROM Produit p WHERE p.prixProduit = :prixProduit")
    , @NamedQuery(name = "Produit.findByMarqueProduit", query = "SELECT p FROM Produit p WHERE p.marqueProduit = :marqueProduit")
    , @NamedQuery(name = "Produit.findByImage", query = "SELECT p FROM Produit p WHERE p.image = :image")
    , @NamedQuery(name = "Produit.findByQuantite", query = "SELECT p FROM Produit p WHERE p.quantite = :quantite")})
public class Produit implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "nom_produit")
    private String nomProduit;
    @Basic(optional = false)
    @NotNull
    @Column(name = "prix_produit")
    private double prixProduit;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "marque_produit")
    private String marqueProduit;
    @Size(max = 255)
    @Column(name = "image")
    private String image;
    @Basic(optional = false)
    @NotNull
    @Column(name = "quantite")
    private int quantite;
    @JoinColumn(name = "categorie_id", referencedColumnName = "id")
    @ManyToOne
    private Categorie categorieId;

    public Produit() {
    }

    public Produit(Integer id) {
        this.id = id;
    }

    public Produit(Integer id, String nomProduit, double prixProduit, String marqueProduit, int quantite) {
        this.id = id;
        this.nomProduit = nomProduit;
        this.prixProduit = prixProduit;
        this.marqueProduit = marqueProduit;
        this.quantite = quantite;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomProduit() {
        return nomProduit;
    }

    public void setNomProduit(String nomProduit) {
        this.nomProduit = nomProduit;
    }

    public double getPrixProduit() {
        return prixProduit;
    }

    public void setPrixProduit(double prixProduit) {
        this.prixProduit = prixProduit;
    }

    public String getMarqueProduit() {
        return marqueProduit;
    }

    public void setMarqueProduit(String marqueProduit) {
        this.marqueProduit = marqueProduit;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public Categorie getCategorieId() {
        return categorieId;
    }

    public void setCategorieId(Categorie categorieId) {
        this.categorieId = categorieId;
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
        if (!(object instanceof Produit)) {
            return false;
        }
        Produit other = (Produit) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.myapp.entities.Produit[ id=" + id + " ]";
    }
    
}
