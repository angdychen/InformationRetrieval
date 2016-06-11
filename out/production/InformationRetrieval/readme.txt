1.代码构造：
	CreateIndex.java(构建hashmap,生产字典index.dict)
	BooleanInquire.java(实现布尔查询)
	main.java(主函数)
2.实现思路：
    一、CreateIndex.java
	①循环读取文件
	②若map中不含有此词项，将DocFreq存为1和DocId都存在Smap中，
	  将该词和Smap存放在map中
	③否则说明该词已经出现在map中，则判断目前读取到的词项和
	  已经在文件中的词项是否在一个文件中，若不在一个文件中则
	  将DocFreq加1，并DocID的数组中增加一个id。存放在map中。
	④利用迭代器将hashmap写入文件。
	⑤返回map。
    二、BooleanInquire.java
	①将用户的输入获取到数组内，若用户只输入一个单词则
	  直接到map中获取其DocID的数组。
	②否则判断连接词为or还是and，然后调用其各自的方法。
    三、main.java
	①调用CreateIndex类，new出其对象createindex,并构建返回一个tmpmap。
	②调用BooleanInquire类中的search方法。
3.执行方法：
	将CreateIndex.java 
	BooleanInquire.java 
	main.java
	这三个类建于一个java Project中，执行t.java
4.执行环境：
	MyEclipse10 + jdk1.6 + win7