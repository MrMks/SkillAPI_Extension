# SkillAPI_Extension
## Components
 * [Value Compare](#value-compare)
 * [Manual Trigger(Mechanic)](#manual-triggermechanic)
 * [Manual Trigger(Trigger)](#manual-triggertrigger)
 * [Each Target](#each-target)
## Details
### Value Compare
**Type: Condition**
* 参数列表
  
  |关键字|名称|类型|描述|
  |:---:|:---:|:---:|:---|
  |value_1|Value 1|Text(value)|-|
  |value_2|Value 2|Text(value)|-|
  |expr|Expression|Dropdown\[EQ,NE,GE,GT,LE,LT]|见编辑器中有关该字段的描述|
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