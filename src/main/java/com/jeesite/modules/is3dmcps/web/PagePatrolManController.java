package com.jeesite.modules.is3dmcps.web;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jeesite.common.collect.MapUtils;
import com.jeesite.modules.is3dmcps.entity.IsPatrolRec;
import com.jeesite.modules.is3dmcps.service.IsPatrolRecService;
import com.jeesite.modules.is3dmcps.service.IsPatrolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jeesite.common.collect.ListUtils;
import com.jeesite.common.web.BaseController;

import javax.servlet.http.HttpServletRequest;

/**
 * 
 * @author ZX
 *生产管理主界面
 */
@CrossOrigin
@RestController
@RequestMapping(value = "static")
public class PagePatrolManController extends BaseController{
    @Autowired
    IsPatrolRecService isPatrolRecService;
    @Autowired
    IsPatrolService isPatrolService;
    /**
     * 巡检弹窗
     * Post:deviceID or deviceName
     * @return
     * Json:
     * [{ “patrolID”:”xxxx”,“ patrolName”:”xxx”,” patrolContent”:”xxx….” },{ “patrolID”:”xxxx”,“ patrolName”:”xxx”,” patrolContent”:”xxx….” }]
     */
	@RequestMapping(value = {"patrolPop", ""})
	public List<Map<String, Object>> patrolPop() {
		List<Map<String, Object>> mapList = ListUtils.newArrayList();
        Map<String, Object> map = MapUtils.newHashMap();
		String patrolID;
        String patrolName;
        String patrolContent;
        patrolID="aaa123456";
        patrolName="巡检1";
        patrolContent="未发现异常";
        map.put("patrolID",patrolID);
        map.put("patrolName",patrolName);
        map.put("patrolContent",patrolContent);
        mapList.add(map);
		return mapList;
	}

    /**
     *
     * @param request
     * Post(Put):
     * {“deviceID”:”xxx”,” patrolID”:”xxx”,” patrolPersion”:”xxx”,”state”:true,”remark”:”xxxxxxxxxxxx”}
     * @return
     */
    @RequestMapping(value = {"postPatrol", ""})
    public Map<String,Object> postPatrol(HttpServletRequest request){
        String deviceID=request.getParameter("deviceID");
        String patrolID=request.getParameter("patrolID");
        String patrolPersion=request.getParameter("patrolPersion");
        String state=request.getParameter("state");
        String remark=request.getParameter("remark");
        System.out.println(deviceID+patrolID+patrolPersion+state+remark);
        Date date=new Date();
        Map<String,Object> map=new HashMap<>();
        try{
            String patrolName=isPatrolService.get(patrolID).getName();
            IsPatrolRec isPatrolRec=new IsPatrolRec(patrolID,patrolName,remark,patrolPersion,date);
            isPatrolRecService.save(isPatrolRec);
        }catch(Exception exception){
            map.put("result",exception.toString());
            return map;
        }
        map.put("result","ok");
        return map;
    }

    /**
     * 近7天巡检情况
     * @return
     * Json：
     * [{“date”:1,” patrolCount”:123},{“date”:2,” patrolCount”:123} ,…]
     */
    @RequestMapping(value = {"sevenPatrol", ""})
    public List<Map<String, Object>> sevenPatrol() {
        List<Map<String, Object>> mapList = ListUtils.newArrayList();
        Map<String, Object> map = MapUtils.newHashMap();
        int date;
        int patrolCount;
        for(int i=1;i<8;i++){
            date=i;
            patrolCount=123;
            map.put("date",date);
            map.put("patrolCount",patrolCount);
            mapList.add(map);
        }
        return mapList;
    }
}
	
	