package pl.ttpsc.javaupdate.project;


import pl.ttpsc.javaupdate.project.config.Config;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.ttpsc.javaupdate.project.model.Document;
import pl.ttpsc.javaupdate.project.model.Project;
import pl.ttpsc.javaupdate.project.persistence.sql.SqlPersistenceManager;
import pl.ttpsc.javaupdate.project.persistence.sql.SqlService;
import pl.ttpsc.javaupdate.project.utility.Menu;
import pl.ttpsc.javaupdate.project.view.console.CompanyMenu;

public class ConsoleApplication {

    public static void main(String[] args) throws SQLException, IOException {
        Config config = new Config();
               // .withSqlPersistence();
        Scanner scanner = new Scanner(System.in);
        SqlService sqlService = new SqlService();
        sqlService.createTable(Project.class);
        sqlService.createTable(Document.class);
        Menu menu = CompanyMenu.getMainMenu();
        menu.chooseOption(scanner);

        //final Logger log = LoggerFactory.getLogger(ConsoleApplication.class);

        final Logger LOGGER = LogManager.getLogger(ConsoleApplication.class);
        LOGGER.error("error");
        LOGGER.info("This is an info message.");
        LOGGER.warn("This is a warning message.");

        SqlPersistenceManager sqlPersistenceService = new SqlPersistenceManager();


        /*
        Project proj1 = new Project(1, "proj1");
        Document doc1 = new Document(1,"doc1","content doc1");
        Document doc2 = new Document(2, "doc2","content doc2");
        Document doc3 = new Document(3,"doc3","content doc3");

        //sqlPersistenceService.insert(proj1);
       //sqlPersistenceService.insert(doc1);
       // sqlPersistenceService.insert(doc2);
       // sqlPersistenceService.insert(doc3);
        Criteria docCriteriaFromSql = new Criteria();
        docCriteriaFromSql.setClassType(Document.class);
        List<Persistable> documentsFromDatabase = sqlPersistenceService.read(docCriteriaFromSql);
        System.out.println("Documents from database: " + documentsFromDatabase);
        sqlPersistenceService.delete(doc3);
        List<Persistable> documentsWithoutDoc3FromDatabase = sqlPersistenceService.read(docCriteriaFromSql);
        System.out.println("Documents list after delete doc3: "+ documentsWithoutDoc3FromDatabase);
        sqlPersistenceService.update(2,new Document(2,"doc2","content changed"));
        List<Persistable> documentsWithUpdatedDoc2FromDatabase = sqlPersistenceService.read(docCriteriaFromSql);
        System.out.println("Documents with updated Doc2 From Database: "+ documentsWithUpdatedDoc2FromDatabase);
        Criterion docCriterion = new Criterion("name", Operator.Equals, "doc2");
        docCriteriaFromSql.addCriterion(docCriterion);
        List<Persistable> documentsWithFiltrFromDatabase = sqlPersistenceService.read(docCriteriaFromSql);
        System.out.println("Documents with filtr(\"name\"=\"doc2\") from database: " + documentsWithFiltrFromDatabase);
        Criteria projectCriteriaFromSql = new Criteria();
        Criterion projectCriterion = new Criterion("name", Operator.Equals, "newu");
        projectCriteriaFromSql.addCriterion(projectCriterion);
        projectCriteriaFromSql.setClassType(Document.class);
        List<Persistable> projectsFromDatabase = sqlPersistenceService.read(docCriteriaFromSql);
        System.out.println("Projects from database: " + projectsFromDatabase);
*/







    }
}
