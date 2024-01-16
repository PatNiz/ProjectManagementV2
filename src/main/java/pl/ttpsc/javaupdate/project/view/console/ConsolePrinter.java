package pl.ttpsc.javaupdate.project.view.console;

import pl.ttpsc.javaupdate.project.model.Document;
import pl.ttpsc.javaupdate.project.model.User;

import java.util.Collection;
import java.util.Scanner;

public class ConsolePrinter {
    private Scanner sc = new Scanner(System.in);

    public void printDocuments(Collection<Document> documents) {
        documents.stream()
                .map(Document::toString)
                .forEach(this::printLine);

    }

    public void printLine(String text) {
        System.out.println(text);
    }

}
