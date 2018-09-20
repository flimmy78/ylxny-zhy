package com.zhy.modules.realtime.controller;

import com.zhy.common.utils.CommonUtil;
import com.zhy.common.utils.R;
import com.zhy.modules.company.controller.CompanyController;
import com.zhy.modules.qxgl.controller.AbstractController;
import com.zhy.modules.summary.service.DataRecordService;
import com.zhy.modules.summary.service.SummaryQuarterService;
import io.lettuce.core.dynamic.annotation.Value;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @Package com.zhy.modules.realtime.controller
 * @Description:
 * @User: zengqiang
 * @Date: 2018-09-15
 */
@RestController
@RequestMapping("realtime")
public class RealtimeController extends CompanyController {
    @Autowired
    SummaryQuarterService summaryQuarterService;

    @RequestMapping("/fuhe/list")
    @ResponseBody
    public R fuheList(@Value Long lineId) {
        Long companyId = getCompanyId();
        if(CommonUtil.isNull(lineId)){
            lineId = (long)getUser(true).getMainLine();
        }
        JSONObject obj = summaryQuarterService.getFuheList(companyId, lineId);
        System.out.println(obj);
        return R.ok().put("data", obj);
    }

    @RequestMapping("/fuhe/refresh")
    @ResponseBody
    public R fuheRefresh(@Value Long lineId, String date) {
        Long companyId = getCompanyId();
        if(CommonUtil.isNull(lineId)){
            lineId = (long)getUser(true).getMainLine();
        }
        JSONObject obj = summaryQuarterService.getLatestFuhe(date, companyId, lineId);
        System.out.println(obj);
        return R.ok().put("data", obj);
    }
}