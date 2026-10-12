package core.basesyntax.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class FileReaderImplTest {
    private static FileReader fileReader;

    @BeforeAll
    static void beforeAll() {
        fileReader = new FileReaderImpl();
    }

    @Test
    void readFromFile_validFile_ok() {
        String filePath = "src/test/resources/test_input.csv";
        List<String> actual = fileReader.read(filePath);

        assertNotNull(actual);
        assertEquals(3, actual.size());
        assertEquals("type,fruit,quantity", actual.get(0));
        assertEquals("b,banana,100", actual.get(1));
    }

    @Test
    void readFromFile_nonExistentFile_notOk() {
        String filePath = "src/test/resources/non_existent.csv";
        assertThrows(RuntimeException.class, () -> {
            fileReader.read(filePath);
        });
    }
}
