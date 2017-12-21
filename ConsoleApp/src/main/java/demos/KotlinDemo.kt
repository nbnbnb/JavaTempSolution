package demos


fun test() {

// 正常编码是
// 第1行：\n
// 第2行：8*空格 + x + \n
// 第3行：8*空格 + y + \n
// 第4行：8*空格 + z + \n
// 第5行：8*空格
    val xyz = """
x
y
z
"""
// 先使用 \r\n \r \n 进行分割
// 再剔除空白行
// 最后将缩进符替换为 ""
    val xyz_new = xyz.trimIndent()
    println(xyz_new == "x\ny\nz")

    val abc = """
|a
|b
|c""".trimMargin() // | 为默认前导字符
    println(abc == "a\nb\nc")


}


