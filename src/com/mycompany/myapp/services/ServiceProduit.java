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
import com.mycompany.myapp.entities.Produit;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author lenovo
 */
public class ServiceProduit {
    
    public ArrayList<Produit> produits;

    public static ServiceProduit instance = null;
    public boolean resultOK;
    private ConnectionRequest req;
    
     public ServiceProduit() {
        req = new ConnectionRequest();
    }
      public static ServiceProduit getInstance() {
        if (instance == null) {
            instance = new ServiceProduit();
        }
        return instance;
    }
    
       public boolean addProduit(Produit p) {

        String nom = p.getNomProduit();
        double prix =  p.getPrixProduit();
        String marque = p.getMarqueProduit();
        String image = p.getImage();
        int quantite = p.getQuantite();
        
    
     //String url = Statics.BASE_URL + "create?name=" + t.getName() + "&status=" + t.getStatus();
      String url = Statics.BASE_URL1 + "addProduitJSON/new?nom_produit=" + nom + "&prix_produit=" + prix + "&marque_produit=" + marque + "&image=" + image + "&quantite=" + quantite;
      
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
       
       public ArrayList<Produit> parseProduits(String jsonText) {
    try {
        produits = new ArrayList<>();
        JSONParser j = new JSONParser();
        Map<String, Object> produitsListJson
                = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
        
        
        
         List<Map<String, Object>> list = (List<Map<String, Object>>) produitsListJson.get("root");
        for (Map<String, Object> obj : list) {
            Produit p = new Produit();
            float id = Float.parseFloat(obj.get("id").toString());
            p.setId((int) id);
            
             p.setPrixProduit(((double) Float.parseFloat(obj.get("prix_produit").toString())));
            p.setMarqueProduit(obj.get("marque_produit").toString());
             p.setQuantite(((int) Float.parseFloat(obj.get("quantite").toString())));
           
           String image = obj.get("image") != null ? obj.get("image").toString() : "";
            p.setImage(image);
            if (obj.get("nom_produit") == null) {
                p.setNomProduit("null");
            } else {
                p.setNomProduit(obj.get("nom_produit").toString());

            }
            
            produits.add(p);
        }

       } catch (IOException ex) {
        System.out.println(ex.getMessage());
    }
    return produits;
}
       
       public ArrayList<Produit> getAllProduits() {
    String url = Statics.BASE_URL1 + "produitmobile/list";
    req.setUrl(url);
    req.setPost(false);
    req.addResponseListener(new ActionListener<NetworkEvent>() {
        @Override
        public void actionPerformed(NetworkEvent evt) {
            produits = parseProduits(new String(req.getResponseData()));
            req.removeResponseListener(this);
        }
    });
    NetworkManager.getInstance().addToQueueAndWait(req);
    return produits;
}
       
       
       public boolean updateProduit(Produit p) {
    String id = Integer.toString(p.getId());
    String nom = p.getNomProduit();
    double prix = p.getPrixProduit();
    String marque = p.getMarqueProduit();
     String image = p.getImage();
    
    
    int quantite = p.getQuantite();
     
    

    String url = Statics.BASE_URL1 + "updateProduitJSON/" + id + "?nomProduit=" + nom + "&prixProduit=" + prix + "&marqueProduit=" + marque + "&image=" + image + "&quantite=" + quantite;

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
       
       public boolean deleteProduit(int id) {
    String url = Statics.BASE_URL1 + "deleteProduitJSON/" + id;

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
