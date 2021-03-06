/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.is3dmcps.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.is3dmcps.entity.IsMaintainRec;
import com.jeesite.modules.is3dmcps.dao.IsMaintainRecDao;

import java.util.List;

/**
 * 保养记录Service
 * @author xx
 * @version 2019-03-06
 */
@Service
@Transactional(readOnly=true)
public class IsMaintainRecService extends CrudService<IsMaintainRecDao, IsMaintainRec> {
	
	/**
	 * 获取单条数据
	 * @param isMaintainRec
	 * @return
	 */
	@Override
	public IsMaintainRec get(IsMaintainRec isMaintainRec) {
		return super.get(isMaintainRec);
	}
	
	/**
	 * 查询分页数据
	 * @param page 分页对象
	 * @param isMaintainRec
	 * @return
	 */
	@Override
	public Page<IsMaintainRec> findPage(Page<IsMaintainRec> page, IsMaintainRec isMaintainRec) {
		return super.findPage(page, isMaintainRec);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param isMaintainRec
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(IsMaintainRec isMaintainRec) {
		super.save(isMaintainRec);
	}
	
	/**
	 * 更新状态
	 * @param isMaintainRec
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(IsMaintainRec isMaintainRec) {
		super.updateStatus(isMaintainRec);
	}
	
	/**
	 * 删除数据
	 * @param isMaintainRec
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(IsMaintainRec isMaintainRec) {
		super.delete(isMaintainRec);
	}

	/**
	 * 需要保养的数量
	 */
	public int need_maintain(){
		return super.dao.need_maintain();
	}

	/**
	 * 需要保养的记录
	 */
	public List<IsMaintainRec> need_maintain_details(){
		return super.dao.need_maintain_details();
	}
	
}