package ua.com.clearsolution.util;

import ua.com.clearsolution.persistence.datatable.DataTableResponse;
import ua.com.clearsolution.persistence.entity.BaseEntity;
import ua.com.clearsolution.view.dto.response.PageData;
import ua.com.clearsolution.view.dto.response.ResponseDto;

public class WebResponseUtil {

    private WebResponseUtil() {}

    public static PageData<? extends ResponseDto> initPageData(DataTableResponse<? extends BaseEntity> tableResponse) {
        PageData<? extends ResponseDto> pageData = new PageData<>();
        pageData.setCurrentPage(tableResponse.getCurrentPage());
        pageData.setPageSize(tableResponse.getPageSize());
        pageData.setOrder(tableResponse.getOrder());
        pageData.setSort(tableResponse.getSort());
        pageData.setItemsSize(tableResponse.getItemsSize());
        pageData.initPaginationState();
        return pageData;
    }
}