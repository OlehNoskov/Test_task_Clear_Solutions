package ua.com.clearsolution.view.dto.request;

import lombok.Getter;
import lombok.Setter;

public class SortData {
    @Getter
    @Setter
    private String sort;
    @Getter
    @Setter
    private String order;

    public SortData() {
    }

    public SortData(String sort, String order) {
        this.sort = sort;
        this.order = order;
    }
}