package manfred.end.closure
/**
 * 闭包声明
 * 1、闭包基本的语法结构：外面一对大括号，接着是申明参数，参数类型可省略，在是一个 -> 箭头号，最后就是闭包体里面的内容
 * 2、闭包也可以不定义参数，如果闭包没定义参数的话，则隐含有一个参数，这个参数名字叫 it
 *
 * 闭包调用
 * 1、闭包可以通过 .call 方法来调用
 * 2、闭包可以直接用括号+参数来调用
 *
 * 闭包中的关键变量
 * 每个闭包中都含有 this、owner 和 delegate 这三个内置对象
 * 注意：
 * 1、getThisObject() 方法 和 thisObject 属性等同于 this
 * 2、getOwner() 方法 等同于 owner
 * 3、getDelegate() 方法 等同于 delegate
 *
 * 注意：
 * 1、this 永远指向定义该闭包最近的类对象，就近原则，定义闭包时，哪个类离的最近就指向哪个，我这里的离得近是指定义闭包的这个类，包含内部类
 * 2、owner 永远指向定义该闭包的类对象或者闭包对象，顾名思义，闭包只能定义在类中或者闭包中
 * 3、delegate 和 owner 是一样的，我们在闭包的源码中可以看到，owner 会把自己的值赋给 delegate，但同时 delegate 也可以赋其他值
 * 注意：在我们使用 this , owner , 和 delegate 的时候， this 和 owner 默认是只读的，我们外部修改不了它，这点在源码中也有体现，但是可以对 delegate 进行操作
 *
 */
class ClosureTest {
    static void main(String[] args) {
        //定义一个闭包赋值给 closure 变量
        def closure = { params1, params2 ->
            params1 + params2
        }

        //闭包调用方式1: 闭包可以通过 .call 方法来调用
        def result1 = closure('erdai ', '666')
        //闭包调用方式2: 闭包可以直接用括号+参数来调用
        def result2 = closure.call('erdai ', '777')

        //打印值
        println result1
        println result2

        //定义一个无参闭包
        def closure1 = {
            println('无定义参数闭包')
        }
        closure1() //或者调用 closure1.call()


        //定义一个闭包
        def outerClosure = {
            println "this: " + this
            println "owner: " + owner
            println "delegate: " + delegate
        }
        //调用闭包
        outerClosure.call()
        //打印结果
        //证明当前三者都指向了GroovyGrammar这个脚本类对象

        def people1 = new People1()
        def people2 = new People2()
        people1.closure.delegate = people2
        people1.closure.call()

        people1.closure.resolveStrategy = Closure.DELEGATE_FIRST
        people1.closure.call()

    }
}

class People1 {
    def name = '我是People1'

    def action() {
        println '吃饭'
    }

    def closure = {
        println name
        action()
    }
}

class People2 {
    def name = '我是People2'

    def action() {
        println '睡觉'
    }
}



