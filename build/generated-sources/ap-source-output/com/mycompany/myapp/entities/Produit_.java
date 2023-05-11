package com.mycompany.myapp.entities;

import com.mycompany.myapp.entities.Categorie;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2023-05-02T11:01:36")
@StaticMetamodel(Produit.class)
public class Produit_ { 

    public static volatile SingularAttribute<Produit, String> image;
    public static volatile SingularAttribute<Produit, Categorie> categorieId;
    public static volatile SingularAttribute<Produit, String> marqueProduit;
    public static volatile SingularAttribute<Produit, Integer> id;
    public static volatile SingularAttribute<Produit, String> nomProduit;
    public static volatile SingularAttribute<Produit, Double> prixProduit;
    public static volatile SingularAttribute<Produit, Integer> quantite;

}