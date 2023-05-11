package com.mycompany.myapp.entities;

import com.mycompany.myapp.entities.Categorie;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2023-03-10T21:31:45")
@StaticMetamodel(Abonnement.class)
public class Abonnement_ { 

    public static volatile SingularAttribute<Abonnement, Integer> prix;
    public static volatile SingularAttribute<Abonnement, String> description;
    public static volatile SingularAttribute<Abonnement, Integer> id;
    public static volatile CollectionAttribute<Abonnement, Categorie> categorieCollection;
    public static volatile SingularAttribute<Abonnement, String> nom;

}