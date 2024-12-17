package com.idamachmadfaizin.authorcomment.components

import com.idamachmadfaizin.authorcomment.SettingsConstants
import com.idamachmadfaizin.authorcomment.extensions.format
import com.intellij.icons.AllIcons
import com.intellij.ide.BrowserUtil
import com.intellij.ide.HelpTooltip
import javax.swing.JLabel
import javax.swing.JPanel

class DateFormatLabel(text: String) : JPanel() {
    private val mainLabel: JLabel = JLabel(text)
    private val helpLabel: JLabel = JLabel(AllIcons.General.ContextHelp)

    init {
        val exampleDateFormats = arrayOf("EEEE, MMMM d, yyyy", "yyyy-MM-dd", "MM/dd/yyyy")
        val dateFormatHelpText = exampleDateFormats.joinToString(
            "<br/>", "<html>", "</html>",
        ) {
            "$it (${SettingsConstants.DATE_PREVIEW.format(it)})"
        }

        HelpTooltip()
            .setTitle("Example date formats")
            .setDescription(dateFormatHelpText)
            .setLink(
                "DATE TIME FORMATTER REFERENCE",
                fun() {
                    BrowserUtil.browse("https://docs.oracle.com/javase/8/docs/api/java/time/format/DateTimeFormatter.html")
                },
                true
            )
            .installOn(helpLabel)

        add(mainLabel)
        add(helpLabel)
    }
}