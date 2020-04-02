package org.v.th.receiver.dto.cmd;

import lombok.Builder;
import lombok.Data;

import java.io.File;

@Data
@Builder
public class ReceiveFileCmd {

    private File file;

    private String name;

    private long checksum;

}
