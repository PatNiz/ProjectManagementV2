/**
 * Created By: Patryk Niziołek
 * Created in: 2022
 * Updated in: 2024
 */
package pl.ttpsc.javaupdate.project.view.console;

import pl.ttpsc.javaupdate.project.model.Document;
import pl.ttpsc.javaupdate.project.view.DocumentView;
import java.util.List;
import java.util.Scanner;


public class DocumentConsoleView extends ConsolePrinterHelper implements DocumentView {

    private Scanner sc = new Scanner(System.in);
    @Override
    public void display(List<Document> documents) {
        if (documents.isEmpty()) {
            printLine("List is empty");
        } else {
            documents.forEach(document -> printLine(document.toString()));
        }
    }
    @Override
    public void display(Document document) {
        printLine(document.toString());
    }
    public Document createDocument() {
        printLine("Title: ");
        String title = sc.nextLine();
        printLine("Description: ");
        String description = sc.nextLine();
        printLine("Topic:");
        String topic = sc.nextLine();
        printLine("Content: ");
        String content = sc.nextLine();
        printLine("Attach to project with id: ");
        Long projectId = sc.nextLong();
        return new Document(title,content,description,topic,projectId);
    }
    public Document updateDocument(Long id,Long projectId) {
        printLine("Title: ");
        String title = sc.nextLine();
        printLine("Description: ");
        String description = sc.nextLine();
        printLine("Topic:");
        String topic = sc.nextLine();
        printLine("Content: ");
        String content = sc.nextLine();
        return new Document(id,title,content,description,topic,projectId);
    }
}

