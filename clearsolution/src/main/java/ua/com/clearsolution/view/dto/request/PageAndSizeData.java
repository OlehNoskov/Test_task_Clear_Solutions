package ua.com.clearsolution.view.dto.request;

import lombok.Getter;
import lombok.Setter;

public class PageAndSizeData {
    @Getter
    @Setter
    int page;
    @Getter
    @Setter
    int size;

    public PageAndSizeData() {
    }

    public PageAndSizeData(int page, int size) {
        this.page = page;
        this.size = size;
    }
}