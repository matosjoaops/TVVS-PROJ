# Assignment #2

## Group Information

- Degree: M.EIC
- Group id: 06
- Project id: 03
- Students:
    - João Matos, up201703884
    - Tiago Gomes, up201806658

## *insertPadded* function

```java
public static String insertPadded(String s, int insertAt, String stringToInsert)
```

The *insertedPadded* function, present in the `Strings` class, inserts a given string `stringToInsert` into another `s`, at position `insertAt`, padding it with spaces. We chose this function because it has a clear objective, has three parameters, and can throw an exception, making it an interesting function to test.

### Category-Partition algorithm

We can divide the function testing into the following partitions by changing the values of the three parameters:

#### Partition #1:
- `s` is a string of length > 0
- `stringToInsert` is a string of length > 0
- 0 <= `insertAt` <= length(`s`), where `insertAt` is an integer

#### Partition #2:
- `s` is a string of length >= 0
- `stringToInsert` is a string of length >= 0
- `insertAt` < 0, where `insertAt` is an integer

#### Partition #3:
- `s` is a string of length >= 0
- `stringToInsert` is a string of length >= 0
- `insertAt` > length(`s`), where `insertAt` is an integer

#### Partition #4:
- `s` is a string of length > 0
- `stringToInsert` is en empty string
- 0 <= `insertAt` <= length(`s`), where `insertAt` is an integer

#### Partition #5:
- `s` is an empty string
- `stringToInsert` is a string of length > 0
- 0 <= `insertAt` <= length(`s`), where `insertAt` is an integer

#### Partition #6:
- `s` is a string of length >= 0
- `stringToInsert` is a string of length = 0
- `insertAt` < 0, where `insertAt` is an integer

### Unit tests generated for each category

#### Testings partitions #1, #4 and #5

The following code tests partitions #1, #4, and #5. It uses the `@ParamerizedTest` and `@MethodSource` annotations to test the different partitions without writing duplicate code. The test passes for all partitions, as the code runs as expected.

```java
@ParameterizedTest
@MethodSource("stringIntStringProvider")
public void insertPadded(String baseString, int position, String stringToInsert, String expectedResult) {

    String result = Strings.insertPadded(baseString, position, stringToInsert);

    assertEquals(result, expectedResult);
}

static Stream<Arguments> stringIntStringProvider() {
    return Stream.of(
        arguments("apple", 1, "pear", "a pear pple"), // partition #1
        arguments("test", 4, "", "test"), // partition #4
        arguments("", 0, "a", "a ") // partition #5
    );
}
```


#### Testing partitions #2, #3 and #6

According to the documentation, if the parameter `insertAt` is negative or greater than the length of the base string, then an `IndexOutOfBoundsException` should be thrown. This is what happened for partitions 2 and 3. However, for partition 6, the exception was not thrown. It seems the implementation first checks if the `stringToInsert` is empty and only then checks if `insertAt`'s value is valid. However, since this behavior contradicts the documentation, it can be considered a bug; therefore, the test does not pass. These tests use `assertThrows` to check if the exception is thrown.

```java
@ParameterizedTest()
@MethodSource("stringInvalidPositionStringProvider")
public void insertPaddedNegativePosition(String s, int insertAt, String stringToInsert) {
    assertThrows(IndexOutOfBoundsException.class, () -> Strings.insertPadded(s, insertAt, stringToInsert));
}

static Stream<Arguments> stringInvalidPositionStringProvider() {
    return Stream.of(
        arguments("a", -1, "b"), // partition #2
        arguments("a", 5, "b"), // partition #3
        arguments("test", -1, "") // partition #6
    );
}
```

## *getRelativeDate* function

```java
public static String getRelativeDate(Date date)
```

The *getRelativeDate* function, present in the `RelativeDate` class, receives a `Date` object and returns a string representing the relative date by comparing the date being passed to the time it is right now.

