package com.capgemini.molveno.model.specifications;

public class SearchCriteria {
    private final String key;
    private final String operation;
    private final Object value;

    public SearchCriteria(String key, String operation, Object value) {
        this.key = key;
        this.operation = operation;
        this.value = value;
    }

    public String getOperation() {
        return operation;
    }

    public String getKey() {
        return key;
    }

    public Object getValue() {
        return value;
    }
}
