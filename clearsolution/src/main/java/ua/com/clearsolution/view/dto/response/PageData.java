package ua.com.clearsolution.view.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class PageData<REQ extends ResponseDto> {
    @Getter
    @Setter
    private int currentPage;
    @Getter
    @Setter
    private int pageSize;
    @Getter
    @Setter
    private int totalPageSize;
    @Getter
    @Setter
    private long itemsSize;
    @Getter
    @Setter
    private List<REQ> items;
    @Getter
    @Setter
    private final int[] pageSizeItems;
    @Getter
    @Setter
    private boolean showFirst;
    @Getter
    @Setter
    private boolean showPrevious;
    @Getter
    @Setter
    private boolean showNext;
    @Getter
    @Setter
    private boolean showLast;
    @Getter
    @Setter
    private String sort;
    @Getter
    @Setter
    private String order;
    @Getter
    @Setter
    private int currentShowFromEntries;
    @Getter
    @Setter
    private int currentShowToEntries;

    public PageData() {
        this.currentPage = 0;
        this.pageSizeItems = new int[]{10, 25, 50, 100};
        this.pageSize = this.pageSizeItems[0];
        this.totalPageSize = 0;
        this.itemsSize = 0;
        this.items = new ArrayList<>();
        this.showFirst = false;
        this.showPrevious = false;
        this.showNext = false;
        this.showLast = false;
    }

    public void initPaginationState(int currentPage) {
        if (pageSize < itemsSize) {
            this.totalPageSize = (int) itemsSize / pageSize;
            this.showFirst = this.currentPage != 1;
            this.showPrevious = this.currentPage - 1 != 0;
            this.showLast = this.currentPage - 1 != totalPageSize;
            this.showNext = this.currentPage - 1 != totalPageSize;
        }
        currentShowFromEntries = ((this.currentPage - 1) * pageSize) + 1;
        currentShowToEntries = ((this.currentPage - 1) * pageSize) + items.size();
    }
}