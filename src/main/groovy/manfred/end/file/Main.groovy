package manfred.end.file

class Main {
    static void main(String[] args) {
        //-------------------------------1、文件定位 --------------------------------
        def file = new File('testFile.txt')

        //-----------------------2、使用 eachLine Api 每次读取一行, 闭包参数是每一行的字符串------------
        file.eachLine { String line ->
            println line
        }


        //------------------------3、获取输入流,输出流读文件和写文件---------------------------------
        //获取输入流读取文件的每一行
        //1
        file.withInputStream { InputStream inputStream ->
            inputStream.eachLine { String it ->
                println it
            }
        }

        //2
        file.withReader { BufferedReader it ->
            it.readLines().each { String it2 ->
                println it2
            }
        }


        //获取输出流将字符串写入文件 下面这两种方式写入的文件内容会把之前的内容给覆盖
        //1
        file.withOutputStream { OutputStream outputStream ->
            outputStream.write("erdai999".getBytes())
        }

        //2
        file.withWriter { BufferedWriter it ->
            it.write('erdai999')
        }

        //------------------------4、通过输入输出流实现文件拷贝功能---------------------------------
        //1、通过 withOutputStream withInputStream 实现文件拷贝
        def targetFile = new File('testFile1.txt')
        targetFile.withOutputStream { OutputStream outputStream ->
            file.withInputStream { InputStream inputStream ->
                outputStream << inputStream
            }
        }

        //2、通过 withReader、withWriter 实现文件拷贝
        targetFile.withWriter { BufferedWriter bufferedWriter ->
            file.withReader { BufferedReader bufferedReader ->
                bufferedReader.eachLine { String line ->
                    bufferedWriter.write(line + "\r\n")
                }
            }
        }
    }
}
