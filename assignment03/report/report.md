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

#### Between Partition #2 and Partition #3:
- On-point: current date
- Off-point: current date plus 1 day

#### Between Partition #4 and Partition #1:
- On-point: null
- Off-point: date before the current date

#### Between Partition #4 and Partition #2:
- On-point: null
- Off-point: current date

#### Between Partition #4 and Partition #3:
- On-point: null
- Off-point: date after the current date


### Unit tests generated for each category

The following code tests partitions #1, #2, #3, and #4, as well as its boundaries. It uses the `@ParamerizedTest` and `@MethodSource` annotations to test the different partitions without having to write duplicate code. All the tests fail because, despite its name, the function just seems to return a string representation of the date provided in the `date` parameter instead of returning a relative date.

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
