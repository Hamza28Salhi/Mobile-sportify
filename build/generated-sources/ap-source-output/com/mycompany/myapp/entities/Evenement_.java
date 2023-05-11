package com.mycompany.myapp.entities;

import com.mycompany.myapp.entities.Reservation;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2023-03-09T10:12:32")
@StaticMetamodel(Evenement.class)
public class Evenement_ { 

    public static volatile SingularAttribute<Evenement, Date> date;
    public static volatile SingularAttribute<Evenement, Integer> nbPlace;
    public static volatile SingularAttribute<Evenement, String> evenPic;
    public static volatile SingularAttribute<Evenement, String> titre;
    public static volatile SingularAttribute<Evenement, String> description;
    public static volatile CollectionAttribute<Evenement, Reservation> reservationCollection;
    public static volatile SingularAttribute<Evenement, Integer> id;
    public static volatile SingularAttribute<Evenement, String> type;
    public static volatile SingularAttribute<Evenement, String> lieu;

}