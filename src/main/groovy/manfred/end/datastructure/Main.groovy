package manfred.end.datastructure

class Main {
    static void main(String[] args) {
        println """testArray +++++++++++++++++++++++++++++"""
        testArray()
        println """testList +++++++++++++++++++++++++++++"""
        testList()
        println """testMap +++++++++++++++++++++++++++++"""
        testMap()
        println """testRange +++++++++++++++++++++++++++++"""
        testRange()
    }

    private static void testMap() {
        // 1、Map 表示键-值表，其底层对应 Java 中的 LinkedHashMap
        // 2、Map 变量由[:]定义，冒号左边是 key，右边是 Value。key 必须是字符串，value 可以是任何对象
        // 3、Map 的 key 可以用 '' 或 "" 或 ''' '''包起来，也可以不用引号包起来
        def map = [a: 1, 'b': true, "c": "Groovy", '''d''': '''ddd''']

        //---------------------------- Map 中元素访问操作 ----------------
        /*
         * 有如下三种方式：
         * 1、map.key
         * 2、map[key]
         * 3、map.get(ket)
         */
        println map.a
        println map['b']
        println map.get('c')


        //---------------------------- Map 中添加和修改元素 -------------------
        //如果当前 key 在 map 中不存在，则添加该元素，如果存在则修改该元素
        map.put('key', 'value')
        map['key'] = "value"

        //---------------------------- Map 中遍历元素 -------------------
        //下面为了打印输出的格式清晰，做了一些额外的操作
        map.each {
            print "$it.key $it.value \t"
        }
        println()

        map.each { key, value ->
            print "$key $value \t"
        }
        println()

        map.eachWithIndex { entry, index ->
            print "$entry.key $entry.value $index \t"
        }
        println()

        map.eachWithIndex { key, value, index ->
            print "$key $value $index \t"
        }

    }

    private static void testList() {
        //在 Groovy 中定义的集合默认就是对应于 Java 中 ArrayList 集合
        def list1 = [1, 2, 3]
        //打印 list 类型
        println list1.class

        //集合元素可以接收任意的数据类型
        def list2 = ['erdai666', 1, true]
        println list2

        //方式1：通过 Java 的强类型方式去定义
        LinkedList list3 = [4, 5, 6]

        //方式2：通过 as 关键字来指定
        def list4 = [1, 2, 3] as LinkedList
        println list3.class
        println list4.class

        def list = [1, 2, 3]
        //-------------------------- 增加元素 ---------------------------------
        //有以下几种方式
        list.add(20)
        list.leftShift(20)
        list << 20

        //-------------------------- 删除元素 ---------------------------------
        //根据下标移除元素
        list.remove(0)

        //-------------------------- 修改元素 ---------------------------------
        //根据下标修改元素
        list[0] = 100

        //-------------------------- 查询元素 ---------------------------------
        //调用闭包的 find 方法，方法中接收一个闭包，闭包的参数就是 list 中的元素
        list.find {
            println it
        }
    }

    private static void testArray() {
        //在 Java 中，我们一般会这样去定义一个数组
        String[] javaArray = ["Java", "Groovy", "Android"]

        //在 Groovy 中，我们一般会使用 as 关键字定义数组
        def groovyArray = ["Java", "Groovy", "Android"] as String[]
    }

    static void testRange() {
        //定义一个两端都是闭区间的范围
        def range = 1..10
        range.each {
            print it + " "
        }
        println()

        //如果不想包含最后一个元素
        def range1 = 1..<10
        range1.each {
            print it + " "
        }
        println()

        //打印头尾边界元素
        println "$range1.from $range1.to"

    }


}
