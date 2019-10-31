package com.zrp.orderservice.exception.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.zrp.orderservice.util.DateUtils;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Response<T> {

    private HttpStatus status;
    private T payload;
    private Object errors;
    private Object metadata;

    public static <T> Response<T> badRequest() {
        Response<T> response = new Response<>();
        response.setStatus(HttpStatus.BAD_REQUEST);
        return response;
    }

    public static <T> Response<T> ok() {
        Response<T> response = new Response<>();
        response.setStatus(HttpStatus.OK);
        return response;
    }

    public static <T> Response<T> unauthorized() {
        Response<T> response = new Response<>();
        response.setStatus(HttpStatus.UNAUTHORIZED);
        return response;
    }

    public static <T> Response<T> accessDenied() {
        Response<T> response = new Response<>();
        response.setStatus(HttpStatus.FORBIDDEN);
        return response;
    }

    public static <T> Response<T> notFound() {
        Response<T> response = new Response<>();
        response.setStatus(HttpStatus.NOT_FOUND);
        return response;
    }

    public static <T> Response<T> methodNotSupported() {
        Response<T> response = new Response<>();
        response.setStatus(HttpStatus.METHOD_NOT_ALLOWED);
        return response;
    }

    public void addErrorMsgToResponse(String errorMsg, Exception ex) {
        ResponseError error = new ResponseError()
                .setDetails(errorMsg)
                .setMessage(ex.getMessage())
                .setTimestamp(DateUtils.today());
        setErrors(error);
    }

    @Getter
    @Accessors(chain = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class PageMetadata {
        private int size;
        private long totalElements;
        private int totalPages;
        private int number;

        public PageMetadata(int size, long totalElements, int totalPages, int number) {
            this.size = size;
            this.totalElements = totalElements;
            this.totalPages = totalPages;
            this.number = number;
        }
    }

}