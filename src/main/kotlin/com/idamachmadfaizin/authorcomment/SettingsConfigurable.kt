package com.idamachmadfaizin.authorcomment

import com.idamachmadfaizin.authorcomment.components.DateFormatLabel
import com.idamachmadfaizin.authorcomment.extensions.format
import com.intellij.openapi.options.Configurable
import com.intellij.ui.DocumentAdapter
import com.intellij.ui.components.JBTextField
import com.intellij.util.ui.FormBuilder
import javax.swing.JComponent
import javax.swing.JLabel
import javax.swing.JPanel
import javax.swing.event.DocumentEvent
import javax.swing.event.DocumentListener

class SettingsConfigurable : Configurable {
    private val authorNameLabel: JLabel = JLabel("Author Name:")
    private val authorNameField: JBTextField
    private val dateFormatLabel: DateFormatLabel = DateFormatLabel("Date Format:")
    private val dateFormatField: JBTextField
    private val dateFormatPreview: JLabel
    private val onChangedDateFormatField: DocumentListener

    private val settings = Settings.getInstance()

    init {
        authorNameField = JBTextField(settings.authorName)
        dateFormatField = JBTextField(settings.dateFormat)
        dateFormatPreview = JLabel(SettingsConstants.DATE_PREVIEW.format(dateFormatField.text))

        onChangedDateFormatField = object : DocumentAdapter() {
            override fun textChanged(e: DocumentEvent) {
                dateFormatPreview.text = SettingsConstants.DATE_PREVIEW.format(dateFormatField.text)
            }
        }
        dateFormatField.document.addDocumentListener(onChangedDateFormatField)
    }

    override fun disposeUIResources() {
        dateFormatField.document.removeDocumentListener(onChangedDateFormatField)
    }

    override fun createComponent(): JComponent {
        return FormBuilder.createFormBuilder()
            .addLabeledComponent(authorNameLabel, authorNameField, 1)
            .addLabeledComponent(dateFormatLabel, dateFormatField, 1)
            .addLabeledComponent(JLabel(), dateFormatPreview)
            .addComponentFillVertically(JPanel(), 0)
            .panel
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
}