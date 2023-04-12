package manfred.end.json

import groovy.json.JsonSlurper

class Main {
    static void main(String[] args) {
        //发送请求获取服务器响应的数据
        def response = getNetWorkData("https://www.wanandroid.com/banner/json")
        println response.data[0].desc
        println response.data[0].imagePath
    }

    def static getNetWorkData(String url) {
        def connect = new URL(url).openConnection()
        connect.setRequestMethod("GET")
        //这个会阻塞线程 在Android中不能这样操作 但是在桌面程序是可以的
        connect.connect()
        def response = connect.content.text

        //json转实体对象
        def jsonSlurper = new JsonSlurper()
        jsonSlurper.parseText(response)
    }
    //打印结果
}
