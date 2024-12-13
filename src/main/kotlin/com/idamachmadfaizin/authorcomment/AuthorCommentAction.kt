package com.idamachmadfaizin.authorcomment

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.command.WriteCommandAction
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class AuthorCommentAction : AnAction() {
    override fun actionPerformed(e: AnActionEvent) {
        val editor = e.getRequiredData(CommonDataKeys.EDITOR)
        val project = e.project ?: return
        val settings = AuthorCommentSettings.getInstance()

        var text = "<author>${settings.authorName}</author>"

        try {
            val dateFormatter = DateTimeFormatter.ofPattern(settings.dateFormat)
            val currentDate = LocalDateTime.now().format(dateFormatter)

            text += "<date>$currentDate</date>"
        } catch (e: Exception) {
            text += "<date>${e.message}</date>"
        }

        WriteCommandAction.runWriteCommandAction(project) {
            // Get all carets
            val caretModel = editor.caretModel
            val carets = caretModel.allCarets

            // Process each caret in reverse order to avoid offset issues
            for (caret in carets.reversed()) {
                if (caret.hasSelection()) {
                    // Replace selected text
                    val start = caret.selectionStart
                    val end = caret.selectionEnd
                    editor.document.replaceString(start, end, text)
                    // Update caret position after replacement
                    caret.moveToOffset(start + text.length)
                } else {
                    // Insert at caret position
                    editor.document.insertString(caret.offset, text)
                    // Update caret position after insertion
                    caret.moveToOffset(caret.offset + text.length)
                }
            }
        }
    }
}
