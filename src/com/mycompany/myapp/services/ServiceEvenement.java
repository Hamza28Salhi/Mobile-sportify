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
import com.mycompany.myapp.entities.Evenement;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Date;

/**
 *
 * @author HOUYEM
 */
public class ServiceEvenement {
    public ArrayList<Evenement> evenement;

    public static ServiceEvenement instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    public ServiceEvenement() {
        req = new ConnectionRequest();
    }

    public static ServiceEvenement getInstance() {
        if (instance == null) {
            instance = new ServiceEvenement();
        }
        return instance;
    }
    
    
    public boolean addEvenement(Evenement e) {

        //Date date = e.getDate();
       String type =  e.getType();
        String lieu = e.getLieu();
        String description = e.getDescription();
        String even_pic = e.getEvenPic();
        String titre = e.getTitre();
        //int nb_place =  e.getnb_place();

       
        
        //String url = Statics.BASE_URL + "create?name=" + t.getName() + "&status=" + t.getStatus();
      String url = Statics.BASE_URL1 + "addEvenementJSON/new?lieu=" + lieu + "&description=" + description + "&type=" + type + "&even_pic=" + even_pic + "&titre=" + titre ;


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
    
public ArrayList<Evenement> parseEvenements(String jsonText) {
    try {
        evenement = new ArrayList<>();
        JSONParser j = new JSONParser();
        Map<String, Object> EvenementsListJson
                = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

        List<Map<String, Object>> list = (List<Map<String, Object>>) EvenementsListJson.get("root");
        for (Map<String, Object> obj : list) {
            Evenement e = new Evenement();
            float id = Float.parseFloat(obj.get("id").toString());
            e.setId((int) id);
            //e.setDate(("date"));
           e.setType(obj.get("type").toString());
          
            e.setLieu(obj.get("lieu").toString());
            e.setDescription(obj.get("description").toString());
           //String even_pic = obj.get("even_pic") != null ? obj.get("even_pic").toString() : "";
            //e.setEven_pic(even_pic);
            
            
            evenement.add(e);
        }

    } catch (IOException ex) {
        System.out.println(ex.getMessage());
    }
    return evenement;
}

public ArrayList<Evenement> getAllEvenement() {
    String url = Statics.BASE_URL1 + "evenementmobile/list";
    req.setUrl(url);
    req.setPost(false);
    req.addResponseListener(new ActionListener<NetworkEvent>() {
        @Override
        public void actionPerformed(NetworkEvent evt) {
            evenement = parseEvenements(new String(req.getResponseData()));
            req.removeResponseListener(this);
        }
    });
    NetworkManager.getInstance().addToQueueAndWait(req);
    return evenement;
}

public boolean deleteEvenement(int id) {
    String url = Statics.BASE_URL1 + "deleteEvenementJSON/" + id;

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
