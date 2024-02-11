/**
 * Created By: Patryk Niziołek
 * Created in: 2022
 * Updated in: 2024
 */
package pl.ttpsc.javaupdate.project.action.documentActions;

import lombok.extern.log4j.Log4j2;

import pl.ttpsc.javaupdate.project.action.Action;
import pl.ttpsc.javaupdate.project.config.Config;
import pl.ttpsc.javaupdate.project.model.CompanyUser;
import pl.ttpsc.javaupdate.project.model.Document;
import pl.ttpsc.javaupdate.project.model.Project;
import pl.ttpsc.javaupdate.project.model.Role;
import pl.ttpsc.javaupdate.project.persistence.sql.SqlService;
import pl.ttpsc.javaupdate.project.repository.DocumentRepository;
import pl.ttpsc.javaupdate.project.repository.ProjectRepository;
import pl.ttpsc.javaupdate.project.utility.MenuComponent;
import pl.ttpsc.javaupdate.project.view.DocumentView;
import pl.ttpsc.javaupdate.project.view.console.DocumentConsoleView;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static pl.ttpsc.javaupdate.project.security.Authorization.isUserAuthorizedToPerformAction;

@Log4j2
public class CreateDocumentAction implements Action, MenuComponent {

    private String name;
    private Config config;
    private CompanyUser docCreator;

    public CreateDocumentAction(String name, Config config, CompanyUser docCreator) {
        this.name = name;
        this.config = config;
        this.docCreator = docCreator;
    }

    public String getName() {
        return name;
    }

    public void execute() {
        DocumentView documentView = config.getDocumentView();
        DocumentRepository documentRepository = config.getDocumentRepository();
        ProjectRepository projectRepository = config.getProjectRepository();
        if (documentView instanceof DocumentConsoleView) {
            Document document = ((DocumentConsoleView) documentView).createDocument();
            Optional<Project> projectById = projectRepository.findProjectById(document.getProjectId());
            projectById.ifPresentOrElse(project -> {
                if (isUserAuthorizedToPerformAction(docCreator, projectById.get().getId(), config, getAllowedRoles())) {
                    document.setCreatorid(docCreator.getId());
                    document.setId(SqlService.generateId(document));
                    documentRepository.save(document);
                    log.info("Document added");
                } else {
                    log.info("You are not authorized to create document.");
                }
            }, () -> log.info("Project with ID " + document.getProjectId() + " not found."));
        }
    }

    @Override
    public List<Role> getAllowedRoles() {
        return Arrays.asList(Role.ENGINEER, Role.MANAGER);
    }

}



