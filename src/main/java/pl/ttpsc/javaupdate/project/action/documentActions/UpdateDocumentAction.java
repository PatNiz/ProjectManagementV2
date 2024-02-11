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
import pl.ttpsc.javaupdate.project.model.Role;
import pl.ttpsc.javaupdate.project.repository.DocumentRepository;
import pl.ttpsc.javaupdate.project.utility.MenuComponent;
import pl.ttpsc.javaupdate.project.view.DocumentView;
import pl.ttpsc.javaupdate.project.view.console.DocumentConsoleView;
import java.util.Arrays;
import java.util.List;


@Log4j2
public class UpdateDocumentAction implements Action, MenuComponent {
    private String name;
    private CompanyUser user;
    private Config config;

    public UpdateDocumentAction(String name, Config config, CompanyUser user) {
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
            Long documentId = ((DocumentConsoleView) documentView).getDocumentId();
            documentRepository.findDocumentFromProject(documentId, projectId)
                    .ifPresentOrElse(
                            document -> {
                                if (document.getCreatorid().equals(user.getId())) {
                                    Document updatedDocument = ((DocumentConsoleView) documentView).updateDocument(document.getId(), projectId);
                                    updatedDocument.setCreatorid(user.getId());
                                    updatedDocument.setProjectId(projectId);
                                    documentRepository.update(updatedDocument);
                                    log.info("document updated");
                                } else {
                                    log.info("You are not creator this document");
                                }
                            },
                            () -> log.info("Document with ID " + documentId + " not found.")
                    );

        }
    }

    @Override
    public List<Role> getAllowedRoles() {
        return Arrays.asList(Role.ENGINEER, Role.MANAGER);
    }


}