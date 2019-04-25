package com.jeesite.modules.is3dmcps.web;

import java.util.List;
import java.util.Map;

import com.jeesite.common.collect.MapUtils;
import com.jeesite.modules.is3dmcps.entity.IsDevice;
import com.jeesite.modules.is3dmcps.entity.IsDeviceCode;
import com.jeesite.modules.is3dmcps.service.IsDeviceCodeService;
import com.jeesite.modules.is3dmcps.service.IsDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jeesite.common.collect.ListUtils;
import com.jeesite.common.web.BaseController;

/**
 *
 * @author ZX
 *设备管理主界面
 */
@CrossOrigin
@RestController
@RequestMapping(value = "static")
public class PageDeviceManController extends BaseController{
    @Autowired
    private IsDeviceService isDeviceService;
    @Autowired
    private IsDeviceCodeService isDeviceCodeService;
    /**
     * 场景设备都能点
     * @return
     * Json:
     * [{“deviceSceneParentPath”:”xxx/xxx/xxx/xxx”,”deviceID”:”xxx”,”deviceName”:”xxx”},
     * {“deviceSceneParentPath”:”xxx/xxx/xxx/xxx”,”deviceID”:”xxx”,”deviceName”:”xxx”}]
     */
    @RequestMapping(value = {"sceneDevice", ""})
    public List<Map<String, Object>> sceneDevice() {
        List<Map<String, Object>> mapList = ListUtils.newArrayList();
        String deviceSceneParentPath;
        String deviceID;
        String deviceName;
        deviceSceneParentPath="123/123/123/123";
        deviceID="12323123";
        deviceName="穿梭车";
        Map<String, Object> map = MapUtils.newHashMap();
        map.put("deviceSceneParentPath",deviceSceneParentPath);
        map.put("deviceID",deviceID);
        map.put("deviceName",deviceName);
        mapList.add(map);
        String deviceSceneParentPath1;
        String deviceID1;
        String deviceName1;
        deviceSceneParentPath1="123/123/123/123";
        deviceID1="12323123";
        deviceName1="穿梭车";
        Map<String, Object> map1 = MapUtils.newHashMap();
        map1.put("deviceSceneParentPath",deviceSceneParentPath1);
        map1.put("deviceID",deviceID1);
        map1.put("deviceName",deviceName1);
        mapList.add(map1);
        return mapList;
    }

    /**
     * 所有设备列表
     * @return
     * Json：
     * {[”deviceID”:”xxx”,”deviceName”:”Car01”,”deviceCategoryName”:”智能双向小车”,”deviceTypeName”:”SFTA003” ,”runState”:”Run”],
     *  [”deviceID”:”xxx”,”deviceName”:”XXX”,”deviceCategoryName”:”XXX” ,”deviceTypeName”:”xxxx” ,”runState”:”Sleep”],
     *  [”deviceID”:”xxx”,”deviceName”:”XXX”,”deviceCategoryName”:”XXX” ,”deviceTypeName”:”xxxx” ,”runState”:”Fault”]}
     */
    @RequestMapping(value = {"deveiceList", ""})
    public List<Map<String, Object>> deveiceList() {
        List<Map<String, Object>> mapList = ListUtils.newArrayList();
        String deviceID;
        String deviceName;
        String deviceCategoryName;
        String deviceTypeName;
        String runState;
        List<IsDevice> isDevices=isDeviceService.findList(new IsDevice());
        for(IsDevice isDevice:isDevices){
            deviceID= isDevice.getId();
            deviceName=isDevice.getDeviceNo();
            deviceCategoryName=isDevice.getDeviceCodeName();
            String device_code_id=isDevice.getDeviceCodeId();
            IsDeviceCode isDeviceCode=isDeviceCodeService.get(device_code_id);
            if(isDeviceCode!=null){
                deviceTypeName=isDeviceCode.getModel();
            }else{
                deviceTypeName="";
            }
            runState=isDevice.getDeviceStatus();
            System.out.println(runState);
            switch (runState){
                case "0":
                    runState="Sleep";
                    break;
                case "1":
                    runState="Run";
                    break;
                case "2":
                    runState="Fault";
                    break;
                case "9":
                    runState="abandon";
                    break;
            }

            Map<String, Object> map = MapUtils.newHashMap();
            map.put("deviceID",deviceID);
            map.put("deviceName",deviceName);
            map.put("deviceCategoryName",deviceCategoryName);
            map.put("deviceTypeName",deviceTypeName);
            map.put("runState",runState);
            mapList.add(map);
        }

        return mapList;
    }

