package com.idamachmadfaizin.authorcomment

import com.intellij.openapi.options.Configurable
import javax.swing.JComponent
import javax.swing.JPanel
import javax.swing.JTextField
import java.awt.GridBagLayout
import java.awt.GridBagConstraints
import javax.swing.JLabel

class AuthorDateSettingsConfigurable : Configurable {
    private var panel: JPanel? = null
    private var authorNameField: JTextField? = null
    private var dateFormatField: JTextField? = null
    private val settings = AuthorDateSettings.getInstance()

    override fun createComponent(): JComponent {
        panel = JPanel(GridBagLayout())
        val c = GridBagConstraints()

        c.fill = GridBagConstraints.HORIZONTAL
        c.gridx = 0
        c.gridy = 0
        panel!!.add(JLabel("Author Name:"), c)

        c.gridx = 1
        authorNameField = JTextField(settings.authorName)
        panel!!.add(authorNameField!!, c)

        c.gridx = 0
        c.gridy = 1
        panel!!.add(JLabel("Date Format:"), c)

        c.gridx = 1
        dateFormatField = JTextField(settings.dateFormat)
        panel!!.add(dateFormatField!!, c)

        return panel!!
    }

    override fun isModified(): Boolean {
        return authorNameField?.text != settings.authorName ||
                dateFormatField?.text != settings.dateFormat
    }

    override fun apply() {
        settings.authorName = authorNameField?.text ?: "MyName"
        settings.dateFormat = dateFormatField?.text ?: "EEEE, MMMM d, yyyy"
    }

    override fun getDisplayName(): String = "Author Comment Settings"
}