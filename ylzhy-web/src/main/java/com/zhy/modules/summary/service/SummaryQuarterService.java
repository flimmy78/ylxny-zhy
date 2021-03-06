package com.zhy.modules.summary.service;

import com.baomidou.mybatisplus.service.IService;
import com.zhy.common.utils.PageUtils;
import com.zhy.modules.summary.entity.SummaryQuarterEntity;
import net.sf.json.JSONObject;

import java.util.Map;

/**
 * 
 *
 * @author xtp
 * @email xtp1211@163.com
 * @date 2018-09-20 09:40:42
 */
public interface SummaryQuarterService extends IService<SummaryQuarterEntity> {

    PageUtils queryPage(Map<String, Object> params);

    JSONObject getRealTimeGonglv(String date, Long companyId);

    JSONObject getFuheList(Long companyId, Long lineId);

    JSONObject getLatestFuhe(String date, Long companyId, Long lineId);

	JSONObject electricCurrent(String startTime, String endTime, String companyId, Integer lineId);

	JSONObject voltage(String startTime, String endTime, String companyId, Integer lineId);

	JSONObject powerFactor(String startTime, String endTime, String companyId, Integer lineId);
}

