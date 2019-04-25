package com.jeesite.modules.is3dmcps.web;

import java.util.List;
import java.util.Map;

import com.jeesite.common.collect.MapUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jeesite.common.collect.ListUtils;
import com.jeesite.common.web.BaseController;

/**
 * 
 * @author ZX
 *设备展示页面
 */
@CrossOrigin
@RestController
@RequestMapping(value = "static")
public class PageDeviceDisplayController extends BaseController{

    /**
     * 所有设备型号
     * @return
     * Json:
     * [{“deviceTypeID”:”xxx”,“deviceTypeName”:”xxx”,”deviceCategoryName”:”XXX”},{“deviceTypeID”:”xxx”,“deviceTypeName”:”xxx”,”d
     * eviceCategoryName”:”XXX”}]
     */
	@RequestMapping(value = {"allDeviceType", ""})
	public List<Map<String, Object>> allDeviceType() {
		List<Map<String, Object>> mapList = ListUtils.newArrayList();
        Map<String, Object> map = MapUtils.newHashMap();
        String deviceTypeID;
        String deviceTypeName;
        String deviceCategoryName;
        deviceTypeID="21923";
        deviceTypeName="SFTR003";
        deviceCategoryName="智能双向穿梭车";
        map.put("deviceTypeID",deviceTypeID);
        map.put("deviceTypeName",deviceTypeName);
        map.put("deviceCategoryName",deviceCategoryName);
        mapList.add(map);
        Map<String, Object> map1 = MapUtils.newHashMap();
        String deviceTypeID1;
        String deviceTypeName1;
        String deviceCategoryName1;
        deviceTypeID1="21233";
        deviceTypeName1="ABRC01";
        deviceCategoryName1="快换电池装置";
        map1.put("deviceTypeID",deviceTypeID1);
        map1.put("deviceTypeName",deviceTypeName1);
        map1.put("deviceCategoryName",deviceCategoryName1);
        mapList.add(map1);
        Map<String, Object> map2 = MapUtils.newHashMap();
        String deviceTypeID2;
        String deviceTypeName2;
        String deviceCategoryName2;
        deviceTypeID2="2123";
        deviceTypeName2="TW3";
        deviceCategoryName2="往复式输送升降机";
        map2.put("deviceTypeID",deviceTypeID2);
        map2.put("deviceTypeName",deviceTypeName2);
        map2.put("deviceCategoryName",deviceCategoryName2);
        mapList.add(map2);
        Map<String, Object> map3 = MapUtils.newHashMap();
        String deviceTypeID3;
        String deviceTypeName3;
        String deviceCategoryName3;
        deviceTypeID3="21223";
        deviceTypeName3="TW4";
        deviceCategoryName3="往复式升降机";
        map3.put("deviceTypeID",deviceTypeID3);
        map3.put("deviceTypeName",deviceTypeName3);
        map3.put("deviceCategoryName",deviceCategoryName3);
        mapList.add(map3);
        Map<String, Object> map4 = MapUtils.newHashMap();
        String deviceTypeID4;
        String deviceTypeName4;
        String deviceCategoryName4;
        deviceTypeID4="4143";
        deviceTypeName4="MQ22";
        deviceCategoryName4="拆码垛机";
        map4.put("deviceTypeID",deviceTypeID4);
        map4.put("deviceTypeName",deviceTypeName4);
        map4.put("deviceCategoryName",deviceCategoryName4);
        mapList.add(map4);
        Map<String, Object> map5 = MapUtils.newHashMap();
        String deviceTypeID5;
        String deviceTypeName5;
        String deviceCategoryName5;
        deviceTypeID5="5153";
        deviceTypeName5="GM12";
        deviceCategoryName5="加取盖机";
        map5.put("deviceTypeID",deviceTypeID5);
        map5.put("deviceTypeName",deviceTypeName5);
        map5.put("deviceCategoryName",deviceCategoryName5);
        mapList.add(map5);
		return mapList;
	}

    /**
     * 零件信息
     * Post:devicePartID or devicePartName
     * @return
     * Json:
     * {“devicePartID ”:”xxxx”,“devicePartName”:”ASSEM10”,”devicePartDetailedName”:”货物托盘检测传感器”,”devicePartCount”:2,”devicePartDescription”:”利用托盘传感器XXXXXXXXXXXXXXXXXXX”}
     */
    @RequestMapping(value = {"partInfo", ""})
    public List<Map<String, Object>> partInfo() {
        List<Map<String, Object>> mapList = ListUtils.newArrayList();
        Map<String, Object> map = MapUtils.newHashMap();
        String devicePartID;
        String devicePartName;
        String devicePartDetailedName;
        int devicePartCount;
        String devicePartDescription;
        devicePartID="212321";
        devicePartName="上立片";
        devicePartDetailedName="***的上立片";
        devicePartCount=3;
        devicePartDescription="利用托盘传感器";
        map.put("devicePartID",devicePartID);
        map.put("devicePartName",devicePartName);
        map.put("devicePartDetailedName",devicePartDetailedName);
        map.put("devicePartCount",devicePartCount);
        map.put("devicePartDescription",devicePartDescription);
        mapList.add(map);
        return mapList;
    }

    /**
     * 组件的点击
     * Post:deviceTypeID or deviceTypeName
     * @return
     *Json:
     * [{“deviceComponentID”:”xxx”, “deviceComponentName”:”组件xxx”,”deviceComponentSceneParentPath”:”xxx/xxx/xxx/xxx” },
     * {“deviceComponentID”:”xxx”,“deviceComponentName”:”组件xxx”,”deviceComponentSceneParentPath”:”xxx/xxx/xxx/xxx” }]
     */
    @RequestMapping(value = {"componentClick", ""})
    public List<Map<String, Object>> componentClick() {
        List<Map<String, Object>> mapList = ListUtils.newArrayList();
        Map<String, Object> map = MapUtils.newHashMap();
        String deviceComponentID;
        String deviceComponentName;
        String deviceComponentSceneParentPath;
        deviceComponentID="212321";
        deviceComponentName="上立片";
        deviceComponentSceneParentPath="**/**/**/**";
        map.put("deviceComponentID",deviceComponentID);
        map.put("deviceComponentName",deviceComponentName);
        map.put("deviceComponentSceneParentPath",deviceComponentSceneParentPath);
        mapList.add(map);
        return mapList;
    }

    /**
     *零件列表
     * @return
     * Json:
     * [{“devicePartID”:”xxx”,”devicePartName”:”XXXxx”,“deviceComponentID”:”xxx”,“deviceComponentName”:”组件xxx” },
     * {“devicePartID”:”xxx”,”devicePartName”:”XXXxx”, “deviceComponentID”:”xxx”, “deviceComponentName”:”组件xxx”]
     */
    @RequestMapping(value = {"partList", ""})
    public List<Map<String, Object>> partList() {
        List<Map<String, Object>> mapList = ListUtils.newArrayList();
        Map<String, Object> map = MapUtils.newHashMap();
        String devicePartID;
        String devicePartName;
        String deviceComponentID;
        String deviceComponentName;
        devicePartID="212321";
        devicePartName="上立片";
        deviceComponentID="212123";
        deviceComponentName="组件3";
        map.put("devicePartID",devicePartID);
        map.put("devicePartName",devicePartName);
        map.put("deviceComponentID",deviceComponentID);
        map.put("deviceComponentName",deviceComponentName);
        mapList.add(map);
        return mapList;
    }
}
	
	