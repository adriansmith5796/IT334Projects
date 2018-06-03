package tests;

import org.junit.Before;
import org.junit.Test;
import structures.HashTable;
import interfaces.ICollection;

import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;

public class HashTableTest extends TestFacade
{
    private ICollection<String> table;
    private String[] testValues = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j",
                             "k", "l", "m", "n", "o", "p", "q", "r", "s", "t",
                             "u", "v", "w", "x", "y", "z"};

    public static void foo()
    {

    }

    @Before
    public void setup()
    {
        table = new HashTable<String>();
    }

    @Test
    public void testAdd()
    {
        //add a few elements
        for (String element : testValues)
        {
            table.add(element);
        }

        //testValues the size
        equals("size() is incorrect after using add()", testValues.length, table.size());

        //verify each element is in the table
        for (String element : testValues)
        {
            isTrue("element is missing after adding to hash table", table.contains(element));
        }
    }

    @Test
    public void testRemove()
    {
        //add a few elements
        for (String element : testValues)
        {
            table.add(element);
        }

        //remove items that should be in the table
        table.remove(testValues[0]);
        table.remove(testValues[10]);
        table.remove(testValues[25]);

        //verify that they are removed
        equals("size() is incorrect after using add()", testValues.length - 3, table.size());
        for (int i = 0; i < testValues.length; i++)
        {
            if (i != 0 && i != 10 && i != 25)
            {
                isTrue("element is missing after removing another element in the hash table",
                        table.contains(testValues[i]));
            }
        }

        try
        {
            table.remove("!");
            fail("A NoSuchElementException should be thrown when removing a missing element");
        }
        catch (NoSuchElementException ex)
        {
            //do nothing...
        }
    }

    @Test
    public void testContains()
    {
        //add a few elements
        for (String element : testValues)
        {
            table.add(element);
        }

        //look for present elements
        for (String element : testValues)
        {
            isTrue("table does not contain elements that have been added",
                    table.contains(element));
        }

        //look for missing elements
        isFalse("table should not report missing elements as present in the table",
                table.contains("!"));
    }

    @Test
    public void testSizeAndIsEmpty()
    {
        //empty table?
        isTrue("table should be empty at first", table.isEmpty());
        equals("table should have size zero with no elements", 0, table.size());

        //add elements and see if size changes
        for (int i = 0; i < testValues.length; i++)
        {
            table.add(testValues[i]);
            equals("size() incorrect after calling add", i + 1, table.size());
        }

        //remove elements and see if size changes
        for (int i = testValues.length - 1; i >= 0; i--)
        {
            table.remove(testValues[i]);
            equals("size() incorrect after calling add", i, table.size());
        }

        //clear elements and see if the table is reported as empty
        table.clear();
        isTrue("table should be empty after calling clear()", table.isEmpty());
        equals("table should have size zero after calling clear()", 0, table.size());
    }

    @Test
    public void testClear()
    {
        //add elements
        for (int i = 0; i < testValues.length; i++)
        {
            table.add(testValues[i]);
        }

        table.clear();

        //the table should be empty now
        isTrue("table should be empty after calling clear()", table.isEmpty());
        equals("table should have size zero after calling clear()", 0, table.size());

        //no elements should be present
        for (int i = 0; i < testValues.length; i++)
        {
            isFalse("table is reporting elements after clear() is called", table.contains(testValues[i]));
        }
    }

    @Test
    public void testIterator()
    {
        //do we have an iterator?
        isFalse("iterator is returned null with no elements in the table", table.iterator() == null);

        //add a few elements
        for (String element : testValues)
        {
            table.add(element);
        }

        //verify that each element is returned
        boolean[] found = new boolean[testValues.length];
        for (String element : table)
        {
            //find the element, mark it as found
            for (int i = 0; i < testValues.length; i++)
            {
                if (testValues[i].equals(element))
                {
                    if (found[i])
                    {
                        fail("duplicate element found with iterator: " + element);
                    }
                    else
                    {
                        found[i] = true;
                    }
                }
            }
        }

        //check that each was found
        for (int i = 0; i < found.length; i++)
        {
            if (!found[i])
            {
                fail("element not found with iterator: " + testValues[i]);
            }
        }

        //verify that you cannot concurrently alter the structure
        try
        {
            boolean first = true;
            for (String element : table)
            {
                if (first)
                {
                    table.add("!");
                    first = false;
                }
            }
            fail("Concurrent modification allowed with iterator");
        }
        catch(ConcurrentModificationException ex)
        {
            //do nothing...
        }

        try
        {
            boolean first = true;
            for (String element : table)
            {
                if (first)
                {
                    table.remove("e");
                    first = false;
                }
            }
            fail("Concurrent modification allowed with iterator");
        }
        catch(ConcurrentModificationException ex)
        {
            //do nothing...
        }

        try
        {
            boolean first = true;
            for (String element : table)
            {
                if (first)
                {
                    table.clear();
                    first = false;
                }
            }
            fail("Concurrent modification allowed with iterator");
        }
        catch(ConcurrentModificationException ex)
        {
            //do nothing...
        }
    }
}
