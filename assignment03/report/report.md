# Assignment #3

## Group Information

- Degree: M.EIC
- Group id: 06
- Project id: 03
- Students:
    - João Matos, up201703884
    - Tiago Gomes, up201806658

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

#### Partition #4:
- `date` is null


### Boundary Value Analysis

#### Between Partition #1 and Partition #2:
- On-point: current date minus 1 day
- Off-point: current date
- In-points: dates before the current date
- Out-points: dates equal to after the current date; null

#### Between Partition #2 and Partition #3:
- On-point: current date
- Off-point: current date plus 1 day
- In-points: dates after the current date
- Out-points: dates before or after the current date; null

#### Between Partition #4 and Partition #1:
- On-point: null
- Off-point: date before the current date
- In-points: null
- Out-points: dates before the current date

#### Between Partition #4 and Partition #2:
- On-point: null
- Off-point: current date
- In-points: null
- Out-points: current date

#### Between Partition #4 and Partition #3:
- On-point: null
- Off-point: date after the current date
- In-points: null
- Out-points: dates after the current date


### Unit tests generated for each category

The following code tests partitions #1, #2, #3, and #4, as well as its boundaries. It uses the `@ParamerizedTest` and `@MethodSource` annotations to test the different partitions without having to write duplicate code. All the tests except for the null date case fail because, despite its name, the function just seems to return a string representation of the date provided in the `date` parameter instead of returning a relative date. The `getRelativeDateNull` passes, as it throws a `NullPointerException`, as expected.

```java
@ParameterizedTest
    @MethodSource("dateProvider")
    public void getRelativeDate(Date date, String expectedResult) {

        String result = RelativeDate.getRelativeDate(date);

        assertEquals(result, expectedResult);
    }

    @Test(expected = NullPointerException.class)
    public void getRelativeDateNull() {

        RelativeDate.getRelativeDate((Date) null); // Partition #4;
                                                   // Between Partition #4 and Partition #1 On-point;
                                                   // Between Partition #4 and Partition #2 On-point;
                                                   // Between Partition #4 and Partition #3 On-point;
    }

    static Stream<Arguments> dateProvider() throws ParseException {
        Date now = new Date();
        Date before = new SimpleDateFormat("yyyy/MM/dd").parse("2000/10/1");
        Date after = new SimpleDateFormat("yyyy/MM/dd").parse("3000/10/1");

        Instant nowInstant = Instant.now();
        Instant before1DayInstant = nowInstant.minus(Duration.ofDays(1));
        Date before1Day = Date.from(before1DayInstant);

        Instant after1DayInstant = nowInstant.plus(Duration.ofDays(1));
        Date after1Day = Date.from(after1DayInstant);

        String nowString = getRelativeDateUtil(now, now);
        String beforeString = getRelativeDateUtil(now, before);
        String afterString = getRelativeDateUtil(now, after);
        String before1DayString = getRelativeDateUtil(now, before1Day);
        String after1DayString = getRelativeDateUtil(now, after1Day);

        return Stream.of(
                arguments(before, beforeString), // Partition #1; Between Partition #4 and Partition #1 Off-point
                arguments(now, nowString), // Partition #2; Between Partition #1 and Partition #2 Off-point;
                                           // Between Partition #2 and Partition #3 On-point;
                                           // Between Partition #4 and Partition #2 Off-point
                arguments(after, afterString), // Partition #3; Between Partition #4 and Partition #3 Off-point
                arguments(before1Day, before1DayString), // Between Partition #1 and Partition #2
                arguments(after1Day, after1DayString) // Between Partition #2 and Partition #3
        );
    }

    static String getRelativeDateUtil(Date now, Date date) {
        Calendar calendar = new GregorianCalendar();
        Calendar calendar2 = new GregorianCalendar();

        calendar.setTime(now);
        calendar2.setTime(date);

        return String.format("%04d/%02d/%02d",
            calendar2.get(Calendar.YEAR) - calendar.get(Calendar.YEAR),
            calendar2.get(Calendar.MONTH) - calendar.get(Calendar.MONTH), // months start at 0
            calendar2.get(Calendar.DAY_OF_MONTH) - calendar.get(Calendar.DAY_OF_MONTH)
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
- `s` a non-empty string that is not composed only by spaces


### Boundary Value Analysis

#### Between Partition #1 and Partition #2:
- On-point: null
- Off-point: empty string

#### Between Partition #2 and Partition #3:
- On-point: empty string
- Off-point: string with one space character

#### Between Partition #2 and Partition #4:
- On-point: empty string
- Off-point: string one non-space character


### Unit tests generated for each category

The following code tests partitions #1, #2, #3, and #4, as well as its boundaries. As the previously tested functions, it uses the `@ParamerizedTest` and `@MethodSource` annotations to test the different partitions without having to write duplicate code. The test passes for all partitions and boundaries, as the code runs as expected.

```java
@ParameterizedTest
@MethodSource("stringProvider")
public void isBlank(String s, boolean expectedResult) {

    boolean result = Strings.isBlank(s);
    
    assertEquals(result, expectedResult);
}

