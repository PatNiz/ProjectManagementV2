/**
 * Created By: Patryk Niziołek
 * Created in: 2022
 * Updated in: 2024
 */
package pl.ttpsc.javaupdate.project.exception;

public class IdGenerationException extends RuntimeException {
    public IdGenerationException(String message, Throwable cause) {
        super(message, cause);
    }
}
