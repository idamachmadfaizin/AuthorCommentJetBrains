package com.idamachmadfaizin.authorcomment

import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.components.*
import com.intellij.util.xmlb.XmlSerializerUtil

@State(
    name = "AuthorDateSettings",
    storages = [Storage("authorDatePlugin.xml")]
)
class AuthorDateSettings : PersistentStateComponent<AuthorDateSettings> {
    var authorName: String = "MyName"
    var dateFormat: String = "EEEE, MMMM d, yyyy"

    companion object {
        fun getInstance(): AuthorDateSettings = ApplicationManager.getApplication().getService(AuthorDateSettings::class.java)
    }

    override fun getState(): AuthorDateSettings = this

    override fun loadState(state: AuthorDateSettings) {
        XmlSerializerUtil.copyBean(state, this)
    }
}