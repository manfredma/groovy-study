package manfred.end.xml

import groovy.xml.MarkupBuilder
import groovy.xml.XmlSlurper

class Main {

    static void main(String[] args) {
        println "testParseXml ++++++++++++++++++++++++++"
        testParseXml()
        println "testGenerateXml ++++++++++++++++++++++++++"
        testGenerateXml()
    }

    static void testParseXml() {
        //定义一个带格式的 xml 字符串
        def xml = '''
            <response>
               <value>
                   <books id="1" classification="android">
                       <book available="14" id="2">
                           <title>第一行代码</title>
                           <author id="2">郭霖</author>
                       </book>
                       <book available="13" id="3">
                           <title>Android开发艺术探索</title>
                           <author id="3">任玉刚</author>
                       </book>
                   </books>
               </value>
            </response>
            '''
        //创建 XmlSlurper 类对象，解析 XML 文件主要借助 XmlSlurper 这个类
        def xmlSlurper = new XmlSlurper()
        //解析 mxl 返回 response 根结点对象
        def response = xmlSlurper.parseText(xml)
        //打印一些结果
        println response.value.books[0].book[0].title.text()
        println response.value.books[0].book[1].author.text()
        //打印结果

        //1、使用迭代器解析
        response.value.books.each { books ->
            books.book.each { book ->
                println book.title
                println book.author
            }
        }
        //打印结果

        //2、深度遍历 XML 数据
        def str1 = response.depthFirst().findAll { book ->
            return book.author == '郭霖'
        }
        println str1
        //打印结果


        //3、广度遍历 XML 数据
        def str2 = response.value.books.children().findAll { node ->
            node.name() == 'book' && node.@id == '2'
        }.collect { node ->
            "$node.title $node.author"
        }
        println str2
        //打印结果

    }

    static void testGenerateXml() {
        /**
         * <response>
         *      <value>
         *          <books id="1" classification="android">
         *              <book available="14" id="2">
         *                 <title>第一行代码</title>
         *                 <author id="2">郭霖</author>
         *             </book>
         *             <book available="13" id="3">
         *                 <title>Android开发艺术探索</title>
         *                 <author id="3">任玉刚</author>
         *             </book>
         *         </books>
         *     </value>
         * </response>
         */
        //方式1：通过下面这种方式 就可以实现上面的效果，但是这种方式有个弊端，数据都是写死的
        def sw = new StringWriter()
        def xmlBuilder = new MarkupBuilder(sw)
        xmlBuilder.response {
            value {
                books(id: '1', classification: 'android') {
                    book(available: '14', id: '2') {
                        title('第一行代码')
                        author(id: '2', '郭霖')
                    }
                    book(available: '13', id: '3') {
                        title('Android开发艺术探索')
                        author(id: '3', '任玉刚')
                    }
                }
            }
        }
        println sw

        //方式2：将 XML 数据对应创建相应的数据模型，就像我们解析 Json 创建相应的数据模型是一样的
        //创建 XML 对应数据模型

        //创建 response 对象
        def response = new Response()
        //构建 XML
        xmlBuilder.response {
            value {
                books(id: response.value.books.id, classification: response.value.books.classification) {
                    response.value.books.book.each {
                        def book1 = it
                        book(available: it.available, id: it.id) {
                            title(book1.title)
                            author(authorId: book1.authorId, book1.author)
                        }
                    }
                }
            }
        }
        println sw
    }
}

class Response {

    def value = new Value()

    class Value {

        def books = new Books(id: '1', classification: 'android')

        class Books {
            def id
            def classification
            def book = [new Book(available: '14', id: '2', title: '第一行代码', authorId: 2, author: '郭霖'),
                        new Book(available: '13', id: '3', title: 'Android开发艺术探索', authorId: 3, author: '任玉刚')]

            class Book {
                def available
                def id
                def title
                def authorId
                def author
            }
        }
    }
}
