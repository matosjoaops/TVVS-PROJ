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

The *insertedPadded* function, present in the Strings class, inserts a given string `stringToInsert` into another `s`, at position `insertAt`, padding it with spaces. We chose this function because is has a clear objective, it has three parameters and can throw an exception, making it an interesting function to test.

### Category-Partition algorithm

We can divide the function testing in the following partitions by changing the values of the three parameters:

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

The following code tests the partitions #1, #4 and #5. It makes use of the `@ParamerizedTest` and `@MethodSource` annotations to test the different partitions without having to write duplicate code. The test passes for all partitions, as the code runs as expected.

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

According to the documentation, if the parameter `insertAt` is negative or greater than the length of the base string, then an `IndexOutOfBoundsException` should be thrown. This is what happened for partitions 2 and 3. However, for partition 6 the exception wasn't thrown. It seems the implementation first checks if the `stringToInsert` is empty and only then checks if `insertAt`'s value is valid. But since this behaviour contradicts the documentation, it can be considered a bug and therefore, the test does not pass.

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



## *isBlank* function

```java
public static boolean isBlank(String s)
```


## *prependString* function

```java
public static void prependString(ArrayList<String> list, String prepend)
```


## *fileName* function

```java
public static String fileName(String path)
```


