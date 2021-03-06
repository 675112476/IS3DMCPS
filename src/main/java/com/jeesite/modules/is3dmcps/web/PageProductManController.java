package com.jeesite.modules.is3dmcps.web;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.jeesite.modules.is3dmcps.entity.*;
import com.jeesite.modules.is3dmcps.service.*;
import com.jeesite.utils.CompareDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jeesite.common.collect.ListUtils;
import com.jeesite.common.collect.MapUtils;
import com.jeesite.common.web.BaseController;

/**
 *
 * @author ZX
 *生产管理主界面
 */
@CrossOrigin
@RestController
@RequestMapping(value = "static")
public class PageProductManController extends BaseController{
    @Autowired
    private IsDeviceService isDeviceService;
    @Autowired
    private IsDeviceCodeService isDeviceCodeService;
    @Autowired
    private IsMaintainRecService isMaintainRecService;
    @Autowired
    private IsPatrolRecService isPatrolRecService;
    @Autowired
    private IsMaintainService isMaintainService;
    @Autowired
    private IsPatrolService isPatrolService;
    @Autowired
    private IsFaultsService isFaultsService;

    /**
     * 设备运行数量；设备故障数量；维修设备数量；
     * @return
     * Json:
     * {“deviceRuningCount”:210,”deviceFaultsCount”:4,”deviceRepairCount”:2}
     * 具体三个数量需计算出
     */
    @RequestMapping(value = {"totalQuantity", ""})
    public Map<String, Object> totalQuantity() {
        Map<String, Object> map = MapUtils.newHashMap();
        int deviceRuningCount;
        int deviceFaultsCount;
        int deviceRepairCount;
        deviceRuningCount=isDeviceService.getAll()-isFaultsService.getFaultsCount();
        deviceFaultsCount=isFaultsService.getFaultsCount();
        deviceRepairCount=isMaintainRecService.need_maintain();
        map.put("deviceRuningCount",deviceRuningCount);
        map.put("deviceFaultsCount",deviceFaultsCount);
        map.put("deviceRepairCount",deviceRepairCount);
        return map;
    }

    /**
     * 设备故障提醒；设备保养提醒；设备巡检提醒
     * @return
     * Json:
     * [{“noticeType”:”Patrol”,”noticeTime”:”13:12”,”noticeDescribe”:”设备XX到达固定巡检时间”},
     * {“noticeType”:”Maintain”,”noticeTime”:”14:12”,”noticeDescribe”:”设备XX需要更换零件”},
     * {“noticeType”:”Fault”,”noticeTime”:”15:12”,”noticeDescribe”:”设备XX发现故障”}]
     * 这里的时间是消息内容应该是根据间隔时间和上次巡查的时间算出
     */
    @RequestMapping(value = {"deviceAlert", ""})
    public List<Map<String, Object>> deviceAlert() {
        List<Map<String, Object>> mapList = ListUtils.newArrayList();
        List<IsMaintainRec> maintains = isMaintainRecService.need_maintain_details();
        List<IsPatrol> Patrols = isPatrolService.findList(new IsPatrol());
        List<IsFaults> Faults = isFaultsService.getFaultsDetails();
        List<IsPatrol> patrols=isPatrolService.getPatrol();
        Date curTime=new Date();// new Date()为获取当前系统时间
        if(Patrols != null && Patrols.size()>= 1){
            Map<String, Object> map = MapUtils.newHashMap();
            Date noticeTime;//应该巡检时间
            String noticeDescribe;
            String deviceName;
            Date LastTime;//上次巡检时间
            //设置日期格式

            for(IsPatrol isPatrol:patrols){
                LastTime=isPatrolRecService.getLastTime(isPatrol.getId());
                System.out.println("lasttime"+LastTime);
                deviceName=isPatrol.getName();
                if(LastTime==null){
                    Date useDate=curTime;
                    noticeTime=CompareDate.getTargetDate(useDate,isPatrol.getInterval());
                    System.out.println("noticeTime"+noticeTime);
                }else{
                    Calendar c = Calendar.getInstance();
                    c.setTime(LastTime);
                    System.out.println("useDate"+LastTime);
                    c.add(Calendar.DAY_OF_MONTH,isPatrol.getInterval());
                    noticeTime=c.getTime();
                    System.out.println("useDate"+noticeTime);
                }

                if(noticeTime.getTime()<=curTime.getTime()){
                    noticeDescribe="巡检点"+deviceName+"到达固定巡检时间";
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    map.put("noticeTitle",NoticeItemTypeToTitle("Maintain"));
                    map.put("noticeColor",NoticeItemTypeToColor("Maintain"));
                    map.put("noticeTime",df.format(noticeTime));
                    map.put("noticeDescribe",noticeDescribe);
                    mapList.add(map);
                }
            }
        }else{
            Map<String, Object> map = MapUtils.newHashMap();
            map.put("noticeTitle",NoticeItemTypeToTitle("Maintain"));
            map.put("noticeColor",NoticeItemTypeToColor("Maintain"));
            map.put("noticeTime","");
            map.put("noticeDescribe","");
            mapList.add(map);
        }
        if( maintains!= null && maintains.size()>= 1){
            Map<String, Object> map = MapUtils.newHashMap();
            String noticeTime;
            String noticeDescribe;
            String deviceName;
            for(IsMaintainRec isMaintainrec:maintains){
                if(isMaintainrec.getPlanDate().getTime()<=curTime.getTime()){
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    noticeTime=df.format(isMaintainrec.getPlanDate());
                    deviceName=isMaintainrec.getDeviceNo();
                    noticeDescribe="设备"+deviceName+"需要更换零件";
                    map.put("noticeTitle",NoticeItemTypeToTitle("Patrol"));
                    map.put("noticeColor",NoticeItemTypeToColor("Patrol"));
                    map.put("noticeTime",noticeTime);
                    map.put("noticeDescribe",noticeDescribe);
                    mapList.add(map);
                }
            }

        }else{
            Map<String, Object> map = MapUtils.newHashMap();
            map.put("noticeTitle",NoticeItemTypeToTitle("Patrol"));
            map.put("noticeColor",NoticeItemTypeToColor("Patrol"));
            map.put("noticeTime","");
            map.put("noticeDescribe","");
            mapList.add(map);
        }
        if(Faults != null && Faults.size()>= 1){
            Map<String, Object> map = MapUtils.newHashMap();
            String noticeTime;
            String noticeDescribe;
            String deviceName;
            for( IsFaults faults:Faults){
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                noticeTime=df.format(faults.getFaultsTime().getTime());
                deviceName=faults.getName();
                noticeDescribe="设备"+deviceName+"发现故障";
                map.put("noticeTitle",NoticeItemTypeToTitle("Fault"));
                map.put("noticeColor",NoticeItemTypeToColor("Fault"));
                map.put("noticeTime",noticeTime);
                map.put("noticeDescribe",noticeDescribe);
                mapList.add(map);
            }

        }else{
            Map<String, Object> map = MapUtils.newHashMap();
            map.put("noticeTitle",NoticeItemTypeToTitle("Fault"));
            map.put("noticeColor",NoticeItemTypeToColor("Fault"));
            map.put("noticeTime","");
            map.put("noticeDescribe","");
            mapList.add(map);
        }
        return mapList;
    }
    /**
     * 各品牌当前（今日）库存数量
     * json:
     *  [{“brand”:”兰州细支珍品”,”count”:10},{“brand”:”XXXXXX”,”count”:20}]
     */
    @RequestMapping(value = {"stockAmount", ""})
    public List<Map<String, Object>> stockAmount() {
        List<Map<String, Object>> mapList = ListUtils.newArrayList();
        Map<String, Object> map = MapUtils.newHashMap();
        String brand;
        int count;
        brand="兰州细支珍品";
        count=10;
        map.put("brand",brand);
        map.put("count",count);
        mapList.add(map);
        Map<String, Object> map1 = MapUtils.newHashMap();
        String brand1;
        int count1;
        brand1="利群";
        count1=20;
        map1.put("brand",brand1);
        map1.put("count",count1);
        mapList.add(map1);
        return mapList;
    }

