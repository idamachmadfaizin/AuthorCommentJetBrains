package com.idamachmadfaizin.authorcomment

import com.intellij.openapi.options.Configurable
import com.intellij.ui.DocumentAdapter
import com.intellij.util.ui.FormBuilder
import java.awt.GridBagLayout
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.swing.JComponent
import javax.swing.JLabel
import javax.swing.JPanel
import javax.swing.JTextField
import javax.swing.event.DocumentEvent

class AuthorCommentSettingsConfigurable : Configurable {
    private var panel: JPanel
    private var authorNameField: JTextField
    private var dateFormatField: JTextField
    private var dateFormatPreview: JLabel
    private val datePreview = LocalDateTime.of(LocalDate.now().year, 1, 31, 0, 0)
    private val settings = AuthorCommentSettings.getInstance()
    private val exampleDateFormats = arrayOf("EEEE, MMMM d, yyyy", "yyyy-MM-dd", "MM/dd/yyyy")

    init {
        panel = JPanel(GridBagLayout())
        authorNameField = JTextField(settings.authorName)
        dateFormatField = JTextField(settings.dateFormat)
        dateFormatField.toolTipText =
            exampleDateFormats.joinToString(
                "<br/>",
                "<html>Example date formats:<br/>",
                "</html>",
            ) {
                "$it (${getDateFormat(it)})"
            }
        dateFormatPreview = JLabel(getDateFormat(dateFormatField.text))

        dateFormatField.document.addDocumentListener(object : DocumentAdapter() {
            override fun textChanged(e: DocumentEvent) {
                dateFormatPreview.text = getDateFormat(dateFormatField.text)
            }
        })
    }

    override fun createComponent(): JComponent {
        panel = FormBuilder.createFormBuilder()
            .addLabeledComponent(JLabel("Author Name:"), authorNameField, 1)
            .addLabeledComponent(JLabel("Date Format:"), dateFormatField, 1)
            .addLabeledComponent(JLabel(), dateFormatPreview)
            .addComponentFillVertically(JPanel(), 0)
            .panel

        return panel
    }

    override fun isModified(): Boolean {
        return authorNameField.text != settings.authorName ||
                dateFormatField.text != settings.dateFormat
    }

    override fun apply() {
        settings.authorName = authorNameField.text
        settings.dateFormat = dateFormatField.text
    }

    override fun reset() {
        if (!isModified) return

        authorNameField.text = settings.authorName
        dateFormatField.text = settings.dateFormat
    }

    override fun getDisplayName(): String = "Author Comment Settings"

    private fun getDateFormat(pattern: String): String {
        try {
            val dateFormatter = DateTimeFormatter.ofPattern(pattern)
            return datePreview.format(dateFormatter)
        } catch (e: Exception) {
            return "Error: ${e.message}"
        }
    }
}