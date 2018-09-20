package com.zhy.modules.company.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

/**
 * 企业信息表
 * 
 * @author xtp
 * @email xtp1211@163.com
 * @date 2018-09-06 10:13:31
 */
@TableName("t_company")
public class CompanyEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 企业表ID，UUID表示
	 */
	@TableId(type=IdType.UUID)
	private String companyid;
	/**
	 * 企业名称
	 */
	private String companyname;
	/**
	 * 用户性质，对应dict表lx为用户性质的代码
	 */
	private String property;
	/**
	 * 企业类型，质，对应dict表lx为用户类别的代码
	 */
	private String type;
	/**
	 * 供电点位
	 */
	private String gddw;
	/**
	 * 客户营业号
	 */
	private String khyyh;
	/**
	 * 计费方式—需量
	 */
	private BigDecimal jffsXl;
	/**
	 * 计费方式-容量
	 */
	private BigDecimal jffsRl;
	/**
	 * 计量方式
	 */
	private String jlfs;
	/**
	 * 录入时间
	 */
	private Date createtime;
	/**
	 * 修改时间
	 */
	private Date modifytime;
	/**
	 * 录入人
	 */
	private Long lrr;
	/**
	 * 省份（省，市）
	 */
	private Long province;
	/**
	 * 市（市-区）
	 */
	private Long city;
	/**
	 * 县
	 */
	private Long county;
	/**
	 * 详细地址地址，通过该地址定位地图
	 */
	private String address;
	/**
	 * 经度
	 */
	private String lon;
	/**
	 * 维度
	 */
	private String lat;
	/**
	 * 所属部分或者分公司（是哪个分公司或者部门的客户）sys_dept
	 */
	private Long dept;
	/**
	 * 所属业务
	 */
	private Long ssyw;
	/**
	 * 
	 * 是否启用  （正常来说，合作的客户就是启用的，但是不一定在线，在线是采集到了平台的数据的用户）
	 */
	private  String xybz;
	/**
	 * 是否在线
	 */
    private  String online;
    @TableField(exist=false)
    private String id;
    @TableField(exist=false)
    private String name;
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 设置：企业表ID，UUID表示
	 */
	public void setCompanyid(String companyid) {
		this.companyid = companyid;
	}
	/**
	 * 获取：企业表ID，UUID表示
	 */
	public String getCompanyid() {
		return companyid;
	}
	/**
	 * 设置：企业名称
	 */
	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}
	/**
	 * 获取：企业名称
	 */
	public String getCompanyname() {
		return companyname;
	}
	/**
	 * 设置：用户性质，对应dict表lx为用户性质的代码
	 */
	public void setProperty(String property) {
		this.property = property;
	}
	/**
	 * 获取：用户性质，对应dict表lx为用户性质的代码
	 */
	public String getProperty() {
		return property;
	}
	/**
	 * 设置：企业类型，质，对应dict表lx为用户类别的代码
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * 获取：企业类型，质，对应dict表lx为用户类别的代码
	 */
	public String getType() {
		return type;
	}
	/**
	 * 设置：供电点位
	 */
	public void setGddw(String gddw) {
		this.gddw = gddw;
	}
	/**
	 * 获取：供电点位
	 */
	public String getGddw() {
		return gddw;
	}
	/**
	 * 设置：客户营业号
	 */
	public void setKhyyh(String khyyh) {
		this.khyyh = khyyh;
	}
	/**
	 * 获取：客户营业号
	 */
	public String getKhyyh() {
		return khyyh;
	}
	/**
	 * 设置：计费方式—需量
	 */
	public void setJffsXl(BigDecimal jffsXl) {
		this.jffsXl = jffsXl;
	}
	/**
	 * 获取：计费方式—需量
	 */
	public BigDecimal getJffsXl() {
		return jffsXl;
	}
	/**
	 * 设置：计费方式-容量
	 */
	public void setJffsRl(BigDecimal jffsRl) {
		this.jffsRl = jffsRl;
	}
	/**
	 * 获取：计费方式-容量
	 */
	public BigDecimal getJffsRl() {
		return jffsRl;
	}
	/**
	 * 设置：计量方式
	 */
	public void setJlfs(String jlfs) {
		this.jlfs = jlfs;
	}
	/**
	 * 获取：计量方式
	 */
	public String getJlfs() {
		return jlfs;
	}
	/**
	 * 设置：录入时间
	 */
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	/**
	 * 获取：录入时间
	 */
	public Date getCreatetime() {
		return createtime;
	}
	/**
	 * 设置：修改时间
	 */
	public void setModifytime(Date modifytime) {
		this.modifytime = modifytime;
	}
	/**
	 * 获取：修改时间
	 */
	public Date getModifytime() {
		return modifytime;
	}
	/**
	 * 设置：录入人
	 */
	public void setLrr(Long lrr) {
		this.lrr = lrr;
	}
	/**
	 * 获取：录入人
	 */
	public Long getLrr() {
		return lrr;
	}
	/**
	 * 设置：省份（省，市）
	 */
	public void setProvince(Long province) {
		this.province = province;
	}
	/**
	 * 获取：省份（省，市）
	 */
	public Long getProvince() {
		return province;
	}
	/**
	 * 设置：市（市-区）
	 */
	public void setCity(Long city) {
		this.city = city;
	}
	/**
	 * 获取：市（市-区）
	 */
	public Long getCity() {
		return city;
	}
	/**
	 * 设置：县
	 */
	public void setCounty(Long county) {
		this.county = county;
	}
	/**
	 * 获取：县
	 */
	public Long getCounty() {
		return county;
	}
	/**
	 * 设置：详细地址地址，通过该地址定位地图
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * 获取：详细地址地址，通过该地址定位地图
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * 设置：经度
	 */
	public void setLon(String lon) {
		this.lon = lon;
	}
	/**
	 * 获取：经度
	 */
	public String getLon() {
		return lon;
	}
	/**
	 * 设置：维度
	 */
	public void setLat(String lat) {
		this.lat = lat;
	}
	/**
	 * 获取：维度
	 */
	public String getLat() {
		return lat;
	}
	/**
	 * 设置：所属部分或者分公司（是哪个分公司或者部门的客户）sys_dept
	 */
	public void setDept(Long dept) {
		this.dept = dept;
	}
	/**
	 * 获取：所属部分或者分公司（是哪个分公司或者部门的客户）sys_dept
	 */
	public Long getDept() {
		return dept;
	}
	/**
	 * 设置：所属业务
	 */
	public void setSsyw(Long ssyw) {
		this.ssyw = ssyw;
	}
	/**
	 * 获取：所属业务
	 */
	public Long getSsyw() {
		return ssyw;
	}
	public String getXybz() {
		return xybz;
	}
	public void setXybz(String xybz) {
		this.xybz = xybz;
	}
	public String getOnline() {
		return online;
	}
	public void setOnline(String online) {
		this.online = online;
	}
	@Override
	public String toString() {
		return "CompanyEntity [companyid=" + companyid + ", companyname=" + companyname + ", property=" + property
				+ ", type=" + type + ", gddw=" + gddw + ", khyyh=" + khyyh + ", jffsXl=" + jffsXl + ", jffsRl=" + jffsRl
				+ ", jlfs=" + jlfs + ", createtime=" + createtime + ", modifytime=" + modifytime + ", lrr=" + lrr
				+ ", province=" + province + ", city=" + city + ", county=" + county + ", address=" + address + ", lon="
				+ lon + ", lat=" + lat + ", dept=" + dept + ", ssyw=" + ssyw + ", xybz=" + xybz + ", online=" + online
				+ "]";
	}
	
}