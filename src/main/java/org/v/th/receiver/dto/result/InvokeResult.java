package org.v.th.receiver.dto.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.v.th.receiver.enums.ResultEnums;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class InvokeResult<T> {

    private int code;

    private String message;

    private T data;

    public static final InvokeResult<?> SUCCESS = new InvokeResult<>(ResultEnums.SUCCESS);

    public InvokeResult(ResultEnums enums) {
        this.code = enums.getCode();
        this.message = enums.getMessage();
    }

}
