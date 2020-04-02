package org.v.th.receiver.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.v.th.receiver.configuration.ReceiverConfig;
import org.v.th.receiver.dto.result.InvokeResult;
import org.v.th.receiver.enums.ResultEnums;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@RestController
public class FileController {

    @Autowired
    private ReceiverConfig receiverConfig;

    @PostMapping("file/store")
    public InvokeResult<?> receiveFile(@RequestParam("file") MultipartFile multipartFile, String name, long checksum) throws IOException {
        String uuid = UUID.randomUUID().toString();
        if (log.isInfoEnabled()) {
            log.info("receive {}, and rename to {}", name, uuid);
        }
        File file = new File(receiverConfig.getStoragePath(), uuid);
        FileUtils.writeByteArrayToFile(file, multipartFile.getBytes(), false);

        if (receiverConfig.isVerifyChecksum() && FileUtils.checksumCRC32(file) != checksum) {
            if (log.isWarnEnabled()) {
                log.warn("{} checksum error", name);
            }
            if (!FileUtils.deleteQuietly(file)) {
                log.warn("{} delete failed", file);
            }
            return new InvokeResult<>(ResultEnums.CHECKSUM_ERROR);
        }

        return InvokeResult.SUCCESS;
    }

}
