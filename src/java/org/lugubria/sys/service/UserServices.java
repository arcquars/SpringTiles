/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lugubria.sys.service;

import com.google.gson.Gson;
import java.util.HashMap;
import java.util.Map;
import org.lugubria.sys.config.Apli;
import org.lugubria.sys.domain.Person;
import org.lugubria.sys.domain.User;
import org.lugubria.sys.service.facade.IUserRepository;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author angel
 */
public class UserServices implements IUserRepository {

    private RestTemplate rt;
    private Apli apli;
    private Gson gson;
    private String urlUser;
    
    public UserServices(){
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        rt = new RestTemplate();
        apli = (Apli)ctx.getBean("myApli");
        gson = new Gson();
        urlUser = apli.getUrl()+"user/";
    }
    
    @Override
    public boolean validateUser(String username, String password) {
        String urlUserValid = urlUser+"valid?login="+username+"&password="+password;

        System.out.println("direccion:: "+urlUserValid);
        String result12 = rt.getForObject(urlUserValid, String.class);
        
        Boolean validdd = gson.fromJson(result12, Boolean.class);
        return validdd.booleanValue();
    }

    @Override
    public int getUserId(String username, String password){
        String urlUserId = urlUser+"getUserId?login="+username+"&password="+password;

        String result12 = rt.getForObject(urlUserId, String.class);
        
        Integer userId = gson.fromJson(result12, Integer.class);
        return userId.intValue();
    }
    
    @Override
    public User getUser(String username, String password) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Map<String, String> getMenuMain() {
        Map<String, String> hm = new HashMap<String, String>();
        hm.put("Personal", "/SpringTiles/personalpp.html");
        hm.put("Productos", "/SpringTiles/productpp.html");
        hm.put("Tienda", "/SpringTiles/branchpp.html");
        hm.put("Inventarios", "/SpringTiles/inventorypp.html");
        hm.put("Categoria", "/SpringTiles/categorypp.html");
        hm.put("Pagos", "/SpringTiles/creditpp.html");

        return hm;
    }

    public Person getPerson(int person_id) {
        Person person = new Person();
        person.setNames("Pedro");
        person.setLastname("Murillo");
        person.setPerId(new Integer(person_id));

        return person;
    }
    
    
}
