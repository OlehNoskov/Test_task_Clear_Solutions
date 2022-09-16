package ua.com.clearsolution.view.dto.request;

import lombok.Data;

@Data
public class SortData {
    private String sort;
    private String order;

    public SortData(String sort, String order) {
        this.sort = sort;
        this.order = order;
    }
}