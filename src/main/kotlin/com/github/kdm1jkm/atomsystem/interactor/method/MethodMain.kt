package com.github.kdm1jkm.atomsystem.interactor.method

import com.github.kdm1jkm.atomsystem.ApplicationManager
import com.github.kdm1jkm.atomsystem.interactor.Interactor
import com.github.kdm1jkm.atomsystem.manager.getApplied
import com.github.kdm1jkm.atomsystem.models.Application.State.ACCEPTED
import com.github.kdm1jkm.atomsystem.models.Application.State.NOT_RATED
import com.github.kdm1jkm.atomsystem.models.ApplicationMethod

class MethodMain(private val manager: ApplicationManager, private val method: ApplicationMethod) : Interactor {
    override fun run(): Interactor? {
        println("\n현재 ${method.name}님으로 로그인되어 있습니다.")
        println("1. 지원서 목록 확인")
        println("2. 지원서 점수 매기기")
        println("3. 합격자 확인")
        println("4. 돌아가기")
        print("Enter: ")

        return when (readln()) {
            "1" -> run {
                println("\n--원서 목록--")
                manager.getApplied(method)
                    .map { if (it.state == NOT_RATED) "${it.studentId}(NONE)" else "${it.studentId}(${it.score})" }
                    .forEach { println(it) }

                run()
            }
            "2" -> MethodScore(manager, method)
            "3" -> run {
                println("\n--합격자 목록--")
                manager.getApplied(method)
                    .filter { it.state == ACCEPTED }
                    .sortedByDescending { it.score }
                    .map { "${it.studentId}: ${it.score}" }
                    .forEach { println(it) }
                run()
            }
            "4" -> MethodEntry(manager)
            else -> run()
        }
    }
}