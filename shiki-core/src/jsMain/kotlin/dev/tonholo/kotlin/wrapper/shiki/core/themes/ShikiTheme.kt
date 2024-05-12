package dev.tonholo.kotlin.wrapper.shiki.core.themes

sealed interface ShikiTheme {
    val name: String

    data object Andromeeda : ShikiTheme {
        override val name = "andromeeda"
    }

    data object AuroraX : ShikiTheme {
        override val name = "aurora-x"
    }

    data object AyuDark : ShikiTheme {
        override val name = "ayu-dark"
    }

    data object CatppuccinFrappe : ShikiTheme {
        override val name = "catppuccin-frappe"
    }

    data object CatppuccinLatte : ShikiTheme {
        override val name = "catppuccin-latte"
    }

    data object CatppuccinMacchiato : ShikiTheme {
        override val name = "catppuccin-macchiato"
    }

    data object CatppuccinMocha : ShikiTheme {
        override val name = "catppuccin-mocha"
    }

    data object DarkPlus : ShikiTheme {
        override val name = "dark-plus"
    }

    data object Dracula : ShikiTheme {
        override val name = "dracula"
    }

    data object DraculaSoft : ShikiTheme {
        override val name = "dracula-soft"
    }

    data object GitHubDark : ShikiTheme {
        override val name = "github-dark"
    }

    data object GitHubDarkDefault : ShikiTheme {
        override val name = "github-dark-default"
    }

    data object GitHubDarkDimmed : ShikiTheme {
        override val name = "github-dark-dimmed"
    }

    data object GitHubLight : ShikiTheme {
        override val name = "github-light"
    }

    data object GitHubLightDefault : ShikiTheme {
        override val name = "github-light-default"
    }

    data object Houston : ShikiTheme {
        override val name = "houston"
    }

    data object LightPlus : ShikiTheme {
        override val name = "light-plus"
    }

    data object MaterialTheme : ShikiTheme {
        override val name = "material-theme"
    }

    data object MaterialThemeDarker : ShikiTheme {
        override val name = "material-theme-darker"
    }

    data object MaterialThemeLighter : ShikiTheme {
        override val name = "material-theme-lighter"
    }

    data object MaterialThemeOcean : ShikiTheme {
        override val name = "material-theme-ocean"
    }

    data object MaterialThemePalenight : ShikiTheme {
        override val name = "material-theme-palenight"
    }

    data object MinDark : ShikiTheme {
        override val name = "min-dark"
    }

    data object MinLight : ShikiTheme {
        override val name = "min-light"
    }

    data object Monokai : ShikiTheme {
        override val name = "monokai"
    }

    data object NightOwl : ShikiTheme {
        override val name = "night-owl"
    }

    data object Nord : ShikiTheme {
        override val name = "nord"
    }

    data object OneDarkPro : ShikiTheme {
        override val name = "one-dark-pro"
    }

    data object OneLight : ShikiTheme {
        override val name = "one-light"
    }

    data object Poimandres : ShikiTheme {
        override val name = "poimandres"
    }

    data object Red : ShikiTheme {
        override val name = "red"
    }

    data object RosePine : ShikiTheme {
        override val name = "rose-pine"
    }

    data object RosePineDawn : ShikiTheme {
        override val name = "rose-pine-dawn"
    }

    data object RosePineMoon : ShikiTheme {
        override val name = "rose-pine-moon"
    }

    data object SlackDark : ShikiTheme {
        override val name = "slack-dark"
    }

    data object SlackOchin : ShikiTheme {
        override val name = "slack-ochin"
    }

    data object SnazzyLight : ShikiTheme {
        override val name = "snazzy-light"
    }

    data object SolarizedDark : ShikiTheme {
        override val name = "solarized-dark"
    }

    data object SolarizedLight : ShikiTheme {
        override val name = "solarized-light"
    }

    data object Synthwave84 : ShikiTheme {
        override val name = "synthwave-84"
    }

    data object TokyoNight : ShikiTheme {
        override val name = "tokyo-night"
    }

    data object Vesper : ShikiTheme {
        override val name = "vesper"
    }

    data object VitesseBlack : ShikiTheme {
        override val name = "vitesse-black"
    }

    data object VitesseDark : ShikiTheme {
        override val name = "vitesse-dark"
    }

    data object VitesseLight : ShikiTheme {
        override val name = "vitesse-light"
    }
}
