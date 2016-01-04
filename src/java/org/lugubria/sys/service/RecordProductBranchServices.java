/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lugubria.sys.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import org.lugubria.sys.config.Apli;
import org.lugubria.sys.domain.RecordProductBranchDto;
import org.lugubria.sys.service.facade.IRecordProductBranchRepository;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author angel
 */
public class RecordProductBranchServices implements IRecordProductBranchRepository {

    private RestTemplate rt;
    private Apli apli;
    private Gson gson;
    private String urlRpb;

    public RecordProductBranchServices() {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        rt = new RestTemplate();
        apli = (Apli) ctx.getBean("myApli");
        gson = new Gson();
        urlRpb = apli.getUrl() + "recordProductBranch/";
    }

    @Override
    public List<RecordProductBranchDto> getListRpbByBranch(int branchId, int type, String criteria) {
        String urlListRpbByCriteria = urlRpb + "findByCriteriaAndBranch?branchId=" + branchId+"&type="+type+"&criteria="+criteria;

        String result12 = rt.getForObject(urlListRpbByCriteria, String.class);

        List<RecordProductBranchDto> listBranch = new ArrayList<RecordProductBranchDto>();
        try {
            Type collectionType = new TypeToken<List<RecordProductBranchDto>>() {
            }.getType();
            listBranch = gson.fromJson(result12, collectionType);
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }

        return listBranch;
    }

    @Override
    public boolean validDeleteByBranchId(int branchId) {
        String urlListRpbByCriteria = urlRpb + "validDeleteBranch?branchId=" + branchId;

        String result12 = rt.getForObject(urlListRpbByCriteria, String.class);

        boolean listBranch = true;
        try {
            listBranch = gson.fromJson(result12, Boolean.class);
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }

        return listBranch;
    }

    
}
