package cyolo.cyolo.service

import org.apache.logging.log4j.kotlin.logger
import org.springframework.stereotype.Service
import java.util.concurrent.ConcurrentHashMap

@Service
class WordCounter {

    private val log = logger()

    val wordMap: ConcurrentHashMap<String, Int> = ConcurrentHashMap()


    suspend fun count(word: String) {
        wordMap.compute(word) { _, value -> (value ?: 1) + 1 }.also { log.trace("$word: $it") }
    }

}
