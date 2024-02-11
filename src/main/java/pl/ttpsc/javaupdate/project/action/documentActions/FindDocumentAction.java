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
import pl.ttpsc.javaupdate.project.model.Role;
import pl.ttpsc.javaupdate.project.repository.DocumentRepository;
import pl.ttpsc.javaupdate.project.utility.MenuComponent;
import pl.ttpsc.javaupdate.project.view.DocumentView;
import pl.ttpsc.javaupdate.project.view.console.DocumentConsoleView;

import java.util.*;

import static pl.ttpsc.javaupdate.project.security.Authorization.isUserAuthorizedToPerformAction;

@Log4j2
public class FindDocumentAction implements Action, MenuComponent {
    private String name;
    private Config config;
    private CompanyUser user;

    public FindDocumentAction(String name, Config config, CompanyUser user) {
        this.name = name;
        this.config = config;
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void execute() {
        DocumentView documentView = config.getDocumentView();
        DocumentRepository docRepo = config.getDocumentRepository();
        if (documentView instanceof DocumentConsoleView) {
            Long projectId = ((DocumentConsoleView) documentView).getProjectId();
            if (isUserAuthorizedToPerformAction(user, projectId, config, getAllowedRoles())) {
                Long elementId = ((DocumentConsoleView) documentView).getElementId();
                docRepo.findDocumentFromProject(elementId, projectId)
                        .ifPresentOrElse(
                                document -> documentView.display(document),
                                () -> log.info("Document with ID " + elementId + " not found.")
                        );
            } else {
                log.info("You are not authorized to find this documents from project:" + projectId);
            }
        }
    }

    @Override
    public List<Role> getAllowedRoles() {
        return Arrays.asList(Role.ENGINEER, Role.MANAGER, Role.HR);
    }

}
