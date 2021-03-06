/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.is3dmcps.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.is3dmcps.entity.IsDevice;
import com.jeesite.modules.is3dmcps.dao.IsDeviceDao;

/**
 * 设备Service
 * @author xx
 * @version 2019-03-07
 */
@Service
@Transactional(readOnly=true)
public class IsDeviceService extends CrudService<IsDeviceDao, IsDevice> {
	
	/**
	 * 获取单条数据
	 * @param isDevice
	 * @return
	 */
	@Override
	public IsDevice get(IsDevice isDevice) {
		return super.get(isDevice);
	}
	
	/**
	 * 查询分页数据
	 * @param page 分页对象
	 * @param isDevice
	 * @return
	 */
	@Override
	public Page<IsDevice> findPage(Page<IsDevice> page, IsDevice isDevice) {
		return super.findPage(page, isDevice);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param isDevice
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(IsDevice isDevice) {
		super.save(isDevice);
	}
	
	/**
	 * 更新状态
	 * @param isDevice
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(IsDevice isDevice) {
		super.updateStatus(isDevice);
	}
	
	/**
	 * 删除数据
	 * @param isDevice
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(IsDevice isDevice) {
		super.delete(isDevice);
	}

	/**
	 * 获得总数
	 */
	public int getAll(){return this.dao.getAll();}

}