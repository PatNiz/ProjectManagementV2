/**
 * Created By: Patryk Niziołek
 * Created in: 2022
 * Updated in: 2024
 */
package pl.ttpsc.javaupdate.project.model;

import lombok.*;
import pl.ttpsc.javaupdate.project.persistence.Persistable;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Project implements Persistable {
    private Long id;
    private String name;
    private Long creatorId;

    public Project( String name) {
        this.name = name;
    }
}
