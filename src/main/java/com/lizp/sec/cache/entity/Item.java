package com.lizp.sec.cache.entity;

import java.io.Serializable;

public class Item implements Serializable{
	// is org.springframework.core.serializer.support.SerializationFailedException: Failed to serialize object using DefaultSerializer;
   private Long id;// id
   private Long merchantId;//  商家id
   private Long depotId;// 商家仓库id
   private Integer auditStatus;// 审核状态:1-草稿,2-待审核,3-审核通过,4-审核不通过,5-商家不可见
   private Long companyId;// 公司id
   private String productCode;// 商品编码
   private String productName;// 商品名称
   private String thumb;// 主图
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}
	public Long getDepotId() {
		return depotId;
	}
	public void setDepotId(Long depotId) {
		this.depotId = depotId;
	}
	public Integer getAuditStatus() {
		return auditStatus;
	}
	public void setAuditStatus(Integer auditStatus) {
		this.auditStatus = auditStatus;
	}
	public Long getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getThumb() {
		return thumb;
	}
	public void setThumb(String thumb) {
		this.thumb = thumb;
	}
   
   
}
