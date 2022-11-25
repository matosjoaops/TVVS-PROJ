# Assignment #5

## Group Information

- Degree: M.EIC
- Group id: 06
- Project id: 03
- Students:
  - João Matos, up201703884
  - Tiago Gomes, up201806658

## `fileName` function

```java
public static String fileName(String path) {
  if (!Strings.isBlank(path)) {
    // adapted from DropboxAPI.java v1.5.4
    if (path.endsWith("/")) {
      path = path.substring(0, path.length() - 1);
    }
    int ind = path.lastIndexOf('/');
    return path.substring(ind + 1, path.length());
  }
  return "";
}
```

This function returns a file's name that is contained in the path that is provided as a parameter. It was chosen because it has multiple conditions and is simple.

### Dataflow Testing

#### CFG

![](./images/fileName_cfg.png)

#### Paths table

##### path

| pair id | def | use   | path       |
| ------- | --- | ----- | ---------- |
| 1       | 1   | (2,T) | <1,2,3>    |
| 2       | 1   | (2,F) | <1,2,6>    |
| 3       | 1   | (3,T) | <1,2,3,4>  |
| 4       | 1   | (3,F) | <1,2,3,5>  |
| 5       | 1   | 4     | <1,2,3,4>  |
| 6       | 1   | 5     | <1,2,3,5>  |
| 7       | 1   | 7     | <1,2,3,5,7 |
| 8       | 4   | 4     | <4,4>      |
| 9       | 4   | 5     | <4,5>      |
| 10      | 4   | 7     | <4,5,7>    |

##### ind

| pair id | def | use | path  |
| ------- | --- | --- | ----- |
| 1       | 5   | 7   | <5,7> |

## `getRelativeDate` function

```java
/**
 * This method returns a String representing the relative date by comparing
 * the Date being passed in to the date / time that it is right now.
 * 
 * @param date
 * @return String representing the relative date
 */
public static String getRelativeDate(Date date) {
  Calendar converted = GregorianCalendar.getInstance();
  converted.setTime(date);
  return getRelativeDate(converted);
}
```

This function returns a String representation of the provided date relative to the current time (or at least that's what it claims to do). This function was chosen because it does not have any conditions and receives a `Date` as a parameter.

### Dataflow Testing

#### CFG

![](./images/getRelativeDate_cfg.png)

#### Paths table

##### date

| pair id | def | use | path    |
| ------- | --- | --- | ------- |
| 1       | 1   | 3   | <1,2,3> |


##### converted

| pair id | def | use | path    |
| ------- | --- | --- | ------- |
| 1       | 2   | 3   | <2,3>   |
| 2       | 2   | 4   | <2,3,4> |

## `setSelectedItem` function

```java
@Override
public void setSelectedItem(Object o) {
    if (o == null) {
        super.setSelectedItem(placeholder);
    } else if (!placeholder.equals(o)) {
        super.setSelectedItem(o);
    } else if (firstSelect) {
        firstSelect = false;
        super.setSelectedItem(o);
    }
}
```

This function is part of a GUI related class and is used to set the selected item in a combo box. It was chosen because it receives an `Object` as a parameter and has multiple conditions.

