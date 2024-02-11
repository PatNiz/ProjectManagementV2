/**
 * Created By: Patryk Niziołek
 * Created in: 2022
 * Updated in: 2024
 */
package pl.ttpsc.javaupdate.project.model;

import lombok.*;
import pl.ttpsc.javaupdate.project.persistence.Persistable;
@Setter
@Getter
@AllArgsConstructor
@ToString
@NoArgsConstructor
public class Document implements Persistable {

    private Long id;
    private String title;
    private String content;
    private String description;
    private String topic;
    private Long creatorid;
    private Long projectId;

    public Document(Long id, String title, String content, String description, String topic, Long projectId) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.description = description;
        this.topic = topic;
        this.projectId = projectId;
    }
    public Document(String title, String content, String description, String topic, Long projectId) {
        this.title = title;
        this.content = content;
        this.description = description;
        this.topic = topic;
        this.projectId = projectId;
    }
}