package cyolo.cyolo.service

import org.apache.logging.log4j.kotlin.logger
import org.springframework.stereotype.Service
import java.util.*


@Service
class WordsProcessor(
    private val counter: WordCounter
) {

    private val log = logger()

    suspend fun parse(input: String) {
        input.split(',').forEach { counter.count(it) }
    }

    suspend fun getRank(): Map<String, Int> {
        val counts = counter.wordMap.also { log.trace(it) }

        return when (counts.size) {
            0 -> mapOf()
            else -> {
                val topWords = PriorityQueue<Pair<String, Int>>(5, compareBy { -it.second }).also { queue ->
                    counts.forEach { queue.add(Pair(it.key, it.value)) }
                }.let { queue ->
                    (1..(queue.size.takeIf { it<5 } ?: 5)).associate { queue.poll() }
                }

                topWords.mapRank(topWords.values.first(), topWords.values.last())
            }
        }
    }

    private fun Map<String, Int>.mapRank(max: Int, min: Int): Map<String, Int> {
        return if (max == min) {
            map { (word, _) -> word to 1 }.toMap()
        } else {
            map { (word, count) -> word to (4 * (count - min) / (max - min) + 1) }.toMap() //counts.size?
        }
    }

}
