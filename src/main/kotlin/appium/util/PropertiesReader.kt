package appium.util

import java.util.Properties

class PropertiesReader {
    inline fun <reified T : Any> getProp(key: String): T {
        val props = javaClass.classLoader.getResourceAsStream("config.properties").use {
            if (it == null) throw IllegalStateException("config.properties file not found")
            Properties().apply { load(it) }
        }

        val value = props.getProperty(key) ?: throw IllegalArgumentException("Property $key not found")

        return when (T::class) {
            String::class -> value as T
            Int::class -> value.toInt() as T
            Boolean::class -> value.toBoolean() as T
            else -> throw IllegalArgumentException("Unsupported property type for $key")
        }
    }
}
