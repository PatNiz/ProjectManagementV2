package pl.ttpsc.javaupdate.project.persistence.json;

import java.io.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import pl.ttpsc.javaupdate.project.persistence.*;


public class JsonPersistenceManager implements PersistenceManager {

    @Override
    public List<Persistable> read(Criteria criteria) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = getJsonNode(criteria);
        JsonNode persistableNode = rootNode.get(criteria.getClassType().getSimpleName().toLowerCase());
        if (persistableNode == null) {
            throw new IllegalArgumentException("Could not find any objects of type " + criteria.getClassType().getSimpleName() + " in the JSON file.");
        }
        List<Persistable> persistableObjects = new ArrayList<>();
        for (JsonNode node : persistableNode) {
            boolean match = true;
            for (Criterion criterion : criteria.getCriteria()) {
                JsonNode valueNode = node.get(criterion.getColumnName());
                if (valueNode == null || !criterion.getOperator().evaluate(valueNode.asText(), criterion.getValue())) {
                    match = false;
                    break;
                }
            }
            if (match) {
                Persistable persistableObject = (Persistable) mapper.treeToValue(node, criteria.getClassType());
                persistableObjects.add(persistableObject);
            }
        }
        return persistableObjects;
    }

    @Override
    public void update(Persistable persistable) {

    }

    @Override
    public void update(Integer id, Persistable persistableObject) throws SQLException {

    }

    @Override
    public void delete(Persistable persistable) {

    }

    @Override
    public List<Persistable> find(QuerySpec queySpec) {
        return null;
    }

    private JsonNode getJsonNode(Criteria criteria) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode;
        if (criteria.getFilePath() != null) {
            File jsonFile = new File(criteria.getFilePath());
            rootNode = mapper.readTree(jsonFile);
        } else if (criteria.getJsonString() != null) {
            rootNode = mapper.readTree(criteria.getJsonString());
        } else {
            throw new IllegalArgumentException("Criteria must have either file path or json string set");
        }
        return rootNode;
    }
}