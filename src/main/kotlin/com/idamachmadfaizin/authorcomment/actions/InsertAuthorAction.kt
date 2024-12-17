package com.idamachmadfaizin.authorcomment.actions

import com.idamachmadfaizin.authorcomment.Settings
import com.idamachmadfaizin.authorcomment.extensions.writeCommandAction
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent

class InsertAuthorAction : AnAction() {
    override fun actionPerformed(e: AnActionEvent) {
        val settings = Settings.getInstance()

        val text = "<author>${settings.authorName}</author>"

        e.writeCommandAction(text)
    }
}
