# everlogic

轻量级的逻辑表达式引擎

## 特性

支持无限层级的逻辑嵌套

```sql
(
    (rule1)
    and
    (
        (rule2)
        or
        (rule3)
    )
) and (
    (rule4)
    and
    (
        (rule5)
        or
        (
            (
                (rule7)
                or 
                (rule8)
            )
            and 
            (
                (rule9)
                and 
                (rule10)
            )
        )
    )
)
```


## 扩展性

一切皆可自定义

- 自定义运算符
- 自定义数据类型
- 自定义类型转换, 将输入转为目标类型, 如字符串转数字
- 自定义比较运算, 比较两个值的大小
- 自定义逻辑运算, 根据运算符与操作数得到布尔值
- 自定义数据转换，如取整, 四舍五入, 日期格式化

## 内置数据类型

- 字符串
- 数字
- 日期
- 布尔
- json
- 字符串数组
- 数字数组

## 操作符

- nil: 为空
- not_nil: 不为空
- equal: 相等
- ne: 不相等
- gt: 大于
- gte: 大于或等于
- lt: 小于
- lte: 小于或等于
- contain: 包含
- inside: 包含于
- start_with: 以参数为开头
- end_with: 以参数为结尾
- regex: 正则匹配

## 操作符适用类型

- 字符串: nil, not_nil, equal, ne, lt, lte, gt, gte, contain, inside, start_with, end_with, regex
- 数字: nil, not_nil, equal, ne, lt, lte, gt, gte
- 日期: nil, not_nil, equal, ne, lt, lte, gt, gte
- 布尔: equal, ne
- json: equal, ne, contain, inside
- 字符串数组: equal, ne, contain, inside
- 数字数组: equal, ne, contain, inside

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

[LogicRuleTest](https://github.com/limen/everlogic/tree/master/src/test/java/LogicRuleTest.java)

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

## 内置Converter

将参数转为特定类型，如

- StringConverter 将参数转为String类型
- NumberConverter 将参数转为Double类型
- DateConverter 将参数转为Date类型

## 内置Comparator

比较参数值，如

- StringComparator 比较String类型变量
- NumberComparator 比较Double类型变量
- DateComparator 比较Date类型变量

## 内置Formatter

- StringFormatter 字符串转大写/小写, 取部分字符串
- NumberFormatter 四舍五入, 取整, 浮点部分限制位数, 上取整
- DateFormatter 格式化日期, 如格式化为yyMMdd

## EnvContainer

环境变量容器，当变量为动态类型时，通过EnvContainer实时获取。

例如(#{a} and #{b})，其中#{a}、#{b}均为动态变量，运算时会先从容器中取值，再求最终结果。


## 扩展

见 [extend](https://github.com/limen/everlogic/tree/master/src/main/java/com/limengxiang/everlogic/extend)

- 实现一个逻辑单元, 如 StringCILogic
- 定义新的 LogicUnitFactory
- 注册 LogicUnitFactory，见 LogicEvaluator#addLogicUnitFactory


