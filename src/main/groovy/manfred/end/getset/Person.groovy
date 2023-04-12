package manfred.end.getset

//情况1：在我们创建属性的时候，Groovy会帮我们自动创建 get 和 set 方法
class People {
    def name
    def age
}

//情况2 当我们定义了一个属性的 get 方法，而没有定义这个属性，默认这个属性只读
//我们修改一下People类
class People2 {
    def name

    def getAge() {
        12
    }
}

//情况3: 如果我们不想调用这个特殊的 get 方法时则可以使用 .@ 直接域访问操作符访问属性本身
class People3 {
    def name
    def age

    def getName() {
        "My name is $name"
    }
}


static void main(String[] args) {
    def people = new People()
    people.name = 'erdai'
    people.age = 19
    println "姓名: $people.name 年龄: $people.age"

    def people2 = new People2()
    people2.name = 'erdai'
    // people2.age = 19 // 会提示不能修改只读值 groovy.lang.ReadOnlyPropertyException
    println "姓名: $people2.name 年龄: $people2.age"

    //这里使用了命名的参数初始化和默认的构造器创建people对象，后面会讲到
    def people3 = new People3(name: 'erdai666')
    people3.age = 19
    def myName = people3.@name

    //打印值
    println myName
    println "姓名: $people3.name 年龄: $people3.age"
    //看到区别了吗？使用 people.name 则会去调用这个属性的get方法，而 people.@name 则会访问这个属性本身

    testClass(People.class)
    testClass(People)
}

//定义一个测试class的方法，从前面的语法我们知道，方法的参数类型是可以省略的
def testClass(myClass) {
    println("" + myClass)
}