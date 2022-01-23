# SkillAPI_Extension
## Components
 * [Value Compare](#value-compare)
 * [Manual Trigger(Mechanic)](#manual-triggermechanic)
 * [Manual Trigger(Trigger)](#manual-triggertrigger)
 * [Each Target](#each-target)
 * [Value Divide](#value-divide)
 * [Value Pow](#value-pow)
 * [Value Logarithm](#value-logarithm)
 * [Value Trigonometric](#value-trigonometric)
 * [Value Position](#value-position)
 * [Value Clear](#value-clear)
 * [Value Target Count](#value-target-count)
 * [Comment](#comment)(0.4.3-build16)
 * Reverse Target(0.4.4-build17, Experiment)
## Details
### Value Compare
**Type: Condition**
* 参数列表
  
  |关键字|名称|类型|描述|
  |:---:|:---:|:---:|:---|
  |value_1|Value 1|Text(value)|-|
  |value_2|Value 2|Text(value)|-|
  |expr|Expression|Dropdown\[EQ,NE,GE,GT,LE,LT]|EQ:等于;NE不等于;GE:大于等于;GT:大于;LE:小于等于;LT:小于|
### Manual Trigger(Mechanic)
**Type: Mechanic**
* 参数列表
  
  |关键字|名称|类型|描述|
  |:---:|:---:|:---:|:---|
  |key|Key|Text(key)|一个用于选择[触发器](#manual-triggertrigger "Manual Trigger(Trigger)")的字段|
* 使用方式  
  本组件与[Manual Trigger(Trigger)](#manual-triggertrigger "Manual Trigger(Trigger)")组件配合使用，通过配置相同的响应字段，使 Mechanic 组件触发 Trigger 组件。

SkillAPI中，value的记录是随玩家而变化的，跨触发器与技能可用，例如在A技能中记录的值可以在B技能中使用(在该值被置空之前)。  
假设，存在一个技能有多个触发器，而这些触发器最终执行相同的大段内容，此时便可利用此对组件，将共通的内容置于Manual Trigger(Trigger)下执行，在需要的地方调用Manual Trigger(Mechanic)。
  
### Manual Trigger(Trigger)
**Type: Trigger**
* 参数列表
  
  |关键字|名称|类型|描述|
  |:---:|:---:|:---:|:---|
  |key|Key|Text(key)|用于指示本触发器的响应字段|
* 记录值：无

### Each Target
**Type: Target**
* 参数列表

  |关键字|名称|类型|描述|
  |:---:|:---:|:---:|:---|
  |stop|Stop|Dropdown\[False,True]|是否在子组件未正常运行时停止。在此项设置为true时，本组件会在子组件报考运行错误时向上报告运行错误|

### Value Divide
**Type: Mechanic**

* 功能: **除法运算**
* 参数列表

  |关键字|名称|类型|描述|
  |:---:|:---:|:---:|:---|
  |key|Key|Text(key)|在何处保存返回值|
  |numerator|Numerator|Number(0,0)|分子|
  |denominator|Denominator|Number(1,0)|分母|
  |exception|Exception|Number(0,0)|当运算将产生错误时将使用此值作为返回值|

### Value Pow
**Type: Mechanic**

* 功能: **指数运算**
* 参数列表

  |关键字|名称|类型|描述| 
  |:---:|:---:|:---:|:---|
  |key|Key|Text(key)|在何处保存返回值|
  |base|Base|Number(1,0)|底数|
  |exponent|Exponent|Number(0,0)|指数|

### Value Logarithm
**Type: Mechanic**

* 功能: **log运算**
* 参数列表

  |关键字|名称|类型|描述|
  |:---:|:---:|:---:|:---|
  |key|Key|Text(key)|在何处保存返回值|
  |base|Base|Number(1,0)|底数|
  |num|Num|Number(0,0)|真数|
  |exception|Exception|Number(0,0)|当运算产生错误时使用该值|

### Value Trigonometric
**Type: Mechanic**

* 功能: **三角函数运算**
* 参数列表

  |关键字|名称|类型|描述| 
  |:---:|:---:|:---:|:---|
  |key|Key|Text(key)|在何处保存返回值|
  |func|Func|Dropdown\[sin,cos,tan,asin,acos,atan]|选择三角运算使用的函数，前缀带a的为逆运算|
  |num|Num|Number(0,0)|当函数选择正运算时，单位应为rad|

### Value Position
**Type: Mechanic**

* 功能: **记录坐标值**
* 参数列表

  |关键字|名称|类型|描述|
  |:---:|:---:|:---:|:---|
  |key|Key|Text(key)|记录值前缀|

* 此组件将一次性记录多个值，分别为目标的x,y,z坐标,yaw,pitch角度和所在的世界名，对应的键为 前缀_\(x|y|z|yaw|pitch|world)
### Value Clear
**Type: Mechanic**

* 功能: **清理记录值**
* 参数列表

  |关键字|名称|类型|描述| 
  |:---:|:---:|:---:|:---|
  |key|Key|Text(key)|要清理的值|

### Value Target Count
**Type: Mechanic**

* 功能: **记录目标数量**
* 参数列表

  |关键字|名称|类型|描述| 
  |:---:|:---:|:---:|:---|
  |key|Key|Text(key)|键|

### Comment
**Type: Mechanic**
* FromVersion: 0.4.3-build16
* 参数列表

  |关键字|名称|类型|描述|
  |:---:|:---:|:---:|:---|
  |cmt|Comment|Text()|注释|
  |skip|Skip|Dropbox\[false,true]|是否跳过注释的子组件|