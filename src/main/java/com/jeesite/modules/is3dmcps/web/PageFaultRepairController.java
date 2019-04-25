package com.jeesite.modules.is3dmcps.web;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jeesite.common.collect.MapUtils;
import com.jeesite.modules.is3dmcps.entity.IsFaults;
import com.jeesite.modules.is3dmcps.entity.IsRepairRec;
import com.jeesite.modules.is3dmcps.service.IsFaultsService;
import com.jeesite.modules.is3dmcps.service.IsRepairRecService;
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
 *故障维修
 */
@CrossOrigin
@RestController
@RequestMapping(value = "static")
public class PageFaultRepairController extends BaseController{

    @Autowired
    IsFaultsService isFaultsService;
    @Autowired
    IsRepairRecService isRepairRecService;
    /**
     * 故障及维修的提交
     * @return
     */
	@RequestMapping(value = {"faultSubmit", ""})
	public List<Map<String, Object>> faultSubmit() {
		List<Map<String, Object>> mapList = ListUtils.newArrayList();
        Map<String, Object> map = MapUtils.newHashMap();
        String state;
        String infoJsonStr;
        state="Normal";
        infoJsonStr="出现行走故障";
        map.put("state",state);
        map.put("infoJsonStr",infoJsonStr);
        mapList.add(map);
		return mapList;
	}

    /**
     *
     * @param request
     * @return
     * （1）报故障：
     * {“deviceID”:”xxx”,”deviceName”:”xxxxx”,”persion”:”xxxxx”,”faultResult”:”xxx损坏”,”recommendedSolution”:”xxx”}
     * （2）维修：（state：true代表完成，false代表未完成。false时，content仍会发送,但不用管content字段， content字段可能包含脏数据）
     * Post:
     * {“deviceID”:”xxx”,”deviceName”:”xxxxx”,”persion”:”xxxxx”,“faultID”:”xxx”,”state”:false,”content”,”xxxxxxxxxx”}
     */
    @RequestMapping(value = {"postFault", ""})
    public Map<String,Object> postFault(HttpServletRequest request){
        String deviceID=request.getParameter("deviceID");
        String deviceName=request.getParameter("deviceName");
        String persion=request.getParameter("persion");
        String faultResult=request.getParameter("faultResult");
        String recommendedSolution=request.getParameter("recommendedSolution");
        String faultID=request.getParameter("faultID");
        String state=request.getParameter("state");
        String content=request.getParameter("content");
        System.out.println(deviceID+deviceName+persion+state+content);
        Date date=new Date();
        Map<String,Object> map=new HashMap<>();
        if(state==null){
            IsFaults isFaults=new IsFaults(deviceName,deviceID,deviceName,null,date,persion,faultResult,recommendedSolution);
            try{
                isFaultsService.save(isFaults);
            }catch(Exception exception){
                map.put("result",exception.toString());
                return map;
            }
            map.put("result","ok");
            return map;
        }else{
            if(state.equals("true")){
                state="2";
            }else{
                state="0";
            }
            String faultName=isFaultsService.get(faultID).getName();
            IsRepairRec isRepairRec=new IsRepairRec(faultID,faultName,content,state,persion,date);
            try{
                isRepairRecService.save(isRepairRec);
            }catch(Exception exception){
                map.put("result",exception.toString());
                return map;
            }
            map.put("result","ok");
            return map;
        }
    }

    /**
     * 近7天故障情况
     * Post:deviceID or deviceName
     * @return
     * Json：
     * [{“date”:1,” faultCount”:123,”repairedCount”:54},{“date”:2,” faultCount”:123,” repairedCount”:54},…]
     */
    @RequestMapping(value = {"sevenFault", ""})
    public List<Map<String, Object>> sevenFault   () {
        List<Map<String, Object>> mapList = ListUtils.newArrayList();
        Map<String, Object> map = MapUtils.newHashMap();
        int date;
        int faultCount;
        int repairedCount;

        for(int i=1;i<8;i++){
            date=i;
            faultCount=123;
            repairedCount=54;
            map.put("date",date);
            map.put("faultCount",faultCount);
            map.put("repairedCount",repairedCount);
            mapList.add(map);
        }
        return mapList;
    }
}
	
	