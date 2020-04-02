package org.v.th.receiver.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum  ResultEnums {

    SUCCESS(0, "success"),
    CHECKSUM_ERROR(100, "checksum error");

    @Getter
    private int code;

    @Getter
    private String message;

}
