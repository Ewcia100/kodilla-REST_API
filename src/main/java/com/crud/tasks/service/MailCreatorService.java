package com.crud.tasks.service;

import com.crud.tasks.trello.config.AdminConfig;
import com.crud.tasks.trello.config.CompanyConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class MailCreatorService {
    @Autowired
    private AdminConfig adminConfig;

    @Autowired
    private CompanyConfig companyConfig;

    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;

    public String buildTrelloCardEmail(String message) {
        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("preview_message", "Message about new task");
        context.setVariable("tasks_url", "http://localhost:8888/tasks_frontend/");
        context.setVariable("button", "Visit website");
        context.setVariable("admin_name", adminConfig.getAdminName());
        context.setVariable("owner_name", companyConfig.getOwnerName());
        context.setVariable("owner_surname", companyConfig.getOwnerSurname());
        context.setVariable("company_name", companyConfig.getCompanyName());
        context.setVariable("company_address", companyConfig.getCompanyAddress());
        context.setVariable("company_mail", companyConfig.getCompanyEmail());
        return templateEngine.process("mail/created-trello-card-mail", context);
    }

}

