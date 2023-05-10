package com.mycompany.myapp.entities;

import com.mycompany.myapp.entities.Produit;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2023-03-09T10:12:32")
@StaticMetamodel(Categorie.class)
public class Categorie_ { 

    public static volatile SingularAttribute<Categorie, String> nomCategorie;
    public static volatile CollectionAttribute<Categorie, Produit> produitCollection;
    public static volatile SingularAttribute<Categorie, Integer> id;

}