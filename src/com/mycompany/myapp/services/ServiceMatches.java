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
import com.mycompany.myapp.entities.Equipe;
import com.mycompany.myapp.entities.Matches;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Ace River
 */
public class ServiceMatches {
    
      public ArrayList<Matches> matches;

    public static ServiceMatches instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    public ServiceMatches() {
        req = new ConnectionRequest();
    }

    public static ServiceMatches getInstance() {
        if (instance == null) {
            instance = new ServiceMatches();
        }
        return instance;
    }
    
     public boolean addMatches(Matches e) {

        String name = e.getNom();
        String stade = e. getStade();
        String score = e. getScore();
        Equipe nom_equipe = e.getNomEquipeId();
        Date date = e.getDate();
        Float latitude =  e.getLatitude();
        Float longitude = e.getLongitude();
      
       
        
        //String url = Statics.BASE_URL + "create?name=" + t.getName() + "&status=" + t.getStatus();
      String url = Statics.BASE_URL1 + "addMatchesJSON/new?nom=" + name + "&stade=" + stade + "&score=" + score + "&nom_equipe=" + nom_equipe+ "&date=" + date + "&latitude=" + latitude + "&longitude=" + longitude;


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
    
     
     
     public ArrayList<Matches> parseMatches(String jsonText) {
    try {
        matches = new ArrayList<>();
        JSONParser j = new JSONParser();
        Map<String, Object> matchesListJson
                = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

        List<Map<String, Object>> list = (List<Map<String, Object>>) matchesListJson.get("root");
        for (Map<String, Object> obj : list) {
            Matches e = new Matches();
            float id = Float.parseFloat(obj.get("id").toString());
            e.setId((int) id);
          try {
    float latitude = Float.parseFloat(obj.get("latitude").toString());
    e.setLatitude(latitude);
} catch (NumberFormatException | NullPointerException ex) {
    // handle the exception, e.g. log an error or set a default value
}

try {
    float longitude = Float.parseFloat(obj.get("longitude").toString());
    e.setLongitude(longitude);
} catch (NumberFormatException | NullPointerException ex) {
    // handle the exception, e.g. log an error or set a default value
}
            e.setStade(obj.get("stade").toString());
            if (obj.get("nom") == null) {
                e.setNom("null");
            } else {
                e.setNom(obj.get("nom").toString());

            }
            
            matches.add(e);
        }

    } catch (IOException ex) {
        System.out.println(ex.getMessage());
    }
    return matches;
}

     public ArrayList<Matches> getAllMatches() {
    String url = Statics.BASE_URL1 + "matchesmobile/list";
    req.setUrl(url);
    req.setPost(false);
    req.addResponseListener(new ActionListener<NetworkEvent>() {
        @Override
        public void actionPerformed(NetworkEvent evt) {
            matches = parseMatches(new String(req.getResponseData()));
            req.removeResponseListener(this);
        }
    });
    NetworkManager.getInstance().addToQueueAndWait(req);
    return matches;
}

     
     public boolean updateMatches(Matches e) {
        String id = Integer.toString(e.getId());
        String name = e.getNom();
        String stade = e. getStade();
        String score = e. getScore();
        Equipe nom_equipe = e.getNomEquipeId();
        Date date = e.getDate();
        float latitude =  e.getLatitude();
        float longitude = e.getLongitude();

    String url = Statics.BASE_URL1 + "updateMatchesJSON/" + id + "?nom=" + name + "&stade=" + stade + "&score=" + score + "&nom_equipe=" + nom_equipe+ "&date=" + date + "&latitude=" + latitude + "&longitude=" + longitude;

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
public boolean deleteMatches(int id) {
    String url = Statics.BASE_URL1 + "deleteMatchesJSON/" + id;

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
