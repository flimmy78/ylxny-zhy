package com.zhy.modules.summary.service;

import com.baomidou.mybatisplus.service.IService;
import com.zhy.common.utils.PageUtils;
import com.zhy.modules.report.entity.FuheReportEntity;
import com.zhy.modules.report.entity.YearPowerReportEntity;
import com.zhy.modules.summary.entity.SummaryYearEntity;

import java.util.List;
import java.util.Map;

/**
 * 统计表（按天统计）
 *
 * @author xtp
 * @email xtp1211@163.com
 * @date 2018-09-14 17:38:33
 */
public interface SummaryYearService extends IService<SummaryYearEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<YearPowerReportEntity> getPowerYearList(Map<String, Object> params, Long companyId);

    List<FuheReportEntity> getFuheYearList(Map<String, Object> params, Long companyId);
}

