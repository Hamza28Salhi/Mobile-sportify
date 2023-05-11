/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.ui.Dialog;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.mycompany.myapp.gui.SessionManager;
import com.mycompany.myapp.gui.ProfileForm;
import com.codename1.ui.TextField;
import com.codename1.ui.util.Resources;


/**
 *
 * @author azizo
 */
public class ServiceUser {
    
    public ArrayList<User> user;

    public static ServiceUser instance = null;
    public boolean resultOK;
     private User result; // declare result as a member variable
    private ConnectionRequest req;
    String json;

    
    
    public ServiceUser() {
        req = new ConnectionRequest();
    }

    public static ServiceUser getInstance() {
        if (instance == null) {
            instance = new ServiceUser();
        }
        return instance;
    }
   
   
    public boolean addUser(User e) {

        String full_name = e.getFullName();
        String email = e. getEmail();
        String address = e. getAddress();
        String password = e. getPassword();
     
       // Check if the email already exists in the database
    ArrayList<User> existingUsers = getAllUser();
    for (User user : existingUsers) {
        if (user.getEmail().equals(email)) {
            // Email already exists, return false
            return false;
        }
    }
    
    
       
        //String url = Statics.BASE_URL + "create?name=" + t.getName() + "&status=" + t.getStatus();
      String url = Statics.BASE_URL1 + "addUserJSON/new?full_name=" + full_name + "&email=" + email + "&address=" + address + "&password=" + password ;


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
   
public ArrayList<User> parseUser(String jsonText) {
    try {
        user = new ArrayList<>();
        JSONParser j = new JSONParser();
        Map<String, Object> userListJson
                = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

        List<Map<String, Object>> list = (List<Map<String, Object>>) userListJson.get("root");
        for (Map<String, Object> obj : list) {
            User e = new User();
            
            
            e.setFullName(obj.get("full_name").toString());
            e.setEmail(obj.get("email").toString());
            
            e.setPassword(obj.get("password").toString());
          if (obj.get("Address") == null) {
                e.setAddress("null");
            } else {
                e.setAddress(obj.get("Address").toString());

            }
            
           
            user.add(e);
        }

    } catch (IOException ex) {
        System.out.println(ex.getMessage());
    }
    return user;
}

public ArrayList<User> getAllUser() {
    String url = Statics.BASE_URL1 + "AllUsers";
    req.setUrl(url);
    req.setPost(false);
    req.addResponseListener(new ActionListener<NetworkEvent>() {
        @Override
        public void actionPerformed(NetworkEvent evt) {
            user = parseUser(new String(req.getResponseData()));
            req.removeResponseListener(this);
        }
    });
    NetworkManager.getInstance().addToQueueAndWait(req);
    return user;
}

public void signin(TextField email,TextField password, Resources rs ) {
        
        
        String url = Statics.BASE_URL1+"signin?email="+email.getText().toString()+"&password="+password.getText().toString();
        req = new ConnectionRequest(url, false); //false ya3ni url mazlt matba3thtich lel server
        req.setUrl(url);
        
        req.addResponseListener((e) ->{
            
            JSONParser j = new JSONParser();
            
            String json = new String(req.getResponseData()) + "";
            
            
            try {
            
            if(json.equals("failed")) {
                Dialog.show("Echec d'authentification","Email ou mot de passe éronné","OK",null);
            }
            else {
                System.out.println("data =="+json);
                
                Map<String,Object> userData = j.parseJSON(new CharArrayReader(json.toCharArray()));
                
                
             
                //Session 
                float id = Float.parseFloat(userData.get("id").toString());
                SessionManager.setId((int)id);//jibt id ta3 userData ly3ml login w sajltha fi session ta3i
                
                SessionManager.setPassowrd(userData.get("password").toString());
                SessionManager.setFullName(userData.get("fullName").toString());
                SessionManager.setEmail(userData.get("email").toString());
                SessionManager.setAddress(userData.get("address").toString());
                
                //photo 
                
                if(userData.get("photo") != null)
                    SessionManager.setPhoto(userData.get("photo").toString());
                
                
                if(userData.size() >0 ) // l9a user
                   
                    new ProfileForm(rs).show();
                    
                    }
            
            }catch(Exception ex) {
                ex.printStackTrace();
            }
            
            
            
        });
    
         //ba3d execution ta3 requete ely heya url nestanaw response ta3 server.
        NetworkManager.getInstance().addToQueueAndWait(req);
    }



public boolean deleteUser(int id) {
    String url = Statics.BASE_URL1 + "deleteUserJSON/" + id;
    ConnectionRequest req = new ConnectionRequest();
    req.setUrl(url);
    req.setHttpMethod("DELETE");
    req.addResponseListener((evt) -> {
        if (req.getResponseCode() == 200) {
            resultOK = true;
        } else {
            resultOK = false;
        }
    });
    NetworkManager.getInstance().addToQueueAndWait(req);
    return resultOK;
}


public String getPasswordByEmail(String email, Resources rs ) {
        
        
        String url = Statics.BASE_URL+"/user/getPasswordByEmail?email="+email;
        req = new ConnectionRequest(url, false); //false ya3ni url mazlt matba3thtich lel server
        req.setUrl(url);
        
        req.addResponseListener((e) ->{
            
            JSONParser j = new JSONParser();
            
             json = new String(req.getResponseData()) + "";
            
            
            try {
            
          
                System.out.println("data =="+json);
                
                Map<String,Object> password = j.parseJSON(new CharArrayReader(json.toCharArray()));
                
                
            
            
            }catch(Exception ex) {
                ex.printStackTrace();
            }
            
            
            
        });
    
         //ba3d execution ta3 requete ely heya url nestanaw response ta3 server.
        NetworkManager.getInstance().addToQueueAndWait(req);
    return json;
    }
}
