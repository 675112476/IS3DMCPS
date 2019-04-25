package com.jeesite.modules.is3dmcps.web;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jeesite.common.collect.MapUtils;
import com.jeesite.modules.is3dmcps.entity.IsMaintain;
import com.jeesite.modules.is3dmcps.entity.IsMaintainRec;
import com.jeesite.modules.is3dmcps.service.IsMaintainRecService;
import com.jeesite.modules.is3dmcps.service.IsMaintainService;
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
 *维护保养
 */
@CrossOrigin
@RestController
@RequestMapping(value = "static")
public class PageMaintainController extends BaseController{
    @Autowired
    IsMaintainRecService isMaintainRecService;
    @Autowired
	IsMaintainService isMaintainService;
	/**
	 * 保养弹窗
	 * @return
	 */
	@RequestMapping(value = {"maintainPop", ""})
	public List<Map<String, Object>> maintainPop() {
		List<Map<String, Object>> mapList = ListUtils.newArrayList();
		Map<String, Object> map = MapUtils.newHashMap();
		String maintainID;
		String maintainName;
		String maintainContent;
		maintainID="213123";
		maintainName="dwdwd";
		maintainContent="dwdw";
		map.put("maintainID",maintainID);
		map.put("maintainName",maintainName);
		map.put("maintainContent",maintainContent);
		mapList.add(map);
		Map<String, Object> map1 = MapUtils.newHashMap();
		String maintainID1;
		String maintainName1;
		String maintainContent1;
		maintainID1="213123";
		maintainName1="dwdwd";
		maintainContent1="dwdw";
		map1.put("maintainID",maintainID1);
		map1.put("maintainName",maintainName1);
		map1.put("maintainContent",maintainContent1);
		mapList.add(map1);
		return mapList;
	}

    /**
     * 保养录入
     * Post(Put):
     * {“deviceID”:”xxx”,” maintainID”:”xxx”,”miantainPersion”:”xxx”,”state”:true,”remark”:”xxxxxxxxxxxx”}
     * @return
     */
    @RequestMapping(value = {"postMaintain", ""})
    public Map<String,Object> postMaintain(HttpServletRequest request){
        String deviceID=request.getParameter("deviceID");
        String maintainID=request.getParameter("maintainID");
        String maintainPersion=request.getParameter("maintainPersion");
        String state=request.getParameter("state");
        if(state.equals("true")){
        	state="1";
		}else{
        	state="0";
		}
        String remark=request.getParameter("remark");
		System.out.println(deviceID+maintainID+maintainPersion+state+remark);
        Date date=new Date();
		Map<String,Object> map=new HashMap<>();
        try{
			String maintainName=isMaintainService.get(maintainID).getName();
			IsMaintainRec isMaintainRec=new IsMaintainRec(maintainID,maintainName,deviceID,null,null,remark,maintainPersion,date,state);
            isMaintainRecService.save(isMaintainRec);
        }catch(Exception exception){
			map.put("result",exception.toString());
			return map;
		}
		map.put("result","ok");
        return map;
    }

	/**
	 * 七天维保
	 * @return
	 */
	@RequestMapping(value = {"sevenMaintain", ""})
	public List<Map<String, Object>> sevenMaintain() {
		List<Map<String, Object>> mapList = ListUtils.newArrayList();
		Map<String, Object> map = MapUtils.newHashMap();
		int date;
		int miantainPlanCount;
		int finishCount;
		for(int i=1;i<8;i++){
			date=i;
			miantainPlanCount=123;
			finishCount=54;
			map.put("date",date);
			map.put("miantainPlanCount",miantainPlanCount);
			map.put("finishCount",finishCount);
			mapList.add(map);
		}
		return mapList;
	}

}
	
	