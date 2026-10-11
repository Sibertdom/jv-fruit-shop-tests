package core.basesyntax.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class FileWriterImplTest {
    private final FileWriter fileWriter = new FileWriterImpl();

    @Test
    void write_validData_ok() {
        String filePath = "src/test/resources/test_output.csv";
        String report = "fruit,quantity\nbanana,150";

        fileWriter.write(report, filePath);

        File file = new File(filePath);
        Assertions.assertTrue(file.exists());

        try {
            String content = Files.readString(Path.of(filePath));
            Assertions.assertEquals(report, content);
        } catch (IOException e) {
            Assertions.fail("Failed to read the written file: " + e.getMessage());
        } finally {
            file.delete(); // Очищуємо за собою файл після тесту
        }
    }
}
