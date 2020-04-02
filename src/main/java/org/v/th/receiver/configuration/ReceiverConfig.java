package org.v.th.receiver.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.io.File;

@Data
@Configuration
@ConfigurationProperties(prefix = "receiver")
public class ReceiverConfig {

    private File storagePath;

    private boolean verifyChecksum = true;

}
