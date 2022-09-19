package ua.com.clearsolution.facade;

import org.springframework.web.context.request.WebRequest;

import ua.com.clearsolution.persistence.entity.User;
import ua.com.clearsolution.view.dto.request.RequestDto;
import ua.com.clearsolution.view.dto.response.PageData;
import ua.com.clearsolution.view.dto.response.ResponseDto;

public interface BaseFacade <REQ extends RequestDto, RES extends ResponseDto> extends MainFacade {
    User createAndReturn(REQ req);
    void create(REQ req);
    User updateAndReturn(REQ req, long id);
    void update(REQ req, long id);
    void delete(long id);
    RES findById(long id);
    PageData<RES> findAll(WebRequest request);
}