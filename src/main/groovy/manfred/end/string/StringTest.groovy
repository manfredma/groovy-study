package manfred.end.string

class StringTest {
    static void main(String[] args) {
        def age = 16
        def name = 'erdai'
        //定义一个不可扩展字符串，和我门在Java中使用差不多
        def str1 = 'hello ' + name
        //定义可扩展字符串，字符串里面可以引用变量值，当 {} 里面只有一个变量时，{}也可以去掉
        def str2 = "hello $name ${name + age}"
        //定义带输出格式的不可扩展字符串 使用 \ 字符来分行
        def str3 = '''
\
hello
name
'''
        //定义一个 String 类型的变量接收 GStringImpl 类型的变量，并没有强转
        String str4 = str2

        //打印类型和值 下面代码我省略了 println 方法的（），上面有讲到这种语法也是允许的
        println 'str1类型: ' + str1.class
        println 'str1输出值: ' + str1
        println 'str2类型: ' + str2.class
        println 'str2输出值: ' + str2
        println 'str3类型: ' + str3.class
        println 'str3输出值: ' + str3
        println 'str4类型: ' + str4.class
        println 'str4输出值: ' + str4

    }
}
