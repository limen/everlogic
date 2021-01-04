# everlogic

## 特性

支持

- 逻辑表达式，如 a>b and b==2 and c!=d，a>b or b==2 or c!=d
- 聚合逻辑表达式，如 (a>b and b==2 and c!=d) and (a1>b1 or b1==2 or c1!=d1) and (a2>b2 xor b2==2 xor c2!=d2) 

## 支持的数据类型

- 字符串
- 数字
- 日期
- 布尔
- JSON
- 字符串数组
- 数字数组

## 操作符

- equal: 全部类型
- ne: 全部类型
- gt: 数字，字符串，日期，布尔
- gte: 同gt
- lt: 同gt
- lte: 同gt
- contain: JSON，数组+元素，数组+数组，元素类型为数字或字符串
- inside: JSON，元素+数组，数组+数组，元素类型为数字或字符串
- start_with: 字符串
- end_with: 同start_with

## JSON

支持的值类型

- 数字
- 字符串
- 数字数组(当成字符串数组处理)
- 字符串数组
- 布尔

运算符

- contain: {a:123,b:"456"} contain {a:123} 返回 true, {a:123,b:"456"} contain {a:1234} 返回false
- inside: 与contain逻辑相反

## 示例

### 逻辑表达式

[LogicGroupTest](https://github.com/limen/everlogic/tree/master/src/test/java/LogicGroupTest.java)

### 聚合逻辑表达式

[AggregateLogicGroupTest](https://github.com/limen/everlogic/tree/master/src/test/java/AggregateLogicGroupTest.java)

### 字符串

[StringLogicTest](https://github.com/limen/everlogic/tree/master/src/test/java/StringLogicTest.java)

### 数字

[NumberLogicTest](https://github.com/limen/everlogic/tree/master/src/test/java/NumberLogicTest.java)

### 日期

[DateLogicTest](https://github.com/limen/everlogic/tree/master/src/test/java/DateLogicTest.java)

### JSON

[JSONLogicTest](https://github.com/limen/everlogic/tree/master/src/test/java/JSONLogicTest.java)

### 字符串数组

[StringArrLogicTest](https://github.com/limen/everlogic/tree/master/src/test/java/StringArrLogicTest.java)

### 数字数组

[NumberArrLogicTest](https://github.com/limen/everlogic/tree/master/src/test/java/NumberArrLogicTest.java)

## Converter

将参数转为特定类型，如

- StringConverter 将参数转为String类型
- NumberConverter 将参数转为Double类型
- DateConverter 将参数转为Date类型

## Comparator

比较参数值，如

- StringComparator 比较String类型变量
- NumberComparator 比较Double类型变量
- DateComparator 比较Date类型变量
- CalenderComparator 增强了DateComparator，可指定比较的字段，如只比较年、月、日
