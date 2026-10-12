package core.basesyntax.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class FileWriterImplTest {
    private static FileWriter fileWriter;

    @BeforeAll
    static void beforeAll() {
        fileWriter = new FileWriterImpl();
    }

    @Test
    void write_validData_ok() {
        String filePath = "src/test/resources/test_output.csv";
        String report = "fruit,quantity\nbanana,150";

        fileWriter.write(report, filePath);

        File file = new File(filePath);
        assertTrue(file.exists());

        try {
            String content = Files.readString(Path.of(filePath));
            assertEquals(report, content);
        } catch (IOException e) {
            fail("Failed to read the written file: " + e.getMessage());
        } finally {
            file.delete();
        }
    }

    @Test
    void write_nullPath_notOk() {
        String report = "fruit,quantity\nbanana,150";
        assertThrows(RuntimeException.class, () -> {
            fileWriter.write(report, null);
        });
    }
}
