package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.config.Company;
import com.crud.tasks.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MailCreatorService {

    @Autowired
    private AdminConfig adminConfig;

    @Autowired
    private Company company;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;

    private List<String> taskTitleListCreator() {
        List<String> taskTitleList;

        taskTitleList = taskRepository.findAll().stream()
                .map(task -> task.getTitle())
                .collect(Collectors.toList());

        return taskTitleList;
    }

    public String buildTrelloCardEmail(String message) {

        List<String> functionality = new ArrayList<>();
        functionality.add("You can manage your task");
        functionality.add("Provides connection with Trello Account");
        functionality.add("Application allows sending task to Trello");

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
        context.setVariable("show_button", false);
        context.setVariable("is_friend", true);
        context.setVariable("admin_config", adminConfig);
        context.setVariable("application_functionality", functionality);
        return templateEngine.process("mail/created-trello-card-mail", context);
    }

    public String buildEmailScheduler(String message) {
        Context context = new Context();
        context.setVariable("preview_message", "Task: once a day email");
        context.setVariable("message", message);
        context.setVariable("task_url", "http://localhost:8888/tasks_frontend/");
        context.setVariable("button", "Show tasks");
        context.setVariable("admin_config", adminConfig);
        context.setVariable("goodbye_message", "Thank you for your attention!");
        context.setVariable("company", company);
        context.setVariable("is_friend", true);
        context.setVariable("tasks_list", taskTitleListCreator());
        return templateEngine.process("mail/scheduler-email", context);
    }
}
