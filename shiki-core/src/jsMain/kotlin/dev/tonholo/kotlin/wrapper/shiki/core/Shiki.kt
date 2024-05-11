package dev.tonholo.kotlin.wrapper.shiki.core

import dev.tonholo.kotlin.wrapper.shiki.core.types.CodeToHastOptions
import kotlinx.browser.document
import kotlinx.coroutines.await
import org.w3c.dom.HTMLScriptElement
import org.w3c.dom.events.Event
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

@JsExport
@OptIn(ExperimentalJsExport::class)
class Shiki internal constructor(api: ShikiApi) : ShikiApi by api {
    @JsExport.Ignore
    suspend fun codeToHtml(
        code: String,
        options: CodeToHastOptions,
    ): String = codeToHtmlPromise(code, options).await()

    companion object {
        lateinit var instance: Shiki
        private const val SHIKI_SCRIPT_ID = "shiki-loader"
        private const val SHIKI_LOADED_EVENT = "shiki-module-loaded"

        operator fun invoke(api: ShikiApi) {
            if (::instance.isInitialized) return
            console.log("initialize Shiki", api)
            instance = Shiki(api)
            document.dispatchEvent(Event(SHIKI_LOADED_EVENT))
        }

        @JsExport.Ignore
        suspend fun initialize() = suspendCoroutine { continuation ->
            if (::instance.isInitialized) {
                continuation.resume(instance)
                return@suspendCoroutine
            }
            if (js.globals.globalThis.KotlinWrapperShiki == undefined) {
                js.globals.globalThis.KotlinWrapperShiki = Shiki
            }

            document.addEventListener(SHIKI_LOADED_EVENT, callback = {
                println("shiki loaded.")
                continuation.resume(instance)
            }, false)

            val isLoaderNotPresent = document.querySelector("script[id='$SHIKI_SCRIPT_ID']") == null
            if (isLoaderNotPresent) {
                (document.createElement("script") as HTMLScriptElement).apply {
                    id = SHIKI_SCRIPT_ID
                    type = "module"
                    // TODO: use version from config.
                    textContent = """
                    |import * as shiki from 'https://esm.run/shiki@1.5.1'
                    |
                    |KotlinWrapperShiki.invoke(shiki)
                """.trimMargin()
                }.also { script ->
                    document.body?.appendChild(script)
                }
            }
        }
    }
}
