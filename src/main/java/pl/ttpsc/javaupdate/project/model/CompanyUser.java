/**
 * Created By: Patryk Niziołek
 * Created in: 2022
 * Updated in: 2024
 */
package pl.ttpsc.javaupdate.project.model;

import lombok.*;
import pl.ttpsc.javaupdate.project.persistence.Persistable;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CompanyUser implements Persistable {

    private Long id;
    private String username;
    private String password;

    public CompanyUser(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
