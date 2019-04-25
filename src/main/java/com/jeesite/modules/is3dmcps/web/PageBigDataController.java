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
 *大数据统计分析展示界面
 */
@CrossOrigin
@RestController
@RequestMapping(value = "static")
public class PageBigDataController extends BaseController{

    /**
     * 生产信息分析界面
     * Post:
     * {“analysisType”:”InventoryInfo”,”timeCoordinate”:” Monthly”,” timeDomainYear”:2018}
     * 或
     * {“analysisType”:”InventoryInfo”,”timeCoordinate”:”Daily”,”timeDomainYear”:2019,”timeDomainMonth”:5}
     * @return
     * Json:
     *  [{“brand”:”兰州（细支珍品）烟丝”,“totalWeightThis”:25000,“totalWeightLast”:24000,”finishWeight”:23000,”timeVariable”:10},
     * {“brand”:”兰州（细支珍品）烟丝”,“totalWeightThis”:25000,“totalWeightLast”:24000,”finishWeight”:23000,”timeVariable”:11}]
     */
	@RequestMapping(value = {"productInfoAnalyse", ""})
	public List<Map<String, Object>> productInfoAnalyse() {
		List<Map<String, Object>> mapList = ListUtils.newArrayList();
        Map<String, Object> map = MapUtils.newHashMap();
        String brand;
        int totalWeightThis;
        int totalWeightLast;
        int finishWeight;
        int timeVariable;
        brand="兰州（细支珍品）烟丝";
        totalWeightThis=25000;
        totalWeightLast=24000;
        finishWeight=23000;
        timeVariable=10;
        map.put("brand",brand);
        map.put("totalWeightThis",totalWeightThis);
        map.put("totalWeightLast",totalWeightLast);
        map.put("finishWeight",finishWeight);
        map.put("timeVariable",timeVariable);
        mapList.add(map);
		return mapList;
	}
    /**
     * 小车运行统计
     * Post:
     * {”timeCoordinate”:” Monthly”,” timeDomainYear”:2018}
     * 或
     * {”timeCoordinate”:”Daily”,”timeDomainYear”:2019,”timeDomainMonth”:5}
     * @return
     */
    @RequestMapping(value = {"carRunStatics", ""})
    public List<Map<String, Object>> carRunStatics() {
        List<Map<String, Object>> mapList = ListUtils.newArrayList();
        Map<String, Object> map = MapUtils.newHashMap();
        String timeCoordinate;
        int timeDomainYear;
        timeCoordinate="Monthly";
        timeDomainYear=2018;
        map.put("timeCoordinate",timeCoordinate);
        map.put("timeDomainYear",timeDomainYear);
        mapList.add(map);
        return mapList;
    }

    /**
     * 小车运行情况
     * Post:deviceID or deviceName
     * @return
     * Json:
     * {“runDistance”:11114582,”liftCount”: 255456,”veerCount”:3646863,”runFaultsCount”:2648,”liftFaultsCount”:687,”veerFaultsCount”:54,”allFaultsCount”:3389}
     */
    @RequestMapping(value = {"carRunStatus", ""})
    public List<Map<String, Object>> carRunStatus() {
        List<Map<String, Object>> mapList = ListUtils.newArrayList();
        Map<String, Object> map = MapUtils.newHashMap();
        int runDistance;
        int liftCount;
        int veerCount;
        int runFaultsCount;
        int liftFaultsCount;
        int veerFaultsCount;
        int allFaultsCount;
        runDistance=11114582;
        liftCount=122018;
        veerCount=2018;
        runFaultsCount=18;
        liftFaultsCount=25;
        veerFaultsCount=3;
        allFaultsCount=40;
        map.put("runDistance",runDistance);
        map.put("liftCount",liftCount);
        map.put("veerCount",veerCount);
        map.put("runFaultsCount",runFaultsCount);
        map.put("liftFaultsCount",liftFaultsCount);
        map.put("veerFaultsCount",veerFaultsCount);
        map.put("allFaultsCount",allFaultsCount);
        mapList.add(map);
        return mapList;
    }

    /**
     * 小车历史故障统计
     * @return
     * Json：
     * [{“deviceName”:”CAR01”,” allFaultsCount”:76},{“deviceName”:”CAR02”,” allFaultsCount”:54}]
     */
    @RequestMapping(value = {"carHistoryFault", ""})
    public List<Map<String, Object>> carHistoryFault() {
        List<Map<String, Object>> mapList = ListUtils.newArrayList();
        Map<String, Object> map = MapUtils.newHashMap();
        String deviceName;
        int allFaultsCount;
        deviceName="CAR01";
        allFaultsCount=122018;
        map.put("deviceName",deviceName);
        map.put("allFaultsCount",allFaultsCount);
        mapList.add(map);
        Map<String, Object> map1 = MapUtils.newHashMap();
        String deviceName1;
        int allFaultsCount1;
        deviceName1="CAR01";
        allFaultsCount1=122018;
        map1.put("deviceName",deviceName1);
        map1.put("allFaultsCount",allFaultsCount1);
        mapList.add(map1);
        return mapList;
    }

    /**
     * 生产线平均生产能力表
     * @return
     * Json:
     *  [{“productionLineName”:”生产线一”,“timeVariable”:11,“productionCapacity”:22000},
     * {“productionLineName”:”生产线一”, “timeVariable”:12,“productionCapacity”:25000}]
     */
    @RequestMapping(value = {"productionLine", ""})
    public List<Map<String, Object>> productionLine() {
        List<Map<String, Object>> mapList = ListUtils.newArrayList();
        for(int i=1;i<13;i++){
            Map<String, Object> map = MapUtils.newHashMap();
            String productionLineName;
            int timeVariable;
            int productionCapacity;
            productionLineName="生产线一";
            timeVariable=i;
            productionCapacity=122018;
            map.put("productionLineName",productionLineName);
            map.put("timeVariable",timeVariable);
            map.put("productionCapacity",productionCapacity);
            mapList.add(map);
        }
        return mapList;
    }

    /**
     * 月批次出入库耗时情况表
     * Post:
     * {”timeDomainYear”:2019,”timeDomainMonth”:5}
     * @return
     * Json:
     * [{“batch”:”YXZZ19010”,”weight”:9845.11,”timeCost”:18},
     * {“batch”:”YXZZ19011”,”weight”:4235.15,”timeCost”:13}]
     */
    @RequestMapping(value = {"monthlyBatchTime", ""})
    public List<Map<String, Object>> monthlyBatchTime() {
        List<Map<String, Object>> mapList = ListUtils.newArrayList();
        Map<String, Object> map = MapUtils.newHashMap();
        String batch;
        double weight;
        double timeCost;
        batch="YXZZ19013";
        weight=9845.11;
        timeCost=18;
        map.put("batch",batch);
        map.put("weight",weight);
        map.put("timeCost",timeCost);
        mapList.add(map);
        Map<String, Object> map1 = MapUtils.newHashMap();
        String batch1;
        double weight1;
        double timeCost1;
        batch1="YXZZ19013";
        weight1=9845.11;
        timeCost1=18;
        map1.put("batch",batch1);
        map1.put("weight",weight1);
        map1.put("timeCost",timeCost1);
        mapList.add(map1);
        return mapList;
    }
}
	
	