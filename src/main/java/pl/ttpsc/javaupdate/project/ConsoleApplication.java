/**
 * Created By: Patryk Niziołek
 * Created in: 2022
 * Updated in: 2024
 */
package pl.ttpsc.javaupdate.project;


import pl.ttpsc.javaupdate.project.action.LoginAction;
import pl.ttpsc.javaupdate.project.config.Config;


public class ConsoleApplication {
    public static void main(String[] args)  {
        Config config = new Config()
                .withSqlPersistence()
                .withConsoleView();
        LoginAction loginAction = new LoginAction("Login",config);
        loginAction.execute();
    }
}