static Stream<Arguments> stringProvider() {
    return Stream.of(
        arguments(null, true), // Partition #1; Between Partition #1 and Partition #2 On-point
        arguments("", true), // Partition #2; Between Partition #1 and Partition #2 Off-point; 
                             // Between Partition #2 and Partition #3 On-point;
                             // Between Partition #2 and Partition #4 On-point
        arguments("   ", true), // Partition #3
        arguments("123", false), // Partition #4
        arguments(" ", true), // Between Partition #2 and Partition #3 Off-point
        arguments("a", false) // Between Partition #2 and Partition #4 Off-point
    );
}
```


## *prependString* function

```java
public static void prependString(ArrayList<String> list, String prepend)
```

The *prependString* function, which can be found in the Util class, concatenates the `prepend` string at the beginning of each string contained in the `list`. This function was chosen for its simplicity.

### Category-Partition algorithm

Given the described parameters, the following partitions were created for each parameter:

#### Partitions for `list`
1. `list` is `null` 
2. `list` is an empty list
3. `list` is a list with one or more elements

#### Partitions for `prepend`
1. `prepend` is `null`
2. `prepend` is an empty string
3. `prepend` is a string with one or more characters

### Boundary Value Analysis

#### Boundaries for `list`

##### Between Partition #1 and Partition #2:
- On-point: `null`
- Off-point: empty list
- In-points: all non-null lists
- Out-points: `null`

##### Between Partition #2 and Partition #3:
- On-point: empty list
- Off-point: any list with one element
- In-points: all lists with one or more elements
- Out-points: empty list and `null`

#### Boundaries for `prepend`

##### Between Partition #1 and Partition #2:
- On-point: `null`
- Off-point: empty string
- In-points: all non-null strings
- Out-points: `null`

##### Between Partition #2 and Partition #3:
- On-point: empty string
- Off-point: any string with one character
- In-points: all strings with one or more characters
- Out-points: empty string and `null` 


### Unit tests generated for each category

The following code was used to test all the partitions and their boundaries. Several lists with the same content had to be used for the tests since their contents could not be reset between tests. Each test receives the parameters for the function along with the expected list and uses `assertEquals` to compare the content of the lists. For tests where we expected an exception to be thrown we used `assertThrows`. We expected the function to throw an exception when `prepend` was `null`. However, this did not happen, leading that test to fail. All other tests passed.

```java
@ParameterizedTest
@MethodSource("prependProvider")
public void prepend(ArrayList<String> list, String prepend, ArrayList<String> expected) {
    Util.prependString(list, prepend);
    assertEquals(list, expected);
}

@Test
public void nullList() {
    assertThrows(Exception.class, () -> Util.prependString(null, "a"));//On-point between partitions 1 and 2 for list parameter
}

@Test
public void nullString() {
    assertThrows(Exception.class, () -> Util.prependString(new ArrayList<>(Arrays.asList("b")), null));//On-point between partitions 1 and 2 for prepend parameter
}

static Stream<Arguments> prependProvider() {
    String normalString = "a";
    ArrayList<String> normalList1 = new ArrayList<>(Arrays.asList("b"));
    ArrayList<String> normalList2 = new ArrayList<>(Arrays.asList("b"));

    ArrayList<String> testList1 = new ArrayList<>();
    ArrayList<String> testList2 = new ArrayList<>(Arrays.asList("b"));

    String testString1 = "";
    String testString2 = "a";

    ArrayList<String> expectedList1 = new ArrayList<>();
    ArrayList<String> expectedList2 = new ArrayList<>(Arrays.asList("ab"));
    ArrayList<String> expectedList3 = new ArrayList<>(Arrays.asList("b"));
    ArrayList<String> expectedList4 = new ArrayList<>(Arrays.asList("ab"));

    return Stream.of(
            arguments(testList1, normalString, expectedList1), //On-point between partitions 2 and 3 for list parameter
                                                                //Off-point between partitions 1 and 2 for list parameter
            arguments(testList2, normalString, expectedList2), //Off-point between partitions 2 and 3 for list parameter
            arguments(normalList1, testString1, expectedList3),//On-point between partitions 2 and 3 for prepend parameter
                                                                //Off-point between partitions 1 and 2 for prepend parameter
            arguments(normalList2, testString2, expectedList4) //Off-point between partitions 2 and 3 for prepend parameter
    );
}
```
