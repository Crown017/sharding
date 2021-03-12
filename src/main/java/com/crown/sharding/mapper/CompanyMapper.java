package com.crown.sharding.mapper;

import com.crown.sharding.entity.Company;

/**
 * @author luhaihui
 * @create 2021/3/12 下午5:55
 */
public interface CompanyMapper {

    void addCompany(Company company);

    Company getCompany(Long companyId);
}
