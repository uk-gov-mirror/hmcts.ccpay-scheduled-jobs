package uk.gov.hmcts.payment.processors;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.*;

class VolumeReaderTest {

    @Test
    void getFromVolume() throws IOException {
        Path tempDirWithPrefix = Files.createTempDirectory("volumeReaderTest");
        Files.write(tempDirWithPrefix.resolve("dummy-file"), "my-dummy-file".getBytes(StandardCharsets.UTF_8));
        VolumeReader volumeReader = new VolumeReader(tempDirWithPrefix.toString());

        String fromVolume = volumeReader.getFromVolume("dummy-file");
        
        assertThat(fromVolume, is("my-dummy-file"));
    }
}
