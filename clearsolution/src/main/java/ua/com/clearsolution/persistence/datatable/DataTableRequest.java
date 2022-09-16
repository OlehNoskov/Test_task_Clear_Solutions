package ua.com.clearsolution.persistence.datatable;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

public class DataTableRequest {
    @Getter
    @Setter
    private int page;
    @Getter
    @Setter
    private int size;
    @Getter
    @Setter
    private String order;
    @Getter
    @Setter
    private String sort;
    @Getter
    @Setter
    private Map<String, String[]> requestParamMap;
}