    /**
     * 设备点击
     * Post: deviceID or deviceName
     * @return
     * Json:
     * {”deviceName”:”xxx”,” deviceCategoryName”:”XXXXX” ,”deviceTypeName”:”xxxx”,
     * “runState”:”正常”,”FaultCount”:46,”runTimeCount”:10.5,”useTime”:256}
     */
    @RequestMapping(value = {"deviceClick", ""})
    public List<Map<String, Object>> maintainRecList() {
        List<Map<String, Object>> mapList = ListUtils.newArrayList();
        String deviceName;
        String deviceCategoryName;
        String deviceTypeName;
        String runState;
        int FaultCount;
        double runTimeCount;
        double useTime;
        deviceName="穿梭车";
        deviceCategoryName="智能双向小车";
        deviceTypeName="SFTA003";
        runState="Run";
        FaultCount=46;
        runTimeCount=10.5;
        useTime=256;
        Map<String, Object> map = MapUtils.newHashMap();
        map.put("deviceName",deviceName);
        map.put("deviceCategoryName",deviceCategoryName);
        map.put("deviceTypeName",deviceTypeName);
        map.put("runState",runState);
        map.put("FaultCount",FaultCount);
        map.put("runTimeCount",runTimeCount);
        map.put("useTime",useTime);
        mapList.add(map);
        return mapList;
    }

    /**
     * 其他参数
     * Post: deviceID or deviceName
     * @return
     * Json:
     * {“installationSite”:”XXXX”,”department”:”XXX”,” manufacturer”:”XXX”,” purpose”:” XXX”,
     * ” startWorkDate”:”2019-1-1”,”madeDate”:”2018-1-1”,”yearlyDepreciation”:53,”durableYears”:3.5}
     */
    @RequestMapping(value = {"otherParameter", ""})
    public List<Map<String, Object>> otherParameter() {
        List<Map<String, Object>> mapList = ListUtils.newArrayList();
        String installationSite;
        String department;
        String manufacturer;
        String purpose;
        String startWorkDate;
        String madeDate;
        double yearlyDepreciation;
        double durableYears;
        installationSite="兰州烟丝库";
        department="仓储库";
        manufacturer="昆明欧迈科技";
        purpose="烟箱垛箱";
        startWorkDate="2018-10-06";
        madeDate="2018-10-01";
        yearlyDepreciation=53;
        durableYears=3.5;
        Map<String, Object> map = MapUtils.newHashMap();
        map.put("installationSite",installationSite);
        map.put("department",department);
        map.put("manufacturer",manufacturer);
        map.put("purpose",purpose);
        map.put("startWorkDate",startWorkDate);
        map.put("madeDate",madeDate);
        map.put("yearlyDepreciation",yearlyDepreciation);
        map.put("durableYears",durableYears);
        mapList.add(map);
        return mapList;
    }

    /**
     * 设备参数
     * Post: deviceID or deviceName
     * @return
     * Json:
     * [{“property”:”xxxxxx”,”value”:”xxxxxxxxxxx” },{“property”:”xxxxxx”,”value”:”xxxxxxxxxxx” }]
     */
    @RequestMapping(value = {"deviceParameter", ""})
    public List<Map<String, Object>> deviceParameter() {
        List<Map<String, Object>> mapList = ListUtils.newArrayList();
        String property;
        String value;
        property="设备尺寸";
        value="1200*1100*260";
        Map<String, Object> map = MapUtils.newHashMap();
        map.put("property",property);
        map.put("value",value);
        mapList.add(map);
        return mapList;
    }
}
	
	