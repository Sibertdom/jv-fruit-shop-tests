package core.basesyntax.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import core.basesyntax.db.Storage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class ReportGeneratorImplTest {
    private static ReportGenerator reportGenerator;

    @BeforeAll
    static void beforeAll() {
        reportGenerator = new ReportGeneratorImpl();
    }

    @AfterEach
    void tearDown() {
        Storage.getStorage().clear();
    }

    @Test
    void getReport_validStorage_ok() {
        Storage.getStorage().put("banana", 150);
        Storage.getStorage().put("apple", 90);

        String lineSeparator = System.lineSeparator();
        String expected = "fruit,quantity" + lineSeparator
                + "banana,150" + lineSeparator
                + "apple,90";
        String actual = reportGenerator.getReport();

        assertEquals(expected, actual);
    }

    @Test
    void getReport_emptyStorage_ok() {
        String expected = "fruit,quantity";
        String actual = reportGenerator.getReport();
        assertEquals(expected, actual);
    }
}
