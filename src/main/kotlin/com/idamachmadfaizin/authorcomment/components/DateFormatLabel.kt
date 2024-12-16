package com.idamachmadfaizin.authorcomment.components

import com.idamachmadfaizin.authorcomment.DATE_PREVIEW
import com.idamachmadfaizin.authorcomment.extensions.format
import com.intellij.icons.AllIcons
import com.intellij.ide.BrowserUtil
import com.intellij.ide.HelpTooltip
import java.awt.FlowLayout
import javax.swing.JLabel
import javax.swing.JPanel

class DateFormatLabel(text: String) : JPanel(FlowLayout(FlowLayout.LEFT)) {
    private val mainLabel: JLabel = JLabel(text)
    private val helpLabel: JLabel = JLabel(AllIcons.General.ContextHelp)

    init {
        val exampleDateFormats = arrayOf("EEEE, MMMM d, yyyy", "yyyy-MM-dd", "MM/dd/yyyy")
        val dateFormatHelpText = exampleDateFormats.joinToString(
            "<br/>", "<html>", "</html>",
        ) {
            "$it (${DATE_PREVIEW.format(it)})"
        }

        @Suppress("DialogTitleCapitalization")
        HelpTooltip()
            .setTitle("Example date formats")
            .setDescription(dateFormatHelpText)
            .setLink(
                "Date Time Formatter Reference",
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