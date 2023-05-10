package com.mycompany.myapp.entities;

import com.mycompany.myapp.entities.Evenement;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2023-03-09T10:12:32")
@StaticMetamodel(Reservation.class)
public class Reservation_ { 

    public static volatile SingularAttribute<Reservation, String> paiement;
    public static volatile SingularAttribute<Reservation, String> adresse;
    public static volatile SingularAttribute<Reservation, String> telephone;
    public static volatile SingularAttribute<Reservation, Evenement> evenementId;
    public static volatile SingularAttribute<Reservation, Integer> id;
    public static volatile SingularAttribute<Reservation, String> nom;
    public static volatile SingularAttribute<Reservation, String> prenom;

}