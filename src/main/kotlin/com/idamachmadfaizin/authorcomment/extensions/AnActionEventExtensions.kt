package com.idamachmadfaizin.authorcomment.extensions

import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.command.WriteCommandAction

/**
 * Write text to all carets
 * @param text String the text to be inserted.
 */
fun AnActionEvent.writeCommandAction(text: String) {
    val project = this.project ?: return
    val editor = this.getData(CommonDataKeys.EDITOR) ?: return

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