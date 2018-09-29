package sbwebapp.entities

import javax.validation.constraints.DecimalMin
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

class Book {
    @NotEmpty(message = "书名不能为空")
    var bookName: String = ""

    @DecimalMin(value = "0.1", message = "单价最低为0.1")
    var price: Double = 0.0

    @NotEmpty(message = "GroupA 不能为空", groups = [GroupA::class])
    var groupA: String = ""

    @NotEmpty(message = "GroupB 不能为空", groups = [GroupB::class])
    var groupB: String = ""
}