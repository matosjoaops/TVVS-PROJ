# Assignment #5

## Group Information

- Degree: M.EIC
- Group id: 06
- Project id: 03
- Students:
  - João Matos, up201703884
  - Tiago Gomes, up201806658

## Selected functions

### fileName

![](./images/fileName.png)

(say why it was chosen and what it does)

### getRelativeDate

![](./images/getRelativeDate.png)

(say why it was chosen and what it does)

### setSelectedItem

![](./images/setSelectedItem.png)

(say why it was chosen and what it does)

## Dataflow Testing

### fileName

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