### Category-Partition algorithm
We can divide the function testing into the following partitions by changing the values of the parameter:

#### Partition #1:
- `date` is a date/time before the current date/time

#### Partition #2:
- `date` is a date/time equal to the current date/time

#### Partition #3:
- `date` is a date/time after the current date/time


### Unit tests generated for each category

The following code tests partitions #1, #2, and #3. As the previously tested functions, it uses the `@ParamerizedTest` and `@MethodSource` annotations to test the different partitions without having to write duplicate code. All the tests fail because, despite its name, the function just seems to return a string representation of the date provided in the `date` parameter instead of returning a relative date.

```java
@ParameterizedTest
@MethodSource("dateProvider")
public void getRelativeDate(Date date, String expectedResult) {

    String result = RelativeDate.getRelativeDate(date);

    assertEquals(result, expectedResult);
}

static Stream<Arguments> dateProvider() throws ParseException {
    Calendar calendar = new GregorianCalendar();
    Calendar calendar2 = new GregorianCalendar();

    Date now = new Date();
    Date before = new SimpleDateFormat("yyyy/MM/dd").parse("2000/10/1");
    Date after = new SimpleDateFormat("yyyy/MM/dd").parse("3000/10/1");

    calendar.setTime(now);
    calendar2.setTime(now);
    String nowString = String.format("%04d/%02d/%02d",
        calendar2.get(Calendar.YEAR) - calendar.get(Calendar.YEAR),
        calendar2.get(Calendar.MONTH) - calendar.get(Calendar.MONTH), // months start at 0
        calendar2.get(Calendar.DAY_OF_MONTH) - calendar.get(Calendar.DAY_OF_MONTH)
    );


    calendar2.setTime(before);
    String beforeString = String.format("%04d/%02d/%02d",
        calendar2.get(Calendar.YEAR) - calendar.get(Calendar.YEAR),
        calendar2.get(Calendar.MONTH) - calendar.get(Calendar.MONTH), // months start at 0
        calendar2.get(Calendar.DAY_OF_MONTH) - calendar.get(Calendar.DAY_OF_MONTH)
    );

    calendar2.setTime(after);
    String afterString = String.format("%04d/%02d/%02d",
        calendar2.get(Calendar.YEAR) - calendar.get(Calendar.YEAR),
        calendar2.get(Calendar.MONTH) - calendar.get(Calendar.MONTH), // months start at 0
        calendar2.get(Calendar.DAY_OF_MONTH) - calendar.get(Calendar.DAY_OF_MONTH)
    );


    return Stream.of(
            arguments(before, beforeString), // partition #1
            arguments(now, nowString), // partition #2
            arguments(after, afterString) // partition #3
    );
}
```


## *isBlank* function

```java
public static boolean isBlank(String s)
```

The *isBlank* function, present in the `Strings` class, checks the passed-in string to see if it is null, empty, or only composed of whitespaces.

### Category-Partition algorithm
We can divide the function testing into the following partitions by changing the values of the parameter:

#### Partition #1:
- `s` is null

#### Partition #2:
- `s` is an empty string

#### Partition #3:
- `s` is only composed of spaces

#### Partition #4:
- `s` a non-empty string

### Unit tests generated for each category

The following code tests partitions #1, #2, #3, and #4. As the previously tested functions, it uses the `@ParamerizedTest` and `@MethodSource` annotations to test the different partitions without having to write duplicate code. The test passes for all partitions, as the code runs as expected.

```java
@ParameterizedTest
@MethodSource("stringProvider")
public void isBlank(String s, boolean expectedResult) {

    boolean result = Strings.isBlank(s);
    
    assertEquals(result, expectedResult);
}

static Stream<Arguments> stringProvider() {
    return Stream.of(
        arguments(null, true), // partition #1
        arguments("", true), // partition #2
        arguments("   ", true), // partition #3
        arguments("123", false) // partition #4
    );
}
```


## *prependString* function

