package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.config.Company;
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
    private Company company;

    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;

    public String buildTrelloCardEmail(String message) {
        Context context = new Context();
        context.setVariable("preview_message", "Created new trello card");
        context.setVariable("message", message);
        context.setVariable("task_url", "http://localhost:8888/tasks_frontend/");
        context.setVariable("button", "Visit website");
        context.setVariable("admin_name", adminConfig.getAdminName());
        context.setVariable("goodbye_message", "Thank you for your attention!");
        context.setVariable("company_details_name", company.getCompanyName());
        context.setVariable("company_details_goal", company.getCompanyGoal());
        context.setVariable("company_details_email", company.getCompanyEmail());
        context.setVariable("company_details_phone", company.getCompanyPhone());
        return templateEngine.process("mail/created-trello-card-mail", context);
    }
}
