import java.util.Vector;

public class Tester {
    private abstract class Suite {
        public class Failure {
            public String message;
            public int lineNumber;
            public Failure(String message, int lineNumber) {
                this.message = message;
                this.lineNumber = lineNumber;
            }
        }
        Vector<Failure> failures = new Vector<Failure>();
        
        int numAssertions = 0;

        /**
         * Helper to promise that two objects are equal. If they are not, the failure is added to the
         * test failures and will be printed in the report.
         * 
         * @param a
         * @param b
         * @param message
         */
        protected void assertEquals(String a, String b, String message) {
            numAssertions++;
            if (!a.equals(b)) {
                Failure falure = new Failure(message, Thread.currentThread().getStackTrace()[2].getLineNumber());
                failures.add(falure);
            }
        }
        protected void assertEquals(int a, int b, String message) {
            numAssertions++;
            if (a != b) {
                Failure falure = new Failure(message, Thread.currentThread().getStackTrace()[2].getLineNumber());
                failures.add(falure);
            }
        }
        protected void assertEquals(Object a, Object b, String message) {
            numAssertions++;
            if (a != b) {
                Failure falure = new Failure(message, Thread.currentThread().getStackTrace()[2].getLineNumber());
                failures.add(falure);
            }
        }
        protected void assertNotEquals(Object a, Object b, String message) {
            numAssertions++;
            if (a == b) {
                Failure falure = new Failure(message, Thread.currentThread().getStackTrace()[2].getLineNumber());
                failures.add(falure);
            }
        }
        protected void assertNotNull(Object a, String message) {
            numAssertions++;
            if (a == null) {
                Failure falure = new Failure(message, Thread.currentThread().getStackTrace()[2].getLineNumber());
                failures.add(falure);
            }
        }
        protected void assertNull(Object a, String message) {
            numAssertions++;
            if (a != null) {
                Failure falure = new Failure(message, Thread.currentThread().getStackTrace()[2].getLineNumber());
                failures.add(falure);
            }
        }
        protected void assertTrue(boolean a, String message) {
            numAssertions++;
            if (!a) {
                Failure falure = new Failure(message, Thread.currentThread().getStackTrace()[2].getLineNumber());
                failures.add(falure);
            }
        }
        protected void assertFalse(boolean a, String message) {
            numAssertions++;
            if (a) {
                Failure falure = new Failure(message, Thread.currentThread().getStackTrace()[2].getLineNumber());
                failures.add(falure);
            }
        }

        /**
         * Helper to insert a set of unique random values into an array. Values may be repeated due to the limited
         * set of characters available.
         * 
         * @param chars
         * @param numChars
         */
        protected void insertRandCharsInto(String[] chars, int numChars) {
            for (int i = 0; i < numChars; i++) {
                chars[i] = String.valueOf((char) (Math.random() * 26 + 97));
            }
        }

        /**
         * Descendants of the class should implement this method to run their tests.
         *
         */
        public abstract void run();

        /**
         * Helper to run the test in a try catch block and print the results.
         * The idea is to prevent the entire thread from dying.
         * 
         */
        public void tryRun() {
            try {
                run();
            } catch (Exception e) {
                Failure falure = new Failure("Exception thrown: " + e.getMessage(), e.getStackTrace()[0].getLineNumber());
                failures.add(falure);
            }
        }

