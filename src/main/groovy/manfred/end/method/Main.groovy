package manfred.end.method

/**
 * 1、使用 def 关键字定义一个方法，方法不需要指定返回值类型，参数类型，方法体内的最后一行会自动作为返回值，而不需要return关键字
 * 2、方法调用可以不写 () ，最好还是加上 () 的好，不然可读性不好
 * 3、定义方法时，如果参数没有返回值类型，我们可以省略 def，使用 void 即可
 * 4、实际上不管有没有返回值，Groovy 中返回的都是 Object 类型
 * 5、类的构造方法，避免添加 def 关键字
 */

class Main {
    static void main(String[] args) {
        def sum = new Main().sum(1, 2) //还可以写成这样，但是可读性不好 def sum = sum 1,2
        println(sum)

        // def myClass = new MyClass()
        // myClass.print("xxx")

    }

    def sum(a, b) {
        a + b
    }


    //如果方法没有返回值，我们可以这样写：
    void doSomething(param1, param2) {

    }

    //类的构造方法，避免添加 def 关键字

}


