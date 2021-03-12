package com.crown.sharding.controller;

import com.crown.sharding.entity.Company;
import com.crown.sharding.mapper.CompanyMapper;
import org.apache.shardingsphere.core.strategy.keygen.SnowflakeShardingKeyGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author luhaihui
 * @create 2021/3/12 下午6:01
 */
@RestController
@RequestMapping("company")
public class CompanyController {

    @Resource
    CompanyMapper companyMapper;

    @Resource
    SnowflakeShardingKeyGenerator userKeyGenerator;

    @GetMapping("/create")
    public String company(@RequestParam String name,@RequestParam String code){
        Comparable<?> comparable = userKeyGenerator.generateKey();
        Company company = new Company();
        company.setCompname(name);
        company.setCompanyCode(code);
        company.setCompanyId((Long) comparable);
        companyMapper.addCompany(company);
        return "success";
    }

    @GetMapping("/info")
    public Company companyInfo(@RequestParam Long companyId){
        Company company = companyMapper.getCompany(companyId);
        return company;
    }
}