    /**
     * 当前正在执行的工单信息
     * @return
     * json:
     * [{“completionRatio”:25,”workOrderNumber”:”XXXXXXXXXX”,”startTimeHour”:”08”, ”startTimeMinute”:”56”},
     * {“completionRatio”:65,”workOrderNumber”:”XXXXXXXXXX” ,”startTimeHour”:”08”, ”startTimeMinute”:”56”}]
     */
    @RequestMapping(value = {"workOrder", ""})
    public List<Map<String, Object>> workOrder() {
        List<Map<String, Object>> mapList = ListUtils.newArrayList();
        Map<String, Object> map = MapUtils.newHashMap();
        int completionRatio;
        String workOrderNumber;
        String startTimeHour;
        String startTimeMinute;
        completionRatio=25;
        workOrderNumber="IJWDN222";
        startTimeHour="08";
        startTimeMinute="56";
        map.put("completionRatio",completionRatio);
        map.put("workOrderNumber",workOrderNumber);
        map.put("startTimeHour",startTimeHour);
        map.put("startTimeMinute",startTimeMinute);
        mapList.add(map);
        Map<String, Object> map1 = MapUtils.newHashMap();
        int completionRatio1;
        String workOrderNumber1;
        String startTimeHour1;
        String startTimeMinute1;
        completionRatio1=125;
        workOrderNumber1="IJWDN123";
        startTimeHour1="01";
        startTimeMinute1="16";
        map1.put("completionRatio",completionRatio1);
        map1.put("workOrderNumber",workOrderNumber1);
        map1.put("startTimeHour",startTimeHour1);
        map1.put("startTimeMinute",startTimeMinute1);
        mapList.add(map1);
        return mapList;
    }
    private String NoticeItemTypeToColor(String type)
    {
        switch(type){
            case "Patrol":
                return "E09F2DFF";
            case "Maintain":
                return "00B4EBFF";
            case "Fault":
                return "E82E2EFF";
            default:
                return "FFFFFFFF";
        }
    }

    private String NoticeItemTypeToTitle(String type)
    {
        switch (type){
            case "Patrol":
                return "巡检提醒";
            case "Maintain":
                return "维保提醒";
            case "Fault":
                return "设备故障";
            default:
                return "NaN";
        }
    }
}
