package ua.com.clearsolution.persistence.datatable;

import lombok.Getter;
import lombok.Setter;
import ua.com.clearsolution.persistence.entity.BaseEntity;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class DataTableResponse <E extends BaseEntity>{
    @Getter
    @Setter
    private int currentPage;
    @Getter
    @Setter
    private int pageSize;
    @Getter
    @Setter
    private int size;
    @Getter
    @Setter
    private long itemsSize;
    @Getter
    @Setter
    private String sort;
    @Getter
    @Setter
    private String order;
    @Getter
    @Setter
    private List<E> items;
    @Getter
    @Setter
    private Map<Object, Object> otherParamMap;

    public DataTableResponse() {
        this.currentPage = 1;
        this.pageSize = 10;
        this.itemsSize = 0;
        this.items = Collections.emptyList();
        this.otherParamMap = Collections.emptyMap();
    }
}