package com.crud.tasks.service;

import com.crud.tasks.trello.config.AdminConfig;
import com.crud.tasks.trello.config.CompanyConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.ArrayList;
import java.util.List;

@Service
public class MailCreatorService {
    @Autowired
    private AdminConfig adminConfig;

    @Autowired
    private CompanyConfig companyConfig;

    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;


    public static List<String> functionality(){
        List<String> functionality=new ArrayList<>();
        functionality.add("You can manage your tasks");
        functionality.add("Provides connection with Trello Account");
        functionality.add("Application allows sending tasks to Trello");
        return functionality;
    }
    public String buildTrelloCardEmail(String message) {
        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("preview_message", "Message about new task");
        context.setVariable("tasks_url", "http://localhost:8888/tasks_frontend/");
        context.setVariable("button", "Visit website");
        context.setVariable("admin_config", adminConfig);
        context.setVariable("show_button", false);
        context.setVariable("owner_name", companyConfig.getOwnerName());
        context.setVariable("owner_surname", companyConfig.getOwnerSurname());
        context.setVariable("company_name", companyConfig.getCompanyName());
        context.setVariable("company_address", companyConfig.getCompanyAddress());
        context.setVariable("company_mail", companyConfig.getCompanyEmail());
        context.setVariable("is_friend", false);
        context.setVariable("application_functionality", MailCreatorService.functionality());
        return templateEngine.process("mail/created-trello-card-mail", context);
    }
    public String buildScheduledEmail(String message) {

        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("preview_message", "Message about new task");
        context.setVariable("tasks_url", "http://localhost:8888/tasks_frontend/");
        context.setVariable("button", "Visit website");
        context.setVariable("admin_config", adminConfig);
        context.setVariable("show_button", true);
        context.setVariable("owner_name", companyConfig.getOwnerName());
        context.setVariable("owner_surname", companyConfig.getOwnerSurname());
        context.setVariable("company_name", companyConfig.getCompanyName());
        context.setVariable("company_address", companyConfig.getCompanyAddress());
        context.setVariable("company_mail", companyConfig.getCompanyEmail());
        context.setVariable("is_friend", false);
        context.setVariable("application_functionality", MailCreatorService.functionality());
        return templateEngine.process("mail/created-trello-card-mail", context);
    }
}

