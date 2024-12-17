package com.idamachmadfaizin.authorcomment

import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage
import com.intellij.util.xmlb.XmlSerializerUtil

@State(
    name = "AuthorCommentSettings",
    storages = [Storage("authorCommentPlugin.xml")]
)
class Settings : PersistentStateComponent<Settings> {
    var authorName: String = "Author Name"
    var dateFormat: String = "EEEE, MMMM d, yyyy"

    companion object {
        fun getInstance(): Settings =
            ApplicationManager.getApplication().getService(Settings::class.java)
    }

    override fun getState(): Settings = this

    override fun loadState(state: Settings) {
        XmlSerializerUtil.copyBean(state, this)
    }
}