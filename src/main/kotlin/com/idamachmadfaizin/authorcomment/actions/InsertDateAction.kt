package com.idamachmadfaizin.authorcomment.actions

import com.idamachmadfaizin.authorcomment.Settings
import com.idamachmadfaizin.authorcomment.extensions.format
import com.idamachmadfaizin.authorcomment.extensions.writeCommandAction
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import java.time.LocalDate

class InsertDateAction : AnAction() {
    override fun actionPerformed(e: AnActionEvent) {
        val settings = Settings.getInstance()

        val currentDate = LocalDate.now().format(settings.dateFormat)
        val text = "<date>$currentDate</date>"

        e.writeCommandAction(text)
    }
}