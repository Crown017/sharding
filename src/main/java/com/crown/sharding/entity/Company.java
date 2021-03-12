package com.crown.sharding.entity;

/**
 * @author luhaihui
 * @create 2021/3/12 下午5:54
 */
public class Company {

    private String compname;
    private String companyCode;
    private Long companyId;

    public String getCompname() {
        return compname;
    }

    public void setCompname(String compname) {
        this.compname = compname;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }
}
