/**
 * Created By: Patryk Niziołek
 * Created in: 2022
 * Updated in: 2024
 */
package pl.ttpsc.javaupdate.project.view;

import pl.ttpsc.javaupdate.project.model.Document;

import java.util.List;

public interface DocumentView {

void display(List<Document> documents);
void display(Document document);

}
