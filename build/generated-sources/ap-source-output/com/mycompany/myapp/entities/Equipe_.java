package com.mycompany.myapp.entities;

import com.mycompany.myapp.entities.Matches;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2023-05-11T00:29:42")
@StaticMetamodel(Equipe.class)
public class Equipe_ { 

    public static volatile SingularAttribute<Equipe, String> entraineur;
    public static volatile SingularAttribute<Equipe, String> categorie;
    public static volatile SingularAttribute<Equipe, Integer> classement;
    public static volatile SingularAttribute<Equipe, Integer> id;
    public static volatile SingularAttribute<Equipe, String> nom;
    public static volatile SingularAttribute<Equipe, String> picture;
    public static volatile CollectionAttribute<Equipe, Matches> matchesCollection;
    public static volatile SingularAttribute<Equipe, String> joueurs;

}