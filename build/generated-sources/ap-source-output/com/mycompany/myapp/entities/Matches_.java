package com.mycompany.myapp.entities;

import com.mycompany.myapp.entities.Equipe;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2023-05-11T00:29:42")
@StaticMetamodel(Matches.class)
public class Matches_ { 

    public static volatile SingularAttribute<Matches, Date> date;
    public static volatile SingularAttribute<Matches, String> score;
    public static volatile SingularAttribute<Matches, Float> latitude;
    public static volatile SingularAttribute<Matches, Equipe> nomEquipeId;
    public static volatile SingularAttribute<Matches, Integer> id;
    public static volatile SingularAttribute<Matches, String> stade;
    public static volatile SingularAttribute<Matches, String> video;
    public static volatile SingularAttribute<Matches, String> nom;
    public static volatile SingularAttribute<Matches, Float> longitude;

}