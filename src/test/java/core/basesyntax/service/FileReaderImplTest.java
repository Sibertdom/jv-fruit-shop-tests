package core.basesyntax.service;

import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class FileReaderImplTest {
    private final FileReader fileReader = new FileReaderImpl();

    @Test
    void readFromFile_validFile_ok() {
        String filePath = "src/test/resources/test_input.csv";
        List<String> actual = fileReader.read(filePath);

        Assertions.assertNotNull(actual);
        Assertions.assertEquals(3, actual.size());
        Assertions.assertEquals("type,fruit,quantity", actual.get(0));
        Assertions.assertEquals("b,banana,100", actual.get(1));
    }

    @Test
    void readFromFile_nonExistentFile_notOk() {
        String filePath = "src/test/resources/non_existent.csv";
        Assertions.assertThrows(RuntimeException.class, () -> {
            fileReader.read(filePath);
        });
    }
}
