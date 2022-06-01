package com.github.kdm1jkm.interactor.student

import com.github.kdm1jkm.ApplicationManager
import com.github.kdm1jkm.interactor.Interactor
import com.github.kdm1jkm.interactor.MainEntry

class StudentEntry(private val manager: ApplicationManager) : Interactor {
    override fun run(): Interactor? {
        println("1. 회원가입")
        println("2. 로그인")
        println("3. 돌아가기")
        print("Enter: ")

        return when (readln()) {
            "1" -> StudentRegister(manager)
            "2" -> StudentLogin(manager)
            "3" -> MainEntry(manager)
            else -> run()
        }
    }
}