        /**
         * Print a summary of the test, indicating the failures and their related messages to stdout. Will
         * print a success message if no failures were encountered.
         *
         */
        protected void printReport() {
            if (failures.size() > 0) {
                System.out.println("Test Suite " + this.getClass().getSimpleName() + " failed with " + failures.size() + " failures:");
                for (Failure failure : failures) {
                    System.out.println("Line " + failure.lineNumber + ": " + failure.message);
                }
            } else {
                System.out.println("Suite " + this.getClass().getSimpleName() + " ran " + numAssertions + " assertions successfully.");
            }
        }
    }
    public class TestAppend extends Suite {
        /**
         * Inserts a selection of Strings into the list and verifies that they have been inserted and that the
         * size is correct.
         */
        public void run() {
            int CHARS = 20;
            String[] chars = new String[CHARS]; 
            insertRandCharsInto(chars, CHARS);
            LinkedList list = new LinkedList(chars[0]);
            for (int i = 1; i < CHARS; i++) {
                list.append(chars[i]);
                assertEquals(list.length(), i + 1, "Expected a length of " + (i + 1) + " but got " + list.length());
            }
            for (int i = 0; i < CHARS; i++) {
                LinkedList.Node toDelete = list.search(chars[i]);
                assertNotNull(toDelete, "Expected to find " + chars[i] + " but got null.");
                list.delete(toDelete);
                LinkedList.Node foundNode = list.search(chars[i]);
                assertEquals(list.length(), CHARS - (i + 1), "Expected length " + (CHARS - (i + 1)) + " but got " + list.length());
                assertNotEquals(toDelete, foundNode, "Expected not to not find " + chars[i] + " but found it.");
            }
        }
    }
    class TestContains extends Suite {
        /**
         * Inserts a selection of random elements into the linked list and sees if they're there.
         * See if it correctly identifies elements that are not in the list.
         * 
         */
        public void run() {
            int CHARS = 20;
            String[] chars = new String[CHARS]; 
            insertRandCharsInto(chars, CHARS);
            LinkedList list = new LinkedList(chars[0]);
            // Adding elements and retrieving them.
            for (int i = 1; i < CHARS; i++) {
                LinkedList.Node appendedNode = list.append(chars[i]);
                assertNotNull(appendedNode, "Expected to find " + chars[i] + " but got null.");
                assertTrue(list.contains(appendedNode), "Expected list to contain " + chars[i] + " but it didn't.");
            }
            // Elements not in the list
            LinkedList.Node notInList = list.new Node("not in list");
            assertFalse(list.contains(notInList), "Expected list to not contain " + notInList + " but it did.");
        }
    }
    public class TestEdge extends Suite {
        /**
         * Tests the edge cases of the LinkedList such as adding at the beginning and end of the list, and deleting at
         * the beginning and end of the list.
         * 
         */
        public void run() {
            int CHARS = 4;
            String[] chars = new String[CHARS];
            insertRandCharsInto(chars, CHARS);

            LinkedList list = new LinkedList(chars[0]); // going to be the middle node.

            list.prepend(chars[1]); // now the first node.
            list.append(chars[2]); // now the last node

            LinkedList.Node prependedNode = list.search(chars[1]);
            LinkedList.Node appendedNode = list.search(chars[2]);
            LinkedList.Node middleNode = list.search(chars[0]);

            list.delete(prependedNode);
            list.delete(appendedNode);

            assertNull(middleNode.getNext(), "Expected middle node to have no next node.");
            assertNull(middleNode.getPrev(), "Expected middle node to have no previous node.");

            // Ensure that the list still works.

            list.append(chars[3]);
            LinkedList.Node newNode = list.search(chars[3]);
            assertNotNull(newNode, "Expected linked list to remain usable after deletes were made.");
        }
    }
    public class TestInsert extends Suite {
        /**
         * Tests inserting elements within the list.
         *
         */
        public void run() {
            int TOTAL_CHARS = 20;
            if (TOTAL_CHARS % 2 != 0) {
                throw new RuntimeException("CHARS must be even.");
            }
            
            String[] chars = new String[TOTAL_CHARS];
            insertRandCharsInto(chars, TOTAL_CHARS);
            LinkedList list = new LinkedList(chars[0]);
            // Add a few elements to the list.
            Vector<LinkedList.Node> nodes = new Vector<LinkedList.Node>();
            
            for (int i = 1; i < TOTAL_CHARS / 2; i++) {
                nodes.add(list.append(chars[i]));
            }
            // Insert a few other elements into the list and find them.
            int index = (TOTAL_CHARS / 2); // One element is not in this vector as it is not possible to get a class from the constructor.
            for (LinkedList.Node prevNode : nodes) {
                LinkedList.Node nextNode = prevNode.getNext(); 
                LinkedList.Node insertedNode = list.insert(chars[index], prevNode);
                assertEquals(insertedNode.getData(), chars[index], "Expected to find " + chars[index] + " but got " + insertedNode.getData());
                assertEquals(insertedNode, prevNode.getNext(), "Expected to find " + insertedNode + " after " + prevNode + " but got " + insertedNode.getNext());
                assertEquals(insertedNode.getPrev(), prevNode, "Expected to find " + prevNode + " before " + insertedNode + " but got " + prevNode);
                assertEquals(insertedNode.getNext(), nextNode, "Expected to find " + nextNode + " after " + insertedNode + " but got " + nextNode);
                assertEquals(list.length(), index + 1, "Expected length " + (index + 1) + " but got " + list.length());
                index++;
            }
        }
    }
    public static void main(String[] args) {
        Tester tester = new Tester();
        Tester.TestContains testContains = tester.new TestContains();
        testContains.run();
        testContains.printReport();
        Tester.TestAppend testAppend = tester.new TestAppend();
        testAppend.run();
        testAppend.printReport();
        Tester.TestEdge testEdge = tester.new TestEdge();
        testEdge.run();
        testEdge.printReport();
        Tester.TestInsert testInsert = tester.new TestInsert();
        testInsert.tryRun();
        testInsert.printReport();
    }
}