```java
public static void prependString(ArrayList<String> list, String prepend)
```

The *prependString* function, which can be found in the Util class, concatenates the `prepend` string at the beginning of each string contained in the `list`. This function was chosen for its simplicity.

### Category-Partition algorithm

Given the described parameters, the following partitions were created:

#### Partition #1:
- `list` is an ArrayList with length > 0
- `prepend` is a string with length > 0

#### Partition #2:
- `list` is an ArrayList with length = 0
- `prepend` is a string with length > 0

#### Partition #3:
- `list` is an ArrayList with length > 0
- `prepend` is a string with length = 0

#### Partition #4:
- `list` is an ArrayList with length = 0
- `prepend` is a string with length = 0

### Unit tests generated for each category

The following code was used to test all the partitions. Several lists with the same content had to be used for the tests since their contents could not be reset between tests. Each test receives the parameters for the function along with the expected list and uses `assertEquals` to compare the content of the lists. All tests passed.

```java
@ParameterizedTest
@MethodSource("prependProvider")
public void prepend(ArrayList<String> list, String prepend, ArrayList<String> expected) {
    Util.prependString(list, prepend);
    assertEquals(list, expected);
}

static Stream<Arguments> prependProvider() {
    ArrayList<String> list1 = new ArrayList<>(
            Arrays.asList("a", "b")
    );
    ArrayList<String> expectedList1 = new ArrayList<>(
            Arrays.asList("ca", "cb")
    );

    ArrayList<String> list2 = new ArrayList<>();
    ArrayList<String> expectedList2 = new ArrayList<>();

    ArrayList<String> list3 = new ArrayList<>(
            Arrays.asList("a", "b")
    );
    ArrayList<String> expectedList3 = new ArrayList<>(
            Arrays.asList("a", "b")
    );

    ArrayList<String> list4 = new ArrayList<>();
    ArrayList<String> expectedList4 = new ArrayList<>();


    return Stream.of(
            arguments(list1, "c", expectedList1),
            arguments(list2, "c", expectedList2),
            arguments(list3, "", expectedList3),
            arguments(list4, "", expectedList4)
    );
}
```

## *fileName* function

```java
public static String fileName(String path)
```

This function returns the corresponding filename for a given `path`. It was chosen for the unique properties that valid paths have.

### Category-Partition algorithm

The following partitions were created for this function (all paths use the Unix format):

#### Partition #1:
- `path` is a valid absolute path with no spaces and multiple occurrences of the `/` character

#### Partition #2:
- `path` is a valid relative path with no spaces and a single occurrence of the `/` character

#### Partition #3:
- `path` is a valid relative path with no spaces and no occurrences of the `/` character

#### Partition #4:
- `path` is a valid absolute path with a space between quotation marks

#### Partition #5:
- `path` is a valid absolute path with a space preceded by the escape character (`\`)

#### Partition #6:
- `path` is an invalid absolute path with a space

#### Partition #7:
- `path` is an empty string

### Unit tests generated for each category

The following code was used to test this function's partitions. A parameterized test was used to provide the different paths for each partition. We also expected the function to throw an exception when the invalid path was used, but that did not happen, causing that test to fail.


```java
@ParameterizedTest
@MethodSource("pathProvider")
public void fileName(String path, String expected) {
    String result = Path.fileName(path);
    assertEquals(result, expected);
}

@Test
public void invalidFileName() {
    assertThrows(Exception.class, () -> Path.fileName("/home/user/space dir/file6.txt"));
}

static Stream<Arguments> pathProvider() {
    return Stream.of(
            arguments("/home/user/file1.txt", "file1.txt"),
            arguments("dir/file2.txt", "file2.txt"),
            arguments("file3.txt", "file3.txt"),
            arguments("/home/user/\"space dir\"/file4.txt", "file4.txt"),
            arguments("/home/user/space\\ dir/file5.txt", "file5.txt"),
            arguments("", "")
    );
}
```
