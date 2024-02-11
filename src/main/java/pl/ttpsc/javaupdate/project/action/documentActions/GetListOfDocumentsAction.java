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

import java.util.Arrays;
import java.util.List;

import static pl.ttpsc.javaupdate.project.security.Authorization.isUserAuthorizedToPerformAction;

@Log4j2
public class GetListOfDocumentsAction implements Action, MenuComponent {
    private String name;
    private Config config;
    private CompanyUser user;

    public GetListOfDocumentsAction(String name, Config config, CompanyUser user) {
        this.name = name;
        this.config = config;
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void execute() {
        DocumentRepository documentRepository = config.getDocumentRepository();
        DocumentView documentView = config.getDocumentView();
        if (documentView instanceof DocumentConsoleView) {
            Long projectId = ((DocumentConsoleView) documentView).getProjectId();
            if (isUserAuthorizedToPerformAction(user, projectId, config, getAllowedRoles())) {
                documentView.display(documentRepository.findAllDocuments(projectId));
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



