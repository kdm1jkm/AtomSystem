package com.github.kdm1jkm.atomsystem.interactor.student

import com.github.kdm1jkm.atomsystem.ApplicationManager
import com.github.kdm1jkm.atomsystem.interactor.Interactor
import com.github.kdm1jkm.atomsystem.manager.apply
import com.github.kdm1jkm.atomsystem.manager.getMethodById
import com.github.kdm1jkm.atomsystem.models.Student

class StudentApply(private val manager: ApplicationManager, private val student: Student) : Interactor {
    override fun run(): Interactor {
        println("\n--지원서 작성--")
        print("지원할 곳의 id 입력: ")
        val method = manager.getMethodById(readln())
        if (method == null) {
            println("해당 id는 존재하지 않습니다.")
            return StudentMain(manager, student)
        }

        println("지원서 작성, 작성 완료후엔 빈 줄에서 enter.")
        println("----------")
        println(method.content)
        println("----------")

        val content = StringBuilder().run {
            var new: String
            while (true) {
                print(" ")
                new = readln()
                if (new == "") break
                append(new)
                append("\n")
            }
            toString()
        }

        val preference = run {
            var input: Int? = null
            while (input == null) {
                print("우선순위 입력(숫자가 낮을수록 우선시함): ")
                input = readln().toIntOrNull()
            }
            input
        }

        manager.apply(student, method, content, preference)
        manager.save()

        return StudentMain(manager, student)
    }
}