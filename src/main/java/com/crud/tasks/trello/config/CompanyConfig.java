package com.crud.tasks.trello.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class CompanyConfig {
    @Value("${info.app.owner.name}")
    private String ownerName;
    @Value("${info.app.owner.surname}")
    private String ownerSurname;
    @Value("${info.company.name}")
    private String companyName;
    @Value("${info.app.administrator.address.street}")
    private String companyAddress;
    @Value("${info.company.email}")
    private String companyEmail;

}
