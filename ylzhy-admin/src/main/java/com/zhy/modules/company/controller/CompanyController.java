package com.zhy.modules.company.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

 
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zhy.modules.company.entity.CompanyEntity;
import com.zhy.modules.company.service.CompanySbxxService;
import com.zhy.modules.company.service.CompanyService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONObject;

 
import com.baomidou.mybatisplus.plugins.Page;
import com.zhy.common.utils.PageUtils;
import com.zhy.common.utils.R;
import com.zhy.common.validator.ValidatorUtils;



/**
 * 企业信息表
 *
 * @author xtp
 * @email xtp1211@163.com
 * @date 2018-09-06 10:13:31
 */
@RestController
@RequestMapping("/company")
@Api(tags="管理后台首页接口")
public class CompanyController {
    @Autowired
    private CompanyService companyService;
    @Autowired
    private CompanySbxxService companySbxxService;
    /**
     * 列表,
     * 按createtime 时间排序
     * 请求参数：公司名称:Companyname
     */
    @RequestMapping("/list")
    //@RequiresPermissions("company:company:list")
    public R list(@RequestParam Map<String, Object> params){
    	
        PageUtils page = companyService.queryPage(params);
        return R.ok().put("page", page);
    }
    
     /**
      * 
      * 
      * 
      * @param params（分页，页码，查询的参数:companyId,companyName,address   ）
      * @return
      */
    @RequestMapping("/listId/{companyid}")
    //@RequiresPermissions("company:company:list")
    public R listById(@RequestParam Map<String, Object> params){
          
    	params = companyService.queryCompanyMapPageList(params);
 
    	
    	//HashMap<String ,Object>   rok=new HashMap<String ,Object> ();
     
    
    	Page<Map<String, Object>> page= (Page<Map<String, Object>>) params.get("page");
    	params.put("rows",page.getRecords());
    	params.put("total",page.getTotal());
       
    	return R.ok().put("list", params);  
    }
    
    /**
     * 
     *  根据输入的名称，地址 等模糊查询公司信息
     * @param params
     * @return
     */
    @RequestMapping("/listAll")
    //@RequiresPermissions("company:company:list")
    public R comlist(@RequestParam Map<String, Object> params){
     
    	params = companyService.queryCompanyMapPageList(params);
      
    	
    	HashMap<String ,Object>   rok=new HashMap<String ,Object> ();
    	
    	Page<Map<String, Object>>  page=(Page<Map<String, Object>>) params.get("page");
    	rok.put("rows",page.getRecords());
    	rok.put("total",page.getTotal());
    	 
    	return R.ok().put("jsonObject", rok);
    }
    
    
    /**
     * 根据名称查询
     */
    @RequestMapping("/selectList/{companyname}")
    //@RequiresPermissions("company:company:list")
    public R selectList(@PathVariable("companyname") String companyname,@RequestParam Map<String, Object> params){
    	
    	
    	PageUtils page = companyService.selectList(companyname,params);
    	
    	List<CompanyEntity> list = (List<CompanyEntity>) page.getList();
        for(CompanyEntity com:list){
        	com.setName(com.getCompanyname());
      
        	com.setId(com.getCompanyid());
        }
    	HashMap<String ,Object>   rok=new HashMap<String ,Object> ();
    	rok.put("name", "智慧运维平台");
    	rok.put("open", "open");
    	rok.put("children",list);
    	return R.ok().put("jsonObject", rok);
    }
    
    @RequestMapping("/initList")
    //@RequiresPermissions("company:company:list")
    public R initList(@RequestParam Map<String, Object> params){
    	
    	PageUtils page = companyService.initList(params);
    	 
        List<CompanyEntity> list=(List<CompanyEntity>) page.getList();
        for(CompanyEntity com:list){
        	com.setName(com.getCompanyname());
      
        	com.setId(com.getCompanyid());
        }
    	HashMap<String ,Object>   rok=new HashMap<String ,Object> ();
    	rok.put("name", "智慧运维平台");
    	rok.put("open", "open");
    	rok.put("children",list);
    	return R.ok().put("jsonObject", rok);
     
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{companyid}")
    @RequiresPermissions("company:company:info")
    public R info(@PathVariable("companyid") String companyid){
        CompanyEntity company = companyService.selectById(companyid);
        return R.ok().put("company", company);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("company:company:save")
    public R save(@RequestBody CompanyEntity company){
    	 
        companyService.insert(company);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("company:company:update")
    public R update(@RequestBody CompanyEntity company){
        ValidatorUtils.validateEntity(company);
        companyService.updateAllColumnById(company);//全部更新
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("company:company:delete")
    public R delete(@RequestBody String[] companyids){
        companyService.deleteBatchIds(Arrays.asList(companyids));
        return R.ok();
    }
    /**
     * 
     * 根据公司收入的名称模糊查询公司LIST
     * comName 模糊查询的 条件，必输
     * showSl 根据模糊查询返回的条数
     * @return
     */
    @RequestMapping("/loadList")
    @ApiOperation(value="模糊查询公司",notes="")
    public R listCompanyByNameInSize(@RequestParam(value = "comName", required = true) String companyName,@RequestParam(value = "showSl", defaultValue = "20")Integer showSl){
    	
    	
    	   List<Map<String,Object>> comList=companyService.loadCompanyNameQueryInShowSize(companyName,showSl);
    	   return R.ok().put("list", comList);
    }
    /**
     * 
     *  得到公司 所有公司 的名称，地址信息（前showSl的公司） 用来显示地址，在顶图定位
     *  
     * @return
     * 上午11:31:45
     */
    @PostMapping("/api/IndexInfo/{showSl}")
    @ApiOperation(value="注册",notes="得到公司 所有公司 的名称，地址信息（前showSl的公司） 用来显示地址，在顶图定位,公司数量，在线公司数量，设备数量，在线设备数量")
        public  R  CompanyAllInfo(@PathVariable("showSl") Integer showSl){
   
    	   Integer companySl =companyService.CompanyAllSl("");//所有的公司数量
    	   Integer companySlOnLine =companyService.CompanyAllSl("Y");//在线的公司数量
    	  //按创建时间降序获取到要在地图上显示的公司的名称，地址信息，
    	   //后期可能需要根据是否告警 等来在地图上显示信息
    	   List<Map<String ,Object>> mapcompanyList=companyService.CompanyListShowInMap(showSl,"createtime");
    	   Integer sbSl =companySbxxService.CompanyAllSbxx("");//设备信息
    	   Integer sbOnline =companySbxxService.CompanyAllSbxxOnline("");//在线的设备设备信息  
    	   R r=new R();
    	   r.put("companySl", companySl);
    	   r.put("companySlOnLine",companySlOnLine);
    	   r.put("mapcompanyList", mapcompanyList);
           r.put("sbSl", sbSl);
    	   r.put("sbOnline",sbOnline);
    	   return  r;
    }
    
}
