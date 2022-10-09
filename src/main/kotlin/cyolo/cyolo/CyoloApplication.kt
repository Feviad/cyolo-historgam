package cyolo.cyolo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CyoloApplication

fun main(args: Array<String>) {
	runApplication<CyoloApplication>(*args)
}
