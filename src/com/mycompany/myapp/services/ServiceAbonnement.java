/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.Log;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Abonnement;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 *
 * @author Ace River
 */
public class ServiceAbonnement {
    
      public ArrayList<Abonnement> abonnements;

    public static ServiceAbonnement instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    public ServiceAbonnement() {
        req = new ConnectionRequest();
    }

    public static ServiceAbonnement getInstance() {
        if (instance == null) {
            instance = new ServiceAbonnement();
        }
        return instance;
    }
    
    
    public boolean addAbonnement(Abonnement e) {

        String nom = e.getNom();
        String description = e. getDescription();
        int prix =  e.getPrix();
      
      
       
        
        //String url = Statics.BASE_URL + "create?name=" + t.getName() + "&status=" + t.getStatus();
      String url = Statics.BASE_URL1 + "addAbonnementJSON/new?nom=" + nom + "&description=" + description + "&prix=" + prix ;


        req.setUrl(url);
        req.setPost(false);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
    
public ArrayList<Abonnement> parseAbonnements(String jsonText) {
    try {
        abonnements = new ArrayList<>();
        JSONParser j = new JSONParser();
        Map<String, Object> abonnementsListJson
                = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

        List<Map<String, Object>> list = (List<Map<String, Object>>) abonnementsListJson.get("root");
        for (Map<String, Object> obj : list) {
            Abonnement e = new Abonnement();
            float id = Float.parseFloat(obj.get("id").toString());
            e.setId((int) id);
            e.setPrix(((int) Float.parseFloat(obj.get("prix").toString())));
            e.setNom(obj.get("nom").toString());
            e.setDescription(obj.get("description").toString());
            
            
          
            if (obj.get("nom") == null) {
                e.setNom("null");
            } else {
                e.setNom(obj.get("nom").toString());

            }
            
            abonnements.add(e);
        }

    } catch (IOException ex) {
        System.out.println(ex.getMessage());
    }
    return abonnements;
}

public ArrayList<Abonnement> getAllAbonnement() {
    String url = Statics.BASE_URL1 + "AllAbonnements";
    req.setUrl(url);
    req.setPost(false);
    req.addResponseListener(new ActionListener<NetworkEvent>() {
        @Override
        public void actionPerformed(NetworkEvent evt) {
            abonnements = parseAbonnements(new String(req.getResponseData()));
            req.removeResponseListener(this);
        }
    });
    NetworkManager.getInstance().addToQueueAndWait(req);
    return abonnements;
}

public boolean updateAbonnement(Abonnement e) {
    String id = Integer.toString(e.getId());
    String nom = e.getNom();
    String description = e.getDescription();
    int prix = e.getPrix();
    

    String url = Statics.BASE_URL1 + "updateAbonnementJSON/" + id + "?nom=" + nom + "&description=" + description + "&prix=" ;

    req.setUrl(url);
    req.setPost(false);

    req.addResponseListener(new ActionListener<NetworkEvent>() {
        @Override
        public void actionPerformed(NetworkEvent evt) {
            resultOK = req.getResponseCode() == 200;
            req.removeResponseListener(this);
        }
    });

    NetworkManager.getInstance().addToQueueAndWait(req);

    return resultOK;
}
public boolean deleteAbonnement(int id) {
    String url = Statics.BASE_URL1 + "deleteAbonnementJSON/" + id;

    req.setUrl(url);
    req.setPost(false);

    req.addResponseListener(new ActionListener<NetworkEvent>() {
        @Override
        public void actionPerformed(NetworkEvent evt) {
            resultOK = req.getResponseCode() == 200;
            req.removeResponseListener(this);
        }
    });

    NetworkManager.getInstance().addToQueueAndWait(req);

    return resultOK;
}


}
