package uk.gov.hmcts.payment.processors;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class VolumeReader {
    
    private String mountPath;

    public VolumeReader(String mountPath) {
        this.mountPath = mountPath;
    }

    public String getFromVolume(String fileName) {
        try {
            return new String(Files.readAllBytes(Paths.get(mountPath, fileName)), StandardCharsets.UTF_8).trim();
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
