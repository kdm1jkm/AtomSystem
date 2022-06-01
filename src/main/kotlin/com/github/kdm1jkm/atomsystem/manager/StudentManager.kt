package com.github.kdm1jkm.atomsystem.manager

import com.github.kdm1jkm.atomsystem.ApplicationManager
import com.github.kdm1jkm.atomsystem.models.Application
import com.github.kdm1jkm.atomsystem.models.ApplicationMethod
import com.github.kdm1jkm.atomsystem.models.Student

fun ApplicationManager.registerStudent(student: Student) {
    with(data.students) {
        if (any { it.id == student.id }) {
            throw IllegalArgumentException("Student id ${student.id} already exist.")
        }

        add(student)
    }
}

fun ApplicationManager.getApplied(student: Student): List<Application> {
    return data.applications.filter {
        it.studentId == student.id
    }
}

fun ApplicationManager.apply(student: Student, applicationMethod: ApplicationMethod, content: String, preference: Int) {
    data.applications.add(
        Application(
            student.id,
            applicationMethod.id,
            content,
            Application.State.NOT_RATED,
            preference
        )
    )
}

fun ApplicationManager.getStudentById(id: String): Student? {
    return data.students.find { it.id == id }
}

fun ApplicationManager.getApplied(method: ApplicationMethod): List<Application> {
    return data.applications.filter {
        it.methodId == method.id
    }
}