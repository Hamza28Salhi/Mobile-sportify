/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author HOUYEM
 */
@Entity
@Table(name = "evenement")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Evenement.findAll", query = "SELECT e FROM Evenement e")
    , @NamedQuery(name = "Evenement.findById", query = "SELECT e FROM Evenement e WHERE e.id = :id")
    , @NamedQuery(name = "Evenement.findByDate", query = "SELECT e FROM Evenement e WHERE e.date = :date")
    , @NamedQuery(name = "Evenement.findByType", query = "SELECT e FROM Evenement e WHERE e.type = :type")
    , @NamedQuery(name = "Evenement.findByLieu", query = "SELECT e FROM Evenement e WHERE e.lieu = :lieu")
    , @NamedQuery(name = "Evenement.findByDescription", query = "SELECT e FROM Evenement e WHERE e.description = :description")
    , @NamedQuery(name = "Evenement.findByEvenPic", query = "SELECT e FROM Evenement e WHERE e.evenPic = :evenPic")
    , @NamedQuery(name = "Evenement.findByTitre", query = "SELECT e FROM Evenement e WHERE e.titre = :titre")
    , @NamedQuery(name = "Evenement.findByNbPlace", query = "SELECT e FROM Evenement e WHERE e.nbPlace = :nbPlace")})
public class Evenement implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "type")
    private String type;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "lieu")
    private String lieu;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "description")
    private String description;
    @Size(max = 255)
    @Column(name = "even_pic")
    private String evenPic;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "titre")
    private String titre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "nb_place")
    private int nbPlace;
    @OneToMany(mappedBy = "evenementId")
    private Collection<Reservation> reservationCollection;

    public Evenement() {
    }

    public Evenement(Integer id) {
        this.id = id;
    }

    public Evenement(Integer id, Date date, String type, String lieu, String description, String titre, int nbPlace) {
        this.id = id;
        this.date = date;
        this.type = type;
        this.lieu = lieu;
        this.description = description;
        this.titre = titre;
        this.nbPlace = nbPlace;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEvenPic() {
        return evenPic;
    }

    public void setEvenPic(String evenPic) {
        this.evenPic = evenPic;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public int getNbPlace() {
        return nbPlace;
    }

    public void setNbPlace(int nbPlace) {
        this.nbPlace = nbPlace;
    }

    @XmlTransient
    public Collection<Reservation> getReservationCollection() {
        return reservationCollection;
    }

    public void setReservationCollection(Collection<Reservation> reservationCollection) {
        this.reservationCollection = reservationCollection;
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
        if (!(object instanceof Evenement)) {
            return false;
        }
        Evenement other = (Evenement) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.myapp.entities.Evenement[ id=" + id + " ]";
    }
    
}
