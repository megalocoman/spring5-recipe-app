package guru.springframework.converters;

import guru.springframework.command.CategoryCommand;
import guru.springframework.domain.Category;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CategoryCommandToCategoryTest {

    public static final Long ID_VALUE = 1L;
    public static final String DESCRIPTION = "Description";
     CategoryCommandToCategory convert;

    @Before
    public void setUp() throws Exception {
        convert = new CategoryCommandToCategory();
    }

    public void testNullObject(){
        assertNull(convert.convert(null));
    }

    @Test
    public void testEmptyObject(){
        assertNotNull(convert.convert(new CategoryCommand()));
    }
    @Test
    public void convert() {

        CategoryCommand command = new CategoryCommand();
        command.setId(ID_VALUE);
        command.setDescription(DESCRIPTION);

        Category category = convert.convert(command);

        assertEquals(ID_VALUE,  category.getId());
        assertEquals(DESCRIPTION, category.getDescription());


    }
}