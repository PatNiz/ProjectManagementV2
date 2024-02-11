/**
 * Created By: Patryk Niziołek
 * Created in: 2022
 * Updated in: 2024
 */
package pl.ttpsc.javaupdate.project.repository;

import pl.ttpsc.javaupdate.project.model.Document;
import pl.ttpsc.javaupdate.project.persistence.PersistenceManager;
import pl.ttpsc.javaupdate.project.persistence.QuerySpec;
import java.util.*;


public class DocumentRepository {

    private final PersistenceManager manager;
    public DocumentRepository(PersistenceManager manager){
        this.manager = manager;
    }

    public void save(Document document){ manager.insert(document); }
    public void delete(Document document){
        manager.delete(document);
    }

    public void update(Document document){ manager.update(document);}
    public List<Document> findAllDocuments(Long projectId) {
        QuerySpec<Document> documentQuerySpec = new QuerySpec<>(Document.class);
        documentQuerySpec.addCondition("projectid",projectId);
        return manager.find(documentQuerySpec);
    }
    public Optional<Document> findDocumentByName(String name){
        QuerySpec<Document> querySpec = new QuerySpec<>(Document.class);
        querySpec.addCondition("name",name);
        List<Document> documents = manager.find(querySpec);
        return documents.isEmpty() ? Optional.empty() : Optional.of(documents.get(0));
    }
    public Optional<Document> findDocumentById(Long id){
        QuerySpec<Document> querySpec = new QuerySpec<>(Document.class);
        querySpec.addCondition("id",id);
        List<Document> documents = manager.find(querySpec);
        return documents.isEmpty() ? Optional.empty() : Optional.of( documents.get(0));
    }
    public Optional<Document> findDocumentFromProject(Long id,Long projectId){
        QuerySpec<Document> querySpec = new QuerySpec<>(Document.class);
        querySpec.addCondition("id",id);
        querySpec.addCondition("projectid",projectId);
        List<Document> documents = manager.find(querySpec);
        return documents.isEmpty() ? Optional.empty() : Optional.of( documents.get(0));
    }

}
