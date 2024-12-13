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
class AuthorCommentSettings : PersistentStateComponent<AuthorCommentSettings> {
    var authorName: String = "AuthorName"
    var dateFormat: String = "EEEE, MMMM d, yyyy"

    companion object {
        fun getInstance(): AuthorCommentSettings =
            ApplicationManager.getApplication().getService(AuthorCommentSettings::class.java)
    }

    override fun getState(): AuthorCommentSettings = this

    override fun loadState(state: AuthorCommentSettings) {
        XmlSerializerUtil.copyBean(state, this)
    }
}