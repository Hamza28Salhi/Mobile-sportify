package com.mycompany.myapp.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2023-03-09T09:38:58")
@StaticMetamodel(User.class)
public class User_ { 

    public static volatile SingularAttribute<User, String> password;
    public static volatile SingularAttribute<User, String> address;
    public static volatile SingularAttribute<User, String> matrFisc;
    public static volatile SingularAttribute<User, String> userPic;
    public static volatile SingularAttribute<User, String> prodCategory;
    public static volatile SingularAttribute<User, String> roles;
    public static volatile SingularAttribute<User, String> fullName;
    public static volatile SingularAttribute<User, Date> dateNaiss;
    public static volatile SingularAttribute<User, Integer> id;
    public static volatile SingularAttribute<User, String> resetToken;
    public static volatile SingularAttribute<User, String> email;
    public static volatile SingularAttribute<User, String> jobPosition;

}