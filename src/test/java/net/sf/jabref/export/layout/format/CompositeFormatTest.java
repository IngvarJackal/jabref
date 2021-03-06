package net.sf.jabref.export.layout.format;

import net.sf.jabref.export.layout.LayoutFormatter;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CompositeFormatTest {

    @Test
    public void testComposite() {

        {
            LayoutFormatter f = new CompositeFormat();
            assertEquals("No Change", f.format("No Change"));
        }
        {
            LayoutFormatter f = new CompositeFormat(new LayoutFormatter[]{new LayoutFormatter() {

                public String format(String fieldText) {
                    return fieldText + fieldText;
                }

            }, new LayoutFormatter() {

                public String format(String fieldText) {
                    return "A" + fieldText;
                }

            }, new LayoutFormatter() {

                public String format(String fieldText) {
                    return "B" + fieldText;
                }

            }});

            assertEquals("BAff", f.format("f"));
        }

        {
            LayoutFormatter f = new CompositeFormat(new AuthorOrgSci(),
                    new NoSpaceBetweenAbbreviations());
            LayoutFormatter first = new AuthorOrgSci();
            LayoutFormatter second = new NoSpaceBetweenAbbreviations();

            assertEquals(second.format(first.format("John Flynn and Sabine Gartska")), f.format("John Flynn and Sabine Gartska"));
            assertEquals(second.format(first.format("Sa Makridakis and Sa Ca Wheelwright and Va Ea McGee")), f.format("Sa Makridakis and Sa Ca Wheelwright and Va Ea McGee"));
        }
    }

}
